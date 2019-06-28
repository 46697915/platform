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
	function addOrderJz(){
		$('#dlg').dialog('open').dialog('setTitle','新增');
		$('#fm').form('clear');
		url=path+"/orderJz/add";
		mesTitle = '新增成功';
	}
	
	//编辑用户信息
 	function editOrderJz(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑');
		 	$('#fm').form('load',row);
		 	url = path+"/orderJz/modify?id="+id;
		 	mesTitle = '编辑成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteOrderJz(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/orderJz/delete?id="+id;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveOrderJz(){
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
	function saveOrderJz_del(){
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
 	function searchOrderJzQ(){
	 	$("#datagrid").datagrid("load", {
            "username": $('#search_username').val()
        });
	}
	
	//查询
 	function searchOrderJz(){
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
			url="${path}/orderJz/datagrid" 
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
					<th field="ordercode" width="100">订单编号</th>
					<th field="associator" width="100">会员名</th>
					<th field="paytype" width="100">付款方式</th>
					<th field="transport" width="100">运输方式</th>
					<th field="discount" width="100">折扣</th>
					<th field="createdate" width="100">创建日期</th>
					<th field="remark" width="100">备注</th>
					<th field="orderstate" width="100">订单状态</th>
					<th field="paystate" width="100">付款状态</th>
					<th field="refundstae" width="100">退款状态</th>
					<th field="receiptamount" width="100">收款金额</th>
					<th field="oderamount" width="100">订单总金额</th>
					<th field="spentscore" width="100">订单总税换积分</th>
					<th field="transportamount" width="100">运费总金额</th>
					<th field="goodsamount" width="100">商品总金额</th>
					<th field="goodstotal" width="100">商品总数量</th>
					<th field="ismodify" width="100">是否修改过</th>
					<th field="transporttype" width="100">配送方式编码</th>
					<th field="transporttypename" width="100">配送方式名称</th>
					<th field="transportno" width="100">快递运单号</th>
					<th field="transportcompany" width="100">快递公司名称</th>
					<th field="transportremark" width="100">确认发货备注</th>
					<th field="customerremark" width="100">客户附加要求</th>
					<th field="iscomment" width="100">是否已经评论</th>
					<th field="getscore" width="100">订单获赠积分</th>

				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addOrderJz();">新增</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editOrderJz();">编辑</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteOrderJz();">删除</a>
			<span>用户名:</span><input name="search_username" id="search_username" value="" size=10 /> 
  			<a href="javascript:searchOrderJzQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchOrderJz();">更多查询</a>
		</div>

		<!-- 添加/修改对话框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:400px;padding:30px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post" novalidate>
			
				<div class="fitem">
					<label>订单编号:</label> <input name="ordercode" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>会员名:</label> <input name="associator" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>付款方式:</label> <input name="paytype" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>运输方式:</label> <input name="transport" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>折扣:</label> <input name="discount" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>创建日期:</label> <input name="createdate" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>备注:</label> <input name="remark" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>订单状态:</label> <input name="orderstate" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>付款状态:</label> <input name="paystate" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>退款状态:</label> <input name="refundstae" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>收款金额:</label> <input name="receiptamount" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>订单总金额:</label> <input name="oderamount" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>订单总税换积分:</label> <input name="spentscore" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>运费总金额:</label> <input name="transportamount" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>商品总金额:</label> <input name="goodsamount" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>商品总数量:</label> <input name="goodstotal" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>是否修改过:</label> <input name="ismodify" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>配送方式编码:</label> <input name="transporttype" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>配送方式名称:</label> <input name="transporttypename" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>快递运单号:</label> <input name="transportno" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>快递公司名称:</label> <input name="transportcompany" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>确认发货备注:</label> <input name="transportremark" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>客户附加要求:</label> <input name="customerremark" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>是否已经评论:</label> <input name="iscomment" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>订单获赠积分:</label> <input name="getscore" class="easyui-textbox" required="true">
				</div>

			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveOrderJz()" style="width:90px">保存</a> 
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
				iconCls="icon-ok" onclick="saveOrderJz_del()" style="width:90px">删除</a> 
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
				iconCls="icon-ok" onclick="searchOrderJz()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
