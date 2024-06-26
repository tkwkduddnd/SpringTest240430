package com.ezen.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
public class MemberVO {

	/*
	 * create table member( id varchar(100), pw varchar(256) not null, name
	 * varchar(100), email varchar(100), home varchar(100), age int, reg_date
	 * datetime default now(), last_login datetime default now(), primary key(id));
	 */
	private String id;
	private String pw;
	private String name;
	private String email;
	private String home;
	private int age;
	private String reg_date;
	private String last_login;
}
