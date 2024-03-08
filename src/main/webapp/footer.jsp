<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- <img class="img-fluid logo_totalpage" src="common/images/hanbit1.png" alt="logo"> -->
<%
		String user_id = (String) session.getAttribute("roleID");

	%>

	<%
		if (null == user_id) {
			response.sendRedirect("login.jsp");
		}
	%>
<!--Footer Start-->
	<footer class="navbar footer fixed-bottom footer-light footer-shadow content container-fluid">
        <p>&copy; 2020 iDigitronics</p>
    </footer>
	<!--Footer end-->
</body>
</html>