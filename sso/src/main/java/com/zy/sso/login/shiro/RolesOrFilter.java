package com.zy.sso.login.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.assertj.core.util.Arrays;

/**  
*   
* 项目名称：sso  
* 类名称：RolesOrFilter  
* 类描述：  
* 创建人：zhangyi  
* 创建时间：2019年5月5日 下午9:39:53  
* 修改人：zhangyi  
* 修改时间：2019年5月5日 下午9:39:53  
* 修改备注：  
* @version   
*   
*/
public class RolesOrFilter extends AuthorizationFilter{

	//Object 就是角色信息的数组，||关系
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
		// TODO Auto-generated method stub
		Subject subject=getSubject(request, response);
		String [] str=(String [])o;
		if(str ==null || str.length==0) {
			return true;
		}
		for (String s : str) {
			if(subject.hasRole(s)) {
				return true;
			}
		}
		return false;
	}

}
