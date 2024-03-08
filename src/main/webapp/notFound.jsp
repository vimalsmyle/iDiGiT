<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body{
    padding: 0px;
    margin: 0px;
}
#notfound {
    position: relative;
    height: 100vh;
}
.notfound {
    max-width: 767px;
    width: 100%;
    line-height: 1.4;
    padding: 0 15px;
}
#notfound .notfound {
    position: absolute;
    left: 50%;
    top: 50%;
    -webkit-transform: translate(-50%,-50%);
    -ms-transform: translate(-50%,-50%);
    transform: translate(-50%,-50%);
}
.notfound .notfound-404 {
    position: relative;
    height: 150px;
    line-height: 150px;
    margin-bottom: 25px;
}
.notfound .notfound-404 h1 {
    font-family: titillium web,sans-serif;
    font-size: 186px;
    font-weight: 900;
    margin: 0;
    color:#b2d136;
    text-transform: uppercase;
}
.notfound h2 {
    font-family: titillium web,sans-serif;
    font-size: 26px;
    font-weight: 700;
    margin: 0;
}
.notfound p {
    font-family: montserrat,sans-serif;
    font-size: 14px;
    font-weight: 500;
    margin-bottom: 0;
    text-transform: uppercase;
}
.notfound a {
    font-family: titillium web,sans-serif;
    display: inline-block;
    text-transform: uppercase;
    color: #fff;
    text-decoration: none;
    border: none;
    background: #5c91fe;
    padding: 10px 40px;
    font-size: 14px;
    font-weight: 700;
    border-radius: 1px;
    margin-top: 15px;
    -webkit-transition: .2s all;
    transition: .2s all;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Not Found</title>
</head>
<body>
<div id="notfound">
<div class="notfound">
<div class="notfound-404">
<h1>404</h1>
</div>
<h2>Oops! This Page Could Not Be Found</h2>
<p>Sorry but the page you are looking for does not exist, have been removed. name changed or is temporarily unavailable</p>
<a href="#">Go To Homepage</a>
</div>
</div>
</body>


</html>