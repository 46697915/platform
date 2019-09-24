package com.wxsoft.shiro.controller;

import com.wxsoft.shiro.business.entity.User;
import com.wxsoft.shiro.business.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wxsoftframwork.core.common.model.json.AjaxJson;

import javax.servlet.ServletRequest;

@RestController
public class IndexController {

    @Autowired
    IUserService userService;

    //登录校验
//    @RequestMapping("/login")
//    public String login(User user){
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//        return "success";
//    }

    @RequestMapping("/login")
    public AjaxJson login(User loginUser, ServletRequest request){

        ModelAndView view = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(),loginUser.getPassword());

        AjaxJson aj = new AjaxJson();
        aj.setSuccess(true);
        try {
// 4、登录，即身份验证
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("账号不存在!");
            e.printStackTrace();
            aj.setSuccess(false);
            aj.setMsg("账号不存在!");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码不正确!");
            e.printStackTrace();
            aj.setSuccess(false);
            aj.setMsg("密码不正确!");
        } catch (AuthenticationException e) {
            System.out.println("身份认证失败!");
            e.printStackTrace();
            aj.setSuccess(false);
            aj.setMsg("身份认证失败!");
        }
        //如果有权限
//        if(!subject.isAuthenticated()){
//
//            subject.login(token);
//        }
        //获取上一次请求路径
//        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
//        String url = "";
//        if(savedRequest != null){
//            url = savedRequest.getRequestUrl();
//        }else{
//            url = "/page/main.html";
//        }

//        view.setViewName("redirect:"+url);
        return aj;
    }

    @RequestMapping("/register")
    public String add(User user){
        ModelAndView view = new ModelAndView();
        userService.insert(user);
        view.setViewName("redirect:/login.html");
        AjaxJson aj = new AjaxJson();
        aj.setSuccess(true);
        return aj.getJsonStr();
    }

    @RequestMapping("/logout")
    public String logout(User loginUser){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "已注销";
    }
}
