/**
 * 
 */
$(document)
		.ready(
				function() {
					
					$("#highchart_container2").hide();

  if(sessionStorage.getItem("roleID") != 3){

	  var urlgas = "./homedashboard/Gas/"+sessionStorage.getItem("roleID");
	  var urlwater = "./homedashboard/water/"+sessionStorage.getItem("roleID");
	  var url1 = parseInt(sessionStorage.getItem("roleID"))==1||parseInt(sessionStorage.getItem("roleID"))==4?$("#comName").val():sessionStorage.getItem("ID");
	  
	  $.getJSON(urlgas+"/"+url1, function(data) {
			//var Options = "";
  	  document.querySelector("#gasActive").innerText = data.active;
  	  document.querySelector("#gasInactive").innerText = data.inActive;
  	  document.querySelector("#gasLive").innerText = data.live;
  	  document.querySelector("#gasnonLive").innerText = data.nonLive;
  	  document.querySelector("#gasemergency").innerText = data.emergency;
  	  document.querySelector("#gasLowbattery").innerText = data.lowBattery;
  	//  document.querySelector("#gasActivePercentage").innerText = data.activePercentage;
  //	  document.querySelector("#gasinactivePercentage").innerText = data.inActivePercentage;
			
		});
	  
	  
	  
	  $.getJSON(urlwater+"/"+url1, function(data) {
			//var Options = "";
		  document.querySelector("#waterActive").innerText = data.active;
	  	  document.querySelector("#waterInactive").innerText = data.inActive;
	  	  document.querySelector("#waterLive").innerText = data.live;
	  	  document.querySelector("#waternonLive").innerText = data.nonLive;
	  	  document.querySelector("#wateremergency").innerText = data.emergency;
	  	  document.querySelector("#waterLowbattery").innerText = data.lowBattery;
	  //	  document.querySelector("#waterActivePercentage").innerText = data.activePercentage;
	  //	  document.querySelector("#waterinactivePercentage").innerText = data.inActivePercentage;
			
		});
		var comUrl = ($("#comName").val() ==undefined || $("#comName").val()==null)?sessionStorage
				.getItem(
						"communityName"):$("#comName").val();
						
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "./graph/Gas/0/0/"+ comUrl,
			dataType : "JSON",

			success : function(d) {
				$("#gasLoader").hide();
				$('#container').highcharts(
						{
							chart: {
		        		        type: 'column'
		        		    },
							title : {
								text : ''
							},
		        		    xAxis: {
		        		        categories: d.xAxis
		        		    },
		        		    plotOptions: {
		        	            series: {
		        	                cursor: 'pointer',
		        	                pointWidth: 20,
		        	                point: {
		        	                    events: {
		        	                        click: function () {
		        	                           // alert('Category: ' + this.category + ', value: ' + this.y);
		        	                            //window.location="./dashboard/Gas/"+comUrl+"/"+this.category+"/"+0+"/"+0;
		        	                        	sessionStorage.removeItem("filterId");
		        	                            window.location = "customerDashboard.jsp?com="+comUrl+"&block="+this.category+"&type=Gas";
		        	                        }
		        	                    }
		        	                }
		        	            }
		        	        },

		        		    series: [{
		        		        data: d.yAxis,
		        		        name: 'Consumption'
		        		    }]

						});
			}
		});
		
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "./graph/Water/0/0/"+ $("#comName").val(),
			dataType : "JSON",

			success : function(d) {
				$("#waterLoader").hide();
				$('#container1').highcharts(
						{
							chart: {
		        		        type: 'column'
		        		    },
							title : {
								text : ''
							},
		        		    xAxis: {
		        		        categories: d.xAxis
		        		    },

		        		    plotOptions: {
		        	            series: {
		        	                cursor: 'pointer',
		        	                pointWidth: 20,
		        	                point: {
		        	                    events: {
		        	                        click: function () {
		        	                            //alert('Category: ' + this.category + ', value: ' + this.y);
		        	                           // window.location="./dashboard/water/"+comUrl+"/"+this.category+"/"+0+"/"+0;
		        	                        	sessionStorage.removeItem("filterId");
		        	                            window.location = "customerDashboard.jsp?com="+comUrl+"&block="+this.category+"&type=water";
		        	                        }
		        	                    }
		        	                }
		        	            }
		        	        },

		        		    series: [{
		        		        data: d.yAxis,
		        		        name: 'Consumption'
		        		    }]

						});
			}
		});
		
		
		}
 });