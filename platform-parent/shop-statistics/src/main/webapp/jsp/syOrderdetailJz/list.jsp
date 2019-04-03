<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/jspcommon/easyui_core.jsp"%>
    <title>列表</title>

</head>
<body class="easyui-layout" fit="true">

<div region="center" border="false" style="overflow: hidden;">
    <table id="dg" class="easyui-datagrid"
           url="${pageContext.request.contextPath}/syOrderdetailJz/listForPage"
           toolbar="#toolbar" pagination="true"
           rownumbers="true" fitColumns="true"
           singleSelect="true"
           striped="true"
           fit="true"
           nowrap="false">
        <thead>
        <tr>
            <th field="id" width="100">ID</th>
            <th field="ordercode" width="100">订单编号</th>
            <th field="detailcode" width="100">订单项编号</th>
            <th field="goodsid" width="100">商品ID</th>
            <th field="barcode" width="100">药品条码</th>
            <th field="goodsname" width="100">药品名称</th>
            <th field="commonname" width="100">通用名</th>
            <th field="giftcode" width="100">赠品编码</th>
            <th field="price" width="100">价格</th>
            <th field="amount" width="100">数量</th>
            <th field="money" width="100">总金额</th>
            <th field="transportmoney" width="100">配送费</th>
            <th field="iscomment" width="100">是否评价过</th>
            <th field="getmoney" width="100">收款金额</th>
            <th field="paytype" width="100">支付方式</th>
            <th field="peybank" width="100">支付银行</th>
            <th field="remark" width="100">备注</th>
            <th field="commentid" width="100">评论ID</th>
            <th field="isenough" width="100">库存是否充足</th>
            <th field="units" width="100">购货单位</th>
            <th field="createdate" width="100">创建日期</th>
            <th field="transferId" width="100">结转记录id</th>
        </tr>
        </thead>
    </table>
    <div id="toolbar">
        <form id="searchfm" method="post">
            <table>
                <tr>
                    <td>
                        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addObject()">添加</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                           onclick="editObject()">编辑</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                           onclick="destroyObject()">删除</a>
                    </td>
                    <td>
                        <input id="searchName" name="name" class="easyui-textbox" data-options="prompt:'输入查询名称'">
                    </td>
                    <td>
                        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <div id="dlg" class="easyui-dialog"
         data-options="closed:true,modal:true,border:'thin',
         width: 600,
         height: 400 ,
         title: '添加/修改',
         buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <table>
                <tr>
                    <td>
                        <input name="id" class="easyui-textbox" label="ID:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="ordercode" class="easyui-textbox" label="订单编号:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="detailcode" class="easyui-textbox" label="订单项编号:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="goodsid" class="easyui-textbox" label="商品ID:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="barcode" class="easyui-textbox" label="药品条码:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="goodsname" class="easyui-textbox" label="药品名称:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="commonname" class="easyui-textbox" label="通用名:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="giftcode" class="easyui-textbox" label="赠品编码:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="price" class="easyui-textbox" label="价格:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="amount" class="easyui-textbox" label="数量:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="money" class="easyui-textbox" label="总金额:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="transportmoney" class="easyui-textbox" label="配送费:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="iscomment" class="easyui-textbox" label="是否评价过:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="getmoney" class="easyui-textbox" label="收款金额:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="paytype" class="easyui-textbox" label="支付方式:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="peybank" class="easyui-textbox" label="支付银行:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="remark" class="easyui-textbox" label="备注:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="commentid" class="easyui-textbox" label="评论ID:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="isenough" class="easyui-textbox" label="库存是否充足:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="units" class="easyui-textbox" label="购货单位:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="createdate" class="easyui-textbox" label="创建日期:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="transferId" class="easyui-textbox" label="结转记录id:" style="width:100%">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveObject()"
           style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
           onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
</div>
<script type="text/javascript">
    var url;
    //页面加载初始化
    $(function () {
        $('#dg').datagrid({
            url: __path + "/syOrderdetailJz/listForPage",
            queryParams:{}//每次请求的参数
        });
    });
    function doSearch() {
//        alert($('#searchName').val());
        //输出以数组形式序列化表单值
        var data = $('#searchfm').serializeArray();
        var obj = {};
        $.each(data, function (i, v) {
            obj[v.name] = v.value;
        })
//        $('#dg').datagrid('load',obj);

        $('#dg').datagrid('load', {
            name: $('#searchName').val()
        });

    }
    function addObject() {
        $('#dlg').dialog('open').dialog('center');
        $('#fm').form('clear');
        url = __path + '/syOrderdetailJz/add';
    }
    function editObject() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('center');
            $('#fm').form('load', row);
            url = __path + '/syOrderdetailJz/edit?id=' + row.id;
        }
    }
    function saveObject() {
        $('#fm').form('submit', {
            url: url,
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('(' + result + ')');
                if (!result.result) {
                    $.messager.show({
                        title: 'Error',
                        msg: result.error
                    });
                } else {
                    $('#dlg').dialog('close');        // close the dialog
                    $('#dg').datagrid('reload');    // reload the user data
                }
            }
        });
    }
    function destroyObject() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Confirm', '你确定删除这条记录吗?', function (r) {
                if (r) {
                    $.post('/syOrderdetailJz/delete', {ids: row.id}, function (result) {
                        if (result.result) {
                            $('#dg').datagrid('reload');    // reload the user data
                        } else {
                            $.messager.show({    // show error message
                                title: 'Error',
                                msg: result.error
                            });
                        }
                    }, 'json');
                }
            });
        }
    }
</script>
</body>
</html>
