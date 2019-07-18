package com.wxsoft.shrio.test2realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "myrealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
//这里只通过用户名密码认证
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = (String) token.getPrincipal();
        String pwd = new String((char[]) token.getCredentials());
        if (!"zhang".equals(name)) {
            throw new UnknownAccountException("用户名不正确！");//用户名错误
        }
        if (!"123".equals(pwd)) {
            throw new IncorrectCredentialsException("密码错误！");//密码错误
        }
        return new SimpleAuthenticationInfo(name, pwd, getName());
    }
}