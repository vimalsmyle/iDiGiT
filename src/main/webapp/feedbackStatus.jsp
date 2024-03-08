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

<title>Feedback Details</title>
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
								<a class="text-dark" href="home.jsp">Home</a> <span>/</span> <span
									class="activeurl">Feedback Status</span>
							</div>
						</div>


						<div class="row mr-0 ml-0">

							<div class="right_data col-md-12 mt-4 mb-4">
								<!--Right start-->
								<!-- <div class="row mb-4">
					<div class="col-md-6">
						<h3>Approval Details</h3>
					</div>
				</div> -->
								<div class="row">
									<div class="col-md-12">
										<table id="feedbackTable"
											class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
											style="width: 100%">
											<thead>
												<tr>
													<th>CRN Number</th>
													<th>House Number</th>
													<th>Name</th>
													<th>Feedback</th>
													<th>Description</th>
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

	<div class="modal fade" id="filter" tabindex="-1" role="dialog"
		aria-labelledby="filterModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Feedback Form</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-6">
							<div
								class="group form-group has-feedback has-success bmd-form-group is-filled">
								<label class="bmd-label-floating select-label">Action</label> <select
									class="form-control" id="action" name="action">
									<option value="-1">Select Action</option>
									<option value="1">Approved</option>
									<option value="2">Rejected</option>
								</select>

							</div>
						</div>
						<div class="col-md-6">
							<div
								class="group form-group has-feedback has-success bmd-form-group is-filled">
								<label class="bmd-label-floating">Remark</label> <input
									type="hidden" id=feedbackId>
								<textarea class="form-control" id="description"
									name="description"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer m-auto">
						<button type="button" class="btn btn-primary btn-raised mr-4"
							id="feedbackapproval">Submit</button>
						<button type="button" class="btn btn-danger btn-raised mr-4"
							data-dismiss="modal">
							Close
							<div class="ripple-container"></div>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="footer.jsp" />

	<%
		}
	%>

	<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
    integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
    crossorigin="anonymous"></script> -->

	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

	<script src="common/js/bootstrap.min.js"></script>

	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	<script src="js/feedback.js"></script>
	<script src="js/common.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js"
		integrity="sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U"
		crossorigin="anonymous"></script>
	<script
		src="https://unpkg.com/bootstrap-material-design@4.1.1/dist/js/bootstrap-material-design.js"
		integrity="sha384-CauSuKpEqAFajSpkdjv3z9t8E7RlpJ1UP0lKM/+NdtSarroVKu069AlsRPKkFBz9"
		crossorigin="anonymous"></script>
	<script>
		$(document).ready(function() {
			$('body').bootstrapMaterialDesign();
		});
	</script>
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.js"></script> -->
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
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
			$('#liveTable').DataTable();
		});
	</script>

	<script>
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>

</body>

</html>