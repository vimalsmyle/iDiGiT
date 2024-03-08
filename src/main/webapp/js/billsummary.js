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
					$("#billSummary")
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


										if ($("#fromMonth_topup").val() == "null" || $("#fromMonth_topup").val() == "-1") {

											bootbox
											.alert("Select from month");
											return false;
										}
										
										if ($("#toMonth_topup").val() == "null" || $("#toMonth_topup").val() == "-1") {

											bootbox
											.alert("Select to month");
											return false;
										}
										
										
										data1["fromMonth"] = $("#fromMonth_topup")
												.val();
										data1["toMonth"] = $("#toMonth_topup").val();

										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./billpaymentsummary",
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(d) {
														if($.fn.DataTable.isDataTable("#billsummaryTable_wrapper")){
															$('#billsummaryTable_wrapper').DataTable().clear();
															$('#billsummaryTable').DataTable().destroy();
														}
														$('#billsummaryTable_wrapper thead').empty();
														$('#billsummaryTable_wrapper tbody').remove();
														
														
														$("#theadBody").append("<tr><th>Customer Unique ID</th><th>First Name</th><th>Last Name</th><th>Bill Amount</th>" +
														"<th>Mode Of Payment</th><th>Razorpay order ID</th><th>Razorpay payment ID</th><th>Razor Pay RefundID</th><th>Razor Pay Refund Status</th>" +
														"<th>Payment Status</th><th>Transacted By UserName</th><th>Transacted By Role Description</th><th>Billing date</th><th>payment date</th></tr>")


														 table = $('#billsummaryTable').DataTable(
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
																			"data" : "firstName",
																			"defaultContent": ""
																			},{
																			"data" : "lastName",
																			"defaultContent": ""
																			},{
																				"data" : "billAmount",
																				"defaultContent": ""
																				},{
																			"data" : "modeOfPayment",
																			"defaultContent": ""
																			},{
																				"data" : "razorPayOrderID",
																				"defaultContent": ""
																				},{
																					"data" : "razorPayPaymentID",
																					"defaultContent": ""
																					}
																				
																				,{
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
																				"data" : "billingDate",
																				"defaultContent": ""
																				},{
																					"data" : "paymentDate",
																					"defaultContent": ""
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
																		        title:'Bill Summary'  },
																		         
																		        {
																		        extend: 'pdf',
																		        footer: 'true',
																		        //className: 'custom-btn fa fa-file-pdf-o',
																		        exportOptions: {
																		            columns: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14]
																		        },
																		        //text: 'pdf',
																		        orientation: 'landscape',
																		        title:'Bill Summary'  }
																		],
																		 initComplete: function() {
																			   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
																			   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
																			  }
																	});

														 $("div.headname").html('<h3>Bill Summary</h3>');
															//table.ajax.reload()
														 
													}
												});
										return false;
									});
				});
