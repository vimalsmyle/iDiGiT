<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<link rel="icon" type="image/png" sizes="16x16"
	href="common/images/1-hanbit.png">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">

<link href="common/css/materialize.fontawsome.css" rel="stylesheet">

<title>Block Management</title>
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
								<span class="activeurl">Block Management</span>
							</div>
						</div>

						<div class="row mr-0 ml-0">
							<div class="right_data col-md-12 mt-4 mb-4">
							<div id="loader" style="display: none;">
														<div id="status">&nbsp;</div>
													</div>
								<!--Right start-->
								<div class="row">
									<div class="col-md-12">
										<table id="blockTable"
											class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
											style="width: 100%">
											<thead class="bg-primary text-white">
												<tr>
													<th>Community</th>
													<th>Block Name</th>
													<th>Location</th>
													<th>email</th>
													<th>Mobile</th>
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
					<h5 class="modal-title" id="exampleModalLabel">Add Block</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="blockDetails">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="text">Community:<span class="impp"><sup>*</sup></span></label>
									<select class="form-control  form-control-sm select2"
										id="selectcommunityName" name="selectcommunityName">
									</select>
								</div>
							</div>



							<div class="col-md-6">
								<div class="group form-group">
									<label for="text">Block Name:<span class="impp"><sup>*</sup></span></label>
									<input type="text" class="form-control  form-control-sm"
										name="blockNameAdd" id="blockNameAdd">
								</div>
							</div>

							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">Location:<span
										class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control  form-control-sm  form-control  form-control-sm-sm"
										name="blockLocationAdd" id="blockLocationAdd">
								</div>
							</div>
							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">Mobile Number<span
										class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control  form-control-sm" name="blockMobileAdd"
										id="blockMobileAdd" maxlength="10">
								</div>
							</div>

							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">Email<span
										class="impp"><sup>*</sup></span></label> <input type="email"
										class="form-control  form-control-sm" name="blockEmailAdd"
										id="blockEmailAdd">
								</div>
							</div>

							<div class="col-md-6"></div>
						</div>

						<div class="row">
							<div class="col-md-12 text-right">
								<button class="btn btn-primary submit-button" value="Save!"
									id="blockAdd" type="button" disabled>Save</button>
								<button type="button"
									class="btn btn-danger btn-raised resetFilter" id="resetFilter">Reset</button>
								<button type="button" class="btn btn-danger btn-raised"
									data-dismiss="modal">
									Close
									<div class="ripple-container"></div>
								</button>
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="myBlockEdit" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">Edit Block</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="blockEdit">
						<div class="row">
							<div class="col-md-6">
								<div id="formcomunityName" class="group form-group">
									<label class="bmd-label-floating">Community<span
										class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control  form-control-sm select2"
										name="communityNameEdit" id="communityNameEdit" disabled>
								</div>
							</div>
							<div class="col-md-6">
								<div id="formblockName" class="group form-group">
									<label class="bmd-label-floating">Block Name<span
										class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control  form-control-sm" name="blockNameEdit"
										id="blockNameEdit">
								</div>
							</div>

							<div class="col-md-6">
								<div id="formblocklocation" class="group form-group">
									<label class="bmd-label-floating">Location<span
										class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control  form-control-sm" name="blockLocationEdit"
										id="blockLocationEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div id="formblockMobile" class="group form-group">
									<label class="bmd-label-floating">Mobile Number<span
										class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control  form-control-sm" name="blockMobileEdit"
										id="blockMobileEdit" maxlength="10">
								</div>
							</div>

							<div class="col-md-6">
								<div id="formblockEmail" class="group form-group">
									<label class="bmd-label-floating">Email<span
										class="impp"><sup>*</sup></span></label> <input type="email"
										class="form-control  form-control-sm" name="blockEmailEdit"
										id="blockEmailEdit"> <input type="hidden"
										id="blockIdhidden">

								</div>
							</div>
							<div class="col-md-6"></div>
						</div>
						<div class="row">
							<div class="col-md-12 text-right">
								<button class="btn btn-primary submit-button" value="Save!"
									id="blockEditsave" type="button" disabled>Update</button>

								<button type="button"
									class="btn btn-danger btn-raised resetFilter" id="resetFilter">Reset</button>
								<button type="button" class="btn btn-danger btn-raised"
									data-dismiss="modal">
									Close
									<div class="ripple-container"></div>
								</button>

							</div>
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
	<script src="js/block.js"></script>
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