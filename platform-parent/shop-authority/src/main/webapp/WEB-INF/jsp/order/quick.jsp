<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统</title>
<%@include file="/WEB-INF/jsp/include/easyui_core.jsp"%>
<%--
<link href="${path}/Css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/Js/jquery-1.7.2.min.js"></script>--%>
<script type="text/javascript" src="${path}/js/Bll.js"></script>

<script type="text/javascript">
	
</script>
<style>
*
{
	margin: 0;
	padding: 0;
	list-style-type: none;
}
a, img
{
	border: 0;
}
body
{
	font: 12px/180% Arial, Helvetica, sans-serif, "新宋体";
}

.selectbox
{
	width: 458px;
	height: 255px;
	margin: 40px auto 0 auto;
}
.selectbox div
{
	float: left;
}
.selectbox .select-bar
{
	padding: 0 20px;
}
.selectbox .select-bar select
{
	width: 150px;
	height: 243px;
	border: 2px #A0A0A4 outset;
	padding: 4px;
}
.selectbox .btn
{
	width: 50px;
	height: 30px;
	margin-top: 10px;
	cursor: pointer;
}

.btn-bar
{
	padding-top:40px;
    vertical-align: middle;
    text-align: center;
}
</style>
</head>
<body class="easyui-layout" fit="true">
	<form id="form1" runat="server">
    <div class="selectbox">
        <%--加载绑定数据源--%>
        <div class="select-bar">
            <select multiple="multiple" id="select1">
                <option value="小孩子">小孩子</option>
                <option value="小兔子">小兔子</option>
                <option value="小孬蛋">小孬蛋</option>
                <option value="小诺诺">小诺诺</option>
                <option value="小露珠">小露珠</option>
                <option value="坏孩子">坏孩子</option>
                <option value="小awake">小awake</option>
                <option value="小菠萝">小菠萝</option>
                <option value="小this">小this</option>
                <option value="小猪猪">小猪猪</option>
            </select>
        </div>
        <%--操作事件，可以根据选择的进行数据的保存 如果保存数据直接获取 id="select2"的数值即可--%>
        <div class="btn-bar">
            <span id="add">
                <input type="button" class="btn" value=">" /></span><br />
            <span id="add_all">
                <input type="button" class="btn" value=">>" /></span><br />
            <span id="remove">
                <input type="button" class="btn" value="<" /></span><br />
            <span id="remove_all">
                <input type="button" class="btn" value="<<" /></span>
        </div>
        <%-- 选中到右边获取的数据信息，即需要保存的数据--%>
        <div class="select-bar">
            <select multiple="multiple" id="select2">
            </select></div>
    </div>
    </form>
</body>
</html>
