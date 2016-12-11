// jQuery script that runs on page load
$(document).ready(function() {
	// When an anchor tag is clicked
	$("a").click(function() {
		// If the link isn't already active
		if(!$(this).hasClass("active")) {
			// Store page title after extracting it from <a>'s href
			var page = $(this).attr("href").slice(1).toLowerCase();
			// If the clicked link isn't of the sub-pages
			if(!$(this).parent().hasClass("tab")) {
				// Deactivate all links except the clicked one
				$("#main-nav li").removeClass("active");
				$(this).parent().addClass("active");
				// If tabs are already shown
				if($(".tabs").css("height") == "48px") {
					// Hide them with an animation
					$(".tabs").animate({ height: 0 });
				}
				// If the clicked link has sub-pages
				if($('.' + page).length > 0) {
					// Open tabs with an animation
					$(".tabs").animate({ height: "48px" }, {
						// Before the animation starts
						start: function() {
							// Hide all sub-pages
							$(".tab").addClass("hide");
							// Show the sub-pages related to the selected category
							$('.' + page).removeClass("hide").first().find("a").click();
						}
					});
					// Reaching here means it's a category, so end
					// execution as there's no need to load any page
					return;
				}
			}
			// Reaching here means it's either a main-page or a sub-page
			// Load page according to hash from <a>'s href
			$("#content").load(page + ".html");
		}
	});
	// Deactivate all links initially
	$(".tab a").removeClass("active");
	// Initially, click link from hash if any, otherwise click home
	$("a[href='" + (location.hash ? location.hash : "#Home") + "']").click();
});
