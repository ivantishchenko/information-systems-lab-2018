"use strict";

var id;
var is_debug = false;

var base_url = "http://localhost:8000/api/";
var numvalue;
var morn1=0,morn2=0,even1=0,even2=0,aft1=0,aft2=0,nig1=0,nig2=0;
var filter;
//var base_url = "http://livingstone.ethz.ch:8000/api/";

function myFunction(){
	$('.advice1hide').toggle();
	$("#test1").toggleClass("fa-minus-square");
}

function myFunction2(){
	$('.advice2hide').toggle();
	$("#test2").toggleClass("fa-minus-square");
}

function myFunction3(){
	$('.advice3hide').toggle();
	$("#test3").toggleClass("fa-minus-square");
}

function myFunction4(){
	$('.advice4hide').toggle();
	$("#test4").toggleClass("fa-minus-square");
}

function myFunction5(){
	$('.advice5hide').toggle();
	$("#test5").toggleClass("fa-minus-square");
}

function myFunction6(){
	$('.advice6hide').toggle();
	$("#test6").toggleClass("fa-minus-square");
}

var api_calls = [ do_images, do_followers, do_hashtags, do_posttimes, do_weektimes, do_likefollower, do_commentfollower,do_hashtagpost, do_advicefollow, do_advicehash,do_regularity,
					do_imagevideo,do_popular,do_likehash,do_commenthash,do_week,do_advicepostingtime,
					do_advicepostperweek,do_advicehashtagsPerPost, do_adviceimageVideo,
					do_advicelikehash,do_advicecommenthash,do_advicepostsPerDay ];


$(document).ready(function() {
	console.log("ready");

	if (is_debug) {
		init_debug();
	}
	var slider = document.getElementById("myRange");
	var output = document.getElementById("demo");
	output.innerHTML="100";

	slider.oninput = function() {
		if(slider.value == 1)
			output.innerHTML="1";
		if(slider.value == 4)
			output.innerHTML="3";
		if(slider.value == 7)
			output.innerHTML="10";
		if(slider.value == 10)
			output.innerHTML="100";
	}
});

function init_debug() {
	$("#profile1").prop("value");
	$("#profile2").prop("value");

	$(".welcome-form button").click();
}

function on_compare_click() {
	console.log("on_compare_click");
	$(".limiter").css(
	{
		display: "none"
	});

	var profile1 = $("#profile1").prop("value");
	var profile2 = $("#profile2").prop("value");

	var slider = document.getElementById("myRange");
	var output = document.getElementById("demo");

	//console.log(slider.value)
	if(slider.value == 1)
		numvalue = 1;
	if(slider.value == 4)
		numvalue = 3;
	if(slider.value == 7)
		numvalue = 10;
	if(slider.value == 10)
		numvalue = -1;

	console.log(numvalue);

	filter = $("input[name='radio']:checked").val();
	console.log(filter);

	console.log(profile1);
	console.log(profile2);

	//$(".wait-text").text("Creating comparison for " + profile1 + " & " + profile2);

	$(".wait-wrapper").css(
	{
		display: "flex"
	});

	do_init_request(profile1, profile2, numvalue, filter);
}

function do_init_request(profile1, profile2, numvalue, filter) {

	var params = JSON.stringify({"profile1": profile1, "profile2": profile2, "filter": filter, "numvalue": numvalue});

	var req = new XMLHttpRequest();
	req.open("POST", base_url);
	req.setRequestHeader("Content-type", "application/json");

	req.onload = function() {
		handle_init_request(this.responseText);

	//	$("#Profile1").text(profile1);
	//	$("#Profile2").text(profile2);
}

req.send(params);
}

function handle_init_request(responseText) {
	id = responseText;

	$(".limiter").css({
		display: "none"
	});

	$("#displaydata").css({
		display: "block"
	});

	api_calls.forEach(f => f());

	/*$(".wait-wrapper").css({
		display: "none"
	});*/
}

function do_followers() {
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "follower/ratio?id=" + id);

	req.onload = function() {
		var followerData = JSON.parse(this.responseText);

		$("#FollowerCount1").text(followerData["followCount1"]);
		$("#FollowerCount2").text(followerData["followCount2"]);

		$("#FolloweeCount1").text(followerData["followedByCount1"]);
		$("#FolloweeCount2").text(followerData["followedByCount2"]);

		var num1 = followerData["ratio1"];
		num1 = parseFloat(num1).toFixed(5);

		var num2 = followerData["ratio2"];
		num2 = parseFloat(num2).toFixed(5);

		$("#Ratio1").text(num1);
		$("#Ratio2").text(num2);
	}

	req.send();
} 


