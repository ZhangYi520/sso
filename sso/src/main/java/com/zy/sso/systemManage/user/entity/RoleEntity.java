package com.zy.sso.systemManage.user.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class RoleEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -6761134573138338836L;

	private Integer id;//主键
	private String roleName;//角色描述
	private List<AuthorEntity> author;//权限集合
}
