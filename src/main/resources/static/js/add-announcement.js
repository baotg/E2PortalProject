//    $(document).ready(function () {
//         $("#btnSaveAnnouncement").click(function (event) {
//             event.preventDefault();
//             var title = $("#title").val();
//             var summary = $("#summary").val();
//             var contentDetail = $("#contentDetail").val();
//             $('#errTitle').html('');
//             $('#errSummary').html('');
//             $('#errContent').html('');
//             if(title == '' || summary == '' || contentDetail == ''){
//             	if(title ==''){
//         			$('#errTitle').html('*Thông tin này không được để trống!');       			
//         		}
//         		if(summary ==''){
//         			$('#errSummary').html('*Thông tin này không được để trống!');    			
//         		}
//         		if(contentDetail ==''){
//         			$('#errContent').html('*Thông tin này không được để trống!');      		
//         		}
//         			return false;
//             }
//             Metro.dialog.close('#announcememt-add-dialog');
//             Metro.dialog.open('#loading-dialog');
//             fire_ajax_submit_add_announcement();
//         });
//     });
    function doSaveAnnouncement(){
    	 event.preventDefault();
    	 var title = $("#title").val();
         var summary = $("#summary").val();
         var contentDetail = $("#contentDetail").val();
         $('#errTitle').html('');
         $('#errSummary').html('');
         $('#errContent').html('');
         if(title == '' || summary == '' || contentDetail == ''){
         	if(title ==''){
     			$('#errTitle').html('*Thông tin này không được để trống!');       			
     		}
     		if(summary ==''){
     			$('#errSummary').html('*Thông tin này không được để trống!');    			
     		}
     		if(contentDetail ==''){
     			$('#errContent').html('*Thông tin này không được để trống!');      		
     		}
     			return false;
         }
         Metro.dialog.close('#announcememt-add-dialog');
         Metro.dialog.open('#loading-dialog');
         fire_ajax_submit_add_announcement();
    }

    function fire_ajax_submit_add_announcement() {

        var form = $('#add-form')[0];
        var data = new FormData(form);
        $.ajax({
            type: "POST",
            url: "announcement/save",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
            	Metro.dialog.close('#loading-dialog');
            	 $("#content-wrapper").html(data.toString());
            },
            error: function (e) {
                alert('Lưu không thành công!');
            }
        });
        $('#add-form')[0].reset();
        function retrieveGuests(url) {
            $("#content-wrapper").load(url);
        }
    }
    function cancelAddingAnnouncement(url){
        Metro.dialog.create({
            title: "Hủy bỏ",
            content: "<div>Nội dung hiện tại sẽ không được lưu lại.<br> <b>Bạn có muốn tiếp tục?</b></div>",
            actions: [
                {
                    caption: "Hủy bỏ",
                    cls: "js-dialog-close alert",
                    onclick: function(){
                        // $("#content-wrapper").load(url);
                    	 Metro.dialog.close('#announcememt-add-dialog');
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