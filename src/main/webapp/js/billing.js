
$(document)
		.ready(
				function() {

					if (sessionStorage.getItem("roleID") == 2
							|| sessionStorage.getItem("roleID") == 5) {
						$("#communityNameAdd").val(
								sessionStorage.getItem("communityName"));
						$("#formcommunityNameAdd")
								.addClass(
										"group form-group has-feedback has-success bmd-form-group is-filled")
						$("#blockNameAdd").val(
								sessionStorage.getItem("blockName"));
						$("#formblockNameAdd")
								.addClass(
										"group form-group has-feedback has-success bmd-form-group is-filled")
					}

					if (sessionStorage.getItem("roleID") == 1
							|| sessionStorage.getItem("roleID") == 2) {
						$("#blockAddButton").show();
						var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
								+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>"
								+ "<'row'<'col-sm-12'tr>>"
								+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					} else if (sessionStorage.getItem("roleID") == 3) {
						var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
								+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-5 total'>>"
								+ "<'row'<'col-sm-12'tr>>"
								+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					} else {
						$("#customerAddd").remove();
						var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
								+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>"
								+ "<'row'<'col-sm-12'tr>>"
								+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					}

					var filterId = sessionStorage.getItem("day") == "day" ? "1"
							: "-1";
					sessionStorage.removeItem("day")
					if( sessionStorage
							.getItem("roleID")){
						var ID =  sessionStorage
							.getItem("CustomerUniqueID");
					}else{
						var ID =  sessionStorage
						.getItem("ID");
					}
					
					$('#billingstatusTable1').hide();
					var responseD;
					table = $('#billingstatusTable')
							.DataTable(
									{
										"dom" : dom1,
										"responsive" : true,
										/* "processing" : true, */
										"serverSide" : false,
										"bDestroy" : true,
										"bPaginate" : true,
										"pagging" : true,
										"bProcessing" : true,
										"ordering" : true,
										"order" : [ 0, "desc" ],
										"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
										"pageLength" : 25,
										"scrollX" : true,
										"ajax" : {
											"url" : "./billing/"
													+ sessionStorage
															.getItem("roleID")
													+ "/"
													+ ID
													+ "/" + filterId,
											"type" : "GET",
											"data" : function(search) {
											},
											"complete" : function(json) {
												console.log(json);
												responseD = json.responseJSON;
												return json.data;
											},
										},
										"columns" : [
												{
													"data" : "communityName",
													"defaultContent" : ""
												},
												{
													"data" : "blockName",
													"defaultContent" : ""
												},
												{
													"data" : "houseNumber",
													"defaultContent" : ""
												},
												{
													"data" : "totalConsumption",
													"defaultContent" : ""
												},
												{
													"data" : "totalAmount",
													"defaultContent" : ""
												},
												{
													"mData" : "action",
													"render" : function(data, type, row) {
														
														return "<a href=# id=CustomerMeters data-toggle=modal data-target=#myCustomerMeters onclick='getCustomerMeters(\""
														+ row.customerBillingID
														+ "\")'>"
														+ "Multiple"
														+ "</a>"
														
													}

												},
												{
													"data" : "transactedBy",
													"defaultContent" : ""
												},
												{
													"data" : "modeOfPayment",
													"defaultContent" : ""
												},
												{
													"data" : "paidDate",
													"defaultContent" : ""
												},
												{
													"data" : "billMonth",
													"defaultContent" : ""
												},
												{
													"data" : "billYear",
													"defaultContent" : ""
												},
												{
													"data" : "logDate",
													"defaultContent" : ""
												},
												{
													"data" : "status",
													"defaultContent" : ""
												},
												{
													"mData" : "action",
													"render" : function(data,
															type, row) {
														 if (row.status == "Paid"
																) {
															return "<a onclick='getReceiptTransactionID("
																	+ row.customerBillingID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>receipt</i>"
																	+ "</a>"
														} else if (row.status == "Pending") {
															return "<a onclick='getPayBillTransactionID(\""
															+ row.customerUniqueID
															+ "\")'>"
															+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>receipt</i>"
															+ "</a>"
														}

													}
												} ],
										"columnDefs" : [
												{
													// orderable : false,
													targets : (sessionStorage
															.getItem("roleID") == 3) ? [
															0, 1, 2, 3 ]
															: 11,
													visible : ((sessionStorage
															.getItem("roleID") == 3)) ? false
															: (((sessionStorage
																	.getItem("roleID") == 1)
																	|| (sessionStorage
																			.getItem("roleID") == 2) || (sessionStorage
																	.getItem("roleID") == 3)) && (!(sessionStorage
																	.getItem("roleID") == 5) || !(sessionStorage
																	.getItem("roleID") == 4)))

												}, {
													"className" : "dt-center",
													"targets" : "_all"
												} ],
										"buttons" : [
											{
												extend : 'excel',
												footer : 'true',
												// text : 'Excel',
												title : 'Billing',
											// className: 'custom-btn fa
											// fa-file-excel-o'
												
												action:function(e,dt,node,config){
													executeDownloadDashboard(responseD);
												}
												
											}/*,
												{

													className : 'customButton',
													text : "Filter",
													action : function(e, dt,
															button, config) {
														$('.customButton')
																.attr(
																		{
																			"data-toggle" : "modal",
																			"data-target" : "#filter"
																		});
													}
												}*/ ],
												 initComplete: function() {
													   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
													   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
													  }
									});

					if (sessionStorage.getItem("roleID") == 3
							|| sessionStorage.getItem("roleID") == 2
							|| sessionStorage.getItem("roleID") == 5) {
						if(sessionStorage.getItem("roleID") == 3){
							
						}else{
						table.buttons($('a.customButton')).remove();
					}}
					$("div.headname").html('<h3>Billing Status</h3>');
					// $("div.total").html('MUI ID: '+data.meterID+ ' Total
					// Units: '+data.meterID);

					$("#customerFilter")
							.click(
									function() {

										var url = $(
												"#filterselectcommunityName")
												.val() == "-1" ? sessionStorage
												.getItem("roleID")
												+ "/0/-1/0"
												: $(
														"#filterselectBlockBasedonCommunity")
														.val() == "Select Block" ? $(
														"#filterselectcommunityName")
														.val() == "-1" ? sessionStorage
														.getItem("roleID")
														+ "/0/-1/-1"
														: sessionStorage
																.getItem("roleID")
																+ "/0/"
																+ $(
																		"#filterselectcommunityName")
																		.val()
																+ "/0"
														: "2/"
																+ $(
																		"#filterselectBlockBasedonCommunity")
																		.val()
																+ "/-1/0"

										$
												.ajax({
													type : "GET",
													contentType : "application/json",
													url : "./billing/"
															+ url,
													dataType : "JSON",

													success : function(d) {

														$('#billingstatusTable')
																.dataTable()
																._fnAjaxUpdate();
														$(
																"#billingstatusTable_wrapper")
																.hide();
														$("#filter").modal(
																"hide");
														$("#billingstatusTable1")
																.show();
														var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
																+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>"
																+ "<'row'<'col-sm-12'tr>>"
																+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
														var hCols = [ 3, 4 ];
														var responseDD=d.data;
														table = $(
																'#billingstatusTable1')
																.DataTable(
																		{

																			"dom" : dom1,
																			"responsive" : true,
																			/*
																			 * "processing" :
																			 * true,
																			 */
																			"serverSide" : false,
																			"bDestroy" : true,
																			"bPaginate" : true,
																			"pagging" : true,
																			"bProcessing" : true,
																			"ordering" : true,
																			"order" : [
																					0,
																					"desc" ],
																			"lengthMenu" : [
																					5,
																					10,
																					25,
																					30,
																					50,
																					75 ],
																			"pageLength" : 25,
																			"scrollY" : 324,
																			"scrollX" : false,
																			"data" : d.data,
																			"columns" : [
																					{
																						"data" : "communityName",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "blockName",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "houseNumber",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "meterID",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "amount",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "emergencyCredit",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "alarmCredit",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "modeOfPayment",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "razorPayOrderID",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "razorPayPaymentID",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "razorPayRefundID",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "RazorPayRefundStatus",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "transactionDate",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "transactedByUserName",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "transactedByRoleDescription",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "RazorPayPaymentStatus",
																						"defaultContent" : ""
																					},
																					{
																						"mData" : "action",
																						"render" : function(
																								data,
																								type,
																								row) {
																							 if (row.Status == "Paid"
																									) {
																								return "<a onclick='getReceiptTransactionID("
																										+ row.transactionID
																										+ ")'>"
																										+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>receipt</i>"
																										+ "</a>"
																							} else if (row.status == "Pending") {
																								return "<a onclick='getPayBillTransactionID(\""
																		+ row.customerUniqueID
																		+ "\")'>"
																								+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>receipt</i>"
																								+ "</a>"
																							}

																						}
																					} ],
																			"columnDefs" : [
																					{
																						// orderable
																						// :
																						// false,
																						targets : 11,
																						visible : (((sessionStorage
																								.getItem("roleID") == 1)
																								|| (sessionStorage
																										.getItem("roleID") == 2) || (sessionStorage
																								.getItem("roleID") == 3)) && (!(sessionStorage
																								.getItem("roleID") == 5) || !(sessionStorage
																								.getItem("roleID") == 4)))
																					},
																					{
																						"className" : "dt-center",
																						"targets" : "_all"
																					} ],
																			"buttons" : [
																				{
																					extend : 'excel',
																					footer : 'true',
																					// text : 'Excel',
																					title : 'Billing',
																				// className: 'custom-btn fa
																				// fa-file-excel-o'
																					
																					action:function(e,dt,node,config){
																						executeDownloadDashboard(responseDD);
																					}
																					
																				},
																					{
																						text : 'Reset',
																						action : function(
																								e,
																								dt,
																								node,
																								config) {
																							alert('Button activated');
																						},
																						className : 'customButton',

																						action : function(
																								e,
																								dt,
																								button,
																								config) {

																							window.location = "billingDetails.jsp"
																						}
																					} ],
																					 initComplete: function() {
																						   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
																						   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
																						  }
																		});
														if (sessionStorage
																.getItem("roleID") == 3) {
															table
																	.buttons(
																			$('a.customButton'))
																	.remove();
														}
														$("div.headname")
																.html(
																		'<h3>Billing Status</h3>');
													}
												});
										return false;
									});

					$("#resetFilter").on(function() {
						$("input:text").val("");
					});
				});





