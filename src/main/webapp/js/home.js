/**
 * 
 */
$(document)
		.ready(
				function() {

					$("#highchart_container2").hide();

					if (sessionStorage.getItem("roleID") != 3) {

						$
								.getJSON(
										"./homedashboard/Gas/"
												+ sessionStorage
														.getItem("roleID")
												+ "/"
												+ sessionStorage.getItem("ID"),
										function(data) {
											// var Options = "";
											document
													.querySelector("#gasActive").innerText = data.active;
											document
													.querySelector("#gasInactive").innerText = data.inActive;
											document.querySelector("#gasLive").innerText = data.live;
											document
													.querySelector("#gasnonLive").innerText = data.nonLive;
											document
													.querySelector("#gasemergency").innerText = data.emergency;
											document
													.querySelector("#gasLowbattery").innerText = data.lowBattery;
											// document.querySelector("#gasActivePercentage").innerText
											// = data.activePercentage;
											// document.querySelector("#gasinactivePercentage").innerText
											// = data.inActivePercentage;

										});

						$
								.getJSON(
										"./homedashboard/water/"
												+ sessionStorage
														.getItem("roleID")
												+ "/"
												+ sessionStorage.getItem("ID"),
										function(data) {
											// var Options = "";
											document
													.querySelector("#waterActive").innerText = data.active;
											document
													.querySelector("#waterInactive").innerText = data.inActive;
											document
													.querySelector("#waterLive").innerText = data.live;
											document
													.querySelector("#waternonLive").innerText = data.nonLive;
											document
													.querySelector("#wateremergency").innerText = data.emergency;
											document
													.querySelector("#waterLowbattery").innerText = data.lowBattery;
											// document.querySelector("#waterActivePercentage").innerText
											// = data.activePercentage;
											// document.querySelector("#waterinactivePercentage").innerText
											// = data.inActivePercentage;

										});

						$
								.ajax({
									type : "GET",
									contentType : "application/json",
									url : "./graph/Gas/0/0/"
											+ sessionStorage.getItem("ID"),
									dataType : "JSON",

									success : function(d) {
										$("#gasLoader").hide();
										$('#container')
												.highcharts(
														{
															chart : {
																type : 'column'
															},
															title : {
																text : ''
															},
															xAxis : {
																categories : d.xAxis
															},
															plotOptions : {
																series : {
																	cursor : 'pointer',
																	pointWidth : 20,
																	point : {
																		events : {
																			click : function() {
																				// alert('Category:
																				// ' +
																				// this.category
																				// + ',
																				// value:
																				// ' +
																				// this.y);
																				sessionStorage.removeItem("filterId");
																				window.location = "blockDashboard.jsp?com="
																						+ this.category;

																			}
																		}
																	}
																}
															},

															series : [ {
																data : d.yAxis,
																name : 'Consumption'

															} ]

														});
									}
								});

						$
								.ajax({
									type : "GET",
									contentType : "application/json",
									url : "./graph/Water/0/0/"
											+ sessionStorage.getItem("ID"),
									dataType : "JSON",

									success : function(d) {
										$("#waterLoader").hide();
										$('#container1')
												.highcharts(
														{
															chart : {
																type : 'column'
															},
															title : {
																text : ''
															},
															xAxis : {
																categories : d.xAxis
															},

															plotOptions : {
																series : {
																	cursor : 'pointer',
																	pointWidth : 20,
																	point : {
																		events : {
																			click : function() {
																				// alert('Category:
																				// ' +
																				// this.category
																				// + ',
																				// value:
																				// ' +
																				// this.y);
																				sessionStorage.removeItem("filterId");
																				window.location = "blockDashboard.jsp?com="
																						+ this.category;

																			}
																		}
																	}
																}
															},

															series : [ {
																data : d.yAxis,
																name : 'Consumption'
															} ]

														});
									}
								});

					}
					if (sessionStorage.getItem("roleID") == 3) {

						$.ajax({
							type : "GET",
							contentType : "application/json",
							url : "./customergraphall/0/0" + "/"
									+ sessionStorage.getItem("userID"),
							dataType : "JSON",

							success : function(d) {

								Highcharts.chart('highchart_container1', {

									title : {
										text : 'CRN/CAN/UAN: '
												+ sessionStorage
														.getItem("userID")
									},

									yAxis : {
										title : {
											text : 'Number of Consumption'
										}
									},

									xAxis : {
										/*
										 * accessibility: { rangeDescription:
										 * 'Range: 2010 to 2017' }
										 */
										categories : d.xAxis
									},

									legend : {
										layout : 'vertical',
										align : 'right',
										verticalAlign : 'middle'
									},

									plotOptions : {
										series : {
											label : {
												connectorAllowed : false
											},
									//		pointStart : 2010
										}
									},

									series : d.series,

									responsive : {
										rules : [ {
											condition : {
												maxWidth : 500
											},
											chartOptions : {
												legend : {
													layout : 'horizontal',
													align : 'center',
													verticalAlign : 'bottom'
												}
											}
										} ]
									}

								});
							}

						});

						$(document)
								.on(
										'click',
										'#view',
										function() {
											var type = $("#type").val();
											if(type=="" || type==undefined){
												swal({
													  title: "error",
													  text: "Please select type",
													  icon: "error"
													})
												return false;
											}
											
											
											var month = $("#month").val();
											
											
											var year = $("#start_date").val();

											$("#highchart_container1").hide();
											$("#highchart_container2").show();

											$
													.ajax({
														type : "GET",
														contentType : "application/json",
														url : "./customergraph/"+type+"/"
																+ $(
																		"#start_date")
																		.val()
																+ "/"
																+ $("#month")
																		.val()
																+ "/"
																+ sessionStorage
																		.getItem("userID"),
														dataType : "JSON",

														success : function(d) {

															$(
																	'#highchart_container2')
																	.highcharts(
																			{
																				chart : {
																					type : 'line',
																					backgroundColor : 'transparent'
																				},
																				title : {
																					text : 'Consumption Graph'
																				},
																				subtitle : {
																					text : sessionStorage
																							.getItem("customerUniqueID")
																				},
																				xAxis : {
																					categories : d.xAxis,

																					title : {
																						text : null
																					},
																				},
																				yAxis : {
																					min : 0,
																					title : {
																						text : 'Chart',
																						align : 'high'
																					},
																					labels : {
																						overflow : 'justify'
																					}

																				},
																				tooltip : {
																					valueSuffix : ''
																				},
																				plotOptions : {
																					bar : {
																						dataLabels : {
																							enabled : true
																						}
																					}
																				},

																				legend : {
																					layout : 'vertical',
																					align : 'right',
																					verticalAlign : 'top',
																					x : -40,
																					y : 100,
																					floating : true,
																					borderWidth : 1,
																					backgroundColor : ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
																					shadow : true
																				},

																				credits : {
																				// enabled
																				// :
																				// false
																				},
																				series : [ {
																					data : d.yAxis,
																					name : ''
																				} ]

																			});
														}
													});

										});
						
						

						$
								.getJSON(
										"./customer/"
												+ sessionStorage
														.getItem("roleID")
												+ "/"
												+ sessionStorage.getItem("userID")
												+ "/-1",
										function(data) {
											$
													.each(
															data.data,
															function(i, item) {

																document
																		.querySelector(".community").innerText = item.communityName;
																document
																		.querySelector(".block").innerText = item.blockName;
																document
																		.querySelector(".CRN_Number").innerText = item.CustomerUniqueID;

															});
										});

					}

				});

