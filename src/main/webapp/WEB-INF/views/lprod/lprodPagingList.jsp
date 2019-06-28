<%@page import="kr.or.ddit.lprod.model.LprodVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.Map"%>
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

<title>Lprod리스트</title>

<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp" %>

<style>
	.lprodTr:hover{
		cursor: pointer;
	}
</style>
<script>
	$(document).ready(function(){
		
		$(".lprodTr").on("click",function(){
			var lprodId = $(this).data("lprodid");
			
			$("#lprodId").val(lprodId);
			
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
						<h2 class="sub-header">Lprod</h2>
						
						<form id="frm" action="${cp }/lprod/lprod"
							  method="get" >
							<input type="hidden" id="lprodId" name="lprodId"/>
						</form>
						
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>Lprod_ID</th>
									<th>Lprod_gu</th>
									<th>상품분류명</th>
								</tr>
							
								<c:forEach items="${lprodPagingList }" var="lprodVO">
									<tr class="lprodTr" data-lprodid="${lprodVO.lprod_id }">
										<td class="lprodId">${lprodVO.lprod_id }</td>										
										<td>${lprodVO.lprod_gu }</td>										
										<td>${lprodVO.lprod_nm }</td>										
									</tr>
								</c:forEach>

							</table>
						</div>

						<a class="btn btn-default pull-right">상품분류등록</a>

						<div class="text-center">
							<ul class="pagination" >
								 
							   <c:set var="pageVO" value="${pageVO }" />
							   <c:choose>
							    <c:when test="${pageVO.page == 1 }">
							  	 	<li class="previous disabled">
										<span>«</span>
									</li>
							  	</c:when>
							  	<c:otherwise>
							  		<li class="previous">
										<a href="${cp}/lprod/pagingList?page=${pageVO.page-1 }&pageSize=${pageVO.pageSize}">«</a>
									</li>
							  	</c:otherwise>
							   </c:choose>
								  
								<c:set var="lprodsCnt" value="${lprodsCnt }"/>
								<c:set var="pageLenth" value="${paginationSize }"/>
								<c:forEach var="i" begin="1" end="${pageLenth }" step="1">
									<c:choose>
										<c:when test="${pageVO.page eq i }">
											<li class = "active">
												<span>${i }</span>
											</li>
										</c:when>
										<c:otherwise>
											<li><a href="${cp}/lprod/pagingList?page=${i }&pageSize=${pageVO.pageSize}">${i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								 <c:choose>
								 	<c:when test="${pageVO.page eq pageLength} ">
								 		<li class="next disabled">
											<span>»</span>
										</li>	
								 	</c:when>
								 	<c:otherwise>
								 		<li class="next">
								 			<a href="${cp}/lprod/pagingList?page=${pageVO.page + 1 }&pageSize=${pageVO.pageSize }">»</a>
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
