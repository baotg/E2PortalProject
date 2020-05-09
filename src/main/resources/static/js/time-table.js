$(document).ready(function () {
    $("#btnSubmitTimeTable").click(function (event) {
        event.preventDefault();
        fire_ajax_submit_time_table();
    });
});

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
            if(data==='successful')
            	retrieveGuests('/timetable/import');
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



   