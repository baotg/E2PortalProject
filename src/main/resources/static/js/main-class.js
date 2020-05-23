function getMainClasses() {
    var faculty = document.getElementById("faculty-select");
    var facultyId = faculty.options[faculty.selectedIndex].value;
    if (facultyId == 'empty') {
        var url = '/mainclass/search?id=';
        $('#mainclass-class-table').load(url);
        return;
    }
    var url = '/mainclass/search?id=' + facultyId;
    $('#main-class-table').load(url);
}

function deleteMainClass(id) {
    Metro.dialog.create({
        title: "Xóa lớp chủ nhiệm",
        content: "<div>Các thông tin liên quan như: Danh sách sinh viên, và thông tin của sinh viên đều sẽ bị xóa.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [{
                caption: "Xóa",
                cls: "js-dialog-close alert",
                onclick: function() {
                    var url = 'mainclass/delete?id=' + id.replace('dlt', '');
                    $("#main-class-table").load(url);
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