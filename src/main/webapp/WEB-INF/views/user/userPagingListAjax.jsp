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

<%@include file="/WEB-INF/views/common/basicLib.jsp" %>
<style>
	.userTr:hover{
		cursor: pointer;
	}
</style>

<script>
	$(document).ready(function(){
		// 사용자 tr 태그 이벤트 등록
		$("#userListTbody").on("click", ".userTr",function(){
			// 클릭한 행에 사용자 id를 가져오는 방법
			console.log( $(this).find(".userId").text() );

			// 사용자id를 #userId 값으로 설정해주고
			var userId = $(this).find(".userId").text();
			$("#userId").val(userId);

			//#frm을 이용하여 submit()
			$("#frm").submit();

		});	
	
		
		// 첫번째 페이지의 사용자 정보를 요청
// 		userPagingListAjax(1,10);
		userPagingListAjaxHtml(1,10);
		
		
	});
	
	// 데이터 응답을 json으로 받음
	function userPagingListAjax(page, pageSize){
		
		html = "";
		pagination = "";
		
		$.ajax({
			url : "/user/pagingListAjax",
			method : "post",
			data : "page="+ page + "&pageSize="+ pageSize,
			success :  function(data){
				console.log(data);
				console.log(data.data.userList);
				console.log(data.data.paginationSize);
				data.data.userList.forEach(function(user){
					html += "<tr class='userTr'>";
					html += "	<td class='userId'>"+user.userId+"</td>";
					html += "	<td>"+user.name+"</td>";
					html += "	<td>"+user.alias+"</td>";
					html += "	<td>"+user.birthStr+"</td>";
					html += "</tr>";
				})
				
				
				// 페이지네이션 생성
				var pHtml = "";
				console.log(data.pageVO);
				var pageVO = data.pageVO;
				var paginationSize = data.data.paginationSize; 
				
				if(pageVO.page == 1)
					pHtml += "<li class='previous disabled'><span>«</span></li>";
				else{
					pHtml += "<li class='previous'>";
					pHtml += "<a href='javascript:userPagingListAjax("+ (pageVO.page-1) +","+ pageVO.pageSize +")'>«</a></li>";
				}				
				
				for(var i=1; i <= data.data.paginationSize; i++){
					if( i == pageVO.page)
						pHtml += "<li class='active'><span>" + i + "</span></li>";
					else{
						pHtml += "<li><a href='javascript:userPagingListAjax("+ i +","+ pageVO.pageSize +")'>" + i + "</a></li>";
						
					}
				}
				
				if(pageVO.page == paginationSize )
					pHtml += "<li class='next disabled'><span>»</span></li>";
				else{
					pHtml += "<li class='next'><a href='javascript:userPagingListAjax("+ (pageVO.page + 1) +","+ pageVO.pageSize+")'>»</a></li>";			
				}
				/*
				
				 <c:choose>
				 	<c:when test="${pageVO.page == pageLength }">
				 		<li class="next disabled">
							<span>»</span>
						</li>	
				 	</c:when>
				 	<c:otherwise>
				 		<li class="next">													
				 			<a href="${cp}/user/pagingList?page=${pageVO.page + 1 }&pageSize=${pageVO.pageSize}">»</a>
				 		</li>
				 	</c:otherwise>
				 </c:choose>	
				
				*/
				$("#userListTbody").html(html);
				$(".pagination").html(pHtml);				
			},
			error: function(status){
				console.log(status);
			}
			
		})
		
	};
	
	// 데이터 응답을 html로 받음
	function userPagingListAjaxHtml(page, pageSize){
		$.ajax({
			url : "/user/pagingListHtml",
			method : "post",
			data : "page="+ page + "&pageSize="+ pageSize,
			success :  function(data){
// 				console.log(data);
				var html = data.split("SEPERATORSEPERATOR");
				console.log(html);
				$("#userListTbody").html(html[0]);
				$(".pagination").html(html[1]);
			},
			error: function(status){
				console.log(status);
			}
		})
	}
	
</script>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자(tiles)</h2>
		
		<!-- 사용자 상세조회 : userId가 필요 -->
		<form id="frm" action="${cp }/user/user"
			  method="get">
			  <input type="hidden" id="userId" name="userId"/>
		</form>
		
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
				<tr>
					<th>사용자 아이디</th>
					<th>사용자 이름</th>
					<th>사용자 별명</th>
					<th>생년월일</th>
				</tr>	
				</thead>
				
				<tbody id="userListTbody">
				</tbody>
					
			</table>
		</div>

		<a href="${cp }/user/userListExcel?filename=userList" class="btn btn-default pull-right">엑셀다운</a>
		<a href="${cp }/user/form" class="btn btn-default pull-right">사용자 등록</a>
		<br><br>
		
		<div class="text-center">
			<ul class="pagination" >
				
				
			</ul>
		</div>
	</div>
</div>

</html>
