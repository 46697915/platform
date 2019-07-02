<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统</title>
<%@include file="/WEB-INF/jsp/include/easyui_core.jsp"%>

<script type="text/javascript">
	//提示消息
	var mesTitle;
	var hasAttrDataGrid;
	var noAttrDataGrid;
	$(function() {
		hasAttrDataGrid = $('#hasAttrDataGrid').datagrid({
			url : path+'/goods/datagridForXS',
			striped : true,
			rownumbers : true,
			idField : 'id',
			sortName : 'id',
			sortOrder : 'asc',
			fit: true,
			fitColumns: false,
			pagination: true,
			nowrap: false,
			singleSelect: false, 
			striped: true,
			nowrap: false,
            pageSize: 50,//每页显示的记录条数，默认为10
            pageList: [20, 30, 50, 100],//可以设置每页记录条数的列表
            beforePageText: '第',//页数文本框前显示的汉字
            afterPageText: '页    共 {pages} 页',
            displayMsg: '当前显示 {from}-{to} 条记录,共 {total} 条记录',
			onClickRow: function(rowIndex,rowData){
				moveInByClick(rowData);
			},
			onLoadSuccess: function(data){
				if(data.total == 1){
					moveInByClick(data.rows[0]);
				}
				$('#barcode_goods').val('');
			},
			frozenColumns : [ [ {
				field : 'checkbox',
				checkbox:true
			}] ]
		});
// 		var p = $('#hasAttrDataGrid').datagrid('getPager');
//         $(p).pagination({
//             pageSize: 50,//每页显示的记录条数，默认为10
//             pageList: [20, 30, 50, 100],//可以设置每页记录条数的列表
//             beforePageText: '第',//页数文本框前显示的汉字
//             afterPageText: '页    共 {pages} 页',
//             displayMsg: '当前显示 {from}-{to} 条记录,共 {total} 条记录'
//         });
		
		noAttrDataGrid = $('#noAttrDataGrid').datagrid({
			url : '',
			striped : true,
			rownumbers : true,
			idField : 'id',
			sortName : 'id',
			sortOrder : 'asc',
			frozenColumns : [ [ {
				field : 'checkbox',
				checkbox:true
			}, {
				width : '50',
				title : '名称',
				field : 'name',
				sortable : true
			},
			{
				width : '50',
				title : '单价',
				field : 'sellprice',
				sortable : true
			},
			{
				width : '50',
				title : '数量',
				field : 'count',
				editor: 'numberbox',
				sortable : true
			},{
				width : '50',
				title : 'id',
				field : 'id',
				sortable : true
			}  ] ]
		});
		//某个控件回车事件：
		$('#barcode_goods').keydown(function(e){
			if(e.keyCode==13){
			   queryGoods(); //处理事件
			}
		}); 
		//if (window.event.keyCode==13) window.event.keyCode=0 　　//这样就取消回车键了
		
	});
	//查询商品方法
	function queryGoods(){
	 	$("#hasAttrDataGrid").datagrid("load", {
            "barcode": $('#barcode_goods').val()
        });
	}
	//计算总费用
	function total(){
		var totalMomey = 0;
		var rows = $('#noAttrDataGrid').datagrid('getData').rows;
		
		var length = rows.length;
		for(var i = 0;i<length;i++){
			totalMomey=totalMomey*1+ (rows[i].sellprice)*1*parseInt(rows[i].count);
		}
		$('#totalMoney').val(totalMomey);
		$('#receiveTotalMoney').val(totalMomey);
	}
	//结算方法
	function charge(){
		var rows = $('#noAttrDataGrid').datagrid('getData').rows;
		var totalMoney = $('#totalMoney').val();
		var receiveTotalMoney = $('#receiveTotalMoney').val();
		if(rows){
			MaskUtil.mask();
			$.ajax({
				type : "POST",
				url  : path+'/order/calculateDetail',
				data : {
					rows: JSON.stringify(rows),
					totalMoney:totalMoney,
					receiveTotalMoney:receiveTotalMoney
				},
				success : function(result){
					MaskUtil.unmask();
					var result = result ;
					if (result.success){
					 	emptyOrder(); 
					} else {
						 mesTitle = '订单保存失败';
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
		}else{
			alert("您的订单中没有任何药品！添加要购买的商品");
		}
	}
	//向订单中添加商品(单击事件)
	function moveInByClick(row){
		var id = row.id;
		var goodsname = row.goodsname;
		var sellprice = row.price;
		var rows = $('#noAttrDataGrid').datagrid('getData').rows;  
		var length = rows.length;
		//循环判断是否已经添加过，若添加过，就将数量加一，若没有添加记录
		for(var i = 0;i<length;i++){
			if(id==rows[i].id){
				var rowIndex = $('#noAttrDataGrid').datagrid('getRowIndex', rows[i]);
				var count = parseInt(rows[i].count)+1;
				$('#noAttrDataGrid').datagrid('updateRow',{
					index: rowIndex,
					row: {
						count: count
					}
				});
				total();
				return;
			}
		}
		$('#noAttrDataGrid').datagrid('insertRow',{
			index: length+1,	// 索引从0开始
			row: {
				id: id,
				name: goodsname,
				sellprice: sellprice,
				count: 1
			}
		});
		total();
	}
	//向订单中添加商品
	function moveIn(){
	
		var row = $('#hasAttrDataGrid').datagrid('getSelected');
		var id = row.id;
		var goodsname = row.goodsname;
		var sellprice = row.price;
		var rows = $('#noAttrDataGrid').datagrid('getData').rows;  
		var length = rows.length;
		//循环判断是否已经添加过，若添加过，就将数量加一，若没有添加记录
		for(var i = 0;i<length;i++){
			if(id==rows[i].id){
				var rowIndex = $('#noAttrDataGrid').datagrid('getRowIndex', rows[i]);
				var count = parseInt(rows[i].count)+1;
				$('#noAttrDataGrid').datagrid('updateRow',{
					index: rowIndex,
					row: {
						count: count
					}
				});
				total();
				return;
			}
		}
		$('#noAttrDataGrid').datagrid('insertRow',{
			index: length+1,	// 索引从0开始
			row: {
				id: id,
				name: goodsname,
				sellprice: sellprice,
				count: 1
			}
		});
		total();

		/* var count = 0;
		var jsonstr = '{"total":2,"rows":[{"id":"M000005","name":"检测设备","count":"1"}]}';  
		var data = $.parseJSON(jsonstr);    
		$('#noAttrDataGrid').datagrid('loadData', data); //将数据绑定到datagrid
		var row = $('#hasAttrDataGrid').datagrid('getSelected');
		if(row){
		var id = row.id;
		var goodsname = row.goodsname;
		alert(id);
		alert(goodsname);
			
		}else{
		 $.messager.alert('提示', '请选择要删除的记录！', 'error');
		} */
		/* var idStr = '';
		for(var i = 0; i < checkeds.length; i++){
			idStr += checkeds[i].id + ",";
		}
		if(idStr.length == 0){
			alert("请选择要移入的属性");
			return;
		}
		
		parent.$.messager.confirm('询问', '您是否要执行该操作？', function(b) {
			if (b) {
				progressLoad();
				$.post('${ctx}/goodscat/addCatAttr', {
					catId : idStr,
					attrIds : idStr
				}, function(result) {
					if (result.success) {
						hasAttrDataGrid.datagrid('reload');
						noAttrDataGrid.datagrid('reload');
					} else{
						parent.$.messager.alert('提示', result.msg, 'info');
					}
					progressClose();
				}, 'JSON');
			}
		}); */
		
	}
	//从订单中删除商品 
	function moveOut(){
		var row = $('#noAttrDataGrid').datagrid('getSelected');
		if(row){
			var rowIndex = $('#noAttrDataGrid').datagrid('getRowIndex',row);
			if(row.count>1){
				var count = parseInt(row.count)-1;
				$('#noAttrDataGrid').datagrid('updateRow',{
				index: rowIndex,
				row: {
					count: count
					}
				});
				total();
			}else{
			 	$('#noAttrDataGrid').datagrid('deleteRow', rowIndex);
			 	total();
			}
		}else{
			alert("请选择要删除的药品信息");
		}
		/* var idStr = '';
		for(var i = 0; i < checkeds.length; i++){
			idStr += checkeds[i].id + ",";
		}
		if(idStr.length == 0){
			alert("请选择要移出的属性");
			return;
		}
		
		parent.$.messager.confirm('询问', '您是否要执行该操作？', function(b) {
			if (b) {
				progressLoad();
				$.post('${ctx}/goodscat/removeCatAttr', {
					catId : idStr,
					attrIds : idStr
				}, function(result) {
					if (result.success) {
						hasAttrDataGrid.datagrid('reload');
						noAttrDataGrid.datagrid('reload');
					} else{
						parent.$.messager.alert('提示', result.msg, 'info');
					}
					progressClose();
				}, 'JSON');
			}
		}); */
	}
	//清空订单
	function emptyOrder(){
		var rows = $('#noAttrDataGrid').datagrid('getData').rows;  
		var length = rows.length;
		//循环清空订单
		for(var i = length-1 ; i >= 0; i--){
			var rowIndex = $('#noAttrDataGrid').datagrid('getRowIndex', rows[i]);
			$('#noAttrDataGrid').datagrid('deleteRow', rowIndex);
		}
		$('#totalMoney').val('');
		$('#receiveTotalMoney').val('');
		//hasAttrDataGrid.datagrid('reload');
		queryGoods();
	}
	/**
		从新加载缓存数据
	*/
	function reloadeGoods(){
		MaskUtil.mask();
		$.ajax({
			type : "POST",
			url  : path+'/goods/listToCathe',
			data : {"barcode": ''},
			success : function(result){
				MaskUtil.unmask();
			},
			error : function(){
				MaskUtil.unmask();
				alert("出错了");
			}
		});
	}
</script>
<style>
</style>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:true" >
	
	<!--1.1 egion="north"，指明高度，可以自适应-->
	  <div data-options="region:'north',split:true" style="height:40px">
	    <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reloadeGoods();">拉取商品数据</a>
	  </div>
	  
	  <!--1.2 region="west",必须指明宽度-->
	  <div data-options="region:'west',split:true,title:'商品',collapsible:false" style="overflow: auto; width:47%;">
	  	<div id="toolbar_goods">
	    	<label >条形码：<input type="text" id = "barcode_goods" value = "" width="10px"></label>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="queryGoods();">查询</a> 
		</div>
	    <table id="hasAttrDataGrid" class="easyui-datagrid" 
	    	data-options="toolbar:'#toolbar_goods'">
			<thead>
				<tr>
					<th field="goodsname" width="100">药品名称</th>
					<th field="price" width="100">售价</th>
					<th field="specifications" width="100">规格</th>
					<!--<th field="specificationsname" width="100">规格</th>-->
					<th field="manufactor" width="100">厂家</th>
					<th field="stock" width="100">库存数量</th>
					<th field="barcode2" width="100">药品条码2</th>
					<th field="barcode" width="100">药品条码</th>
					<th field="commonname" width="100">通用名</th>
					
					<!--<th field="dosageform" width="100">药品剂型</th>
					--><th field="dosageformname" width="100">药品剂型</th>
					<!--<th field="type1" width="100">所属类别1</th>
					<th field="type2" width="100">所属类别2</th>
					<th field="type3" width="100">所属类别3</th>
					--><th field="units" width="100">购货单位</th>
					<th field="generatenum" width="100">生产批号</th>
					<th field="generatedate" width="100">生成日期</th>
					<th field="validityperiod" width="100">有效期</th>
					<th field="shelflife" width="100">保质期</th>
					<th field="position" width="100">摆放位置</th>
					<th field="storage" width="100">存储位置</th>
					<th field="costprice" width="100">成本价</th>
					<!--<th field="pharmacyid" width="100">药店ID</th>
					<th field="id" width="100">id</th>
					-->
				</tr>
			</thead>
	    </table>
	  </div>
	  
	  <!--1.3region="center",这里的宽度和高度都是由周边决定，不用设置-->
	  <div data-options="region:'center'" style="padding-top:142px; padding-left:12px;">
	    <br><a href="javascript:void(0)" class="easyui-linkbutton c1" style="width:60px; margin-bottom:20px;" onclick="moveIn()">添加 》</a>
	    <br><a href="javascript:void(0)" class="easyui-linkbutton c5" style="width:60px; margin-bottom:20px;" onclick="moveOut()">《 减少</a>
	    <br><a href="javascript:void(0)" class="easyui-linkbutton c5" style="width:60px" onclick="emptyOrder()">清空订单</a>
	  </div>
	  
	  <!--1.4 region="east",必须指明宽度-->
	  <div data-options="region:'east',split:true,title:'订单',collapsible:false" style="width:45%;">
	  	<div id="toolbar_order">
			<label >总费用：<input type="text" id = "totalMoney" readonly="readonly" value = "0" maxlength="18" size="9">元</label>
	  		<label >实际总费用：<input type="text" id = "receiveTotalMoney" value = "" maxlength="18" size="9">元</label>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="charge();">结算</a> 
		</div>
	  	
	    <table id="noAttrDataGrid" data-options="fit:true,border:false,toolbar:'#toolbar_order'"></table>
	   </div>
	  
	   <!--1.5 region="south"，指明高度，可以自适应-->
	  <div data-options="region:'south',split:true" style="height:50px;"><center> <h3> </h3></center></div>
	  
	</div>
</body>
</html>
