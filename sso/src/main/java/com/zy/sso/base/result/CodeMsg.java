package com.zy.sso.base.result;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * 
 **************************************************
 * @Description:通用后台CODE错误码
 * @author:ZY
 * @date:2019年01月14日
 **************************************************
 */
@Data
@Getter
@Setter
public class CodeMsg {
	
	private int code;
	private String msg;
	
	
	//通用的错误码 5001XX
	public static CodeMsg BASE_SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg BASE_SERVER_ERROR = new CodeMsg(500000, "服务端异常");
	public static CodeMsg BASE_BIND_ERROR = new CodeMsg(500001, "参数校验异常");
	public static CodeMsg BASE_LOGIN_ERROR = new CodeMsg(500002, "未登录");
	public static CodeMsg UN_AUTHORIZED = new  CodeMsg(500003, "用户授权认证未通过,客户端请求参数中token无效!");
	public static CodeMsg HAS_NO_PRIVILEGE = new  CodeMsg(500004, "用户无权限,请联系管理员!");
	public static CodeMsg NAME_IS_EXSITE = new CodeMsg(500005, "名称已存在");
	public static CodeMsg PARAMETER_ERROR = new CodeMsg(500006, "参数错误");
	public static CodeMsg KAPTCHA_ERROR = new CodeMsg(500007, "验证码错误");
	public static CodeMsg DATA_ERROR = new CodeMsg(500008, "数据为空");
	public static CodeMsg SQL_ERROR = new CodeMsg(500009, "SQL执行错误");
	public static CodeMsg KAPTCHA_NOSEND = new CodeMsg(500010, "验证码未发送，请发送验证码后再试！");

	//文件上传502XXX
	public static CodeMsg FILE_ERROR = new CodeMsg(502001, "文件上传失败");
	public static CodeMsg FILE_TYPE_ERROR = new CodeMsg(502002, "文件类型错误");
	public static CodeMsg FILE_SIZE_ERROR = new CodeMsg(502003, "文件大小超出范围");
	public static CodeMsg FILE_NULL_ERROR = new CodeMsg(502004, "文件为空");
	
	
	public CodeMsg( int code,String msg ) {
		this.code = code;
		this.msg = msg;
	}	
	public CodeMsg fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code, message);
	}
	
}
