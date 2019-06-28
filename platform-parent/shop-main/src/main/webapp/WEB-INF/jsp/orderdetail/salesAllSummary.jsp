<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>所有销售数据汇总（现金和医保）</title>
<%@include file="/WEB-INF/jsp/include/easyui_core.jsp"%>

<script type="text/javascript">
	//请求地址
	var url;
	//提示消息
	var mesTitle;
	

	//快速查询
 	function searchOrderdetailQ(){
	    var options = $('#datagrid').datagrid('options');
	    options.url = path + '/orderdetail/salesAllSummaryData';
	    /**
	    options.queryParams = {
	 		"barcode_search": $('#barcode_search').combobox('getValue'),
	 	 	"createdate_begin": $('#search_begin').datebox('getValue'),
            "createdate_end": $('#search_end').datebox('getValue'),
            "ordercode": $('#ordercode').val()
        };
    	$('#datagrid').datagrid(options);
    	*/
	 	$("#datagrid").datagrid("load", {
	 		"barcode_search": $('#barcode_search').combobox('getValue'),
	 	 	"createdate_begin": $('#search_begin').datebox('getValue'),
            "createdate_end": $('#search_end').datebox('getValue'),
            "ordercode": $('#ordercode').val()
        });
	}
 	function exportExcel(){
 		var method = path + '/orderdetail/exportAllExcel?barcode_search='+$('#barcode_search').combobox('getValue')+
 			'&createdate_begin='+$('#search_begin').datebox('getValue')+
 			'&createdate_end='+$('#search_end').datebox('getValue');
		  document.getElementById('ff').action = method;
	  	  document.getElementById('ff').submit();
	}
 	
	//清空条件
	function cleanSearch(){
		$('#barcode_search').combobox('setValue','');
		$('#search_begin').datebox('setValue','');
		$('#search_end').datebox('setValue','');
		$('#ordercode').val('');
	}
	
	//查询
 	function searchOrderdetail(){
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
			<!-- url="${path}/orderdetail/salesAllSummaryData"  -->
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
					<th field="commonname" width="250">通用名</th>
					<th field="total" width="100">数量</th>
					<th field="amount" width="100">总金额</th>
					<th field="specifications" width="250">规格</th>
					<th field="unitsname" width="100">单位</th>
					<th field="barcode" width="100">条形码</th>
				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<form id="ff" method="post" >
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a>
			<%--<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addOrderdetail();">新增</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editOrderdetail();">编辑</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteOrderdetail();">删除</a>
			--%><a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="cleanSearch();">清空条件</a> 
			<span>药品:	</span> <input id="barcode_search" name="barcode_search" class="easyui-combobox">
			<span>交易日期:</span><input id="search_begin" class="easyui-datebox" size=10  >
							至<input id="search_end" class="easyui-datebox" size=10  >
			<%--<span>订单编号:</span><input name="ordercode" id="ordercode" value="" size=10 /> --%>
  			<a href="javascript:searchOrderdetailQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			<a href="javascript:exportExcel()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">导出</a> 
			<%--<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchOrderdetail();">更多查询</a>
		--%></div>
</form>
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
				iconCls="icon-ok" onclick="searchOrderdetail()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
