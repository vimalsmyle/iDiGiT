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
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-datetimepicker/2.7.1/css/bootstrap-material-datetimepicker.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">


<link href="common/css/materialize.fontawsome.css" rel="stylesheet">

<title>Customer Management</title>
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
						<div class="row mr-0 ml-0">

							<div class="right_data col-md-12 mt-4 mb-4">
								<!--Right start-->
								<div class="row">
									<div class="col-md-12">
										<h5 class="modal-title" id="exampleModalLabel">Manual
											Data Entry</h5>

										<form id="manualDetails">
											<div id="template">
												<div class="row">
													<div id="loader" style="display: none;">
														<div id="status">&nbsp;</div>
													</div>
													<div class="col-md-4">
														<div
															class="form-group has-feedback has-success bmd-form-group is-filled">
															<label class="bmd-label-floating select-label">Select
																CRN/CAN/UAN</label> <select class="form-control select2"
																id="manualCRNAll" name="manualCRNAll"
																onchange="showMetersbyCRN(this.value);">
															</select>
														</div>
													</div>



													<div class="col-md-4">
														<div
															class="form-group has-feedback has-success bmd-form-group is-filled">
															<label class="bmd-label-floating select-label">Select
																MIU</label> <select class="form-control"
																id="allMeters" name="allMeters"
																onchange="showDetailsbyMeter(this.value);">
															</select>
														</div>
													</div>
													
													<div class="col-md-4">
														<div
															class="form-group has-feedback has-success bmd-form-group is-filled">
															<label class="bmd-label-floating select-label">Previous Reading
															</label> <input type="text" class="form-control form-control-sm"
																name="oldreading" id="oldreading" disabled>
														</div>
													</div>

													<div class="col-md-4">
														<div
															class="form-group has-feedback has-success bmd-form-group is-filled">
															<label class="bmd-label-floating select-label">Last updated time
															</label> <input type="text"
																class="form-control form-control-sm" name="lastUpdatedTime"
																id="lastUpdatedTime" disabled>
														</div>
													</div>
													
													
													<div class="col-md-4">
														<div
															class="form-group has-feedback has-success bmd-form-group is-filled">
															<label class="bmd-label-floating select-label">Current Reading
															</label> <input type="text"
																class="form-control form-control-sm" name="currentreading"
																id="currentreading">
														</div>
													</div>
													
												</div>
											</div>

											<div class="row mt-2">

												<div class="col-md-12 text-right">
													<button class="btn btn-primary submit-button" value="Save!"
														id="manual" type="button">Save</button>


												</div>
											</div>
										</form>

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


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-datetimepicker/2.7.1/js/bootstrap-material-datetimepicker.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/locale/ja.js"></script>


	<script src="js/dropdown.js"></script>
	<script src="js/manualdata.js"></script>
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
		src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


	<script>
		$(function() {
			$('#timestamp').bootstrapMaterialDatePicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				clearBtn : true,
				todayBtn : "linked",
				weekStart : 1,
			})

			$('#syncTime').bootstrapMaterialDatePicker({
				format : 'HH:mm:ss',
				clearBtn : true,
				todayBtn : "linked",
				weekStart : 1,
			})

		});
	</script>
</body>

</html>