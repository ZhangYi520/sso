package com.zy.sso.user.entity;


import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel //swagger提示
public class UserEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -5366358180307579246L;
	@ApiModelProperty(name = "id", value = "主键", required = false, dataType = "Integer")
	private Integer id;//主键
	private String userName;//用户名
	private String password;//密码
	private List<RoleEntity> role;//角色集合
	
}