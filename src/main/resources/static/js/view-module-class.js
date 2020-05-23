function deleteStudentFromModuleClass(el) {
    Metro.dialog.create({
        title: "Xóa sinh viên khỏi lớp học phần",
        content: "<div>Các thông tin liên quan như: Kết quả học tập, Điểm danh đều sẽ bị xóa.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [{
                caption: "Xóa",
                cls: "js-dialog-close alert",
                onclick: function() {
                    console.log(el.dataset.moduleid);
                    var url = 'moduleclass/delete-student?studentId=' + el.id.replace('dlt', '') + "&moduleClassId=" + el.dataset.moduleid;
                    console.log(url);
                    $("#content-wrapper").load(url);
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