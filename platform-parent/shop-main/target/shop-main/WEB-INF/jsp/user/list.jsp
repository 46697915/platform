<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户管理</title>
<%@include file="/WEB-INF/jsp/include/easyui_core.jsp"%>

<script type="text/javascript">

	//对身份证的校验

	$.extend($.fn.validatebox.defaults.rules, {
	    idcard: {
	        validator: function (value, param) {
	            return idCard(value);
	        },
	        message:'请输入正确的身份证号码'
	    }
	});
	var idCard = function (value) {
	    if (value.length == 18 && 18 != value.length) return false;
	    var number = value.toLowerCase();
	    var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
	    var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
	    if (re == null || a.indexOf(re[1]) < 0) return false;
	    if (re[2].length == 9) {
	        number = number.substr(0, 6) + '19' + number.substr(6);
	        d = ['19' + re[4], re[5], re[6]].join('-');
	    } else d = [re[9], re[10], re[11]].join('-');
	    for (var i = 0; i < 17; i++) sum += number.charAt(i) * w[i];
	    return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
	};
	//请求地址
	var url;
	//提示消息
	var mesTitle;
	
	//添加用户信息
	function addUser(){
		$('#dlg').dialog('open').dialog('setTitle','新增用户');
		$('#fm').form('clear');
		url=path+"/user/addUser";
		mesTitle = '新增用户成功';
	}
	
	//编辑用户信息
 	function editUser(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑用户');
		 	$('#fm').form('load',row);//这句话有问题，第一次加载时正确的，第二次就出错了，还保持第一次的数据
		 	url = path+"/user/saveOrUpdate?id="+id;
		 	mesTitle = '编辑用户成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteUser(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除用户');
		 	$('#fm').form('load',row);//这句话有问题，第一次加载时正确的，第二次就出错了，还保持第一次的数据
		 	url = path+"/user/deleteUser?id="+id;
		 	mesTitle = '删除用户成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveUser(){
	 	$('#fm').form('submit',{
		 	url: url,
		 	onSubmit: function(){
		 		return $(this).form('validate');
		 	},
			success: function(result){
				/* console.info(result); */
				var result = eval('('+result+')');
				if (result.success){
				 	$('#dlg').dialog('close'); 
				 	$('#datagrid').datagrid('reload'); 
				} else {
					 mesTitle = '新增用户失败';
				}
				$.messager.show({ 
					 title: mesTitle,
					 msg: result.msg
				});
			}
	 	});
	}	
	
	//提交删除内容
	function saveUser_del(){
	 	$('#fmdelete').form('submit',{
		 	url: url,
		 	onSubmit: function(){
		 		return $(this).form('validate');
		 	},
			success: function(result){
				/* console.info(result); */
				var result = eval('('+result+')');
				if (result.success){
				 	$('#dlg_delete').dialog('close'); 
				 	$('#datagrid').datagrid('reload'); 
				} else {
					 mesTitle = '新增用户失败';
				}
				$.messager.show({ 
					 title: mesTitle,
					 msg: result.msg
				});
			}
	 	});
	}

	//快速查询
 	function searchUserQ(){
	 	$("#datagrid").datagrid("load", {
            "username": $('#search_username').val()
        });
	 	/*
	 	$.post(url,
	 		{"username":$('#search_username').val},
	 		function(){
          		$.messager.alert("提示", "删除成功", "info");
            	$('#datagrid').datagrid('reload'); 
        	}
        );*/
	}
	
	//查询
 	function searchUser(){
		$('#dlgsearch').dialog('open').dialog('setTitle','查询');
		$('#fmsearch').form('clear');
		url = path+"/user/searchUser";
		mesTitle = '查询用户成功';
	}
	
	//刷新
	function reload(){
		$('#datagrid').datagrid('reload'); 
	}
	//页面加载后执行
	$(function(){
		$('#pharmacy_form').combobox({
			url: path+'/drugStore/findBy',
			panelHeight: '120',
			valueField:'shortname',
			textField:'drugstorename',
		});
    });
</script>

</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<!-- 用户信息列表 title="用户管理" -->
		<table id="datagrid" class="easyui-datagrid" 
		    fit="true"
			url="${path}/user/datagrid" 
			toolbar="#toolbar" 
			pagination="true"
			fitColumns="false" 
			singleSelect="false" 
			rownumbers="true"
			striped="true"
			border="false" 
			nowrap="false">
			<thead>
				<tr>
					<th field="username" width="100">用户名</th>
					<th field="password" width="100">密码</th>
					<th field="creator" width="100">创建人</th>
					<th field="createtime" width="150">创建时间</th>
					<th field="updater" width="100">最后修改人</th>
					<th field="updatetime" width="150">最后修改时间</th>
					<th field="status" width="100">状态</th>
					<th field="roleid" width="100">角色ID</th>
					<th field="nickname" width="100">昵称</th>
					<th field="email" width="100">邮箱</th>
					<th field="remark" width="100">备注</th>
					<th field="pharmacy" width="100">所属药店</th>
					<th field="name" width="100">真实姓名</th>
					<th field="idcard" width="180">身份证号</th>
				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addUser();">新增</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editUser();">编辑</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteUser();">删除</a>
			<span>用户名:</span><input name="search_username" id="search_username" value="" size=10 /> 
  			<a href="javascript:searchUserQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			<!-- <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchUser();">更多查询</a> -->
		</div>

		<!-- 添加/修改对话框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:400px;padding:30px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post" novalidate>
				<div class="fitem">
					<label>用户名:</label> <input name="username" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>密码:</label> <input name="password" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>身份证:</label> <input validType="idcard" name="idcard" class="easyui-textbox" >
				</div>
				<div class="fitem">
					<label>性别:</label> 
					<input type="radio" name="gender" id="gender" value="男" style="width:50px;">男</input>
					<input type="radio" name="gender" id="gender" value="女" style="width:50px;">女</input>
				</div>
				<div class="fitem">
					<label>所属药店:</label> <input id="pharmacy_form"  name="pharmacy" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>入职时间:</label> <input name="createtime" type="text" class="easyui-datebox" required="required"/>
				</div>
				<div class="fitem">
					<label>Email:</label> <input name="email" class="easyui-textbox" validType="email">
				</div>
				<div class="fitem">
					<label>QQ:</label> <input name="qq" class="easyui-textbox" >
				</div>
				<div class="fitem">
					<label>微信:</label> <input name="weixin" class="easyui-textbox" >
				</div>
				<div class="fitem">
					<label>昵称:</label> <input name="nickname" class="easyui-textbox">
				</div>
				<div class="fitem">
					<label>备注:</label> <input name="remark" class="easyui-textbox">
				</div>
				<div class="fitem">
					<label>真实姓名:</label> <input name="name" class="easyui-textbox">
				</div>
			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
		<!-- 删除对话框 -->
		<div id="dlg_delete" class="easyui-dialog"
			style="width:300px;height:200px;padding:30px 20px" closed="true"
			buttons="#dlg-del-buttons">
			<div class="ftitle">请谨慎操作</div>
			<form id="fmdelete" method="post" novalidate>
					<label>确定删除用户吗？</label>
			</form>
		</div>
		
		<!-- 删除对话框按钮 -->
		<div id="dlg-del-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveUser_del()" style="width:90px">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlg_delete').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
		<!-- 查询对话框 -->
		<div id="dlgsearch" class="easyui-dialog"
			style="width:400px;height:380px;padding:30px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fmsearch" method="post" novalidate>
				<label>权限:</label>  
                    <span id="span1" style="display: inline-block;"></span>  
                </div>
			</form>
		</div>
		
		<!-- 查询对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="searchUser()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
