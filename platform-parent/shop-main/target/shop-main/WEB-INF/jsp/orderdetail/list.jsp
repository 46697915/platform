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
	function addOrderdetail(){
		$('#dlg').dialog('open').dialog('setTitle','新增');
		$('#fm').form('clear');
		url=path+"/orderdetail/add";
		mesTitle = '新增成功';
	}
	
	//编辑用户信息
 	function editOrderdetail(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑');
		 	$('#fm').form('load',row);
		 	url = path+"/orderdetail/modify?id="+id;
		 	mesTitle = '编辑成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteOrderdetail(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/orderdetail/delete?id="+id;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveOrderdetail(){
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
	function saveOrderdetail_del(){
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
 	function searchOrderdetailQ(){
	 	$("#datagrid").datagrid("load", {
	 		"barcode_search": $('#barcode_search').combobox('getValue'),
	 	 	"createdate_begin": $('#search_begin').datebox('getValue'),
            "createdate_end": $('#search_end').datebox('getValue'),
            "ordercode": $('#ordercode').val()
        });
	}
	//清空条件
	function cleanSearch(){
		$('#barcode_search').combobox('setValue','');
		$('#search_begin').datebox('setValue','');
		$('#search_end').datebox('setValue','');
		$('#ordercode').textbox('setValue','');
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
		$('#paytype_form').combobox({
			url: path+'/keyvalue/kvByType?type=paytype',
			panelHeight: 'auto',
			valueField:'code',
			textField:'name'
		});
		
		$('#ismodify_form').combobox({
			url: path+'/keyvalue/kvByType?type=isnot',
			panelHeight: 'auto',
			valueField:'code',
			textField:'name'
		});
		$('#iscomment_form').combobox({
			url: path+'/keyvalue/kvByType?type=isnot',
			panelHeight: 'auto',
			valueField:'code',
			textField:'name'
		});

		$('#isenough_form').combobox({
			url: path+'/keyvalue/kvByType?type=isnot',
			panelHeight: 'auto',
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
			url="${path}/orderdetail/datagrid" 
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
					<th field="ordercode" width="150">订单编号</th>
					<th field="goodsname" width="150">药品名称</th>
					<th field="commonname" width="150">通用名</th>
					<th field="price" width="100">价格</th>
					<th field="amount" width="100">数量</th>
					<th field="money" width="100">总金额</th>
					<th field="detailcode" width="100">订单项编号</th>
					<th field="barcode" width="100">药品条码</th>
					<th field="giftcode" width="200">赠品编码</th>
					<th field="transportmoney" width="200">配送费</th>
					<%--<th field="iscomment" width="200">是否评价过</th>
					--%><th field="iscommentname" width="200">是否评价过</th>
					<th field="getmoney" width="200">收款金额</th>
					<%--<th field="paytype" width="200">支付方式</th>
					--%><th field="paytypename" width="200">支付方式</th>
					<th field="peybank" width="200">支付银行</th>
					<th field="remark" width="200">备注</th>
					<th field="commentid" width="200">评论ID</th>
					<%--<th field="isenough" width="200">库存是否充足</th>
					--%><th field="isenoughname" width="200">库存是否充足</th>
					<th field="units" width="200">购货单位</th>
					<%--<th field="goodsid" width="200">药品ID</th>
					--%><th field="createdate" width="160">创建日期</th>
				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
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
			<span>订单编号:</span><input name="ordercode" id="ordercode" value="" size=10  class="easyui-textbox"/> 
  			<a href="javascript:searchOrderdetailQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			<%--<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchOrderdetail();">更多查询</a>
		--%></div>

		<!-- 添加/修改对话框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:400px;padding:30px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post" novalidate>
			
				<div class="fitem">
					<label>订单编号:</label> <input name="ordercode" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>订单项编号:</label> <input name="detailcode" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>药品条码:</label> <input name="barcode" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>药品名称:</label> <input name="goodsname" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>通用名:</label> <input name="commonname" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>赠品编码:</label> <input name="giftcode" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>价格:</label> <input name="price" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>数量:</label> <input name="amount" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>总金额:</label> <input name="money" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>配送费:</label> <input name="transportmoney" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>是否评价过:</label> <input id="iscomment_form" name="iscomment" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>收款金额:</label> <input name="getmoney" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>支付方式:</label> <input id="paytype_form" name="paytype" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>支付银行:</label> <input name="peybank" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>备注:</label> <input name="remark" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>评论ID:</label> <input name="commentid" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>库存是否充足:</label> <input id="isenough_form" name="isenough" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>购货单位:</label> <input name="units" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>购货单位:</label> <input name="goodsid" class="easyui-textbox">
				</div>
			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveOrderdetail()" style="width:90px">保存</a> 
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
				iconCls="icon-ok" onclick="saveOrderdetail_del()" style="width:90px">删除</a> 
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
				iconCls="icon-ok" onclick="searchOrderdetail()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
