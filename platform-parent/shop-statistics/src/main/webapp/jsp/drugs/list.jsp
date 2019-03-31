<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/jspcommon/easyui_core.jsp"%>
    <title>列表</title>

</head>
<body class="easyui-layout" fit="true">

<div region="center" border="false" style="overflow: hidden;">
    <table id="dg" class="easyui-datagrid"
           toolbar="#toolbar" pagination="true"
           rownumbers="true" fitColumns="true"
           singleSelect="true"
           striped="true"
           fit="true"
           nowrap="false">
        <thead>
        <tr>
            <th field="id" width="100">ID</th>
            <th field="barcode" width="100">药品条码</th>
            <th field="drugsname" width="100">药品名称</th>
            <th field="commonname" width="100">通用名</th>
            <th field="type1" width="100">所属类别1</th>
            <th field="type2" width="100">所属类别2</th>
            <th field="type3" width="100">所属类别3</th>
            <th field="dosageform" width="100">药品剂型</th>
            <th field="specifications" width="100">规格</th>
            <th field="units" width="100">单位</th>
            <th field="manufactor" width="100">生成厂家</th>
            <th field="approvalnum" width="100">批准文号</th>
            <th field="drugscode" width="100">内部编码</th>
            <th field="commonshotspell" width="100">通用名拼音简码</th>
            <th field="commonnamespell" width="100">通用名拼音码</th>
            <th field="operator" width="100">操作人</th>
            <th field="operatedate" width="100">操作日期</th>
            <th field="bxdygx" width="100">医保对应关系</th>
            <th field="barcode2" width="100">药品条码2真正条形码</th>
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
                        <input name="barcode" class="easyui-textbox" label="药品条码:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="drugsname" class="easyui-textbox" label="药品名称:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="commonname" class="easyui-textbox" label="通用名:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="type1" class="easyui-textbox" label="所属类别1:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="type2" class="easyui-textbox" label="所属类别2:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="type3" class="easyui-textbox" label="所属类别3:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="dosageform" class="easyui-textbox" label="药品剂型:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="specifications" class="easyui-textbox" label="规格:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="units" class="easyui-textbox" label="单位:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="manufactor" class="easyui-textbox" label="生成厂家:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="approvalnum" class="easyui-textbox" label="批准文号:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="drugscode" class="easyui-textbox" label="内部编码:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="commonshotspell" class="easyui-textbox" label="通用名拼音简码:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="commonnamespell" class="easyui-textbox" label="通用名拼音码:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="operator" class="easyui-textbox" label="操作人:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="bxdygx" class="easyui-textbox" label="医保对应关系:" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="barcode2" class="easyui-textbox" label="药品条码2真正条形码:" style="width:100%">
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
        })
//        $('#dg').datagrid('load',obj);

        $('#dg').datagrid('load', {
            name: $('#searchName').val()
        });

    }
    function addObject() {
        $('#dlg').dialog('open').dialog('center');
        $('#fm').form('clear');
        url = '/drugs/add';
    }
    function editObject() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('center');
            $('#fm').form('load', row);
            url = '/drugs/edit?id=' + row.id;
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
                    $.post('/drugs/deleteBatch', {ids: row.id}, function (result) {
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
