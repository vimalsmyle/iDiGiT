							// community - > CommunityId		
									$(document).ready(function() {
										
										
										$("#sensorTable1").hide();
										
										table = $('#sensorTable')
										.DataTable(
										{
											"dom": "<'row'<'col-sm-4 headname'><'col-sm-3'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
											/*"processing" : false,*/
											"serverSide" : false,
											"bDestroy" : true,
											"pagging" : true,
											"bPaginate": true,
											"bProcessing" : false,
											"ordering" : true,
											"order" : [ 0, "desc" ],
											"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
											"pageLength" : 25,
										"scrollY" : 324,
										"scrollX" : true,
										"ajax" : {
										"url":"./sensordashboard/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
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
										}
										,{
											"data" : "customerUniqueID"
											}
										
										,{
											"data" : "firstName"
											}
										
										,{
											"data" : "lastName"
											}
										
										,{
											"data" : "HouseNumber"
											}
										
										
										,{
											"mData" : "action",
											"render" : function(data, type, row) {
												
												return "<a href=# id=sensorDetails data-toggle=modal data-target=#mySensorMeters onclick='getSensorMeters(\""
												+ row.equipment_serial_id
												+ "\")'>"
												+ row.equipment_serial_id
												+ "</a>"
												
											}

										},
										

										
										
										
										{
											"data" : "battery_percentage"
											},
										
										
										
										{
										"data" : "record_interval"
										},{
										"data" : "sync_interval"
										},{
										"data" : "rssi"
										},
										
										{
										"data" : "online_powersupply"
										},{
										"data" : "gsm_status"
										},{
										"data" : "ethernet_status"
										},{
										"data" : "nfc_status"
										},{
										"data" : "flash_status"
										},{
										"data" : "nfc_memory_status"
										},{
										"data" : "flash_memory_status"
										},{
										"data" : "low_gsm"
										},{
										"data" : "low_battery"
										},{
										"data" : "sensor_detachment"
										},{
										"data" : "door_open_switch"
										},{
										"data" : "magnetic_tamper"
										},{
										"data" : "LogDate"
										}
										],
										"columnDefs" : [ {
											//targets : 11, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
											"className": "dt-center", "targets": "_all"
										}], "buttons": [
											{
												extend: 'excel',
										        footer: 'true',
										        //text: 'Excel',
										        //className : 'custom-btn fa fa-file-excel-o',
										        title:'Sensor Details'  },
										         
										        {
										        extend: 'pdf',
										        footer: 'true',
										        exportOptions: {
										            columns: [0,1,2,3,4,5,6]
										        },
										       // text: 'pdf',
										        //className : 'custom-btn fa fa-file-pdf-o',
										        orientation: 'landscape',
										        title:'Sensor Details'  }
										        /**,
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
										         }*/
										],
										 initComplete: function() {
											   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
											   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
											  }
										});
										 $("div.headname").html('<h3>Sensor Details</h3>');
										 
										 (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 5) ? $(".customButton").remove() : ""
											 
											 
											 
										});
									
									
									
									 
									 function getSensorMeters(CRNNumber){
										 $("#readingBody").empty();
										 $("#readerSensorStatusBody").empty();
										 $("#tbodyPerDayFlowRateBody").empty();
										 $("#tbodyLiveFlowRateBody").empty();
										 $("#tbodyDigitalOutputBody").empty();
										 $("#tbodyAnalogInputBody").empty();
										 
										 $("#tbodyAnalogOutputBody").empty();
										 $("#tbodyVoltageOutputBody").empty();
										 
										$.getJSON("./sensordashboard/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
											$.each(data.data, function(i, item) {
												if (CRNNumber == item.equipment_serial_id) {
													
													
													let tbodyReading =``;
													
													tbodyReading+=`<td>`+ item.reading1 +`</td> <td>`+ item.reading2 +`</td> <td>`+ item.reading3 +`</td> <td>`+ item.reading4 +`</td>`;
													$("#readingBody").append(tbodyReading);
													
													let tbodyReaderSensorStatus =``;
													
													tbodyReaderSensorStatus+=`<td>`+ item.reader_sensor_status1 +`</td> <td>`+ item.reader_sensor_status2 +`</td> <td>`+ item.reader_sensor_status3 +`</td> <td>`+ item.reader_sensor_status4 +`</td>`;
													$("#readerSensorStatusBody").append(tbodyReaderSensorStatus);
													
													
													
													let tbodyPerDayFlowRate =``;
													
													tbodyPerDayFlowRate+=`<td>`+ item.per_day_flow_rate1 +`</td> <td>`+ item.per_day_flow_rate2 +`</td> <td>`+ item.per_day_flow_rate3 +`</td> <td>`+ item.per_day_flow_rate4 +`</td>`;
													$("#tbodyPerDayFlowRateBody").append(tbodyPerDayFlowRate);
													
													let tbodyLiveFlowRate =``;
													
													tbodyLiveFlowRate+=`<td>`+ item.live_flow_rate1 +`</td> <td>`+ item.live_flow_rate2 +`</td> <td>`+ item.live_flow_rate3 +`</td> <td>`+ item.live_flow_rate4 +`</td>`;
													$("#tbodyLiveFlowRateBody").append(tbodyLiveFlowRate);
													
													
													let tbodyDigitalOutput =``;
													
													tbodyDigitalOutput+=`<td>`+ item.digital_outputs1 +`</td> <td>`+ item.digital_outputs2 +`</td> <td>`+ item.digital_outputs3 +`</td> <td>`+ item.digital_outputs4 +`</td>`;
													$("#tbodyDigitalOutputBody").append(tbodyDigitalOutput);
													
													

													let tbodyAnalogInput =``;
													
													tbodyAnalogInput+=`<td>`+ item.analog_inputs1 +`</td> <td>`+ item.analog_inputs2 +`</td> <td>`+ item.analog_inputs3 +`</td> <td>`+ item.analog_inputs4 +`</td>`;
													$("#tbodyAnalogInputBody").append(tbodyAnalogInput);
													
													let tbodyAnalogOutput =``;
													
													tbodyAnalogOutput+=`<td>`+ item.analog_outputs1 +`</td> <td>`+ item.analog_outputs2 +`</td> <td>`+ item.analog_outputs3 +`</td> <td>`+ item.analog_outputs4 +`</td>`;
													$("#tbodyAnalogOutputBody").append(tbodyAnalogOutput);
													
													
													let tbodyVoltageOutput =``;
													
													tbodyVoltageOutput+=`<td>`+ item.voltage_outputs1 +`</td> <td>`+ item.voltage_outputs2 +`</td> <td>`+ item.voltage_outputs3 +`</td> <td>`+ item.voltage_outputs4 +`</td>`;
													$("#tbodyVoltageOutputBody").append(tbodyVoltageOutput);
													
													
													
												} 
											});
											
										});
									}
									 
									 
									 
									 