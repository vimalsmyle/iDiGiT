
/**
 * 
 */


$(document).ready(function() {
	if(sessionStorage.getItem("roleID") == 1){
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}else{
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
		
	}
table = $('#gatewayTable')
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
"ajax" : {
"url":"./gateway",
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
"data" : "gatewayName"
},{
"data" : "gatewaySerialNumber"
},{
"data" : "gatewayIP"
},{
"data" : "gatewayPort"
}
,{
"data" : "modifiedDate"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		/*<button type="button"
			class="btn btn-raised btn-primary float-right"
			data-toggle="modal" data-target="#exampleModal">
			<i class="fa fa-user"></i>
		</button>*/
	//return "<a href='#communityEditModal' class='teal modal-trigger' data-toggle='modal' data-target='#communityEditModal' id='communityEditModal' onclick='getSocietyFormEdit("+row.communityID+")'><i class='material-icons' style='color:#17e9e9'>edit</i></a>"
		
		return "<div id = actionfield> <a href=# id=GatewayEdit data-toggle=modal data-target=#myBlockEdit onclick='getGatewayFormEdit("
																	+ row.gatewayID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>"
																	+"<a onclick='getGatewayFormDelete("
																	+ row.gatewayID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer;'>delete</i>"
																	+ "</a></div>"
	}
	}



],
"columnDefs" : [ {
//	orderable : false,
	targets: 5, visible: (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4) || !(sessionStorage.getItem("roleID") == 5)))
},
{
	"className": "dt-center", "targets": "_all"
}], "buttons": [
	   
	]
});

$("div.headname").html('<h3>Gateway Management</h3>');

$("div.addevent").html('<button type="button" id="gatewayAddButton"'
		+'class="btn btn-raised btn-primary float-right"'
			+'data-toggle="modal" data-target="#exampleModal">'
			+'<i class="fa fa-plus">Add</i>'
			+'</button>');

});






$(document)
				.ready(
						function() {
							$('#gatewayDetails')
							
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													gatewayNameAdd : {
														message : 'Gateway Name is not valid',
														validators : {
															notEmpty : {
																message : 'Gateway name is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'Gateway name must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
																message : 'Gateway name can only consist of Alphanumaric'
															}
														}
													},
													serialNumberAdd : {
														message : 'Serial Number is not valid',
														validators : {
															notEmpty : {
																message : 'Serial Number is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'Serial Number must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'Serial Number can only consist of alphanumaric'
															}
														}
													},
													ipAdd : {
														message : 'ip is not valid',
														validators : {
															notEmpty : {
																message : 'IP is required and cannot be empty'
															},
															regexp : {
																regexp : /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/,
																message : 'IP can only consist of numbers with point'
															}
														}
													},
													portAdd : {
														message : 'Port is not valid',
														validators : {
															notEmpty : {
																message : 'Port is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 4,
																message : 'Port number must be 4'
															},
															regexp : {
																regexp : /^[0-9]+$/,
																message : 'Port number can only consist of number'
															}
														}
													}
												}
											});
							
							
							
							
							$('#gatewayEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											gatewayNameEdit : {
												message : 'Gateway Name is not valid',
												validators : {
													notEmpty : {
														message : 'Gateway Name is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'Gateway Name must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'Gateway Name can only consist of Alphanumaric'
													}
												}
											},
											serialNumberEdit : {
												message : 'Serial Number is not valid',
												validators : {
													notEmpty : {
														message : 'Serial Number is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'Serial Number must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'Serial Number can only consist of alphanumaric'
													}
												}
											},
											ipEdit : {
												message : 'IP is not valid',
												validators : {
													notEmpty : {
														message : 'IP is required and cannot be empty'
													},
													regexp : {
														regexp : /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/,
														message : 'IP can only consist of numbers with point'
													}
												}
											},
											portEdit : {
												message : 'Port is not valid',
												validators : {
													notEmpty : {
														message : 'Port is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 4,
														message : 'Port Number must be 4'
													},
													regexp : {
														regexp : /^[0-9]+$/,
														message : 'Port Number can only consist of number'
													}
												}
											}
										}
									});
							
							
							
							

							$('#gatewayDetails')
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
																	false).attr('class', 'btn btn-success submit-button');;
												} else {
													$('.submit-button', $(this))
															.attr('disabled',
																	true);
												}
											});

							
							
							
							$('#gatewayEdit').on(
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
											$('#gatewayEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#gatewayEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
							
												$(document).on('click', '#gatewayAdd', function () {
													 
												var data1 = {}
												data1["gatewayName"] = $("#gatewayNameAdd").val();
												data1["gatewaySerialNumber"] = $("#serialNumberAdd").val();
												data1["gatewayIP"] = $("#ipAdd").val();
												data1["gatewayPort"] = $("#portAdd").val();
												data1["createdByID"] = sessionStorage.getItem("createdByID");
												data1["loggedInUserID"] = sessionStorage.getItem("userID");
												data1["roleID"] = sessionStorage.getItem("roleID");

												$('#gatewayAdd').prop('disabled', true).addClass('disabled').off( "click" );
												
												/*console.log("===>"
														+ JSON.stringify(data1));*/
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./gateway/add",
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
																		    window.location = "gateway.jsp";
																		    
																		});
																	return false;
																
																	

																} else if(data.result == "Failure"){
									
																	swal.fire({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "gateway.jsp";
																		    
																		});
																	return false;
																	
																	
																}else {
																	
																	swal.fire({
																		  title: "error",
																		  text: "Something went Wrong",
																		  icon: "error"
																		}).then(function() {
																		    window.location = "gateway.jsp";
																		    
																		});
																	
																}
															}
														});
												return false;
											});
							
							
							
							$("#gatewayEditsave")
							.click(
									function() {

										var data1 = {}
										
										var data1 = {}
										data1["gatewayName"] = $("#gatewayNameEdit").val();
										data1["gatewaySerialNumber"] = $("#serialNumberEdit").val();
										data1["gatewayIP"] = $("#ipEdit").val();
										data1["gatewayPort"] = $("#portEdit").val();
										data1["createdByID"] = sessionStorage.getItem("createdByID");
										data1["loggedInUserID"] = sessionStorage.getItem("userID");
										data1["roleID"] = sessionStorage.getItem("roleID");
								
										/*alert("===>"
												+ JSON.stringify(data1));*/
										$('#blockEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./gateway/edit/"+$("#gatewayIdhidden").val(),
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
																    window.location = "gateway.jsp";
																    
																});
															return false;
															

														} else if(data.result == "Failure"){
															
															swal.fire({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "gateway.jsp";
																    
																});
															return false;
															//});
														}
													}
												});
										return false;
									});
							
							
						});




