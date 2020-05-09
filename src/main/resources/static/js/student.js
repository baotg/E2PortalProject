$(document).ready(function () {
    $("#btnSubmitStudent").click(function (event) {
        $("#fileNotChosen").html('');
        event.preventDefault();
        fire_ajax_submit_student();
    });
});

function fire_ajax_submit_student() {
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
            if(data==='notMatch'){
            	console.log('nomatch');
            	var fileNotMatch = " *Tệp tin tải lên sai định dạng, vui lòng thử lại!";
                $("#fileNotChosen").html(fileNotMatch);
                Metro.dialog.open('#student-dialog');
                return false;
            }
            if(data==='successful'){
            	$.ajax({
                    type: "GET",
                    url: '/student/import',
                    dataType: "html",
                    success: function(data) {
                    	Metro.dialog.close('#loading-dialog');
                    	if(data==='notMatch'){
                    		var fileNotMatch = " *Tệp tin tải lên sai định dạng, vui lòng thử lại!";
                    		 $("#fileNotChosen").html(fileNotMatch);
                    		 Metro.dialog.open('#student-dialog');
                    		 return false;
                    	}
                    	$('#student-preview-dialog-content').html(data.toString());
                   	 Metro.dialog.open('#student-preview-dialog');
                    //	getStudentPreviewDialog('/student/import');
                    },
                    error: function() {
                    	Metro.dialog.close('#loading-dialog');
                        alert('Đã có lỗi xảy ra, vui lòng thử lại!');
                    }
                });  
            	
            }
            	
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
	url+='?ajax=true';
	$("#content-wrapper").load(url);
}
//function editStudent(id) {
//var url = '/student/edit?id=' + id.replace('edt','');
//	$("#student-edit-dialog-content").load(url);
//}
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
                     $("#students-table").load(url);
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
function getClasses() {
	var faculty = document.getElementById("faculty-select");
	var aClass = document.getElementById("class-select");

	var facultyId = faculty.options[faculty.selectedIndex].value;
	aClass = '';
	if(facultyId=='empty'){
		return;
	}
	var url = '/student/search?id=' + facultyId;
	$('#select-classes').load(url);
}
function getStudents() {
	var aClass = document.getElementById("class-select");
	var classId = aClass.options[aClass.selectedIndex].value;
	if(classId=='empty'){
		classId='';
	}
	var url2 = '/student/search/class?id=' + classId;
	
		
	$('#students-table').load(url2);
}
function getStudentPreviewDialog(url){
	$('#student-preview-dialog-content').load(url);
	 Metro.dialog.open('#student-preview-dialog');
}


   