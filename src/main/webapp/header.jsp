<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,500,600" rel="stylesheet">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>

<%
		String user_id = (String) session.getAttribute("roleID");

	%>

	<%
		if (null == user_id) {
			response.sendRedirect("login.jsp");
		}
	%>
<!--Header Start-->
 <!-- bg-info -->
	 <div class="main-header">
			<div class="container-fluid headerColor">
			  <div class="main-header-left"> 
				<a class="main-header-menu-icon" href="#" id="mainSidebarToggle"><span></span></a> 
			  </div>
			  <div class="main-header-right">
				<div class="btn-group">
				<span class="main-img-user" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<i class="fa fa-user-circle-o" aria-hidden="true"></i>
				 </span>
				  <div class="dropdown-menu main-profile-menu  dropdown-menu-right">
				    <div class="header-navheading"> 
						<h6 class="main-notification-title" id="navbardrop"></h6> 
						<p class="main-notification-text">idigitronics</p>
					</div>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="myprofile.jsp"> <i class="fa fa-user"></i> My Profile </a>
					
					<a class="dropdown-item" href="setting.jsp"> <i class="fa fa-user"></i> Setting</a>
					
					
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="logout.jsp"> <i class="fa fa-sign-out"></i> Sign Out </a>
					</div>
				</div>
			  </div>
			</div>
	   </div>
	<!--Header end-->
	<script>

document.querySelector("#navbardrop").innerText = "  "+sessionStorage.getItem("userID");
</script>
</body>


	
</html>