////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

function do_hashtags() {
	console.log("in hashtag");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "hashtags/percentage?id=" + id);

	req.onload = function() {
		var hashtagData = JSON.parse(this.responseText);

		var text = "";
		var ctr = 1;
		for(var i in hashtagData["hashtags1"]){
			//text = i + " : " + hashtagData["hashtags1"][i];
			//console.log(ctr);
			if(ctr==1){
				$('#tp11').text(i);
				var l1 = new ldBar("#p11");
				l1.set(hashtagData["hashtags1"][i]);
				//console.log(typeof(hashtagData["hashtags1"][i]));
			}

			if(ctr==2){
				$('#tp12').text(i);
				var l1 = new ldBar("#p12");
				l1.set(hashtagData["hashtags1"][i]);
			}
			if(ctr==3){
				$('#tp13').text(i);
				var l1 = new ldBar("#p13");
				l1.set(hashtagData["hashtags1"][i]);
			}
			if(ctr==4){
				$('#tp14').text(i);
				var l1 = new ldBar("#p14");
				l1.set(hashtagData["hashtags1"][i]);
			}
			if(ctr==5){
				$('#tp15').text(i);
				var l1 = new ldBar("#p15");
				l1.set(hashtagData["hashtags1"][i]);
			}

			ctr=ctr+1;
			//$('ol.p1').appendChild(e);
		}
		//	$('ol.p1').append( "<li>" + text + "</li>" + '<div class="progressbar"></div>');

		var text1 = "";
		ctr = 1;
		for(var i in hashtagData["hashtags2"]){

			if(ctr==1){
				$('#tp21').text(i);
				var l1 = new ldBar("#p21");
				l1.set(hashtagData["hashtags2"][i]);
				//console.log(typeof(hashtagData["hashtags2"][i]));
			}

			if(ctr==2){
				$('#tp22').text(i);
				var l1 = new ldBar("#p22");
				l1.set(hashtagData["hashtags2"][i]);
			}
			if(ctr==3){
				$('#tp23').text(i);
				var l1 = new ldBar("#p23");
				l1.set(hashtagData["hashtags2"][i]);
			}
			if(ctr==4){
				$('#tp24').text(i);
				var l1 = new ldBar("#p24");
				l1.set(hashtagData["hashtags2"][i]);
			}
			if(ctr==5){
				$('#tp25').text(i);
				var l1 = new ldBar("#p25");
				l1.set(hashtagData["hashtags2"][i]);
			}

			ctr=ctr+1;
		}
	}
	req.send();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

function do_images(){
	console.log("in images");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "userinfo?id=" + id);

	req.onload = function() {
		var userData = JSON.parse(this.responseText);

		//console.log(userData["picUrl1"]);
		$('#img1').attr('src',userData["picUrl1"]);
		$('#img2').attr('src',userData["picUrl2"]);

		var flag1 = userData["privacyFlag1"];
		var flag2 = userData["privacyFlag2"];
		if(flag1==true){
			$('#private1').text("Warning Private Profile");
			$('#private2').text("Warning Private Profile");
			$('#private2').css("visibility","hidden");
		}
			
		if(flag2==true){
			$('#private2').text("Warning Private Profile");
			$('#private1').text("Warning Private Profile");	
			$('#private1').css("visibility","hidden");
		}
			

		$('#Profile1').text(userData["fullName1"]);
		$('#Profile2').text(userData["fullName2"]);

	}

	req.send();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

