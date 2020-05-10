function editAnnouncement(id) {
	var url = 'announcement/edit?id=' + id.replace('edt','');
	$("#announcement-add-dialog-content").load(url);
	Metro.dialog.open('#announcememt-add-dialog');
}
function addAnnouncement() {
	var url = 'announcement/add?ajax=true';
	$("#announcement-add-dialog-content").load(url);
	Metro.dialog.open('#announcememt-add-dialog');
}
function deleteAnnouncement(id){
	Metro.dialog.create({
		title: "Xóa thông báo",
		content: "<div>Nội dung bạn vừa chọn sẽ bị xóa vĩnh viễn khỏi hệ thống.<br> <b>Bạn có muốn tiếp tục?</b></div>",
		actions: [
			{
				caption: "Xóa",
				cls: "js-dialog-close alert",
				onclick: function(){
					var url = 'announcement/delete?id=' + id.replace('dlt','');
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
function goToPage(id){
	var href=document.location.href;
	window.history.pushState(href, href, id);
	var url = id += '&ajax=true'
	$("#content-wrapper").load(id);
}


