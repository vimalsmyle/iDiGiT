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

<title>Bill Payment</title>
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
						<div class="row mr-0 ml-0">

							<div class="row">
								<div class="col-md-12">
									<a class="text-dark" href="home.jsp">Home</a> <span>/</span> <span
										class="activeurl">Bill Payment</span>
								</div>
							</div>

							<div class="right_data col-md-12 mt-4 mb-4">
								<!--Right start-->
								<div class="row mb-4">
									<div class="col-md-10 m-auto">
										<div class="card">
											<div class="card-header bg-primary cardHeading text-white">Bill Payment</div>
											<div class="card-body scroll right-block">
												<form id="topupDetails">
													<div class="row">

														<%
															if (user_id.equalsIgnoreCase("1")) {
														%>

														<div class="col-md-4">
															<div class="group form-group">
																<label class="bmd-label-floating select-label">Community<sup
																	class="imp">*</sup></label> <select
																	class="form-control form-control-sm select2"
																	id="selectcommunityName" name="selectcommunityName"
																	onchange="showBlockbyCommunity(this.value);">
																</select>
															</div>
														</div>
														<div class="col-md-4">
															<div class="group form-group">
																<label class="bmd-label-floating select-label">Select
																	Block<sup class="imp">*</sup>
																</label> <select class="form-control form-control-sm select2"
																	id="selectBlockBasedonCommunity"
																	name="selectBlockBasedonCommunity"
																	onchange="showCustomerbyBlockForBill(this.value);">

																</select>
															</div>
														</div>
														<div class="col-md-4">
															<div class="group form-group">
																<label class="bmd-label-floating select-label">Select
																	CRN<sup class="imp">*</sup>
																</label> <select class="form-control form-control-sm select2"
																	id="selectHouseBasedonBlockForBill"
																	name="selectHouseBasedonBlockForBill"
																	onchange="showBillingDetails(this.value);">
																</select>
															</div>
														</div>
														<%
															} else if (user_id.equalsIgnoreCase("2")) {
														%>

														<div class="col-md-4">
															<div id="formcommunityNameAdd" class="group form-group">
																<label class="bmd-label-floating">Community Name</label>
																<input type="text" class="form-control form-control-sm"
																	name="communityNameAdd" id="communityNameAdd" disabled>
															</div>
														</div>
														<div class="col-md-4">
															<div id="formblockNameAdd" class="group form-group">
																<label class="bmd-label-floating">Block Name</label> <input
																	type="text" class="form-control form-control-sm"
																	name="blockNameAdd" id="blockNameAdd" disabled>
															</div>
														</div>

														<div class="col-md-4">
															<div class="group form-group">
																<label class="bmd-label-floating select-label">Select
																	CRN<sup class="imp">*</sup>
																</label> <select class="form-control form-control-sm"
																	id="selectHouseBasedonBlockForBill"
																	name="selectHouseBasedonBlockForBill"
																	onchange="showBillingDetails(this.value);">
																</select>
															</div>
														</div>

														<%
															} else if (user_id.equalsIgnoreCase("3")) {
														%>
														<div class="col-md-4">
															<div id="formCRNNumber" class="group form-group has-feedback has-success bmd-form-group is-filled">
																<label class="bmd-label-floating">CRN Number</label> <input
																	type="text" class="form-control form-control-sm"
																	id="selectHouseBasedonBlockForBill"
																	name="selectHouseBasedonBlockForBill"
																	disabled>
															</div>
														</div>

														<%
															}
														%>

														<div class="col-md-4">
															<div id="formcurrentBalance_topup"
																class="group form-group">
																<label class="bmd-label-floating">Total Consumption</label> <input type="text"
																	class="form-control form-control-sm"
																	id="totalConsumption" name="totalConsumption"
																	disabled>
															</div>
														</div>
														<div class="col-md-4">
															<div id="formdateTime_topup" class="group form-group">
																<label class="bmd-label-floating">Total Amount</label> <input
																	type="text" class="form-control form-control-sm"
																	id="totalAmount" name="totalAmount" disabled>
																	
																	<input
																	type="hidden" class="form-control form-control-sm"
																	id="customerBillingID" name="customerBillingID">
															</div>
														</div>
														<div class="col-md-4">
															<div id="formunit_topup" class="group form-group">
																<label class="bmd-label-floating">Late Fee</label> <input
																	type="text" class="form-control form-control-sm"
																	id="lateFee" name="lateFee" disabled>
															</div>
														</div>
														<div class="col-md-4">
															<div id="formemergency_topup" class="group form-group">
																<label class="bmd-label-floating">Due Date</label> <input type="text"
																	class="form-control form-control-sm"
																	id="dueDate" name="dueDate" disabled>
															</div>
														</div>
														<div class="col-md-4">
															<div id="formalarm_topup" class="group form-group">
																<label class="bmd-label-floating">Billing Date</label> <input
																	type="text" class="form-control form-control-sm"
																	id="billingDate" name="billingDate" disabled>
															</div>
														</div>

														<div class="col-md-4">
															<div id="formreconnection_topup" class="group form-group">
																<label class="bmd-label-floating">Bill Month</label> <input type="text"
																	class="form-control form-control-sm"
																	id="billMonth" name="billMonth"
																	disabled>
															</div>
														</div>

														<div class="col-md-4">
															<div id="formfixed_topup" class="group form-group">
																<label class="bmd-label-floating">Bill Year</label> <input
																	type="text" class="form-control form-control-sm"
																	id="billYear" name="billYear" disabled>
															</div>
														</div>

														
														<%
															if (!user_id.equalsIgnoreCase("3")) {
														%>
														<div class="col-md-4">
															<div class="group form-group">
																<label class="bmd-label-floating">Payment Mode</label> <select
																	class="form-control form-control-sm" id="paymentMode"
																	name="paymentMode">
																	<option value="-1">Select Mode</option>
																	<option value="Cash">Cash</option>
																	<option value="Online">Online</option>
																</select>
															</div>
														</div>
														<%
															}
														%>

													</div>

													<div class="row">
														<div class="col-md-12 text-right">
															<!--     <button type="button" id="topup" class="btn btn-primary submit-button btn-raised float-right mr-4" disabled>Submit<div class="ripple-container"></div></button> -->
															<button id="bill"
																class="btn btn-secondary submit-button btn-raised"
																>
																Submit
																<div class="ripple-container"></div>
															</button>
															<button type="button"
																class="btn btn-secondary btn-raised resetFilter"
																id="resetFilter">Reset</button>
														</div>

													</div>
												</form>
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


	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Proceed to Pay
						from RazorPay</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="payOnline"></div>
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
	<script src="js/bill.js"></script>
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

	<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
		$(document).ready(function() {
			$('.button-left').click(function() {
				$('.left ').toggleClass('fliph');

			});
		});
	</script>
</body>

</html>