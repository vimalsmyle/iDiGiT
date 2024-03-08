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
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">
	
	<link href="common/css/materialize.fontawsome.css"
	rel="stylesheet">

<title>Community Management</title>
</head>


<body class="main-sidebar-show">
<%
		String user_id = (String) session.getAttribute("roleID");
		String cust_id = request.getParameter("cust");
	%>

	<%
		if (user_id == null) {
			response.sendRedirect("login.jsp");
		}else {
	%>
<div id="preloader" style="display:none;">
  <div id="status">&nbsp;</div>
</div>
<input type="hidden" value=<%=cust_id%> id="custUniqueId">
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
						<h5 class="modal-title" id="exampleModalLabel">Customer Edit
						Form</h5>
						
						<form id="customerEdit">
						<div id="template">
						<div class="row">
						
						<div class="col-md-4">
								<div id="formcommunityNameEdit" class="group">
									<label class="bmd-label-floating">Community</label> 
									 <input
										type="text" class="form-control" name="communityNameEdit"
										id="communityNameEdit" disabled>
								</div>
							</div>
							<div class="col-md-4">
								<div id="formblockNameEdit" class="group">
									<label class="bmd-label-floating">Block</label> <input
										type="text" class="form-control" name="blockNameEdit"
										id="blockNameEdit" disabled>
								</div>
							</div>

							<div class="col-md-4">
								<div id="formfirstNameEdit" class="group">
									<label class="bmd-label-floating">First Name</label> <input
										type="text" class="form-control" name="firstNameEdit"
										id="firstNameEdit">
								</div>
							</div>
							<div class="col-md-4">
								<div id="formlastNameEdit" class="form-group">
									<label class="bmd-label-floating">Last Name</label> <input
										type="text" class="form-control" name="lastNameEdit"
										id="lastNameEdit" disabled>
								</div>
							</div>
							
							
							
							<div class="col-md-4">
								<div id="formhouseNoEdit" class="group">
									<label class="bmd-label-floating">House No.</label> <input
										type="text" class="form-control" name="houseNoEdit"
										id="houseNoEdit">
								</div>
							</div>
							<div class="col-md-4">
								<div id="formmobileNoEdit" class="group">
									<label class="bmd-label-floating">Mobile No</label> <input
										type="text" class="form-control" name="mobileNoEdit"
										id="mobileNoEdit" maxlength="10">
								</div>
							</div>
							
							
							
							
							<div class="col-md-4">
								<div id="formemailEdit" class="group">
									<label class="bmd-label-floating">Email</label> <input
										type="email" class="form-control" name="emailEdit"
										id="emailEdit">
								</div>
							</div>
							
							<div class="col-md-4">
								<div id="formCRNEdit" class="group">
									<label class="bmd-label-floating">CRN/CAN/UAN</label> <input
										type="text" class="form-control form-control-sm" name="CRNEdit"
										id="CRNEdit" disabled>
								</div>
							</div>
							
							
							
							
							<div class="col-md-4">
							<div class="group form-group has-feedback has-success bmd-form-group is-filled">
							<label class="bmd-label-floating"></label> 
							<br/>
							<!-- <button class="btn btn-primary"
									 value="Add!" id="addMeter"
									type="button">Add</button> -->
									<input type="hidden" id="rowCount" name="rowCount">
									<input type="hidden" id="rowCountArray" name="rowCountArray">
									</div>
							</div>
							
							
						</div>
						</div>
							
							<div class="row mt-2">
							
							<div class="col-md-12 text-right">
									<button class="btn btn-primary submit-button"
									 value="Save!" id="customerEditsave" 
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