package com.zy.sso.base.util.sendNote;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zy.sso.base.result.CodeMsg;
import com.zy.sso.base.result.Result;
@Component
public class SendMesgUtil {
	private static Logger log = LoggerFactory.getLogger(SendMesgUtil.class);
	
	public static RedisTemplate<String, Object> redisTemplate;
	
	@Resource
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		SendMesgUtil.redisTemplate = redisTemplate;
	}

	public static Result<SendMsgVo> sendMsg(SendMsgVo vo) {
	
		//String phone = vo.getPhone();// 获取电话
		//int i=userApiDao.getUserByPhone();
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		vo.setRequestId(uuid);// 设置请求Id,随机生成
		vo.setSignature("重庆市经济和信息化委员会");// 签名
		String tepm = vo.getTemplateCode();// 获取模板code
		
		// 生成6位验证码
		StringBuilder s = new StringBuilder("");
		while (s.length() < 6) {
			s.append((int) (Math.random() * 10));
		}
		String varCode = s.toString();
		log.info(varCode);
		// variable变量数据赋值
		List<Map<String, String>> listNew = new ArrayList<Map<String, String>>();// 用于保存请求参数中的variable
		Map<String, String> mapNew = new HashMap<>();
		switch (tepm) {// 发送请求的模板code信息设置
		case "SMS_161593659":
			mapNew.put("key", "verCode");
			break;
		case "SMS_161598619":
			mapNew.put("key", "verCode");
			break;
		case "SMS_161598625":
			mapNew.put("key", "searchCode");
			break;
		default:
			break;
		}
		mapNew.put("val", varCode);
		listNew.add(mapNew);

		vo.setVariable(listNew);// 重新赋值

		// 发送数据报文
		String url = "http://222.178.203.71:8016/sms/api/sendMsg";
		// System.out.println(JSONObject.toJSONString(vo));
		Result<SendMsgVo> res=SendMesgUtil.interfaceUtil(url, JSONObject.toJSONString(vo));
		System.out.println("-----"+res);
		// 如果成功
		if(res.getCode()==0) {
			String key=vo.getPhone();
//			System.out.println(key+"----"+varCode);
			redisTemplate.opsForValue().set(key,varCode);//保存redis
			//System.out.println("redis："+redisTemplate.opsForValue().get(vo.getPhone()).toString());
			redisTemplate.expire(key,30,TimeUnit.MINUTES);//30分钟超时 	
			// 返回成功信息给前台
			return res;
		}else {
			return res;
		}
		
		
	}
	
	/**方法重写，用于发送查询码
	 * @param vo
	 * @param session
	 * @param queryCode 查询码
	 * @return
	 */
	public static Result<Object> sendMsg(SendMsgVo vo,String queryCode) {
		//String phone = vo.getPhone();// 获取电话
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		vo.setRequestId(uuid);// 设置请求Id,随机生成
		vo.setSignature("重庆市经济和信息化委员会");// 签名
		String tepm = vo.getTemplateCode();// 获取模板code

		// 生成6位验证码
//		StringBuilder s = new StringBuilder("");
//		while (s.length() < 6) {
//			s.append((int) (Math.random() * 10));
//		}
		String verCode = queryCode;//查询码当做验证码
		log.info(verCode);
		// variable变量数据赋值
		List<Map<String, String>> listNew = new ArrayList<Map<String, String>>();// 用于保存请求参数中的variable
		Map<String, String> mapNew = new HashMap<>();
		switch (tepm) {// 发送请求的模板code信息设置
		case "SMS_161593659":
			mapNew.put("key", "verCode");
			break;
		case "SMS_161598619":
			mapNew.put("key", "verCode");
			break;
		case "SMS_161598625":
			mapNew.put("key", "searchCode");
			break;
		default:
			break;
		}
		mapNew.put("val", verCode);
		listNew.add(mapNew);

		vo.setVariable(listNew);// 重新赋值

		// 发送数据报文
		String url = "http://222.178.203.71:8016/sms/api/sendMsg";
		// System.out.println(JSONObject.toJSONString(vo));
		
		Result<SendMsgVo> res=SendMesgUtil.interfaceUtil(url, JSONObject.toJSONString(vo));//发送短语结果集
		
		//如果发送成功
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//		HttpSession session=request.getSession();
//		session.setAttribute("varCode", verCode);// 验证码保存session
		
		//失败
		
		
//		res.setMsg(res.getData().getClass());
		// 返回成功信息给前台
		return Result.success(res);
	}
	
	public static Result<SendMsgVo> interfaceUtil(String path, String data) {
		try {
			URL url = new URL(path);
			// 打开和url之间的连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			PrintWriter out = null;
			// 请求方式
			conn.setRequestMethod("GET");
//	           //设置通用的请求属性
			conn.setRequestProperty("token", "XMS51RT6BDM90");
			conn.setRequestProperty("Content-Type", " application/json");
			conn.setRequestProperty("cache-control", "no-cache");
			// 设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数即数据
			out.print(data);
			// 缓冲数据
			out.flush();
			// 获取URLConnection对象对应的输入流
			InputStream is = conn.getInputStream();
			// 构造一个字符流缓存
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			StringBuilder strbui = new StringBuilder("");
			while ((str = br.readLine()) != null) {
				strbui.append(str);
			} 
			log.info(strbui.toString());
			SendMsgVo v=new SendMsgVo();
			JSONObject jsonObject = JSON.parseObject(strbui.toString());
			v.setToken(jsonObject.getString("token"));
			v.setRequestId(jsonObject.getString("requestId"));
			v.setOperId(jsonObject.getString("operId"));
			//v.setResponseInfo(jsonObject.getJSONObject("responseInfo"));
			JSONObject j=jsonObject.getJSONObject("responseInfo");
			ResponseInfo responseInfo = JSON.parseObject(j.toString(), new TypeReference<ResponseInfo>() {});
			v.setResponseInfo(responseInfo);
//			JSONObject jsonObject=JSONObject.fromObject(strbui.toString());
//			SendMsgVo stu=(SendMsgVo)JSONObject.toBean(jsonObject, SendMsgVo.class);
			// 关闭流
			is.close();
			// 断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
			// 固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
			conn.disconnect();
			log.info("短信发送结束。。。。。。。。。。。。。");
			if(v.getResponseInfo().getCode().equals("0000")) {
				Result<SendMsgVo> succRes=new Result<SendMsgVo>();
				succRes.setCode(0);
				succRes.setData(v);
				succRes.setMsg("发送成功！！！");
				return succRes;
			}else {
				Result<SendMsgVo> errRes=new Result<SendMsgVo>();
				errRes.setData(v);
				errRes.setCode(500010);
				switch (v.getResponseInfo().getContent()) {
				case "isv.BUSINESS_LIMIT_CONTROL":
					errRes.setMsg("您的手机号码今日已达到最大发送短信次数，请明天再试！");
					break;
				default:
					errRes.setMsg("短信发送失败!");
					break;
				}
				return errRes;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error(CodeMsg.BASE_SERVER_ERROR);
		}
	}

	
}
