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
<link href="common/css/materialize.fontawsome.css" rel="stylesheet">

<title>Report</title>
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
						<div class="page-header">
							<!-- <h3>Gas</h3> -->
						</div>
						<div class="row">
							<div class="col-xl-3 col-md-6 mb-4">
								<div class="card border-left-warning shadow sidingBlock">
									<a href="userReport.jsp"><div class="card-body p-3 ">
											<div class="row m-0">
												<div
													class="text-xs font-weight-bold text-uppercase  justify-content-start col p-0">
													User Consumption Report</div>
												<div class="justify-content-end f-30 d-flex">
													<i class="fa fa-bar-chart"></i>
												</div>
											</div>

										</div></a>
								</div>
							</div>


							<div class="col-xl-3 col-md-6 mb-4">
								<div class="card border-left-warning shadow sidingBlock">
									<a href="topupSummary.jsp"><div class="card-body p-3 ">
											<div class="row m-0">
												<div
													class="text-xs font-weight-bold text-uppercase  justify-content-start col p-0">
													Topup Summary Report</div>
												<div class="justify-content-end f-30 d-flex">
													<i class="fa fa-arrow-up"></i>
												</div>
											</div>

										</div></a>
								</div>
							</div>

							<div class="col-xl-3 col-md-6 mb-4">
								<div class="card border-left-warning shadow sidingBlock">
									<a href="billSummary.jsp"><div class="card-body p-3 ">
											<div class="row m-0">
												<div
													class="text-xs font-weight-bold text-uppercase  justify-content-start col p-0">
													Bill Summary Report</div>
												<div class="justify-content-end f-30 d-flex">
													<i class="fa fa-files-o"></i>
												</div>
											</div>

										</div></a>
								</div>
							</div>


							<div class="col-xl-3 col-md-6 mb-4">
								<div class="card border-left-warning shadow sidingBlock">
									<a href="financialreports.jsp"><div class="card-body p-3 ">
											<div class="row m-0">
												<div
													class="text-xs font-weight-bold text-uppercase  justify-content-start col p-0">
													Financial Report</div>
												<div class="justify-content-end f-30 d-flex">
													<i class="fa fa-money"></i>
												</div>
											</div>

										</div></a>
								</div>
							</div>


							<div class="col-xl-3 col-md-6 mb-4">
								<div class="card border-left-warning shadow sidingBlock">
									<a href="alarmReport.jsp"><div class="card-body p-3 ">
											<div class="row m-0">
												<div
													class="text-xs font-weight-bold text-uppercase  justify-content-start col p-0">
													Alarm Report</div>
												<div class="justify-content-end f-30 d-flex">
													<i class="fa fa-bell"></i>
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

	<script src="js/common.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
</body>

</html>