/**
 * 
 */

$(document).ready(function () {
	 $('.select2').select2();
    	var flag =true;
    /*  $('.arrowreport').click(function () {
        $('.left ').toggleClass('fliph');
		flag = !flag;
		if(flag == true){
			document.querySelector('.left_nav').className="left_nav col-md-2 pl-0 pr-0";
			document.querySelector('.right_data').className="right_data col-md-10 mt-4 mb-4";
		}else if(flag == false){
			document.querySelector('.left_nav').className="left_nav col-md-1 pl-0 pr-0";	
			document.querySelector('.right_data').className="right_data col-md-10 mt-4 mb-4";
			var oTable = $("#liveTable").dataTable();
			
		}
      });*/
      var pageURL = $(location). attr("href");
		//alert(pageURL.split('LORA_BLE/')[1]);
		let  url = pageURL.split('IDigi/')[1].split("?")[0] =="LoginAction.jsp"?"home.jsp":pageURL.split('IDigi/')[1];
	/*	document.querySelector("a[href='"+url+"']").className = "active";
		*/
		
		$(".resetFilter")
		.click(
				function() {
					 //$("input:text").val("");
					    $('input:text').not(':disabled').val('');
					    
					    $('.btn .btn-secondary .submit-button').attr('disabled',
								true);

				});	
		
    });



$(window).on('load', function() { 
	  $('#status').fadeOut(); 
	  $('#preloader').delay(0).fadeOut('slow'); 
	  $('body').delay(0).css({'overflow':'visible'});
	})


function returnBack(){
	window.location.reload();
}

function redirection(obj){
	
	if(obj == "day"){
		sessionStorage.setItem("day",obj);
		window.location = "topupStatus.jsp";
	}
	else{
		sessionStorage.setItem("filterId",obj);
		window.location = "LiveDashBoard.jsp";
	}
	
}
$(document).on('click', '#mainSidebarToggle' ,function(event) {
	event.preventDefault();
	if (window.matchMedia('(min-width: 300px)').matches) {
		$('body').toggleClass('main-sidebar-hide');
		$('#communityTable').DataTable();
	} else {
		$('body').toggleClass('main-sidebar-show');
		$('#communityTable').DataTable();
	}
	

	

});

function returnBack(){
	window.location.reload();
}

function redirection(obj,type){
	
	if(obj == "day"){
		sessionStorage.setItem("day",obj);
		window.location = "topupStatus.jsp";
	}
	else{
		sessionStorage.removeItem("filterId");
		sessionStorage.setItem("filterId",obj);
		
		if (sessionStorage
				.getItem(
				"roleID") == 2 || sessionStorage
				.getItem(
				"roleID")==5) {
			var comm = sessionStorage
			.getItem(
					"communityName");
			
			var block = sessionStorage
			.getItem(
					"blockName");
			
		}else{
			var comm=$("#comName").val()==undefined?"0":$("#comName").val();
			var block = $("#blockName").val()==undefined?"0":$("#blockName").val();
		}
		
		
		
		window.location = "customerDashboard.jsp?com="+comm+"&block="+block+"&type="+type;
	}
	
}

function dashboardAll(type){
	if (sessionStorage
			.getItem(
			"roleID") == 2) {
		var comm = sessionStorage
		.getItem(
				"communityName");
		
		var block = sessionStorage
		.getItem(
		"blockName");
	}else{
		var comm=$("#comName").val()==undefined?"0":$("#comName").val()
				var block = $("#blockName").val()==undefined?"0":$("#blockName").val();
	}
	
	
	window.location = "customerDashboard.jsp?com="+comm+"&block="+block+"&type="+type;
}


$(document)
.ready(
		function() {
			$("#manualBilling")
					.click(
							function() {
								
								$("#loader").show();
								
								$
										.ajax({
											type : "GET",
											contentType : "application/json",
											url : "./manualbillgeneration",
											dataType : "JSON",

											success : function(data) {
												$("#loader").hide();
												if (data.result == "Success") {
													swal.fire({
														  title: "Saved",
														  text: data.Message,
														  icon: "success"
														}).then(function() {
														    window.location = "manualBilling.jsp";
														    
														});
													return false;
												}
											}
										});
								return false;
							});
		});



