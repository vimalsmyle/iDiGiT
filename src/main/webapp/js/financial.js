/**
 * 
 */
$(document)
		.ready(
				function() {
					if (sessionStorage.getItem("roleID") == 2
							|| sessionStorage.getItem("roleID") == 5) {
						// if(sessionStorage.getItem("roleID") == 2){
						$("#communityNameAdd").val(
								sessionStorage.getItem("communityName"));
						$("#formcommunityNameAdd")
								.addClass(
										"input-group form-group has-feedback has-success bmd-form-group is-filled")
						$("#blockNameAdd").val(
								sessionStorage.getItem("blockName"));
						$("#formblockNameAdd")
								.addClass(
										"input-group form-group has-feedback has-success bmd-form-group is-filled")
						// }
						document.querySelector(".blockimp").innerText = "*";
					}
				});
$(document)
		.ready(
				function() {
					$("#financialReport")
							.click(
									function() {

										var selectcommunityName = $(
												"#selectcommunityName").val();

										if ($("#selectcommunityName").val() == "-1") {

											bootbox
													.alert("Select Community Id");
											return false;
										}

										if (sessionStorage.getItem("roleID") == 2
												|| sessionStorage
														.getItem("roleID") == 5) {
											if ($(
													"#selectBlockBasedonCommunity")
													.val() == "null"
													|| $(
															"#selectBlockBasedonCommunity")
															.val() == "Select Block") {

												bootbox
														.alert("Select Block Name");
												return false;
											}

											/*
											 * if ($("#end_date").val() ==
											 * "null" || $("#end_date").val() ==
											 * "") {
											 * 
											 * bootbox .alert("Select Month");
											 * return false; }
											 */

										}
										
										if ($("#paytype").val() == "null"
											|| $("#paytype").val() == "-1") {

										bootbox.alert("Select pay type");
										return false;
									}

										if ($("#start_date").val() == "null"
												|| $("#start_date").val() == "") {

											bootbox.alert("Select Only Year");
											return false;
										}

										var data1 = {}
										if (sessionStorage.getItem("roleID") == 2
												|| sessionStorage
														.getItem("roleID") == 5) {

											data1["communityID"] = sessionStorage
													.getItem("communityID")
											data1["blockID"] = sessionStorage
													.getItem("ID");

										} else {

											data1["communityID"] = $(
													"#selectcommunityName")
													.val();
											data1["blockID"] = $(
													"#selectBlockBasedonCommunity")
													.val() == "Select Block" ? 0
													: $(
															"#selectBlockBasedonCommunity")
															.val();

										}

										/*
										 * data1["CRNNumber"] = $(
										 * "#selectHouseBasedonBlock") .val();
										 * data1["meterID"] = $("#AMR_topup")
										 * .val();
										 */
										data1["year"] = $("#start_date").val();
										data1["month"] = $("#end_date").val() == "" ? 0
												: $("#end_date").val();
										
										data1["payType"] =  $("#paytype").val();

										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./financialreports/"
															+ sessionStorage
																	.getItem("roleID")
															+ "/"
															+ sessionStorage
																	.getItem("ID"),
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(d) {

														if($.fn.DataTable.isDataTable("#financialTable_wrapper")){
															$('#financialTable_wrapper').DataTable().clear();
															$('#financialTable').DataTable().destroy();
														}
														$('#financialTable_wrapper thead').empty();
														$('#financialTable_wrapper tbody').remove();
														
														
														$("#theadBody").append("<tr><th>Community Name</th><th>Block Name</th><th>House Number</th><th>Total Amount</th><th>Total Units</th></tr>")


													

														table = $(
																'#financialTable')
																.DataTable(
																		{
																			"dom" : "<'row'<'col-sm-4 headname'><'col-sm-3'><'col-sm-2'f>>"
																					+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-4 totalcount'><'col-sm-1 addevent'>>"
																					+ "<'row'<'col-sm-12'tr>>"
																					+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
																			"responsive" : true,
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
																			"scrollX" : true,
																			"data" : d.data,
																			"columns" : [
																					{
																						"data" : "communityName"
																					},
																					{
																						"data" : "blockName"
																					},
																					{
																						"data" : "houseNumber"
																					},
																					{
																						"data" : "totalAmount"
																					},
																					{
																						"data" : "totalUnits"
																					} ],
																			"columnDefs" : [ {
																				// targets
																				// :
																				// 11,
																				// visible:
																				// (((sessionStorage.getItem("roleID")
																				// ==
																				// 1)
																				// ||
																				// (sessionStorage.getItem("roleID")
																				// ==
																				// 2)
																				// ||
																				// (sessionStorage.getItem("roleID")
																				// ==
																				// 3))
																				// &&
																				// (!(sessionStorage.getItem("roleID")
																				// ==
																				// 5)
																				// ||
																				// !(sessionStorage.getItem("roleID")
																				// ==
																				// 4)))
																				"className" : "dt-center",
																				"targets" : "_all"
																			} ],
																			"buttons" : [
																					/*
																					 * 'csvHtml5',
																					 * 'excelHtml5',
																					 * 'pdfHtml5'
																					 */

																					{
																						// extend:
																						// 'excel',
																						extend: 'excel',
																						//className : 'custom-btn fa fa-file-excel-o',
																						footer : 'true',
																					//	text : 'Excel',
																						title : 'Financial Report'
																					},
																					  {
																					        extend: 'pdf',
																					        footer: 'true',
																					      //  className: 'custom-btn fa fa-file-pdf-o',
																					        exportOptions: {
																					            columns: [0,1,2,3,4]
																					        },
																					        //text: 'pdf',
																					        orientation: 'landscape',
																					        title : 'Financial Report' }
																					
																					], initComplete: function() {
																						   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
																						   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
																						  }
																		});

														$("div.headname")
																.html(
																		'<h3>Financial Reports</h3>');
														// table.ajax.reload()

														$("div.totalcount")
																.html(
																		'<b>Total Amount: </b>'
																				+ d.totalAmountForSelectedPeriod
																				+ '  <b>&nbsp&nbspTotal Units: </b> '
																				+ d.totalUnitsForSelectedPeriod);
														$("div.addevent")
																.html(
																		'<button id="back" onClick="returnBack()"'
																				+ 'class="btn btn-raised btn-primary float-right"'
																				+ '>'
																				+ '	<span>Back</span>'
																				+ '</button>');

													}
												});
										return false;
									});
				});
