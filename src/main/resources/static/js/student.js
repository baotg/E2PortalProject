$(document).ready(function () {
    $("#btnSubmit").click(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });
});

function fire_ajax_submit() {
    var form = $('#import-student')[0];
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
function deleteStudent(id) {
	var url = '/student/delete?id=' + id.replace('dlt','');
	$("#content-wrapper").load(url);
}


   