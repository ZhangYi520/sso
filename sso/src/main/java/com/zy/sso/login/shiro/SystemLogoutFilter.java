//package com.zy.sso.login.shiro;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.authc.LogoutFilter;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.stereotype.Service;
//
//
///**  
//*   
//* 项目名称：sso  
//* 类名称：LogoutFilter  
//* 类描述：  
//* 创建人：zhangyi  
//* 创建时间：2019年5月7日 下午9:54:40  
//* 修改人：zhangyi  
//* 修改时间：2019年5月7日 下午9:54:40  
//* 修改备注：  
//* @version   
//*   
//*/
//@Service
//public class SystemLogoutFilter extends LogoutFilter{
//	@Override
//	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//		//登出操作 清除缓存  subject.logout() 可以自动清理缓存信息, 这些代码是可以省略的  这里只是做个笔记 表示这种方式也可以清除
//        Subject subject = getSubject(request,response);
//        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
//        ShiroRealm shiroRealm = (ShiroRealm) securityManager.getRealms().iterator().next();
//        PrincipalCollection principals = subject.getPrincipals();
//        shiroRealm.clearCache(principals);
//
//        //登出
//        subject.logout();
//
//        //获取登出后重定向到的地址
//        String redirectUrl = getRedirectUrl(request,response,subject);
//        //重定向
//        issueRedirect(request,response,redirectUrl);
//        return false;
//	}
//}
