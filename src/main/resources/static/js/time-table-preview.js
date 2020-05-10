function cancelImportTimeTable(url){
    Metro.dialog.create({
        title: "Hủy bỏ",
        content: "<div>Nội dung hiện tại sẽ không được lưu lại.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [
            {
                caption: "Hủy bỏ",
                cls: "js-dialog-close alert",
                onclick: function(){
                	Metro.dialog.close('#time-table-preview-dialog');
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
function confirmImportTimeTable(url){
    Metro.dialog.create({
        title: "Xác nhận nhập lịch học",
        content: "<div>Nội dung hiện tại sẽ được lưu lại.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [
            {
                caption: "Đồng ý",
                cls: "js-dialog-close alert",
                onclick: function(){
                	Metro.dialog.close('#time-table-preview-dialog');
                	callAjaxSave(url);
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