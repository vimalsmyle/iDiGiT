/**
 * 
 */


$(document).ready(function() {
	if(sessionStorage.getItem("roleID") == 1){
		$("#tariffAddd").show();
		var dom1 ="<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 text-right addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}else{
		$("#tariffAddd").remove();
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
	}
	
	//alert(!(sessionStorage.getItem("roleID") == 4))
	
table = $('#tariffTable')
.DataTable(
{
	"dom": dom1,
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
"ajax" : {
"url":"./tariff",
"type" : "GET",
"data" : function(search) {
},
"complete" : function(json) {
	console.log(json);
return json.data;
},
},
"columns" : [
                        {
"data" : "tariffName"
},{
"data" : "tariff"
},{
"data" : "emergencyCredit"
},{
"data" : "alarmCredit"
},{
"data" : "fixedCharges"
},{
"data" : "modifiedDate"
},{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		return "<a href=# id=TariffEdit data-toggle=modal data-target=#myTariffEdit onclick='getTariffFormEdit("
																	+ row.tariffID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a> "
																	+"<a onclick='getDeleteTraiff("
																	+ row.tariffID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9; cursor:pointer;'>delete</i>"
																	+ "</a></div>"
	}
	}
],
"columnDefs" : [ {
//	orderable : false,
	targets: 6, visible: !(sessionStorage.getItem("roleID") == 4)
},
{
	"className": "dt-center", "targets": "_all"
}
], "buttons": [
   
]
});

$("div.headname").html('<h3>Tariff Details</h3>');

$("div.addevent").html('<button type="button" id="tariffAddd" class="btn btn-raised btn-primary" data-toggle="modal" data-target="#exampleModal"> <i class="fa fa-plus">Add</i></i></button>');

});






