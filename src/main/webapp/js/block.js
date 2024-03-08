
/**
 * 
 */


$(document).ready(function() {
	if(sessionStorage.getItem("roleID") == 1){
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
	}else{
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
		
	}
table = $('#blockTable')
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
"url":"./block/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
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
"data" : "communityName"
},{
"data" : "blockName"
},{
"data" : "Location"
},{
"data" : "email"
}
,{
"data" : "mobile"
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
		
		return "<div id = actionfield> <a href=# id=BlockEdit data-toggle=modal data-target=#myBlockEdit onclick='getBlockFormEdit("
																	+ row.blockID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>"
																	+"<a onclick='getBlockFormDelete("
																	+ row.blockID
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

$("div.headname").html('<h3>Block Management</h3>');

$("div.addevent").html('<button type="button" id="blockAddButton"'
		+'class="btn btn-raised btn-primary float-right"'
			+'data-toggle="modal" data-target="#exampleModal">'
			+'<i class="fa fa-plus">Add</i>'
			+'</button>');

});






$(document)
				.ready(
						function() {
							$('#blockDetails')
							
							/*.find('[name="selectcommunityName"]')
            .selectpicker()
            .change(function(e) {
                $('#blockDetails').bootstrapValidator('revalidateField', 'selectcommunityName');
            })
            .end()*/
							
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													/*selectcommunityName: {
									                    validators: {
									                        notEmpty: {
									                            message: 'Please select your native language.'
									                        }
									                    }
									                },*/
													blockNameAdd : {
														message : 'Block Name is not valid',
														validators : {
															notEmpty : {
																message : 'Block Name is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 35,
																message : 'Block Name must be more than 2 and less than 35 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
																message : 'Block Name can only consist of Alphanumaric'
															}
														}
													},
													blockLocationAdd : {
														message : 'Location is not valid',
														validators : {
															notEmpty : {
																message : 'Location is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 35,
																message : 'Locaton must be more than 2 and less than 35 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z ]+$/,
																message : 'Location can only consist of alphabetical'
															}
														}
													},
													blockMobileAdd : {
														message : 'Mobile is not valid',
														validators : {
															notEmpty : {
																message : 'Mobile is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]{10}$/,
																message : 'Mobile can only consist of number'
															}
														}
													},
													blockEmailAdd : {
														message : 'Email is not valid',
														validators : {
															notEmpty : {
																message : 'Email is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'Community Address can only consist of alphabetical and number'
															}*/
														}
													}
												}
											});
							
							
							
							
							$('#blockEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											blockNameEdit : {
												message : 'Block Name is not valid',
												validators : {
													notEmpty : {
														message : 'Block Name is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 35,
														message : 'Block Name must be more than 2 and less than 35 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'Block Name can only consist of Alphanumaric'
													}
												}
											},
											blockLocationEdit : {
												message : 'Location is not valid',
												validators : {
													notEmpty : {
														message : 'Location is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 35,
														message : 'Location must be more than 2 and less than 35 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z ]+$/,
														message : 'Location can only consist of alphabetical'
													}
												}
											},
											blockMobileEdit : {
												message : 'Mobile is not valid',
												validators : {
													notEmpty : {
														message : 'Mobile is required and cannot be empty'
													},
													regexp : {
														regexp : /^\d{10}$/,
														message : 'Mobile can only consist of number'
													}
												}
											},
											blockEmailEdit : {
												message : 'Email is not valid',
												validators : {
													notEmpty : {
														message : 'Email is required and cannot be empty'
													}/*,
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'Community Address can only consist of alphabetical and number'
													}*/
												}
											}
										}
									});
							
							
							
							

							$('#blockDetails')
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

							
							
							
							$('#blockEdit').on(
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
											$('#blockEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#blockEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
							
												$(document).on('click', '#blockAdd', function () {
													 
												
												//alert(""+$("#selectcommunityName").val());

												if($("#selectcommunityName").val() == -1 || $("#selectcommunityName").val() == null || $("#selectcommunityName").val() == "Select Community"){
													bootbox
													.alert("Select Community Id");
													return false;
												}
												
												var data1 = {}
												data1["communityID"] = $("#selectcommunityName").val();
												data1["blockName"] = $("#blockNameAdd").val();
												data1["location"] = $("#blockLocationAdd").val();
												data1["mobileNumber"] = $("#blockMobileAdd").val();
												data1["email"] = $("#blockEmailAdd").val();
												data1["createdByID"] = sessionStorage.getItem("createdByID");
												data1["loggedInUserID"] = sessionStorage.getItem("userID");
												data1["roleID"] = sessionStorage.getItem("roleID");

												$('#blockAdd').prop('disabled', true).addClass('disabled').off( "click" );
												$("#loader").show();
												/*console.log("===>"
														+ JSON.stringify(data1));*/
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./block/add",
															data : JSON
																	.stringify(data1),
															dataType : "JSON",

															success : function(
																	data) {
																$("#loader").hide();
																if (data.result == "Success") {

																	swal.fire({
																		  title: "Saved",
																		  text: data.Message,
																		  icon: "success"
																		}).then(function() {
																		    window.location = "blockDetails.jsp";
																		    
																		});
																	return false;
																
																	

																} else if(data.result == "Failure"){
									
																	swal.fire({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "blockDetails.jsp";
																		    
																		});
																	return false;
																	
																	
																}else {
																	
																	swal.fire({
																		  title: "error",
																		  text: "Something went Wrong",
																		  icon: "error"
																		}).then(function() {
																		    window.location = "blockDetails.jsp";
																		    
																		});
																	
																}
															}
														});
												return false;
											});
							
							
							
							$("#blockEditsave")
							.click(
									function() {

										var data1 = {}
										
										var data1 = {}
										data1["blockName"] = $("#blockNameEdit").val();
										data1["location"] = $("#blockLocationEdit").val();
										data1["mobileNumber"] = $("#blockMobileEdit").val();
										data1["email"] = $("#blockEmailEdit").val();
										data1["createdByID"] = sessionStorage.getItem("createdByID");
										data1["loggedInUserID"] = sessionStorage.getItem("userID");
										data1["roleID"] = sessionStorage.getItem("roleID");
								
										/*alert("===>"
												+ JSON.stringify(data1));*/
										$('#blockEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										$("#loader").show();
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./block/edit/"+$("#blockIdhidden").val(),
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(
															data) {
														/*alert("data"
																+ JSON
																		.stringify(data));*/
														$("#loader").hide();
														if (data.result == "Success") {

															/*alert( "data"
																	+ data.result);*/
															
															swal.fire({
																  title: "Saved",
																  text: data.Message,
																  icon: "success"
																}).then(function() {
																    window.location = "blockDetails.jsp";
																    
																});
															return false;
															

														} else if(data.result == "Failure"){
															
															swal.fire({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "blockDetails.jsp";
																    
																});
															return false;
															//});
														}
													}
												});
										return false;
									});
							
							
						});




function getBlockFormEdit(id) {

 // alert(id);

	$.getJSON("./block/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.blockID) {
				
				$('#communityNameEdit').val(item.communityName).trigger("change");
				$("#formcomunityName").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#blockNameEdit').val(item.blockName).trigger("change");
				$("#formblockName").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#blockLocationEdit').val(item.Location).trigger("change");
				$("#formblocklocation").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			    $('#blockMobileEdit').val(item.mobile).trigger("change");
				$("#formblockMobile").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#blockEmailEdit').val(item.email).trigger("change");
				$("#formblockEmail").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$("#blockIdhidden").val(item.blockID);
			
				$('#blockEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myBlockEdit').modal('show');
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