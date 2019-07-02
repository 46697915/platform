<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>用户列表</title>
		<link type="text/css" rel="styleSheet"
			href="<%=path%>/js/themes/gray/easyui.css">
		<link type="text/css" rel="styleSheet"
			href="<%=path%>/js/themes/icon.css">
		<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/easyui-lang-zh_CN.js"></script>

		<script type="text/javascript">
	$(function() {
		var url;
		
		//添加用户链接
		$("#btnSave").bind("click",function(){
			 $("#userForm").form("clear");
			 $("#name").removeAttr("readonly");
			 $("#boy").attr("checked", "checked");
			 $("#userOper").dialog("open").dialog("setTitle", "添加用户");
			 url = '${pageContext.request.contextPath }/user/addUser.do';
		});
		
		//编辑用户链接
		$("#btnEdit").bind("click",function(){
           var selectedRow = $("#userGrid").datagrid("getSelections");
		   if(selectedRow.length <= 0){
				$.messager.alert("提示", "请选择需编辑的行", "edit");
		   }
		   else{
				$.post("user!find",{"userID":selectedRow[0].id},function(data){
				//window.alert($.param(data));
				if(data){
				   var userJsonData = eval(data);
				   $("#userForm").form("clear");
				   $("#name").val(userJsonData.name).attr("readonly","readonly");
				   if(userJsonData.sex == 0){
				   $("#boy").attr("checked","checked");
				}
				else{
				   $("#girls").attr("checked","checked");
				}
				$("#phone").val(userJsonData.phone);
				$("#email").val(userJsonData.email);
				$("#remark").val(userJsonData.remark);
				                             
				$("#userOper").dialog("open").dialog("setTitle", "编辑用户");
				url = '${pageContext.request.contextPath }/user/editUser.do?userId='+ userJsonData.id;
		      }
			},"json");
		  }	     
		});
		
		//查询链接
		$("#btnQuery").bind("click",function(){
		    var queryName = $.trim($("#queryName").val());
		    var querySex = $.trim($("#querySex").val());
		    $("#userGrid").datagrid("load",{"name":queryName,"sex":querySex});
		});
		
		//构造列表
		$('#userGrid').datagrid( {
			title : '用户管理',
			width : 'auto',
			height : 'auto',
			nowrap : true,
			striped : true,
			collapsible : true,
			singleSelect : true,
			url : '${pageContext.request.contextPath }/user/userList.do',
			idField : 'id',
			columns : [ [ {
				field : 'userName',
				title : '名称',
				width : 100,
				align : 'center'
			}, {
				field : 'sex',
				title : '性别',
				width : 100,
				align : 'center',
				formatter : function(val, rec) {
				    if(val == 1)
				       return "男";
				    else if(val == 2)
				       return "女";
				}
			}, {
				field : 'phone',
				title : '手机号码',
				width : 200,
				align : 'center'
			}, {
				field : 'email',
				title : '邮箱',
				width : 200,
				align : 'center'
			}, {
				field : "remark",
				title : "备注",
				width : 300,
				align : "center"
			}, {
				field : "id",
				title : "操作",
				width : 300,
				align : "center",
				formatter : function(val, rec) {
                    return "<a href='javascript:remove("+val+");'>删除</a>";
				}
			} ] ],
			pagination : true,
			rownumbers : true
		});
		//设置分页控件  
		var p = $('#userGrid').datagrid('getPager');  
		$(p).pagination({  
		    pageSize: 10,//每页显示的记录条数，默认为10  
		    pageList: [5,10,15],//可以设置每页记录条数的列表  
		    beforePageText: '第',//页数文本框前显示的汉字  
		    afterPageText: '页    共 {pages} 页',  
		    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		    onBeforeRefresh:function(){ 
		        $(this).pagination('loading'); 
		        alert('before refresh'); 
		        $(this).pagination('loaded'); 
		    }
		});

		//构造对话框
		$("#userOper").dialog( {
			width : "400",
			height : "300",
			buttons : [ {
				text : "保存",
				iconCls : "icon-save",
				handler : function() {
					$("#userForm").form("submit", {
						url : url,
						onSubmit : function() {
							return $(this).form("validate");
						},
						success : function(result) {
							if (result == "succ") {
							    $("#userGrid").datagrid("reload");
								$("#userOper").dialog("close");
							} else if(result == "fail") {
								$.messager.alert("提示", "保存失败", "error");
							}
							else{
							    $.messager.alert("提示", "该用户名称已存在,请重新输入", "error");
							    $("#name").val(" ").focus();
							}
						}
					});
				}
			}, {
				text : "取消",
				iconCls : "icon-cancel",
				handler : function() {
					$("#userOper").dialog("close");
				}
			} ]
		});
	});
	
	//删除操作
	function remove(userID){
	   $.messager.confirm("提示","确认删除?",function(r){
	      if(r){
	         $.post("user!remove",{"userID":userID},function(data){
	          if(data == "succ"){
	                 $.messager.alert("提示", "删除成功", "info");
	                 $("#userOper").dialog("close");
	                 $("#userGrid").datagrid("reload");
	              }
	              else{
	                 $.messager.alert("提示", "删除失败", "error");
	              }
	         },"text");
	      }
	   });
	   
	}
	
</script>
	</head>

	<body>
		<fieldset>
			<legend>
				用户管理
			</legend>
			<table id="userGrid" toolbar="#toolbar" style="height: 100%;"
				width="100%"></table>
		</fieldset>

		<!--列表工具栏 -->
		<div id="toolbar" style="height: auto">
			<div>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" id="btnSave">添加</a>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" id="btnEdit">编辑</a>
			</div>
			<div>
				名称:
				<input type="text" id="queryName" name="queryName" />
				性别:
				<select id="querySex" name="querySex">
					<option value="0">
						全部
					</option>
					<option value="1">
						男
					</option>
					<option value="2">
						女
					</option>
				</select>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-search" plain="true" id="btnQuery">查询</a>
			</div>
		</div>

		<!-- 对话框 -->
		<div id="userOper" closed="true"
			style="padding-left: 45px; padding-top: 10px;">
			<form id="userForm" method="post">
				<table>
					<tr>
						<td>
							你的名字:
						</td>
						<td>
							<input id="name" name="name" class="easyui-validatebox"
								required="true" missingMessage="请填写你的名字" />
						</td>
					</tr>
					<tr>
						<td>
							性别:
						</td>
						<td>
							<label>
								<input type="radio" id="boy" name="sex" value="1" />
								男
							</label>
							<label>
								<input type="radio" id="girls" name="sex" value="2" />
								女
							</label>
						</td>
					</tr>
					<tr>
						<td>
							手机号码:
						</td>
						<td>
							<input id="phone" name="phone" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							年龄:
						</td>
						<td>
							<input class="easyui-numberbox" missingMessage="请填写你的邮箱"
								required="true" id="age" name="age" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							密码:
						</td>
						<td>
							<input id="password" name="password" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							邮箱:
						</td>
						<td>
							<input id="email" name="email" class="easyui-validatebox"
								required="true" validType="email" missingMessage="请填写你的邮箱"
								invalidMessage="邮箱格式错误" />
						</td>
					</tr>
					<tr>
						<td>
							备注:
						</td>
						<td>
							<textarea id="remark" name="remark" cols="23" rows="4"></textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
