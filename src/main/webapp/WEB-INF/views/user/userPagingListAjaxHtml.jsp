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


<c:forEach items="${data.userList }" var="user">
	<tr class="userTr" id="${user.userId }" data-userid="${user.userId }" data-name="${user.name }">
		<td class="userId">${user.userId }</td>										
		<td>${user.name }</td>										
		<td>${user.alias }</td>										
		<td><fmt:formatDate value="${user.birth }" pattern="yyyy.MM.dd"/></td>
	</tr>
</c:forEach>

SEPERATORSEPERATOR

<c:choose>
	<c:when test="${pageVO.page == 1 }">
		<li class="previous disabled">
			<span>«</span>
		</li>
	</c:when>
	<c:otherwise>
		<li class="previous">											
			<a href="javascript:userPagingListAjaxHtml(${pageVO.page-1 },${pageVO.pageSize});">«</a>
		</li>
	</c:otherwise>
</c:choose>								 


<c:set var="pageLength" value="${data.paginationSize }"/>
<c:forEach var="i" begin="1" step="1" end="${pageLength }">
	<c:choose>
		<c:when test="${pageVO.page == i }">
			<li class = "active">
					<span>${i }</span>
			</li>
		</c:when>
		<c:otherwise>															
			<li><a href="javascript:userPagingListAjaxHtml(${i },${pageVO.pageSize});">${i }</a></li>
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
			<a href="javascript:userPagingListAjaxHtml(${pageVO.page+1 },${pageVO.pageSize});">»</a>
		</li>
	</c:otherwise>
</c:choose>		

</html>