function do_weektimes(){
	console.log("in week");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "posttime/week?id=" + id);

	req.onload = function() {
		var weekData = JSON.parse(this.responseText);

		/*var sum1 = 0;
		for(var i in weekData["distribution1"]){
			sum1 = sum1 + weekData["distribution1"][i];
		}

		for(var i in weekData["distribution1"]){
			weekData["distribution1"][i] = sum1/weekData["distribution1"][i];
		}

		var sum2 = 0;
		for(var i in weekData["distribution2"]){
			sum2 = sum2 + weekData["distribution2"][i];
		}

		for(var i in weekData["distribution2"]){
			weekData["distribution2"][i] = sum2/weekData["distribution2"][i];
		}*/

		var ctx = document.getElementById("myChart13").getContext('2d');
		var mypieChart1 = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: ["Sunday", "Monday", "Tuesday", "Wednesday","Thursday","Friday","Saturday"],
				datasets: [{
					label: 'Posting Time',
					data: weekData["distribution1"],
					backgroundColor: [
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 159, 64, 1.0)',
					'rgba(100, 100, 100, 1.0)'
					],
					borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(100, 100, 100,1)'
					],
					borderWidth: 1
				}]
			},
			options : {
	            scales: {
	                xAxes: [{
	                    gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    }
	                }],
	                yAxes: [{
	                    ticks: {
							beginAtZero:true
						}
	                }]
	            },
	    		legend: {
			    	display: false
			    }
    		}
		});

		var ctx = document.getElementById("myChart23").getContext('2d');
		var mypieChart2 = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: ["Sunday", "Monday", "Tuesday", "Wednesday","Thursday","Friday","Saturday"],
				datasets: [{
					label: 'Posting Time',
					data: weekData["distribution2"],
					backgroundColor: [
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 159, 64, 1.0)',
					'rgba(100, 100, 100, 1.0)'
					],
					borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(100, 100, 100,1)'
					],
					borderWidth: 1
				}]
			},
			options : {
	            scales: {
	                xAxes: [{
	                    gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    }
	                }],
	                yAxes: [{
	                    ticks: {
							beginAtZero:true
						}
	                }]
	            },
	    		legend: {
			    	display: false
			    }
    		}
		});
	}
	req.send();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

function do_posttimes(){

	console.log("in post times");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "posttime/histogram?id=" + id);

	req.onload = function() {

		var postData = JSON.parse(this.responseText);
		
		//console.log(postData["distribution1"]);
		var ctx = document.getElementById("myChart").getContext('2d');
		var myChart = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: ["", "2", "", "4", "", "6","","8","","10","","12","","14","","16","","18","","20","","22","","24"],
				datasets: [{
					label: 'Posting Time',
					data: postData["distribution1"],
					backgroundColor: [
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 159, 64, 1.0)',
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)'
					],
					borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero:true
						}
					}],
					xAxes: [{
	                    gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    },
	                    scaleLabel: {
				            display: true,
				            labelString: 'Hours'
				        }
	                }]
				},
	    		legend: {
			    	display: false
			    }
			}
		});



		var ctx = document.getElementById("myChart2").getContext('2d');
		var myChart2 = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: ["", "2", "", "4", "", "6","","8","","10","","12","","14","","16","","18","","20","","22","","24"],
				datasets: [{
					label: 'Posting Time',
					data: postData["distribution2"],
					backgroundColor: [
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 159, 64, 1.0)',
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 159, 64, 1.0)',
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 159, 64, 1.0)',
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 159, 64, 1.0)'
					],
					borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero:true
						}
					}],
					xAxes: [{
	                    gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    },
	                    scaleLabel: {
				            display: true,
				            labelString: 'Hours'
				        }
	                }]
				},
	    		legend: {
			    	display: false
			    }
			}
		});


		var data1 = postData["distribution1"];
		var data2 = postData["distribution2"];

		var i;
		for(i = 6;i<12;i++){
			morn1 = morn1  + postData["distribution1"][i];
			morn2 = morn2  + postData["distribution2"][i];
		}
		for(i=12;i<17;i++){
			aft1 = aft1 + postData["distribution1"][i];
			aft2 = aft2  + postData["distribution2"][i];
		}
		for(i=17;i<22;i++){
			even1 = even1 + postData["distribution1"][i];
			even2 = even2  + postData["distribution2"][i];
		}
		for(i=22;i<24;i++){
			nig1 = nig1 + postData["distribution1"][i];
			nig2 = nig2  + postData["distribution2"][i];
		}
		for(i=0;i<6;i++){
			nig1 = nig1 + postData["distribution1"][i];
			nig2 = nig2  + postData["distribution2"][i];
		}

		var ctx = document.getElementById("myChart11").getContext('2d');
		var mypieChart1 = new Chart(ctx, {
			type: 'pie',
			data: {
				labels: ["Morning", "Afternoon", "Evening", "Night"],
				datasets: [{
					data: [morn1,aft1,even1,nig1],
					backgroundColor: [
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)'
					],
					borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)'
					],
					borderWidth: 1
				}]
			}
		});

		var ctx = document.getElementById("myChart22").getContext('2d');
		var mypieChart1 = new Chart(ctx, {
			type: 'pie',
			data: {
				labels: ["Morning", "Afternoon", "Evening", "Night"],
				datasets: [{
					label: 'Posting Time',
					data: [morn2,aft2,even2,nig2],
					backgroundColor: [
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)'
					],
					borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)'
					],
					borderWidth: 1
				}]
			}
		});
	}
	req.send();
}


