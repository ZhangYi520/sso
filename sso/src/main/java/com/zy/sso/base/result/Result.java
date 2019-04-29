package com.zy.sso.base.result;

import com.github.pagehelper.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 **************************************************
 * @Description:通用返回结果集
 * @author:ZY
 * @date:2019年01月14日
 **************************************************
 */
@Data
@Getter
@Setter
@ApiModel
public class Result<T> extends PageResult{
	
	@ApiModelProperty(name = "code", value = "状态码", dataType = "Integer")
	private int code;
	
	@ApiModelProperty(name = "msg", value = "状态描述", dataType = "String")
	private String msg;
	
	@ApiModelProperty(name = "data", value = "数据对象", dataType = "T")
	private T data;
	
	/**
	 *  成功时候的调用
	 * */
	@SuppressWarnings("unchecked")
	public static  <T> Result<T> success(T data){
		if(data instanceof Page<?>) {
			Page<T> page = (Page<T>) data;
			return new Result<T>(data, page.getPageNum(), page.getPageSize(), (int)page.getTotal());
		} else {
			return new Result<T>(data);
		}
	}
	
	public static <T> Result<T> success() {
		Result<T> r = new Result<>();
		r.setCode(CodeMsg.BASE_SUCCESS.getCode());
		r.setMsg(CodeMsg.BASE_SUCCESS.getMsg());
		r.setData(null);
		return r;
	}
	
	
	public static <T> Result<T> success(CodeMsg codeMsg,T data) {
		Result<T> r = new Result<>();
		r.setCode(codeMsg.getCode());
		r.setMsg(codeMsg.getMsg());
		r.setData(data);
		return r;
	}
	
	/**
	 * 成功时候的调用,且返回列表数据有分页
	 */
	public static  <T> Result<T> success(T data,Integer page,Integer pageSize,Integer total){
		return new Result<T>(data,page,pageSize,total);
	}
	
	/**
	 *  失败时候的调用
	 * */
	public static  <T> Result<T> error(CodeMsg codeMsg){
		return new Result<T>(codeMsg);
	}
	/**
	 *  失败时候的调用,有返回对象
	 * */
	public static  <T> Result<T> error(CodeMsg codeMsg,T data){
		return new Result<T>(codeMsg,data);
	}
	
	public Result() {}
	
	private Result(T data) {
		this.code = CodeMsg.BASE_SUCCESS.getCode();
		this.msg = CodeMsg.BASE_SUCCESS.getMsg();
		this.data = data;
	}
	
	private Result(T data,Integer page,Integer pageSize,Integer total) {
		super(page,pageSize,total);
		this.code = CodeMsg.BASE_SUCCESS.getCode();
		this.msg = CodeMsg.BASE_SUCCESS.getMsg();
		this.data = data;
	}
	
	private Result(CodeMsg codeMsg,T data) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
			this.data = data;
		}
	}
	
	private Result(CodeMsg codeMsg) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
	}		
}
