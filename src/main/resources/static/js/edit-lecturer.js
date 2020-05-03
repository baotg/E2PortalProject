 $(document).ready(function () {
        $("#btnSaveLecturer").click(function (event) {
            event.preventDefault();
            var vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
            var firstName = $("#firstName").val();
            var lastName = $("#lastName").val();
            var address = $("#address").val();
            var phoneNumber = $("#numberPhone").val();
            var dateOfBirth = $("#dateOfBirth").val();
            var email = $("#email").val();

        	$("#errLecturerLastName").html('');
        	$("#errLecturerFirstName").html('');
        	$("#errLecturerAddress").html('');
        	$("#errLecturerPhoneNumber").html('');
        	$("#errLecturerDOB").html('');
        	$("#errLecturerEmail").html('');
        	 if(firstName =='' || lastName == '' || address == '' || phoneNumber == '' ||  email == '' ){
        	    	if(firstName ==''){
        				$('#errLecturerFirstName').html('*Thông tin này không được để trống!');
        				
        			}
        			if(lastName ==''){
        				$('#errLecturerLastName').html('*Thông tin này không được để trống!');
        				
        			}
        			if(address ==''){
        				$('#errLecturerAddress').html('*Thông tin này không được để trống!');
        			
        			}
        			if(phoneNumber ==''){
        				$('#errLecturerPhoneNumber').html('*Thông tin này không được để trống!');
        			
        			}
        			if(dateOfBirth == ''){
        				$('#errLecturerDOB').html('*Thông tin này không được để trống!');
        			}
        			if(email ==''){
        				$('#errLecturerEmail').html('*Thông tin này không được để trống!');
        			}
        			
        				return false;
        	    }
        	            
        	        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        			if(!email.match(mailformat))
        			{
        				$('#errLecturerEmail').html(' *Địa chỉ email không hợp lệ');
        				return false;
        			}
        			var yearOld = new Date().getFullYear() - new Date(dateOfBirth).getFullYear();
        			if(yearOld<0 || yearOld>100){
        				$('#errLecturerDOB').html(' *Ngày sinh không hợp lệ!');
        				return false;
        			}
        				
        			if(yearOld<18){
        				$('#errLecturerDOB').html(' *Chưa đủ 18 tuổi!');
        				return false;
        			}
        			
        			if(vnf_regex.test(phoneNumber) == false){
        				$("#errLecturerPhoneNumber").html(' *Số điện toại không đúng định dạng');
        				return false;
        			}

        	        fire_ajax_submit();
        	    });
        	   });

    function fire_ajax_submit() {
        var form = $('#lecturer-form')[0];
        var data = new FormData(form);

        $.ajax({
            type: "POST",
            url: "lecturer/save",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
            	 $("#content-wrapper").html(data.toString());
                console.log("OK!")
            },
            errLectureror: function (e) {
                alert('Lưu không thành công!');
            }
        });
        $('#lecturer-form')[0].reset();
       
    }
    function cancelEdtingLecturer(url){
        Metro.dialog.create({
            title: "Hủy bỏ",
            content: "<div>Nội dung hiện tại sẽ không được lưu lại.<br> <b>Bạn có muốn tiếp tục?</b></div>",
            actions: [
                {
                    caption: "Hủy bỏ",
                    cls: "js-dialog-close alert",
                    onclick: function(){
                         $("#content-wrapper").load(url);
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
