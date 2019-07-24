<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/7/24
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript">
    var websocket = null;
    var host = document.location.host;
    var username = "cl"; // 获得当前登录人员的userName
    // alert(username)
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        alert("浏览器支持Websocket。 "+host);
        websocket = new WebSocket('ws://' + host + '/webSocket/' + username);
    } else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        alert("WebSocket连接发生错误")
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        alert("WebSocket连接成功")
        setMessageInnerHTML("WebSocket连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        // alert("接收到消息的回调方法")
        // alert("这是后台推送的消息：" + event.data);
        setMessageInnerHTML("这是后台推送的消息：" + event.data);
        // websocket.close();
        // alert("webSocket已关闭！")
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }
</script>
阿斯顿发
<div id="message"></div>
</body>
</html>
