// jQuery script that runs on page load
$(document).ready(function() {
    // Declare a function to load child page
    $.extend({ loadPage: function(title) {
        // Load page into #content from provided title
        $("#content").load(title.toLowerCase() + ".html");
    }});

    // Initially, load page by hash if any, otherwise load home
    $.loadPage(location.hash ? location.hash : "Home");

    // When an anchor tag is clicked
    $("a").click(function() {
        // Load page according to hash from <a>'s href
        $.loadPage($(this).attr("href").slice(1));
    });
});
