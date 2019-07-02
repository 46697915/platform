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
	function addGoods(){
		$('#dlg').dialog('open').dialog('setTitle','新增');
		$('#fm').form('clear');
		url=path+"/goods/add";
		mesTitle = '新增成功';
	}
	
	//编辑用户信息
 	function editGoods(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑');
		 	$('#fm').form('load',row);
		 	url = path+"/goods/modify?id="+id;
		 	mesTitle = '编辑成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteGoods(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/goods/delete?id="+id;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveGoods(){
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
	function saveGoods_del(){
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
 	function searchGoodsQ(){
	 	$("#datagrid").datagrid("load", {
	 		"barcode_search": $('#barcode_search').combobox('getValue'),
	 		"name_search": $('#name_search').val(),
	 		"stock_search": $('#stock_search').val()
        });
	}
	//清空条件
	function cleanSearch(){
		$('#barcode_search').combobox('setValue','');
		$('#name_search').val('');
		$('#stock_search').val('');
	}
	
	//查询
 	function searchGoods(){
		$('#dlgsearch').dialog('open').dialog('setTitle','查询');
		$('#fmsearch').form('clear');
		url = path+"/user/searchUser";
		mesTitle = '查询成功';
	}
 	function exportExcel(){
 		var method = path + '/goods/exportExcel';
		  document.getElementById('fm').action = method;
	  	  document.getElementById('fm').submit();
	}
	//刷新
	function reload(){
		$('#datagrid').datagrid('reload'); 
	}
	//页面加载后执行
	$(function(){
		//$("#drugsid_form" ).css("display", "none");
		$('#dosageform_form').combobox({
			url: path+'/keyvalue/kvByType?type=dosageform',
			panelHeight: '120',
			valueField:'code',
			textField:'name'
		});
		$('#units_form').combobox({
			url: path+'/keyvalue/kvByType?type=units',
			panelHeight: '120',
			valueField:'code',
			textField:'name'
		});
		$('#barcode_form').combobox({
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
				$("#drugsid_form").val(rec.id);// 
				//$("#drugsid_form").textbox('setValue',rec.id);
				$("#drugsname_form").textbox('setValue',rec.drugsname);
				$("#commonname_form").textbox('setValue',rec.commonname);
				
				$("#commonshotspell_form").textbox('setValue',rec.commonshotspell);
				$("#commonnamespell_form").textbox('setValue',rec.commonnamespell);
				$("#bxdygx_form").textbox('setValue',rec.bxdygx);
			}
		});
		
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
			url="${path}/goods/datagrid" 
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
					<th field="barcode2" width="100">药品条码2</th>
					<th field="goodsname" width="150">药品名称</th>
					<th field="commonname" width="100">通用名</th>
					<th field="stock" width="100">库存数量</th>
					<th field="drugscode" width="100">药品内部代码</th>
					<th field="operator" width="100">操作人</th>
					<th field="operatedatestr" width="200">操作日期</th>
					<th field="bxdygx" width="200">医保对应关系</th>
					<th field="price" width="100">售价</th>
					<th field="costprice" width="100">成本价</th>
					<th field="units" width="100">购货单位</th>
					<th field="generatenum" width="100">生产批号</th>
					<th field="generatedate" width="100">生成日期</th>
					<th field="validityperiod" width="100">有效期</th>
					<th field="shelflife" width="100">保质期</th>
					<th field="position" width="100">摆放位置</th>
					<th field="storage" width="100">存储位置</th>
					<!--<th field="dosageform" width="100">药品剂型</th>
					--><th field="dosageformname" width="100">药品剂型</th>
					<!--<th field="specifications" width="100">规格</th>
					--><th field="specificationsname" width="100">规格</th>
					<!--<th field="pharmacyid" width="100">药店ID</th>
					-->
					<!--<th field="drugsid" width="100">药品id</th>
					--><th field="commonshotspell" width="100">通用名拼音简码</th>
					<th field="commonnamespell" width="100">通用名拼音码</th>
					<!--<th field="type1" width="100">所属类别1</th>
					<th field="type2" width="100">所属类别2</th>
					<th field="type3" width="100">所属类别3</th>
				--></tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a>
			<!-- <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addGoods();">新增</a>  -->
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editGoods();">编辑</a> 
			<!-- <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteDrugs();">删除</a> -->
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="cleanSearch();">清空条件</a> 
			<span>药品:	</span> <input id="barcode_search" name="barcode_search" class="easyui-combobox">
			<span>药品名:</span><input name="name_search" id="name_search" value="" size=10 /> 
			<span>库存:</span><input name="stock_search" id="stock_search" value="" size=10 /> 
  			<a href="javascript:searchGoodsQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			<a href="javascript:exportExcel()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">导出</a> 
			<!--<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchGoods();">更多查询</a>
		--></div>

		<!-- 添加/修改对话框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:400px;padding:30px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post" novalidate>
			
				<div class="fitem">
					<label>药品条码:</label> <input id="barcode_form" name="barcode" class="easyui-textbox" required="true">
				</div>
				<div class="fitem" style="display:none" >
					<label>药品ID:</label> <input id="drugsid_form" hidden="hidden"  name="drugsid" >
				</div>
				<div class="fitem">
					<label>药品名称:</label> <input  id="drugsname_form" name="goodsname" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>通用名:</label> <input id="commonname_form"  name="commonname" class="easyui-textbox" required="true">
				</div>
				<div class="fitem" style="display:none">
					<label>通用名拼音简码:</label> <input id="commonshotspell_form"  name="commonshotspell" class="easyui-textbox" >
				</div>
				<div class="fitem"  style="display:none">
					<label>通用名拼音码:</label> <input id="commonnamespell_form"  name="commonnamespell" class="easyui-textbox" >
				</div>
				<div class="fitem" style="display:none">
					<label>医保对应关系:</label> <input id="bxdygx_form"  name="bxdygx" class="easyui-textbox" >
				</div>
				<!--<div class="fitem"  style="display:none">
					<label>所属类别1:</label> <input name="type1" class="easyui-textbox" >
				</div>
				<div class="fitem"  style="display:none">
					<label>所属类别2:</label> <input name="type2" class="easyui-textbox" >
				</div>
				<div class="fitem"  style="display:none">
					<label>所属类别3:</label> <input name="type3" class="easyui-textbox" >
				</div>
				-->
				<div class="fitem">
					<label>药品剂型:</label> <input id="dosageform_form"  name="dosageform" class="easyui-textbox" >
				</div>
				<div class="fitem">
					<label>规格:</label> <input id="specifications_form" name="specifications" class="easyui-textbox" >
				</div>
				<div class="fitem">
					<label>售价:</label> <input name="price" class="easyui-numberbox" precision="2" required="true">
				</div>
				<div class="fitem">
					<label>成本价:</label> <input name="costprice" class="easyui-numberbox" precision="2" >
				</div>
				<div class="fitem">
					<label>购货单位:</label> <input id="units_form" name="units" class="easyui-textbox">
				</div>
				<div class="fitem">
					<label>生产批号:</label> <input name="generatenum" class="easyui-textbox" >
				</div>
				<div class="fitem">
					<label>生产日期:</label> <input name="generatedate" class="easyui-datebox" >
				</div>
				<div class="fitem">
					<label>有效期:</label> <input name="validityperiod" class="easyui-textbox" >
				</div>
				<div class="fitem">
					<label>保质期:</label> <input name="shelflife" class="easyui-textbox">
				</div>
				<div class="fitem" style="display:none">
					<label>库存数量:</label> <input name="stock" class="easyui-numberbox"  >
				</div>
				<div class="fitem">
					<label>摆放位置:</label> <input name="position" class="easyui-textbox" >
				</div>
				<div class="fitem">
					<label>存储位置:</label> <input name="storage" class="easyui-textbox" >
				</div>
				<div class="fitem"  style="display:none">
					<label>所属药店:</label> <input  name="pharmacyid" class="easyui-textbox" >
				</div>
				<div class="fitem"  style="display:none" >
					<label>操作人:</label> <input name="operator" class="easyui-textbox" >
				</div>
				
				
				
			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveGoods()" style="width:90px">保存</a> 
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
				iconCls="icon-ok" onclick="saveGoods_del()" style="width:90px">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlg_delete').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
		<!-- 查询对话框 -->
		<div id="dlgsearch" class="easyui-dialog"
			style="width:400px;height:380px;padding:30px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fmsearch" method="post" novalidate>
				<label>商品:</label>  
                    <span id="span1" style="display: inline-block;"></span>  
                </div>
			</form>
		</div>
		
		<!-- 查询对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="searchGoods()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
