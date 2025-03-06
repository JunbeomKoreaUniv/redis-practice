package com.example.redis_recentSearch.entity;

import lombok.Data;

@Data
public class SearchHistory {

    private String userId;
    private String searchKeyword;
    private String searchDt;
}
