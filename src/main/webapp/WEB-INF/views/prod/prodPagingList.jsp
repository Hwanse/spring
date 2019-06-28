<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.ZoneOffset"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<style>
	.userTr:hover{
		cursor: pointer;
	}
</style>
<script>
	$(document).ready(function(){
		// 사용자 tr 태그 이벤트 등록
		$(".prodTr").on("click",function(){
			// 클릭한 행에 사용자 id를 가져오는 방법

			// 사용자id를 #prodId 값으로 설정해주고
			var prodId = $(this).find(".prodId").text();
			$("#prodId").val(prodId);

			//#frm을 이용하여 submit()
			$("#frm").submit();

		});	
	
	});
</script>
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
						
						<!-- 사용자 상세조회 : userId가 필요 -->
						<form id="frm" action="${cp }/prod/prod"
							  method="get">
							  <input type="hidden" id="prodId" name="prodId"/>
						</form>
						
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>상품 아이디</th>
									<th>상품 이름</th>
									<th>상품 사이즈</th>
									<th>상품 가격</th>
								</tr>
							
									<c:forEach items="${prodList }" var="prod">
										<tr class="prodTr" id="${prod.prod_id }" data-prod_id="${prod.prod_id }" data-name="${prod.prod_name}">
											<td class="userId">${prod.prod_id }</td>										
											<td>${prod.prod_name }</td>										
											<td>${prod.prod_size }</td>										
											<td>${prod.prod_cost }</td>
										</tr>
									</c:forEach>

							</table>
						</div>

						<a href="#" class="btn btn-default pull-right">상품 등록</a>
						<br><br>
						<a href="${cp }/prod/prodListExcel?filename=prodList" class="btn btn-default pull-right">엑셀다운</a>
						
						<div class="text-center">
							<ul class="pagination" >
								
								<c:choose>
									<c:when test="${pageVO.page == 1 }">
										<li class="previous disabled">
											<span>«</span>
										</li>
									</c:when>
									<c:otherwise>
										<li class="previous">											
											<a href="${cp}/prod/pagingList?page=${pageVO.page-1 }&pageSize=${pageVO.pageSize}">«</a>
										</li>
									</c:otherwise>
								</c:choose>								 

<%-- 								<c:set var="usersCnt" value="${usersCnt }"/> --%>
								<c:set var="pageLength" value="${paginationSize }"/>
								<c:forEach var="i" begin="1" step="1" end="${pageLength }">
									<c:choose>
										<c:when test="${pageVO.page == i }">
											<li class = "active">
													<span>${i }</span>
											</li>
										</c:when>
										<c:otherwise>															
											<li><a href="${cp}/prod/pagingList?page=${i }&pageSize=${pageVO.pageSize}">${i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								 <c:choose>
								 	<c:when test="${pageVO.page == pageLength }">
								 		<li class="next disabled">
											<span>»</span>
										</li>	
								 	</c:when>
								 	<c:otherwise>
								 		<li class="next">													
								 			<a href="${cp}/prod/pagingList?page=${pageVO.page + 1 }&pageSize=${pageVO.pageSize}">»</a>
								 		</li>
								 	</c:otherwise>
								 </c:choose>		
								 
							</ul>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
