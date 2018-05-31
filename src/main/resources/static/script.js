"use strict";

var id;
var is_debug = true;

var base_url = "http://localhost:8000/api/";
// var base_url = "http://livingstone.ethz.ch:8000/api/";

var api_calls = [ do_followers];

$(document).ready(function() {
	console.log("ready");

	if (is_debug) {
		init_debug();
	}
});

function init_debug() {
	$("#profile1").prop("value", "ninaagdal");
	$("#profile2").prop("value", "saskagram");

	$(".welcome-form button").click();
}

function on_compare_click() {
	console.log("on_compare_click");
	$(".welcome-wrapper").css(
	{
		display: "none"
	});

	var profile1 = $("#profile1").prop("value");
	var profile2 = $("#profile2").prop("value");

	console.log(profile1);
	console.log(profile2);

	$(".wait-text").text("Creating comparison for " + profile1 + " & " + profile2);

	$(".wait-wrapper").css(
	{
		display: "flex"
	});

	do_init_request(profile1, profile2);
}

function do_init_request(profile1, profile2) {

	var params = JSON.stringify({"profile1": profile1, "profile2": profile2});

	var req = new XMLHttpRequest();
	req.open("POST", base_url);
	req.setRequestHeader("Content-type", "application/json");

	req.onload = function() {
		handle_init_request(this.responseText);

		$("#th-profile1").text(profile1);
		$("#th-profile2").text(profile2);
	}

	req.send(params);
}

function handle_init_request(responseText) {
	id = responseText;

	$(".wait-wrapper").css({
		display: "none"
	});

	$(".comparison-wrapper").css({
		display: "block"
	});

	api_calls.forEach(f => f());
}

function do_followers() {
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "follower/ratio?id=" + id);

	req.onload = function() {
		var followerData = JSON.parse(this.responseText);

		$("#follow-count1").text(followerData["followCount1"]);
		$("#follow-count2").text(followerData["followCount2"]);

		$("#followedBy-count1").text(followerData["followedByCount1"]);
		$("#followedBy-count2").text(followerData["followedByCount2"]);

		$("#ratio1").text(followerData["ratio1"]);
		$("#ratio2").text(followerData["ratio2"]);
	}

	req.send();
}