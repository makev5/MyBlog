package com.xiaoke.blog.service.impl;


import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.xiaoke.blog.VO.BlogUserTabSortVO;
import com.xiaoke.blog.mapper.BlogMapper;
import com.xiaoke.blog.service.ElasticSearchService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    /**
     *  从数据库中获取博客列表存到ES文档中
     * @return
     * @throws IOException
     */
    public Boolean saveToES() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        List<BlogUserTabSortVO> blogList = blogMapper.getBlogList();
        for (int i = 0; i < blogList.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("blog_search")
                            .id("" + (i + 1))
                            .source(JSON.toJSONString(blogList.get(i)), XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        return !bulkResponse.hasFailures();
    }

    /**
     *  实现搜索功能
     * @param keyword
     * @return
     */
    @Override
    public Map<Object, Object> searchPage(String keyword, Integer currentPage, Integer pageSize) throws IOException {
        // 从数据库中获取博客列表存到ES文档中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        List<BlogUserTabSortVO> blogList = blogMapper.getBlogList();
        for (int i = 0; i < blogList.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("blog_search")
                            .id("" + (i + 1))
                            .source(JSON.toJSONString(blogList.get(i)), XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        // 起始索引
        int start = (currentPage - 1) * pageSize;
        // 总页数
        int pages = 0;

        // 构造条件
        SearchRequest searchRequest = new SearchRequest("blog_search");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //分页
        sourceBuilder.from(start);
        sourceBuilder.size(pageSize);

        // 精准匹配
//        BoolQueryBuilder termQueryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.multiMatchQuery(keyword,"title","description")
//                .analyzer("ik_max_word").operator(Operator.OR));
//        MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, "title", "description").analyzer("ik_max_word");

//        MatchPhrasePrefixQueryBuilder matchPhrasePrefixQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("title", keyword);
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword, "title", "description");
        sourceBuilder.query(multiMatchQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 关键字高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.field("description");
        highlightBuilder.requireFieldMatch(true); // 多个高亮显示
        highlightBuilder.preTags("<span style='color: red;'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);

        // 执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // 解析结果
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit: searchResponse.getHits().getHits()){

            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            HighlightField description = highlightFields.get("description");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap(); // 原来的结果
            // 解析高亮字段
            if (title != null){
                Text[] fragments = title.fragments();
                String n_title = "";
                for (Text text: fragments){
                    n_title += text;
                }
                sourceAsMap.put("title", n_title); // 高亮字段替换掉原来的内容即可！
            }
            if (description != null){
                Text[] fragments = description.fragments();
                String n_description= "";
                for (Text text: fragments){
                    n_description += text;
                }
                sourceAsMap.put("description", n_description); // 高亮字段替换掉原来的内容即可！
            }

            list.add(sourceAsMap);
        }


        // 判断总页数
        if (blogList.size() % pageSize==0){
            pages = list.size() / pageSize;
        }else {
            pages = list.size() / pageSize + 1;
        }

        return MapUtil.builder()
                .put("total", list.size())
                .put("size", pageSize)
                .put("current", currentPage)
                .put("pages",pages)
                .put("records", list)
                .map();
    }

}
