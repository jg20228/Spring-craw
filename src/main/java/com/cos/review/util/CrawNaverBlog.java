package com.cos.review.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cos.review.model.Product;
import com.cos.review.model.SearchKeyword;

public class CrawNaverBlog {
	
	public List<Product> startDayCraw(String keyword){
		//오늘 날짜만 퍼면된다.
		//day를 들고있기도하는데 1일전, 2일전도 가지고 있음
	
		
		return null;
	}
	public List<Product> startAllCraw(String keyword){
			//String keyword = "갤럭시20";
			int start = 1; // 10씩 증가하면 됨.
			int fail = 0;
			
			List<Product> products = new ArrayList<>();
			while (start!=1001) {
				String url = "https://search.naver.com/search.naver?date_from=&date_option=0&date_to=&dup_remove=1&nso=&post_blogurl=&post_blogurl_without=&query="+keyword+"&sm=tab_pge&srchby=all&st=sim&where=post&start="+start;
				
				try {
					Document doc = Jsoup.connect(url).get();
					Elements els1 = doc.select(".blog .sh_blog_top .sh_blog_title");
					Elements els2 = doc.select(".blog .sh_blog_top .txt_inline");
					Elements els3 = doc.select(".blog .sh_blog_top .sp_thmb img");
					for (int i = 0; i < els1.size(); i++) {
						Product product = new Product();
						product.setTitle(els1.get(i).attr("title"));
						product.setBlogUrl(els1.get(i).attr("href"));
						product.setDay(els2.get(i).text());
						product.setThumnail(els3.get(i).attr("src"));
						//set,get
						products.add(product);
						System.out.println(i);
					}
				} catch (Exception e) {
					fail++;
					e.printStackTrace();
				}
				for (Product product : products) {
					System.out.println("===========================");
					System.out.println("제목 : " + product.getTitle());
					System.out.println("주소 : " + product.getBlogUrl());
					System.out.println("섬네일 : " + product.getThumnail());
					System.out.println("날짜 : " + product.getDay());
					System.out.println();
				}
				System.out.println(start);
				start = start + 10;
				System.out.println(url);
		}
		return products;
	}
	
}
