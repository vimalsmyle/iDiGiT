/**
 * 
 */

$(document)
		.ready(
				function() {
					
					if(sessionStorage.getItem("roleID") == 2){
						$("#communityNameAdd").val(sessionStorage.getItem("communityName"));
						$("#formcommunityNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
						$("#blockNameAdd").val(sessionStorage.getItem("blockName"));
						$("#formblockNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
					}
				});

$(document)
		.ready(
				function() {
					table = $('#configurationstatusTable1').hide();
					table = $('#configurationstatusTable')
							.DataTable(
									{
										"dom" : "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
												"responsive" : true,
												"processing" : true,
												"serverSide" : false,
												"bDestroy" : true,
												"bPaginate": true,
												"pagging" : true,
												"bProcessing" : true,
												"ordering" : true,
												"order" : [ 0, "desc" ],
												"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
												"pageLength" : 25,
												"scrollY" : 324,
												"scrollX" : true,
												"ajax" : {
											"url" : "./configuration/"+ sessionStorage.getItem("roleID")+ "/"+ sessionStorage.getItem("ID")+"/-1",
											"type" : "GET",
											"data" : function(search) {
											},
											"complete" : function(json) {
												cosole.log(json.data);
												return json.data;
											},
										},
										"columns" : [
											{
												"data" : "miuID",
												"defaultContent": ""
											},{
												"mData" : "action",
												"render" : function(data,
														type, row) {
													if(row.commands.length==0){
														return "---";
													}else{
												return row.commands[0].commandType
													}
											}
									},{
												"mData" : "action",
												"render" : function(data,
														type, row) {
												
													if(row.commands.length==0){
														return "---";
													}else{
														return row.commands[0].value
													}
											}
											},{
												"mData" : "action",
												"render" : function(data,
														type, row) {
													if(row.commands.length==0){
														return "---";
													}else{
														return row.commands[0].status
														}
											}
											},{
												"mData" : "action",
												"render" : function(data,
														type, row) {
													if(row.commands.length==0){
														return "---";
													}else{
														return row.commands[0].modifiedDate
													}
											}
											},
											{
												"mData" : "action",
												"render" : function(data,
														type, row) {

													if(row.commands.length==0){
														return "---";
													}else{if(row.commands[0].status == "Passed"){
													
														return "---";
														
													}else if(row.commands[0].status == "Pending" || row.commands[0].status == "Pending...waiting for acknowledge" || row.commands[0].status=="Already Executed"){
														
														return "---";
														
													}
													
													else if(row.commands[0].status == "Failed" || 
															row.commands[0].status ==  "Command Not Found"){
														return "<a onclick='getDeleteTransactionID("
														+ row.transactionID
														+ ")'>"
														+ "<i class='material-icons' style='color:#17e9e9; cursor:pointer;'>delete</i>"
														+ "</a>"
														
													}else{
														return "---";
													}
													}
													
												}
											} ],
										"columnDefs" : [ {
										//	orderable : false,
											//targets :5, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
										},
										{
											"className": "dt-center", "targets": "_all"
										}],
										"buttons" : [
										{
											extend : 'excel',
											footer : 'true',
											//text : 'Excel',
											exportOptions : {
												columns : [ 0, 1]
											},
											//className: 'custom-btn fa fa-file-excel-o',
											title : 'Configuration Status'
										},

										{
											extend : 'pdf',
											//className: 'custom-btn fa fa-file-pdf-o',
											footer : 'true',
											exportOptions : {
												columns : [ 0, 1 ]
											},
											//text : 'pdf',
											orientation : 'landscape',
											title : 'Configuration Status'
										},
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
									        } ],
											 initComplete: function() {
												   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
												   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
												  }

									});
					if(sessionStorage.getItem("roleID") == 3 || sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
						table.buttons( $('a.customButton') ).remove();	
					}
					
					$("div.headname").html('<h3>Configuration Status</h3>');
					
					
					

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
											url : "./configuration/"+url,
											dataType : "JSON",

											success : function(d) {
												
													$('#configurationstatusTable').dataTable()._fnAjaxUpdate();
													$("#configurationstatusTable_wrapper").hide();
													$("#filter").modal("hide");
													
													$("#configurationstatusTable1").show();
													
													var hCols = [ 3, 4 ];
													table = $('#configurationstatusTable1')
													.DataTable(
															{
																
																	"dom": "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
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
																	"scrollY" : 324,
																	"scrollX" : false,
																	"data" : d.data,
																	"columns" : [
																		{
																			"data" : "miuID",
																			"defaultContent": ""
																		},{
																			"mData" : "action",
																			"render" : function(data,
																					type, row) {
																				if(row.commands.length==0){
																					return "---";
																				}else{
																					return row.commands[0].commandType
																				}
																		}
																		},{
																			"mData" : "action",
																			"render" : function(data,
																					type, row) {
																				if(row.commands.length==0){
																					return "---";
																				}else{
																					return row.commands[0].value
																				}
																		}
																		},{
																			"mData" : "action",
																			"render" : function(data,
																					type, row) {
																				if(row.commands.length==0){
																					return "---";
																				}else{
																					return row.commands[0].status
																				}
																		}
																		},{
																			"mData" : "action",
																			"render" : function(data,
																					type, row) {
																				if(row.commands.length==0){
																					return "---";
																				}else{
																					return row.commands[0].modifiedDate
																				}
																		}
																		},
																		{
																			"mData" : "action",
																			"render" : function(data,
																					type, row) {

																				if(row.commands.length==0){
																					return "---";
																				}else{if(row.commands[0].status == "Passed"){
																				
																					return "---";
																					
																				}else if(row.commands[0].status == "Pending" || row.commands[0].status == "Pending...waiting for acknowledge" || row.commands[0].status=="Already Executed"){
																					
																					return "---";
																					
																				}
																				
																				else if(row.commands[0].status == "Failed" || row.commands[0].status ==  "Command Not Found"){
																					return "<a onclick='getDeleteTransactionID("
																					+ row.transactionID
																					+ ")'>"
																					+ "<i class='material-icons' style='color:#17e9e9; cursor:pointer;'>delete</i>"
																					+ "</a>"
																					
																				}else{
																					return "---";
																					
																				}
																				}
																				
																			}
																		}  ],
																"columnDefs" : [ {
																//	orderable : false,
																//	targets : 5, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
																},
																{
																	"className": "dt-center", "targets": "_all"
																}],
																"buttons" : [
																{
																	extend : 'excel',
																	footer : 'true',
																	//text : 'Excel',
																	exportOptions : {
																		columns : [ 0, 1]
																	},
																	//className: 'custom-btn fa fa-file-excel-o',
																	title : 'Configuration Status'
																},

																{
																	extend : 'pdf',
																	footer : 'true',
																	exportOptions : {
																		columns : [ 0, 1 ]
																	},
																	//className: 'custom-btn fa fa-file-pdf-o',
																	//text : 'pdf',
																	orientation : 'landscape',
																	title : 'Configuration Status'
																},
																{
													                text: 'Reset',
													                action: function ( e, dt, node, config ) {
													                    alert( 'Button activated' );
													                },
													                className: 'customButton',
													               
													                action: function ( e, dt, button, config ) {
													                   
													                	window.location = "configurationStatus.jsp"
													                }
													            } ],
																 initComplete: function() {
																	   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
																	   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
																	  }

															});
													if(sessionStorage.getItem("roleID") == 3 || sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
														table.buttons( $('a.customButton') ).remove();	
													}
											
											$("div.headname").html('<h3>Configuration</h3>');
											}
										});
								return false;
							});
					
					$("#resetFilter")
					.on(
							function() {
								 $("input:text").val("");
							});	
					
				});





