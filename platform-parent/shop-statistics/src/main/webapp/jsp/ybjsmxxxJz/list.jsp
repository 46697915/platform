<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <%@include file="/jspcommon/easyui_core.jsp"%>
    <title>列表</title>

</head>
<body class="easyui-layout" fit="true">

<div region="center" border="false" style="overflow: hidden;">
    <table id="dg" class="easyui-datagrid"
           url="${pageContext.request.contextPath}/ybjsmxxxJz/listForPage"
           toolbar="#toolbar" pagination="true"
           rownumbers="true" fitColumns="true"
           singleSelect="true"
           striped="true"
           fit="true"
           nowrap="false">
        <thead>
        <tr>
            <th field="id" width="100">ID</th>
            <th field="ybjsh" width="100">医保结算号</th>
            <th field="zyh" width="100">住院号</th>
            <th field="cfh" width="100">处方号</th>
            <th field="cfnxh" width="100">处方内序号</th>
            <th field="yyxmbm" width="100">医院项目编号</th>
            <th field="yyxmmc" width="100">医院项目名称</th>
            <th field="ybxmbm" width="100">对应医保项目编码</th>
            <th field="xmgg" width="100">项目规格</th>
            <th field="xmdw" width="100">项目单位</th>
            <th field="xmjx" width="100">项目剂型</th>
            <th field="xmdj" width="100">项目单价</th>
            <th field="xmsl" width="100">项目数量</th>
            <th field="xmje" width="100">项目金额</th>
            <th field="hjrq" width="100">划价日期</th>
            <th field="kdysxm" width="100">开单医生姓名</th>
            <th field="zxks" width="100">取药窗口/执行科室</th>
            <th field="sfybxm" width="100">是否医保项目</th>
            <th field="mcyl" width="100">每次用量</th>
            <th field="sypc" width="100">使用频次</th>
            <th field="yf" width="100">用法</th>
            <th field="zxts" width="100">执行天数</th>
            <th field="isupdate" width="100">是否更新库存</th>
            <th field="barcode" width="100">药品条码</th>
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
                        <input name="ybjsh" class="easyui-textbox" label="医保结算号:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="zyh" class="easyui-textbox" label="住院号:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="cfh" class="easyui-textbox" label="处方号:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="cfnxh" class="easyui-textbox" label="处方内序号:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="yyxmbm" class="easyui-textbox" label="医院项目编号:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="yyxmmc" class="easyui-textbox" label="医院项目名称:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="ybxmbm" class="easyui-textbox" label="对应医保项目编码:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="xmgg" class="easyui-textbox" label="项目规格:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="xmdw" class="easyui-textbox" label="项目单位:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="xmjx" class="easyui-textbox" label="项目剂型:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="xmdj" class="easyui-textbox" label="项目单价:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="xmsl" class="easyui-textbox" label="项目数量:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="xmje" class="easyui-textbox" label="项目金额:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="hjrq" class="easyui-textbox" label="划价日期:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="kdysxm" class="easyui-textbox" label="开单医生姓名:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="zxks" class="easyui-textbox" label="取药窗口/执行科室:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="sfybxm" class="easyui-textbox" label="是否医保项目:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="mcyl" class="easyui-textbox" label="每次用量:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="sypc" class="easyui-textbox" label="使用频次:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="yf" class="easyui-textbox" label="用法:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="zxts" class="easyui-textbox" label="执行天数:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="isupdate" class="easyui-textbox" label="是否更新库存:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="barcode" class="easyui-textbox" label="药品条码:" style="width:100%">
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
            url: __path + "/ybjsmxxxJz/listForPage",
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
        url = __path + '/ybjsmxxxJz/add';
    }
    function editObject() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('center');
            $('#fm').form('load', row);
            url = __path + '/ybjsmxxxJz/edit?id=' + row.id;
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
                    $.post('/ybjsmxxxJz/delete', {ids: row.id}, function (result) {
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
