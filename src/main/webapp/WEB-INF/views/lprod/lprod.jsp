<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.ZoneOffset"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>사용자 상세조회</title>

<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp"%>

</head>

<body>


	<!-- header -->
	<%@include file="/WEB-INF/views/common/header.jsp"%>

	<div class="container-fluid">

		<div class="row">

			<!-- left -->
			<%@include file="/WEB-INF/views/common/left.jsp"%>


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">lprod상세</h2>

						<form class="form-horizontal" role="form">
							
							<c:set var="vo" value="${SELECT_LPROD_INFO }" scope="request"/>
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">lprod_id</label>
								<div class="col-sm-10">
									<label class="control-label">${vo.lprod_id }</label>
									<input type="hidden" id="userId"
										name="userId" placeholder="사용자 아이디">
								</div>
							</div>

							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">lprod_gu</label>
								<div class="col-sm-10">
									<label class="control-label">${vo.lprod_gu }</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">lprod_nm</label>
								<div class="col-sm-10">
									<label class="control-label">${vo.lprod_nm }</label>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-default">lprod 수정</button>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
