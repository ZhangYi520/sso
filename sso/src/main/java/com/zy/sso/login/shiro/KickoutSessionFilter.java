package com.zy.sso.login.shiro;

import org.apache.shiro.web.filter.AccessControlFilter;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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
	private String kickoutUrl; // 被踢出的跳转的url
    private boolean kickoutAfter = false; //后者登录踢出前者登录
    private int maxSession = 1; //一个用户只能有1个登录在线 
 
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
        this.cache = cacheManager.getCache("es");
    }
 
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }
 
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，走平时的操作
            return true;
        }
 
        Session session = subject.getSession();
//        UserEntity user = (UserEntity)subject.getPrincipal();
//        System.out.println("进入自定义过滤器："+user.toString());
//        String username = user.getUserName();
        String username =(String) subject.getPrincipal();
        Serializable sessionId = session.getId();
 
        // 同步控制,读取缓存用户 没有就存入
        Deque<Serializable> deque = cache.get(username);
        if(deque == null) {
            deque = new LinkedList<Serializable>();
            cache.put(username, deque);
        }
 
        //如果队列里没有此sessionId，放入队列
        if(!deque.contains(sessionId)) {
            deque.push(sessionId);
            ((HttpServletRequest)request).getSession().setAttribute("currentUser", username);
        }
 
        //如果队列里的sessionId数超出最大会话数，开始踢人
        while(deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            if(kickoutAfter) { //如果踢出后者 同一个用户第二次在别的地方登录，报错org.apache.shiro.session.UnknownSessionException
                kickoutSessionId = deque.removeFirst();
            } else { //否则踢出前者
                kickoutSessionId = deque.removeLast();
            }
            try {
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(kickoutSession != null) {
                    kickoutSession.stop();// 停止这个session，重新登录
                }
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
 
        return true;
    }
	
}
