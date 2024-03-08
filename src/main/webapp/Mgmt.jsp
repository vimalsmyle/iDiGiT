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

<title>User Details</title>
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
			<div class="inner-body custom-scrollbar-js" id="content-5">
				<div class="row custom-scrollbar-css">
					<div class="col-md-12">
						<div class="row">
							<div class="col-md-12">
								<a class="text-dark" href="home.jsp">Home</a>
								<span>/</span>
								<span class="activeurl">User Management</span>
				</div>
						</div>


						<div class="row mr-0 ml-0">

							<div class="right_data col-md-12 mt-4 mb-4">
								<!--Right start-->
								<div class="row mb-4">
									<!-- <div class="col-md-6">
						<h3>User Details</h3>
					</div>
					<div class="col-md-6">
						<button type="button"
							class="btn btn-raised btn-primary float-right"
							data-toggle="modal" data-target="#exampleModal">
							<i class="fa fa-plus">Add</i></i>
						</button>
					</div> -->
								</div>
								<div class="row">
									<div class="col-md-12">
										<table id="mgmtTable"
											class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
											style="width: 100%">
											<thead class="bg-primary text-white">
												<tr>
													<th>User ID</th>
													<th>Email</th>
													<th>Mobile</th>
													<th>User Name</th>
													<th>Role</th>
													<th>Community Name</th>
													<th>Block Name</th>
													<th>Created By User Name</th>
													<th>Created By Role Description</th>
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
					<h5 class="modal-title" id="exampleModalLabel">User Add Form</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="userDetails">
						<div class="row">

							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating select-label">Type Of
										user</label> <select class="form-control" id="selecttypeofuser"
										name="selecttypeofuser"
										onchange="showCommunitybyTypeuser(this.value);">
										<option value="-1">Select Type</option>
										<option value="4">Super Admin Supervisor</option>
										<option value="5">Admin Supervisor</option>
									</select>
								</div>
							</div>

							<div class="col-md-6" id="usercommunityId">
								<div id="formcomunityName" class="group form-group">
									<label class="bmd-label-floating select-label">Community
										Name</label> <select class="form-control" id="selectcommunityName"
										name="selectcommunityName"
										onchange="showBlockbyCommunity(this.value);">
										<!--  <option>Select Community</option> -->
									</select>

								</div>
							</div>
							<div class="col-md-6" id="userblockId">
								<div id="formblockName" class="group form-group">
									<label class="bmd-label-floating select-label">Select
										Block</label> <select class="form-control"
										id="selectBlockBasedonCommunity"
										name="selectBlockBasedonCommunity">
									</select>
								</div>
							</div>

							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">User ID</label> <input
										type="text" class="form-control" name="userIDAdd"
										id="userIDAdd">
								</div>
							</div>
							
							
							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">Email</label> <input
										type="email" class="form-control" name="emailAdd"
										id="emailAdd">
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">Mobile no.</label> <input
										type="text" class="form-control" name="mobileAdd"
										id="mobileAdd">
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">User Name</label> <input
										type="text" class="form-control" name="userNameAdd"
										id="userNameAdd">
								</div>
							</div>



							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">User Password</label> <input
										type="password" class="form-control" name="userPasswordAdd"
										id="userPasswordAdd">
								</div>
							</div>
							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">Confirm Password</label> <input
										type="password" class="form-control" name="confirmPasswordAdd"
										id="confirmPasswordAdd">
								</div>
							</div>

							<div class="col-md-6"></div>

							<div class="col-md-4">
								<button class="btn btn-primary submit-button" value="Save!"
									id="userAdd" type="button" disabled>Save</button>
							</div>

							<div class="col-md-3">
								<button type="button"
									class="btn btn-danger btn-raised mr-3 resetFilter"
									id="resetFilter">Reset</button>
							</div>


							<div class="col-md-4">
								<button type="button" class="btn btn-danger btn-raised mr-4"
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
	<script src="js/mgmt.js"></script>
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