$(document).ready(function() {
    var path = $(location).attr("href").split('#');
    loadPage(path.length > 1 ? path[1] : "Home");

    $("a").click(function() {
        loadPage($(this).attr("href").slice(1));
    });
});

function loadPage(title) {
    $("#content").load(title.toLowerCase() + ".html");
}