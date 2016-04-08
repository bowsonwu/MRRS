<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh_Hant">
<head>
<meta charset="utf-8" />
<title>MeetingRoomReservationSystem: Welcome!</title>
<script
	src="${pageContext.request.contextPath}/resources/jquery/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<link
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/resources/mrrs/css/common.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container-fluid">
		<div class="col-xs-12">
			<%-- 上方區 --%>
			<div class="row">
				<%-- Banner --%>
				<%@ include file="banner.jsp"%>
				<%-- Navigation Bar --%>
				<%@ include file="navbar.jsp"%>
				<%-- Breadcrumb Bar --%>
				<div id="breadcrumb-bar"></div>
			</div>
			<%-- 中央區 --%>
			<div id="center-area" class="row">
				<%-- Content Box --%>
				<div id="content-box-grid" class="col-md-12">
					<div class="row">
						<div id="content-box-container">
							<div class="row">
								<div class="col-md-offset-1 col-md-6">
									<h3>最新公告</h3>
									<table class="table table-hover">
										<tr>
											<th>標題</th>
											<th>公告時間</th>
											<th>修改時間</th>
											<th>公告人員</th>
										</tr>
										<c:forEach items="${announcements}" var="announcement">
											<tr>
												<td>${announcement.getTitle()}</td>
												<td>${announcement.getPostTimeString()}</td>
												<td>${announcement.getModifiedTimeString()}</td>
												<td>${announcement.getPostUser().getName()}</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%-- Right Box --%>
				<div id="right-box-grid" class="dttGray-background">
					<div id="right-box-container"></div>
				</div>
			</div>
			<%-- 下方區 --%>
			<div class="row">
				<%-- Modal Box --%>
				<div id="modal-box"></div>
				<%-- Bottom Box --%>
				<%@ include file="bottombox.jsp"%>
			</div>
		</div>
	</div>
	<%-- POP ALERT -- TEMPORARY --%>
	<div id="pop-alert"
		style="position: fixed; bottom: -200px; left: 30px; width: 360px; height: 100px; box-shadow: 1px 1px 6px rgba(0, 0, 0, .3); background-color: #FAFAFA; border-radius: 4px; overflow: hidden; z-index: 5000">
		<div id="pop-alert-title" class="clearfix"
			style="padding: 6px; font-size: 1.4em; font-weight: bold; background: #00A1DE; color: #FFF">
			<span id="pop-alert-title-glyphicon" aria-hidden="true"></span> <span
				id="pop-alert-title-text"></span> <span
				id="pop-alert-title-glyphicon"
				class="glyphicon glyphicon-remove pull-right" aria-hidden="true"
				style="cursor: pointer" onClick="PopAlert.hide()"></span>
		</div>
		<div id="pop-alert-message" style="padding: 8px; font-size: 1.1em"></div>
	</div>
</body>
</html>