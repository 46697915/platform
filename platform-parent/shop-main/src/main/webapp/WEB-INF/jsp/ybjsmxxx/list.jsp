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
	function addYbjsmxxx(){
		$('#dlg').dialog('open').dialog('setTitle','新增');
		$('#fm').form('clear');
		url=path+"/ybjsmxxx/add";
		mesTitle = '新增成功';
	}
	
	//编辑用户信息
 	function editYbjsmxxx(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑');
		 	$('#fm').form('load',row);
		 	url = path+"/ybjsmxxx/modify?id="+id;
		 	mesTitle = '编辑成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteYbjsmxxx(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/ybjsmxxx/delete?id="+id;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveYbjsmxxx(){
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
	function saveYbjsmxxx_del(){
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
 	function searchYbjsmxxxQ(){
	 	$("#datagrid").datagrid("load", {
	 		"isupdate": $('#isupdate_search').combobox('getValue'),
	 	 	"hjrq_begin": $('#hjrq_begin').datebox('getValue'),
            "hjrq_end": $('#hjrq_end').datebox('getValue'),
            "yyxmmc": $('#name_search').val(),
            "yyxmbm": $('#yyxmbm_search').val(),
            "username": $('#search_username').val()
        });
	}
	//清空条件
	function cleanSearch(){
		$('#isupdate_search').combobox('setValue','');
		$('#search_begin').datebox('setValue','');
		$('#search_end').datebox('setValue','');
		$('#name_search').val('');
		$('#yyxmbm_search').val('');
	}
	
	//查询
 	function searchYbjsmxxx(){
		$('#dlgsearch').dialog('open').dialog('setTitle','查询');
		$('#fmsearch').form('clear');
		url = path+"/user/searchUser";
		mesTitle = '查询成功';
	}
	
	//更新库存
	function updateStoreByDate(){
 		var ybjsrq_search = $('#ybjsrq_search').datebox('getValue');
 		if(ybjsrq_search == ''){
 			alert('请输入结算日期');
 			return ;
 		}
		MaskUtil.mask();
		$.ajax({
			type : "POST",
			url  : path+'/ybjszxx/updateStoreByDate',
			data : {
				ybjsrq_search: ybjsrq_search
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
	//刷新
	function reload(){
		$('#datagrid').datagrid('reload'); 
	}
	//页面加载后执行
	$(function(){
		$('#isupdate_search').combobox({
			url: path+'/keyvalue/kvByType?type=isnot',
			width: 50,
			panelHeight: 'auto',
			valueField:'code',
			textField:'name'
		});
    });
	function exportExcel(){
 		var method = path + '/ybjsmxxx/exportExcel';
		  document.getElementById('fm').action = method;
	  	  document.getElementById('fm').submit();
	}
</script>

</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<!-- 信息列表 title="管理" -->
		<table id="datagrid" class="easyui-datagrid" 
		    fit="true"
			url="${path}/ybjsmxxx/datagrid" 
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
					<th field="ybjsh" width="100" sortable="true">医保结算号</th>
					<th field="yyxmmc" width="150">医院项目名称</th>
					<th field="xmdj" width="100">项目单价</th>
					<th field="xmsl" width="100">项目数量</th>
					<th field="xmje" width="100">项目金额</th>
					<th field="hjrq" width="100" sortable="true">划价日期</th>
					<th field="kdysxm" width="100">开单医生姓名</th>
					<th field="zxks" width="100">取药窗口/执行科室</th>
					<th field="cfh" width="100">处方号</th>
					<th field="cfnxh" width="100">处方内序号</th>
					<th field="yyxmbm" width="100">医院项目编号</th>
					<th field="ybxmbm" width="100">对应医保项目编码</th>
					<th field="isupdate" width="100">是否更新库存</th>
					<th field="xmgg" width="200">项目规格</th>
					<th field="xmdw" width="100">项目单位</th>
					<th field="xmjx" width="100">项目剂型</th>
					<th field="sfybxm" width="100">是否医保项目</th>
					<th field="mcyl" width="100">每次用量</th>
					<th field="sypc" width="100">使用频次</th>
					<th field="yf" width="100">用法</th>
					<th field="zxts" width="100">执行天数</th>
					<th field="zyh" width="100">住院号</th>
					<th field="barcode" width="200">条形码</th>
				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a>
			<!-- 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addYbjsmxxx();">新增</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editYbjsmxxx();">编辑</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteYbjsmxxx();">删除</a> -->
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="cleanSearch();">清空条件</a> 
			<span>药品名:</span><input name="name_search" id="name_search" value="" size=10 />
			<span>药品内部代码:</span><input name="yyxmbm_search" id="yyxmbm_search" value="" size=10 />  
			<span>划价日期:</span><input id="hjrq_begin" class="easyui-datebox" size=10  >
							至<input id="hjrq_end" class="easyui-datebox" size=10  >
			<span>是否更新库存:	</span> <input id="isupdate_search" name="isupdate_search" class="easyui-combobox">
  			<a href="javascript: searchYbjsmxxxQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			<!-- <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchYbjszxx();">更多查询</a>
			 -->
			<br/>
			<span>结算日期:</span><input id="ybjsrq_search" class="easyui-datebox" size=10  >
  			<a href="javascript: updateStoreByDate()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">更新库存</a>
  			<a href="javascript:exportExcel()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">导出</a> 
			
		</div>

		<!-- 添加/修改对话框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:400px;padding:30px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post" novalidate>
			
				<div class="fitem">
					<label>医保结算号:</label> <input name="ybjsh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>住院号:</label> <input name="zyh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>处方号:</label> <input name="cfh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>处方内序号:</label> <input name="cfnxh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>医院项目编号:</label> <input name="yyxmbm" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>医院项目名称:</label> <input name="yyxmmc" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>对应医保项目编码:</label> <input name="ybxmbm" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>项目规格:</label> <input name="xmgg" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>项目单位:</label> <input name="xmdw" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>项目剂型:</label> <input name="xmjx" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>项目单价:</label> <input name="xmdj" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>项目数量:</label> <input name="xmsl" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>项目金额:</label> <input name="xmje" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>划价日期:</label> <input name="hjrq" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>开单医生姓名:</label> <input name="kdysxm" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>取药窗口/执行科室:</label> <input name="zxks" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>是否医保项目:</label> <input name="sfybxm" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>每次用量:</label> <input name="mcyl" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>使用频次:</label> <input name="sypc" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>用法:</label> <input name="yf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>执行天数:</label> <input name="zxts" class="easyui-textbox" required="true">
				</div>

			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveYbjsmxxx()" style="width:90px">保存</a> 
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
				iconCls="icon-ok" onclick="saveYbjsmxxx_del()" style="width:90px">删除</a> 
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
				iconCls="icon-ok" onclick="searchYbjsmxxx()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
