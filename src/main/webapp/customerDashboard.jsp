<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta charset="utf-8">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<link rel="icon" type="image/png" sizes="16x16"
	href="common/images/1-hanbit.png">
<!-- Material Design for Bootstrap CSS -->
<!-- <link rel="stylesheet" href="common/css/style.css"> -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">

<link href="common/css/materialize.fontawsome.css" rel="stylesheet">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css"
	rel="stylesheet" type="text/css" />

<title>Customer Details</title>
</head>


<body class="main-sidebar-show">
	<%
		String communityName = request.getParameter("com");
		String blockName = request.getParameter("block");
		String type = request.getParameter("type");
		String user_id = (String) session.getAttribute("roleID");
	%>

	<%
		if (user_id == null) {
			response.sendRedirect("login.jsp");
		} else {
	%>
	<div id="preloader" style="display: none;">
		<div id="status">&nbsp;</div>
	</div>

	<jsp:include page="header.jsp" />

	<jsp:include page="slidebar.jsp" />
	<div class="top-spacing"></div>
	<div class="main-content side-content pt-0">
		<div class="container-fluid">
			<div class="inner-body custom-scrollbar-js" id="content-5">
				<div class="row custom-scrollbar-css">
					<div class="col-md-12">
						<div class="row">
							<div class="col-md-12">
								<a class="text-dark" href="home.jsp">Home</a> <span>/</span> <span
									class="activeurl">Customer Details</span>
							</div>
						</div>
						
						<input type="hidden" value='<%=communityName%>' id="comName">
						
						<input type="hidden" value='<%=blockName%>' id="blockName">
						
						<input type="hidden" value='<%=type%>' id="type">
						
						

						<div id="accordion" class="accordionFilter">
							<div class="card">
								<div class="card-header">
									<span class="mr-auto">Advance Filter</span> <a
										class="card-link float-right" data-toggle="collapse"
										href="#collapseOne"> <i
										class="fa fa-angle-up reportarrow" aria-hidden="true"></i>
									</a>
								</div>
								<div id="collapseOne" class="collapse hide"
									data-parent="#accordion">
									<div class="card-body">
										<div class="card-body">
											<form id="topupDetails">
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="bmd-label-floating">Date From</label> <input
																type="text" id="start_date" name="start_date"
																class="form-control">
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="bmd-label-floating">Date To</label> <input
																type="text" id="end_date" name="end_date"
																class="form-control">
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="bmd-label-floating">Reading from</label> <input
																type="text" id="reading_from" name="reading_from"
																class="form-control" maxlength="7">
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="bmd-label-floating">Reading To</label> <input
																type="text" id="reading_to" name="reading_to"
																class="form-control" maxlength="7">
														</div>
													</div>

													<div class="col-md-6">
														<div class="form-group">
															<label class="bmd-label-floating">Battery (%)
																From</label> <input type="text"
																class="form-control input_height" id="battery_from"
																name="battery_from">
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="bmd-label-floating">Battery (%) To</label>
															<input type="text" class="form-control input_height"
																id="battery_to" name="battery_to">
														</div>
													</div>

													<div class="col-md-6">
														<div class="form-group">
															<label class="bmd-label-floating select-label">Tamper</label>
															<select class="form-control" id="tamper" name="tamper">
																<option value="-1">Tamper Type</option>
																<option value="1">Magnetic Tamper</option>
																<option value="2">Door Open</option>
																<option value="3">MAG - Door</option>
																<option value="4">NFC Tamper</option>
															</select>
														</div>
													</div>
													<div class="col-md-6"></div>

												</div>

												<div class="row">
													<div class="col-md-11">
														<button type="button"
															class="btn btn-primary btn-raised mr-4"
															id="dashboardFilter">Filter</button>
														<button type="button"
															class="btn btn-danger btn-raised mr-4"
															data-dismiss="modal">
															Close
															<div class="ripple-container"></div>
														</button>
														<button type="button"
															class="btn btn-secondary btn-raised mr-4"
															id="resetFilter">Reset</button>
													</div>
													<!-- <div class="col-md-1">
														<button type="button"
															class="btn btn-secondary btn-raised mr-3 resetFilter"
															id="resetFilter">Reset</button>
													</div> -->
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="row mr-0 ml-0">

							<div class="right_data col-md-12 mt-4 mb-4">
								<!--Right start-->
								<div class="row">
									<div class="col-md-12">
										<table id="liveTable"
											class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
											style="width: 100%">
											<thead id="theadBody" class="bg-primary text-white">
												<tr>
													<th>Community</th>
													<th>Block</th>
													<th>House Number</th>
													<th>Name</th>
													<th>CRN/CAN/UAN</th>
													<th>Meters</th>
													
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>


										<table id="liveTable1"
											class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
											style="width: 100%">
											<thead id="theadBody" class="bg-primary text-white">
												<tr>
													<th>Community</th>
													<th>Block</th>
													<th>House Number</th>
													<th>Name</th>
													<th>CRN/CAN/UAN</th>
													<th>Meters</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
								</div>

								<!--Right end-->
							</div>
						</div>
					</div>
				</div>
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

					<table id="customerMeterTable"
						class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
						style="width: 100%">
						<thead>
							<tr>
								<th>TimeStamp</th>
								<th>Meter Serial Number</th>
								<th>MIU ID</th>
								<th>Gateway</th>
								<th>Reading</th>
								<th>Consumption</th>
								<th>Battery</th>
								<th>Box AMR Tamper</th>
								<th>Magnetic Tamper</th>
								<th>NFC Tamper</th>
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
					</table>


				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />

	<%
		}
	%>
	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	<script src="js/customerDashboard.js"></script>
	<script src="js/common.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>

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



	<script
		src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>

	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>



	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				var date = new Date();
				var currentMonth = date.getMonth();
				var currentDate = date.getDate();
				var currentYear = date.getFullYear();
				/* $('#start_date').bootstrapMaterialDatePicker
				({
					time: true,
					clearButton: true,
					format: 'YYYY-MM-DD HH:mm',
					 maxDate: new Date(currentYear, currentMonth, currentDate)
				}); */

				$('#start_date').datepicker({
					todayHighlight : true,
					autoclose : true,
					format : "yyyy-mm-dd",
					clearBtn : true,
					todayBtn : "linked",
					weekStart : 1
				}).on('changeDate', function(e) {
					var startDate = $('#start_date').datepicker('getDate');
					$('#end_date').datepicker('setStartDate', startDate);
				});
				var lastDate = new Date(new Date().getFullYear(), new Date()
						.getMonth() + 1, 0);

				$('#end_date').datepicker({
					todayHighlight : true,
					autoclose : true,
					format : "yyyy-mm-dd",
					clearBtn : true,
					todayBtn : "linked",
					weekStart : 1
				}).on('changeDate', function(e) {
					var endDate = $('#end_date').datepicker('getDate');
					$('#start_date').datepicker('setEndDate', lastDate);
				});
			});
</script>
</html>