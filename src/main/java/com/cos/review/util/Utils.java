package com.cos.review.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
	public static String dayParse(String date) {
		LocalDate todayLocalDate = LocalDate.now();

		if(date.contains("어제")) {
			return todayLocalDate.minusDays(1).toString();
		}else if(date.contains("일 전")) {
			char minus = date.charAt(0);
			System.out.println("minus :" + minus);
			int n = minus-48;
			System.out.println(n);
			System.out.println(todayLocalDate.minusDays(n));
			return todayLocalDate.minusDays(n).toString();
		}else if(date.contains("시간 전")){
			return todayLocalDate.minusDays(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}else {
			return date.substring(0, date.length()-1).replace(".", "-");
		}
	}
}
