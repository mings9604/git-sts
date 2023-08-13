package com.icia.board.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardDto {
	private int b_num;
	private String b_title; 
	private String b_contents; 
	private String b_id;  
	private String b_name;   
	private Timestamp b_date;   //오라클Date, String 서로호환
	private String b_views;
	
}
