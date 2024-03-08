<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="common/css/style1.css">
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.css">
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
<!-- 	<div class="container-fluid">
    <div class="row d-flex d-md-block flex-nowrap wrapper">
        <div class="col-md-2 float-left col-1 pl-0 pr-0 collapse width show" id="sidebar">
            <div class="list-group border-0 text-center text-md-left"> -->
            <div class="main-sidebar main-sidebar-sticky">
		<div class="sidemenu-logo">
			<a class="main-logo" href="home.jsp"> 
				<img src="common/images/logo-white.png" class="header-brand-img desktop-logo img-fluid" alt="logo"> 
				<img src="common/images/logoIcon.png" class="header-brand-img main-sidebar-Hidelogo img-fluid" alt="logo"> 
			</a> 
		</div>
	
		<div class="main-sidebar-body body_content" id="sidebar">
		   
			<div class="list-group border-0 text-center text-md-left">
             <%
			if (user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("4")) {
				%>	
            
                <!-- <a href="LiveDashBoard.jsp" class="list-group-item d-inline-block collapsed"><i class="fa fa-tachometer"></i> 
 <span class="textData">DashBoard</span></a> -->
             	 <a href="setup.jsp" class="list-group-item" data-parent="#menu3">
             	 <i class="fa fa-cogs"></i>
             	 <span class="textData">Customer Setup </span></a>
                   
              <!--   <div class="collapse" id="menu3" data-parent="#sidebar">
                    <a href="communityDetails.jsp" class="list-group-item" data-parent="#menu3"><i class="fa fa-film"></i><span class="textData">Community Details </span></a>
                     <a href="blockDetails.jsp" class="list-group-item" data-parent="#menu3"><i class="fa fa-film"></i><span class="textData">Block Details </span></a>
                      <a href="customerDetails.jsp" class="list-group-item" data-parent="#menu3"><i class="fa fa-film"></i><span class="textData">Customer Details </span></a>
                       <a href="gateway.jsp" class="list-group-item" data-parent="#menu3"><i class="fa fa-film"></i><span class="textData">Gateway Details </span></a>
                </div> -->
                <a href="tariff.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><img src=common/images/icons/tariff.png /><span class="textData">Tariff </span></a>
                <a href="alert.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><img src=common/images/icons/alerts.png /> <span class="textData">Alert Configuration</span></a>
                
                <%if(user_id.equalsIgnoreCase("1")){ %>
                 <a href="configuration.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><img src=common/images/icons/GAS1.png /> <span class="textData">Meter Commands</span></a>
               
                <a href="Sensor.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><img src=common/images/icons/tariff.png /><span class="textData">Sensor</span></a> 
				
               <!--   <a href="#" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-clock-o"></i> <span class="textData">Meter Commands</span></a>
                
 -->                 <%}%>
                
                <!-- <a href="configurationStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-th"></i> <span class="textData">Meter Commands Status</span></a>
                 -->
                 <a href="configurationStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-list"></i> <span class="textData">Meter Commands Status</span></a>
                 
                <%if(user_id.equalsIgnoreCase("1")){ %>
                <a href="topup.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-credit-card"></i> <span class="textData">ReCharge</span></a>
                <%}%>
                <a href="topupStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-list"></i> <span class="textData">ReCharge Status</span></a>
                <%if(user_id.equalsIgnoreCase("1")){ %>
                <a href="bill.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-credit-card"></i> <span class="textData">Bill Payment</span></a>
                <%}%>
                <a href="billingDetails.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-credit-card"></i> <span class="textData">Billing Status</span></a>
                
                
                <!-- <a href="#" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-envelope"></i> <span class="textData">Vacation</span></a>
                --> <a href="reports.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-bar-chart-o"></i> <span class="textData">Reports</span></a>
                
                	<%if(user_id.equalsIgnoreCase("1")){ %>
                <!-- <a href="Mgmt.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-star"></i> <span class="textData">User Management</span></a> -->
                 <a href="Mgmt.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-star"></i> <span class="textData">User Management</span></a>
                 
                 <a href="manualBilling.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-star"></i> <span class="textData">Manual Billing</span></a>
                 
                  <a href="manualdata.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-star"></i> <span class="textData">Manual Data</span></a>
                  
                   <a href="Sensor.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-star"></i> <span class="textData">Sensor Dashboard</span></a>
                  
            <%}%>
            <% } else if (user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("5")) {
                %>
             <a href="approval.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-calendar"></i> <span class="textData">Approval</span></a>
		 <a href="setup.jsp" class="list-group-item" data-parent="#menu3">
             	 <i class="fa fa-cogs"></i>
             	 <span class="textData">Customer Setup </span></a>
                <!--  <a href="tariff.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-heart"></i> <span class="textData">Tariff </span></a>
                --> <a href="alert.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-list"></i> <span class="textData">Alert Configuration</span></a>
                
                
                   <%if(user_id.equalsIgnoreCase("2")){ %>
                <a href="configuration.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-clock-o"></i> <span class="textData">Meter Commands</span></a>
                <%}%>
                
                 <a href="configurationStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-th"></i> <span class="textData">Meter Commands Status</span></a>
                
                <%if(user_id.equalsIgnoreCase("2")){ %>
                <a href="topup.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-gear"></i> <span class="textData">ReCharge</span></a>
                 <a href="bill.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-credit-card"></i> <span class="textData">Bill Payment</span></a>
                <%}%>
                
                 <a href="topupStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-calendar"></i> <span class="textData">ReCharge Status</span></a>
                  <a href="billingDetails.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-credit-card"></i> <span class="textData">Billing Status</span></a>
                
                <a href="reports.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-bar-chart-o"></i> <span class="textData">Reports</span></a>
                
                		<%if(user_id.equalsIgnoreCase("2")){ %>
                 <a href="feedbackStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-envelope"></i> <span class="textData">Feedback Status</span></a>
                 
                  <a href="manualBilling.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-star"></i> <span class="textData">Manual Billing</span></a>
                 
                 
                 
               <%}%>
                <% } else if (user_id.equalsIgnoreCase("3")) {%>
                
                <a href="topup.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-gear"></i> <span class="textData">ReCharge</span></a>
                 <a href="topupStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-calendar"></i> <span class="textData">ReCharge Status</span></a>
                <a href="bill.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-credit-card"></i> <span class="textData">Bill Payment</span></a>
                  <a href="billingDetails.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-credit-card"></i> <span class="textData">Billing Status</span></a>
                
               
                 <a href="userReport.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-envelope"></i> <span class="textData">User Consumptions</span></a>
                <a href="feedback.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-bar-chart-o"></i> <span class="textData">Feedback/Compliant</span></a>
              <% } %>
            </div>
        </div>
    </div>
	<%
		}
	%>
	
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.concat.min.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.1/jstree.min.js"></script>
  </body>
</html>