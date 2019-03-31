function x($) {
    var extendFns = {};
    /**
     * 注入函数
     * fnName：函数名称，必填；
     * fn：函数实体，必填；
     * isGlobal：函数作用域，选填，默认是局部变量，如果需要全局访问可以设置成true；
     * 成功添加返回true，失败添加返回false
     * 2016-4-16进行更新：

     * !! 此处是项目中大组件的具体ID,可以用this.selector代替作为当前对象的方法
     *
     * 现在使用的是该头部选项卡tabHeaderCollection下的正在访问的页面选项id作为选项;
     * 注意：如果是用动态数据作为id，请不要用局部方法，否则会出现内存累积问题；
     * 注意：注册方法时推荐使用$().injectFn形式进行注册，这样会省下查找元素的时间，当然即使查找元素也不需要过多时间，所以这项只是推荐
     * */
    $.fn.injectFn = function (fnName, fn, isGlobal) {
        //这里必须需要两个参数以上才能添加成功
        if (arguments.length > 1) {
            //函数名称必须是字符串
            if (!(fnName && $.type(fnName) === "string")) {
                //$.messager.alert("Error","The function name is  required!");
                return false;
            }

            //第二个参数必须是函数实体
            if (!$.isFunction(fn)) {
                //$.messager.alert("Error","The function is  error!");
                return false;
            }

            //是否是全局函数，如果是，无论在哪里都可以访问，局部变量，作用范围为当前头部大选项卡范围内。
            if (isGlobal === true) {
                if (extendFns.global) {
                    extendFns.global[fnName] = fn;
                }
                else {
                    extendFns.global = {};
                    extendFns.global[fnName] = fn;
                }
            }
            else {
                var selectTab=$("#tabHeaderCollection>p.selected").get(0);
                //此处可以用this.selector代替，相应的setDatagrid也用this.selector
                if(selectTab){
                    var selector=selectTab.id+"<==>";
                }
                else
                {
                    return false;
                }

                var obj = extendFns[selector];
                if (obj) {
                    obj[fnName] = fn;
                }
                else {
                    obj = extendFns[selector] = {};
                    obj[fnName] = fn;
                }
            }

            return true;
        }
        return false;
    };

    //grid使用函数，可以直接调用注入的函数，也可以对grid进行初始化，也可以返回相对应的函数。
    //option:有三种类型的值：1.字符串：返回立即执行函数执行后的结果；2.数组：对grid进行初始化，最简单的一种grid使用方式；3.对象：可以是对grid进行初始化，也可以是获取需要的执行函数。
    /*---当options为对象---
     *@columns datagrid 列属性
     *@toolbar 包括title、search、addRow，或者直接是数组，传递给datagrid属性
     *@pagination  pagination相关数据
     *@handlers 可以是boolean值，也可以是包含handlers的方法名的对象，取值为true
     * */
    $.fn.setDatagrid = function (option) {
        //默认grid 对象，可以进行扩展修改
        var defaultOption = {
            showHeader: true,
            toolbar: [],
            idField: "Id",
            columns: [],
            striped: true,
            autoRowHeight: false,
            remoteSort: false,
            rownumbers: false,
            pagination: false,
            loadMsg: "Loading......",
            width: '100%',
            height: '100%',
            singleSelect: true
        };

        var self = this;

        //默认的toolbar 无法修改，只能调用
        var toolbar = {
            //标题相关
            title: {
                text: 'Severity Scale',
                handler: function () {
                    return {text: '<b class="NoBtnStyle title left">' + toolbar.title.text + '</b>'}

                }
            },
            search: {
                handler: function () {
                },
                prompt: 'input keywords'
            },
            addRow: {
                text: 'Severity Scale',
                handler: function () {
                    var selectedRow = $(self).datagrid('getSelected');
                    var selectedIndex = $(self).datagrid('getRowIndex', selectedRow);
                    //所选位置下方添加一条空记录，如没有选择，则在第一条增加
                    $(self).datagrid('insertRow', {
                        index: selectedIndex + 1,
                        row: {isNewRecord: true}
                    });
                    $(self).datagrid('beginEdit', selectedIndex + 1);
                }
            }
        };


        //pagination相关数据，可以进行修改，也可以不调用
        var pagination = {
            pageList: [20, 40, 60, 80],
            pageSize: 20,
            pageNumber: 1,
            layout: ['list', 'sep', 'first', 'prev', 'links', 'next', 'last', 'sep', 'refresh'],
            displayMsg: 'Current {from} - {to} Records Total {total} Records',
            onSelectPage: function (pageNumber, pageSize) {
                //datagird 如果有pagination,需重写此方法
            }
        };

        //row handler 内部常用函数，使用比较高的函数
        var rowHandler = {
            /**
             * @param newRecord 是否为新增row
             * @param index  index of row
             * @param options  ajax请求对象包括子对象newRequest and updateRequest，子对象url和data是必填的
             * @param rowMsg  row数据存在时候的提示信息
             * */
            saveRow: function (newRecord, index, options, rowMsg) {
                //新增row
                if (newRecord) {
                    //新增相关数据
                    var newRequest = {
                        url: '',
                        type: 'POST',
                        async: true,
                        data: {},
                        success: function (rep, textStatus, jqXHR) {
                            if (rep.ResponseCode === 100) {
                                rep.ResponseData.isNewRecord = false;
                                $(self).datagrid('updateRow', {
                                    index: index,
                                    row: rep.ResponseData
                                });
                                $.NotifyMsg("Success", rep.ResponseMsg);
                            }else{
                                $(self).datagrid('deleteRow', index);
                                $.NotifyError("Error", rep.ResponseMsg);
                            }
                        },
                        error: function (rep) {
                            $(self).datagrid('beginEdit', index);
                            $.NotifyError("Error", 'service loaded error');
                        },
                        complete: $.noop
                    };
                    $.extend(newRequest, options.newRequest);
                    $.SendRequest(newRequest.url, newRequest.data, newRequest.type, newRequest.complete, newRequest.error, newRequest.success, newRequest.async);
                }
                //更新row
                else {
                    var updateRequest = {
                        url: '',
                        type: 'PUT',
                        async: true,
                        data: {},
                        success: function (rep, textStatus, jqXHR) {
                            if (rep.ResponseCode == 300) {
                                $(self).datagrid('updateRow', {
                                    index: index,
                                    row: rep.ResponseData
                                });
                                $.NotifyMsg("Success", rep.ResponseMsg);
                                $(self).datagrid('refreshRow', index);
                            } else if (rep.ResponseCode == -300 || -301) {
                                //装载原来的数据
                                $(self).datagrid('updateRow', {
                                    index: index,
                                    row: rep.ResponseData
                                });
                                $(self).datagrid('beginEdit', index);
                                $.NotifyError(SMART.msg.isExist.title, SMART.msg.isExist.content(rowMsg));
                            }
                            //出错时候处理
                            else {
                                $.NotifyError("Error", rep.ResponseMsg);
                                $(self).datagrid('deleteRow', index);
                            }
                        },
                        error: function (rep) {
                            $(self).datagrid('beginEdit', index);
                            $.NotifyError("Error", 'service loaded error');
                        },
                        complete: $.noop
                    };
                    $.extend(updateRequest, options.updateRequest);
                    $.SendRequest(updateRequest.url, updateRequest.data, updateRequest.type, updateRequest.complete, updateRequest.error, updateRequest.success, updateRequest.async);
                }
            },
            cancelRow: function (target) {
                var index = window.Ext_EasyUI.DataGrid.getRowIndex(target);
                var pagerows = $(this.selector).datagrid('getRows');
                var row = pagerows[index];
                $(self).datagrid('cancelEdit', index);
            },
            editRow: function (target) {
                var index = window.Ext_EasyUI.DataGrid.getRowIndex(target);
                $(self).datagrid('selectRow', index);
                $(self).datagrid('beginEdit', index);
            },
            deleteRow: function (target, url, id, msg, queryParam) {
                var index = window.Ext_EasyUI.DataGrid.getRowIndex(target);
                // 获取当前页数据
                var pagerows = $(self).datagrid('getRows');
                // 获取当前操作行
                var row = pagerows[index];
                var rowID = row[id];
                var params = queryParam ||'';
                $.messager.confirm(SMART.msg.remove.title, 'Please confirm deletion of ' + msg + ' . This operation cannot be undone.', function (r) {
                    if (r) {
                        $.SendRequest(url + rowID, params, "Delete", $.noop, function (rep, textStatus, jqXHR) {
                            $.NotifyError("Error", 'service loaded error');
                        }, function (rep, textStatus, jqXHR) {
                            if (rep.ResponseCode === 200) {
                                $(self).datagrid('deleteRow', index);
                            }
                            //出错时候处理
                            else {
                                $.NotifyError("Error", rep.ResponseMsg);
                            }
                        });
                    }
                });
            },
            //选中行上移一位
            upRow: function (target, url) {
                var index = window.Ext_EasyUI.DataGrid.getRowIndex(target),
                    data = $(this.selector).datagrid("getRows");
                var curData = data[index],
                    upData = data[parseInt(index - 1)];
                if (index == 0) {
                    $.NotifyWarning('Warning', 'The row is already at the top.');
                    return false;
                }
                $.SendRequest(url + $.UUID(), {
                    method: 'ExchangeSort',
                    sourceId: curData.Id,
                    sourceSort: curData.Sort,
                    targetId: upData.Id,
                    targetSort: upData.Sort
                }, 'PUT', $.noop, function (rep, textStatus, jqXHR) {
                    $.NotifyError("Error", 'service load error');
                }, function (rep) {
                    if (rep.ResponseCode == 300) {
                        var currentSort = curData.Sort;
                        var upDataSort = upData.Sort;
                        upData.Sort = currentSort; //上=下
                        curData.Sort = upDataSort; //下=上
                        //更新上面一条的数据sort
                        $(self).datagrid('updateRow', {
                            index: index - 1,
                            row: upData
                        }).datagrid('updateRow', {
                            index: index,
                            row: curData
                        });


                        var upIndex = parseInt(index - 1);
                        var delIndex = parseInt(index + 1);
                        $(self).datagrid('insertRow', {
                            index: upIndex,
                            row: curData
                        }).datagrid('deleteRow', delIndex);

                    } else {
                        $.NotifyError("Error", rep.ResponseMsg);
                    }
                });
            },
            //row往下移一位
            downRown: function (target, url) {
                var index = window.Ext_EasyUI.DataGrid.getRowIndex(target);
                var len = $(this.selector).datagrid("getRows").length;
                if (index == len - 1) {
                    $.NotifyWarning('Warning', 'The row is already at the bottom.');
                    return false;
                }
                var data = $(this.selector).datagrid("getRows");
                var curData = data[index],
                    downData = data[parseInt(index + 1)];
                $.SendRequest(url + $.UUID(), {
                    method: 'ExchangeSort',
                    sourceId: curData.Id,
                    sourceSort: curData.Sort,
                    targetId: downData.Id,
                    targetSort: downData.Sort
                }, 'PUT', $.noop, function (rep, textStatus, jqXHR) {
                    $.NotifyError("Error", 'service load error');
                }, function (rep) {
                    if (rep.ResponseCode == 300) {
                        var currentSort = curData.Sort;
                        var downDataSort = downData.Sort;
                        downData.Sort = currentSort; //上=下
                        curData.Sort = downDataSort; //下=上
                        //更新上面一条的数据sort
                        $(self).datagrid('updateRow', {
                            index: index + 1,
                            row: downData
                        }).datagrid('updateRow', {
                            index: index,
                            row: curData
                        });


                        $(self).datagrid('insertRow', {
                            index: index + 2,
                            row: curData
                        }).datagrid('deleteRow', index);
                    } else {
                        $.NotifyError("Error", rep.ResponseMsg);
                    }
                });
            },
            updateActions: function (index) {
                $(self).datagrid('refreshRow', index);
            },
            /**
             * @param options属性 url必须
             * */
            loadPageData: function (options) {
                var requestParam = {
                    url: '',
                    type: 'GET',
                    async: true,
                    data: '',
                    success: function (rep, textStatus, jqXHR) {
                        if (rep.ResponseCode === 400) {
                            var data = rep.ResponseData;
                            $(self).datagrid('loadData', data);
                            $(self).datagrid('loaded');
                            defaultOption.pagination ? $(self).datagrid('getPager').pagination('loaded') : '';
                            $.NotifyMsg("Success", rep.ResponseMsg);
                            // 刷新 分页栏目的基础信息
                            if (defaultOption.pagination) {
                                $(self).datagrid('getPager').pagination('refresh', {
                                    pageNumber: options.data.pageIndex,
                                    pageSize: options.data.pageSize,
                                    total: rep.ResponseTotal || 0
                                });
                            }
                        } else {
                            $(self).datagrid('loaded');
                            defaultOption.pagination ? $(self).datagrid('getPager').pagination('loaded') : '';
                            $.NotifyMsg('Error', rep.ResponseMsg);
                            if (defaultOption.pagination) {
                                $(self).datagrid('getPager').pagination('refresh', {
                                    pageNumber: options.data.pageIndex,
                                    pageSize: options.data.pageSize,
                                    total: rep.ResponseTotal || 0
                                });
                            }
                        }
                    },
                    error: function (rep) {
                        $(self).datagrid('loaded');
                        defaultOption.pagination ? $(self).datagrid('getPager').pagination('loaded') : '';
                        $.NotifyError('Error', 'service loaded error');
                        if (defaultOption.pagination) {
                            $(self).datagrid('getPager').pagination('refresh', {
                                pageNumber: options.data.pageIndex,
                                pageSize: options.data.pageSize,
                                total: 0
                            });
                        }
                    },
                    complete: $.noop
                };

                //extend 参数
                $.extend(requestParam, options);
                if (!options) {
                    $.NotifyWarning('Warning', 'parameters is required.');
                    return false;
                }
                // UI状态 - 表格进入正在读取状态
                $(self).datagrid('loading');
                defaultOption.pagination ? $(self).datagrid('getPager').pagination('loading') : '';

                //发送获取分页数据的请求
                $.SendRequest(requestParam.url, requestParam.data, requestParam.type, requestParam.complete, requestParam.error, requestParam.success, requestParam.async);
            },
        };

        //使用头部选项卡作为局部变量 此处可以用this.selector判断
        var selectTab=$("#tabHeaderCollection>p.selected").get(0);
        if(selectTab){
            var selector=selectTab.id+"<==>";
        }
        else{
            selector="";
        }


        //方法汇集，注入的非全局方法优先级最高，rowHandler其次，全局方法最低。
        var handlers = $.extend({}, extendFns["global"] || {}, rowHandler, extendFns[selector] || {});

        //最简单的用法，就是直接传递一数组【columns data】进来就可以使用.
        if ($.isArray(option)) {
            defaultOption.columns = option;
        }
        //如果是对象，相对来说是比较复杂的
        else if ($.isPlainObject(option)) {
            defaultOption.columns = option.columns;

            /**
             * 工具栏，option.toolbar
             * 不是数组，直接传对象，只能在title，search，addRow上extend
             * 是数组，直接extend datagrid toolbar
             */
            if (!$.isArray(option.toolbar)) {
                if (option.toolbar === true) {
                    delete option.toolbar;
                }
                else if ($.isPlainObject(option.toolbar)) {
                    $.extend(toolbar, option.toolbar);
                    delete option.toolbar
                }
                var gridToolBar = [];
                //add toolbar
                /**
                 * @param options的toolbar的子对象为空对象时，去除该toolbar选项，不增加到toolbar
                 * */
                for (var i in toolbar) {
                    if (i === 'title' && !$.isEmptyObject(toolbar.title)) {
                        gridToolBar.push(toolbar.title.content());
                    }
                    if (i === 'search' && !$.isEmptyObject(toolbar.search)) {
                        gridToolBar.push({text: '<input class="NoBtnStyle" id="searchbox">'});
                    }
                    if (i === 'addRow' && !$.isEmptyObject(toolbar.addRow)) {
                        gridToolBar.push({
                            text: toolbar.addRow.text,
                            iconCls: 'icon-add',
                            handler: toolbar.addRow.handler
                        })
                    }

                }
                defaultOption.toolbar = gridToolBar;
            }
            else if ($.isArray(option.toolbar)) {
                $.extend(defaultOption.toolbar, option.toolbar);
            }

            /**
             * 分页 option.pagination
             * 布尔值，默认变量paginaton
             * obj， extend 变量pagination
             * */
            if (option.pagination === true) {
                defaultOption.pagination = true;
                delete option.pagination;
            }
            else if ($.isPlainObject(option.pagination)) {
                defaultOption.pagination = true;
                $.extend(pagination, option.pagination);
                delete option.pagination;
            }

            //通用事件，需要的时候才会附加返回。
            var handlerFns = {length: 0, selector: self.selector};
            //返回所有函数：设置handlers为true即可，比如{handlers:true}
            if (option.handlers === true) {
                for (var key in handlers) {
                    handlerFns[key] = handlers[key];
                    handlerFns.length++;
                }
                delete option.handlers;
            }
            else if ($.isPlainObject(option.handlers)) {
                for (var key in option.handlers) {
                    //只要设置相对应的函数名称为true就可以返回，比如返回editRow函数，可以这样设置{editRow:true}
                    if (option.handlers[key] === true) {
                        handlerFns[key] = handlers[key];
                        handlerFns.length++;
                    }
                }
                delete option.handlers
            }

            //set null,所有需要用到的函数在handlerFns对象里
            handlers = null;

            //删除不相关的属性后再设置defaultOption
            $.extend(defaultOption, option);
        }
        //字符串形式调用method
        else {
            //直接使用该函数
            if ($.type(option) === "string") {
                var key = arguments[0];
                var handler = handlers[key];
                if (handler) {
                    var arrs = Array.prototype.slice.call(arguments, 1);
                    handler.apply(self, arrs);
                }
            }
            return self;
        }

        //是否是进行函数调用？如果是，不用写columns，就可以直接返回,
        // 注：返回的事件获取方法是，返回对象obj，那么调用函数可以这样获取obj.handler下的所有函数就是你需要的函数。
        if (!$.isArray(defaultOption.columns)) {
            if (handlerFns.length > 0)
                self.handler = handlerFns;
            return self;
        }

        var grid = $(self).datagrid(defaultOption)
            .datagrid({
                onBeforeEdit: function (index, row) {
                    row.editing = true;
                    $(self).datagrid('refreshRow', index);
                },
                onAfterEdit: function (index, row) {
                    row.editing = false;
                    $(self).datagrid('refreshRow', index);
                },
                onCancelEdit: function (index, row) {
                    // 判断是否为新增加数据
                    var newRecord = row.hasOwnProperty('isNewRecord') && row.isNewRecord === true;
                    // 如果说新增数据，取消操作会则删除本行
                    if (newRecord) {
                        $(self).datagrid('deleteRow', index);
                    }
                    else {
                        row.editing = false;
                        $(self).datagrid('refreshRow', index);
                    }
                },
                onLoadSuccess: function(){
                    SMART.UI.columns_align();
                }
            });
        if (defaultOption.pagination) {
            //调用的时候再次datagrid方法会覆盖这次生成的pagination，变成default pagination
            //所以setTimeout调用
            setTimeout(function () {
                $(self).datagrid("getPager").pagination(pagination);
            },50)
        }

        if (handlerFns.length > 0) {
            grid.handler = handlerFns;
        }

        //返回datagird自身，链式
        return grid;
    }

}