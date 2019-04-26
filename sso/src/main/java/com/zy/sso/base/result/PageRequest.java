package com.zy.sso.base.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 **************************************************
 * @Description:通用分页请求参数
 * @author:CSX
 * @date:2019年01月14日
 **************************************************
 */
@Data
@Getter
@Setter
@ApiModel
public class PageRequest {

	@ApiModelProperty(name = "page", value = "当前页码,默认1", required = false, dataType = "Integer")
	private Integer page = 1;

	@ApiModelProperty(name = "pageSize", value = "每页显示条数,默认10", required = false, dataType = "Integer")
	private Integer pageSize = 10;
	
	@ApiModelProperty(name = "sort", value = "排序名称,默认根据id排序", required = false, dataType = "String")
	private String sort = "id";

	@ApiModelProperty(name = "sortOrder", value = "默认asc排序", required = false, dataType = "String")
	private String sortOrder = "asc";
	
	@ApiModelProperty(name = "startIndex", value = "分页-开始位置",hidden = true)
	private Integer startIndex = pageSize*(page-1);
	
	public void calc() {
		this.startIndex = pageSize*(page-1);
	}
	
	public PageRequest() {
		this.startIndex = pageSize*(page-1);
	}
	
	public PageRequest(Integer page, Integer pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.startIndex = pageSize*(page-1);
	}

	public PageRequest(int pageSize2) {
		this.pageSize = pageSize2;
		this.startIndex = pageSize*(page-1);
	}
}
