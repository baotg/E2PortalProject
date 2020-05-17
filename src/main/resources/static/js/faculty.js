function deleteFaculty(id){
	console.log(id);
    Metro.dialog.create({
        title: "Xóa khoa - viện",
        content: "<div>Các thông tin liên quan như: Lớp chủ nhiệm, Lớp học phần sẽ bị xóa.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [
            {
                caption: "Xóa",
                cls: "js-dialog-close alert",
                onclick: function(){
                	
                	 var url = 'faculty/delete?id=' + id.replace('dlt','');
                     $("#faculty-table").load(url);
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
function getFacultyEditDialog(id){
	var url = '/faculty/edit?id=' + id.replace('edt','');
	$("#faculty-edit-dialog-content").load(url);
	Metro.dialog.open('#faculty-edit-dialog');
}