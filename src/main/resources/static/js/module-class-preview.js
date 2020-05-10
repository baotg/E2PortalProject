
function cancelImportModuleClass(url){
    Metro.dialog.create({
        title: "Hủy bỏ",
        content: "<div>Nội dung hiện tại sẽ không được lưu lại.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [
            {
                caption: "Hủy bỏ",
                cls: "js-dialog-close alert",
                onclick: function(){
                    // $("#content-wrapper").load(url);
                	Metro.dialog.close('#module-class-preview-dialog');
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
function confirmImportModuleClass(url){
    Metro.dialog.create({
        title: "Xác nhận nhập lớp học phần",
        content: "<div>Nội dung hiện tại sẽ được lưu lại.<br> <b>Bạn có muốn tiếp tục?</b></div>",
        actions: [
            {
                caption: "Đồng ý",
                cls: "js-dialog-close alert",
                onclick: function(){
                	Metro.dialog.close('#module-class-preview-dialog');
                	callAjaxSave(url);
                	// $("#content-wrapper").load(url);
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