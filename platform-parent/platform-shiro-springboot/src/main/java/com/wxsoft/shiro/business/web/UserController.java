package com.wxsoft.shiro.business.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxsoft.shiro.business.entity.User;
import com.wxsoft.shiro.business.entity.UserVo;
import com.wxsoft.shiro.business.service.IUserService;
import com.wxsoft.shiro.shiro.PasswordHelper;
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
import org.wxsoftframwork.ui.core.vue.element.PageElementUI;
import org.wxsoftframwork.ui.core.vue.element.ResponseElementUI;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cl
 * @since 2019-09-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;


    @RequestMapping("/login")
    public AjaxJson login(User loginUser, ServletRequest request) {

        ModelAndView view = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(), loginUser.getPassword());
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
//        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("currentUserId");
//        loginUser.setId(Integer.valueOf(userId));
        aj.setObj(loginUser);
        return aj;
    }

    @RequestMapping("/logout")
    public AjaxJson logout(User loginUser) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        AjaxJson aj = new AjaxJson();
        aj.setSuccess(true);
        return aj;
    }

    /**
     * 查询列表数据
     *
     * @return
     */
    @RequestMapping("/list")
    public ResponseElementUI list() {
        List r = service.selectBy(null);
        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(r);
        return rr;
    }

    /**
     * 菜单列表分页
     *
     * @param
     * @return
     */
    @RequestMapping("/listForPage")
    public ResponseElementUI listForPage(PageElementUI pe, UserVo vo) {
        PageHelper.startPage(pe.getPageNO(), pe.getPageSize());
        List r = service.selectBy(vo);

        //获取分页信息
        PageInfo pi = new PageInfo(r);

        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(pi);

        return rr;
    }

    @RequestMapping("/add")
    public ResponseElementUI add(User vo) {
        //给密码加密，获取盐
        PasswordHelper.encryptPassword(vo);
        boolean r = service.insert(vo);
        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(r);
        return rr;
    }

    @RequestMapping("/edit")
    public ResponseElementUI edit(User vo) {
        //给密码加密，获取盐
        PasswordHelper.encryptPassword(vo);
        boolean r = service.updateById(vo);
        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(r);
        return rr;
    }

    /**
     * 存在类型转换问题，使用批量删除
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public ResponseElementUI delete(HttpServletRequest request, String id) {
        boolean r = service.deleteById(id);

        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(r);
        return rr;
    }

    @RequestMapping("deleteBatch")
    public ResponseElementUI deleteBatch(HttpServletRequest request,
                                         String ids[]) {
        boolean r = service.deleteBatchIds(Arrays.asList(ids));

        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(r);
        return rr;
    }

}
