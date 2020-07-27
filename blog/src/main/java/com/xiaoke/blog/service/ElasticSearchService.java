package com.xiaoke.blog.service;

import java.io.IOException;
import java.util.Map;

public interface ElasticSearchService {

    Map<Object, Object> searchPage(String keyword, Integer currentPage, Integer pageSize) throws IOException;
}
