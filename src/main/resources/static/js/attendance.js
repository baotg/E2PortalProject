// $(document).ready(function () {
//     $("#btnSubmitAttendance").click(function (event) {
//         event.preventDefault();
//         fire_ajax_submit_attendance();
//     });
// });
function doImportAttendance() {
    event.preventDefault();
    fire_ajax_submit_attendance();
}

function deleteAttendance(id) {
    Metro.dialog.create({
        title: "Xóa điểm danh",
        content: "<div>Thông tin vừa chọn sẽ được xóa khỏi hệ thống.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [{
                caption: "Xóa",
                cls: "js-dialog-close alert",
                onclick: function() {
                    var url = 'attendance/delete?id=' + id.replace('dlt', '');
                    $("#attendance-table").load(url);
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

function fire_ajax_submit_attendance() {
    var file = $("#file-attendance").val();
    var form = $('#import-attendance')[0];
    var data = new FormData(form);
    if (file == '') {
        var fileNotChosen = " *Chưa chọn tệp để tải lên";
        $("#fileNotChosenAnnouncement").html(fileNotChosen);
        return false;
    }
    $("#fileNotChosenAnnouncement").html('');
    Metro.dialog.close('#attendance-dialog');
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
                $("#fileNotChosenAnnouncement").html(fileNotMatch);
                Metro.dialog.open('#attendance-dialog');
                return false;
            }
            if (data === 'successful') {
                $.ajax({
                    type: "GET",
                    url: '/attendance/import',
                    dataType: "html",
                    success: function(data) {
                        Metro.dialog.close('#loading-dialog');
                        if (data === 'notMatch') {
                            var fileNotMatch = " *Tệp tin tải lên sai định dạng, vui lòng thử lại!";
                            $("#fileNotChosenAnnouncement").html(fileNotMatch);
                            Metro.dialog.open('#attendance-dialog');
                            return false;
                        }
                        $('#attendance-preview-dialog-content').html(data.toString());
                        Metro.dialog.open('#attendance-preview-dialog');
                        //	getStudentPreviewDialog('/student/import');
                    },
                    error: function() {
                        Metro.dialog.close('#loading-dialog');
                        alert('Đã có lỗi xảy ra, vui lòng thử lại!');
                    }
                });
            }
            //	retrieveGuests('/attendance/import');
        },
        error: function(e) {

            alert('Tải lên không thành công!');
        }
    });
    $('#import-attendance')[0].reset();

    function retrieveGuests(url) {
        $("#content-wrapper").load(url);
    }

}

function getClassesAttendance() {
    var faculty = document.getElementById("faculty-select");
    var aClass = document.getElementById("class-select");
    var urlnone = '/attendance/search/class?id=';
    var facultyId = faculty.options[faculty.selectedIndex].value;
    aClass = '';
    if (facultyId == 'empty') {
        $('#attendance-table').load(urlnone);
        $('#select-classes').load('/attendance/search?id=');
        return;
    }
    var url = '/attendance/search?id=' + facultyId;
    $('#select-classes').load(url);
    $('#attendance-table').load(urlnone);
}

function getAttendanes() {
    var aClass = document.getElementById("class-select");
    var classId = aClass.options[aClass.selectedIndex].value;
    if (classId == 'empty') {
        classId = '';
    }
    var url2 = '/attendance/search/class?id=' + classId;
    $('#attendance-table').load(url2);
}