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
	function addYbjszxx(){
		$('#dlg').dialog('open').dialog('setTitle','新增');
		$('#fm').form('clear');
		url=path+"/ybjszxx/add";
		mesTitle = '新增成功';
	}
	
	//编辑用户信息
 	function editYbjszxx(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg').dialog('open').dialog('setTitle','编辑');
		 	$('#fm').form('load',row);
		 	url = path+"/ybjszxx/modify?id="+id;
		 	mesTitle = '编辑成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
 	
	//删除用信息
 	function deleteYbjszxx(){
	 	var row = $('#datagrid').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除');
		 	$('#fm').form('load',row);
		 	url = path+"/ybjszxx/delete?id="+id;
		 	mesTitle = '删除成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
 	
	//保存添加、修改内容
	function saveYbjszxx(){
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
	function saveYbjszxx_del(){
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
	//获取医保主信息
 	function getYbjszxxShow(){
 		var ybjsrq_search = $('#ybjsrq_search').datebox('getValue');
 		if(ybjsrq_search == ''){
 			alert('请输入结算日期');
 			return ;
 		}
	 	$('#dlg_getYbjszxx').dialog('open').dialog('setTitle','获取医保主信息');
	 	url = path+"/ybjszxx/getYBJSXX?ybjsrq_search="+ybjsrq_search;
	 	mesTitle = '获取成功';
	}
 	
	//获取医保主信息
	function getYbjszxx(){
 		var ybjsrq_search = $('#ybjsrq_search').datebox('getValue');
 		if(ybjsrq_search == ''){
 			alert('请输入结算日期');
 			return ;
 		}
 		MaskUtil.mask();
	 	$('#fm_getYbjszxx').form('submit',{
		 	url: path+"/ybjszxx/getYBJSXX?ybjsrq_search="+ybjsrq_search,
		 	onSubmit: function(){
		 		return $(this).form('validate');
		 	},
			success: function(result){
				/* console.info(result); */
				MaskUtil.unmask();
				var result = eval('('+result+')');
				if (result.success){
				 	$('#dlg_getYbjszxx').dialog('close'); 
				 	$('#datagrid').datagrid('reload'); 
				} else {
					 mesTitle = '获取失败';
				}
				$.messager.show({ 
					 title: mesTitle,
					 msg: result.msg
				});
			}
	 	});
	}
	//获取医保主信息(根据时间段)
	function getYbjszxxBySjd(){
 		var ybjsrq_begin_search = $('#ybjsrq_begin_search').datebox('getValue');
 		var ybjsrq_end_search = $('#ybjsrq_end_search').datebox('getValue');
 		if(ybjsrq_begin_search == ''){
 			alert('请输入结算开始日期');
 			return ;
 		}if(ybjsrq_end_search == ''){
 			alert('请输入结算结束日期');
 			return ;
 		}
 		MaskUtil.mask();
	 	$('#fm_getYbjszxx').form('submit',{
		 	url: path+"/ybjszxx/getYBJSXXBySJD?ybjsrq_begin_search="+ybjsrq_begin_search+"&ybjsrq_end_search="+ybjsrq_end_search,
		 	onSubmit: function(){
		 		return $(this).form('validate');
		 	},
			success: function(result){
				/* console.info(result); */
				MaskUtil.unmask();
				var result = eval('('+result+')');
				if (result.success){
				 	$('#dlg_getYbjszxx').dialog('close'); 
				 	$('#datagrid').datagrid('reload'); 
				} else {
					 mesTitle = '获取失败';
				}
				$.messager.show({ 
					 title: mesTitle,
					 msg: result.msg
				});
			}
	 	});
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
	
	//快速查询
 	function searchYbjszxxQ(){
 		var ybjsrq_begin_search = $('#ybjsrq_begin_search').datebox('getValue');
 		var ybjsrq_end_search = $('#ybjsrq_end_search').datebox('getValue');
	 	$("#datagrid").datagrid("load", {
            "bxid": $('#bxid_search').val(),
            "ybjsrq_begin_search": ybjsrq_begin_search,
            "ybjsrq_end_search": ybjsrq_end_search
        });
	}
	
	//查询
 	function searchYbjszxx(){
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
			url="${path}/ybjszxx/datagrid" 
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
					<th field="kahao" width="100">病人卡号</th>
					<th field="bxid" width="100">保险编号(个人编号)</th>
					<th field="xzmc" width="120">参保险种名称</th>
					<th field="zfy" width="100">总费用</th>
					<th field="jbjjzf" width="100">基本基金支付</th>
					<th field="grzhzf" width="100">个人账户支付</th>
					<th field="xjzh" width="100">现金支付</th>
					<th field="tcqh" width="100">统筹区号</th>
					<th field="yyjgdm" width="100">医院机构编号</th>
					<th field="klxbh" width="100">卡类型编号</th>
					<th field="xzbh" width="100">参保险种编号</th>
					<th field="blh" width="100">病历号（处方号）</th>
					<th field="ptlsh" width="100">平台流水号</th>
					<th field="rylb" width="100">人员类别</th>
					<th field="rylbmc" width="100">人员类别名称</th>
					<th field="sftsmz" width="100">是否特殊门诊</th>
					<th field="tsmzfl" width="100">特殊门诊分类</th>
					<th field="tsmzflmc" width="100">特殊门诊分类名称</th>
					<th field="dbjjzf" width="100">大病基金支付</th>
					<th field="dbeczf" width="100">大病二次补偿支付金额</th>
					<th field="bcjjzf" width="100">补充基金支付</th>
					<th field="gwyjjzf" width="100">公务员基金支付</th>
					<th field="grzf" width="100">个人自付金额</th>
					<th field="qzzf" width="100">其中自费金额</th>
					<th field="qzqfx" width="100">其中起付线金额</th>
					<th field="ybjsbh" width="100">医保结算编号</th>
					<th field="tsbt" width="100">特殊补贴</th>
					<th field="isupdate" width="100">是否更新</th>
				</tr>
			</thead>
		</table>

		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a>
			<!-- 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addYbjszxx();">新增</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editYbjszxx();">编辑</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteInstorage();">删除</a> -->	
				
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="cleanSearch();">清空条件</a> 
			<span>身份证号:</span><input id="bxid_search" name="bxid_search" value="" size=10 />
  			<a href="javascript:searchYbjszxxQ()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-jright" plain="true" onclick="searchYbjszxx();">更多查询</a>
			<br/>
			<span>结算日期:</span>
			<input id="ybjsrq_begin_search" class="easyui-datebox" size=10  >
			至<input id="ybjsrq_end_search" class="easyui-datebox" size=10  >
  			<a href="javascript:getYbjszxxBySjd()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">同步医保数</a>
			<!-- 
  			<a href="javascript: updateStoreByDate()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">更新库存</a>
  			 -->	
		</div>

		<!-- 添加/修改对话框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:400px;padding:30px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post" novalidate>
			
				<div class="fitem">
					<label>统筹区号:</label> <input name="tcqh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>医院机构编号:</label> <input name="yyjgdm" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>卡类型编号:</label> <input name="klxbh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>病人卡号:</label> <input name="kahao" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>保险编号(个人编号):</label> <input name="bxid" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>参保险种编号:</label> <input name="xzbh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>参保险种名称:</label> <input name="xzmc" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>病历号（处方号）:</label> <input name="blh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>平台流水号:</label> <input name="ptlsh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>人员类别:</label> <input name="rylb" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>人员类别名称:</label> <input name="rylbmc" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>是否特殊门诊:</label> <input name="sftsmz" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>特殊门诊分类:</label> <input name="tsmzfl" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>特殊门诊分类名称:</label> <input name="tsmzflmc" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>总费用:</label> <input name="zfy" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>基本基金支付:</label> <input name="jbjjzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>大病基金支付:</label> <input name="dbjjzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>大病二次补偿支付金额:</label> <input name="dbeczf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>补充基金支付:</label> <input name="bcjjzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>公务员基金支付:</label> <input name="gwyjjzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>个人账户支付:</label> <input name="grzhzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>现金支付:</label> <input name="xjzh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>个人自付金额:</label> <input name="grzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>其中自费金额:</label> <input name="qzzf" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>其中起付线金额:</label> <input name="qzqfx" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>医保结算编号:</label> <input name="ybjsbh" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>特殊补贴:</label> <input name="tsbt" class="easyui-textbox" required="true">
				</div>

			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveYbjszxx()" style="width:90px">保存</a> 
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
				iconCls="icon-ok" onclick="saveYbjszxx_del()" style="width:90px">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlg_delete').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
		<!-- 获取医保信息对话框 -->
		<div id="dlg_getYbjszxx" class="easyui-dialog"
			style="width:300px;height:200px;padding:30px 20px" closed="true"
			buttons="#dlg-getYbjszxx-buttons">
			<div class="ftitle">获取医保信息</div>
			<form id="fm_getYbjszxx" method="post" novalidate>
					<label>确定获取医保信息吗？</label>
			</form>
		</div>
		
		<!-- 获取医保信息对话框按钮 -->
		<div id="dlg-getYbjszxx-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="getYbjszxx()" style="width:90px">获取</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlg_getYbjszxx').dialog('close')"
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
				iconCls="icon-ok" onclick="searchYbjszxx()" style="width:90px">查询</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlgsearch').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
	</div>
</body>
</html>
