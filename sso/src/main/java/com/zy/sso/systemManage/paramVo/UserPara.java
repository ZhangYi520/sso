package com.zy.sso.systemManage.paramVo;

import java.io.Serializable;

import com.zy.sso.base.result.PageRequest;
import com.zy.sso.file.vo.PageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel //swagger提示
public class UserPara extends PageRequest implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -5366358180307579246L;
	@ApiModelProperty(name = "id", value = "主键", required = false, dataType = "Integer")
	private Integer id;//主键
	private String userName;//用户名
	private String headImg="";//头像地址
	private String ct;//创建时间
	
}
