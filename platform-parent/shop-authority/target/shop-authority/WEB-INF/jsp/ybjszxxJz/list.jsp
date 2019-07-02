<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统</title>
<%@include file="/WEB-INF/jsp/include/easyui_core.jsp"%>

<script type="text/javascript">
	//请求地址
	var url;
	//提示消息
	var mesTitle;
	
	//添加用户信息
	function addYbjszxxJz(){
		$('#dlg').dialog('open').dialog('setTitle','新增');
		$('#fm').form('clear');
		url=path+"/ybjszxxJz/add";
		mesTitle = '新增成功';
	}
	
	//编辑用户信息
 	function editYbjszxxJz(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑');
		 	$('#fm').form('load',row);
		 	url = path+"/ybjszxxJz/modify?id="+id;
		 	mesTitle = '编辑成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteYbjszxxJz(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/ybjszxxJz/delete?id="+id;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveYbjszxxJz(){
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
					 mesTitle = '新增失败';
				}
				$.messager.show({ 
					 title: mesTitle,
					 msg: result.msg
				});
			}
	 	});
	}	
	
	//提交删除内容
	function saveYbjszxxJz_del(){
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
					 mesTitle = '新增失败';
				}
				$.messager.show({ 
					 title: mesTitle,
					 msg: result.msg
				});
			}
	 	});
	}

	//快速查询
 	function searchYbjszxxJzQ(){
	 	$("#datagrid").datagrid("load", {
            "username": $('#search_username').val()
        });
	}
	
	//查询
 	function searchYbjszxxJz(){
		$('#dlgsearch').dialog('open').dialog('setTitle','查询');
		$('#fmsearch').form('clear');
		url = path+"/user/searchUser";
		mesTitle = '查询成功';
	}
	
	//刷新
	function reload(){
		$('#datagrid').datagrid('reload'); 
	}
</script>

</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<!-- 信息列表 title="管理" -->
		<table id="datagrid" class="easyui-datagrid" 
		    fit="true"
			url="${path}/ybjszxxJz/datagrid" 
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
					<th field="tcqh" width="100">统筹区号</th>
					<th field="yyjgdm" width="100">医院机构编号</th>
					<th field="klxbh" width="100">卡类型编号</th>
					<th field="kahao" width="100">病人卡号</th>
					<th field="bxid" width="100">保险编号(个人编号)</th>
					<th field="xzbh" width="100">参保险种编号</th>
					<th field="xzmc" width="100">参保险种名称</th>
					<th field="blh" width="100">病历号（处方号）</th>
					<th field="ptlsh" width="100">平台流水号</th>
					<th field="rylb" width="100">人员类别</th>
					<th field="rylbmc" width="100">人员类别名称</th>
					<th field="sftsmz" width="100">是否特殊门诊</th>
					<th field="tsmzfl" width="100">特殊门诊分类</th>
					<th field="tsmzflmc" width="100">特殊门诊分类名称</th>
					<th field="zfy" width="100">总费用</th>
					<th field="jbjjzf" width="100">基本基金支付</th>
					<th field="dbjjzf" width="100">大病基金支付</th>
					<th field="dbeczf" width="100">大病二次补偿支付金额</th>
					<th field="bcjjzf" width="100">补充基金支付</th>
					<th field="gwyjjzf" width="100">公务员基金支付</th>
					<th field="grzhzf" width="100">个人账户支付</th>
					<th field="xjzh" width="100">现金支付</th>
					<th field="grzf" width="100">个人自付金额</th>
					<th field="qzzf" width="100">其中自费金额</th>
					<th field="qzqfx" width="100">其中起付线金额</th>
					<th field="ybjsbh" width="100">医保结算编号</th>
					<th field="tsbt" width="100">特殊补贴</th>
					<th field="isupdate" width="100">是否更新库存</th>
					<th field="hjrq" width="100">划价日期</th>

				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addYbjszxxJz();">新增</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editYbjszxxJz();">编辑</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteYbjszxxJz();">删除</a>
			<span>用户名:</span><input name="search_username" id="search_username" value="" size=10 /> 
  			<a href="javascript:searchYbjszxxJzQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchYbjszxxJz();">更多查询</a>
		</div>

		<!-- 添加/修改对话框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:400px;padding:30px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post" novalidate>
			
				<div class="fitem">
					<label>统筹区号:</label> <input name="tcqh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>医院机构编号:</label> <input name="yyjgdm" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>卡类型编号:</label> <input name="klxbh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>病人卡号:</label> <input name="kahao" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>保险编号(个人编号):</label> <input name="bxid" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>参保险种编号:</label> <input name="xzbh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>参保险种名称:</label> <input name="xzmc" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>病历号（处方号）:</label> <input name="blh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>平台流水号:</label> <input name="ptlsh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>人员类别:</label> <input name="rylb" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>人员类别名称:</label> <input name="rylbmc" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>是否特殊门诊:</label> <input name="sftsmz" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>特殊门诊分类:</label> <input name="tsmzfl" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>特殊门诊分类名称:</label> <input name="tsmzflmc" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>总费用:</label> <input name="zfy" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>基本基金支付:</label> <input name="jbjjzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>大病基金支付:</label> <input name="dbjjzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>大病二次补偿支付金额:</label> <input name="dbeczf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>补充基金支付:</label> <input name="bcjjzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>公务员基金支付:</label> <input name="gwyjjzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>个人账户支付:</label> <input name="grzhzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>现金支付:</label> <input name="xjzh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>个人自付金额:</label> <input name="grzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>其中自费金额:</label> <input name="qzzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>其中起付线金额:</label> <input name="qzqfx" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>医保结算编号:</label> <input name="ybjsbh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>特殊补贴:</label> <input name="tsbt" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>是否更新库存:</label> <input name="isupdate" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>划价日期:</label> <input name="hjrq" class="easyui-textbox" required="true">
				</div>

			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveYbjszxxJz()" style="width:90px">保存</a> 
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
					<label>确定删除吗？</label>
			</form>
		</div>
		
		<!-- 删除对话框按钮 -->
		<div id="dlg-del-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveYbjszxxJz_del()" style="width:90px">删除</a> 
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
				iconCls="icon-ok" onclick="searchYbjszxxJz()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
