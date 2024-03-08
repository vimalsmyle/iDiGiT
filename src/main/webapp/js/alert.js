/**
 * 
 */

$(document)
		.ready(
				function() {

					if (sessionStorage.getItem("roleID") == 1) {
						// $("#alertAddbutton").show();
						var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
								+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>"
								+ "<'row'<'col-sm-12'tr>>"
								+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					} else {
						/* $("#alertAddbutton").remove(); */
						var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
		
	}
	
table = $('#alertTable')
.DataTable(
{
										/* "processing" : false, */
										"dom" : dom1,
										"serverSide" : false,
										"bDestroy" : true,
										"pagging" : true,
										"bProcessing" : false,
										"ordering" : true,
										"order" : [ 0, "desc" ],
										"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
										"pageLength" : "25",
										"scrollX" : true,
"ajax" : {
											"url" : "./alert",
											"type" : "GET",
											"data" : function(search) {
											},
											"complete" : function(json) {
												console
														.log(JSON
																.stringify(json.responseText)
																+ "json.data"
																+ json);
												var json_data = JSON
														.parse(JSON
																.stringify(json.responseText));
												// Object.keys(json).length >0 ?
												// $("")
												console
														.log(JSON
																.stringify(json_data.data));
												// console.log(Object.keys(json_data.data[0]).length);
												return json.data;
											},
										},
										"columns" : [
												{

													"data" : "noAMRInterval"
												},
												{
													"data" : "timeOut"
												},
												{
													"data" : "reconnectionCharges"
												},
												{
													"data" : "reconnectionChargeDays"
												},
												{
													"data" : "billGenerationDate"
												},
												{
													"data" : "lateFee"
												},
												{
													"data" : "GST"
												},{
													"data" : "remarks"
												},
												{
													"data" : "dueDayCount"
												},
												{
													"data" : "registeredDate"
												},
												{
													"mData" : "action",
													"render" : function(data,
															type, row) {

														return "<a href=# id=alertEdit data-toggle=modal data-target=#myAlertEdit onclick='getAlertFormEdit("
																+ row.alertID
																+ ")'>"
																+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																+ "</a>"
													}
												}

										],
										"columnDefs" : [
												{
													// orderable : false,
													targets : 9,
													visible : (sessionStorage
													.getItem("roleID") == 1) 
														
														
														/*(((sessionStorage
																.getItem("roleID") == 1)
																|| (sessionStorage
																		.getItem("roleID") == 2) || (sessionStorage
																.getItem("roleID") == 3)) && (!(sessionStorage
																.getItem("roleID") == 5) || !(sessionStorage
																.getItem("roleID") == 4)))*/
												}, {
													"className" : "dt-center",
													"targets" : "_all"
												}

										],
										initComplete : function() {
											$('.buttons-excel')
													.html(
															'<i class="fa fa-file-excel-o" />')
											$('.buttons-pdf')
													.html(
															'<i class="fa fa-file-pdf-o" />')
											if ($('#alertTable').DataTable()
													.rows('tr').count() >= 1) {
												$("#alertAddbutton").remove();
											}
										}

									});

					$("div.headname").html('<h3>Alert Settings</h3>');

					$("div.addevent")
							.html(
									'<button type="button" id="alertAddbutton"'
											+ 'class="btn btn-raised btn-primary float-right"'
											+ 'data-toggle="modal" data-target="#exampleModal">'
											+ '	<i class="fa fa-user-plus"></i>'
											+ '</button>');

				});

