package com.zy.sso.login.shiro;

import org.apache.shiro.web.filter.AccessControlFilter;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zy.sso.systemManage.user.entity.UserEntity;

/**
 * @author Administrator 自定义过滤器，进行用户访问控制
 */
public class KickoutSessionFilter extends AccessControlFilter {
	private static final Logger logger = LoggerFactory.getLogger(KickoutSessionFilter.class);

	private String kickoutUrl; // 踢出后到的地址
	private boolean kickoutAfter = false; // 踢出之前登录的/之后登录的用户 默认false踢出之前登录的用户
	private int maxSession = 1; // 同一个帐号最大会话数 默认1
	private SessionManager sessionManager;
	private Cache<String, Deque<Serializable>> cache;

	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}

	public void setKickoutAfter(boolean kickoutAfter) {
		this.kickoutAfter = kickoutAfter;
	}

	public void setMaxSession(int maxSession) {
		this.maxSession = maxSession;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	// 设置Cache的key的前缀
	public void setCacheManager(CacheManager cacheManager) {
		// 必须和ehcache缓存配置中的缓存name一致
		this.cache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
//		WebUtils.issueRedirect(request, response, "/login");
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		// 没有登录授权 且没有记住我
		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			// 如果没有登录，直接进行之后的流程
			return true;
		}
		Session session = subject.getSession();
		logger.info("==session时间设置：" + String.valueOf(session.getTimeout()) + "===========");
		try {
			// 当前用户
//            User user = (User) subject.getPrincipal();
//            String username = user.getUsername();
			String username = (String) subject.getPrincipal();
//            logger.debug("===当前用户username：==" + username);
//            System.out.println("===当前用户username：==" + username);
			Serializable sessionId = session.getId();
			logger.info("===当前用户sessionId：==" + sessionId);
			// 读取缓存用户 没有就存入
			Deque<Serializable> deque = cache.get(username);
			logger.info("===当前deque：==" + deque);
			if (deque == null) {
				// 初始化队列
				deque = new ArrayDeque<Serializable>();
			}
			// 如果队列里没有此sessionId，且用户没有被踢出；放入队列
			if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
				// 将sessionId存入队列
				deque.push(sessionId);
				// 将用户的sessionId队列缓存
				cache.put(username, deque);
			}
			// 如果队列里的sessionId数超出最大会话数，开始踢人
			while (deque.size() > maxSession) {
				logger.info("===deque队列长度：==" + deque.size());
				Serializable kickoutSessionId = null;
				// 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
				if (kickoutAfter) { // 如果踢出后者
					kickoutSessionId = deque.removeFirst();
				} else { // 否则踢出前者
					kickoutSessionId = deque.removeLast();
				}
				// 踢出后再更新下缓存队列
				cache.put(username, deque);
				try {
					// 获取被踢出的sessionId的session对象
					Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
					if (kickoutSession != null) {
						// 设置会话的kickout属性表示踢出了
						kickoutSession.setAttribute("kickout", true);
					}
					
					
				} catch (Exception e) {// ignore exception
				}
			}
			// ajax请求
			// 如果被踢出了，(前者或后者)直接退出，重定向到踢出后的地址
			if ((Boolean) session.getAttribute("kickout") != null
					&& (Boolean) session.getAttribute("kickout") == true) {
				// 会话被踢出了
				try {
					// 退出登录
					subject.logout();
				} catch (Exception e) { // ignore
				}
				saveRequest(request);
				logger.info("==踢出后用户重定向的路径kickoutUrl:" + kickoutUrl);
				// 重定向
				WebUtils.issueRedirect(request, response, kickoutUrl);
				return false;
			}
			return true;
		} catch (Exception e) { // ignore
			// 重定向到登录界面
			WebUtils.issueRedirect(request, response, "/login");
			return false;
		}
	}

//	private String kickoutUrl; // 被踢出的跳转的url
//    private boolean kickoutAfter = false; //后者登录踢出前者登录
//    private int maxSession = 1; //一个用户只能有1个登录在线 
// 
//    private SessionManager sessionManager;
//    private Cache<String, Deque<Serializable>> cache;
// 
//    public void setKickoutUrl(String kickoutUrl) {
//        this.kickoutUrl = kickoutUrl;
//    }
// 
//    public void setKickoutAfter(boolean kickoutAfter) {
//        this.kickoutAfter = kickoutAfter;
//    }
// 
//    public void setMaxSession(int maxSession) {
//        this.maxSession = maxSession;
//    }
// 
//    public void setSessionManager(SessionManager sessionManager) {
//        this.sessionManager = sessionManager;
//    }
//    
//    // 设置Cache的key的前缀
//    public void setCacheManager(CacheManager cacheManager) {
//    	// 必须和ehcache缓存配置中的缓存name一致
//        this.cache = cacheManager.getCache("es");
//    }
// 
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        return false;
//    }
// 
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        Subject subject = getSubject(request, response);
//        if(!subject.isAuthenticated() && !subject.isRemembered()) {
//            //如果没有登录，走平时的操作
//            return true;
//        }
// 
//        Session session = subject.getSession();
////        UserEntity user = (UserEntity)subject.getPrincipal();
////        System.out.println("进入自定义过滤器："+user.toString());
////        String username = user.getUserName();
//        String username =(String) subject.getPrincipal();
//        System.out.println("shiro过滤器:用户>"+username);
//        Serializable sessionId = session.getId();
// 
//        // 同步控制,读取缓存用户 没有就存入
//        Deque<Serializable> deque = cache.get(username);
//        if(deque == null) {
//            deque = new LinkedList<Serializable>();
//            cache.put(username, deque);
//        }
// 
//        //如果队列里没有此sessionId，放入队列
//        if(!deque.contains(sessionId)) {
//            deque.push(sessionId);
//            ((HttpServletRequest)request).getSession().setAttribute("currentUser", username);
//        }
// 
//        	// 如果队列里的sessionId数超出最大会话数，开始踢人
//     		while (deque.size() > maxSession) {
//     			Serializable kickoutSessionId = null;
//     			if (kickoutAfter) { // 如果踢出后者
//     				kickoutSessionId = deque.removeFirst();
//     			} else { // 否则踢出前者
//     				kickoutSessionId = deque.removeLast();
//     			}
//     			try {
//     				Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
//     				if (kickoutSession != null) {
//     					// 设置会话的kickout属性表示踢出了
//     					kickoutSession.setAttribute("kickout", true);
//     				}
//     			} catch (Exception e) {// ignore exception
//     			}
//     		}
//
//     		// 如果被踢出了，直接退出，重定向到踢出后的地址
//     		if (session.getAttribute("kickout") != null) {
//     			// 会话被踢出了
//     			try {
//     				subject.logout();
//     			} catch (Exception e) { // ignore
//     				e.printStackTrace();
//     			}
//     			saveRequest(request);
//     			WebUtils.issueRedirect(request, response, kickoutUrl);
//     			return false;
////     			HttpServletRequest httpRequest = WebUtils.toHttp(request);
////     			if (ShiroFilterUtils.isAjax(httpRequest)) {
////     				HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
////     				//http会话过期
////     				httpServletResponse.sendError(ShiroFilterUtils.HTTP_STATUS_SESSION_EXPIRE);
////     				return false;
////     			} else {
////     				WebUtils.issueRedirect(request, response, kickoutUrl);
////     				return false;
////     			}
//     		}
//     		return true;
//    }

}
