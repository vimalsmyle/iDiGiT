$(document)
		.ready(
				function() {

					var dom1 = "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f>>"
							+ "<'row'<'col-sm-12'tr>>"
							+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";

					var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
							+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>"
							+ "<'row'<'col-sm-12'tr>>"
							+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					$("#liveTable1").hide();
					var filterId = (sessionStorage.getItem("filterId") == null) ? 0
							: (sessionStorage.getItem("filterId"));
					// sessionStorage.remove("filterId");
					//var hCols = [ 3, 4 ];
					// DataTable initialisation
					if(filterId!=0){
						$(".accordionFilter").hide();
					}else{
						$(".accordionFilter").show();
					}
					
					var filter = (sessionStorage.getItem("filterId")==undefined || sessionStorage.getItem("filterId")==null)
					?"0":sessionStorage.getItem("filterId");
					console.log("./dashboard/"
							+ $("#type").val() + "/"
							+ $("#comName").val() + "/"
							+ $("#blockName").val()
							+ "/0/"+filterId);
					var responseD;
					
					$('#liveTable')
							.DataTable(
									{
										"dom" : dom1,

										responsive : {
											details : {
												renderer : function(api, rowIdx) {
													var data = api
															.cells(rowIdx,
																	':hidden')
															.eq(0)
															.map(
																	function(
																			cell) {
																		var header = $(api
																				.column(
																						cell.column)
																				.header());
																		return '<p style="color:#00A">'
																				+ header
																						.text()
																				+ ' : '
																				+ api
																						.cell(
																								cell)
																						.data()
																				+ '</p>'; // changing
																							// details
																							// mark
																							// up.
																	})
															.toArray().join('');

													return data ? $('<table/>')
															.append(data)
															: false;
												}
											}
										},
										"language" : {
											"emptyTable" : "No data available in table"
										},

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
										"scrollX" : false,
										"ajax" : {
											"url" : "./dashboard/"
													+ $("#type").val() + "/"
													+ $("#comName").val() + "/"
													+ $("#blockName").val()
													+ "/0/"+filterId,
											"type" : "GET",
											"data" : function(search) {
											},
											"complete" : function(json) {
												console.log(json.responseJSON);
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
													"data" : "HouseNumber",
													"defaultContent" : ""

												},
												{
													// "data": "timeStamp",

													"mData" : "action",
													"render" : function(data,
															type, row) {
														return "<span id=color"
																+ ">"
																+ row.firstName
																+ " "
																+ row.lastName
																+ "</span>"
													},
													"defaultContent" : ""
												},
												{
													"data" : "customerUniqueID",
													"defaultContent" : ""
												},
												{
													"mData" : "action",
													"render" : function(data,
															type, row) {

														return "<a href=# id=CustomerMeters data-toggle=modal data-target=#myCustomerMeters onclick='getCustomerMeters(\""
																+ row.customerUniqueID
																+ "\")'>"
																+ "Multiple"
																+ "</a>"

													}

												} ],
										"columnDefs" : [
												/*{
													"orderable" : true,
													"targets" : [ 0, 1, 2, 3,
															4, 5, 6, 7,8 ],
													"className" : "dt-center",
													"targets" : "_all"
												}, {
													orderable : false,
													targets : [9 ]
												} */],

										"buttons" : [
												{
													extend : 'excel',
													footer : 'true',
													// text : 'Excel',
													title : 'Dashboard',
												// className: 'custom-btn fa
												// fa-file-excel-o'
													
													action:function(e,dt,node,config){
														executeDownloadDashboard(responseD);
													}
													
												}/*,

												{
													extend : 'pdf',
													footer : 'true',
													exportOptions : {
														columns : [ 0, 1, 2, 3,
																4, 5]
													},
													orientation : 'landscape',
													title : 'Dashboard',
													// className: 'custom-btn fa
													// fa-file-pdf-o',
													pageSize : 'LEGAL',
													action:function(e,dt,node,config){
														executeDownloadDashboard(responseD);
													}
												}*/ ],
										initComplete : function() {
											$('.buttons-excel')
													.html(
															'<i class="fa fa-file-excel-o" />')
											$('.buttons-pdf')
													.html(
															'<i class="fa fa-file-pdf-o" />')
										}

									})
//sessionStorage.removeItem("filterId");
				
				});


function executeDownloadDashboard(data){
	console.log(data);
	
	bootbox.confirm("ARE YOU SURE TO DOWNLOAD EXCEL", function(result) {
		// alert(result);
		if (result == true) {
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "./dashboard/excel",
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
					a.download = "Dashboard.xlsx";
					document.body.appendChild(a);
					a.click();
					
					
				}
			});
		} else if (result == false) {
			 alert("@"+false)

		}
	});
	
}


