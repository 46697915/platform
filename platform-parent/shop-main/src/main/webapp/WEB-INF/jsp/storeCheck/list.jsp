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
	function addStoreCheck(){
		$('#dlg').dialog('open').dialog('setTitle','新增');
		$('#fm').form('clear');
		url=path+"/storeCheck/add";
		mesTitle = '新增成功';
	}
	
	//编辑用户信息
 	function editStoreCheck(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑');
		 	$('#fm').form('load',row);
		 	url = path+"/storeCheck/modify?id="+id;
		 	mesTitle = '编辑成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteStoreCheck(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/storeCheck/delete?id="+id;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveStoreCheck(){
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
	function saveStoreCheck_del(){
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
 	function searchStoreCheckQ(){
	 	$("#datagrid").datagrid("load", {
	 		"barcode": $('#barcode_search').combobox('getValue'),
	 		"iserrorstore": $("input:checkbox:checked").val(),
            "crrcheckdate": $('#crrcheckdate_search').datebox('getValue'),
	 		"newstore_search": $('#newstore_search').val()
        });
	}
	function exportExcel(){
		var isErrorStore = $("input:checkbox:checked").val();
		if(!isErrorStore){
			isErrorStore = '';
		}
 		var method = path + '/storeCheck/exportStoreCheckExcel'+
 			'?barcode='+$('#barcode_search').combobox('getValue')+
 			'&iserrorstore='+isErrorStore+
 			'&crrcheckdate='+$('#crrcheckdate_search').datebox('getValue')+
 			'&newstore_search='+$('#newstore_search').val();
		  document.getElementById('fmsearch').action = method;
	  	  document.getElementById('fmsearch').submit();
	}
	//清空条件
	function cleanSearch(){
		$('#barcode_search').combobox('setValue','');
		$('#crrcheckdate_search').datebox('setValue','');
		$('#newstore_search').val('');
	}
	
	//盘点库存
	function storeCheck(){
 		var crrcheckdate_search = $('#crrcheckdate_search').datebox('getValue');
 		if(crrcheckdate_search == ''){
 			alert('请输盘点日期');
 			return ;
 		}
		MaskUtil.mask();
		$.ajax({
			type : "POST",
			url  : path+'/storeCheck/check',
			data : {
				crrcheckdate: crrcheckdate_search
			},
			success : function(result){
				MaskUtil.unmask();
				var result = result ;
				if (result.success){
				 	$('#datagrid').datagrid('reload'); 
				} else {
					 mesTitle = '更新失败！';
				}
				$.messager.show({ 
					 title: mesTitle,
					 msg: result.msg
				});
			},
			error : function(){
				MaskUtil.unmask();
				alert("出错了");
			}
		});
	}
	//更加日期删除盘点库存
	function deleteSCByDate(){
 		var crrcheckdate_search = $('#crrcheckdate_search').datebox('getValue');
 		if(crrcheckdate_search == ''){
 			alert('请输盘点日期');
 			return ;
 		}
		MaskUtil.mask();
		$.ajax({
			type : "POST",
			url  : path+'/storeCheck/deleteSCByDate',
			data : {
				crrcheckdate: crrcheckdate_search
			},
			success : function(result){
				MaskUtil.unmask();
				var result = result ;
				if (result.success){
				 	$('#datagrid').datagrid('reload'); 
				} else {
					 mesTitle = '更新失败！';
				}
				$.messager.show({ 
					 title: mesTitle,
					 msg: result.msg
				});
			},
			error : function(){
				MaskUtil.unmask();
				alert("出错了");
			}
		});
	}
	//查询
 	function searchStoreCheck(){
		$('#dlgsearch').dialog('open').dialog('setTitle','查询');
		$('#fmsearch').form('clear');
		url = path+"/user/searchUser";
		mesTitle = '查询成功';
	}
	
	//刷新
	function reload(){
		$('#datagrid').datagrid('reload'); 
	}
	//页面加载后执行
	$(function(){
		$('#barcode_search').combobox({
			url: path+'/drugs/findBy?type=specifications',
			panelHeight: '120',
			valueField:'barcode',
			textField:'commonname',
			filter: function(q, row){
			    var opts = $(this).combobox('options');
			    var r = row[opts.textField].indexOf(q) >= 0 || 
			    		//row['barcode'].indexOf(q) >= 0 || 
			    		row['barcode2'].indexOf(q) >= 0 || 
			    		row['commonshotspell'].indexOf(q.toUpperCase()) >= 0;
			    return r;
			},
			onSelect: function(rec){
			}
		});
		
    });
</script>

</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<!-- 信息列表 title="管理" -->
		<table id="datagrid" class="easyui-datagrid" 
		    fit="true"
			url="${path}/storeCheck/datagrid" 
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
					<th field="drugsname" width="150">药品名称</th>
					<th field="commonname" width="150">通用名</th>
					<th field="initstore" width="100">期初库存</th>
					<th field="instore" width="100">入库数量</th>
					<th field="salecount" width="100">销售数量</th>
					<th field="newstore" width="100">最新库存</th>
					<th field="currstore" width="100">当前实际库存</th>
					<th field="crrcheckdate" width="150">本次盘点日期</th>
					<th field="lastcheckdate" width="150">上次盘点日期</th>
					<th field="checker" width="100">盘点人</th>
					<th field="checkdate" width="150">盘点日期</th>
					<th field="islastcheck" width="100">是否最后一次盘点</th>
					<th field="lastcheckid" width="100">上次盘点ID</th>
					<th field="bxdygx" width="100">医保对应关系</th>
					<th field="drugscode" width="100">药品内部代码</th>
					<!--
					<th field="initamount" width="100">期初库存金额</th>
					<th field="instoreamount" width="100">入库金额</th>
					<th field="saleamount" width="100">销售金额</th>
					<th field="newamount" width="100">最新库存金额</th>
					<th field="curramount" width="100">当前实际库存金额</th>
					--!>
				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a>
			<!-- <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addStoreCheck();">新增</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editStoreCheck();">编辑</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteStoreCheck();">删除</a> -->
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="cleanSearch();">清空条件</a> 
			<span>药品:	</span> <input id="barcode_search" name="barcode_search" class="easyui-combobox">
			<span>盘点日期:</span><input id="crrcheckdate_search" class="easyui-datebox" size=10  > 
  			<span>错误库存:</span><input id="iserrorstore_search" type="checkbox" value='1' class="easyui-checkbox" size=10  >
			<span>最新库存《:</span><input name="newstore_search" id="newstore_search" value="" size=10 />  
  			<a href="javascript:searchStoreCheckQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			<a href="javascript:exportExcel()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">导出</a> 
  			<br>
  			<a href="javascript: storeCheck()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">盘点库存</a>
  			<a href="javascript: deleteSCByDate()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除盘点记录</a> 
			<!-- <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchStoreCheck();">更多查询</a> -->
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
					<label>盘点人:</label> <input name="checker" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>盘点日期:</label> <input name="checkdate" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>医保对应关系:</label> <input name="bxdygx" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>药品内部代码:</label> <input name="drugscode" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>是否最后一次盘点:</label> <input name="islastcheck" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>上次盘点ID:</label> <input name="lastcheckid" class="easyui-textbox" required="true">
				</div>

			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveStoreCheck()" style="width:90px">保存</a> 
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
				iconCls="icon-ok" onclick="saveStoreCheck_del()" style="width:90px">删除</a> 
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
				iconCls="icon-ok" onclick="searchStoreCheck()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
