$(function () {
    var $inputTip = $('#inputTip'), $input = $('#name');
    var $dptTipPanel = $('#dptTipPanel');
    var $dptNameExist = $('#dptNameExist'), $repeatCancel = $('#repeatCancel'), $repeatRelate = $('#repeatRelate');
    var $ok = $('#ok');

    $dptTipPanel.hide();
    $repeatCancel.on('click', function () {
        $dptNameExist.addClass('hidden');
        $ok.removeClass('disabled'); //按钮恢复可用
        $input.removeAttr('disabled');
    });
    $repeatRelate.on('click', function () {
        $.ajax({
            url: '',
            type: '',
            data: {},
            success: function () {

            },
            error: function () {

            }
        });
    });

    // 输入名称事件
    $input.on('input', function () {
        $.ajax({
            url: '/orgDepartment/queryDepartmentNames.do',
            type: 'GET', // 查数据用get
            data: {
                name: $input.val()
            },
            success: function (names) {
                $inputTip.children().remove();
                if (names.length == 0) {
                    $dptTipPanel.hide();
                    return;
                }
                $dptTipPanel.show();
                for (var i in names) {
                    var $li = $('<a>').addClass('list-group-item').on('click', function () {
                        $input.val($(this).text());
                        $dptTipPanel.hide();
                    });
                    $inputTip.append($li.text(names[i]));
                }
            },
            error: function () {
            },
            dataType: 'json'
        });
    });

    // 确定按钮事件
    $ok.on('click', function () {
        $ok.addClass('disabled');  //按钮失效
        $.ajax({
            url: '/orgDepartment/getDepartmentByName.do',
            type: 'GET',
            data: {
                deptName: $('#name').val()
            },
            success: function (has) {
                if (has == 1) {
                    $input.attr('disabled', 'disabled');
                    //数据库存在这个部门号，提示
                    $dptNameExist.removeClass('hidden');
                } else if (has == 0) {
                    // 数据库不存在这个部门号，保存
                    $.ajax({
                        url: '/orgDepartment/saveDepartment.do',
                        type: 'POST', // 添加数据用post
                        data: {
                            deptName: $('#name').val()
                        },
                        success: function (state) {
                            $ok.removeClass('disabled'); //按钮恢复可用
                            $('#dptSaved').removeClass('hidden');
                            if (state == 'success') {
                            } else if (state == 'failure') {
                                alert('添加出现错误');
                            }
                        },
                        error: function (d) {
                            alert('error');
                        },
                        dataType: 'text'
                    });
                }
            },
            error: function () {
                console.log('服务器错误');
            }
        });

    });

    // 取消按钮事件
    $('#cancel').on('click', function () {
    });

});
