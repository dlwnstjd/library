<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>도서 목록 보기</title>
<link rel="stylesheet" href="../../css/main.css">
<script type="text/javascript">
	function listdo(page){
		f = document.sf;
		f.pageNum.value=page;
		f.submit();
	}
</script>
</head>
<body>
<form name="sf" style="text-align:center" method="post">
<input type="hidden" name="pageNum" value="1">
</form>
<table>
<caption>도서 목록</caption>
<c:choose>
	<c:when test="${boardcount==0}">
		<tr><td colspan="5">등록된 도서가 없습니다.</td></tr>
	</c:when>
	<c:otherwise>
	<tr><td colspan="5" style="text-align:right">도서 개수:${boardcount }</td></tr>
	<tr><th>번호</th><th>제목</th>
		<th>저자</th><th>출판사</th>
		<th>출판년도</th><th>가격</th>
		<th>책소개</th><th>입고시간</th>
		<th>대여여부</th></tr>
	<c:forEach var="b" items="${list}">
	<tr><td>${boardnum }</td>
		<c:set var="boardnum" value="${boardnum-1 }"/>
		<td style="text-align:left">
		<a href="info.do?no=${b.no}">${b.name}</a></td>
		<td>${b.author}</td>
		<td>${b.publisher}</td>
		<td>${b.publicationyear}</td>
		<td>${b.price}</td>	
		<td>${b.introduction}</td>	
		<td>
		<fmt:formatDate var="rdate" value="${b.whDate }" pattern="yyyy-MM-dd"/>
		<c:if test="${todat == rdate }">
			<fmt:formatDate value="${b.whDate }" pattern="HH:mm:ss"/>
		</c:if>
		<c:if test="${todat != rdate }">
			<fmt:formatDate value="${b.whDate }" pattern="yyyy-MM-dd HH:mm"/>
		</c:if></td><td>
		${b.status==1?"대여가능":"대여중"}
		</td></tr>
</c:forEach><%--도서 조회 끝--%>
<tr><td colspan="5">
	<%-- 페이지 처리하기 --%>
	<c:choose>
		<c:when test="${pageNum <= 1}">[이전]</c:when>
		<c:otherwise>
			<a href="javascript:listdo(${pageNum -1})">[이전]</a>
		</c:otherwise>
	</c:choose>
	<c:forEach var="a" begin="${startpage}" end="${endpage}">
		<c:choose>
			<c:when test="${a==pageNum }">[${a}]
			</c:when>
			<c:otherwise>
				<a href="javascript:listdo(${a})">[${a}]</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pageNum >= maxpage}">[다음]
		</c:when>
		<c:otherwise>
			<a href="javascript:listdo(${pageNum + 1})">[다음]</a>
		</c:otherwise>
	</c:choose>
</td></tr>
</c:otherwise>
</c:choose>
	<tr><td colspan="5" style="text-align:right">
		<a href="warehousing.do">[도서입고]</a></td></tr>
</table>
</body>
</html>