package com.zy.sso.base.util.sendNote;

import java.io.Serializable;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 **************************************************
 * @Description:短语接口相应信息
 * @author:CSX
 * @date:2019年01月14日
 **************************************************
 */
@ApiModel
@Getter
@Setter
@Data
public class ResponseInfo implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -993440149090266047L;
	
	@ApiModelProperty(name = "code", value = "响应编码", required = false, dataType = "String")
	private String  code;
	@ApiModelProperty(name = "phone", value = "目标手机号码", required = false, dataType = "String")
	private String  phone;
	@ApiModelProperty(name = "receiveTime", value = "平台接收本次操作时间，yyyymmddHHmiss格式字符串", required = false, dataType = "String")
	private String  receiveTime;
	@ApiModelProperty(name = "responseTime", value = "平台响应时间，即阿里云响应的时间，yyyymmddHHmiss格式字符串", required = false, dataType = "String")
	private String  responseTime;
	@ApiModelProperty(name = "content", value = "响应内容", required = false, dataType = "String")
	private String  content;  
}
