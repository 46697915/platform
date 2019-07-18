package com.wxsoft.shrio.test2realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

//jdbc Realm
public class ShrioTest2Realm3 {
    public static void main(String[] args) {
        testHelloworld();
    }

    public static void testHelloworld() {
// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm-jdbc.ini");
// 2、得到SecurityManager实例 并绑定给SecurityUtils
// 有一个java.lang.SecurityManager的类，所以这里得指明包名
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
// 4、登录，即身份验证
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("账号不存在!");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码不正确!");
            e.printStackTrace();
        } catch (AuthenticationException e) {
            System.out.println("身份认证失败!");
            e.printStackTrace();
        }
        System.out.println(subject.isAuthenticated());
// 6、退出
        subject.logout();
    }
}
