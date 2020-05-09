$(document).ready(function () {
    $("#btnSubmitAttendance").click(function (event) {
        event.preventDefault();
        fire_ajax_submit_attendance();
    });
});

function fire_ajax_submit_attendance() {
	var file = $("#file-attendance").val();
    var form = $('#import-attendance')[0];
    var data = new FormData(form);
    if(file==''){
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
        success: function (data) {
            if(data==='successful')
            	retrieveGuests('/attendance/import');
        },
        error: function (e) {

		alert('Tải lên không thành công!');
        }
    });
    $('#import-attendance')[0].reset();
    function retrieveGuests(url) {
        $("#content-wrapper").load(url);
    }

}