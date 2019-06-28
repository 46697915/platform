$(document).ready(function () {

    moveToMove();   //左右一定获取数据信息进行数据的保存
});

//////////////////////////////////////////////////////////////////////
//数据左右移动方法
//////////////////////////////////////////////////////////////////////
function moveToMove() {

    //移到右边
    $('#add').click(function () {

        var cbxBool = 0;
        //获取选中的选项，删除并追加给对方
        for (var i = 0; i < $("#select1 option").length; i++) {
            if ($("#select1 option")[i].selected) {
                cbxBool = 1;
                break;
            }
        }
        if (cbxBool == 0) {
            alert("没有选中项");
            return false;
        }
        $('#select1 option:selected').appendTo('#select2');
    });
    //全部移到右边
    $('#add_all').click(function () {
        //获取全部的选项,删除并追加给对方
        $('#select1 option').appendTo('#select2');
    });

    //移到左边
    $('#remove').click(function () {
        var cbxBool = 0;
        //获取选中的选项，删除并追加给对方
        for (var i = 0; i < $("#select2 option").length; i++) {
            if ($("#select2 option")[i].selected) {
                cbxBool = 1;
                break;
            }
        }
        if (cbxBool == 0) {
            alert("没有选中项");
            return false;
        }
        $('#select2 option:selected').appendTo('#select1');
    });
    //全部移到左边
    $('#remove_all').click(function () {
        $('#select2 option').appendTo('#select1');
    });

    //双击选项
    $('#select1').dblclick(function () { //绑定双击事件
        //获取全部的选项,删除并追加给对方
        $("option:selected", this).appendTo('#select2'); //追加给对方
    });

    //双击选项
    $('#select2').dblclick(function () {
        $("option:selected", this).appendTo('#select1');
    });

}