function getCustomerMetersWithData(data) {
	
	console.log(data);
	
	console.log(JSON.stringify(data));
												$('#customerMeterTable')
														.DataTable(
																{
																	"dom" : "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
																			+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>"
																			+ "<'row'<'col-sm-12'tr>>"
																			+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",

																	"language" : {
																		"emptyTable" : "No data available in table"
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
																	"fixedHeader": {
															            "header": true,
															            "headerOffset": 45,
															            },
															        "scrollX": true,
																	"data" : data.dasboarddata,

																	"columns" : [
																		{
																			"mData" : "action",
																			"render" : function(
																					data,
																					type,
																					row) {
																				return "<span id=color style = color:"
																						+ row.dateColor
																						+ ">"
																						+ row.timeStamp
																						+ "</span>"
																			},
																			"defaultContent" : ""
																		},	
																	{
																		"data" : "meterSerialNumber"
																	},
																			{
																				"data" : "miuID"

																			},
																			{
																				"data" : "gatewayName"

																			},
																			{
																				"data" : "reading"
																			},
																			{
																				"data" : "consumption"

																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.batteryColor
																							+ ">"
																							+ row.battery
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.dooropentamperColor
																							+ ">"
																							+ row.doorOpenTamper
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.magnetictamperColor
																							+ ">"
																							+ row.magneticTamper
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.nfcTamperColor
																							+ ">"
																							+ row.nfcTamper
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"data" : "balance"
																			},
																			{
																				"data" : "emergencyCredit"
																			},
																			{
																				"data" : "payType"
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.valveStatusColor
																							+ ">"
																							+ row.valveStatus
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"data" : "tariff"
																			},
																			{
																				"data" : "communicationStatus"
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.vacationColor
																							+ ">"
																							+ row.vacationStatus
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"data" : "lastTopupAmount"
																			}

																	],
																	"columnDefs" : [],
																	"buttons" : []
																})
																$('#myCustomerMeters').on('shown.bs.modal', function(e){
																	   $($.fn.dataTable.tables(true)).DataTable()
																	      .columns.adjust()
																	      .responsive.recalc();
															});
			
}



function getCustomerMeters(CRNNumber) {
	
	var filter = (sessionStorage.getItem("filterId")==undefined || sessionStorage.getItem("filterId")==null)
	?"0":sessionStorage.getItem("filterId");
	$
			.getJSON(
					"./dashboard/" + $("#type").val() + "/"
							+ $("#comName").val() + "/" + $("#blockName").val()
							+ "/"+CRNNumber+"/"+filter,
					function(data) {
						$
								.each(
										data.data,
										function(i, item) {
											if (CRNNumber == item.customerUniqueID) {
												$('#myCustomerMeters').modal('show');
												$('#customerMeterTable')
														.DataTable(
																{
																	"dom" : "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
																			+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>"
																			+ "<'row'<'col-sm-12'tr>>"
																			+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",

																	"language" : {
																		"emptyTable" : "No data available in table"
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
																	"fixedHeader": {
															            "header": true,
															            "headerOffset": 45,
															            },
															        "scrollX": true,
																	"data" : item.dasboarddata,

																	"columns" : [
																		{
																			"mData" : "action",
																			"render" : function(
																					data,
																					type,
																					row) {
																				return "<span id=color style = color:"
																						+ row.dateColor
																						+ ">"
																						+ row.timeStamp
																						+ "</span>"
																			},
																			"defaultContent" : ""
																		},	
																	{
																		"data" : "meterSerialNumber"
																	},
																			{
																				"data" : "miuID"

																			},
																			{
																				"data" : "gatewayName"

																			},
																			{
																				"data" : "reading"
																			},
																			{
																				"data" : "consumption"

																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.batteryColor
																							+ ">"
																							+ row.battery
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.dooropentamperColor
																							+ ">"
																							+ row.doorOpenTamper
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.magnetictamperColor
																							+ ">"
																							+ row.magneticTamper
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.nfcTamperColor
																							+ ">"
																							+ row.nfcTamper
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"data" : "balance"
																			},
																			{
																				"data" : "emergencyCredit"
																			},
																			{
																				"data" : "payType"
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.valveStatusColor
																							+ ">"
																							+ row.valveStatus
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"data" : "tariff"
																			},
																			{
																				"data" : "communicationStatus"
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.vacationColor
																							+ ">"
																							+ row.vacationStatus
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"data" : "lastTopupAmount"
																			}

																	],
																	"columnDefs" : [],
																	"buttons" : []
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



$("#dashboardFilter")
.click(
		function() {

			if($("#end_date").val() != ""){
				if($("#start_date").val() == ""){
					bootbox.alert("Enter Date From")
					return false;
					}
				}
			
			
			var reading =/^^\d{7}$/;
			if($("#reading_from").val() != ""){
				if (reading.test($("#reading_from").val())) {
					bootbox.alert("Invalid Reading From ");
					return false;
					}
				}
			
			if($("#reading_to").val() != ""){
				if (reading.test($("#reading_to").val())) {
					bootbox.alert("Invalid Reading To")
					return false;
					}
				}
			
			
			
			var battery =/^[0-9]{0,3}$/;
			if($("#battery_from").val() != ""){
				if (!battery.test($("#battery_from").val())) {
					bootbox.alert("Invalid Battery (%) From ");
					return false;
					}
				}
			
			if($("#battery_to").val() != ""){
				if (!battery.test($("#battery_to").val())) {
					bootbox.alert("Invalid Battery (%) To")
					return false;
					}
				}
			
			var data1 = {}
			
			data1["dateFrom"] = $("#start_date").val() == "" ? "null":$("#start_date").val();
			data1["dateTo"] = $("#end_date").val() == "" ? "null":$("#end_date").val();
			data1["readingFrom"] = $("#reading_from").val() == "" ? "0":$("#reading_from").val();
			data1["readingTo"] = $("#reading_to").val() == "" ? "0":$("#reading_to").val()
			data1["batteryVoltageFrom"] = $("#battery_from").val() == "" ? "0":$("#battery_from").val()
			data1["batteryVoltageTo"] = $("#battery_to").val() == "" ? "0":$("#battery_to").val()
			data1["tamperType"] = $("#tamper").val();

			//alert(JSON.stringify(data1));
			var responseD1;
			$
					.ajax({
						type : "POST",
						contentType : "application/json",
						url : "./filterdashboard/"+$("#type").val()+"/"+$("#comName").val()+"/"+$("#blockName").val(),
						data : JSON
								.stringify(data1),
						dataType : "JSON",

						success : function(d) {
							responseD1 = d;
							//if (data.result == "Success") {
							//$('#liveTable').dataTable()._fnAjaxUpdate();
							//$("#form").hide();
							//$("#tablereport").show();
								console.log(JSON.stringify(d));
								$("#liveTable_wrapper").hide();
								$("#exampleModal").modal("hide");
								$("#liveTable1").show();
								
								var hCols = [ 3, 4 ];
							table = $('#liveTable1')
								.DataTable(
										{
											
											"dom" : "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f>>"
													+ "<'row'<'col-sm-12'tr>>"
													+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
													
													"language": {
													      "emptyTable": "No data available in table"
													    },
													 
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
																	"data" : "HouseNumber",
																	"defaultContent" : ""

																},
																{
																	// "data": "timeStamp",

																	"mData" : "action",
																	"render" : function(data,
																			type, row) {
																		return "<span id=color"
																				+ ">"
																				+ row.firstName
																				+ " "
																				+ row.lastName
																				+ "</span>"
																	},
																	"defaultContent" : ""
																},
																{
																	"data" : "customerUniqueID",
																	"defaultContent" : ""
																},
																{
																	"mData" : "action",
																	"render" : function(data,
																			type, row) {

																		return "<a href=# id=CustomerMeters data-toggle=modal data-target=#myCustomerMeters onclick='getCustomerMetersWithData(" + JSON.stringify(row) + ")'>"
																				+ 'Multiple'
																				+ "</a>"

																	}

																} ],
													"columnDefs" : [ {
														"orderable" : true,
														"targets":  [0,1,2,3,4,5],
														"className": "dt-center", "targets": "_all"
													},
													{
														orderable : false,
														targets : [ 6 ]
													}],

											
											  "columnDefs": [{ "visible": false }],
											 
												"buttons" : [
													{
														extend : 'excel',
														footer : 'true',
														// text : 'Excel',
														title : 'Dashboard',
													// className: 'custom-btn fa
													// fa-file-excel-o'
														
														action:function(e,dt,node,config){
															executeDownloadFilterDashboard(responseD1);
														}
														
													}/*,

													{
														extend : 'pdf',
														footer : 'true',
														exportOptions : {
															columns : [ 0, 1, 2, 3,
																	4, 5]
														},
														orientation : 'landscape',
														title : 'Dashboard',
														// className: 'custom-btn fa
														// fa-file-pdf-o',
														pageSize : 'LEGAL',
														action:function(e,dt,node,config){
															executeDownloadDashboard(responseD);
														}
													}*/ ],
													 initComplete: function() {
														   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
														   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
														  }

										})
						}
					});
			return false;
		});

function executeDownloadFilterDashboard(data){
	console.log(data);
	
	bootbox.confirm("ARE YOU SURE TO DOWNLOAD EXCEL", function(result) {
		// alert(result);
		if (result == true) {
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "./filterdashboard/excel",
				xhrFields:{
					responseType:'blob'
				},
				headers:{
					'Accept':'application/json',
					'contentType' : 'application/json'
						},
				data : JSON
				.stringify(data),
				success : function(data1) {
					
					var blob =  data1;
					var downloadUrl = URL.createObjectURL(blob);
					var a= document.createElement("a");
					a.href = downloadUrl;
					a.download = "FilterDashboard.xlsx";
					document.body.appendChild(a);
					a.click();
					
					
				}
			});
		} else if (result == false) {
			 //alert("@"+false)

		}
	});
	
}
