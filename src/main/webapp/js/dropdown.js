/**
 * 
 */



$(function() {
	
	if(sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
		$("#selectHouseBasedonBlock").append("<option>" + "Select CRN" + "</option>");
		
		$.getJSON("./customers/" + sessionStorage.getItem("roleID") + "/"
				+ sessionStorage.getItem("ID")+ "/" + sessionStorage.getItem("ID"), function(data) {
			var Options = "";
			$.each(data.dropDownCustomers, function(key, value) {
				Options = Options + "<option value='" + value + "'>" + value
						+ "</option>";
			});
			$('#selectHouseBasedonBlock').append(Options);
			
		});
		$("#selectHouseBasedonBlockForBill").append("<option>" + "Select CRN" + "</option>");
		
		$.getJSON("./customers/" + sessionStorage.getItem("roleID") + "/"
				+ sessionStorage.getItem("ID")+ "/" + sessionStorage.getItem("ID"), function(data) {
			var Options = "";
			$.each(data.dropDownCustomers, function(key, value) {
				Options = Options + "<option value='" + key + "'>" + value
						+ "</option>";
			});
			$('#selectHouseBasedonBlockForBill').append(Options);
			
		});
		
		
	}
	
	
	$.getJSON("./communities/" + sessionStorage.getItem("roleID") + "/"
			+ sessionStorage.getItem("ID"), function(data) {
		var Options = "<option value='-1'>Select  Community</option>";
		
		$.each(data.dropDownCommunities, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectcommunityName').append(Options);
		$('#filterselectcommunityName').append(Options);
		// $("#selectcommunityName").material_select();
	});
	
	
	$.getJSON("./tariffs", function(data) {
		var Options = "<option value='-1'>Select  Tariff</option>";
		$.each(data.dropDownTariffs, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectTariffName').append(Options);
		$('#selectTariffNameEdit').append(Options);
		$('#tariff').append(Options);
		// $("#selectcommunityName").material_select();
	});
	
	$.getJSON("./tariffsAmount", function(data) {
		var Options = "<option value='-1'>Select  Tariff</option>";
		$.each(data.dropDownTariffs, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#tariff').append(Options);
	});
});



function showBlockbyCommunity(communityId){
	$("#selectBlockBasedonCommunity").find('option').remove();

	$("#selectBlockBasedonCommunity").append("<option>" + "Select Block" + "</option>");
	
	$("#filterselectBlockBasedonCommunity").find('option').remove();

	$("#filterselectBlockBasedonCommunity").append("<option>" + "Select Block" + "</option>");
	
	$("#selectHouseBasedonBlock").find('option').remove();

	$("#selectHouseBasedonBlock").append("<option>" + "Select CRN" + "</option>");
	
	$("#AMR_topup").find('option').remove();

	$("#AMR_topup").val("");
	
	$.getJSON("./blocks/"+ sessionStorage.getItem("roleID") + "/"
			+ sessionStorage.getItem("ID")+ "/" + communityId, function(data) {
		var Options = "";
		$.each(data.dropDownBlocks, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectBlockBasedonCommunity').append(Options);
		$('#filterselectBlockBasedonCommunity').append(Options);
		// $("#filterselectBlockBasedonCommunity").material_select();
	});
}



function showCustomerbyBlock(blockId){
	$("#selectHouseBasedonBlock").find('option').remove();
	$("#selectHouseBasedonBlock").append("<option>" + "Select CRN" + "</option>");
	$.getJSON("./customers/" + sessionStorage.getItem("roleID") + "/"
			+ sessionStorage.getItem("ID")+ "/" + blockId, function(data) {
		var Options = "";
		$.each(data.dropDownCustomers, function(key, value) {
			Options = Options + "<option value='" + value + "'>" + value
					+ "</option>";
		});
		$('#selectHouseBasedonBlock').append(Options);
	});
}

function showCustomerbyBlockForBill(blockId){
	$("#selectHouseBasedonBlockForBill").find('option').remove();
	$("#selectHouseBasedonBlockForBill").append("<option>" + "Select CRN" + "</option>");
	$.getJSON("./customers/" + sessionStorage.getItem("roleID") + "/"
			+ sessionStorage.getItem("ID")+ "/" + blockId, function(data) {
		var Options = "";
		$.each(data.dropDownCustomers, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectHouseBasedonBlockForBill').append(Options);
	});
}

function showCustomerbyBlockForConfig(blockId){
	// alert("@@-->"+communityId);
	$("#selectHouseBasedonBlock").find('option').remove();

	$("#selectHouseBasedonBlock").append("<option>" + "Select CRN" + "</option>");
	
	$.getJSON("./customers/" + sessionStorage.getItem("roleID") + "/"
			+ sessionStorage.getItem("ID")+ "/" + blockId, function(data) {
		var Options = "";
		$.each(data.dropDownCustomers, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectHouseBasedonBlock').append(Options);
		// $("#selectBlockBasedonCommunity").material_select();
	});
}

function showMetersDetails(customerId){
	$("#selectMeters").find('option').remove();

	$("#selectMeters").append("<option>" + "Select Meters" + "</option>");
	$.getJSON("./customermeters/Prepaid/" + customerId, function(data) {
		var Options = "";
		$.each(data.dropDownCustomerMeters, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectMeters').append(Options);
	});
}

function showMetersDetailsPostpaid(customerId){
	$("#selectMeters").find('option').remove();

	$("#selectMeters").append("<option>" + "Select Meters" + "</option>");
	$.getJSON("./customermeters/Postpaid/" + customerId, function(data) {
		var Options = "";
		$.each(data.dropDownCustomerMeters, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectMeters').append(Options);
	});
}

function showAllMetersDetails(customerId){
	$("#selectMeters").find('option').remove();
	var e = document.getElementById("selectHouseBasedonBlock");
	var value=e.options[e.selectedIndex].value;// get selected option value
	var text=e.options[e.selectedIndex].text;
	$("#selectMeters").append("<option>" + "Select Meters" + "</option>");
	$.getJSON("./customermeters/" + text, function(data) {
		var Options = "";
		$.each(data.dropDownAllCustomerMeters, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectMeters').append(Options);
	});
}

function showTopupDetails(meterId){
	
	$.getJSON("./topupdetails/" + $("#CustomerCRNNumber").val()+"/"+meterId, function(data) {
		
		$("#AMR_topup").val(data.topupdetails.miuID).trigger("change");
		$("#formAMR_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#currentBalance_topup").val(data.topupdetails.currentBalance).trigger("change");
		$("#formcurrentBalance_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#dateTime_topup").val(data.topupdetails.IoTTimeStamp).trigger("change");
		$("#formdateTime_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#unit_topup").val(data.topupdetails.tariff).trigger("change");
		$("#formunit_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#emergency_topup").val(data.topupdetails.emergencyCredit).trigger("change");
		$("#formemergency_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#alarm_topup").val(data.topupdetails.alarmCredit).trigger("change");
		$("#formalarm_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#reconnection_topup").val(data.topupdetails.reconnectionCharges).trigger("change");
		$("#formreconnection_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#fixed_topup").val(data.topupdetails.fixedCharges).trigger("change");
		$("#formfixed_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#month_topup").val(data.topupdetails.noOfMonths).trigger("change");
		$("#formmonth_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
		
	});
	
}


function showBillingDetails(customerId){
	
	$.getJSON("./billdetails/" + $("#selectHouseBasedonBlockForBill option[value="+customerId+"]").text(), function(data) {
		// var Options = "";
		$("#totalConsumption").val(data.billdetails.totalConsumption).trigger("change");
		$("#formcurrentBalance_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#totalAmount").val(data.billdetails.totalAmount).trigger("change");
		$("#formdateTime_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#lateFee").val(data.billdetails.lateFee).trigger("change");
		$("#formunit_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#dueDate").val(data.billdetails.dueDate).trigger("change");
		$("#customerBillingID").val(data.billdetails.customerBillingID).trigger("change");
		$("#formemergency_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#billingDate").val(data.billdetails.billingDate).trigger("change");
		$("#formalarm_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#billMonth").val(data.billdetails.billMonth).trigger("change");
		$("#formreconnection_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#billYear").val(data.billdetails.billYear).trigger("change");
		$("#formfixed_topup").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		
	});
	
}


function showCommunitybyTypeuser(id){
	
	if(id=="4"){
		$("#usercommunityId,#userblockId").hide();
	$("#formcomunityName").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
	$("#formblockName").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
	}else if(id =="5")
		{
		$("#formcomunityName").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
		$("#formblockName").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
		$("#usercommunityId").show();
		$("#userblockId").show();
		
		}	
		
}





function showFieldsBasedONCommand(id){
	$("#confValue").remove();
	$("#confValue").remove();
	
	
	if(id=="3"){
		$("#confValue").remove();
		
		$("#row").append(`<div class="col-md-4" id="confValue" class="valueText"
															>
															<div id="formtariff" class="group form-group">
																<label class="bmd-label-floating select-label">Select Tariff<sup
																	class="imp">*</sup></label> <select class="form-control"
																	id="valueText" name="valueText">
																</select>
															</div>
														</div>`);
		
		$.getJSON("./tariffs", function(data) {
			var Options = "<option value='-1'>Select  Tariff</option>";
			$.each(data.dropDownTariffs, function(key, value) {
				Options = Options + "<option value='" + key + "'>" + value
						+ "</option>";
			});
			$('#valueText').append(Options);
			// $("#selectcommunityName").material_select();
		});
		
		
		}
	
	if(id=="5"){
		$("#confValue").remove();
		
		$("#row").append(`<div class="col-md-4" id="confValue" class="valueText"
															>
															<div id="formtariff" class="group form-group">
																<label class="bmd-label-floating select-label">Valve<sup
																	class="imp">*</sup></label> <select class="form-control"
																	id="valueText" name="valueText">
																	
																	<option value="1">Open</option>
																	<option value="0">Close</option>
																	<option value="2">Automatic</option>
																	
																</select>
															</div>
														</div>`);
		
		
		}
	
	else if(id=="6"){
		$("#confValue").remove();
		
		$("#row").append(`<div class="col-md-4" id="confValue" class="valueText"
															>
															<div id="formtariff" class="group form-group">
																<label class="bmd-label-floating select-label">RTC<sup
																	class="imp">*</sup></label> 
																	
												<input type="text" id="start_date" name="start_date" class="form-control" >
																	
															</div>
														</div>`);
		
		$('#start_date').bootstrapMaterialDatePicker({
            format: 'YYYY-MM-DD HH:mm'
        });
		
		}else if(id=="7"){
			$("#confValue").remove();
			$("#row").append(`<div class="col-md-4 valueText" id="confValue">
																<div id="formtariff" class="group form-group">
																	<label class="bmd-label-floating select-label">Start Date<sup
																		class="imp">*</sup></label> 
																		
													<input type="text" id="start_date" name="start_date" class="form-control" >
																		
																</div>
															</div><div class="col-md-4 valueText" id="confValue" 
																>
																<div id="formtariff" class="group form-group">
																	<label class="bmd-label-floating select-label">End Date<sup
																		class="imp">*</sup></label> 
																		
													<input type="text" id="end_date" name="end_date" class="form-control" >
																		
																</div>
															</div>`);
			var lastDate = new Date(new Date().getFullYear(),
					new Date().getMonth() + 1, 0);
			$('#start_date').bootstrapMaterialDatePicker({
				 format: 'YYYY-MM-DD HH:mm:ss',
				clearBtn : true,
				todayBtn : "linked",
				weekStart : 1,
				 minDate:new Date()
			}).on('changeDate', function (selected) {
				alert("start_date"+selected);
		        var minDate = new Date(selected.date.valueOf());
		        $('#end_date').datepicker('setStartDate', minDate);
		    });
			$('#end_date').bootstrapMaterialDatePicker({
				 format: 'YYYY-MM-DD HH:mm:ss',
					clearBtn : true,
					todayBtn : "linked",
					weekStart : 1,
					minDate:new Date()
			}).on('changeDate', function (selected) {
				alert("end_date"+selected);
	            var maxDate = new Date(selected.date.valueOf());
	            $('#start_date').datepicker('setEndDate', maxDate);
	        });
			
			}else if(id=="8"){
				$("#confValue").remove();
				$("#row").append(`<div class="col-md-4" id="confValue" class="valueText"
																	>
																	<div id="formtariff" class="group form-group">
																		<label class="bmd-label-floating select-label">Sync Interval<sup
																			class="imp">*</sup></label> 
																			
														<input type="number" id="sync_interval" name="sync_interval" class="form-control" min="2" max="1440">
																			
																	</div>
																</div>`);
				}else if(id=="9"){
					$("#confValue").remove();
					$("#row").append(`<div class="col-md-4" id="confValue" class="valueText"
																		>
																		<div id="formtariff" class="group form-group">
																			<label class="bmd-label-floating select-label">Reading<sup
																				class="imp">*</sup></label> 
																				
															<input type="text" id="reading" name="reading" class="form-control" maxLength="10">
																				
																		</div>
																	</div>`);
					}else if(id=="13"){
						$("#confValue").remove();
						$("#row").append(`<div class="col-md-4" id="confValue" class="valueText"
																			>
																			<div id="formtariff" class="group form-group">
																				<label class="bmd-label-floating select-label">Sync Time<sup
																					class="imp">*</sup></label> 
																					
																<input type="text" id="syncTime" name="syncTime" class="form-control">
																					
																			</div>
																		</div>`);
						
						$('#syncTime').bootstrapMaterialDatePicker({
				            format: 'HH:mm'
				        });
						
						}
		
}

function showFieldsBasedONType(data){
	if(data == "group"){
		$("#groupCommandId").show();
		
		$(".myCheck").prop("checked", false);
		
	/*
	 * var tem = `<div id="divGroup" class="group form-group has-feedback
	 * has-success bmd-form-group is-filled"> <label class="bmd-label-floating
	 * select-label">Group Command Type<sup class="imp">*</sup> </label>
	 * <select id="framework" name="framework[]" multiple class="form-control"
	 * onchange="groupCommand(this.value);"> <option value="8" >Sync Interval</option>
	 * <option value="9">Meter Reading</option> <option
	 * value="10">PrePaid/PostPaid Mode</option> <option value="11">Meter
	 * Resource Type</option> <option value="12">Clear Tamper</option> <option
	 * value="13">Sync Time</option> </select> </div>`;
	 * 
	 * $("#groupCommandId").append(tem);
	 */
		
		$("#individualCommandId").hide();
	}else if(data=="individual"){
		$("#groupCommandId").hide();
		// $("#divGroup").remove();
		$("#individualCommandId").show();
		$(".myCheck").prop("checked", false);
	}else{
		$("#groupCommandId").hide();
		$("#individualCommandId").hide();
		$(".myCheck").prop("checked", false);
	}
}

function show(value){
	console.log(value);
	
	var optionsselected = $(".active input[type='checkbox']").val();
	
	  $.each(optionsselected,function(i,x) {
	   // $('.divContainer').append('<div>'+x+'</div>')
		  console.log(x);
	  });
}

function onChangeMeterSize(rowCount){
	$('#meterSizeAdd-'+rowCount).find('option').remove();

	$.getJSON("./metersizes/"+$("#selectMeterType-"+rowCount).val(), function(data) {
			var Options = '<option value=-1>Select  Meter Size</option>';
			$.each(data.dropDownMeterSizes, function(key, value) {
				Options = Options + '<option value=' + key + '>' + value
						+ '</option>';
			});
			$('#meterSizeAdd-'+rowCount).append(Options);
		});
	
}


var lastDate = new Date(new Date().getFullYear(),
		new Date().getMonth() + 1, 0);
$('#start_date').bootstrapMaterialDatePicker({
	 format: 'YYYY-MM-DD HH:mm:ss',
	clearBtn : true,
	todayBtn : "linked",
	weekStart : 1,
	 minDate:new Date()
}).on('changeDate', function (selected) {
	alert("start_date"+selected);
    var minDate = new Date(selected.date.valueOf());
    $('#end_date').datepicker('setMinDate', minDate);
});
$('#end_date').bootstrapMaterialDatePicker({
	 format: 'YYYY-MM-DD HH:mm:ss',
		clearBtn : true,
		todayBtn : "linked",
		weekStart : 1,
		minDate:new Date()
}).on('changeDate', function (selected) {
	alert("end_date"+selected);
    var maxDate = new Date(selected.date.valueOf());
    $('#start_date').datepicker('setMaxDate', maxDate);
});
