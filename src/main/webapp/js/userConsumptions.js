/**
 * 
 */

$(document)
		.ready(
				function() {
					
					if(sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
						$("#communityNameAdd").val(sessionStorage.getItem("communityName"));
						$("#formcommunityNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
						$("#blockNameAdd").val(sessionStorage.getItem("blockName"));
						$("#formblockNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
					}
					if(sessionStorage.getItem("roleID") == 3){
						$("#selectHouseBasedonBlock").val(sessionStorage.getItem("userID"));
					}
					
					$("#userconsumption")
							.click(
									function() {

										
										if(sessionStorage.getItem("roleID") != 3){
										
										var selectcommunityName = $(
												"#selectcommunityName")
												.val();

										//alert($("#selectBlockBasedonCommunity").val());
										
										if($("#reportType").val() == "-1"){
											bootbox
											.alert("Please select type");
											return false;
										}
										
										if ($("#selectcommunityName").val() == "-1") {
											
											bootbox
											.alert("Select community name");
											return false;
										}

										if ($("#selectBlockBasedonCommunity").val() == "null" || $("#selectBlockBasedonCommunity").val() == "Select Block") {

											bootbox
											.alert("Select Block Name");
											return false;
										}
										
										if ($("#selectHouseBasedonBlock").val() == "null" || $("#selectHouseBasedonBlock").val() == "Select CRN") {

											bootbox
											.alert("Select CRN Number");
											return false;
										}
										}
										
										
										if($("#reportType").val()=="Graph"){
											if ($("#fromMonth_topup").val() == "null" || $("#fromMonth_topup").val() == "Select Month") {
												bootbox
												.alert("Select from month");
												return false;
											}
											
											if ($("#toMonth_topup").val() == "null" || $("#toMonth_topup").val() == "Select Month") {
												bootbox
												.alert("Select to month");
												return false;
											}
										}else if($("#reportType").val()=="Tabular"){
											if ($("#start_date").val() == "null" || $("#start_date").val() == "") {

												bootbox
												.alert("Select Start Date");
												return false;
											}
											
											if ($("#end_date").val() == "null" || $("#end_date").val() == "") {

												bootbox
												.alert("Select End Date");
												return false;
											}
										}

										
										
										
										var data1 = {}
										
										data1["customerUniqueID"] = $(
												"#selectHouseBasedonBlock")
												.val();
										
											data1["fromDate"] = $("#start_date")
											.val();
											data1["toDate"] = $("#end_date").val();
										
										
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./userconsumptionreports/Tabular",
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(d) {
														if($.fn.DataTable.isDataTable("#userConsumptionsTable_wrapper")){
															$('#userConsumptionsTable_wrapper').DataTable().clear();
															$('#userConsumptionsTable').DataTable().destroy();
														}
														$('#userConsumptionsTable_wrapper thead').empty();
														$('#userConsumptionsTable_wrapper tbody').remove();
														$("#theadBody").append("<tr><th>Customer Unique ID</th><th>MIU ID</th><th>Reading1</th><th>Reading2</th>" +
																"<th>Battery</th><th>Consumption</th><th>Balance</th><th>Tariff</th><th>Alarm Credit</th>" +
																"<th>Emergency Credit</th><th>Date Time</th></tr>")

														
														
														
														
														 table = $('#userConsumptionsTable').DataTable(
																	{
																		"dom": "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6 totalCount'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
																		"responsive" : true,
																		"serverSide" : false,
																		"bDestroy" : true,
																		"bPaginate": true,
																		"pagging" : true,
																		"bProcessing" : true,
																		"ordering" : true,
																		"order" : [ 0, "desc" ],
																		"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
																		"pageLength" : 25,
																		"scrollX" : false,
																		"data" : d.data,
																		"columns" : [ {
																			"data" : "customerUniqueID"
																		}, {
																			"data" : "miuID"
																		}, {
																			"data" : "reading1"
																		}, {
																			"data" : "reading2"
																		}, {
																			"data" : "battery"
																		}, {
																			"data" : "consumption"
																		}, {
																			"data" : "balance"
																		}, {
																			"data" : "tariff"
																		}, {
																			"data" : "alarmCredit"
																		}, {
																			"data" : "emergencyCredit"
																		}, {
																			"data" : "dateTime"
																		}],
																		"columnDefs" : [  {
																			//orderable : false,
																			targets : [0], visible: false
																			
																		},
																		{
																			"className": "dt-center", "targets": "_all"
																		}], "buttons": [
																			   /* 'csvHtml5',
																			'excelHtml5',
																		'pdfHtml5'*/
																			
																			{
																				extend: 'excel',
																		        footer: 'true',
																		        //text: 'Excel',
																		        //className: 'custom-btn fa fa-file-excel-o',
																		        title:'User Consumption Report'  
																		        	},
																		         
																		        {
																		        extend: 'pdf',
																		        footer: 'true',
																		        //className: 'custom-btn fa fa-file-pdf-o',
																		        exportOptions: {
																		            columns: [0,1,2,3,4,5,6,7,8]
																		        },
																		        //text: 'pdf',
																		        orientation: 'landscape',
																		        title:'User Consumption Report'  }
																		],
																		 initComplete: function() {
																			   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
																			   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
																			  }
																	});
														 $("div.headname").html('<h3>User Consumptions</h3>');
														 var template = `<b>CAN:</b><span>`+d.data[0].customerUniqueID+`</span><b>&nbsp&nbspNo. of meters: </b>`+d.sizeMeter;
														 $(".totalCount")
															.html(template);
														
													}
												});
										return false;
									});
				});

$(document).ready(
		function() {
			$("#back")
			.click(
					function() {
		
						window.location = "userConsumptions.jsp";
						return false
						
					});
		});


function showReportType(id){
	if(id=="Graph"){
		$("#divEndDate").hide();
		$("#divStartDate").hide();
		$("#fromMonth").show();
		$("#toMonth").show();
		$("#year").show();
		
	}else if(id=="Tabular"){
		$("#divEndDate").show();
		$("#divStartDate").show();
		$("#fromMonth").hide();
		$("#toMonth").hide();
		$("#year").hide();
	}
	
	
}