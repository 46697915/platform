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
	
	//页面加载后执行
	$(function(){
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
		$('#barcode_search').combobox({
			url: path+'/drugs/findBy?type=specifications',
			panelHeight: '120',
			valueField:'barcode',
			textField:'commonname',
			filter: function(q, row){
			    var opts = $(this).combobox('options');
			    var r = false ;
			    if(row['commonshotspell']){
			    	r = row[opts.textField].indexOf(q) >= 0 || 
			    		//row['barcode'].indexOf(q) >= 0 || 
			    		row['barcode2'].indexOf(q) >= 0 || 
			    		row['commonshotspell'].indexOf(q.toUpperCase()) >= 0;
			    }else{
			    	//alert(row['commonshotspell']+"----"+row['commonname']);
			    	r = row[opts.textField].indexOf(q) >= 0 || 
			    		//row['barcode'].indexOf(q) >= 0|| 
			    		row['barcode2'].indexOf(q) >= 0 ;
			    }
			    return r;
			},
			onSelect: function(rec){
			}
		});
    });
	//添加用户信息
	function addDrugs(){
		$('#dlg').dialog('open').dialog('setTitle','新增');
		$('#fm').form('clear');
		//$('#dosageform_form').combobox('setValue', '001');
		url=path+"/drugs/add";
		mesTitle = '新增成功';
	}
	
	//编辑用户信息
 	function editDrugs(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑');
		 	$('#fm').form('load',row);
		 	url = path+"/drugs/modify?id="+id;
		 	mesTitle = '编辑成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteDrugs(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/drugs/delete?id="+id;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
	//停用信息
 	function stopDrugs(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/drugs/stop?id="+id;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
	//结转
	function transfer(){
 		var jzrq_search = $('#jzrq_search').val();
//  		var jzrq_search = $('#jzrq_search').datebox('getValue');
 		if(jzrq_search == ''){
 			alert('请输结转日期');
 			return ;
 		}
 		
	 	var barcode = "" ;
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (!row){
	 		$.messager.alert('提示', '请选择要结转的记录！', 'error');
	 	}
	 	barcode = row.barcode;
		MaskUtil.mask();
		$.ajax({
			type : "POST",
			url  : path+'/transfer/add',
			data : {
				jzrq: jzrq_search,
				barcode: barcode
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
	//统一结转batchAddForDel
	function batchAddForDel(){
 		var jzrq_search = $('#jzrq_search').val();
//  		var jzrq_search = $('#jzrq_search').datebox('getValue');
 		if(jzrq_search == ''){
 			alert('请输结转日期');
 			return ;
 		}
 		
		MaskUtil.mask();
		$.ajax({
			type : "POST",
			url  : path+'/transfer/batchAddForDel',
			data : {
				jzrq: jzrq_search
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
	//统一结转
	function batchTransfer(){
 		var jzrq_search = $('#jzrq_search').val();
//  		var jzrq_search = $('#jzrq_search').datebox('getValue');
 		if(jzrq_search == ''){
 			alert('请输结转日期');
 			return ;
 		}
 		
		MaskUtil.mask();
		$.ajax({
			type : "POST",
			url  : path+'/transfer/batchAdd',
			data : {
				jzrq: jzrq_search
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
	//更新 药品的库存
 	function updateStore(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/drugs/updateStore?id="+id;
		 	mesTitle = '更新成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveDrugs(){
	 	$('#fm').form('submit',{
		 	url: url,
		 	onSubmit: function(){
		 		return $(this).form('validate');
		 	},
			success: function(result){
				/* console.info(result); */
				var result = eval('('+result+')');
				if (result.success){
				 	//$('#dlg').dialog('close'); 
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
	function saveDrugs_del(){
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
					timeout:5000,
					showType:'slide',
					width: 250,
					height:'30%',  
					style:{  
					    left:'',  
					    right:0,  
					    top:'',  
					    bottom:'0',
					    position:'fixed'//元素定位方式：fixed固定。 默认:absolute绝对定位 
					},
					 title: mesTitle,
					 msg: result.msg
				});
			}
	 	});
	}

	//快速查询
 	function searchDrugsQ(){
	 	$("#datagrid").datagrid("load", {
	 		"barcode_search": $('#barcode_search').combobox('getValue'),
	 		"name_search": $('#name_search').val()
        });
	}
	//清空条件
	function cleanSearch(){
		$('#barcode_search').combobox('setValue','');
		$('#name_search').val('');
	}
	
	//查询
 	function searchDrugs(){
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
			url="${path}/drugs/datagrid" 
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
					<th field="drugsname" width="100">药品名称</th>
					<th field="commonname" width="150">通用名</th>
					<!--<th field="dosageform" width="100">药品剂型</th>
					--><th field="dosageformname" width="100">药品剂型</th>
					<th field="specifications" width="100">规格</th>
					<!--<th field="units" width="100">单位</th>
					--><th field="unitsname" width="100">单位</th>
					<th field="manufactor" width="200">生产厂家</th>
					<th field="operator" width="100">操作人</th>
					<th field="operatedatestr" width="150">操作日期</th>
					
					<th field="approvalnum" width="100">批准文号</th>
					<th field="drugscode" width="100">药品内部代码</th>
					<th field="commonshotspell" width="100">通用名拼音简码</th>
					<th field="commonnamespell" width="100">通用名拼音码</th>
					<th field="bxdygx" width="200">医保对应关系</th>
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
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addDrugs();">新增</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editDrugs();">编辑</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="cleanSearch();">清空条件</a> 
<!-- 			<a href="javascript:void(0);" class="easyui-linkbutton" -->
<!-- 				iconCls="icon-remove" plain="true" onclick="deleteDrugs();">删除</a>   -->
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="stopDrugs();">停用</a> 
			<span>药品:	</span> <input id="barcode_search" name="barcode_search" class="easyui-combobox">
			<span>药品名:</span><input name="name_search" id="name_search" value="" size=10 /> 
  			<a href="javascript:searchDrugsQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			<!--<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchDrugs();">更多查询</a>
			-->
			<br>
			<span>结转截止日期:</span>
			<input id="jzrq_search" class="easyui-textbox" value="2018-12-31" readonly="readonly" size=10 />
<!-- 			<input id="jzrq_search" class="easyui-datebox" value="2017-12-31" size=10  > -->
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="transfer();">结转</a>
<!-- 			<a href="javascript:void(0);" class="easyui-linkbutton" -->
<!-- 				iconCls="icon-remove" plain="true" onclick="batchTransfer();">批量结转</a> -->
<!-- 			<a href="javascript:void(0);" class="easyui-linkbutton" -->
<!-- 				iconCls="icon-remove" plain="true" onclick="batchAddForDel();">批量结转已删除药品</a> -->
 			<a href="javascript:void(0);" class="easyui-linkbutton" 
 				iconCls="icon-remove" plain="true" onclick="updateStore();">更新药品的库存</a> 
			
		</div>

		<!-- 添加/修改对话框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:400px;padding:30px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post" novalidate>
			
				<div class="fitem">
					<label>药品条码:</label> <input name="barcode2" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>药品名称:</label> <input name="drugsname" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>通用名:</label> <input name="commonname" class="easyui-textbox" required="true">
				</div>
				<div class="fitem" >
					<label>通用名拼音简码:</label> <input name="commonshotspell" class="easyui-textbox" >
				</div>
				<div class="fitem" >
					<label>单位:</label> <input id="units_form" name="units" class="easyui-combobox" required="true">
				</div>
				<div class="fitem">
					<label>药品剂型:</label> <input id="dosageform_form" name="dosageform" class="easyui-combobox" required="true">
				</div>
				<div class="fitem">
					<label>规格:</label> <input id="specifications_form" name="specifications" required="true">
				</div>
				<div class="fitem">
					<label>生成厂家:</label> <input name="manufactor" class="easyui-textbox" >
				</div>
				<div class="fitem"  >
					<label>药品内部代码:</label> <input name="drugscode" class="easyui-textbox" >
				</div>
				<div class="fitem" >
					<label>医保对应关系:</label> <input name="bxdygx" class="easyui-textbox" >
				</div>
				<div class="fitem">
					<label>批准文号:</label> <input name="approvalnum" class="easyui-textbox" >
				</div>
				<div class="fitem" >
					<label>通用名拼音码:</label> <input name="commonnamespell" class="easyui-textbox" >
				</div>
				<!--<div class="fitem">
					<label>所属类别1:</label> <input name="type1" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>所属类别2:</label> <input name="type2" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>所属类别3:</label> <input name="type3" class="easyui-textbox" required="true">
				</div>
				-->
				<div class="fitem"  style="display:none">
					<label>操作人:</label> <input name="operator" class="easyui-textbox" >
				</div>
				

			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveDrugs()" style="width:90px">保存</a> 
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
					<label>确定删除吗?</label>
			</form>
		</div>
		
		<!-- 删除对话框按钮 -->
		<div id="dlg-del-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveDrugs_del()" style="width:90px">删除</a> 
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
				iconCls="icon-ok" onclick="searchDrugs()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
