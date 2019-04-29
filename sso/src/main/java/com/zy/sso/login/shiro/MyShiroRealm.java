package com.zy.sso.login.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zy.sso.base.result.Result;
import com.zy.sso.user.entity.AuthorEntity;
import com.zy.sso.user.entity.RoleEntity;
import com.zy.sso.user.entity.UserEntity;
import com.zy.sso.user.service.serviceImpl.UserServiceImpl;

public class MyShiroRealm extends AuthorizingRealm{
	//用于用户查询
    @Autowired
    private UserServiceImpl userServiceImpl;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String userName= (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        Result<UserEntity> result = userServiceImpl.queryUserByUserName(userName);
        UserEntity userEntity=result.getData();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (RoleEntity role:userEntity.getRole()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (AuthorEntity authorEntity:role.getAuthor()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(authorEntity.getAction());
            }
        }
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String userName = authenticationToken.getPrincipal().toString();
        Result<UserEntity> result = userServiceImpl.queryUserByUserName(userName);
        UserEntity userEntity=result.getData();
        if (userEntity == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
            		userName, 
            		userEntity.getPassword().toString(),
            		ByteSource.Util.bytes(userName),
            		getName());
            return simpleAuthenticationInfo;
        }
    }
}
