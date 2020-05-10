//$(document).ready(function () {
//    $("#btnSubmitTimeTable").click(function (event) {
//       
//    });
//});
function doImportTimeTable(){
	 event.preventDefault();
     fire_ajax_submit_time_table();
}
function fire_ajax_submit_time_table() {
	var file = $("#file-time-table").val();
    var form = $('#import-time-table')[0];
    var data = new FormData(form);
    if(file==''){
    	var fileNotChosen = " *Chưa chọn tệp để tải lên";
    	$("#fileNotChosenTimeTable").html(fileNotChosen);
    return false;
    }
    $("#fileNotChosenTimeTable").html('');
    Metro.dialog.close('#time-table-dialog');
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
                $("#fileNotChosenTimeTable").html(fileNotMatch);
                Metro.dialog.open('#time-table-dialog');
                return false;
            }
            if(data==='successful'){
            	$.ajax({
                    type: "GET",
                    url: '/timetable/import',
                    dataType: "html",
                    success: function(data) {
                    	Metro.dialog.close('#loading-dialog');
                    	if(data==='notMatch'){
                    		var fileNotMatch = " *Tệp tin tải lên sai định dạng, vui lòng thử lại!";
                    		 $("#fileNotChosenTimeTable").html(fileNotMatch);
                    		 Metro.dialog.open('#time-table-dialog');
                    		 return false;
                    	}
                    	$('#time-table-preview-dialog-content').html(data.toString());
                    	Metro.dialog.open('#time-table-preview-dialog');
                    //	getStudentPreviewDialog('/student/import');
                    },
                    error: function() {
                    	Metro.dialog.close('#loading-dialog');
                        alert('Đã có lỗi xảy ra, vui lòng thử lại!');
                    }
                });              	
//            	retrieveGuests('/timetable/import');
            }
        },
        error: function (e) {
		alert('Tải lên không thành công!');
        }
    });
    $('#import-time-table')[0].reset();
    function retrieveGuests(url) {
        $("#content-wrapper").load(url);
    }

}
function getClassesTimeTable() {
	var faculty = document.getElementById("faculty-select");
	var aClass = document.getElementById("class-select");
	var urlnone = '/timetable/search/class?id=';
	var facultyId = faculty.options[faculty.selectedIndex].value;
	aClass = '';
	if(facultyId=='empty'){
		$('#students-table').load(urlnone);
		$('#select-classes').load('/timetable/search?id=');
		return;
	}
	var url = '/timetable/search?id=' + facultyId;
	$('#select-classes').load(url);
	$('#time-table-table').load(urlnone);
}
function getTimeTables() {
	var aClass = document.getElementById("class-select");
	var classId = aClass.options[aClass.selectedIndex].value;
	if(classId=='empty'){
		classId='';
	}
	var url2 = '/timetable/search/class?id=' + classId;
	$('#time-table-table').load(url2);
}



   