var payments = {
    1: 'Credit Card',
    2: 'Check',
    3: 'PayPal',
    4: 'Bank-wire'
};

var statuses = {
    1: ['Pre-order', 'bg-pink fg-white'],
    2: ['Payed', 'bg-green fg-white'],
    3: ['Payment Error', 'bg-red fg-white'],
    4: ['Delivered', 'bg-teal fg-white'],
    5: ['Preparing', 'bg-yellow'],
    6: ['Awaiting payment', 'bg-cyan fg-white'],
    7: ['Shipped', 'bg-lightGreen fg-white']
};

var hash = window.location.pathname;
var target = hash.length > 1 ? hash.substr(1) : "home";
var link = $(".navview-menu a[href*="+target+"]");
var menu = link.closest("ul[data-role=dropdown]");
var node = link.parent("li").addClass("active");

function getContent(target){
    window.on_page_functions = [];
    $.get("/" + target +"?ajax=true").then(
        function(response){
            $("#content-wrapper").html(response);
            window.on_page_functions.forEach(function(func){
                Metro.utils.exec(func, []);
            });
        }
    );
}

//getContent(target);

if (menu.length > 0) {
    menu.data("dropdown").open();
}

$(".navview-menu").on(Metro.events.click, "a", function(e){
    var href = $(this).attr("href");
    var pane = $(this).closest(".navview-pane");
    var hash;

    if (Metro.utils.isValue(href) && href.indexOf(".html") > -1) {
        document.location.href = href;
        return false;
    }

    if (href === "") {
        return false;
    }

    hash = href.substr(1);
    href = hash;

    getContent(hash);

    if (pane.hasClass("open")) {
        pane.removeClass("open");
    }

    pane.find("li").removeClass("active");
    $(this).closest("li").addClass("active");

    window.history.pushState(href, href, "/"+hash);

    return false;
});

function updateOrderStatus(){
    var val = $("#sel-statuses").val();
    var table = $("#table-order-statuses").find("tbody");
    var tr, td;

    tr = $("<tr>");
    td = $("<code>").addClass(statuses[val][1]).html(statuses[val][0]);

    $("<td>").html(td).appendTo(tr);
    $("<td>").addClass("text-right").html(""+(new Date()).format("%m/%d/%Y %H:%M")).appendTo(tr);

    table.prepend(tr);
}
function callAjaxSave(url){
	Metro.dialog.open('#loading-dialog');
	$.ajax({
        type: "GET",
        url: url,
        dataType: "html",
        success: function(data) {
        	Metro.dialog.close('#loading-dialog');
        	$("#content-wrapper").html(data.toString());
        },
        error: function() {
        	Metro.dialog.close('#loading-dialog');
            alert('Đã có lỗi xảy ra, vui lòng thử lại!');
        }
    });  
}
function callAjaxGetWithLoading(id,url){
	Metro.dialog.open('#loading-dialog');
	$.ajax({
        type: "GET",
        url: url,
        dataType: "html",
        success: function(data) {
        	Metro.dialog.close('#loading-dialog');
        	$(id).html(data.toString());
        },
        error: function() {
        	Metro.dialog.close('#loading-dialog');
            alert('Đã có lỗi xảy ra, vui lòng thử lại!');
        }
    });  
}
