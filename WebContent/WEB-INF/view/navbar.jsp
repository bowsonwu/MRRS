<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<nav class="navbar navbar-default" style="font-size: 1.1em">
  <div class="container-fluid">
    <div class="collapse navbar-collapse">
      <%-- 新預約  --%>
      <ul class="nav navbar-nav">
        <li><a href="${pageContext.request.contextPath}/Reservation">新預約</a></li>
      </ul>
      
      <%-- 預約查詢  --%>
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">預約查詢<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/Report/${authTokenAuthentication.getPrincipal()}">個人預約</a></li>
          </ul>
        </li>
      </ul>
      
      <%-- 管理 --%>
      <c:set var="roles" value="${roles}" />
      <c:if test="${fn:contains(roles, 'ROLE_ADMIN')}">
      <ul class="nav navbar-nav" id="adminNavBar" >
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">管理<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/approve">簽核</a></li>
            <li><a href="${pageContext.request.contextPath}/meetingRoom">會議室管理</a></li>
          </ul>
        </li>
      </ul>
      </c:if>
      
      <%--以下靠右 --%>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span><span id="navbar-name">${authTokenAuthentication.getName()}</span><span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/logout">登出</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>