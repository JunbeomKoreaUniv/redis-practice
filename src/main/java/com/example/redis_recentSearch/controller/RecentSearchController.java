package com.example.redis_recentSearch.controller;

import com.example.redis_recentSearch.service.RecentSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecentSearchController {

    private RecentSearchService recentSearchService;

    RecentSearchController(RecentSearchService recentSearchService) {
        this.recentSearchService = recentSearchService;
    }

    // web 검색 --> kafka에 기록을 넣고 --> spring consuming --> redis // 지금처럼 api를 만들기보다는 이러한 방식이 더 일반적.
    // 아래의 방식은 하나의 api에 디펜던시가 생김.
    // web 검색 --> spring api 호출 --> redis // 그러나 일단 이 프로젝트는 경험이 목적이므로 이렇게 진행
    // 검색 데이터 입력
    @PostMapping("/recentsearch/{userId}")
    public ResponseEntity<?> searchRecord(@PathVariable String userId, @RequestParam String keyword) {
        recentSearchService.addRecentSearchList(userId, keyword);
        return ResponseEntity.ok("record success");
    }

    // 최근 검색 10개 (list)
    @GetMapping("/recent/search/{userId}")
    public ResponseEntity<?> getRecentSearch(@PathVariable String userId) {
        return ResponseEntity.ok(recentSearchService.getRecentSearchList(userId));
    }
}
