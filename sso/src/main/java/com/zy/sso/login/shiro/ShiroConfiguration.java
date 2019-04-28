package com.zy.sso.login.shiro;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
//import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ShiroConfiguration {
	
	 @Bean  
	    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){  
	       System.out.println("ShiroConfiguration.shirFilter()");  
	       ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();  
	        
	        // 必须设置 SecurityManager   
	       shiroFilterFactoryBean.setSecurityManager(securityManager);  
	        
	        
	        
	       //拦截器.  
	       Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();  
	        
	       //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了  
	       filterChainDefinitionMap.put("/logout", "logout");  
	        
	       //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;  
	        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->  
	       filterChainDefinitionMap.put("/**", "authc");  
	        
	       // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面  
	        shiroFilterFactoryBean.setLoginUrl("/login");  
	        // 登录成功后要跳转的链接  
	        shiroFilterFactoryBean.setSuccessUrl("/index");  
	        //未授权界面;  
	        shiroFilterFactoryBean.setUnauthorizedUrl("/403");  
	        
	       shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);  
	       return shiroFilterFactoryBean;  
	    }  

	//将自己的验证方式加入容器
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }
    
    
  //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }
    @Bean  
    public SecurityManager securityManager(){  
       DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();  
       return securityManager;  
    }
    
	}
