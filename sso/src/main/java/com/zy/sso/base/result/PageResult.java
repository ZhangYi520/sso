package com.zy.sso.base.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 **************************************************
 * @Description:通用分页返回参数
 * @author:ZY
 * @date:2019年01月14日
 **************************************************
 */
@Data
@Getter
@Setter
@ApiModel
public class PageResult{

	@ApiModelProperty(name = "pages", value = "总页数", dataType = "Integer")
	private Integer pages = 0;

	@ApiModelProperty(name = "total", value = "总条数", dataType = "Integer")
	private Integer total = 0;
	
	@ApiModelProperty(name = "page", value = "当前页码", dataType = "Integer")
	private Integer page = 0;

	@ApiModelProperty(name = "pageSize", value = "每页显示条数", dataType = "Integer")
	private Integer pageSize = 10;
	
	public PageResult() {
		super();
	}

	public PageResult(Integer page,Integer pageSize,Integer total) {
		super();
		this.total = total;
		this.page = page;
		this.pageSize = pageSize;
		if(total>0) {
			this.pages = (total%pageSize)!=0?(total/pageSize+1):(total/pageSize);
		}
	}	
}
