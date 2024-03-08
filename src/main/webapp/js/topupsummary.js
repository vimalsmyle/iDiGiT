/**
 * 
 */

$(document).ready(function() {
	if(sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
		
		//if(sessionStorage.getItem("roleID") == 2){
			$("#communityNameAdd").val(sessionStorage.getItem("communityName"));
			$("#formcommunityNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
			$("#blockNameAdd").val(sessionStorage.getItem("blockName"));
			$("#formblockNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		//}
		
		
		document.querySelector(".blockimp").innerText ="*";
	}
	});
$(document)
		.ready(
				function() {
					$("#topupSummary")
							.click(
									function() {

										var data1 = {}
										
										var selectcommunityName = $(
												"#selectcommunityName")
												.val();
										if ($("#selectcommunityName").val() == "-1") {
											
											bootbox
											.alert("Select Community Id");
											return false;
										}

										if(sessionStorage.getItem("roleID") == 1 || sessionStorage.getItem("roleID") == 4){
										
											data1["communityID"] = $("#selectcommunityName").val();
											
										if ($("#selectBlockBasedonCommunity").val() == "null" || $("#selectBlockBasedonCommunity").val() != "Select Block") {

											data1["blockID"] = $(
											"#selectBlockBasedonCommunity").val();
										}else {
											data1["blockID"] = "-1";
										}
										} else if(sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
											
											data1["blockID"] = sessionStorage.getItem("ID");
											
											data1["communityID"] = sessionStorage.getItem("communityID");
											
										}
										
										if ($("#selectHouseBasedonBlock").val() == "null" || $("#selectHouseBasedonBlock").val() != "Select CRN") {

											data1["customerUniqueID"] = $(
											"#selectHouseBasedonBlock")
											.val();
										}else {
											data1["customerUniqueID"] = "";
										}


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
										
										
										data1["fromDate"] = $("#start_date")
												.val();
										data1["toDate"] = $("#end_date").val();

										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./topupsummary",
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(d) {
														if($.fn.DataTable.isDataTable("#topupsummaryTable_wrapper")){
															$('#topupsummaryTable_wrapper').DataTable().clear();
															$('#topupsummaryTable').DataTable().destroy();
														}
														$('#topupsummaryTable_wrapper thead').empty();
														$('#topupsummaryTable_wrapper tbody').remove();
														
														
														$("#theadBody").append("<tr><th>Customer Unique ID</th><th>MIU ID</th><th>First Name</th><th>Last Name</th><th>Recharge Amount</th><th>status</th>" +
														"<th>Mode Of Payment</th><th>Razor Pay RefundID</th><th>Razor Pay Refund Status</th>" +
														"<th>Payment Status</th><th>Transacted By UserName</th><th>Transacted By Role Description</th><th>Date Time</th><th>Action</th></tr>")


														 table = $('#topupsummaryTable').DataTable(
																	{
																		"dom": "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
																		"responsive" : true,
																		/*"processing" : true,*/
																		"serverSide" : false,
																		"bDestroy" : true,
																		"bPaginate": true,
																		"pagging" : true,
																		"bProcessing" : true,
																		"ordering" : true,
																		"order" : [ 0, "desc" ],
																		"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
																		"pageLength" : 25,
																		"scrollX" : true,
																		"data" : d.data,
																		"columns" : [
																			{
																			"data" : "customerUniqueID",
																			"defaultContent": ""
																			},{
																			"data" : "miuID",
																			"defaultContent": ""
																			},{
																			"data" : "firstName",
																			"defaultContent": ""
																			},{
																			"data" : "lastName",
																			"defaultContent": ""
																			},{
																				"data" : "rechargeAmount",
																				"defaultContent": ""
																				},{
																					"data" : "status",
																					"defaultContent": ""
																					},{
																			"data" : "modeOfPayment",
																			"defaultContent": ""
																			},{
																				"data" : "razorPayRefundID",
																				"defaultContent": ""
																				},{
																					"data" : "RazorPayRefundStatus",
																					"defaultContent": ""
																					}
																					,{
																						"data" : "paymentStatus",
																						"defaultContent": ""
																						}
																					
																					
																			,{
																			"data" : "transactedByUserName",
																			"defaultContent": ""
																			},{
																			"data" : "transactedByRoleDescription",
																			"defaultContent": ""
																			},{
																				"data" : "dateTime",
																				"defaultContent": ""
																				},{
																				"mData" : "action",
																				"render" : function(data, type, row) {
																					if(row.status == "Failed"){
																						return "<a onclick='getDeleteTransactionID("
																						+ row.transactionID
																						+ ")'>"
																						+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>delete</i>"
																						+ "</a>";
																						
																					}else if(row.status == "Passed" || row.status == "Pending"){
																						return "<a onclick='getReceiptTransactionID("
																						+ row.transactionID
																						+ ")'>"
																						+"<i class='material-icons' style='color:#17e9e9;cursor:pointer'>receipt</i>"
																						+ "</a>"
																					}else if( row.status == "Pending...waiting for acknowledge"){
																						return "---"
																					}
																																		
																				}
																				}],
																		"columnDefs" : [ {
																		"className": "dt-center", "targets": "_all"
																		}], "buttons": [
																			   /* 'csvHtml5',
																			'excelHtml5',
																		'pdfHtml5'*/
																			
																			{
																				extend: 'excel',
																				//className: 'custom-btn fa fa-file-excel-o',
																		        footer: 'true',
																		        //text: 'Excel',
																		        title:'ReCharge Summary'  },
																		         
																		        {
																		        extend: 'pdf',
																		        footer: 'true',
																		        //className: 'custom-btn fa fa-file-pdf-o',
																		        exportOptions: {
																		            columns: [0,1,2,3,4,5,6,7,8,9,10,11,12]
																		        },
																		        //text: 'pdf',
																		        orientation: 'landscape',
																		        title:'ReCharge Summary'  }
																		],
																		 initComplete: function() {
																			   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
																			   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
																			  }
																	});

														 $("div.headname").html('<h3>ReCharge Summary</h3>');
															//table.ajax.reload()
														 
													}
												});
										return false;
									});
				});

function getReceiptTransactionID(transID){
	bootbox
	.confirm("ARE YOU SURE TO DOWNLOAD RECEIPT",
		function(
			result) {
			//	alert(result);
//				window.open("/PAYGTL_LORA_BLE/status/print/" + transID);
				window.open("http://183.82.122.196:8080/PAYGTL_LORA_BLE/status/print/" + transID);
				/*if(result == true){
					$.ajax({
						type : "GET",
						contentType : "application/json",
						url : "/PAYGTL_LORA_BLE/status/print/" + transID,
						dataType : "JSON",
						success : function(data) {
							//alert("Success====" + data.result);
							if (data.result == "Success") {
								bootbox
								.confirm(
										data.Message,
									function(
										result) {
										window.location = "topupStatus.jsp";
									});

							} else {
								bootbox
								.alert(data.Message);
								return false;
							}
						}
					});
				}else if(result==false){
					//alert("@"+false)
					
				}*/
		});
}