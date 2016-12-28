// jQuery script that runs on page load
$(document).ready(function() {
	$.ajaxSetup({ cache: false });
	$.extend({
		click: function(button, map) {
			$("a[href='" + button + "']").trigger("click", map);
		}, colors: {
			"#Home": ["#5b1f1e", "#e64a19"],
			"#Specs": ["#6a1b9a", "#9c27b0"],
			"#Gallery": ["#1a237e", "#3f51b5"],
			"#Download": ["#0277bd", "#03a9f4"],
			"#Improve": ["#263238", "#607d8b"],
			"#About": ["#880e4f", "#d81b60"],
			"#Login": ["#558b2f", "#8bc34a"]
		}
	});
	// When an anchor tag is clicked
	$("a").click(function(event, map) {
		// If the link isn't already active
		if(!$(this).hasClass("active") && $(this).attr("href").startsWith('#')) {
			// Replace URL's hash with <a>'s href
			location.hash = $(this).attr("href")
			if($.colors[location.hash]) {
				$("nav").animate({
					backgroundColor: $.colors[location.hash][0]
				}, map == "init" ? 0 : 400);
				$("footer").animate({
					backgroundColor: $.colors[location.hash][1]
				}, map == "init" ? 0 : 400);
			}
			// Store page title after extracting it from hash
			var page = location.hash.slice(1).toLowerCase();
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
			$("#content").load(page + ".html", function() {
				$(map).trigger("click", "redirect");
			});
		}
		if(location.hash == "#Home") {
			$("body").addClass("welcome");
		} else {
			$("body").removeClass("welcome");
		}
	});
	// Deactivate all links initially
	$(".tab a").removeClass("active");
	// Initially, click link from hash if any, otherwise click home
	$.click(location.hash ? location.hash : "#Home", "init");
});
