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

<title>Meter Size</title>
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
								<a class="text-dark" href="home.jsp">Home</a> <span>/</span> <a
									class="text-dark" href="setup.jsp">Customer Setup</a> <span>/</span>
								<span class="activeurl">Meter Size</span>
							</div>
						</div>

						<div class="row mr-0 ml-0">

							<div class="right_data col-md-12 mt-4 mb-4">
								<!--Right start-->
								<div class="row">
									<div class="col-md-12">
										<table id="metersizeTable"
											class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer"
											style="width: 100%">
											<thead class="bg-primary text-white">
												<tr>
													<th>Type</th>
													<th>Size</th>
													<th>Per Unit Value</th>
													<th>Date</th>
													<th id="thfiled">Action</th>

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
					<h5 class="modal-title" id="exampleModalLabel">Add Meter Size
						Form</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="metersizeDetails">
						<div class="row">
							<div class="col-md-6">
								<div class="group form-group has-feedback has-success">
									<label>Select
										Meter Type<span class="impp"><sup>*</sup></span>
									</label>
									<select class='form-control select2'
										id=meterTypeAdd name=meterTypeAdd>
										<option value='Gas'>Gas</option>
										<option value='Water'>Water</option>
									</select>
								</div>
							</div>




							<div class="col-md-6">
								<div class="group form-group">
									<label for="text">Size:<span class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control form-control-sm" name="meterSizeAdd"
										id="meterSizeAdd" placeholder="Meter Size">
								</div>
							</div>

							<div class="col-md-6">
								<div class="group form-group">
									<label for="text">Per Unit Value:<span class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control form-control-sm" name="perUnitValueAdd"
										id="perUnitValueAdd" placeholder="Per Unit Value">
								</div>
							</div>



						</div>
						<div class="row">
							<div class="col-md-12 text-right">
								<button class="btn btn-primary submit-button" value="Save!"
									id="metersizeAdd" type="button" disabled>Save</button>
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

	<div class="modal fade" id="mymetersizeEdit" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">Edit Meter Size</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="meterSizeEdit">
						<div class="row">
							<div class="col-md-6">
								<div id="formmeterType" class="group form-group">
									<label for="text">Meter Type:<span class="impp"><sup>*</sup></span></label> <select
										class='form-control form-control-sm select2' id=meterTypeEdit
										name=meterTypeEdit>
										<option value='Gas'>Gas</option>
										<option value='Water'>Water</option>
									</select>
								</div>
							</div>


							<div class="col-md-6">
								<div id="formmeterSize" class="group form-group">
									<label for="text">Size:<span class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control form-control-sm" name="meterSizeEdit"
										id="meterSizeeEdit" placeholder="Meter Size">
								</div>
							</div>

							<div class="col-md-6">

								<div id="formperUnitValue" class="group form-group">
									<label for="text">Per Unit Value:<span class="impp"><sup>*</sup></span></label> <input type="text"
										class="form-control form-control-sm" name="perUnitValueEdit"
										id="perUnitValueEdit" placeholder="">
								</div>
								<input type="hidden" id="meterSizeIDhidden">
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 text-right">
								<button class="btn btn-primary submit-button" value="Save!"
									id="metersizeEditsave" type="button" disabled>Update</button>
								<button type="button"
									class="btn btn-danger btn-raised resetFilter" id="resetFilter">Reset</button>
								<button type="button" class="btn btn-danger btn-raised "
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

	<script src="js/metersize.js"></script>
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
		
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
		
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
			$('#communityTable').DataTable();
		});
	</script>

</body>

</html>