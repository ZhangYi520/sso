package com.zy.sso.systemManage.user.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AuthorEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -3093813205494664299L;
	
	private Long menuId;//id

    private String menuName;//菜单名称

    private Long parentId;//父类id

    private Integer orderNum;//显示顺序

    private String url;//菜单url

    private String menuType;//类型:0目录,1菜单,2按钮

    private String visible;//菜单状态:0显示,1隐藏

    private String perms;//权限字符串

    private String icon;//菜单图标

    private String createBy;//创建人

    private Date createTime;//创建时间

    private String updateBy;//修改人

    private Date updateTime;//修改时间

    private String remark;
}