function getDeleteTransactionID(transID){
	
	//alert(transID);
	
	bootbox
	.confirm(
			"ARE YOU SURE TO DELETE RECORD",
		function(
			result) {
			//	alert(result);
			if(result == true){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "./configuration/delete/" + transID,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "configurationStatus.jsp";
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



function getCustomerMeters(transactionID){
	$.getJSON("./configuration/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1", function(data) {
		$.each(data.data, function(i, item) {
			if (transactionID == item.transactionID) {
				console.log(item);
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
										"data":item.commands,
						
							"columns" : [
									{
										"data" : "commandType"
										
									},
									 {
											"data" : "value"
									},
									 {
										"data" : "modifiedDate"
									}
									,
									{
										"mData" : "action",
										"render" : function(data,
												type, row) {

											if(row.status == "Passed"){
											
												return "---";
												
											}else if(row.status == "Pending" || row.status == "Pending...waiting for acknowledge"){
												
												return "---";
												
											}
											
											else if(row.status == "Failed"){
												return "<a onclick='getDeleteTransactionID("
												+ row.transactionID
												+ ")'>"
												+ "<i class='material-icons' style='color:#17e9e9; cursor:pointer;'>delete</i>"
												+ "</a>"
												
											}
											
											
										}
									} 
									
									],
										"columnDefs" : [],

							"buttons" : [
									]
						})  
				
				
			} 
			else {
			}
		});
		$('#myCustomerMeters').modal('show');
	});
	
	
}