////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

function do_likefollower(){
	console.log("in likefollower");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "ratio/like2follower?id=" + id);

	req.onload = function() {
		var likefollowData = JSON.parse(this.responseText);

		var num1 = likefollowData["ratio1"];
		num1 = parseFloat(num1).toFixed(5);

		var num2 = likefollowData["ratio2"];
		num2 = parseFloat(num2).toFixed(5);

		if(isNaN(num1))
			$('#like2follow1').text("---");
		else
			$('#like2follow1').text(num1);

		if(isNaN(num2))
			$('#like2follow2').text("---");
		else
			$('#like2follow2').text(num2);
	}
	req.send();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

function do_commentfollower(){
	console.log("in commentfollower");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "ratio/comment2follower?id=" + id);

	req.onload = function() {
		var commentfollowData = JSON.parse(this.responseText);

		var num1 = commentfollowData["ratio1"];
		num1 = parseFloat(num1).toFixed(5);

		var num2 = commentfollowData["ratio2"];
		num2 = parseFloat(num2).toFixed(5);

		if(isNaN(num1))
			$('#comment2follow1').text("---");
		else
			$('#comment2follow1').text(num1);

		if(isNaN(num2))
			$('#comment2follow2').text("---");
		else
			$('#comment2follow2').text(num2);
	}
	req.send();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

function do_hashtagpost(){
	console.log("in hashtagpost");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "hashtags/distribution?id=" + id);

	req.onload = function() {
		var hashtagPostData = JSON.parse(this.responseText);

		var sum1=0,sum2=0;
		for(var i=6;i<30;i++){
			sum1 = sum1 + hashtagPostData["distribution1"][i];
			sum2 = sum2 + hashtagPostData["distribution2"][i];
		}

		var d1 = [hashtagPostData["distribution1"][0],hashtagPostData["distribution1"][1],hashtagPostData["distribution1"][2],hashtagPostData["distribution1"][3],hashtagPostData["distribution1"][4],hashtagPostData["distribution1"][5],sum1];
		var d2 = [hashtagPostData["distribution2"][0],hashtagPostData["distribution2"][1],hashtagPostData["distribution2"][2],hashtagPostData["distribution2"][3],hashtagPostData["distribution2"][4],hashtagPostData["distribution2"][5],sum2];

		var ctx = document.getElementById("myChart14").getContext('2d');
		var mypieChart1 = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: ["0","1", "2", "3", "4", "5", ">=6"],
				datasets: [{
					label: 'Posting Time',
					data: d1,
					backgroundColor: [
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 159, 64, 1.0)',
					'rgba(100, 100, 100, 1.0)'
					],
					borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(100, 100, 100,1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero:true
						}
					}],
					xAxes: [{
	                    gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    },
	                    scaleLabel: {
				            display: true,
				            labelString: '# Hashtags'
				        }
	                }]
				},
	    		legend: {
			    	display: false
			    }
			}
		});
		
		var ctx = document.getElementById("myChart24").getContext('2d');
		var mypieChart1 = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: ["0","1", "2", "3", "4", "5", ">=6"],
				datasets: [{
					label: 'Posting Time',
					data: d2,
					backgroundColor: [
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 159, 64, 1.0)',
					'rgba(100, 100, 100, 1.0)'
					],
					borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(100, 100, 100,1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero:true
						}
					}],
					xAxes: [{
	                    gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    },
	                    scaleLabel: {
				            display: true,
				            labelString: '# Hashtags'
				        }
	                }]
				},
	    		legend: {
			    	display: false
			    }
			}
		});
	}
	req.send();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

function do_advicefollow(){
	console.log("in advicefollow");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "advice/follower?id=" + id);

	req.onload = function() {
		var advicefollow = JSON.parse(this.responseText);

		$(".advicefollow").text(advicefollow["advice"]);
	}
	req.send();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

function do_advicehash(){
	console.log("in advicehash");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "advice/hashtag?id=" + id);

	req.onload = function() {
		var advicehash = JSON.parse(this.responseText);

		$(".advicehash").text(advicehash["advice"]);
	}
	req.send();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////


