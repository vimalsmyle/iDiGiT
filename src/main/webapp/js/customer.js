/**
 * 
 */
$(document).ready(function() {

	
	
	
	$.getJSON("./customer/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1", function(data) {
	$.each(data.data, function(i, item) {
		if ($("#custUniqueId").val() == item.CustomerUniqueID) {
			
			var GasOptions;
			var WaterOptions;
			
			
			var rowCount = 0;
			var rowCountArray=[];
			$('#communityNameEdit').val(item.communityName).trigger("change");
			$("#formcommunityNameEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			$('#blockNameEdit').val(item.blockName).trigger("change");
			$("#formblockNameEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			$('#firstNameEdit').val(item.firstName).trigger("change");
			$("#formfirstNameEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			$('#lastNameEdit').val(item.lastName).trigger("change");
			$("#formlastNameEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			$('#houseNoEdit').val(item.houseNumber).trigger("change");
			$("#formhouseNoEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			
			$('#mobileNoEdit').val(item.mobileNumber).trigger("change");
			$("#formmobileNoEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			
			$('#emailEdit').val(item.email).trigger("change");
			$("#formemailEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			if(sessionStorage.getItem("roleID") == 3){
				$('#amrEdit')
				.attr('disabled',
						true);
			}
			$('#CRNEdit').val(item.CustomerUniqueID).trigger("change");
			$("#formCRNEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		    
			$("#customerIdhidden").val(item.CRNNumber);
		
			$('#customerEditsave')
			.attr('disabled',
					false);
			if(sessionStorage.getItem("roleID") == 3){
			
				$('#amrEdit', '#amrEdit')
				.attr('disabled',
						true);
				$('#houseNoEdit')
				.attr('disabled',
						true);
				
			}
			
			$.getJSON("./gateways", function(data) {
				var OptionsGatewayEdit = "<option value='-1'>Select  Gateway</option>";
				$.each(data.dropDownGateways, function(key, value) {
					OptionsGatewayEdit = OptionsGatewayEdit + "<option value='" + key + "'>" + value
							+ "</option>";
					
					
					/*$.getJSON("./metersizes/Gas", function(data) {
						 GasOptions = '<option value=-1>Select  Meter Size</option>';
						$.each(data.dropDownMeterSizes, function(key, value) {
							GasOptions = GasOptions + '<option value=' + key + '>' + value
									+ '</option>';
						});
					});
					
					$.getJSON("./metersizes/Water", function(data) {
						 WaterOptions = '<option value=-1>Select  Meter Size</option>';
						$.each(data.dropDownMeterSizes, function(key, value) {
							WaterOptions = WaterOptions + '<option value=' + key + '>' + value
									+ '</option>';
						});
					});*/
				});
				
				
			
			$.each(item.meters, function(i, meter) {
				rowCount++;
				
				$("#template").append("<div class=row> " +
						"<div class=col-md-4>" +
						"<div id='formmiuIDAdd' class='group form-group has-feedback has-success bmd-form-group is-filled'>"
										+"<label class=bmd-label-floating>MIU ID<span class=impp><sup>*</sup></span></label> <input "
										+"type=text class='form-control form-control-sm' name=miuIDAdd["+rowCount+"]"
										+" id=miuIDAdd-"+rowCount+">"
										+"</div></div>"+
							"<div class=col-md-4>" +
							"<div id='formmeterSerialNumberAdd' class='group form-group has-feedback has-success bmd-form-group is-filled'>"
							+"<label class=bmd-label-floating>Meter Serial Number<span class=impp><sup>*</sup></span></label> <input "
							+"type=text class='form-control form-control-sm' name=meterSerialNumberAdd["+rowCount+"]"
							+" disabled id=meterSerialNumberAdd-"+rowCount+">"
							+"<input type='hidden' id=customerMeterIDAdd-"+rowCount+"></div></div>"+
						"<div class=col-md-4>" +
						"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
						+"<label class=bmd-label-floating>Meter Type<span class=impp><sup>*</sup></span></label> " +
						"<select class='form-control form-control-sm select2'  disabled id=selectMeterType-"+rowCount+" name=selectMeterType["+rowCount+"]>"+
							"<option value='-1'>Select Meter Type</option>"+
							"<option value='Gas'>Gas</option>"+
							"<option value='Water'>Water</option>"+
							"<option value='Energy'>Energy</option>"+
						"</select>"
						+"</div></div>"+
						"<div class=col-md-4>" +
						"<div id='formmeterSizeEdit' class='group form-group has-feedback has-success bmd-form-group is-filled'>"
						+"<label class=bmd-label-floating>Meter Size<span class=impp><sup>*</sup></span></label> " +
						"<select class='form-control form-control-sm select2'   id=meterSizeAdd-"+rowCount+" name=meterSizeAdd["+rowCount+"]>"+
						"</select>"
						+"</div></div>"+
						"<div class=col-md-4>" +
						"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
						+"<label class=bmd-label-floating>Pay Type<span class=impp><sup>*</sup></span></label>" +
						"<select class='form-control form-control-sm select2' disabled id=payTypeAdd-"+rowCount+" name=payTypeAdd["+rowCount+"]>"+
						"<option value='-1'>Select Pay Type</option>"+
						"<option value='Prepaid'>Prepaid</option>"+
						"<option value='Postpaid'>Postpaid</option>"+
					"</select>"
						+"</div></div>"+
						"<div class=col-md-4>" +
						"<div id=formtariffNameEdit class='group form-group has-feedback has-success bmd-form-group is-filled'>"
						+"<label class=bmd-label-floating>Tariff Name<span class=impp><sup>*</sup></span></label> <input "
						+"type=text class='form-control form-control-sm' name=tariffNameAdd["+rowCount+"]"
						+" disabled id=tariffNameAdd-"+rowCount+">"
						+"</div></div>"+
						"<div class=col-md-4>" +
						"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
						+"<label class=bmd-label-floating>Gateway ID<span class=impp><sup>*</sup></span></label> " +
						"<select "+
						"class='form-control form-control-sm gatewayIDAdd' id=gatewayIDAdd-"+rowCount+" name=gatewayIDAdd["+rowCount+"]>"+
						 
						"</select>"
						+"</div></div>"+
						"<div class=col-md-4>" +
						"<div id='formlocationEdit' class='group form-group has-feedback has-success bmd-form-group is-filled'>"
						+"<label class=bmd-label-floating>Location<span class=impp><sup>*</sup></span></label> <input "
						+"type=text class='form-control form-control-sm' disabled name=locationAdd["+rowCount+"]"
						+" id=locationAdd-"+rowCount+">"
						+"</div></div>   " +
						
						
						"<div class=col-md-4>" +
						"<div id='formThresholdMaximumEdit' class='group form-group has-feedback has-success bmd-form-group is-filled'>"
						+"<label class=bmd-label-floating>Threshold Maximum<span class=impp><sup>*</sup></span></label> <input "
						+"type=number class='form-control form-control-sm'  name=thresholdMaximumAdd["+rowCount+"]"
						+" id=thresholdMaximumAdd-"+rowCount+">"
						+"</div></div>   " +
						
						"<div class=col-md-4>" +
						"<div id='formthreshold MinimumEdit' class='group form-group has-feedback has-success bmd-form-group is-filled'>"
						+"<label class=bmd-label-floating>Threshold Minimum<span class=impp><sup>*</sup></span></label> <input "
						+"type=number class='form-control form-control-sm'  name=thresholdMinimumAdd["+rowCount+"]"
						+" id=thresholdMinimumAdd-"+rowCount+">"
						+"<hr></div></div>   " +
						
						
								"</div>" +
								"</div>");
				
				 
			
				
				
				$("#miuIDAdd-"+rowCount).val(meter.miuID);
				$("#customerMeterIDAdd-"+rowCount).val(meter.customerMeterID);
				$("#formmiuIDAdd").removeClass().addClass("group form-group has-feedback has-success bmd-form-group is-filled");
				$("#meterSerialNumberAdd-"+rowCount).val(meter.meterSerialNumber);
				$("#formmeterSerialNumberAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
				$("#selectMeterType-"+rowCount).val(meter.meterType);
			
				$("#formmeterSizeAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
				$("#payTypeAdd-"+rowCount).val(meter.payType);
				$("#selectTariffName-"+rowCount).val(meter.tariffID);
				$("#tariffNameAdd-"+rowCount).val(meter.tariffName);
				//$("#gatewayIDAdd-"+rowCount).val(meter.gatewayID);
				$("#locationAdd-"+rowCount).val(meter.location);
				$("#formlocationAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
				
				$("#thresholdMaximumAdd-"+rowCount).val(meter.thresholdMaximum);
				$("#formThresholdMaximumAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
				
				$("#thresholdMinimumAdd-"+rowCount).val(meter.thresholdMinimum);
				$("#formThresholdMinimumAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
				
				
				
				$('#customerAdd').bootstrapValidator('addField' ,
			        'miuIDEdit['+rowCount+']', {
			        	message : 'MUI ID is not valid',
						validators : {
							notEmpty : {
								message : 'MUI ID is required and cannot be empty'
							},
							stringLength : {
								min : 2,
								max : 30,
								message : 'MUI ID must be more than 2 and less than 30 characters long'
							},
							regexp : {
								regexp : /^[a-zA-Z0-9.,$; ]+$/,
								message : 'MUI ID can only consist of Alphanumaric'
							}
						}
			        });
				 $('#customerAdd').bootstrapValidator('addField',
			        'meterSerialNumberEdit['+rowCount+']', {
			        	message : 'Meter Serial Number is not valid',
						validators : {
							notEmpty : {
								message : 'Meter Serial Number is required and cannot be empty'
							},
							stringLength : {
								min : 2,
								max : 30,
								message : 'Meter Serial Number must be more than 2 and less than 30 characters long'
							},
							regexp : {
								regexp : /^[a-zA-Z0-9.,$; ]+$/,
								message : 'Meter Serial Number can only consist of Alphanumaric'
							}
						}
			        });
				 $('#customerAdd').bootstrapValidator('addField',
			        'meterSizeEdit['+rowCount+']', {
			        	message : 'Meter Size is not valid',
						validators : {
							notEmpty : {
								message : 'Meter Size is required and cannot be empty'
							},
							stringLength : {
								min : 1,
								max : 30,
								message : 'Meter Size must be more than 1 and less than 30 characters long'
							},
							regexp : {
								regexp : /^[0-9]+$/,
								message : 'Meter Size can only consist of Numaric'
							}
						}
			        });
				 $('#customerAdd').bootstrapValidator('addField',
			        'tariffNameEdit['+rowCount+']', {
			        	message : 'Tariff Name is not valid',
						validators : {
							notEmpty : {
								message : 'Tariff Name is required and cannot be empty'
							},
							stringLength : {
								min : 4,
								max : 30,
								message : 'Tariff Name must be more than 4 and less than 30 characters long'
							},
							regexp : {
								regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
								message : 'Tariff Name can only consist of Alphanumaric'
							}
						}
			        });
				
				 $('#customerAdd').bootstrapValidator('addField','locationEdit['+rowCount+']', {
			        	message : 'Location is not valid',
						validators : {
							notEmpty : {
								message : 'Location is required and cannot be empty'
							},
							stringLength : {
								min : 4,
								max : 30,
								message : 'Location must be more than 4 and less than 30 characters long'
							},
							regexp : {
								regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
								message : 'Location can only consist of Alphanumaric'
							}
						}
			        }
				 );
				// rowCount++;
				 rowCountArray.push(rowCount)
				 $("#rowCountArray").val(rowCountArray);				 
				 $("#rowCount").val(rowCount);
				 
				 	$("#formmiuIDAdd").removeClass().addClass("group form-group has-feedback has-success bmd-form-group is-filled");
					$("#formmeterSerialNumberAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
					$("#formmeterSizeAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
					$("#formlocationAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
					$("#formtariffNameAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
				 
					
					
					$("#gatewayIDAdd-"+rowCount).append(OptionsGatewayEdit);
					
					$("#gatewayIDAdd-"+rowCount).val(meter.gatewayID);
					
					var GasOptions;
					$.each(meter.gasDropdown, function(key, value) {
						GasOptions = GasOptions + '<option value=' + key + '>' + value
								+ '</option>';
					});
					
					var WaterOptions;
					$.each(meter.waterDropdown, function(key, value) {
						WaterOptions = WaterOptions + '<option value=' + key + '>' + value
								+ '</option>';
					});
					
					
					if(meter.meterType=="Gas"){
						$("#meterSizeAdd-"+rowCount).append(GasOptions);
						$("#meterSizeAdd-"+rowCount).val(meter.meterIDSize).change();
					}else{
						$("#meterSizeAdd-"+rowCount).append(WaterOptions);
						$("#meterSizeAdd-"+rowCount).val(meter.meterIDSize).change();
					}
					
					
					
					
					
					
					
			});
			 });
			if(rowCount>13){
				$("#meterSizeAdd-"+rowCount).val(item.meters.length);
				$("#addMeter").hide();
			}else{
				$("#meterSizeAdd-"+rowCount).val(item.meters.length);
				$("#addMeter").show();
			}
			
			
			

			
			
		} 
	});
	
	$.getJSON("./tariffs", function(data) {
		var Optionstariff = '<option value=-1>Select  Tariff</option>';
		$.each(data.dropDownTariffs, function(key, value) {
			Optionstariff = Optionstariff + '<option value=' + key + '>' + value
					+ '</option>';
		});
		$('.selectTariffName').append(Optionstariff);
	});
 
 
 
 
});
	
 
 
});


$(document).ready(function() {
	var rowCountArray=[];
	if(sessionStorage.getItem("roleID") == 1 || sessionStorage.getItem("roleID") == 2){
		if(sessionStorage.getItem("roleID") == 2){
			$("#communityNameAdd").val(sessionStorage.getItem("communityName"));
			$("#formcommunityNameAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			$("#blockNameAdd").val(sessionStorage.getItem("blockName"));
			$("#formblockNameAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		}
		$("#blockAddButton").show();
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}else{
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}
	$("#customerTable1").hide();
table = $('#customerTable')
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
"url":"./customer/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1",
"type" : "GET",
"data" : function(search) {
},
"complete" : function(json) {
	console.log(json);
	$("#action").val(json.responseJSON.data[0].action);
return json.data;
},
},
"columns" : [
{
"data" : "communityName"
},{
"data" : "blockName"
},{
"data" : "CustomerUniqueID"
},{
"data" : "firstName"
},{
"data" : "lastName"
},{
"data" : "houseNumber"
},
{
"data" : "email"
},
{
"data" : "mobileNumber"
},{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		return "<a href=# id=CustomerMeters data-toggle=modal data-target=#myCustomerMeters onclick='getCustomerMeters(\""
		+ row.CustomerUniqueID
		+ "\")'>"
		+ "Multiple"
		+ "</a>"
		
	}

}
,{
"data" : "createdByUserName"
},{
"data" : "createdByRoleDescription"
},{
"data" : "date"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		return "<a href='customerEdit.jsp?cust="+row.CustomerUniqueID+"'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>"
																	+"<a onclick='getCustomerFormDelete(\""
																	+ row.CustomerUniqueID
																	+ "\")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer;'>delete</i>"
																	+ "</a>"
																	
																	
	}
	},{
		"mData" : "action",
		"render" : function(data, type, row) {
			
			return "<a href='customerEdit.jsp?cust="+row.CustomerUniqueID+"'>"
																		+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																		+ "</a>"
																		+"<a onclick='getCustomerFormDelete(\""
																		+ row.CustomerUniqueID
																		+ "\")'>"
																		+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer;'>delete</i>"
																		+ "</a>"
		}
		}



],
"columnDefs" : [ {
	targets : 12, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4))),
},{
	targets : 13, visible: (sessionStorage.getItem("roleID") == 3)
//(!(sessionStorage.getItem("roleID") == 1) && (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4))))
},
{
	"className": "dt-center", "targets": "_all"
}], "buttons": [
	{
        className: 'customButton',
        text : "Filter",
         action: function ( e, dt, button, config ) {
         	$('.customButton').attr(
                 {
                     "data-toggle": "modal",
                     "data-target": "#filter"
                 }
             );
         }
     }
]
});

$("div.headname").html('<h3>Customer Management</h3>');

if(sessionStorage.getItem("roleID") == 3 || sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
	table.buttons( $('a.customButton') ).remove();	
}

$("div.addevent").html('<button type="button" id="customerAddd"'
		+'class="btn btn-raised btn-primary float-right"'
			+'data-toggle="modal" data-target="#exampleModal">'
			+'<i class="fa fa-plus">Add</i>'
		+'</button>');

var rowCount = $("#rowCount").val()==""?0:$("#rowCount").val();
$("#addMeter")
.click(
		function() {
			var rowCount = $("#rowCount").val()==""?0:$("#rowCount").val();
			rowCount++;
			
			if(rowCount>13){
				$("#addMeter").hide();
			}else{
				$("#addMeter").show();
			}
			
			
			$("#template").append("<div class=row> " +
									"<div class=col-md-4>" +
									"<div class='group form-group'>"
													+"<label class=bmd-label-floating>MIU ID<span class=impp><sup>*</sup></span></label> <input "
													+"type=text class='form-control form-control-sm' name=miuIDAdd["+rowCount+"]"
													+" id=miuIDAdd-"+rowCount+">"
													+"</div></div>"+
										"<div class=col-md-4>" +
										"<div class='group form-group'>"
										+"<label class=bmd-label-floating>Meter Serial Number<span class=impp><sup>*</sup></span></label> <input "
										+"type=text class='form-control form-control-sm' name=meterSerialNumberAdd["+rowCount+"]"
										+" id=meterSerialNumberAdd-"+rowCount+">"
										+"</div></div>"+
									"<div class=col-md-4>" +
									"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
									+"<label class=bmd-label-floating>Meter Type<span class=impp><sup>*</sup></span></label> " +
									"<select class='form-control form-control-sm select2'  id=selectMeterType-"+rowCount+" onchange='onChangeMeterSize("+rowCount+")' name=selectMeterType["+rowCount+"]>"+
										"<option value='-1'>Select Meter Type</option>"+
										"<option value='Gas'>Gas</option>"+
										"<option value='Water'>Water</option>"+
										"<option value='Energy'>Energy</option>"+
									"</select>"
									+"</div></div>"+
									"<div class=col-md-4>" +
									"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
									+"<label class=bmd-label-floating>Meter Size<span class=impp><sup>*</sup></span></label> <select "+
									"class='form-control form-control-sm' " +
									"id=meterSizeAdd-"+rowCount+" name=meterSizeAdd["+rowCount+"]>"+
									"</select>"
									+"</div></div>"+
									"<div class=col-md-4>" +
									"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
									+"<label class=bmd-label-floating>Pay Type<span class=impp><sup>*</sup></span></label>" +
									"<select class='form-control form-control-sm select2' id=payTypeAdd-"+rowCount+" name=payTypeAdd["+rowCount+"]>"+
									"<option value='-1'>Select Pay Type</option>"+
									"<option value='Prepaid'>Prepaid</option>"+
									"<option value='Postpaid'>Postpaid</option>"+
								"</select>"
									+"</div></div>"+
									"<div class=col-md-4>" +
									"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
									+"<label class=bmd-label-floating>Tariff Name<span class=impp><sup>*</sup></span></label> " +
									"<select "+
									"class='form-control form-control-sm' id=selectTariffName-"+rowCount+" name=selectTariffName["+rowCount+"]>"+
									 
									"</select>"
									+"</div></div>"+
									"<div class=col-md-4>" +
									"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
									+"<label class=bmd-label-floating>Gateway ID<span class=impp><sup>*</sup></span></label> " +
									"<select "+
									"class='form-control form-control-sm' id=gatewayIDAdd-"+rowCount+" name=gatewayIDAdd["+rowCount+"]>"+
									 
									"</select>"
									+"</div></div>"+
									"<div class=col-md-4>" +
									"<div class='group form-group'>"
									+"<label class=bmd-label-floating>Location<span class=impp><sup>*</sup></span></label> <input "
									+"type=text class='form-control form-control-sm' name=locationAdd["+rowCount+"]"
									+" id=locationAdd-"+rowCount+">"
									+"</div></div>   " +
									
									
									"<div class=col-md-4>" +
									"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
									+"<label class=bmd-label-floating>Threshold Maximum<span class=impp><sup>*</sup></span></label> <input "
									+"type=number class='form-control form-control-sm' name=thresholdMaximumAdd["+rowCount+"]"
									+" id=thresholdMaximumAdd-"+rowCount+">"
									+"</div></div>   " +
									
									
									"<div class=col-md-4>" +
									"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
									+"<label class=bmd-label-floating>Threshold Minimum<span class=impp><sup>*</sup></span></label> <input "
									+"type=number class='form-control form-control-sm' name=thresholdMinimumAdd["+rowCount+"]"
									+" id=thresholdMinimumAdd-"+rowCount+">"
									+"<hr></div></div>   " +
									
									
											" <div class='col-md-12 text-right'>" 
									+" 	<button class='btn btn-danger' value='Remove Meter!' onclick='$(this).removeMeter("+rowCount+")' id='removeMeter' " +
											"type='button'>Remove Meter</button>" +
											"</div></div>" +
											"</div>");

			
			
			 $('#customerDetails').bootstrapValidator('addField' ,
		        'miuIDAdd['+rowCount+']', {
		        	message : 'MUI ID is not valid',
					validators : {
						notEmpty : {
							message : 'MUI ID is required and cannot be empty'
						},
						stringLength : {
							min : 2,
							max : 30,
							message : 'MUI ID must be more than 2 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[a-zA-Z0-9.,$; ]+$/,
							message : 'MUI ID can only consist of Alphanumaric'
						}
					}
		        });
			 $('#customerDetails').bootstrapValidator('addField',
		        'meterSerialNumberAdd['+rowCount+']', {
		        	message : 'Meter Serial Number is not valid',
					validators : {
						notEmpty : {
							message : 'Meter Serial Number is required and cannot be empty'
						},
						stringLength : {
							min : 2,
							max : 30,
							message : 'Meter Serial Number must be more than 2 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[a-zA-Z0-9.,$; ]+$/,
							message : 'Meter Serial Number can only consist of Alphanumaric'
						}
					}
		        });
			 $('#customerDetails').bootstrapValidator('addField',
		        'meterSizeAdd['+rowCount+']', {
		        	message : 'Meter Size is not valid',
					validators : {
						notEmpty : {
							message : 'Meter Size is required and cannot be empty'
						},
						stringLength : {
							min : 1,
							max : 30,
							message : 'Meter Size must be more than 1 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[0-9]+$/,
							message : 'Meter Size can only consist of Numaric'
						}
					}
		        });
			 $('#customerDetails').bootstrapValidator('addField',
		        'tariffNameAdd['+rowCount+']', {
		        	message : 'Tariff Name is not valid',
					validators : {
						notEmpty : {
							message : 'Tariff Name is required and cannot be empty'
						},
						stringLength : {
							min : 4,
							max : 30,
							message : 'Tariff Name must be more than 4 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
							message : 'Tariff Name can only consist of Alphanumaric'
						}
					}
		        });
			
			 $('#customerDetails').bootstrapValidator('addField','locationAdd['+rowCount+']', {
		        	message : 'Location is not valid',
					validators : {
						notEmpty : {
							message : 'Location is required and cannot be empty'
						},
						stringLength : {
							min : 2,
							max : 30,
							message : 'Location must be more than 2 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
							message : 'Location can only consist of Alphanumaric'
						}
					}
		        }
			 );
			 
			/* $('#customerDetails').bootstrapValidator('addField','thresholdMaximumAdd['+rowCount+']', {
		        	message : 'Threshold maximum is not valid',
					validators : {
						notEmpty : {
							message : 'Threshold maximum is required and cannot be empty'
						},
						stringLength : {
							min : 1,
							max : 30,
							message : 'Threshold maximum must be more than 1 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[0-9][0-9.]+$/,
							message : 'Threshold maximum can only consist of numeric'
						}
					}
		        }
			 );
			 
			 $('#customerDetails').bootstrapValidator('addField','thresholdMinimumAdd['+rowCount+']', {
		        	message : 'Threshold minimum is not valid',
					validators : {
						notEmpty : {
							message : 'Threshold minimum is required and cannot be empty'
						},
						stringLength : {
							min : 1,
							max : 30,
							message : 'Threshold minimum must be more than 1 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[0-9][0-9.]+$/,
							message : 'Threshold minimum can only consist of numeric'
						}
					}
		        }
			 );*/
			 
			 
			 
			 rowCountArray.push(rowCount)
			 $("#rowCountArray").val(rowCountArray);		
			 $("#rowCount").val(rowCount);
			 
			 $.getJSON("./tariffs", function(data) {
					var Options = '<option value=-1>Select  Tariff</option>';
					$.each(data.dropDownTariffs, function(key, value) {
						Options = Options + '<option value=' + key + '>' + value
								+ '</option>';
					});
					$('#selectTariffName-'+rowCount).append(Options);
				});
			
			 
			 
			 $.getJSON("./gateways", function(data) {
					var Options = "<option value='-1'>Select  Gateway</option>";
					$.each(data.dropDownGateways, function(key, value) {
						Options = Options + "<option value='" + key + "'>" + value
								+ "</option>";
					});
					$('#gatewayIDAdd-'+rowCount).append(Options);
				});
			 

		});

//$("body").on("click", "#removeMeter", function(e) {

  //});



$("#customerAddd").click(function(){
	
window.location ="customer.jsp";	
	

});
});






$(document)
				.ready(
						function() {
							$('#customerDetails').bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													
													firstNameAdd : {
														message : 'First Name is not valid',
														validators : {
															notEmpty : {
																message : 'First Name is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'First Name must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
																message : 'First Name can only consist of Alphanumaric'
															}
														}
													},
													lastNameAdd : {
														message : 'Last Name is not valid',
														validators : {
															notEmpty : {
																message : 'Last Name is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'Last Name must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
																message : 'Last Name can only consist of Alphanumaric'
															}
														}
													},
													houseNoAdd : {
														message : 'House No. is not valid',
														validators : {
															notEmpty : {
																message : 'House No is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'House No must be more than 4 and less than 30 characters long'
															}
														}
													},
													mobileNoAdd : {
														message : 'Mobile No. is not valid',
														validators : {
															notEmpty : {
																message : 'Mobile No. is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]{10}$/,
																message : 'Mobile No. can only consist of number'
															}
														}
													},
													emailAdd : {
														message : 'Email is not valid',
														validators : {
															notEmpty : {
																message : 'Email is required and cannot be empty'
															}
														}
													},
									                CRNAdd : {
														message : 'CRN No. is not valid',
														validators : {
															notEmpty : {
																message : 'CRN No. is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'CRN No. must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$;]+$/,
																message : 'CRN No. can only consist of Alphanumaric'
															}
														}
													}
												}
											});
							
							
							
							
							$('#customerEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											
											firstNameEdit : {
												message : 'First Name is not valid',
												validators : {
													notEmpty : {
														message : 'First Name is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'First Name must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'First Name can only consist of Alphanumaric'
													}
												}
											},
											lastNameEdit : {
												message : 'Last Name is not valid',
												validators : {
													notEmpty : {
														message : 'Last Name is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'Last Name must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'First Name can only consist of Alphanumaric'
													}
												}
											},
											houseNoEdit : {
												message : 'House No. is not valid',
												validators : {
													notEmpty : {
														message : 'House No is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'House No. must be more than 4 and less than 30 characters long'
													}
												}
											},
											mobileNoEdit: {
												message : 'Mobile No. is not valid',
												validators : {
													notEmpty : {
														message : 'Mobile No. is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]{10}$/,
														message : 'Mobile No. can only consist of number'
													}
												}
											},
											emailEdit : {
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
											},
							                CRNEdit : {
												message : 'CRN No. is not valid',
												validators : {
													notEmpty : {
														message : 'CRN No. is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'CRN No. must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$;]+$/,
														message : 'CRN No. can only consist of Alphanumaric'
													}
												}
											}
										}
									});
							
							
							
							
							$("#customerFilter")
							.click(
									function() {

										var url = $("#filterselectcommunityName").val() == "-1" ? sessionStorage.getItem("roleID")+"/0/-1" : $("#filterselectBlockBasedonCommunity").val() == "Select Block" ? 
												$("#filterselectcommunityName").val() == "-1" ? 
												sessionStorage.getItem("roleID")+"/0/-1":sessionStorage.getItem("roleID")+"/0/"+$("#filterselectcommunityName").val():
											"2/"+$("#filterselectBlockBasedonCommunity").val()+"/-1"
												
										$
												.ajax({
													type : "GET",
													contentType : "application/json",
													url : "./customer/"+url,
													dataType : "JSON",

													success : function(d) {
														
														//if (data.result == "Success") {
														$('#customerTable').dataTable()._fnAjaxUpdate();
														//$("#form").hide();
														//$("#tablereport").show();
															console.log(JSON.stringify(d));
															$("#customerTable_wrapper").hide();
															$("#filter").modal("hide");
															$("#customerTable1").show();
															var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
															var hCols = [ 3, 4 ];
															table = $('#customerTable1')
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
																			"data" : d.data,
																			"columns" : [
																	{
																	"data" : "communityName"
																	},{
																	"data" : "blockName"
																	},{
																	"data" : "CustomerUniqueID"
																	},{
																	"data" : "firstName"
																	},{
																	"data" : "lastName"
																	},{
																	"data" : "houseNumber"
																	},{
																	"data" : "email"
																	},
																	
																	{
																	"data" : "mobileNumber"
																	},
																	{
																		"mData" : "action",
																		"render" : function(data, type, row) {
																			
																			return "<a href=# id=CustomerMeters data-toggle=modal data-target=#myCustomerMeters onclick='getCustomerMeters(\""
																			+ row.CustomerUniqueID
																			+ "\")'>"
																			+ "Multiple"
																			+ "</a>"
																			
																		}
																	
																	}
																	
																	,{
																	"data" : "createdByUserName"
																	},{
																	"data" : "createdByRoleDescription"
																	},{
																	"data" : "date"
																	}
																	,{
																		"mData" : "action",
																		"render" : function(data, type, row) {
																			
																			return "<a href=# id=CustomerEdit data-toggle=modal data-target=#myCustomerEdit onclick='getCustomerFormEdit(\""
																		}
																		},{
																			"mData" : "action",
																			"render" : function(data, type, row) {
																				
																				return "<a href=# id=CustomerEdit data-toggle=modal data-target=#myCustomerEdit onclick='getCustomerFormEdit(\""
																																			+ row.CustomerUniqueID
																																			+ "\")'>"
																																			+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																																			+ "</a>"
																			}
																			}



																	],
																	"columnDefs" : [ {
																		targets : 12, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4))),
																	},{
																		targets : 13, visible: (sessionStorage.getItem("roleID") == 3)
																	//(!(sessionStorage.getItem("roleID") == 1) && (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4))))
																	},
																	{
																		"className": "dt-center", "targets": "_all"
																	}], "buttons": [
																		{
															                text: 'Reset',
															                action: function ( e, dt, node, config ) {
															                    alert( 'Button activated' );
															                },
															                className: 'customButton',
															               
															                action: function ( e, dt, button, config ) {
															                   
															                	window.location = "customerDetails.jsp"
															                }
															            }
																	]
																	})
																	$("div.headname").html('<h3>Customer Management</h3>');
													}
												});
										return false;
									});
							
							$("#resetFilter")
							.on(
									function() {
										
										 $("input:text").val("");
							
									});	
							

							$('#customerDetails')
									.on(
											'status.field.bv',
											function(e, data) {
												formIsValid = true;
												$('.group.form-group', $(this))
														.each(
																function() {
																//	alert(this+"@@=>"+formIsValid);
																	formIsValid = formIsValid
																			&& $(this).hasClass(
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

							
							
							
							$('#customerEdit').on(
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
											$('#customerEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#customerEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
												$(document).on('click', '#customerAdd', function () {
													if(sessionStorage.getItem("roleID") != 2){
												if($("#selectcommunityName").val() == -1 || $("#selectcommunityName").val() == null || $("#selectcommunityName").val() == "Select Community"){
													
													swal.fire({
														  title: "error",
														  text: "Select Community Id",
														  icon: "error"
														});
													return false;
												} 
												
												if($("#selectBlockBasedonCommunity").val() == -1 || $("#selectBlockBasedonCommunity").val() == null || $("#selectBlockBasedonCommunity").val() == "Select Block"){
													
													swal.fire({
														  title: "error",
														  text: "Select block id",
														  icon: "error"
														});
													return false;
												}
												
												if(parseInt($("#rowCount").val()) == 0 || $("#rowCount").val() == ""){
													swal.fire({
														  title: "error",
														  text: "Please add atleast one meter",
														  icon: "error"
														});
													return false;
												}
}			
												let communityId = sessionStorage.getItem("roleID") == 2 ? sessionStorage.getItem("communityID") : $("#selectcommunityName").val();
												let blockId = sessionStorage.getItem("roleID") == 2 ? sessionStorage.getItem("ID") : $("#selectBlockBasedonCommunity").val();
												
												var meterDetails = [];
												for(var i=1;parseInt($("#rowCount").val())>=i;i++){
													var array ={};
													
													array["miuID"] = $("#miuIDAdd-"+i).val();
													array["meterSerialNumber"] = $("#meterSerialNumberAdd-"+i).val();
													array["meterType"] = $("#selectMeterType-"+i).val();
													array["meterSizeID"] = $("#meterSizeAdd-"+i).val();
													array["payType"] = $("#payTypeAdd-"+i).val();
													array["tariffID"] = $("#selectTariffName-"+i).val();
													array["gatewayID"] = $("#gatewayIDAdd-"+i).val();
													array["location"] = $("#locationAdd-"+i).val();
													
													array["thresholdMaximum"] = $("#thresholdMaximumAdd-"+i).val();
													array["thresholdMinimum"] = $("#thresholdMinimumAdd-"+i).val();
													
													meterDetails.push(array);
												}
												
												
												
												var data1 = {}
												data1["communityID"] = communityId;
												data1["blockID"] = blockId;
												data1["firstName"] = $("#firstNameAdd").val();
												data1["lastName"] = $("#lastNameAdd").val();
												data1["houseNumber"] = $("#houseNoAdd").val();
												data1["mobileNumber"] = $("#mobileNoAdd").val();
												data1["email"] = $("#emailAdd").val();
												data1["customerUniqueID"] = $("#CRNAdd").val();
												data1["meters"] = meterDetails;
												data1["createdByID"] = sessionStorage.getItem("createdByID");
												data1["loggedInUserID"] = sessionStorage.getItem("userID");
												data1["loggedInRoleID"] = sessionStorage.getItem("roleID");
												
												/*alert("===>"
														+ JSON.stringify(data1));*/
												
											//	$('#customerAdd').prop('disabled', true).addClass('disabled').off( "click" );
												console.log(JSON
														.stringify(data1));
												
												$("#loader").show();
												
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./customer/add",
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

																	swal.fire({
																		  title: "Saved",
																		  text: data.Message,
																		  icon: "success"
																		}).then(function() {
																		    window.location = "customerDetails.jsp";
																		    
																		});
																	return false;
																	
																	

																} else if(data.result == "Failure"){
																	if(data.Message.includes("MIUID IS ALREADY REGISTERED")){
																		swal.fire({
																			  title: "error",
																			  text: data.Message,
																			  icon: "error"
																			})
																			return false;
																	}else{
																	swal.fire({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		})
																		return false;
																		
																	}
																				
																}else {
																	swal.fire({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "customerDetails.jsp";
																		});
																}
															}
														});
												return false;
											});
							
							
										$(document).on('click', '#customerEditsave', function () {
											
											
											var meterDetails = [];
											for(var i=1;parseInt($("#rowCount").val())>=i;i++){
												var array ={};
											if($("#meterSizeAdd-"+i).val() == -1 || $("#meterSizeAdd-"+i).val() == null || $("#meterSizeAdd-"+i).val() == "Select  Meter Size"){
													swal.fire({
														  title: "error",
														  text: "Select Meter Size. For Meter - "+$("#miuIDAdd-"+i).val(),
														  icon: "error"
														});
													return false;
												} 
												array["miuID"] = $("#miuIDAdd-"+i).val();
												array["meterSerialNumber"] = $("#meterSerialNumberAdd-"+i).val();
												array["meterType"] = $("#selectMeterType-"+i).val();
												array["meterSizeID"] = $("#meterSizeAdd-"+i).val();
												array["payType"] = $("#payTypeAdd-"+i).val();
												array["gatewayID"] = $("#gatewayIDAdd-"+i).val();
												array["customerMeterID"] = $("#customerMeterIDAdd-"+i).val();
												array["location"] = $("#locationAdd-"+i).val();
												array["thresholdMaximum"] = $("#thresholdMaximumAdd-"+i).val();
												array["thresholdMinimum"] = $("#thresholdMinimumAdd-"+i).val();
												meterDetails.push(array);
											}
											
											
										var data1 = {}
										
										data1["firstName"] = $("#firstNameEdit").val();
										//data1["houseNumber"] = $("#houseNoEdit").val();
										data1["mobileNumber"] = $("#mobileNoEdit").val();
										data1["email"] = $("#emailEdit").val();
										data1["CRNNumber"] = $("#CRNEdit").val();
										data1["houseNumber"] = $("#houseNoEdit").val();
										data1["meters"] = meterDetails;
										if(sessionStorage.getItem("roleID") == 1 || sessionStorage.getItem("roleID") == 2 ){
											data1["meterID"] = $("#amrEdit").val();
											
											//data1["tariffID"] = $("#selectTariffNameEdit").val();	
										}
										data1["createdByID"] = sessionStorage.getItem("createdByID");
										data1["loggedInUserID"] = sessionStorage.getItem("userID");
										data1["loggedInRoleID"] = sessionStorage.getItem("roleID");
										$('#customerEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										/*alert("===>"
												+ JSON.stringify(data1));*/
										
										$("#loader").show();
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./customer/edit/"+$("#CRNEdit").val(),
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
																    window.location = "customerDetails.jsp";
																    
																});
															return false;
														} else if(data.result == "Failure" && data.Message == undefined){
															
															swal.fire({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "customerDetails.jsp";
																    
																});
															
														}else {
															
															swal.fire({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "customerDetails.jsp";
																    
																});
														}
													}
												});
										return false;
									});
							
							
						});




