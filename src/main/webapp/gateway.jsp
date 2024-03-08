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
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">

<link href="common/css/materialize.fontawsome.css" rel="stylesheet">

<title>Customer Management</title>
</head>


<body class="main-sidebar-show">
	<%
		String user_id = (String) session.getAttribute("roleID");

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
			<div class="inner-body custom-scrollbar-js" id="content-5">
				<div class="row custom-scrollbar-css">
					<div class="col-md-12">
						<div class="row">
							<div class="col-md-12">
								<a class="text-dark" href="home.jsp">Home</a> <span>/</span> <a
									class="text-dark" href="setup.jsp">Customer Setup</a> <span>/</span>
								<span class="activeurl">Gateway Details</span>
							</div>
						</div>

						<div class="row mr-0 ml-0">
							<div class="right_data col-md-12 mt-4 mb-4">
								<!--Right start-->
								<div class="row">
									<div class="col-md-12">
										<table id="gatewayTable"
											class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
											style="width: 100%">
											<thead class="bg-primary text-white">
												<tr>
													<th>Gateway Name</th>
													<th>Gateway Serial Number</th>
													<th>Gateway IP</th>
													<th>Gateway Port</th>
													<th>Date</th>
													<th>Action</th>
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

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add Gateway</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="gatewayDetails">
						<div class="row">
							<div class="col-md-6">
								<div class="group form-group">
									<label for="text">Name:<span class="impp"><sup>*</sup></span></label>
									<input class="form-control" id="gatewayNameAdd"
										name="gatewayNameAdd">

								</div>
							</div>



							<div class="col-md-6">
								<div class="group form-group">
									<label for="text">Serial Number:<span class="impp"><sup>*</sup></span></label>
									<input type="text" class="form-control" name="serialNumberAdd"
										id="serialNumberAdd">
								</div>
							</div>

							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">IP<span class="impp"><sup>*</sup></span></label>
									<input type="text" class="form-control" name="ipAdd" id="ipAdd">
								</div>
							</div>
							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">Port<span
										class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control" name="portAdd" id="portAdd">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 text-right">
								<button class="btn btn-primary submit-button" value="Save!"
									id="gatewayAdd" type="button" disabled>Save</button>

								<button type="button"
									class="btn btn-danger btn-raised resetFilter" id="resetFilter">Reset</button>
								<button type="button" class="btn btn-danger btn-raised"
									data-dismiss="modal">
									Close
									<div class="ripple-container"></div>
								</button>

							</div>

							<!-- 	<div class="col-md-3">
								<button type="button" class="btn btn-secondary btn-raised mr-3 resetFilter" id="resetFilter">Reset</button>
							</div>


							<div class="col-md-4">
								<button type="button" class="btn btn-danger btn-raised mr-4"
									data-dismiss="modal">
									Close
									<div class="ripple-container"></div>
								</button>
							</div> -->
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="myGatewayEdit" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">Edit Gateway</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="gatewayEdit">
						<div class="row">
							<div class="col-md-6">
								<div id="formGatewayName1"
									class="group form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating">Name<span
										class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control" name="gatewayNameEdit"
										id="gatewayNameEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div id="formserialNumber" class="group form-group">
									<label class="bmd-label-floating">Serial Number:<span
										class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control" name="serialNumberEdit"
										id="serialNumberEdit">
								</div>
							</div>

							<div class="col-md-6">
								<div id="formip" class="group form-group">
									<label class="bmd-label-floating">IP<span class="impp"><sup>*</sup></span></label>
									<input type="text" class="form-control" name="ipEdit"
										id="ipEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div id="formport" class="group form-group">
									<label class="bmd-label-floating">Port<span
										class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control" name="portEdit" id="portEdit">
								</div>
							</div>
						</div>

						<input type="hidden" id="gatewayIdhidden">
						<div class="row">
							<div class="col-md-12 text-right">
								<button class="btn btn-primary submit-button" value="Save!"
									id="gatewayEditsave" type="button" disabled>Update</button>

								<button type="button"
									class="btn btn-danger btn-raised resetFilter" id="resetFilter">Reset</button>

								<button type="button" class="btn btn-danger btn-raised"
									data-dismiss="modal">
									Close
									<div class="ripple-container"></div>
								</button>
							</div>

							<!-- <div class="col-md-3">
								<button type="button" class="btn btn-secondary btn-raised mr-3 resetFilter" id="resetFilter">Reset</button>
							</div>


							<div class="col-md-4">
								<button type="button" class="btn btn-danger btn-raised mr-4"
									data-dismiss="modal">
									Close
									<div class="ripple-container"></div>
								</button>
							</div> -->
						</div>
					</form>
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
	<script src="js/gateway.js"></script>
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
		src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>

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
			$('#blockTable').DataTable();
		});
	</script>

</body>

</html>