function do_regularity(){
	console.log("in regularity");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "posttime/regularity?id=" + id);

	req.onload = function() {
		var regularityData = JSON.parse(this.responseText);

		var postweek1 = regularityData["postsPerWeek1"].length;
		var postweek2 = regularityData["postsPerWeek2"].length;

		var xaxis1=[],xaxis2=[];
		var yaxis1=[],yaxis2=[];

		for(var i=0;i<postweek1;i++){
			xaxis1.push(regularityData["postsPerWeek1"][i][0]);
			yaxis1.push(regularityData["postsPerWeek1"][i][1]);
		}

		for(var i=0;i<postweek2;i++){
			xaxis2.push(regularityData["postsPerWeek2"][i][0]);
			yaxis2.push(regularityData["postsPerWeek2"][i][1]);
		}

		var ctx = document.getElementById("myChart15").getContext('2d');
		var mypieChart1 = new Chart(ctx, {
			type: 'horizontalBar',
			data: {
				labels: xaxis1,
				datasets: [{
					label: 'Posting Time',
					data: yaxis1,
					backgroundColor: [
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 159, 64, 1.0)',
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					],
					borderColor: [
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
	                    scaleLabel: {
				            display: true,
				            labelString: '# Posts'
				        },
				        gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    }
					}],
					xAxes: [{
						ticks: {
							beginAtZero:true
						},
	                    scaleLabel: {
				            display: true,
				            labelString: '# Weeks'
				        }
	                }]
				},
	    		legend: {
			    	display: false
			    }
			}
		});

		var ctx = document.getElementById("myChart25").getContext('2d');
		var mypieChart2 = new Chart(ctx, {
			type: 'horizontalBar',
			data: {
				labels: xaxis2,
				datasets: [{
					label: 'Posting Time',
					data: yaxis2,
					backgroundColor: [
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)',
					'rgba(153, 102, 255, 1.0)',
					'rgba(255, 159, 64, 1.0)',
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)'
					],
					borderColor: [
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
	                    scaleLabel: {
				            display: true,
				            labelString: '# Posts'
				        },
				        gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    }
					}],
					xAxes: [{
						ticks: {
							beginAtZero:true
						},
						scaleLabel: {
				            display: true,
				            labelString: '# Weeks'
				        }
	                }]
				},
	    		legend: {
			    	display: false
			    }
			}
		});


		/* -------------------------------------------------------------  */


		var postday1 = regularityData["postsPerDay1"].length;
		var postday2 = regularityData["postsPerDay2"].length;

		var xaxis11=[],xaxis21=[];
		var yaxis11=[],yaxis21=[];

		for(var i=0;i<postday1;i++){
			xaxis11.push(regularityData["postsPerDay1"][i][0]);
			yaxis11.push(regularityData["postsPerDay1"][i][1]);
		}

		for(var i=0;i<postday2;i++){
			xaxis21.push(regularityData["postsPerDay2"][i][0]);
			yaxis21.push(regularityData["postsPerDay2"][i][1]);
		}

		var ctx = document.getElementById("myChart16").getContext('2d');
		var mypieChart11 = new Chart(ctx, {
			type: 'line',
			data: {
				labels: xaxis11,
				datasets: [{
					label: 'Posting Time',
					data: yaxis11,
					backgroundColor: [
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)'
					],
					borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero:true
						},
	                    scaleLabel: {
				            display: true,
				            labelString: '# Days'
				        }
					}],
					xAxes: [{
	                    gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    },
	                    scaleLabel: {
				            display: true,
				            labelString: '# Posts'
				        }
	                }]
				},
	    		legend: {
			    	display: false
			    }
			}
		});

		var ctx = document.getElementById("myChart26").getContext('2d');
		var mypieChart21 = new Chart(ctx, {
			type: 'line',
			data: {
				labels: xaxis21,
				datasets: [{
					label: 'Posting Time',
					data: yaxis21,
					backgroundColor: [
					'rgba(255, 99, 132, 1.0)',
					'rgba(54, 162, 235, 1.0)',
					'rgba(255, 206, 86, 1.0)',
					'rgba(75, 192, 192, 1.0)'
					],
					borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero:true
						},
	                    scaleLabel: {
				            display: true,
				            labelString: '# Days'
				        }
					}],
					xAxes: [{
	                    gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    },
	                    scaleLabel: {
				            display: true,
				            labelString: '# Posts'
				        }
	                }]
				},
	    		legend: {
			    	display: false
			    }
			}
		});
	}
	req.send();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

