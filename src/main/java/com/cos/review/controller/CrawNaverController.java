package com.cos.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.review.model.Product;
import com.cos.review.repository.ProductRepository;
import com.cos.review.util.CrawNaverBlog;

@Controller
public class CrawNaverController {
	
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/craw/naver")
	public String crawNaver() {
		return "craw_naver";
	}
	
	@PostMapping("/craw/naver/proc")
	//@RequestParam("keyword") String abc -> 변수명만 잘 지으면 쓸데없는 코드를 지우자
	public @ResponseBody String postNaver(String keyword) {
		System.out.println(keyword);
		List<Product> products = new CrawNaverBlog().startAllCraw(keyword); 
		//axios = fetch = ajax 
		productRepository.saveAll(products);
		return "성공";
	}
}
