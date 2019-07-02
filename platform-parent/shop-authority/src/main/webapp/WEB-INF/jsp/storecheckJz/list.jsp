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
	function addStorecheckJz(){
		$('#dlg').dialog('open').dialog('setTitle','新增');
		$('#fm').form('clear');
		url=path+"/storecheckJz/add";
		mesTitle = '新增成功';
	}
	
	//编辑用户信息
 	function editStorecheckJz(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑');
		 	$('#fm').form('load',row);
		 	url = path+"/storecheckJz/modify?id="+id;
		 	mesTitle = '编辑成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteStorecheckJz(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/storecheckJz/delete?id="+id;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveStorecheckJz(){
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
	function saveStorecheckJz_del(){
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
 	function searchStorecheckJzQ(){
	 	$("#datagrid").datagrid("load", {
            "username": $('#search_username').val()
        });
	}
	
	//查询
 	function searchStorecheckJz(){
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
			url="${path}/storecheckJz/datagrid" 
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
					<th field="barcode" width="100">药品条码</th>
					<th field="drugsname" width="100">药品名称</th>
					<th field="commonname" width="100">通用名</th>
					<th field="bxdygx" width="100">医保对应关系</th>
					<th field="drugscode" width="100">药品内部代码</th>
					<th field="initstore" width="100">期初库存</th>
					<th field="initamount" width="100">期初库存金额</th>
					<th field="instore" width="100">入库数量</th>
					<th field="instoreamount" width="100">入库金额</th>
					<th field="salecount" width="100">销售数量</th>
					<th field="saleamount" width="100">销售金额</th>
					<th field="newstore" width="100">最新库存</th>
					<th field="newamount" width="100">最新库存金额</th>
					<th field="currstore" width="100">当前实际库存</th>
					<th field="curramount" width="100">当前实际库存金额</th>
					<th field="crrcheckdate" width="100">本次盘点日期</th>
					<th field="lastcheckdate" width="100">上次盘点日期</th>
					<th field="checker" width="100">盘点人</th>
					<th field="checkdate" width="100">盘点日期</th>
					<th field="islastcheck" width="100">是否最后一次盘点</th>
					<th field="lastcheckid" width="100">上次盘点ID</th>
					<th field="transfer_id" width="100">结转记录id</th>

				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addStorecheckJz();">新增</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editStorecheckJz();">编辑</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteStorecheckJz();">删除</a>
			<span>用户名:</span><input name="search_username" id="search_username" value="" size=10 /> 
  			<a href="javascript:searchStorecheckJzQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchStorecheckJz();">更多查询</a>
		</div>

		<!-- 添加/修改对话框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:400px;padding:30px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post" novalidate>
			
				<div class="fitem">
					<label>药品条码:</label> <input name="barcode" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>药品名称:</label> <input name="drugsname" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>通用名:</label> <input name="commonname" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>医保对应关系:</label> <input name="bxdygx" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>药品内部代码:</label> <input name="drugscode" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>期初库存:</label> <input name="initstore" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>期初库存金额:</label> <input name="initamount" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>入库数量:</label> <input name="instore" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>入库金额:</label> <input name="instoreamount" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>销售数量:</label> <input name="salecount" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>销售金额:</label> <input name="saleamount" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>最新库存:</label> <input name="newstore" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>最新库存金额:</label> <input name="newamount" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>当前实际库存:</label> <input name="currstore" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>当前实际库存金额:</label> <input name="curramount" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>本次盘点日期:</label> <input name="crrcheckdate" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>上次盘点日期:</label> <input name="lastcheckdate" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>盘点人:</label> <input name="checker" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>盘点日期:</label> <input name="checkdate" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>是否最后一次盘点:</label> <input name="islastcheck" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>上次盘点ID:</label> <input name="lastcheckid" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>结转记录id:</label> <input name="transfer_id" class="easyui-textbox" required="true">
				</div>

			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveStorecheckJz()" style="width:90px">保存</a> 
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
				iconCls="icon-ok" onclick="saveStorecheckJz_del()" style="width:90px">删除</a> 
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
				iconCls="icon-ok" onclick="searchStorecheckJz()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