function getGatewayFormEdit(id) {

 // alert(id);

	$.getJSON("./gateway", function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.gatewayID) {
				
				$('#gatewayNameEdit').val(item.gatewayName);
				$("#formgatewayName1").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#serialNumberEdit').val(item.gatewaySerialNumber);
				$("#formserialNumber").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#ipEdit').val(item.gatewayIP);
				$("#formip").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			    $('#portEdit').val(item.gatewayPort);
				$("#formport").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$("#gatewayIdhidden").val(item.gatewayID);
			
				$('#gatewayEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myGatewayEdit').modal('show');
	});
}



function getBlockFormDelete(blockId){
	
	swal.fire({
		  title: "Are you sure?",
		  text: "ARE YOU SURE TO DELETE BLOCK!",
		  icon: "warning",
		  showCancelButton: true,
		  confirmButtonClass: "btn-danger",
		  confirmButtonText: "Yes, delete it!",
		  cancelButtonText: "No, cancel plx!",
		  closeOnConfirm: false,
		  closeOnCancel: false
		}).then(
		function(isConfirm) {
		  if (isConfirm.isConfirmed) {
			  
			  $.ajax({
					type : "POST",
					contentType : "application/json",
					url : "./block/delete/" + blockId,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							swal.fire({
								  title: "Deleted",
								  text: data.Message,
								  icon: "success"
								}).then(function() {
								    window.location = "blockDetails.jsp";
								});
							return false;

						} else {
							swal.fire({
								  title: "error",
								  text: data.Message,
								  icon: "error"
								}).then(function() {
								    window.location = "blockDetails.jsp";
								    
								});
							return false;
						}
					}
				});
		  } else {
		    swal.fire("Cancelled", "Your Block Details is safe :)", "error");
		  }
		});

	
}

function getGatewayFormDelete(val){
	bootbox
	.confirm(
			"ARE YOU SURE TO DELETE Gateway",
		function(
			result) {
			//	alert(result);
			if(result == true){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "./gateway/delete/" + val,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "gateway.jsp";
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
				
			}
		});
}
