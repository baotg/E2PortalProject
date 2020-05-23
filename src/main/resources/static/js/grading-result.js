// $(document).ready(function () {
//     $("#btnSubmitGradingResult").click(function (event) {
//         event.preventDefault();
//         fire_ajax_submit_grading_result();
//     });
// });
function doImportGradingResult() {
    event.preventDefault();
    fire_ajax_submit_grading_result();
}

function deleteGradingResult(el) {
    Metro.dialog.create({
        title: "Xóa kết quả học tập",
        content: "<div>Thông tin vừa chọn sẽ bị xóa.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [{
                caption: "Xóa",
                cls: "js-dialog-close alert",
                onclick: function() {
                    var url = 'gradingresult/delete?studentId=' + el.dataset.std + '&moduleClassId=' + el.dataset.mdc;
                    $("#grading-result-table").load(url);
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

function fire_ajax_submit_grading_result() {
    var file = $("#file-grading-result").val();
    var form = $('#import-grading-result')[0];
    var data = new FormData(form);
    if (file == '') {
        var fileNotChosen = " *Chưa chọn tệp để tải lên";
        $("#fileNotChosenGradingResult").html(fileNotChosen);
        return false;
    }
    $("#fileNotChosenGradingResult").html('');
    Metro.dialog.close('#grading-result-dialog');
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
                $("#fileNotChosenGradingResult").html(fileNotMatch);
                Metro.dialog.open('#grading-result-dialog');
                return false;
            }
            if (data === 'successful') {
                $.ajax({
                    type: "GET",
                    url: '/gradingresult/import',
                    dataType: "html",
                    success: function(data) {
                        Metro.dialog.close('#loading-dialog');
                        if (data === 'notMatch') {
                            var fileNotMatch = " *Tệp tin tải lên sai định dạng, vui lòng thử lại!";
                            $("#fileNotChosenGradingResult").html(fileNotMatch);
                            Metro.dialog.open('#grading-result-dialog');
                            return false;
                        }
                        $('#grading-result-preview-dialog-content').html(data.toString());
                        Metro.dialog.open('#grading-result-preview-dialog');
                    },
                    error: function() {
                        Metro.dialog.close('#loading-dialog');
                        alert('Đã có lỗi xảy ra, vui lòng thử lại!');
                    }
                });
            }
        },
        error: function(e) {

            alert('Tải lên không thành công!');
        }
    });
    $('#import-grading-result')[0].reset();

    function retrieveGuests(url) {
        $("#content-wrapper").load(url);
    }

}

function getClassesGradingResult() {

    var faculty = document.getElementById("faculty-select");
    var aClass = document.getElementById("class-select");
    var urlnone = '/gradingresult/search/class?id=';
    var facultyId = faculty.options[faculty.selectedIndex].value;
    aClass = '';
    if (facultyId == 'empty') {
        $('#grading-result-table').load(urlnone);
        $('#select-classes').load('/gradingresult/search?id=');
        return;
    }
    var url = '/gradingresult/search?id=' + facultyId;
    $('#select-classes').load(url);
    $('#grading-result-table').load(urlnone);
}

function getGradingResult() {

    var aClass = document.getElementById("class-select");
    var classId = aClass.options[aClass.selectedIndex].value;
    if (classId == 'empty') {
        classId = '';
    }
    var url2 = '/gradingresult/search/class?id=' + classId;
    $('#grading-result-table').load(url2);
}