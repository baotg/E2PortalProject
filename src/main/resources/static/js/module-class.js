//$(document).ready(function () {
//    $("#btnSubmitModuleClass").click(function (event) {
//        event.preventDefault();
//        fire_ajax_submit_module_class();
//    });
//});
function doImportModuleClass() {
    event.preventDefault();
    fire_ajax_submit_module_class();
}

function fire_ajax_submit_module_class() {
    var file = $("#file-module-class").val();
    var form = $('#import-module-class')[0];
    var data = new FormData(form);
    if (file == '') {
        var fileNotChosen = " *Chưa chọn tệp để tải lên";
        $("#fileNotChosenModuleClass").html(fileNotChosen);
        return false;
    }
    $("#fileNotChosenModuleClass").html('');
    Metro.dialog.close('#module-class-dialog');
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/upload",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function(data) {
            if (data === 'notMatch') {
                console.log('nomatch');
                var fileNotMatch = " *Tệp tin tải lên sai định dạng, vui lòng thử lại!";
                $("#fileNotChosenModuleClass").html(fileNotMatch);
                Metro.dialog.open('#module-class-dialog');
                return false;
            }
            if (data === 'successful') {
                $.ajax({
                    type: "GET",
                    url: '/moduleclass/import',
                    dataType: "html",
                    success: function(data) {
                        Metro.dialog.close('#loading-dialog');
                        if (data === 'notMatch') {
                            var fileNotMatch = " *Tệp tin tải lên sai định dạng, vui lòng thử lại!";
                            $("#fileNotChosenModuleClass").html(fileNotMatch);
                            Metro.dialog.open('#module-class-dialog');
                            return false;
                        }
                        $('#module-class-preview-dialog-content').html(data.toString());
                        Metro.dialog.open('#module-class-preview-dialog');
                        //	getStudentPreviewDialog('/student/import');
                    },
                    error: function() {
                        Metro.dialog.close('#loading-dialog');
                        alert('Đã có lỗi xảy ra, vui lòng thử lại!');
                    }
                });
                //retrieveGuests('/moduleclass/import');
            }
        },
        error: function(e) {

            alert('Tải lên không thành công!');
        }
    });
    $('#import-module-class')[0].reset();

    function retrieveGuests(url) {
        $("#content-wrapper").load(url);
    }

}

function getModuleClasses() {
    var faculty = document.getElementById("faculty-select");
    var facultyId = faculty.options[faculty.selectedIndex].value;
    if (facultyId == 'empty') {
        var url = '/moduleclass/search?id=';
        $('#module-class-table').load(url);
        return;
    }
    var url = '/moduleclass/search?id=' + facultyId;
    $('#module-class-table').load(url);
    //callAjaxGetWithLoading('#module-class-table',url);
}

function viewModuleClass(id) {
    var url = '/moduleclass/' + id.replace('mdc', '');
    url += '?ajax=true';
    $("#content-wrapper").load(url);
}

function deleteModuleClass(id) {
    Metro.dialog.create({
        title: "Xóa lớp học phần",
        content: "<div>Các thông tin liên quan như: Kết quả học tập, và thông tin của sinh viên về lớp học phần đều sẽ bị xóa.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [{
                caption: "Xóa",
                cls: "js-dialog-close alert",
                onclick: function() {
                    var url = 'moduleclass/delete?id=' + id.replace('dlt', '');
                    $("#module-class-table").load(url);
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