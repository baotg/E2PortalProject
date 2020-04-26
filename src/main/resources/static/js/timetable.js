$(document).ready(function () {
    $("#btnSubmitTimeTable").click(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });
});

function fire_ajax_submit() {
    var form = $('#import-time-table')[0];
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



   