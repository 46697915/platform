$.parser.plugins.push("mydialog");//注册扩展组件
$.fn.mydialog = function (options, param) {//定义扩展组件

    //当options为字符串时，说明执行的是该插件的方法。
    if (typeof options == "string") { return $.fn.dialog.apply(this, arguments); }
    options = options || { };

    //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
    return this.each(function () {
        var jq = $(this);

        // alert(JSON.stringify(jq));
        // alert(eval(jq.attr("data-options")));

        function doSave() {
            $.messager.progress();	// 显示进度条
            jq.children("form").form('submit', {
                url: url,
                onSubmit: function () {
                    var isValid = $(this).form('validate');
                    if (!isValid) {
                        $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                    }
                    return isValid;	// 返回false终止表单提交
                },
                success: function (result) {
                    var result = eval('(' + result + ')');
                    $.messager.progress('close');	// 如果提交成功则隐藏进度条
                    if (result.success) {
                        jq.dialog("close");
                        // $("#dg").datagrid("reload");
                        // alert(options.callback);
                        // options.callback ;
                    } else {
                        $.messager.show({
                            title: '我的消息',
                            msg: result.errorMsg,
                            timeout: 5000,
                            showType: 'slide'
                        });
                    }
                }
            });
        }

        function doClose() {
            jq.dialog("close");
        }
        //$.fn.datagrid.parseOptions(this)作用是获取页面中的data-options中的配置
        var opts = $.extend(
            {iconCls:'icon-save',
            resizable:true,
            closed: true,
            buttons: [
                {
                    text: '保存',
                    iconCls: 'icon-add',
                    handler: doSave
                },{
                    text: '取消',
                    iconCls: 'icon-add',
                    handler: doClose
                }
            ],
            modal:true
            },
            $.fn.dialog.parseOptions(this),
            options
        );

        //把配置对象myopts放到$.fn.combobox这个函数中执行。
        //第一个参数 可选。
        // Boolean类型 指示是否深度合并对象，默认为false。
        // 如果该值为true，且多个对象的某个同名属性也都是对象，则该"属性对象"的属性也将进行合并。
        var myopts = $.extend(
            true,
            {},
            opts
        );
        $.fn.dialog.call(jq, myopts);
    });
};