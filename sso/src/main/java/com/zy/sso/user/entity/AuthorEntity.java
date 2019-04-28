package com.zy.sso.user.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthorEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -3093813205494664299L;
	
	private Integer id;//主键
	private String action;//操作
}
