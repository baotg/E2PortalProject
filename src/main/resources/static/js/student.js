$(document).ready(function () {
    $("#btnSubmitStudent").click(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });
});

function fire_ajax_submit() {
	var file = $("#file-student").val();
    var form = $('#import-student')[0];
    var data = new FormData(form);
    if(file==''){
    	var fileNotChosen = " *Chưa chọn tệp để tải lên";
    	$("#fileNotChosen").html(fileNotChosen);
    return false;
    }
    $("#fileNotChosen").html('');
    Metro.dialog.close('#student-dialog');
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/upload",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            if(data==='successful')
            	retrieveGuests('/student/import');
        },
        error: function (e) {

		alert('Tải lên không thành công!');
        }
    });
    $('#import-student')[0].reset();
    function retrieveGuests(url) {
        $("#content-wrapper").load(url);
    }
   

}
function viewStudent(id) {
	var url = '/student/' + id.replace('std','');
	$("#content-wrapper").load(url);
}
function editStudent(id) {
var url = '/student/edit?id=' + id.replace('edt','');
	$("#content-wrapper").load(url);
}
function deleteStudent(id){
    Metro.dialog.create({
        title: "Xóa sinh viên",
        content: "<div>Các thông tin liên quan như: Kết quả học tập, Điểm danh đều sẽ bị xóa.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [
            {
                caption: "Xóa",
                cls: "js-dialog-close alert",
                onclick: function(){
                	 var url = 'student/delete?id=' + id.replace('dlt','');
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


   