package com.xiaoke.blog;

import com.alibaba.fastjson.JSON;
import com.xiaoke.blog.entity.EUser;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    // 测试索引的创建
    @Test
    public void testCreateIndex() throws IOException {
        // 1.创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("小科博客");
        // 2. 客户端执行请求
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    // 测试获取索引是否存在
    @Test
    public void testExitIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("小科博客");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    //测试删除索引
    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("小科博客");
        // 删除
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    // 测试添加文档
    @Test
    public void testAddDocument() throws IOException {
        // 创建对象
        EUser eUser = new EUser("小科",3);
        // 创建请求
        IndexRequest request = new IndexRequest("小科博客");

        // 规则 put/xiaoke_index/_doc/1
        request.id("1");
        // 设置超时
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        // 将我们胡数据放入到请求 json
        IndexRequest source = request.source(JSON.toJSONString(eUser), XContentType.JSON);

        // 客户端发送请求,获取响应的结果
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());
    }

    // 获取文档 判断是否存在 get/index/doc/1
    @Test
    public void testExists() throws IOException {
        GetRequest getRequest = new GetRequest("xiaoke_index", "1");
        // 不获取返回的_sourced的上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");

        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // 获取文档信息
    @Test
    public void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("xiaoke_index", "1");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString()); // 打印文档内容
        System.out.println(getResponse); // 这里返回的全部内容和命令是一样的
    }

    // 更新文档内容
    @Test
    public void testUpdateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("xiaoke_index", "1");
        updateRequest.timeout("1s");

        EUser eUser = new EUser("小科说Java", 21);
        updateRequest.doc(JSON.toJSONString(eUser), XContentType.JSON);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
    }

    // 删除文档记录
    @Test
    public void testDeleteRequest() throws IOException {
        DeleteRequest request = new DeleteRequest("xiaoke_index", "1");
        request.timeout("1s");
        DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
    }

    // 特殊的，真的项目一般都会批量插入数据
    @Test
    public void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<EUser> eUsers = new ArrayList<>();
        eUsers.add(new EUser("xiaoke1", 3));
        eUsers.add(new EUser("xiaoke2", 3));
        eUsers.add(new EUser("xiaoke3", 3));
        eUsers.add(new EUser("xiaoke4", 3));

        for (int i = 0; i < eUsers.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("xiaoke_index")
                    .id("" + (i + 1))
                    .source(JSON.toJSONString(eUsers.get(i)),XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures()); // 是否插入失败 返回false表示成功
    }

    // 查询
    // SearchRequest 搜索请求
    // SearchSourceBuilder 条件构造
    // HighlightBuilder 构建高亮
    // TermQueryBuilder 精确查询
    // xxx QueryBuilder 对应我们刚刚看到的命令
    @Test
    public void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("xiaoke_index");
        // 构建搜索的条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 查询条件，QueryBuilders工具类来实现
        // QueryBuilders.termQuery 精确
        //  QueryBuilders.matchAllQuery 匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "xiaoke2");
       // MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("=============================================");
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }
    }
}
