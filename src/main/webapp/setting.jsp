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

<title>Setting</title>
</head>


<body class="main-sidebar-show">
	<%
		String user_id = (String) session.getAttribute("roleID");
	%>

	<%
		if (null == user_id) {
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
									class="activeurl">My Setting</span>
							</div>
						</div>

						<div class="row mr-0 ml-0">
							<div class="right_data col-md-12 mt-4 mb-4">
								<!--Right start-->
								<div class="row mb-4">
									<div class="col-md-10 m-auto">
										<div class="card">
											<div class="card-header bg-primary cardHeading text-white">My
												Setting</div>
											<div class="card-body scroll right-block">
												<div class="row">
													<div class="col-md-4">&nbsp;</div>
													<div class="col-md-4">Background Color</div>
													<div class="col-md-4">Text Color</div>
												</div>
												<hr>
												<div class="row">
													<div class="col-md-4">
														<label for="favcolor">Select Background color:</label>
													</div>
													<div class="col-md-4">
														<input type="color" id="favcolor" name="favcolor"
															value="#ff0000">
													</div>
													<div class="col-md-4">
														<input type="color" id="favcolor" name="favcolor"
															value="#ff0000">
													</div>
												</div>
												<hr>
												<div class="row">
													<div class="col-md-4">
														<label for="favcolor">Select Header color:</label>
													</div>
													<div class="col-md-4">
														<input type="color" id="favcolor" name="favcolor"
															value="#ff0000">
													</div>
													<div class="col-md-4">
														<input type="color" id="favcolor" name="favcolor"
															value="#ff0000">
													</div>
												</div>
												<hr>
												<div class="row">
													<div class="col-md-4">
														<label for="favcolor">Select Menu:</label>
													</div>
													<div class="col-md-4">
														<input type="color" id="favcolor" name="favcolor"
															value="#ff0000">
													</div>
													<div class="col-md-4">
														<input type="color" id="favcolor" name="favcolor"
															value="#ff0000">
													</div>
												</div>

												<hr>
												<div class="row">
													<div class="col-md-4">
														<label for="favcolor">Select Footer:</label>
													</div>
													<div class="col-md-4">
														<input type="color" id="favcolor" name="favcolor"
															value="#ff0000">
													</div>
													<div class="col-md-4">
														<input type="color" id="favcolor" name="favcolor"
															value="#ff0000">
													</div>
												</div>



												<hr>
												<div class="row">
													<div class="col-md-4">
														<label for="favcolor">Select Panel/Table:</label>
													</div>
													<div class="col-md-4">
														<input type="color" id="favcolor" name="favcolor"
															value="#ff0000">
													</div>
													<div class="col-md-4">
														<input type="color" id="favcolor" name="favcolor"
															value="#ff0000">
													</div>
												</div>
												
												<hr>
												<div class="row">
													<div class="col-md-4">
														<label for="favcolor">Select font family:</label>
													</div>
													<div class="col-md-4">
														<select class="form-control select2" id="fontsList"></select>
													</div>
												</div>
												
											</div>
											<div class="col-md-12 text-right mb-4">
													<button class="btn btn-primary submit-button"
														value="Save!" id="saveVariant" type="button">save Variant</button>
												</div>
										</div>
										
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>

				<!--Right end-->
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />

	<%
		}
	%>

	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>


	<script src="js/setting.js"></script>
	<script src="js/common.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>

	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>



	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

	<script>
	(function ( $ ) {	
		$.fn.webfonts = function(font) {
			var font_set = "";
			font_set = font;

			var fonts = ["Montserrat","Nunito","Old Standard TT","Open Sans","Oswald","Oxygen","Poppins","Roboto","Source Sans Pro"];
			var font_preview = 0;
			var font_list = '<option value="">Please select...</option>';

			for (var i=0; i < fonts.length; i++){
			   	font_list += '<option value="'+ fonts[i] + '">' + fonts[i] + '</option>';
			}

			this.addClass('webfonts').append(font_list);

		    $(this).find('option').each(function(){              
	            if($(this).val()==font_set ){
	                $(this).attr('selected','selected');
	            }
	        });
		};		  
  
     $('#fontsList').webfonts();
	}( jQuery ));
	</script>
</body>
</html>