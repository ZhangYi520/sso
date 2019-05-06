package com.zy.sso.systemManage.menu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import lombok.Data;


/**
 * @author Administrator
 *菜单列表
 */
@Data
public class Menu implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    
    private List<Menu> children = new ArrayList<Menu>();
   
}