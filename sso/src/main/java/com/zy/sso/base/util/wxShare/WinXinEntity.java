package com.zy.sso.base.util.wxShare;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
@ApiModel
/**
 * 
 ***********************************
 * @Description 微信分享实体类
 * @author zhangyi
 * @date 2019年1月25日
 ***********************************
 */
public class WinXinEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1296097656543925857L;
	private String access_token;
    private String ticket ;
    private String noncestr;
    private String timestamp;
    private String str;
    private String signature;
    private String appId="wxfe251748804d0981";
    //private String url;
}
