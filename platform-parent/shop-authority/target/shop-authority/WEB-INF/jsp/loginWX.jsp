<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <meta charset="utf-8">
        <title>微信登录界面</title>
		<meta name="keywords" content="微信登录界面" />
		<meta name="description" content="微信登录界面" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="js/assets/css/reset.css">
        <link rel="stylesheet" href="js/assets/css/supersized.css">
        <link rel="stylesheet" href="js/assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>

        <div class="page-container">
            <h1>登录</h1>
            <form action="weixincore/loginWX" method="post" id="loginForm" name="loginForm">
				<input type="text"  name="openid" class="openid" placeholder="openId" value="${openid }">
                <input type="text" id="loginname" name="loginname" class="username" placeholder="用户名">
                &nbsp;<span id="nameerr" class="errInfo"></span>
                <input type="password" id="password" name="password" class="password" placeholder="密码">
                &nbsp;<span id="pwderr" class="errInfo"></span>
				<input type="text" name="code" id="code" class="login_input" placeholder="验证码" />
                <div class="login_info">
					&nbsp;<span id="codeerr" class="errInfo"> </span>
				</div>
				<img id="codeImg" alt="点击更换" title="点击更换" src=""/>
                <button type="button" onclick="login()">提交</button>
                <div class="error"><span>+</span></div>
            </form>
            <div class="connect"><%--
                <p>Or connect with:</p>
                <p>
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p>
            --%></div>
        </div>
		
        <!-- Javascript -->
        <script src="js/assets/js/jquery-1.8.2.min.js"></script>
        <script src="js/assets/js/supersized.3.2.7.min.js"></script>
        <script src="js/assets/js/supersized-init.js"></script>
        <script src="js/assets/js/scripts.js"></script>
<script type="text/javascript">
		var errInfo = "${errInfo}";
		var status = "${status}";
		$(document).ready(function(){
			if(status!=""&&status=="success"){
				WeixinJSBridge.invoke('closeWindow',{},function(res){
				});
				history.go(-4);
			}
			changeCode();
			$("#codeImg").bind("click",changeCode);
			if(errInfo!=""){
				if(errInfo.indexOf("验证码")>-1){
					$("#codeerr").show();
					$("#codeerr").html(errInfo);
					$("#code").focus();
				}else{
					$("#nameerr").show();
					$("#nameerr").html(errInfo);
				}
			}
			$("#loginname").focus();
			//全局回车事件
			document.onkeydown = function(e){ 
				var ev = document.all ? window.event : e;
				if(ev.keyCode==13) {
					login();//处理事件
				}
			}
		});
	
		function genTimestamp(){
			var time = new Date();
			return time.getTime();
		}
	
		function changeCode(){
			$("#codeImg").attr("src","code?t="+genTimestamp());
		}
		
		function resetErr(){
			$("#nameerr").hide();
			$("#nameerr").html("");
			$("#pwderr").hide();
			$("#pwderr").html("");
			$("#codeerr").hide();
			$("#codeerr").html("");
		}
		
		function check(){
			resetErr();
			if($("#loginname").val()==""){
				$("#nameerr").show();
				$("#nameerr").html("用户名不得为空！");
				$("#loginname").focus();
				return false;
			}
			if($("#password").val()==""){
				$("#pwderr").show();
				$("#pwderr").html("密码不得为空！");
				$("#password").focus();
				return false;
			}
			if($("#code").val()==""){
				$("#codeerr").show();
				$("#codeerr").html("验证码不得为空！");
				$("#code").focus();
				return false;
			}
			return true;
		}
		
		function login(){
			check();
	    	document.loginForm.submit();
		}    	
	</script>
    </body>

</html>


