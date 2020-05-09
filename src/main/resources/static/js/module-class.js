$(document).ready(function () {
    $("#btnSubmitModuleClass").click(function (event) {
        event.preventDefault();
        fire_ajax_submit_module_class();
    });
});

function fire_ajax_submit_module_class() {
	var file = $("#file-module-class").val();
    var form = $('#import-module-class')[0];
    var data = new FormData(form);
    if(file==''){
    	var fileNotChosen = " *Chưa chọn tệp để tải lên";
    	$("#fileNotChosenModuleClass").html(fileNotChosen);
    return false;
    }
    $("#fileNotChosenModuleClass").html('');
    Metro.dialog.close('#module-class-dialog');
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
            	retrieveGuests('/moduleclass/import');
        },
        error: function (e) {

		alert('Tải lên không thành công!');
        }
    });
    $('#import-module-class')[0].reset();
    function retrieveGuests(url) {
        $("#content-wrapper").load(url);
    }

}
function viewStudent(id) {
	var url = '/moduleclass/' + id.replace('std','');
	$("#content-wrapper").load(url);
}
function editStudent(id) {
var url = '/moduleclass/edit?id=' + id.replace('edt','');
	$("#content-wrapper").load(url);
}
function deleteStudent(id) {
	var url = '/moduleclass/delete?id=' + id.replace('dlt','');
	$("#content-wrapper").load(url);
}