$(document)
		.ready(
				function() {
					$('#alertDetails')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											noamrintervalAdd : {
												message : 'No AMR Interval is not valid',
												validators : {
													notEmpty : {
														message : 'No AMR Interval is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'No AMR Interval be more than 2 and less than 30 characters long'
													}
												}
											},
											rechargetimeoutAdd : {
												message : 'Recharge Timeout is not valid',
												validators : {
													notEmpty : {
														message : 'Recharge Timeout is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]+$/,
														message : 'Recharge Timeout can only consist of number'
													}
												}
											},
											reconnectionAdd : {
												message : 'ReConnection Charge is not valid',
												validators : {
													notEmpty : {
														message : 'ReConnection Charge is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]+$/,
														message : 'ReConnection Charge can only consist of number'
													}
												}
											},reconnectionDaysAdd : {
												message : 'ReConnection Days is not valid',
												validators : {
													notEmpty : {
														message : 'ReConnection Days is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]+$/,
														message : 'ReConnection Days can only consist of number'
													}
												}
											},
											billGenerationAdd : {
												message : 'Bill Generation is not valid',
												validators : {
													notEmpty : {
														message : 'Bill Generation is required and cannot be empty'
													}
												}
											},

											lateFeeAdd : {
												message : 'Late Fee is not valid',
												validators : {
													notEmpty : {
														message : 'Late Fee is required and cannot be empty'
													},
													regexp : {
														regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
														message : 'Late Fee can only consist of number'
													}
												}
											},

											gstAdd : {
												message : 'GST Charge is not valid',
												validators : {
													notEmpty : {
														message : 'GST Charge is required and cannot be empty'
													},
													regexp : {
														regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
														message : 'GST Charge can only consist of number'
													}
												}
											},
											remarkAdd : {
												message : 'Remarks are not valid',
												validators : {
													notEmpty : {
														message : 'Remarks are required and cannot be empty'
													}
												}
											},
											duedatecountAdd : {
												message : 'Due Date Count is not valid',
												validators : {
													notEmpty : {
														message : 'Due Date Count is required and cannot be empty'
													},
													regexp : {
														regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
														message : 'Due Date Count can only consist of number'
													}
												}
											}
										}
									});

					$('#alertEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											noamrintervalEdit : {
												message : 'No AMR Interval is not valid',
												validators : {
													notEmpty : {
														message : 'No AMR Interval is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'No AMR Interval be more than 6 and less than 30 characters long'
													}
												}
											},
											rechargetimeoutEdit1 : {
												message : 'Recharge Timeout is not valid',
												validators : {
													notEmpty : {
														message : 'Recharge Timeout is required and cannot be empty'
													}
												}
											},
											connectionEdit1 : {
												message : 'ReConnection Charge is not valid',
												validators : {
													notEmpty : {
														message : 'ReConnection Charge is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]+$/,
														message : 'ReConnection Charge can only consist of number'
													}
												}
											},connectionDaysEdit1 : {
												message : 'ReConnection Days  is not valid',
												validators : {
													notEmpty : {
														message : 'ReConnection Days is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]+$/,
														message : 'ReConnection Days can only consist of number'
													}
												}
											},
											/*billGenerationEdit1 : {
												message : 'Bill Generation is not valid',
												validators : {
													notEmpty : {
														message : 'Bill Generation is required and cannot be empty'
													}
												}
											},*/

											lateFeeEdit1 : {
												message : 'Late Fee is not valid',
												validators : {
													notEmpty : {
														message : 'Late Fee is required and cannot be empty'
													},
													regexp : {
														regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
														message : 'Late Fee can only consist of number'
													}
												}
											},

											gstEdit1 : {
												message : 'GST Charge is not valid',
												validators : {
													notEmpty : {
														message : 'GST Charge is required and cannot be empty'
													},
													regexp : {
														regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
														message : 'GST Charge can only consist of number'
													}
												}
											},
											remarkEdit1 : {
												message : 'Remarks are not valid',
												validators : {
													notEmpty : {
														message : 'Remarks are required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 1000,
														message : 'Remarks cannot be more than 1000 characters'
													}
												}
											},
											duedatecountEdit1 : {
												message : 'Due Date Count is not valid',
												validators : {
													notEmpty : {
														message : 'Due Date Count is required and cannot be empty'
													},
													regexp : {
														regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
														message : 'Due Date Count can only consist of number'
													}
												}
											}
										}
									});

					$('#alertDetails').on(
							'status.field.bv',
							function(e, data) {
								formIsValid = true;
								$('.group.form-group', $(this)).each(
										function() {
											// alert(this+"@@=>"+formIsValid);
											formIsValid = formIsValid
													&& $(this).hasClass(
															'has-success');

											// alert("!!@@=>"+formIsValid);

										});

								if (formIsValid) {
									$('.submit-button', $(this)).attr(
											'disabled', false);
								} else {
									$('.submit-button', $(this)).attr(
											'disabled', true);
								}
							});

					$('#alertEdit').on(
							'status.field.bv',
							function(e, data) {
								formIsValid = true;
								$('.group.form-group', $(this)).each(
										function() {
											// alert(this+"@@=>"+formIsValid);
											formIsValid = formIsValid
													&& $(this).hasClass(
															'has-success');

											// alert("!!@@=>"+formIsValid);

										});

								if (formIsValid) {
									$('#alertEditsave', $(this)).attr(
											'disabled', false);
								} else {
									$('#alertEditsave', $(this)).attr(
											'disabled', true);
								}
							});

					$("#alertAdd").click(
							function() {

								var data1 = {}
								data1["noAMRInterval"] = $("#noamrintervalAdd")
										.val();
								/*
								 * data1["lowBatteryVoltage"] =
								 * $("#lowbatteryvoltageAdd").val();
								 */
								data1["timeOut"] = $("#rechargetimeoutAdd")
										.val();

								data1["reconnectionCharges"] = $(
										"#reconnectionAdd").val();
								data1["reconnectionChargeDays"] = $("#reconnectionDaysAdd").val();

								data1["billGenerationDate"] = $("#billGenerationAdd").val();
								
								data1["lateFee"] = $("#lateFeeAdd").val();

								data1["GST"] = $("#gstAdd").val();
								data1["remarks"] = $("#remarkAdd").val();
								data1["dueDayCount"] = $("#duedatecountAdd")
										.val();

								$.ajax({
									type : "POST",
									contentType : "application/json",
									url : "./alert/add",
									data : JSON.stringify(data1),
									dataType : "JSON",

									success : function(data) {

										if (data.result == "Success") {

											swal.fire({
												title : "Saved",
												text : data.Message,
												icon : "success"
											}).then(function() {
												window.location = "alert.jsp";
											});
											return false;

										} else if (data.result == "Failure") {

											swal.fire({
												title : "error",
												text : data.Message,
												icon : "error"
											}).then(function() {
												window.location = "alert.jsp";
												return false;
											});
										}
									}
								});
								return false;
							});

					$("#alertEditsave")
							.click(
									function() {

										var data1 = {}

										data1["noAMRInterval"] = $(
												"#noamrintervalEdit").val();
										data1["timeOut"] = $(
												"#rechargetimeoutEdit1").val();

										data1["reconnectionCharges"] = $(
												"#connectionEdit1").val();
										
										
										data1["reconnectionChargeDays"] = $(
										"#connectionDaysEdit1").val();
										
										
										/*data1["billGenerationDate"] = $(
												"#billGenerationEdit1").val();*/

										data1["lateFee"] = $("#lateFeeEdit1")
												.val();

										data1["GST"] = $("#gstEdit1").val();
										data1["remarks"] = $("#remarkEdit1").val();
										data1["dueDayCount"] = $(
												"#duedatecountEdit1").val();

										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./alert/edit/"
															+ $(
																	"#alertIdhidden")
																	.val(),
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(data) {

														if (data.result == "Success") {

															/*
															 * alert( "data" +
															 * data.result);
															 */

															swal
																	.fire(
																			{
																				title : "Saved",
																				text : data.Message,
																				icon : "success"
																			})
																	.then(
																			function() {
																				window.location = "alert.jsp";
																			});
															return false;

														} else if (data.result == "Failure") {

															swal
																	.fire(
																			{
																				title : "error",
																				text : data.Message,
																				icon : "error"
																			})
																	.then(
																			function() {
																				window.location = "alert.jsp";
																				return false;
																			});
														}
													}
												});
										return false;
									});

				});