function do_imagevideo(){
	console.log("in imagevideo");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "ratio/video-image-avg?id=" + id);

	req.onload = function() {
		var imagevideoData = JSON.parse(this.responseText);
		
		var imagesSize1 = imagevideoData["imagesSize1"];
		var avgLikesImage1 = imagevideoData["avgLikesImage1"];
		var avgCommentsImage1 = imagevideoData["avgCommentsImage1"];
		var videosSize1 = imagevideoData["videosSize1"];
		var avgLikesVideo1 = imagevideoData["avgLikesVideo1"];
		var avgCommentsVideo1 = imagevideoData["avgCommentsVideo1"];

		var totallikes1 = avgLikesImage1 + avgLikesVideo1;
		avgLikesImage1 = 100 * avgLikesImage1/totallikes1;
		avgLikesVideo1 = 100 * avgLikesVideo1/totallikes1;

		var totalcomments1 = avgCommentsImage1 + avgCommentsVideo1;
		avgCommentsImage1 = 100 * avgCommentsImage1/totalcomments1;
		avgCommentsVideo1 = 100 * avgCommentsVideo1/totalcomments1;

		var ctx = document.getElementById("myChart17").getContext('2d');
		var myChart17 = new Chart(ctx, {
		  type: 'bar',
		  data: {
		    labels: ["# Image/Video", "Likes", "Comments"],
		    datasets: [{
		        label: 'Image',
		        data: [imagesSize1,avgLikesImage1,avgCommentsImage1],
		        backgroundColor: 'rgba(255, 99, 132, 1.0)',
		        borderColor: 'rgba(255,99,132,1)',
		        borderWidth: 2
		      },
		      {
		        label: 'Video',
		        data: [videosSize1,avgLikesVideo1,avgCommentsVideo1],
		        backgroundColor: 'rgba(255, 159, 64, 1.0)',
		        borderColor: 'rgba(255, 159, 64, 1)',
		        borderWidth: 2
		      }
		    ]
		  },
		  options: {
		    scales: {
		      yAxes: [{
		        stacked: true
		      }],
		      xAxes: [{
		        stacked: true,
		        gridLines: {
                    lineWidth: 0,
                    color: "rgba(255,255,255,0)"
                }
		      }]
		    }
		  }
		});

		/* --------------------------------------------------------------------------- */

		var imagesSize2 = imagevideoData["imagesSize2"];
		var avgLikesImage2 = imagevideoData["avgLikesImage2"];
		var avgCommentsImage2 = imagevideoData["avgCommentsImage2"];
		var videosSize2 = imagevideoData["videosSize2"];
		var avgLikesVideo2 = imagevideoData["avgLikesVideo2"];
		var avgCommentsVideo2 = imagevideoData["avgCommentsVideo2"];

		var totallikes2 = avgLikesImage2 + avgLikesVideo2;
		avgLikesImage2 = 100 * avgLikesImage2/totallikes2;
		avgLikesVideo2 = 100 * avgLikesVideo2/totallikes2;

		var totalcomments2 = avgCommentsImage2 + avgCommentsVideo2;
		avgCommentsImage2 = 100 * avgCommentsImage2/totalcomments2;
		avgCommentsVideo2 = 100 * avgCommentsVideo2/totalcomments2;

		var ctx = document.getElementById("myChart27").getContext('2d');
		var myChart27 = new Chart(ctx, {
		  type: 'bar',
		  data: {
		    labels: ["# Image/Video", "Likes", "Comments"],
		    datasets: [{
		    	label: 'Image',
		        data: [imagesSize2,avgLikesImage2,avgCommentsImage2],
		        backgroundColor: 'rgba(255, 99, 132, 1.0)',
		        borderColor: 'rgba(255,99,132,1)',
		        borderWidth: 2
		      },
		      {
		      	label: 'Video',
		        data: [videosSize2,avgLikesVideo2,avgCommentsVideo2],
		        backgroundColor: 'rgba(255, 159, 64, 1.0)',
		        borderColor: 'rgba(255, 159, 64, 1)',
		        borderWidth: 2
		      }
		    ]
		  },
		  options: {
		    scales: {
		      yAxes: [{
		        stacked: true
		      }],
		      xAxes: [{
		        stacked: true,
		        gridLines: {
                    lineWidth: 0,
                    color: "rgba(255,255,255,0)"
                }
		      }]
		    }
		  }
		});
	}
	req.send();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

