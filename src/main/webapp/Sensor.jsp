<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="icon" type="image/png" sizes="16x16" href="common/images/1-hanbit.png">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<!-- Material Design for Bootstrap CSS -->
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
	integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel="stylesheet" href="common/css/style.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">

<title>Sensor Dashboard</title>
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
								<a class="text-dark" href="#">Home</a> <span>/</span> <a
									class="text-dark" href="setup.jsp">Customer Setup</a> <span>/</span>
								<span class="activeurl">Sensor Dashboard</span>
							</div>
						</div>

					<input type="hidden" id="action" />
						<div class="row mr-0 ml-0">

							<div class="right_data col-md-12 mt-4 mb-4">
								<!--Right start-->
								<div class="row">
									<div class="col-md-12">
										<table id="sensorTable"
											class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
											style="width: 100%">
											<thead class="bg-primary text-white">
												<tr>
													<th>Community</th>
													<th>Block</th>
													<th>CRN/CAN/UAN</th>
													<th>First Name</th>
													<th>Last Name</th>
													<th>House No</th>
													<th>Equipment Id</th>
													
													<th>Record Interval</th>
													<th>Sync Interval</th>
													<th>RSSI</th>
													
													<th>Battery Percentage</th>
													<th>Online Power Suppy</th>
													<th>GSM Status</th>
													<th>Ethernet Status</th>
													<th>NFC Status</th>
													<th>Flash Status</th>
													<th>NFC Memory Status</th>
													
													<th>Flash Memory Status</th>
													<th>Low GSM</th>
													<th>Low Battery</th>
													<th>Sensor Detachment</th>
													<th>Door Open Switch</th>
													<th>Magnetic Tamper</th>
													<th>Log Date</th>
													
													
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

	<jsp:include page="footer.jsp" />

	<div class="modal fade" id="filter" tabindex="-1" role="dialog"
		aria-labelledby="filterModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Customer
						Management Filter</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-6">
							<div
								class="form-group has-feedback has-success bmd-form-group is-filled">
								<label class="bmd-label-floating select-label">Select
									Community</label> <select class="form-control"
									id="filterselectcommunityName" name="filterselectcommunityName"
									onchange="showBlockbyCommunity(this.value);">
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div
								class="form-group has-feedback has-success bmd-form-group is-filled">
								<label class="bmd-label-floating select-label">Select
									Block</label> <select class="form-control"
									id="filterselectBlockBasedonCommunity"
									name="filterselectBlockBasedonCommunity">
								</select>
							</div>
						</div>
					</div>

					<div class="modal-footer m-auto">
						<button type="button" class="btn btn-primary btn-raised mr-4"
							id="customerFilter">Filter</button>
						<button type="button" class="btn btn-danger btn-raised mr-4"
							data-dismiss="modal">
							Close
							<div class="ripple-container"></div>
						</button>
						<button type="button" class="btn btn-secondary btn-raised mr-4"
							id="resetFilter">Reset</button>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade bd-example-modal-lg" id="mySensorMeters"
		role="dialog">
		<div class="modal-dialog modal-lg
		">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">Detail of Meters</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">


					<table id="sensorMeterTable"
						class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
						style="width: 100%">
						<thead>
							<tr>
								<th>Reading 1</th>
								<th>Reading 2</th>
								<th>Reading 3</th>
								<th>Reading 4</th>
							</tr>
							</thead>
						<tbody id="readingBody">
						</tbody>
						<thead>
							
							
							<tr>
								<th>Reader Sensor Status 1</th>
								<th>Reader Sensor Status 2</th>
								<th>Reader Sensor Status 3</th>
								<th>Reader Sensor Status 4</th>
							</tr>
							
							
						</thead>
						<tbody id="readerSensorStatusBody">
						</tbody>
						
						<thead>
							<tr>
								<th>Per Day Flow Rate 1</th>
								<th>Per Day Flow Rate 2</th>
								<th>Per Day Flow Rate 3</th>
								<th>Per Day Flow Rate 4</th>
							</tr>
							
							
						</thead>
						<tbody id="tbodyPerDayFlowRateBody">
						</tbody>
						
						
						<thead>
							<tr>
								<th>Live Flow Rate 1</th>
								<th>Live Flow Rate 2</th>
								<th>Live Flow Rate 3</th>
								<th>Live Flow Rate 4</th>
							</tr>
							
							
						</thead>
						<tbody id="tbodyLiveFlowRateBody">
						</tbody>
						
						
						<thead>
							<tr>
								<th>Digital Output 1</th>
								<th>Digital Output 2</th>
								<th>Digital Output 3</th>
								<th>Digital Output 4</th>
							</tr>
							
							
						</thead>
						<tbody id="tbodyDigitalOutputBody">
						</tbody>
						
						
						
						
						<thead>
							<tr>
								<th>Analog Input 1</th>
								<th>Analog Input 2</th>
								<th>Analog Input 3</th>
								<th>Analog Input 4</th>
							</tr>
							
							
						</thead>
						<tbody id="tbodyAnalogInputBody">
						</tbody>
						
						
						
						
						<thead>
							<tr>
								<th>Analog Output 1</th>
								<th>Analog Output 2</th>
								<th>Analog Output 3</th>
								<th>Analog Output 4</th>
							</tr>
							
							
						</thead>
						<tbody id="tbodyAnalogOutputBody">
						</tbody>
						
						
						
						
						<thead>
							<tr>
								<th>Voltage Output 1</th>
								<th>Voltage Output 2</th>
								<th>Voltage Output 3</th>
								<th>Voltage Output 4</th>
							</tr>
							
							
						</thead>
						<tbody id="tbodyVoltageOutputBody">
						</tbody>
						
						
						
					</table>


				</div>
			</div>
		</div>
	</div>

	<%
		}
	%>

	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>


	<script src="js/dropdown.js"></script>
	<script src="js/sensor.js"></script>
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
		src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>


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
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
		$(document).ready(function() {
			$('#customerTable').DataTable();
		});
	</script>

</body>

</html>