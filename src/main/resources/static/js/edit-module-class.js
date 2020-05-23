function saveModuleClass() {
    event.preventDefault();
    $("#errModuleClassName").html('');
    $("#errModuleClassCredit").html('');
    $("#errModuleClassTSession").html('');
    $("#errModuleClassPSession").html('');
    $("#errModuleSemester").html('');
    $("#errModuleStart").html('');
    $("#errModuleEnd").html('');

    var moduleClassName = $("#moduleClassName").val();
    var moduleClassCredit = $("#moduleClassCredit").val();
    var moduleClassTsession = $("#moduleClassTsession").val();
    var moduleClassPsession = $("#moduleClassPsession").val();
    var moduleClassSemester = $("#moduleClassSemester").val();
    var moduleClassStart = $("#moduleClassStart").val();
    var moduleClassEnd = $("#moduleClassEnd").val();

    if (moduleClassName == '' || moduleClassCredit == '' || moduleClassTsession == '' || moduleClassPsession == '' || moduleClassSemester == '' || moduleClassStart == '' || moduleClassEnd == '') {
        if (moduleClassName == '') {
            $('#errModuleClassName').html('*Thông tin này không được để trống!');

        }
        if (moduleClassCredit == '') {
            $('#errModuleClassCredit').html('*Thông tin này không được để trống!');

        }
        if (moduleClassTsession == '') {
            $('#errModuleClassTSession').html('*Thông tin này không được để trống!');

        }
        if (moduleClassPsession == '') {
            $('#errModuleClassPSession').html('*Thông tin này không được để trống!');

        }
        if (moduleClassSemester == '') {
            $('#errModuleSemester').html('*Thông tin này không được để trống!');
        }
        if (moduleClassStart == '') {
            $('#errModuleStart').html('*Thông tin này không được để trống!');

        }
        if (errModuleEnd == '') {
            $('#errModuleEnd').html('*Thông tin này không được để trống!');
        }

        return false;
    }
    Metro.dialog.close('#module-class-edit-dialog');
    fire_ajax_submit_module_class_edit();
}

function fire_ajax_submit_module_class_edit() {
    var form = $('#edit-module-class-form')[0];
    var data = new FormData(form);

    $.ajax({
        type: "POST",
        url: "/moduleclass/save",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function(data) {

            $("#module-class-table").html(data.toString());


        },
        error: function(e) {
            Metro.dialog.close('#module-class-edit-dialog');
            alert('Lưu không thành công!');
        }
    });
    $('#edit-module-class-form')[0].reset();

}

function cancelEdtingModuleClass(url) {
    Metro.dialog.create({
        title: "Hủy bỏ",
        content: "<div>Nội dung hiện tại sẽ không được lưu lại.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [{
                caption: "Hủy bỏ",
                cls: "js-dialog-close alert",
                onclick: function() {
                    Metro.dialog.close('#module-class-edit-dialog');
                }
            },
            {
                caption: "Hủy",
                cls: "js-dialog-close",
                onclick: function() {

                }
            }
        ]
    });
}

function getModuleClassEditDialog(id) {
    var url = '/moduleclass/edit?id=' + id.replace('edt', '');
    $("#module-class-edit-dialog-content").load(url);
    Metro.dialog.open('#module-class-edit-dialog');
}