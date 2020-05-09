$(document).ready(function () {
    $("#btnSubmitGradingResult").click(function (event) {
        event.preventDefault();
        fire_ajax_submit_grading_result();
    });
});

function fire_ajax_submit_grading_result() {
	var file = $("#file-grading-result").val();
    var form = $('#import-grading-result')[0];
    var data = new FormData(form);
    if(file==''){
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
        success: function (data) {
            if(data==='successful')
            	retrieveGuests('/gradingresult/import');
        },
        error: function (e) {

		alert('Tải lên không thành công!');
        }
    });
    $('#import-grading-result')[0].reset();
    function retrieveGuests(url) {
        $("#content-wrapper").load(url);
    }

}