function getAlertFormEdit(id) {

	// alert(id);

	$
			.getJSON(
					"./alert",
					function(data) {
						$
								.each(
										data.data,
										function(i, item) {
											if (id == item.alertID) {
												// alert(item.lowBatteryVoltage);
												$('#noamrintervalEdit').val(
														item.noAMRInterval)
														.trigger("change");
												$("#formnoamrintervalEdit")
														.addClass(
																"group form-group has-feedback has-success bmd-form-group is-filled")
												/*
												 * $('#lowbatteryvoltageEdit1').val(item.lowBatteryVoltage).trigger("change");
												 * $("#formlowbatteryvoltageEdit").addClass("input-group
												 * form-group has-feedback
												 * has-success bmd-form-group
												 * is-filled")
												 */
												$('#rechargetimeoutEdit1').val(
														item.timeOut).trigger(
														"change");
												$("#formrechargetimeoutEdit")
														.addClass(
																"group form-group has-feedback has-success bmd-form-group is-filled")

												$('#connectionEdit1')
														.val(
																item.reconnectionCharges)
														.trigger("change");
												$("#formreconnectionEdit")
														.addClass(
																"group form-group has-feedback has-success bmd-form-group is-filled")
																
																$('#connectionDaysEdit1')
														.val(
																item.reconnectionChargeDays)
														.trigger("change");
												$("#formreconnectionDaysEdit")
														.addClass(
																"group form-group has-feedback has-success bmd-form-group is-filled")
																
												$('#billGenerationEdit1').val(
														item.billGenerationDate)
														.trigger("change");
												/*$("#formbillgenerationEdit")
														.addClass(
																"group form-group has-feedback has-success bmd-form-group is-filled")
*/
												$('#lateFeeEdit1').val(
														item.lateFee).trigger(
														"change");
												$("#formlateFeeEdit")
														.addClass(
																"group form-group has-feedback has-success bmd-form-group is-filled")

												$('#gstEdit1').val(item.GST)
														.trigger("change");
												$("#formgstEdit")
														.addClass(
																"group form-group has-feedback has-success bmd-form-group is-filled")
																
												$('#remarkEdit1').val(item.remarks)
														.trigger("change");
												$("#formremarks")
														.addClass(
																"group form-group has-feedback has-success bmd-form-group is-filled")

												$('#duedatecountEdit1').val(
														item.dueDayCount)
														.trigger("change");
												$("#formduedatecountEdit")
														.addClass(
																"group form-group has-feedback has-success bmd-form-group is-filled")

												$("#alertIdhidden").val(
														item.alertID);
												$('#alertEditsave').attr(
														'disabled', false);

											} else {
											}
										});
						$('#myAlertEdit').modal('show');
					});
}