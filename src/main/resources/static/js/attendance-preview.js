function cancelImportAttendance(url){
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
function confirmImportAttendance(url){
    Metro.dialog.create({
        title: "Xác nhận nhập điểm danh",
        content: "<div>Nội dung hiện tại sẽ được lưu lại.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [
            {
                caption: "Đồng ý",
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