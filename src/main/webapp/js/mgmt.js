/**
 * 
 */


$(document).ready(function() {
table = $('#mgmtTable')
.DataTable(
{//'Pfrtip'
	"dom": "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
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
"url":"./user/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
"type" : "GET",
"data" : function(search) {
},
"complete" : function(json) {
	console.log(json);
return json.data;
},
},
"columns" : [
 /*{
    	"data" : "communityName"
 },*/{
"data" : "userID"
},{
"data" : "emailID"
},{
"data" : "mobileNumber"
},{
"data" : "userName"
},{
"data" : "role"
},{
"data" : "communityName"
},{
"data" : "blockName"
},{
"data" : "createdByUserName"
},{
"data" : "createdByRoleDescription"
}
],
"columnDefs" : [ {
"className": "dt-center", "targets": "_all"
}], "buttons": [
   /* 'csvHtml5',
	'excelHtml5',
'pdfHtml5'*/
	
	/*{extend: 'excel',
        footer: 'true',
        text: 'Excel',
        title:'Statistics'  },
         
        {extend: 'pdf',
        footer: 'true',
        exportOptions: {
            columns: [1,2,3,4,5,6,7,8,9,10,11,12]
        },
        text: 'pdf',
        orientation: 'landscape',
        title:'Statistics'  }*/
]
});

$("div.headname").html('<h3>User Management</h3>');
$("div.addevent").html('<button type="button" class="btn btn-raised btn-primary float-right" data-toggle="modal" data-target="#exampleModal">	<i class="fa fa-plus">Add</i>	</button>');


});






$(document)
				.ready(
						function() {
							$('#userDetails').bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													
													selecttypeofuser: {
									                    validators: {
									                        notEmpty: {
									                            message: 'Please select type of User.'
									                        }
									                    }
									                },
									                selectcommunityName: {
									                    validators: {
									                        notEmpty: {
									                            message: 'Please select Community.'
									                        }
									                    }
									                },
													
									                selectBlockBasedonCommunity : {
									                	 validators: {
										                        notEmpty: {
										                            message: 'Please select Block.'
										                        }
										                    }
													},
													userIDAdd : {
														message : 'Email is not valid',
														validators : {
															notEmpty : {
																message : 'Email is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Community Address can only consist of alphabetical and number'
															}*/
														}
													},
													userIDAdd : {
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
													userIDAdd : {
														message : 'User ID is not valid',
														validators : {
															notEmpty : {
																message : 'User ID is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'User Id must be more than 2 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'User Id can only consist of alphabetical and number'
															}
														}
													},
													mobileAdd : {
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
													emailAdd : {
														message : 'Email is not valid',
														validators : {
															notEmpty : {
																message : 'Email is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Community Address can only consist of alphabetical and number'
															}*/
														}
													},
													userNameAdd : {
														message : 'User Name is not valid',
														validators : {
															notEmpty : {
																message : 'User Name is required and cannot be empty'
															}
														}
													},
													userPasswordAdd : {
														message : 'Password is not valid',
														validators : {
															notEmpty : {
																message : 'Password is required and cannot be empty'
															}
														}
													},
													confirmPasswordAdd : {
														message : 'Confirm Password is not valid',
														validators : {
															notEmpty : {
																message : 'Confirm Password is required and cannot be empty'
															}
														}
													}
												}
											});
							
							
							$('#userDetails')
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

							
							
							
							
							
							
							$("#userAdd")
									.click(
											function() {

												var data1 = {}
												data1["type"] = $("#selecttypeofuser").val();
												
//											alert($("#selectBlockBasedonCommunity").val() +"@@"+$("#selectcommunityName").val())
												
											
												
												if($("#selecttypeofuser").val() == "4"){
													
													
													
												}else if(($("#selecttypeofuser").val() == "5")) {
													
													if ($("#selectcommunityName").val() == "-1") {
														
														bootbox
														.alert("Select Community Id");
														return false;
													}

													if ($("#selectBlockBasedonCommunity").val() == "null" || $("#selectBlockBasedonCommunity").val() == "Select Block") {

														bootbox
														.alert("Select Block Name");
														return false;
													}
													
													data1["communityID"] = $(
													"#selectcommunityName").val();
													data1["blockID"] = $(
													"#selectBlockBasedonCommunity")
													.val();
													
												} 
											
											if ($("#userPasswordAdd").val() != $("#confirmPasswordAdd").val()) {
												//	alert("inpasswordelse");
												bootbox
												.alert("Confirm Password Does not Match!");
														return false;
											}
												
												data1["userID"] = $("#userIDAdd").val();
												data1["userName"] = $("#userNameAdd").val();
												
												data1["emailID"] = $("#emailAdd").val();
												data1["mobileNumber"] = $("#mobileAdd").val();
												
												data1["userPassword"] = $("#userPasswordAdd").val();
												data1["confirmPassword"] = $("#confirmPasswordAdd").val();
												data1["roleID"] = $("#selecttypeofuser").val();
												data1["createdByID"] = sessionStorage.getItem("createdByID");
												data1["loggedInUserID"] = sessionStorage.getItem("userID");
												data1["loggedInRoleID"] = sessionStorage.getItem("roleID");
												
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./user/add",
															data : JSON
																	.stringify(data1),
															dataType : "JSON",

															success : function(
																	data) {
																if (data.result == "Success") {

																	
																	bootbox.alert(data.Message,
																			function(
																					result) {
																					
																		//alert();
																		window.location = "Mgmt.jsp";
																		return false
																	});
																	
																	

																} else if(data.result == "Failure"){
																	
																	bootbox.alert(data.Message,
																			function(
																					result) {
																					
																		//alert();
																		window.location = "Mgmt.jsp";
																		return false
																				});
																}
															}
														});
												return false;
											});
						});


