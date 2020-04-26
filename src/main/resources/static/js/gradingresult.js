$(document).ready(function () {
    $("#btnSubmitGradingResult").click(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });
});

function fire_ajax_submit() {
    var form = $('#import-grading-result')[0];
    var data = new FormData(form);
    
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