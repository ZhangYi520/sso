package com.zy.sso.systemManage.user.entity;


import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel //swagger提示
public class UserEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -1145199645904399906L;
	@ApiModelProperty(name = "id", value = "主键", required = false, dataType = "Integer")
	private Integer id;//主键
	private String userName;//用户名
	private String password;//密码
	private String headImg="";//头像地址
	private String ct="";//头像地址
	private String token;//token
	private List<RoleEntity> role;//角色集合
	
}
