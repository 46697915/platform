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
	function addDrugStore(){
		$('#dlg').dialog('open').dialog('setTitle','新增');
		$('#fm').form('clear');
		url=path+"/drugStore/add";
		mesTitle = '新增成功';
	}
	
	//编辑用户信息
 	function editDrugStore(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑');
		 	$('#fm').form('load',row);
		 	url = path+"/drugStore/modify?id="+id;
		 	mesTitle = '编辑成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteDrugStore(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/drugStore/delete?id="+id;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveDrugStore(){
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
	function saveDrugStore_del(){
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
 	function searchDrugStoreQ(){
	 	$("#datagrid").datagrid("load", {
            "drugstorename": $('#search_username').val()
        });
	}
	
	//查询
 	function searchDrugStore(){
		$('#dlgsearch').dialog('open').dialog('setTitle','查询');
		$('#fmsearch').form('clear');
		url = path+"/drugStore/datagrid";
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
			url="${path}/drugStore/datagrid" 
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
					<th field="drugstorename" width="100">药房名称</th>
					<th field="shortname" width="100">简称</th>
					<th field="linkman" width="100">联系人</th>
					<th field="telephone" width="100">联系电话</th>
					<th field="address" width="100">地址</th>
					<th field="postcode" width="100">邮编</th>
					<th field="linkmanqq" width="100">联系人QQ</th>
					<th field="statusname" width="100">状态</th>
					<th field="creatorname" width="100">创建人</th>
					<th field="createtime" width="100">创建时间</th>
					<th field="updatername" width="100">最后修改人</th>
					<th field="updatetime" width="100">最后修改时间</th>
					<th field="storecode" width="100">药店编码</th>
					<th field="remark" width="100">备注</th>
				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addDrugStore();">新增</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editDrugStore();">编辑</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteDrugStore();">删除</a>
			<span>药店名:</span><input name="search_username" id="search_username" value="" size=10 /> 
  			<a href="javascript:searchDrugStoreQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			<!-- 
			 <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchDrugStore();">更多查询</a>
			 -->
		</div>

		<!-- 添加/修改对话框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:400px;padding:30px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post" novalidate>
			
				<div class="fitem">
					<label>药房名称:</label> <input name="drugstorename" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>简称:</label> <input name="shortname" class="easyui-textbox" required="true">
				</div>
				<!-- div class="fitem">
					<label>创建人:</label> <input name="creator" class="easyui-textbox" required="true">
				</div> 
				<div class="fitem">
					<label>创建时间:</label> <input name="createtime" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>最后修改人:</label> <input name="updater" class="easyui-textbox" required="true">
				</div>
				< div class="fitem">
					<label>最后修改时间:</label> <input name="updatetime" class="easyui-textbox" required="true">
				</div> 
				<div class="fitem">
					<label>状态:</label> <input name="status" class="easyui-textbox" required="true">
				</div>-->
				
				<div class="fitem">
					<label>联系人:</label> <input name="linkman" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>联系电话:</label> <input name="telephone" class="easyui-textbox" validType="mobile">
				</div>
				<div class="fitem">
					<label>联系人QQ:</label> <input name="linkmanqq" class="easyui-numberbox" data-options="min:5,max:20000000000,required:true" >
				</div>
				<div class="fitem">
					<label>邮编:</label> <input name="postcode" class="easyui-textbox" >
				</div>
				<div class="fitem">
					<label>地址:</label> <input name="address" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>备注:</label> <textarea  name="remark" style="height:60px;" class="easyui-textbox" ></textarea>
				</div>
			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveDrugStore()" style="width:90px">保存</a> 
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
				iconCls="icon-ok" onclick="saveDrugStore_del()" style="width:90px">删除</a> 
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
				iconCls="icon-ok" onclick="searchDrugStore()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
