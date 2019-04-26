package com.zy.sso.base.util.wxShare;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zy.sso.base.util.RedisTemplateUtil;


/**
 * 
 **************************************************
 * @Description:请求微信公众号api工具类
 * @author:ZhangYi
 * @date:2019年01月14日
 **************************************************
 */
@Component
public class WeinXinUtil {
	
    public static WinXinEntity getWinXinEntity(String url) {
        WinXinEntity wx = new WinXinEntity();
        ValueOperations<String, Object> opsForValue = RedisTemplateUtil.redisTemplate.opsForValue();
        String access_token1= (String)RedisTemplateUtil.redisTemplate.boundHashOps("wx").get("access_token");//获取redis
        String ticket1= (String)RedisTemplateUtil.redisTemplate.boundHashOps("wx").get("ticket");//获取redis
        String access_token="";
        String ticket="";
        if(access_token1==null && ticket1==null) {
        	System.out.println("方法获取");
        	 access_token = getAccessToken();
             ticket = getTicket(access_token);
             System.out.println("access_token:"+access_token);
             System.out.println("ticket:"+ticket);
             RedisTemplateUtil.redisTemplate.boundHashOps("wx").put("access_token", access_token);
             RedisTemplateUtil.redisTemplate.boundHashOps("wx").put("ticket", ticket);
             RedisTemplateUtil.redisTemplate.boundHashOps("wx").expire(7100,TimeUnit.SECONDS);
        }
        else {
        	System.out.println("缓存获取");
        	access_token=access_token1;
        	ticket=ticket1;
        	 System.out.println("access_token:"+access_token);
             System.out.println("ticket:"+ticket);
        }
        
        Map<String, String> ret = Sign.sign(ticket, url);
        //System.out.println(ret.toString());
        wx.setTicket(ret.get("jsapi_ticket"));
        wx.setSignature(ret.get("signature"));
        wx.setNoncestr(ret.get("nonceStr"));
        wx.setTimestamp(ret.get("timestamp"));
        //wx.setUrl(ret.get("url"));
        return wx;
    }

    //获取token
    private static String getAccessToken() {  
        String access_token = "";  
        String grant_type = "client_credential";//获取access_token填写client_credential   
        String appId="wxfe251748804d0981";//第三方用户唯一凭证  
        String secret="40566775793fe8b59457c983a8ceb68b";//第三方用户唯一凭证密钥，即appsecret   
        //这个url链接地址和参数皆不能变  
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+appId+"&secret="+secret;  //访问链接

        try {  
            URL urlGet = new URL(url);  
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();  
            http.setRequestMethod("GET"); // 必须是get方式请求  
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
            http.setDoOutput(true);  
            http.setDoInput(true);  
            /*System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒  
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒 */
            http.connect();  
            InputStream is = http.getInputStream();  
            int size = is.available();  
            byte[] jsonBytes = new byte[size];  
            is.read(jsonBytes);  
            String message = new String(jsonBytes, "UTF-8");  
            JSONObject demoJson = JSONObject.parseObject(message); 
            System.out.println(demoJson.toString());
            access_token = demoJson.getString("access_token");  
            is.close();  
        } catch (Exception e) {  
                e.printStackTrace();  
        }  
        return access_token;  
    }  

    //获取ticket
    private static String getTicket(String access_token) {  
        String ticket = null;  
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ access_token +"&type=jsapi";//这个url链接和参数不能变  
        try {  
            URL urlGet = new URL(url);  
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();  
            http.setRequestMethod("GET"); // 必须是get方式请求  
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
            http.setDoOutput(true);  
            http.setDoInput(true);  
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒  
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒  
            http.connect();  
            InputStream is = http.getInputStream();  
            int size = is.available();  
            byte[] jsonBytes = new byte[size];  
            is.read(jsonBytes);  
            String message = new String(jsonBytes, "UTF-8");  
            JSONObject demoJson = JSONObject.parseObject(message);  
//            System.err.println(demoJson.toString());
            ticket = demoJson.getString("ticket"); 
//            System.err.println(ticket);
            is.close();  
        } catch (Exception e) {  
                e.printStackTrace();  
        }  
        return ticket;  
    } 


}
