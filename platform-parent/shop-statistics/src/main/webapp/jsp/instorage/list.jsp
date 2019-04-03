<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统</title>
	<%@include file="/jspcommon/easyui_core.jsp"%>

<script type="text/javascript">
	//请求地址
	var url;
	//提示消息
	var mesTitle;
	
	//页面加载后执行
	$(function(){
		$('#signtype_form').combobox({
			url: __path+'/keyvalue/kvByType?type=signtype',
			panelHeight: 'auto',
			valueField:'code',
			textField:'name'
		});
		$('#intype_form').combobox({
			url: __path+'/keyvalue/kvByType?type=instoretype',
			panelHeight: 'auto',
			valueField:'code',
			textField:'name'
		});
		$('#barcode_form').combobox({
			url: __path+'/drugs/findBy?type=specifications',
			panelHeight: '120',
			valueField:'barcode',
			textField:'commonname',
			filter: function(q, row){
			    var opts = $(this).combobox('options');
			    var r = row[opts.textField].indexOf(q) >= 0 || 
			    		//row['barcode'].indexOf(q) >= 0 || 
			    		row['barcode2'].indexOf(q) >= 0 || 
			    		row['drugscode'].indexOf(q) >= 0 
			    		|| row['commonshotspell'].indexOf(q.toUpperCase()) >= 0
			    		;
			    return r;
			},
			onSelect: function(rec){  
				$("#drugsname_form").textbox('setValue',rec.drugsname);
				$("#commonname_form").textbox('setValue',rec.commonname);
				$('#barcode_show').textbox('setValue',rec.barcode) ;
			}
		});
		
		//barcode_show 组件的回车事件
		function keyDownEventFun(){
		    $('#barcode_show').textbox('textbox').keydown(function (event) {
		        if (event.keyCode == 13) {
		        	var bc = $('#barcode_show').textbox('getText');
		        	var bcArr = $('#barcode_form').combobox('getData');
		        	
		        	var isHas = false ;
		        	for (var i = 0; i < bcArr.length; i++){
		        		if(bc==bcArr[i].barcode2){
		        			var rec = bcArr[i] ;
		        			$('#barcode_form').combobox('setValue',rec.barcode);
							$("#drugsname_form").textbox('setValue',rec.drugsname);
							$("#commonname_form").textbox('setValue',rec.commonname);
// 		        		alert(rec.barcode);
							$('#barcode_show').textbox('setValue',rec.barcode) ;
							isHas = true ;
		        		}
		        	}
		        	if(!isHas){
	        			$('#barcode_form').combobox('setValue',"");
						$("#drugsname_form").textbox('setValue',"");
						$("#commonname_form").textbox('setValue',"");
		        	}
		        	if(isHas){
			        	//使入库数量获取焦点
				        $("#inquantity_form").textbox().next('span').find('input').focus();
			        }
		        }
		    });
		
		}
		keyDownEventFun();
		$('#search_barcode').combobox({
			url: __path+'/drugs/findBy?type=specifications',
			panelHeight: '120',
			valueField:'barcode',
			textField:'commonname',
			multiple: false,
        	//onHidePanel: onComboboxHidePanel,
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
	
	//添加用户信息
	function addInstorage(){
		$('#dlg').dialog('open').dialog('setTitle','新增');
		$('#fm').form('clear');
		$('#intype_form').combobox('setValue', '001');
		$('#signtype_form').combobox('setValue', 'zcrk');

		var curr_time = new Date();
		var str = curr_time.getFullYear()+"-";
		str += curr_time.getMonth()+1+"-";
		str += curr_time.getDate();
// 		str += curr_time.getHours()+":";
// 		str += curr_time.getMinutes()+":";
// 		str += curr_time.getSeconds();
		$('#indate_form').datebox('setValue',str);

		url=__path+"/instorage/add";
		mesTitle = '新增成功';
		$('#barcode_show').textbox('setValue','') ;
	}
	
	//编辑用户信息
 	function editInstorage(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑');
		 	$('#fm').form('load',row);
		 	url = __path+"/instorage/modify?id="+id;
		 	mesTitle = '编辑成功';
		 	$('#barcode_show').textbox('setValue','') ;
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteInstorage(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = __path+"/instorage/delete?id="+id;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveInstorage(){
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
				 	$('#barcode_show').textbox('setValue','') ;
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
	function saveInstorage_del(){
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

	//查询，导出参数
	function getParam(){
		var param = {
			"barcode_search": $('#search_barcode').combobox('getValue'),
			"indate_begin": $('#search_begin').datebox('getValue'),
			"indate_end": $('#search_end').datebox('getValue'),
			"drugsname": $('#name_search').val()
	    };
	    return param;
    }
	//清空条件
	function cleanSearch(){
		$('#search_barcode').combobox('setValue','');
		$('#search_begin').datebox('setValue','');
		$('#search_end').datebox('setValue','');
		$('#name_search').val('');
	}
	//快速查询
 	function searchInstorageQ(){
		// $("#datagrid").datagrid({url: __path+'/syInstorageJz/listForPage'});
		var options = $('#datagrid').datagrid('options');
		options.url = __path + '/syInstorageJz/listForPage';
	 	$("#datagrid").datagrid("load", getParam());
	}
	//导出Excel 
 	function exportExcel(){
		
 		var method = __path + '/syInstorageJz/exportInstorageExcel?barcode_search='+$('#search_barcode').combobox('getValue')+
 			'&indate_begin='+$('#search_begin').datebox('getValue')+
 			'&indate_end='+$('#search_end').datebox('getValue')
 			'&drugsname='+$('#name_search').val();
		document.getElementById('fmExport').action = method;
		document.getElementById('fmExport').submit();
		/*
		MaskUtil.mask();
		$.ajax({
			type : "POST",
			url  : __path+'/instorage/exportInstorageExcel',
			data : getParam(),
			success : function(result){
				MaskUtil.unmask();
				var result = result ;
				if (result.success){
					 mesTitle = '导出成功！';
				} else {
					 mesTitle = '导出失败！';
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
		*/
	}
	
	//查询
 	function searchInstorage(){
		$('#dlgsearch').dialog('open').dialog('setTitle','查询');
		$('#fmsearch').form('clear');
		url = __path+"/user/searchUser";
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
			url=""
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
					<th field="commonname" width="100">通用名</th>
					<th field="inquantity" width="100">入库数量</th>
					<!-- <th field="intype" width="100">入库类型</th> -->
					<th field="intypename" width="100">入库类型</th>
					<th field="indate" width="150">入库日期</th>
					<th field="inperson" width="100">入库人</th>
					<th field="money" width="150">入库金额</th>
					<!-- <th field="loggingdate" width="150">入库操作日期</th> -->
					<th field="generatedate" width="150">生产日期</th>
					<th field="validityperiod" width="100">有效期</th>
					<th field="shelflife" width="100">保质期</th>
					<th field="generatenum" width="100">生产批号</th>
					<th field="reviewer" width="100">复核人</th>
					<th field="reviewdate" width="100">复合日期</th>
					<th field="remark" width="100">备注</th>
					<th field="signtypename" width="100">标记类型</th>
					<th field="signtype" width="100">标记类型</th>
					<th field="barcode2" width="100">药品条码2</th>

				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a>
			<%--<a href="javascript:void(0);" class="easyui-linkbutton"--%>
				<%--iconCls="icon-add" plain="true" onclick="addInstorage();">新增</a> --%>
			<!-- <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editInstorage();">编辑</a>  -->
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="cleanSearch();">清空条件</a> 
			<!-- 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteInstorage();">删除</a> -->
				
			<span>药品:	</span> <input id="search_barcode" name="barcode" class="easyui-combobox">
			<span>入库日期:</span>
			<input id="search_begin" class="easyui-datebox" size=10  >
			至
			<input id="search_end" class="easyui-datebox" size=10  >
			<span>药品名:</span><input name="name_search" id="name_search" value="" size=10 /> 
  			<a href="javascript:searchInstorageQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			<a href="javascript:exportExcel()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">导出</a> 
			<!--<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchInstorage();">更多查询</a>
		--></div>

		<!-- 添加/修改对话框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:400px;padding:10px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post" novalidate>
				<div class="fitem">
					<label>药品条码:</label> <input id="barcode_show" name="barcode_show"  class="easyui-textbox" required="true"></font>
				</div>
				<div class="fitem">
					<label>药品条码:</label> <input id="barcode_form" name="barcode" class="easyui-combobox" required="true">
				</div>
				<div class="fitem">
					<label>药品名称:</label> <input id="drugsname_form" name="drugsname" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>通用名:</label> <input id="commonname_form" name="commonname" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>入库数量:</label> <input id="inquantity_form"  name="inquantity" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>入库日期:</label> <input id="indate_form" name="indate" class="easyui-datebox">
				</div>
				<div class="fitem">
					<label>入库金额:</label> <input name="money" class="easyui-numberbox">
				</div>
				<div class="fitem">
					<label>入库类型:</label> <input id="intype_form" name="intype" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>标记类型:</label> <input id="signtype_form" name="signtype" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>生产批号:</label> <input name="generatenum" class="easyui-textbox" >
				</div>
				<div class="fitem">
					<label>生产日期:</label> <input name="generatedate" class="easyui-datebox">
				</div>
				<div class="fitem">
					<label>有效期:</label> <input name="validityperiod" class="easyui-textbox">
				</div>
				<div class="fitem">
					<label>保质期:</label> <input name="shelflife" class="easyui-textbox">
				</div>
				<div class="fitem" style="display:none" >
					<label>入库人:</label> <input name="inperson" class="easyui-textbox">
				</div>
				<div class="fitem" style="display:none" >
					<label>复核人:</label> <input name="reviewer" class="easyui-textbox">
				</div>
				<div class="fitem" style="display:none" >
					<label>复合日期:</label> <input name="reviewdate" class="easyui-textbox">
				</div>
				<div class="fitem">
					<label>备注:</label> <input name="remark" class="easyui-textbox" data-options="multiline:true" style="height:60px" >
				</div>

			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveInstorage()" style="width:90px">保存</a> 
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
				iconCls="icon-ok" onclick="saveInstorage_del()" style="width:90px">删除</a> 
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
				iconCls="icon-ok" onclick="searchInstorage()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
		<div id="export-form">
			<form id="fmExport" method="post">
			</form>
		</div>
	</div>
</body>
</html>
