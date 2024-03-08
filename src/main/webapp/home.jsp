<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<link rel="icon" type="image/png" sizes="16x16"
	href="common/images/1-hanbit.png">
<!-- Material Design for Bootstrap CSS -->
<!-- <link rel="stylesheet" href="common/css/style.css"> -->
<!-- <link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
 -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />

<!-- <link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css"> -->

<link href="common/css/materialize.fontawsome.css" rel="stylesheet">



<title>DashBoard</title>
</head>


<body class="main-sidebar-show">


	<%
		String user_id = (String) session.getAttribute("roleID");
	%>

	<%
		if (user_id == null) {
			response.sendRedirect("login.jsp");
		} else {
	%>
	<div id="preloader">
		<div id="status">&nbsp;</div>
	</div>

	<jsp:include page="header.jsp" />

	<jsp:include page="slidebar.jsp" />
	<div class="top-spacing"></div>
	<div class="main-content side-content pt-0">
		<div class="container-fluid">
			<div class="inner-body body_content" id="content-5">

				<%
					if (user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("4")
								|| user_id.equalsIgnoreCase("5")) {
				%>




				<div class="row">
					<div class="col-md-12">
					
					
					
					<div class="page-header">
							<h3>Water</h3>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="box">
									<!-- <div class="box-header with-border">
              <h3 class="box-title">Monthly Recap Report</h3>
			</div> -->
									<%
										if (user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("4")) {
									%>
									
									<div class="waterloadDiv"><img src="common/images/load.gif" alt="" width="50" height="50" style="" id="waterLoader" class="waterLoad"></div>
									
									<div class="box-body">
										<div class="row">
											<div class="col-md-12">
												<div id="container1"
													style="width: 100%; height: 400px; margin: 0 auto"></div>
											</div>
										</div>
									</div>
									<%
										}
									%>
									<div class="box-footer">

										<div class="container">
											<div class="row">
												<div class="col-md-12">

													<section class="logo-carousel slider" data-arrows="true">
														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="waterActive"></h5>
																<span class="description-text"
																	onClick="redirection(1,'Water')">Active <i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>

														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="waterInactive"></h5>
																<span class="description-text"
																	onClick="redirection(2,'Water')">In-Active <i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>
														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="waterLive"></h5>
																<span class="description-text"
																	onClick="redirection(3,'Water')">Live <i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>

														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="waternonLive"></h5>
																<span class="description-text"
																	onClick="redirection(4,'Water')">Non-Live <i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>

														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="wateremergency"></h5>
																<span class="description-text"
																	onClick="redirection(6,'Water')">Emergency <i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>

														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="waterLowbattery"></h5>
																<span class="description-text"
																	onClick="redirection(5,'Water')">Low Battery <i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>
														<%
															if (user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("5")) {
														%>
														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="gasActivePercentage"></h5>
																<span class="description-text"
																	onClick="dashboardAll('Water')">All Details<i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>
														<%
															}
														%>
														<!-- <div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header"
																	id="waterActivePercentage"></h5>
																<span class="description-text">Active Percentage</span>
															</div>

														</div> -->


													</section>


												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					
					
						<div class="page-header">
							<h3>Gas</h3>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="box">
									<div class="box-body">
										<img src="common/images/load.gif" alt="" width="50" height="50" style="" id="gasLoader"></div>
										<%
											if (user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("4")) {
										%>
										
										<div class="row">
											<div class="col-md-12">
												<div id="container"
													style="width: 100%; height: 400px; margin: 0 auto"></div>
											</div>
										</div>
										<%
											}
										%>
									
									<div class="box-footer">
										<div class="container">
											<div class="row">
												<div class="col-md-12">

													<section class="logo-carousel slider" data-arrows="true">
														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="gasActive"></h5>
																<span class="description-text"
																	onClick="redirection(1,'Gas')">Active <i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>

														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="gasInactive"></h5>
																<span class="description-text"
																	onClick="redirection(2,'Gas')">In-Active <i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>

															</div>

														</div>
														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="gasLive"></h5>
																<span class="description-text"
																	onClick="redirection(3,'Gas')">Live <i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>

														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="gasnonLive"></h5>
																<span class="description-text"
																	onClick="redirection(4,'Gas')">Non-Live <i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>

														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="gasemergency"></h5>
																<span class="description-text"
																	onClick="redirection(6,'Gas')">Emergency <i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>

														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="gasLowbattery"></h5>
																<span class="description-text"
																	onClick="redirection(5,'Gas')">Low Battery <i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>


														<%
															if (user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("5")) {
														%>
														<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header" id="gasActivePercentage"></h5>
																<span class="description-text"
																	onClick="dashboardAll('Gas')">All Details<i
																	class="fa fa-chevron-right view_details"
																	aria-hidden="true"></i>
																</span>
															</div>

														</div>
														<%
															}
														%>

														<!--						<div class="slide sliding-block">

															<div class="text-xs font-weight-bold text-uppercase">
																<h5 class="description-header"
																	id="gasinactivePercentage"></h5>
																<span class="description-text">in-Active
																	Percentage</span>
															</div>

														</div> -->




													</section>


												</div>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>





						


						<!-- End-->



					</div>
				</div>
				<%
					} else if (user_id.equalsIgnoreCase("3")) {
				%>

				<div class="inner-body body_content" id="content-5">
					<div class="row bg-success bg-gradient mt-2 p-2 text-white">
						<!-- <div class="col-md-6">
									Last ReCharge Amount :<span id="lastBillAmount"> </span>
								</div>
								<div class="col-md-6 text-right">
									Last ReCharge Date : <span id="lastBillDate"> </span>
								</div> -->
					</div>
					<div class="row border-bottom bg-light bg-gradient p-2 pt-0">

						<div class="col-md-3">
							<div class="form-group">
								<label for="text">Type:</label> <select
									class="form-control start_date select2" id="type">
									<option value="">Select Type</option>
									<option value="Gas">Gas</option>
									<option value="Water">Water</option>
								</select>
							</div>
						</div>

						<div class="col-md-3">
							<div class="form-group">
								<label for="text">Select Year:</label> <select
									class="yrselectdesc form-control start_date select2"
									id="start_date">
									<option value="">Select Year</option>
								</select>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label for="text">Select Month:</label> <select
									class="form-control month select2" id="month">
									<option value="0">Select Month</option>
									<option value="1">January</option>
									<option value="2">February</option>
									<option value="3">March</option>
									<option value="4">April</option>
									<option value="5">May</option>
									<option value="6">June</option>
									<option value="7">July</option>
									<option value="8">August</option>
									<option value="9">September</option>
									<option value="10">October</option>
									<option value="11">November</option>
									<option value="12">December</option>
								</select>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label for="text">&nbsp; &nbsp;</label>
								<button type="button" id="view"
									class="btn btn-primary submit-button btn-raised mr-4">
									View
									<div class="ripple-container"></div>
								</button>
							</div>

						</div>
					</div>
					<div class="row">
						<div class="col-md-3 border-right">
							<div class="row mt-2">
								<div class="col-md-4">
									<div class="text-center">
										<i class="fa fa-user-circle-o" aria-hidden="true"></i>
										<p class="card-category mt-2 mb-0">Community</p>
										<p class="card-title community" id="community"></p>
									</div>
								</div>
								<div class="col-md-4">
									<div class="text-center">
										<i class="fa fa-user-circle-o" aria-hidden="true"></i>
										<p class="card-category mt-2 mb-0">Block</p>
										<p class="card-title block" id="block"></p>
									</div>
								</div>
								<div class="col-md-4">
									<div class="text-center">
										<i class="fa fa-user-circle-o" aria-hidden="true"></i>
										<p class="card-category mt-2 mb-0">CRN Number</p>
										<p class="card-title CRN_Number" id="CRN_Number"></p>
									</div>
								</div>



								<div class="col-md-4">
									<div class="text-center">
										<i class="fa fa-user-circle-o" aria-hidden="true"></i>
										<p class="card-category mt-2 mb-0">Water</p>
										<a href=# id="CustomerMeters" data-toggle="modal"
											data-target="#myCustomerMeters"
											onclick="getCustomerMeters('Water')"> Multiple </a>
									</div>
								</div>

								<div class="col-md-4">
									<div class="text-center">
										<i class="fa fa-user-circle-o" aria-hidden="true"></i>
										<p class="card-category mt-2 mb-0">Gas</p>
										<a href=# id="CustomerMeters" data-toggle="modal"
											data-target="#myCustomerMeters"
											onclick="getCustomerMeters('Gas')"> Multiple </a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-9">
							<div id="highchart_container1"
								style="width: 100%; height: 400px; margin: 0 auto"></div>

							<div id="highchart_container2"
								style="height: 400px; width: 100%; max-width: 800px; margin: 0 auto"></div>
						</div>
					</div>

				</div>

				<div class="modal fade bd-example-modal-lg" id="myCustomerMeters"
					role="dialog">
					<div class="modal-dialog modal-lg
		">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" align="center">List of Meters</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">

								<!-- 	<table id="customerMeterTable"
						class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
						style="width: 100%">
						<thead>
							<tr>
								<th>TimeStamp</th>
								<th>Meter Serial Number</th>
								<th>MIU ID</th>
								<th>Reading</th>
								<th>Consumption</th>
								<th>Battery</th>
								<th>Box AMR Tamper</th>
								<th>Magnetic Tamper</th>
								<th>Balance</th>
								<th>Emergency Credit</th>
								<th>Pay Type</th>
								<th>Valve Status</th>
								<th>Tariff</th>
								<th>Communication Status</th>
								<th>Vacation Status</th>
								<th>Last Topup Amount</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table> -->

								<table id=customerMeterTable
									class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
									style="width: 100%">
									<thead id="theadBody">
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>




				<%
					}
				%>

			</div>
		</div>
	</div>
	<%
		}
	%>
	<jsp:include page="footer.jsp" />


	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	<script src="js/home.js"></script>
	<script src="js/common.js"></script>
	
		
	<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.colVis.min.js"></script>
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
		
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
		
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.bootstrap.min.js"></script>	
		
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
		
		<script
		src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>	
		
		<script
		src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
		
		
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script  src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<script  src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
<script  src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
		
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>


	<script language="JavaScript">
		$(document).ready(function() {
			$('.logo-carousel').slick({
				infinite : false,
				slidesToShow : 6,
				slidesToScroll : 1,
				autoplay : true,
				autoplaySpeed : 1000,
				arrows : true,
				dots : false,
				pauseOnHover : false,
				responsive : [ {
					breakpoint : 768,
					settings : {
						slidesToShow : 4
					}
				}, {
					breakpoint : 520,
					settings : {
						slidesToShow : 2
					}
				} ]
			});
		});
	</script>
	<script src="common/js/year-select.js"></script>
	<script type="text/javascript">
		$(document).ready(function(e) {
			$('.yearselect').yearselect();

			$('.yrselectdesc').yearselect({
				step : 1,
				order : 'desc'
			});
			$('.yrselectasc').yearselect({
				order : 'asc'
			});
		});
	</script>





</body>

</html>