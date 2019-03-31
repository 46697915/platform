<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/11/13
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/jspcommon/easyui_core.jsp"%>
    <title>列表</title>
</head>
<body class="easyui-layout" fit="true">

<div region="center" border="false" style="overflow: hidden;">
    <table id="dg" title="药品列表" class="easyui-datagrid"
           toolbar="#toolbar" pagination="true"
           rownumbers="true" fitColumns="true"
           singleSelect="true"
           striped="true"
           fit="true"
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
            -->
        </tr>
        </thead>
    </table>
    <div id="toolbar">
        <form id="searchfm" method="post">
            <table>
                <tr>
                    <td>
                        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                           onclick="editUser()">编辑</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                           onclick="destroyUser()">删除</a>
                    </td>
                    <td>
                        <input id="searchName" name="name" class="easyui-textbox" data-options="prompt:'查询药名'">
                    </td>
                    <td>
                        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <div id="dlg" class="easyui-dialog" style="width:400px"
         data-options="closed:true,modal:true,border:'thin',
         title: '添加/修改',
         buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <table>
                <tr>
                    <td>
                        <input name="username" class="easyui-textbox" required="true" label="名称:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="password" class="easyui-textbox" required="true" label="密码:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="remark" class="easyui-textbox" required="true" label="备注:" style="width:100%">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()"
           style="width:90px">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
           onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
    </div>
</div>
<script type="text/javascript">
    var url;
    //页面加载初始化
    $(function () {
        $('#dg').datagrid({
            url: __path + "/drugs/listForPage",
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
            alert(v.name+':'+v.value);
        })

//        $('#dg').datagrid('load',obj);

        $('#dg').datagrid('load', {
            name: $('#searchName').val()
        });

    }
    function newUser() {
        $('#dlg').dialog('open').dialog('center');
        $('#fm').form('clear');
        url = '/user/add';
    }
    function editUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('center');
            $('#fm').form('load', row);
            url = '/user/edit?id=' + row.id;
        }
    }
    function saveUser() {
        $('#fm').form('submit', {
            url: url,
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('(' + result + ')');
                if (result.errorMsg) {
                    $.messager.show({
                        title: 'Error',
                        msg: result.errorMsg
                    });
                } else {
                    $('#dlg').dialog('close');        // close the dialog
                    $('#dg').datagrid('reload');    // reload the user data
                }
            }
        });
    }
    function destroyUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Confirm', '你确定删除这条记录吗?', function (r) {
                if (r) {
                    $.post('/user/delete', {ids: row.id}, function (result) {
                        if (result.success) {
                            $('#dg').datagrid('reload');    // reload the user data
                        } else {
                            $.messager.show({    // show error message
                                title: 'Error',
                                msg: result.errorMsg
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