function getCustomerMeters(type){
	
	
	$
			.getJSON(
					 "./dashboard/"+type+"/"
						+ sessionStorage
						.getItem("communityName") + "/"
						+ sessionStorage
						.getItem("blockName")+ "/"+sessionStorage.getItem("CustomerUniqueID")+"/-1",
					function(data) {
						$
								.each(
										data.data,
										function(i, item) {
											if($.fn.DataTable.isDataTable("#customerMeterTable_wrapper")){
												$('#customerMeterTable_wrapper').DataTable().clear();
												$('#customerMeterTable').DataTable().destroy();
												$('#customerMeterTable').DataTable().clear();
											}
											$('#customerMeterTable_wrapper thead').empty();
											$('#customerMeterTable_wrapper tbody').remove();
											$("#theadBody").append("<tr><th>TimeStamp</th><th>Meter Serial Number</th>" +
													"<th>MIU ID</th><th>Reading</th><th>Consumption</th><th>Battery</th>" +
													"<th>Box AMR Tamper</th>" +
								"<th>Magnetic Tamper</th>" +
								"<th>Balance</th>" +
								"<th>Emergency Credit</th>" +
								"<th>Pay Type</th>" +
								"<th>Valve Status</th>" +
								"<th>Tariff</th>" +
								"<th>Communication Status</th>" +
								"<th>Vacation Status</th>" +
								"<th>Last Topup Amount</th></tr>")

											 table = $('#customerMeterTable').DataTable(
														{
															"dom" : "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
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
														});
											$('#myCustomerMeters').on('shown.bs.modal', function(e){
												   $($.fn.dataTable.tables(true)).DataTable()
												      .columns.adjust()
												      .responsive.recalc();
										});
										});
						
					});
	}