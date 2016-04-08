<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh_Hant">
<head>
    <meta charset="utf-8" />
    <title>MeetingRoomReservationSystem: Welcome!</title>
    <script src="${pageContext.request.contextPath}/resources/jquery/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container-fluid">
	<div class="row" style="padding-top:40px">
		<div class="col-md-4 col-sm-2 col-xs-2"></div>
		<div class="col-md-4 col-sm-8 col-xs-8">
		   <div class="row" style="text-align:center">
		       <div class="col-md-2"></div>
		       <div class="col-md-8">
		       </div>
		       <div class="col-md-2"></div>
		   </div>
		   <div class="row" style="padding-top:20px">
			AccessDenied!!!
			<a href="${pageContext.request.contextPath}/" >go home</a>		      
		   </div>
		</div>
		<div class="col-md-4 col-sm-2 col-xs-2"></div>
	</div>
</div>
</body>
</html>