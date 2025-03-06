package com.example.redis_recentSearch.service;

import com.example.redis_recentSearch.repository.RecentSearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecentSearchService {

    private RecentSearchRepository recentSearchRepository;
    public RecentSearchService(RecentSearchRepository recentSearchRepository) {
        this.recentSearchRepository = recentSearchRepository;
    }

    public List<String> getRecentSearchList(String userId) {
        return recentSearchRepository.getRecentSearch(userId);
    }

    public void addRecentSearchList(String userId, String keyword) {
        recentSearchRepository.addRecentSearch(userId, keyword);
    }
}
