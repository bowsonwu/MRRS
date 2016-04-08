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
		      <c:if test="${message == 'AUTHENTICATION_FAILED'}">
		      <div class="alert alert-danger" role="alert" style="text-align:center">請輸入正確的帳號及密碼</div>
		      </c:if>
		      <c:if test="${message == 'LOGGED_OUT'}">
              <div class="alert alert-success" role="alert" style="text-align:center">您已經成功登出</div>
              </c:if>
		      
		      <form action="${pageContext.request.contextPath}/" method="POST">
		      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	               <div class="panel panel-default" style="text-align:center">
	                   <div class="panel-heading">
	                       <div class="panel-title">MRRS</div>
                       </div>
		               <div class="panel-body" style="padding-left:10%; padding-right:10%">
						  <div class="form-group">
						    <input type="text" class="form-control" id="username" name="username" placeholder="帳號" style="text-align: center">
						  </div>
						  <div class="form-group">
						    <input type="password" class="form-control" id="password" name="password" placeholder="密碼" style="text-align: center">
						  </div>
		               </div>
		               <div class="panel-footer" style="text-align:center">
		                  <button type="submit" class="btn btn-default">登入</button>
		               </div>
	               </div>
	           </form>
		   </div>
		</div>
		<div class="col-md-4 col-sm-2 col-xs-2"></div>
	</div>
</div>
</body>
</html>