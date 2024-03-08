<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<link rel="icon" type="image/png" sizes="16x16" href="common/images/1-hanbit.png">
<!-- Material Design for Bootstrap CSS -->
<!-- <link rel="stylesheet" href="common/css/style.css"> -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">
	
	<link href="common/css/materialize.fontawsome.css"
	rel="stylesheet">

<title>Customer Management</title>
</head>


<body class="main-sidebar-show">
<%
		String user_id = (String) session.getAttribute("roleID");

	%>

	<%
		if (user_id == null) {
			response.sendRedirect("login.jsp");
		}else {
	%>
<div id="preloader" style="display:none;">
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
						<h5 class="modal-title" id="exampleModalLabel">Customer Add
						Form</h5>
						
						<form id="customerDetails">
						<div id="template">
						<div class="row">
						<div id="loader" style="display: none;">
														<div id="status">&nbsp;</div>
													</div>
						<%if(!user_id.equalsIgnoreCase("2")) {%>
							<div class="col-md-4">
								<div class="form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating select-label">Community Name<span class="impp"><sup>*</sup></span></label> 
									<select
										class="form-control select3 form-control-sm select2" id="selectcommunityName" name="selectcommunityName" onchange="showBlockbyCommunity(this.value);">
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating select-label">Select Block<span class="impp"><sup>*</sup></span></label> <select
										class="form-control form-control-sm select2" id="selectBlockBasedonCommunity" name="selectBlockBasedonCommunity">
									</select>
								</div>
							</div>
							<%} else if(user_id.equalsIgnoreCase("2")){%>

							<div class="col-md-4">
								<div id = "formcommunityNameAdd" class="form-group">
									<label class="bmd-label-floating">Community Name<span class="impp"><sup>*</sup></span></label> <input
										type="text" class="form-control form-control-sm" name="communityNameAdd"
										id="communityNameAdd" disabled>
								</div>
							</div>
							<div class="col-md-4">
								<div id = "formblockNameAdd" class="form-group">
									<label class="bmd-label-floating">Block Name<span class="impp"><sup>*</sup></span></label> <input
										type="text" class="form-control form-control-sm" name="blockNameAdd"
										id="blockNameAdd"  disabled>
								</div>
							</div>

<%} %>
							<div class="col-md-4">
								<div class="group form-group">
									<label class="bmd-label-floating">First Name<span class="impp"><sup>*</sup></span></label> <input
										type="text" class="form-control form-control-sm" name="firstNameAdd"
										id="firstNameAdd">
								</div>
							</div>
							<div class="col-md-4">
								<div class="group form-group">
									<label class="bmd-label-floating">Last Name<span class="impp"><sup>*</sup></span></label> <input
										type="text" class="form-control form-control-sm" name="lastNameAdd"
										id="lastNameAdd">
								</div>
							</div>
							
							
							
							<div class="col-md-4">
								<div class="group form-group">
									<label class="bmd-label-floating">House No.<span class="impp"><sup>*</sup></span></label> <input
										type="text" class="form-control form-control-sm" name="houseNoAdd"
										id="houseNoAdd">
								</div>
							</div>
							<div class="col-md-4">
								<div class="group form-group">
									<label class="bmd-label-floating">Mobile No<span class="impp"><sup>*</sup></span></label> <input
										type="text" class="form-control form-control-sm" name="mobileNoAdd"
										id="mobileNoAdd" maxlength="10">
								</div>
							</div>
							
							
							
							
							<div class="col-md-4">
								<div class="group form-group">
									<label class="bmd-label-floating">Email<span class="impp"><sup>*</sup></span></label> <input
										type="email" class="form-control form-control-sm" name="emailAdd"
										id="emailAdd">
								</div>
							</div>
							<div class="col-md-4">
								<div class="group form-group">
									<label class="bmd-label-floating">CRN/CAN/UAN<span class="impp"><sup>*</sup></span></label> <input
										type="text" class="form-control form-control-sm" name="CRNAdd"
										id="CRNAdd">
								</div>
							</div>
							
							
							
							
							<div class="col-md-4">
							<div class="group form-group has-feedback has-success bmd-form-group is-filled">
							<label class="bmd-label-floating"></label> 
							<br/>
							<button class="btn btn-primary"
									 value="Add!" id="addMeter"
									type="button">Add Meter</button>
									<input type="hidden" id="rowCountArray" name="rowCountArray">
									<input type="hidden" id="rowCount" name="rowCount">
									</div>
							</div>
							
							
						</div>
						</div>
							
							<div class="row mt-2">
							
							<div class="col-md-12 text-right">
									<button class="btn btn-primary submit-button"
									 value="Save!" id="customerAdd" 
									type="button" disabled>Save</button>
									<button type="button" class="btn btn-danger btn-raised resetFilter" id="resetFilter">Reset</button>
							
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

	<%} %>
   
	
	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>


	<script src="js/dropdown.js"></script>
	<script src="js/customer.js"></script>
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
			$('#customerTable').DataTable();
		});
	</script>

</body>

</html>