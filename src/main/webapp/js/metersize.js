/**
 * 
 */


$(document).ready(function() {
	if(sessionStorage.getItem("roleID") == 1){
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
	}else{
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
		
	}
	
table = $('#metersizeTable')
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
	"scrollX" : false,
"ajax" : {
"url":"./metersize",
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
"data" : "meterType"
},{
"data" : "meterSize"
},{
"data" : "perUnitValue"
},{
"data" : "modifiedDate"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		return "<div id=tdfiled><a href=# id=metersizeEdit data-toggle=modal data-target=#mymetersizeEdit onclick='getMeterSizeFormEdit("
																	+ row.meterSizeID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>" +
																	"<a onclick='getMeterFormDelete(\""
																	+ row.meterSizeID
																	+ "\")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer;'>delete</i>"
																	+ "</a></div>"
	}
	}



],
"columnDefs" : [ {
	targets: 4, visible: ((sessionStorage.getItem("roleID") == 1) && ((sessionStorage.getItem("roleID") == 1) && !(sessionStorage.getItem("roleID") == 2) || !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4) || !(sessionStorage.getItem("roleID") == 5)))  
},
{
	"className": "dt-center", "targets": "_all"
}], "buttons": [
   
],
language: {
    paginate: {
      next: '<i class="fa fa-angle-right"></i>', // or '→'
      previous: '<i class="fa fa-angle-left"></i>;' // or '←' 
    }
  }

});
$("div.headname").html('<h3>Meter Size</h3>');

$("div.addevent").html('<button type="button" id="metersizepopup"' 
		+'class="btn btn-raised btn-primary float-right"'
		+'	data-toggle="modal" data-target="#exampleModal">'
		+'<i class="fa fa-plus">Add</i>'
		+'</button>');


});






$(document)
				.ready(
						function() {
								$('#metersizeDetails')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													meterSizeAdd : {
														message : 'Meter Size is not valid',
														validators : {
															notEmpty : {
																message : 'Meter Size is required and cannot be empty'
															},
															stringLength : {
																min : 1,
																max : 4,
																message : 'Meter Size must be more than 1 and less than 4 characters long'
															},
															regexp : {
																regexp : /^[0-9]*$/,
																message : 'Meter size can only consist of number'
															}
														}
													},
													perUnitValueAdd : {
														message : 'Per unit value is not valid',
														validators : {
															notEmpty : {
																message : 'Per unit value is required and cannot be empty'
															},
															stringLength : {
																min : 1,
																max : 4,
																message : 'Per unit value must be more than 1 and less than 4 characters long'
															},
															regexp : {
																regexp : /^(0|[1-9]\d*)(\.\d+)?$/,
																message : 'Per Unit Value can only consist of number'
															}
														}
													}
												}
											});
							
							
							
							
							$('#meterSizeEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											meterSizeeEdit : {
												message : 'Meter size is not valid',
												validators : {
													notEmpty : {
														message : 'Meter size is required and cannot be empty'
													},
													stringLength : {
														min :1,
														max : 4,
														message : 'Meter size must be more than 1 and less than 4 characters long'
													},
													regexp : {
														regexp : /^[0-9]*$/,
														message : 'Meter size can only consist of number'
													}
												}
											},
											perUnitValueEdit : {
												message : 'Per Unit Value is not valid',
												validators : {
													notEmpty : {
														message : 'Per Unit Value is required and cannot be empty'
													},
													stringLength : {
														min : 1,
														max : 4,
														message : 'Per Unit Value must be more than 1 and less than 4 characters long'
													},
													regexp : {
														regexp : /^(0|[1-9]\d*)(\.\d+)?$/,
														message : 'Per Unit Value can only consist of number'
													}
												}
											}
										}
									});
							
							
							
							

							$('#metersizeDetails')
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
													$('#metersizeAdd', $(this))
															.attr('disabled',
																	false);
												} else {
													$('#metersizeAdd', $(this))
															.attr('disabled',
																	true);
												}
											});

							
							
							
							$('#meterSizeEdit').on(
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
											$('#metersizeEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#metersizeEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
												$(document).on('click', '#metersizeAdd', function () {

												var data1 = {}
												data1["meterType"] = $("#meterTypeAdd")
														.val();
												data1["meterSize"] = $("#meterSizeAdd").val();
												data1["perUnitValue"] = $("#perUnitValueAdd")
												.val();
												data1["gatewayPort"] = $("#gatewayPortAdd").val();
										
												$('#metersizeAdd').prop('disabled', true).addClass('disabled').off( "click" );
												
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./metersize/add",
															data : JSON
																	.stringify(data1),
															dataType : "JSON",

															success : function(
																	data) {
																
																if (data.result == "Success") {

																	/*alert( "data"
																			+ data.result);*/
																	swal.fire({
																		  title: "Saved",
																		  text: data.Message,
																		  icon: "success"
																		}).then(function() {
																		    window.location = "MeterSize.jsp";
																		});
																	return false
																	

																} else if(data.result == "Failure"){
																	
																	swal.fire({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "MeterSize.jsp";
																		});
																	return false;
																}
															}
														});
												return false;
											});
							
							
							
										$(document).on('click', '#metersizeEditsave', function () {
											
										var data1 = {}
										data1["meterType"] = $("#meterTypeEdit")
												.val();
										data1["meterSize"] = $("#meterSizeeEdit").val();
										data1["perUnitValue"] = $("#perUnitValueEdit")
										.val();
								
										$('#metersizeEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./metersize/edit/"+$("#meterSizeIDhidden").val(),
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(
															data) {
														
														if (data.result == "Success") {

															/*alert( "data"
																	+ data.result);*/
															
															swal.fire({
																  title: "Saved",
																  text: data.Message,
																  icon: "success"
																}).then(function() {
																    window.location = "MeterSize.jsp";
																});
															return false;
															

														} else if(data.result == "Failure"){
															
															swal.fire({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "MeterSize.jsp";
																    return false;
																});
															
														}
													}
												});
										return false;
									});
							
							
						});




function getMeterSizeFormEdit(id) {

//	 alert(id);

	$.getJSON("./metersize", function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.meterSizeID) {
				$('#meterTypeEdit').val(item.meterType).trigger("change");
				$("#formmeterType").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#meterSizeeEdit').val(item.meterSize).trigger("change");
				$("#formmeterSize").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#perUnitValueEdit').val(item.perUnitValue).trigger("change");
				$("#formperUnitValue").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			   
				$("#meterSizeIDhidden").val(item.meterSizeID);
			
				$('#metersizeEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#mymetersizeEdit').modal('show');
	});
}



function getMeterFormDelete(meterSizeId){
	
	bootbox
	.confirm(
			"ARE YOU SURE TO DELETE Meter Size",
		function(
			result) {
			//	alert(result);
			if(result == true){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "./metersize/delete/" + meterSizeId,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "MeterSize.jsp";
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