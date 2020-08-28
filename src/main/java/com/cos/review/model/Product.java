package com.cos.review.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.review.util.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=1000)
	private String blogUrl;
	private String title;
	
	@Column(length=1000)
	private String thumnail;
	private String day;
	
	@CreationTimestamp
	private Timestamp createDate;

	public void setId(int id) {
		this.id = id;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setThumnail(String thumnail) {
		this.thumnail = thumnail;
	}

	public void setDay(String day) {
		this.day = Utils.dayParse(day);
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
}
