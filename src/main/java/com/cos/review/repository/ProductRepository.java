package com.cos.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.review.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findByKeywordId(int keywordId);
}
