<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
name  : <input type="text" readonly value="${userVO.name }"/>
alias : <input type="text" readonly value="${userVO.alias }"/>
birthStr : <input type="text" readonly value="${userVO.birthStr }"/>
birth : <input type="text" readonly value="<fmt:formatDate value='${userVO.birth }' pattern='yyyy-MM-dd' />"/>
