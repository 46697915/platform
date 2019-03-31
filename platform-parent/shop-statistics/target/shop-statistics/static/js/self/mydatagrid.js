$.parser.plugins.push("mydatagrid");//注册扩展组件
$.fn.mydatagrid = function (options, param) {//定义扩展组件

    //当options为字符串时，说明执行的是该插件的方法。
    if (typeof options == "string") {
        // alert1(options);
        return $.fn.datagrid.apply(this, arguments);
    }
    options = options || { };

    if(options.toolbar){
        alert(options);
        alert(options.toolbar);
        options.toolbar = $.extend(
            [
                    {
                        text: '新增',
                        iconCls: 'icon-add',
                        handler: showAdd
                    },
                    {text: '<input id="searchbox" label="名称:" class="easyui-textbox" >'
                    }
            ]
            ,
            options.toolbar
        )
    }

    //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
    return this.each(function () {
        var jq = $(this);

        //$.fn.datagrid.parseOptions(this)作用是获取页面中的data-options中的配置
        var opts = $.extend(
            {pagination: true},
            $.fn.datagrid.parseOptions(this),
            options
        );

        //把配置对象myopts放到$.fn.combobox这个函数中执行。
        //第一个参数 可选。
        // Boolean类型 指示是否深度合并对象，默认为false。
        // 如果该值为true，且多个对象的某个同名属性也都是对象，则该"属性对象"的属性也将进行合并。
        var myopts = $.extend(
            true,
            {
            },
            opts);
        $.fn.datagrid.call(jq, myopts);
    });
};