function do_popular(){
	console.log("in popular posts");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "userinfo/top3-posts?id=" + id);

	req.onload = function() {
		var popoularData = JSON.parse(this.responseText);

		/*
			var d = "Mon May 7 2018 17:54:44 +0200";
			var today = new Date();
			///console.log(Date.parse(d));
			console.log(Date(today.getTime()));
			var dd = today.getDate(); 
			var mm = today.getMonth()+1; //January is 0! 
			var yyyy = today.getFullYear(); 
			if(dd<10){dd='0'+dd;} 
			if(mm<10){mm='0'+mm;} 
			var today = dd+'/'+mm+'/'+yyyy;
			//console.log(today)
		*/

		$('#image11').attr('src',popoularData["urls1"][0]);
		$('#image12').attr('src',popoularData["urls1"][1]);
		$('#image13').attr('src',popoularData["urls1"][2]);

		$('#caption11').text(popoularData["captions1"][0]);
		$('#caption12').text(popoularData["captions1"][1]);
		$('#caption13').text(popoularData["captions1"][2]);

		$('#Likes11').text(popoularData["likes1"][0]);
		$('#Likes12').text(popoularData["likes1"][1]);
		$('#Likes13').text(popoularData["likes1"][2]);

		$('#comments11').text(popoularData["comments1"][0]);
		$('#comments12').text(popoularData["comments1"][1]);
		$('#comments13').text(popoularData["comments1"][2]);

		$('#dates11').text(popoularData["dates1"][0].slice(0,-5));
		$('#dates12').text(popoularData["dates1"][1].slice(0,-5));
		$('#dates13').text(popoularData["dates1"][2].slice(0,-5));

		/* ------------------------------------------------------------------ */
		$('#image21').attr('src',popoularData["urls2"][0]);
		$('#image22').attr('src',popoularData["urls2"][1]);
		$('#image23').attr('src',popoularData["urls2"][2]);

		$('#caption21').text(popoularData["captions2"][0]);
		$('#caption22').text(popoularData["captions2"][1]);
		$('#caption23').text(popoularData["captions2"][2]);

		$('#Likes21').text(popoularData["likes2"][0]);
		$('#Likes22').text(popoularData["likes2"][1]);
		$('#Likes23').text(popoularData["likes2"][2]);

		$('#comments21').text(popoularData["comments2"][0]);
		$('#comments22').text(popoularData["comments2"][1]);
		$('#comments23').text(popoularData["comments2"][2]);

		$('#dates21').text(popoularData["dates2"][0].slice(0,-5));
		$('#dates22').text(popoularData["dates2"][1].slice(0,-5));
		$('#dates23').text(popoularData["dates2"][2].slice(0,-5));

	}
	req.send();
}

function do_likehash(){
	console.log("in likehash");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "hashtags/tags-likes-distribution?id=" + id);

	req.onload = function() {
		var likehashData = JSON.parse(this.responseText);

		//console.log(likehashData["distribution1"]);
		//console.log(likehashData["distribution1"]["Siren"]);
		var xaxis1=[],xaxis2=[];
		var yaxis1=[],yaxis2=[];
		var sum=0,sum2=0;

		for(var i in likehashData["distribution1"]){
			xaxis1.push(i);
			sum = sum + likehashData["distribution1"][i];
		}

		for(var i in likehashData["distribution2"]){
			xaxis2.push(i);
			sum2 = sum2 + likehashData["distribution2"][i];
		}

		for(var i in likehashData["distribution1"]){
			yaxis1.push(100 * likehashData["distribution1"][i]/sum);
		}

		for(var i in likehashData["distribution2"]){
			yaxis2.push(100 * likehashData["distribution2"][i]/sum2);
		}

		var ctx = document.getElementById("myChart18").getContext('2d');
		var mypieChart18 = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: xaxis1,
				datasets: [{
					label: 'hashtags',
					data: yaxis1,
					backgroundColor: [
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
					],
					borderColor: [
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero:true
						}
					}],
					xAxes: [{
	                    gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    },
	                    scaleLabel: {
				            display: true,
				            labelString: 'hashtags'
				        },
	                    ticks: {
	                    	minRotation:70,
	                    	stepSize: 1,
				            min: 0,
				            autoSkip: false
	                    }
	                }]
				},
	    		legend: {
			    	display: false
			    }
			}
		});
		
		var ctx = document.getElementById("myChart28").getContext('2d');
		var mypieChart = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: xaxis2,
				datasets: [{
					label: 'hashtags',
					data: yaxis2,
					backgroundColor: [
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
					],
					borderColor: [
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero:true
						}
					}],
					xAxes: [{
	                    gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    },
	                    scaleLabel: {
				            display: true,
				            labelString: '# Posts'
				        },
	                    ticks: {
	                    	minRotation:70,
	                    	stepSize: 1,
				            min: 0,
				            autoSkip: false
	                    }
	                }]
				},
	    		legend: {
			    	display: false
			    }
			}
		});
	}
	req.send();
}



