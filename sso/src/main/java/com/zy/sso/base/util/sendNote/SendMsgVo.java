package com.zy.sso.base.util.sendNote;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 **************************************************
 * @Description:短语接口实体类信息
 * @author:CSX
 * @date:2019年01月14日
 **************************************************
 */
@ApiModel
@Getter
@Setter
@Data
public class SendMsgVo implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -4450646086279381430L;

	@ApiModelProperty(name = "requestId", value = "请求Id，唯一随机数，用于防止网络重放攻击，每一次请求都使用不同的随机数，采用uuid格式", required = false, dataType = "String")
	private String requestId;
	@ApiModelProperty(name = "String", value = "目标手机号码，最多100个，用“|”分隔", required = false, dataType = "String")
	private String phone;
	
	@ApiModelProperty(name = "signature", value = "签名", required = false, dataType = "String")
	private String signature;
	@ApiModelProperty(name = "templateCode", value = "模板code。注册：SMS_161593659；找回密码：SMS_161598619；企业服务查询码：SMS_161598625", required = false, dataType = "String")
	private String templateCode;

	
	@ApiModelProperty(name = "variable", value = "请求Id，唯一随机数，用于防止网络重放攻击，每一次请求都使用不同的随机数，采用uuid格式", required = false, dataType = "Map")
	private List<Map<String,String>> variable;
	
	@ApiModelProperty(name = "requestId", value = "用户唯一凭证", required = false, dataType = "String")
	private String token;
	@ApiModelProperty(name = "String", value = "本次操作的唯一编码，服务器生成", required = false, dataType = "String")
	private String operId;
	@ApiModelProperty(name = "ResponseInfo", value = "响应信息", required = false, dataType = "ResponseInfo")
	private ResponseInfo responseInfo;
	@ApiModelProperty(name = "queryCode", value = "查询码", required = false, dataType = "String")
	private String queryCode;
	
	@ApiModelProperty(name = "username", value = "用户名", required = false, dataType = "String")
	private String username;
}