$(document)
				.ready(
						function() {
							$('#tariffDetails')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													tariffNameAdd : {
														message : 'Name is not valid',
														validators : {
															notEmpty : {
																message : 'Name is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 25,
																message : 'Name must be more than 4 and less than 25 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z0-9\s]*$/,
																message : 'Name can only consist of alphanumaric with max 25'
															}
														}
													},
													tariffRateAdd : {
														message : 'Per Unit is not valid',
														validators : {
															notEmpty : {
																message : 'Per Unit is required and cannot be empty'
															},
															stringLength : {
																min : 1,
																max : 6,
																message : 'Per Unit must be more than 1 and less than 4 characters long'
															},
															regexp : {
																regexp : /^(0|[1-9]\d*)(\.\d+)?$/,
																message : 'Per Unit can only consist of number'
															}
														}
													},
													emergencyCreditAdd : {
														message : 'Emergency Credit is not valid',
														validators : {
															notEmpty : {
																message : 'Emergency Credit is required and cannot be empty'
															},stringLength : {
																min : 1,
																max : 4,
																message : 'Emergency Credit must be more than 1 and less than 4 characters long'
															},
															regexp : {
																regexp : /^(0|[1-9]\d*)(\.\d+)?$/,
																message : 'Emergency Credit can only consist of number'
															}
														}
													},
													alarmCreditAdd : {
														message : 'Alaram Credit is not valid',
														validators : {
															notEmpty : {
																message : 'Alaram Credit is required and cannot be empty'
															},stringLength : {
																min : 1,
																max : 4,
																message : 'Alaram Credit must be more than 1 and less than 4 characters long'
															},
															regexp : {
																regexp : /^(0|[1-9]\d*)(\.\d+)?$/,
																message : 'Alarm Credit can only consist of number'
															}
														}
													},
													fixedChargeAdd : {
														message : 'Fixed Charge is not valid',
														validators : {
															notEmpty : {
																message : 'Fixed Charge is required and cannot be empty'
															},stringLength : {
																min : 1,
																max : 4,
																message : 'Fixed Charge must be more than 1 and less than 4 characters long'
															},
															regexp : {
																regexp : /^(0|[1-9]\d*)(\.\d+)?$/,
																message : 'Fixed Charge can only consist of number'
															}
														}
													}
												}
											});
							
							
							
							
							$('#tariffEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											tariffNameEdit : {
												message : 'Name is not valid',
												validators : {
													notEmpty : {
														message : 'Name is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 25,
														message : 'Name must be more than 4 and less than 25 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z0-9\s]*$/,
														message : 'Name can only consist of alphabet'
													}
												}
											},
											tariffRateEdit : {
												message : 'Per Unit is not valid',
												validators : {
													notEmpty : {
														message : 'Per Unit is required and cannot be empty'
													},
													stringLength : {
														min : 1,
														max : 6,
														message : 'Per Unit must be more than 1 and less than 4 characters long'
													},
													regexp : {
														regexp : /^(0|[1-9]\d*)(\.\d+)?$/,
														message : 'Rate can only consist number'
													}
												}
											},
											emergencyCreditEdit : {
												message : 'Emergency Credit is not valid',
												validators : {
													notEmpty : {
														message : 'Emergency Credit is required and cannot be empty'
													},stringLength : {
														min : 1,
														max : 4,
														message : 'Emergency Credit must be more than 1 and less than 4 characters long'
													},
													regexp : {
														regexp : /^(0|[1-9]\d*)(\.\d+)?$/,
														message : 'Emergency Credit can only consist of number'
													}
												}
											},
											alarmCreditEdit : {
												message : 'Alaram Credit is not valid',
												validators : {
													notEmpty : {
														message : 'Alaram Credit is required and cannot be empty'
													},stringLength : {
														min : 1,
														max : 4,
														message : 'Alaram Credit must be more than 1 and less than 4 characters long'
													},
													regexp : {
														regexp : /^(0|[1-9]\d*)(\.\d+)?$/,
														message : 'Alarm Credit can only consist of number'
													}
												}
											},
											fixedChargeEdit : {
												message : 'Fixed Charge is not valid',
												validators : {
													notEmpty : {
														message : 'Fixed Charge is required and cannot be empty'
													},stringLength : {
														min : 1,
														max : 4,
														message : 'Fixed Charge must be more than 1 and less than 4 characters long'
													},
													regexp : {
														regexp : /^(0|[1-9]\d*)(\.\d+)?$/,
														message : 'Fixed Charge can only consist of number'
													}
												}
											}
										}
									});
							
							
							
							
							
							
							
							$('#tariffDetails')
									.on(
											'status.field.bv',
											function(e, data) {
												formIsValid = true;
												$('.group.form-group', $(this))
														.each(
																function() {
																//	alert(this+"@@=>"+formIsValid);
																	formIsValid = formIsValid
																			&& $(
																					this)
																					.hasClass(
																							'has-success');
																	
																	//alert("!!@@=>"+formIsValid);
																	
																});

												if (formIsValid) {
													$('.submit-button', $(this))
															.attr('disabled',
																	false);
												} else {
													$('.submit-button', $(this))
															.attr('disabled',
																	true);
												}
											});
							
							
							
							
							$('#tariffEdit').on(
									'status.field.bv',
									function(e, data) {
										formIsValid = true;
										$('.group.form-group', $(this))
												.each(
														function() {
														//	alert(this+"@@=>"+formIsValid);
															formIsValid = formIsValid
																	&& $(
																			this)
																			.hasClass(
																					'has-success');
															
															//alert("!!@@=>"+formIsValid);
															
														});

										if (formIsValid) {
											$('#tariffEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#tariffEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
							

												$(document).on('click', '#tariffAdd', function () {

												var data1 = {}
												data1["tariffName"] = $("#tariffNameAdd").val();
												data1["tariff"] = $("#tariffRateAdd").val();
												data1["emergencyCredit"] = $("#emergencyCreditAdd").val();
												data1["alarmCredit"] = $("#alarmCreditAdd").val();
												data1["fixedCharges"] = $("#fixedChargeAdd").val();
										
											/*	alert("===>"
														+ JSON.stringify(data1));*/
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./tariff/add",
															data : JSON
																	.stringify(data1),
															dataType : "JSON",

															success : function(
																	data) {
																/*alert("data"
																		+ JSON
																				.stringify(data));*/
																if (data.result == "Success") {

																	/*alert( "data"
																			+ data.result);*/
																	
																	swal.fire({
																		  title: "Saved",
																		  text: data.Message,
																		  icon: "success"
																		}).then(function() {
																		    window.location = "tariff.jsp";
																		    
																		});
																	return false;
																	

																} else {
																	swal.fire({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "tariff.jsp";
																		    
																		});
																	return false;

																}
															}
														});
												return false;
											});
							
							
							
							
										$(document).on('click', '#tariffEditsave', function () {

										var data1 = {}
										
										data1["tariffName"] = $("#tariffNameEdit").val();
										data1["tariff"] = $("#tariffRateEdit").val();
										data1["emergencyCredit"] = $("#emergencyCreditEdit").val();
										data1["alarmCredit"] = $("#alarmCreditEdit").val();
										data1["fixedCharges"] = $("#fixedChargeEdit").val();
								
										/*alert("===>"
												+ JSON.stringify(data1));*/
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./tariff/edit/"+$("#tariffIdhidden").val(),
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(
															data) {
														
														if (data.result == "Success") {

															swal.fire({
																  title: "Saved",
																  text: data.Message,
																  icon: "success"
																}).then(function() {
																    window.location = "tariff.jsp";
																    
																});
															return false;
															

														} else if(data.result == "Failure"){
															
															swal.fire({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "tariff.jsp";
																    
																});
															return false;
														}else {
															swal.fire({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "tariff.jsp";
																    
																});
															return false;

														}
													}
												});
										return false;
									});
							
							
							
						});