function do_commenthash(){
	console.log("in likehash");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "hashtags/tags-comments-distribution?id=" + id);

	req.onload = function() {
		var likehashData = JSON.parse(this.responseText);

		//console.log(likehashData["distribution1"]);
		//console.log(likehashData["distribution1"]["Siren"]);
		var xaxis1=[],xaxis2=[];
		var yaxis1=[],yaxis2=[];
		var sum=0,sum2=0;

		for(var i in likehashData["distribution1"]){
			xaxis1.push(i);
			sum = sum + likehashData["distribution1"][i];
		}

		for(var i in likehashData["distribution2"]){
			xaxis2.push(i);
			sum2 = sum2 + likehashData["distribution2"][i];
		}

		for(var i in likehashData["distribution1"]){
			yaxis1.push(100 * likehashData["distribution1"][i]/sum);
		}

		for(var i in likehashData["distribution2"]){
			yaxis2.push(100 * likehashData["distribution2"][i]/sum2);
		}

		var ctx = document.getElementById("myChart19").getContext('2d');
		var mypieChart = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: xaxis1,
				datasets: [{
					
					data: yaxis1,
					backgroundColor: [
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
					],
					borderColor: [
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero:true
						}
					}],
					xAxes: [{
	                    gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)"
	                    },
	                    ticks: {
	                    	minRotation:70,
	                    	stepSize: 1,
				            min: 0,
				            autoSkip: false
	                    }
	                }]
				},
	    		legend: {
			    	display: false
			    }
			}
		});
		
		var ctx = document.getElementById("myChart29").getContext('2d');
		var mypieChart28 = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: xaxis2,
				datasets: [{
					
					data: yaxis2,
					backgroundColor: [
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
					],
					borderColor: [
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero:true,
						}
					}],
					xAxes: [{
	                    gridLines: {
	                        lineWidth: 0,
	                        color: "rgba(255,255,255,0)",
	                    },
	                    ticks: {
	                    	minRotation:70,
	                    	stepSize: 1,
				            min: 0,
				            autoSkip: false
	                    }
	                }]
				},
	    		legend: {
			    	display: false
			    }
			}
		});
	}
	req.send();
}

function do_week(){
	console.log("in advice week");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "advice/week?id=" + id);

	req.onload = function() {
		var userData = JSON.parse(this.responseText);			

		$('.adviceweek').text(userData["advice"]);

	}

	req.send();
}

function do_advicepostingtime(){
	console.log("in advice posting time");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "advice/postingTime/?id=" + id);

	req.onload = function() {
		var userData = JSON.parse(this.responseText);			

		$('.adviceDistribution').text(userData["advice"]);

	}

	req.send();
}


function do_advicepostperweek(){
	console.log("in advice posting time per week");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "advice/postsPerWeek/?id=" + id);

	req.onload = function() {
		var userData = JSON.parse(this.responseText);			

		$('.advicePostWeek').text(userData["advice"]);

	}

	req.send();
}


function do_advicehashtagsPerPost(){
	console.log("in advice posting time per week");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "advice/hashtagsPerPost/?id=" + id);

	req.onload = function() {
		var userData = JSON.parse(this.responseText);			

		$('.advicehashpost').text(userData["advice"]);

	}

	req.send();
}

function do_adviceimageVideo(){
	console.log("in advice imagevideo");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "advice/imageVideo/?id=" + id);

	req.onload = function() {
		var userData = JSON.parse(this.responseText);			

		$('.adviceimagevideo').text(userData["advice"]);

	}

	req.send();
}


function do_advicelikehash(){
	console.log("in advice likehash");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "advice/likesHashtags/?id=" + id);

	req.onload = function() {
		var userData = JSON.parse(this.responseText);			

		$('.advicelikehash').text(userData["advice"]);

	}

	req.send();
}


function do_advicecommenthash(){
	console.log("in advice commenthash");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "advice/commentsHashtags/?id=" + id);

	req.onload = function() {
		var userData = JSON.parse(this.responseText);			

		$('.advicecommenthash').text(userData["advice"]);

	}

	req.send();
}

function do_advicepostsPerDay(){
	console.log("in advice posts per day");
	var req = new XMLHttpRequest();
	req.open("GET", base_url + "advice/postsPerDay/?id=" + id);

	req.onload = function() {
		var userData = JSON.parse(this.responseText);			

		$('.advicePostDay').text(userData["advice"]);

	}

	req.send();
}