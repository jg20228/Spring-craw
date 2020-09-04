package com.cos.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.review.model.SearchKeyword;

public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, Integer>{
	//Optional 걸어서 처리하는게 좋다.
	SearchKeyword findByKeyword(String keyword);
}
