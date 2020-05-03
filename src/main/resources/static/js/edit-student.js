 $(document).ready(function () {
    $("#btnSave").click(function (event) {
        event.preventDefault();
    var vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
    var vnf_regex2 = /((09|03|07|08|05)+([0-9]{8})\b)/g;
    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var address = $("#address").val();
    var phoneNumber = $("#numberPhone").val();
    var dateOfBirth = $("#dateOfBirth").val();
    var parentPhoneNumber = $("#familyNumber").val();
    var email = $("#email").val();

	$("#errLastName").html('');
	$("#errFirstName").html('');
	$("#errAddress").html('');
	$("#errPhoneNumber").html('');
	$("#errDOB").html('');
	$("#errParentPhoneNumber").html('');
	$("#errEmail").html('');
	  
           
    if(firstName =='' || lastName == '' || address == '' || phoneNumber == '' || parentPhoneNumber == '' || email == '' ){
    	if(firstName ==''){
			$('#errFirstName').html('*Thông tin này không được để trống!');
			
		}
		if(lastName ==''){
			$('#errLastName').html('*Thông tin này không được để trống!');
			
		}
		if(address ==''){
			$('#errAddress').html('*Thông tin này không được để trống!');
		
		}
		if(phoneNumber ==''){
			$('#errPhoneNumber').html('*Thông tin này không được để trống!');
		
		}
		if(dateOfBirth == ''){
			$('#errDOB').html('*Thông tin này không được để trống!');
		}
		if(parentPhoneNumber ==''){
			$('#errParentPhoneNumber').html('*Thông tin này không được để trống!');
		
		}
		if(email ==''){
			$('#errEmail').html('*Thông tin này không được để trống!');
		}
		
			return false;
    }
            
        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(!email.match(mailformat))
		{
			$('#errEmail').html(' *Địa chỉ email không hợp lệ');
			return false;
		}
		var yearOld = new Date().getFullYear() - new Date(dateOfBirth).getFullYear();
		if(yearOld<0 || yearOld>100){
			$('#errDOB').html(' *Ngày sinh không hợp lệ!');
			return false;
		}
			
		if(yearOld<18){
			$('#errDOB').html(' *Chưa đủ 18 tuổi!');
			return false;
		}
		
		if(vnf_regex.test(phoneNumber) == false){
			$("#errPhoneNumber").html(' *Số điện toại không đúng định dạng');
			return false;
		}
		if(vnf_regex2.test(parentPhoneNumber) == false){
			$("#errParentPhoneNumber").html(' *Số điện toại không đúng định dạng');
			return false;
		}
        fire_ajax_submit();
    });
   });

    function fire_ajax_submit() {
        var form = $('#student-form')[0];
        var data = new FormData(form);

        $.ajax({
            type: "POST",
            url: "student/save",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                retrieveGuests('/student');
                console.log("OK!")
            },
            error: function (e) {
                alert('Lưu không thành công!');
            }
        });
        $('#student-form')[0].reset();
        function retrieveGuests(url) {
            $("#content-wrapper").load(url);
        }
    }