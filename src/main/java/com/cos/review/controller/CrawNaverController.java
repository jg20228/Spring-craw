package com.cos.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.review.model.Product;
import com.cos.review.model.SearchKeyword;
import com.cos.review.repository.ProductRepository;
import com.cos.review.repository.SearchKeywordRepository;
import com.cos.review.util.CrawNaverBlog;

@Controller
public class CrawNaverController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SearchKeywordRepository searchKeywordRepository;
	
	@GetMapping("/searchKeyword")
	public @ResponseBody List<SearchKeyword> searchKeyword(){
		System.out.println("searchKeyword 호출됨");
		return searchKeywordRepository.findAll();
	}
	
	@GetMapping({"/product","product/{keyword}"})
	public @ResponseBody List<Product> product(@PathVariable String keyword){
		System.out.println("product 호출됨");
		return productRepository.findAll();
	}

	@GetMapping("/craw/naver")
	public String crawNaver(Model model) {
		model.addAttribute("keywords", searchKeywordRepository.findAll());
		return "craw_naver";
	}
	
	@GetMapping("/craw/list")
	public String crawList(Model model) {
		model.addAttribute("keywords", searchKeywordRepository.findAll());
		return "craw_list";
	}
	
	@GetMapping("/craw/clear")
	public String crawClear(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "craw_clear";
	}
	
	@PostMapping("/craw/naver/proc")
	//@RequestParam("keyword") String abc -> 변수명만 잘 지으면 쓸데없는 코드를 지우자
	public @ResponseBody String postNaver(String keyword) {
		System.out.println(keyword);
		List<Product> products = new CrawNaverBlog().startAllCraw(keyword);
		SearchKeyword searchKeywordEntity = searchKeywordRepository.findByKeyword(keyword);
		System.out.println(keyword);
		System.out.println(searchKeywordEntity);
		for (Product product : products) {
			product.setKeyword(searchKeywordEntity);
		}
		//axios = fetch = ajax 
		productRepository.saveAll(products);
		return "성공";
	}
	 
	
	@PostMapping("/craw/keyword/proc")
	public String crawKeywordProc(String keyword) {
		SearchKeyword entity = SearchKeyword.builder()
				.keyword(keyword)
				.build();
		searchKeywordRepository.save(entity);
		return "redirect:/craw/list";
	}
	
	@DeleteMapping("/craw/keyword/delete/{id}")
	public ResponseEntity<?> crawKeywordDelete(@PathVariable int id) {
		//ResponseEntity를 사용하면 @ResponseBody 쓴 효과랑 같음
		searchKeywordRepository.deleteById(id);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
}
