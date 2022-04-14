<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--   
====> EL / JSTL 적용
<%@ page import="com.model2.mvc.service.domain.Purchase" %>

<%
	Purchase purchase = (Purchase) request.getAttribute("purchase");
%>	
========
--%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>구매 내역 확인</title>
</head>
<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=${purchase.tranNo}" method="post">

거래요청이 시작되었습니다.
상대방이 메시지를 확인하면 거래가 시작됩니다:D

<table border=1>
	<tr>
		<td>주문번호</td>
		<td>${purchase.purchaseProd.prodNo}</td>
		<td></td>
	</tr>
	<tr>
		<td>거래요청 기프티콘</td>
		<td>${purchase.purchaseProd.prodName}</td>
		<td></td>
	</tr>
	<tr>
		<td>판매자 아이디</td>
		<td><a href="/getUser.do?userId=${purchase.purchaseProd.sellerId}">${purchase.purchaseProd.sellerId}</a></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		
			${purchase.paymentOption}
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td>${purchase.receiverName}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td>${purchase.receiverPhone}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td>${purchase.divyAddr}</td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td>${purchase.divyMessage}</td>
		<td></td>
	</tr>
</table>
</form>


</body>
</html>