function getTariffFormEdit(id) {

//	 alert(id);

	$.getJSON("./tariff", function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.tariffID) {
				$('#tariffNameEdit').val(item.tariffName).trigger("change");
				$("#formtariffName").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#tariffRateEdit').val(item.tariff).trigger("change");
				$("#formtariffRate").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#emergencyCreditEdit').val(item.emergencyCredit).trigger("change");
				$("#formemergencyCredit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			    $('#alarmCreditEdit').val(item.alarmCredit).trigger("change");
				$("#formalarmCredit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				$('#fixedChargeEdit').val(item.fixedCharges).trigger("change");
				$("#formfixedCharge").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				$("#tariffIdhidden").val(item.tariffID);
			
				$('#tariffEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myTariffEdit').modal('show');
	});
}


function  getDeleteTraiff(id){
	
	swal.fire({
		  title: "Are you sure?",
		  text: "ARE YOU SURE TO DELETE Tariff!",
		  icon: "warning",
		  showCancelButton: true,
		  confirmButtonClass: "btn-danger",
		  confirmButtonText: "Yes, delete it!",
		  cancelButtonText: "No, cancel!",
		  closeOnConfirm: false,
		  closeOnCancel: false
		}).then(
		function(isConfirm) {
		  if (isConfirm.isConfirmed) {
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "./tariff/delete/" + id,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							swal.fire({
								  title: "Deleted",
								  text: data.Message,
								  icon: "success"
								}).then(function() {
								    window.location = "tariff.jsp";
								});
							return false;

						} else {
							swal.fire({
								  title: "error",
								  text: data.Message,
								  icon: "error"
								}).then(function() {
								    window.location = "tariff.jsp";
								    
								});
							return false;
						}
					}
				});
			}else if(result==false){
				 swal.fire("Cancelled", "Your Tariff Details is safe :)", "error");
			}
		});
	/*
	
*/	
	
}