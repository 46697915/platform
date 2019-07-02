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
	function addDrugsDel(){
		$('#dlg').dialog('open').dialog('setTitle','新增');
		$('#fm').form('clear');
		url=path+"/drugsDel/add";
		mesTitle = '新增成功';
	}
	
	//编辑用户信息
 	function editDrugsDel(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑');
		 	$('#fm').form('load',row);
		 	url = path+"/drugsDel/modify?id="+id;
		 	mesTitle = '编辑成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteDrugsDel(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
	 		var barcode = row.barcode;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/drugsDel/delete?barcode="+barcode;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveDrugsDel(){
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
	function saveDrugsDel_del(){
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
 	function searchDrugsDelQ(){
	 	$("#datagrid").datagrid("load", {
	 		"name_search": $('#name_search').val()
        });
	}
	
	//查询
 	function searchDrugsDel(){
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
			url="${path}/drugsDel/datagrid" 
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
					<th field="barcode" width="120">药品条码</th>
					<th field="drugsname" width="150">药品名称</th>
					<th field="commonname" width="130">通用名</th>
					<th field="specifications" width="130">规格</th>
					<th field="manufactor" width="130">生成厂家</th>
					<th field="drugscode" width="120">内部编码</th>
					<th field="bxdygx" width="120">医保对应关系</th>
					<th field="operator" width="60">操作人</th>
					<th field="operatedate" width="150">操作日期</th>
					<th field="deleter" width="60">删除人</th>
					<th field="deletedate" width="150">删除日期</th>
					<th field="commonshotspell" width="100">通用名拼音简码</th>
					<th field="commonnamespell" width="100">通用名拼音码</th>
					<th field="approvalnum" width="100">批准文号</th>
					<th field="units" width="100">单位</th>
					<th field="type1" width="100">所属类别1</th>
					<th field="type2" width="100">所属类别2</th>
					<th field="type3" width="100">所属类别3</th>
					<th field="dosageform" width="100">药品剂型</th>

				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a>
			<!--<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addDrugsDel();">新增</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editDrugsDel();">编辑</a> 
			-->
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteDrugsDel();">恢复药品</a>
			<span>药品名:</span><input name="name_search" id="name_search" value="" size=10 /> 
  			<a href="javascript:searchDrugsDelQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchDrugsDel();">更多查询</a>
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
					<label>所属类别1:</label> <input name="type1" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>所属类别2:</label> <input name="type2" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>所属类别3:</label> <input name="type3" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>药品剂型:</label> <input name="dosageform" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>规格:</label> <input name="specifications" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>单位:</label> <input name="units" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>生成厂家:</label> <input name="manufactor" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>批准文号:</label> <input name="approvalnum" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>内部编码:</label> <input name="drugscode" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>通用名拼音简码:</label> <input name="commonshotspell" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>通用名拼音码:</label> <input name="commonnamespell" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>操作人:</label> <input name="operator" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>操作日期:</label> <input name="operatedate" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>医保对应关系:</label> <input name="bxdygx" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>删除人:</label> <input name="deleter" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>删除日期:</label> <input name="deletedate" class="easyui-textbox" required="true">
				</div>

			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveDrugsDel()" style="width:90px">保存</a> 
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
				iconCls="icon-ok" onclick="saveDrugsDel_del()" style="width:90px">删除</a> 
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
				iconCls="icon-ok" onclick="searchDrugsDel()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
