package com.wxsoft.shiro.shiro.realm;

import com.wxsoft.shiro.business.entity.User;
import com.wxsoft.shiro.business.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录授权时，会访问此类。
 *
 */
@Component
public class UserRealm extends AuthorizingRealm {
    
    
    @Autowired
    private IUserService userService;
    
    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //在数据库中查询用户拥有的角色/权限
//        authorizationInfo.setRoles(userService.findRoles(username));
//        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }

    
    /**
     * 验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        if(user == null){
            throw new UnknownAccountException(); //没找到账号
        }
        
        if(Boolean.TRUE.equals(user.getLocked())){
            throw new LockedAccountException(); //账号被锁定
        }
        //验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), //salt = username+salt
                getName());
            
        
        return authenticationInfo;
    }

}