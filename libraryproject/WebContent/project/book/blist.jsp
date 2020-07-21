<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ��� ����</title>
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
<caption>���� ���</caption>
<c:choose>
	<c:when test="${boardcount==0}">
		<tr><td colspan="5">��ϵ� ������ �����ϴ�.</td></tr>
	</c:when>
	<c:otherwise>
	<tr><td colspan="5" style="text-align:right">���� ����:${boardcount }</td></tr>
	<tr><th>��ȣ</th><th>����</th>
		<th>����</th><th>���ǻ�</th>
		<th>���ǳ⵵</th><th>����</th>
		<th>å�Ұ�</th><th>�԰�ð�</th>
		<th>�뿩����</th></tr>
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
		${b.status==1?"�뿩����":"�뿩��"}
		</td></tr>
</c:forEach><%--���� ��ȸ ��--%>
<tr><td colspan="5">
	<%-- ������ ó���ϱ� --%>
	<c:choose>
		<c:when test="${pageNum <= 1}">[����]</c:when>
		<c:otherwise>
			<a href="javascript:listdo(${pageNum -1})">[����]</a>
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
		<c:when test="${pageNum >= maxpage}">[����]
		</c:when>
		<c:otherwise>
			<a href="javascript:listdo(${pageNum + 1})">[����]</a>
		</c:otherwise>
	</c:choose>
</td></tr>
</c:otherwise>
</c:choose>
	<tr><td colspan="5" style="text-align:right">
		<a href="warehousing.do">[�����԰�]</a></td></tr>
</table>
</body>
</html>