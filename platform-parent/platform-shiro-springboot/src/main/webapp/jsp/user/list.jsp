<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/jspcommon/easyui_core.jsp"%>
    <title>列表</title>

</head>
<body class="easyui-layout" fit="true">

<div region="center" border="false" style="overflow: hidden;">
    <table id="dg" class="easyui-datagrid"
           url="${pageContext.request.contextPath}/user/listForPage"
           toolbar="#toolbar" pagination="true"
           rownumbers="true" fitColumns="true"
           singleSelect="true"
           striped="true"
           fit="true"
           nowrap="false">
        <thead>
        <tr>
            <th field="id" width="100"></th>
            <th field="organizationId" width="100"></th>
            <th field="username" width="100"></th>
            <th field="password" width="100"></th>
            <th field="salt" width="100"></th>
            <th field="roleIds" width="100"></th>
            <th field="locked" width="100"></th>
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
                        <input name="id" class="easyui-textbox" label=":" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="organizationId" class="easyui-textbox" label=":" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="username" class="easyui-textbox" label=":" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="password" class="easyui-textbox" label=":" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="salt" class="easyui-textbox" label=":" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="roleIds" class="easyui-textbox" label=":" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="locked" class="easyui-textbox" label=":" style="width:100%">
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
            url: __path + "/user/listForPage",
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
        url = __path + '/user/add';
    }
    function editObject() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('center');
            $('#fm').form('load', row);
            url = __path + '/user/edit?id=' + row.id;
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
                    $.post('/user/delete', {ids: row.id}, function (result) {
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
