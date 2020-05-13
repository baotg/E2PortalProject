function viewLecturer(id) {
	var url = '/lecturer/' + id.replace('ltr','');
	$("#content-wrapper").load(url);
}

function editLecturer(id){
	var url = '/lecturer/edit?id=' + id.replace('edt','');
	$("#lecturer-edit-dialog-content").load(url);
	Metro.dialog.open('#lecturer-edit-dialog');
}
function deleteLecturer(id){
    Metro.dialog.create({
        title: "Xóa giảng viên",
        content: "<div>Các thông tin liên quan như sau đều sẽ bị xóa: <br>1. Các lớp học phần do giảng viên này đảm nhận<br>2. Các lớp học cho giảng viên này chủ nhiệm<br>3. Toàn bộ sinh viên thuộc các lớp nêu trên<br>4. Bảng điểm danh<br> 5. Thời khóa biểu<br><b>Bạn có muốn tiếp tục?</b></div>",
        actions: [
            {
                caption: "Xóa",
                cls: "js-dialog-close alert",
                onclick: function(){
                	 var url = 'lecturer/delete?id=' + id.replace('dlt','');
                     $("#content-wrapper").load(url);
                }
            },
            {
                caption: "Hủy",
                cls: "js-dialog-close",
                onclick: function(){
                    
                }
            }
        ]
    });
}