function getCustomerMeters(customerBillingID){
	var filterId = sessionStorage.getItem("day") == "day" ? "1"
			: "-1";
	$.getJSON("./billing/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/"+filterId, function(data) {
		console.log(data.data)
		$.each(data.data, function(i, item) {
			if (customerBillingID == item.customerBillingID) {
				
				$('#customerMeterTable')
				.DataTable(
						{
							"dom" : "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
									
									"language": {
									      "emptyTable": "No data available in table"
									    },
									 
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
										
										"scrollX" : true,
										"data":item.individualbills,
						
							"columns" : [
									{
										"data" : "miuID"
										
									},
									 {
											"data" : "meterType"
									},
									 {
										"data" : "previousReading"
									}
									,
									{
										"data" : "presentReading"
										
									},
									 {
											"data" : "consumption"
									},
									 {
										"data" : "tariff"
									},
									 {
										"data" : "billAmount"
								},
								 {
									"data" : "billingDate"
								}
									
									
									],
										"columnDefs" : [/* {
											orderable : false,
											//targets: 5, visible: !(sessionStorage.getItem("roleID") == 4)
											"className": "dt-center", "targets": "_all"
										},
										{
											targets: 4, 
											visible: !(sessionStorage.getItem("roleID") == 5)
										}*/],

							"buttons" : [
									]
						})  

						$('#myCustomerMeters').on('shown.bs.modal', function(e){
							   $($.fn.dataTable.tables(true)).DataTable()
							      .columns.adjust()
							      .responsive.recalc();
					});
				
			} 
		});
	});
	
	
}



function getReceiptTransactionID(transId){
	
	bootbox.confirm("ARE YOU SURE TO DOWNLOAD RECEIPT", function(result) {
		window.open( "./billing/printreceipt/" + transId );
	});
}


function getPayBillTransactionID(customerUniqueID){
	bootbox.confirm("ARE YOU SURE TO DOWNLOAD BILL", function(result) {
		window.open(  "./billing/print/" + customerUniqueID );
	});
}

function executeDownloadDashboard(data){
	console.log(data);
	
	bootbox.confirm("ARE YOU SURE TO DOWNLOAD EXCEL", function(result) {
		// alert(result);
		if (result == true) {
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : ".//billing/excel",
				xhrFields:{
					responseType:'blob'
				},
				headers:{
					'Accept':'application/json',
					'contentType' : 'application/json'
						},
				data : JSON
				.stringify(data),
				success : function(data) {
					
					var blob =  data;
					var downloadUrl = URL.createObjectURL(blob);
					var a= document.createElement("a");
					a.href = downloadUrl;
					a.download = "Billing.xlsx";
					document.body.appendChild(a);
					a.click();
					
					
				}
			});
		} else if (result == false) {
			 alert("@"+false)

		}
	});
	
}

