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
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">

<link href="common/css/materialize.fontawsome.css" rel="stylesheet">

<title>Topup Status</title>
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
								<a class="text-dark" href="home.jsp">Home</a> <span>/</span> <span
									class="activeurl">Topup Status</span>
							</div>
						</div>


						<div class="row mr-0 ml-0">

							<div class="right_data col-md-12 mt-4 mb-4">
								<!--Right start-->
								<div class="row">
									<div class="col-md-12">
										<table id="topstatusTable"
											class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
											style="width: 100%">
											<thead class="bg-primary text-white">
												<tr>
													<th>Community</th>
													<th>Block</th>
													<th>House No</th>
													<th>MIU ID</th>
													<th>Amount</th>
													<th>EC</th>
													<th>Alarm Credit</th>
													<th>Mode of Payment</th>
													<th>Order ID</th>
													<th>Payment ID</th>
													<th>Refund ID</th>
													<th>Refund Status</th>
													<th>Date</th>
													<th>By UserName</th>
													<th>By Role Description</th>
													<th>Status</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
										
										
										<table id="topstatusTable1"
											class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
											style="width: 100%">
											<thead class="bg-primary text-white">
												<tr>
													<th>Community</th>
													<th>Block</th>
													<th>House No</th>
													<th>MIU ID</th>
													<th>Amount</th>
													<th>EC</th>
													<th>Alarm Credit</th>
													<th>Mode of Payment</th>
													<th>Order ID</th>
													<th>Payment ID</th>
													<th>Refund ID</th>
													<th>Refund Status</th>
													<th>Date</th>
													<th>By UserName</th>
													<th>By Role Description</th>
													<th>Status</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>



									</div>
								</div>


								<div class="modal fade" id="filter" tabindex="-1" role="dialog"
									aria-labelledby="filterModalLabel" aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">Configuration
													Filter</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<div class="row">
													<div class="col-md-6">
														<div
															class="input-group form-group has-feedback has-success bmd-form-group is-filled">
															<label class="bmd-label-floating select-label">Select
																Community</label> <select class="form-control"
																id="filterselectcommunityName"
																name="filterselectcommunityName"
																onchange="showBlockbyCommunity(this.value);">
															</select>
														</div>
													</div>
													<div class="col-md-6">
														<div
															class="input-group form-group has-feedback has-success bmd-form-group is-filled">
															<label class="bmd-label-floating select-label">Select
																Block</label> <select class="form-control"
																id="filterselectBlockBasedonCommunity"
																name="filterselectBlockBasedonCommunity">
															</select>
														</div>
													</div>
												</div>
												<div class="modal-footer m-auto">
													<button type="button"
														class="btn btn-primary btn-raised mr-4"
														id="customerFilter">Filter</button>
													<button type="button"
														class="btn btn-danger btn-raised mr-4"
														data-dismiss="modal">
														Close
														<div class="ripple-container"></div>
													</button>
													<button type="button"
														class="btn btn-secondary btn-raised mr-4" id="resetFilter">Reset</button>

												</div>
											</div>
										</div>
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

	<%
		}
	%>

	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>


	<script src="js/dropdown.js"></script>
	<script src="js/topup.js"></script>
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
</body>

</html>