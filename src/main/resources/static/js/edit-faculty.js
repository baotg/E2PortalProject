function saveFaculty(){
	   event.preventDefault();
	   		$("#errFacultyName").html('');
			
			var facultyName = $("#facultyName").val();
			if(facultyName==''){
				$("#errFacultyName").html('Không được bỏ trống!');
				return false;
			}
			Metro.dialog.close('#faculty-edit-dialog');
	        fire_ajax_submit_faculty_edit();
}

    function fire_ajax_submit_faculty_edit() {
        var form = $('#edit-faculty-form')[0];
        var data = new FormData(form);

        $.ajax({
            type: "POST",
            url: "/faculty/save",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                $("#faculty-table").html(data.toString());
            },
            error: function (e) {
            	Metro.dialog.close('#faculty-edit-dialog');
                alert('Lưu không thành công!');
            }
        });
        $('#edit-faculty-form')[0].reset();
        
    }
    function cancelEdtingFaculty(url){
        Metro.dialog.create({
            title: "Hủy bỏ",
            content: "<div>Nội dung hiện tại sẽ không được lưu lại.<br> <b>Bạn có muốn tiếp tục?</b></div>",
            actions: [
                {
                    caption: "Hủy bỏ",
                    cls: "js-dialog-close alert",
                    onclick: function(){
                    	Metro.dialog.close('#faculty-edit-dialog');
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
    
