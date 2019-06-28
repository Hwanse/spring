<%@page import="kr.or.ddit.lprod.model.LprodVO"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.ZoneOffset"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>사용자리스트</title>

<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp" %>

</head>

<body>


	<!-- header -->
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	
	<div class="container-fluid">

		<div class="row">

			<!-- left -->
			<%@include file="/WEB-INF/views/common/left.jsp" %>
			
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


			<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>Lprod_ID</th>
									<th>Lprod_gu</th>
									<th>상품분류명</th>
								</tr>
								
								<c:forEach items="${allLprodList }" var="lprodVO">
									<tr>
											<td>${lprodVO.lprod_id }</td>										
											<td>${lprodVO.lprod_gu }</td>										
											<td>${lprodVO.lprod_nm }</td>										
										</tr>
								</c:forEach>
							</table>
						</div>

						<a class="btn btn-default pull-right">사용자 등록</a>

						<div class="text-center">
							<ul class="pagination">
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