function getCustomerFormEdit(id) {

	window.location("customerEdit.jsp?cust="+id);

}


function getCustomerFormDelete(CRNNumber){
	
	bootbox
	.confirm(
			"ARE YOU SURE TO DELETE CUSTOMER",
		function(
			result) {
			//	alert(result);
			if(result == true){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "./customer/delete/" + CRNNumber,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "customerDetails.jsp";
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

function getCustomerMeters(CRNNumber){
	/*$('#myCustomerMeters_wrapper').DataTable().clear();
	$('#myCustomerMetersTable').DataTable().destroy();
	$('#myCustomerMetersTable_wrapper tbody').remove();
	$('#myCustomerMeters').on('shown.bs.modal', function(e){
		$('#myCustomerMeters_wrapper').DataTable().clear();
		$('#myCustomerMetersTable').DataTable().destroy();
		$('#myCustomerMetersTable_wrapper tbody').remove();*/
	$.getJSON("./customer/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1", function(data) {
		$.each(data.data, function(i, item) {
			if (CRNNumber == item.CustomerUniqueID) {
				
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
										"data":item.meters,
						
							"columns" : [
									{
										"data" : "miuID"
										
									},
									 {
											"data" : "meterSerialNumber"
									},
									 {
										"data" : "meterType"
									}
									,
									{
										"data" : "meterSize"
										
									},
									 {
											"data" : "payType"
									},
									 {
										"data" : "tariffName"
									},
									 {
										"data" : "gatewayName"
								},
								 {
									"data" : "location"
								},
								 {
									"data" : "thresholdMaximum"
								},
								 {
									"data" : "thresholdMinimum"
								}
									
									
									],
										"columnDefs" : [],

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

// define the function within the global scope
$.fn.removeMeter = function(removeValue) {
	var rowCount = $("#rowCount").val()==""?0:$("#rowCount").val();
	rowCount--;
	if(rowCount>13){
		$("#addMeter").hide();
	}else{
		$("#addMeter").show();
	}
	$(this).parent().parent().remove();
	const index = $("#rowCountArray").indexOf(parseInt(removeValue));
	if (index > -1) {
		$("#rowCountArray").splice(index, 1);
	}
	$("#rowCount").val(rowCount);
};

	


