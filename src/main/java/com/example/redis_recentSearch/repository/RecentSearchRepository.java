package com.example.redis_recentSearch.repository;

import com.example.redis_recentSearch.entity.SearchHistory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecentSearchRepository {

    // redis와 연결
    private RedisTemplate<String, String> redisTemplate;

    // 아래 방식도 가능. value를 원하는 객체로
    // private RedisTemplate<String, SearchHistory> objectRedisTemplate;

    public RecentSearchRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // lpush (limit 10)
    public void addRecentSearch(String userId, String keyword) {
        String key = "recent_search" + userId; // recent_search:junbeom
        redisTemplate.opsForList().leftPush(key, keyword);
        redisTemplate.opsForList().trim(key, 0, 9);
    }

    // range (get 10)
    public List<String> getRecentSearch(String userId) {
        String key = "recent_search" + userId;
        return redisTemplate.opsForList().range(key, 0, -1);
    }
}
