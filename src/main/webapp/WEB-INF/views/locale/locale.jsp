<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
	$(document).ready(function(){

		// 페이지 요청시 해당 언어를 받아 select box option값 셋팅
		// 단, 최초 요청시 default값은 ko
// 		$("#lang").val("${param.lang eq null? 'ko' : param.lang }");
		$("#lang").val("${lang}");
		
		$("#lang").on("change", function(){
			$("#frm").submit();
		})
	})
</script>

<form id="frm" action="/locale/view" method="post">
	<select id="lang" name="lang" >
		<option value="ko">한국어</option>
		<option value="en">english</option>
		<option value="ja">日本語</option>
	</select>
</form>

GREETING : <spring:message code="GREETING"/><br>
VISITOR : <spring:message code="VISITOR"> 
			<spring:argument value="${USER_INFO eq null? '접속X' : USER_INFO.name}"/>
		 </spring:message>