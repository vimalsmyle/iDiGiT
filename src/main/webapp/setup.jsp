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

<title>Setup</title>
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
	<div class="main-content side-content pt-0 custom-scrollbar-js">
		<div class="container-fluid">
			<div class="inner-body custom-scrollbar-js" id="content-5">
				<div class="row custom-scrollbar-css">
					<div class="col-md-12">
						<div class="row">

							<%
								if (user_id.equals("1") || user_id.equals("4")) {
							%>

							<div class="col-xl-3 col-md-6 mb-4">


								<div class="card border-left-warning shadow sidingBlock">
									<a href="communityDetails.jsp"><div class="card-body p-3 ">
											<div class="row m-0">
												<div
													class="text-xs font-weight-bold text-uppercase  justify-content-start col p-0">
													Community Details</div>
												<div class="justify-content-end f-30 d-flex">
													<i class="fa fa-building"></i>
												</div>
											</div>

										</div></a>
								</div>

							</div>
							<div class="col-xl-3 col-md-6 mb-4">
								<div class="card border-left-warning shadow sidingBlock">
									<a href="blockDetails.jsp"><div class="card-body p-3">
											<div class="row m-0">
												<div
													class="text-xs font-weight-bold text-uppercase  justify-content-start col p-0">
													Block Details</div>
												<div class="justify-content-end f-30 d-flex">
													<i class="fa fa-building"></i>
												</div>
											</div>

										</div></a>
								</div>
							</div>
							
							<div class="col-xl-3 col-md-6 mb-4">
								<div class="card border-left-warning shadow sidingBlock">
									<a href="gateway.jsp"><div class="card-body p-3">
											<div class="row m-0">
												<div
													class="text-xs font-weight-bold text-uppercase  justify-content-start col p-0">
													Gateway Details</div>
												<div class="justify-content-end f-30 d-flex">
													<i class="fa fa-key"></i>
												</div>
											</div>

										</div></a>
								</div>
							</div>
							
							
							<div class="col-xl-3 col-md-6 mb-4">
								<div class="card border-left-warning shadow sidingBlock">
									<a href="MeterSize.jsp"><div class="card-body p-3">
											<div class="row m-0">
												<div
													class="text-xs font-weight-bold text-uppercase  justify-content-start col p-0">
													Meter size</div>
												<div class="justify-content-end f-30 d-flex">
													<i class="fa fa-dashboard"></i>
												</div>
											</div>

										</div></a>
								</div>
							</div>

							<%
								}
							%>

							<%
								if (!user_id.equals("3")) {
							%>
							

							

							<%
								}
							%>


							<div class="col-xl-3 col-md-6 mb-4">
								<div class="card border-left-warning shadow sidingBlock">
									<a href="customerDetails.jsp"><div class="card-body p-3">
											<div class="row m-0">
												<div
													class="text-xs font-weight-bold text-uppercase  justify-content-start col p-0">
													Customer Details</div>
												<div class="justify-content-end f-30 d-flex">
													<i class="fa fa-users"></i>
												</div>
											</div>

										</div></a>
								</div>
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



</body>

</html>