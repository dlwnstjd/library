<%@page import="java.awt.Graphics2D"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.util.List"%>
<%@page import="model.MemberDao"%>
<%@page import="model.Member"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ������</title>
</head>
<body>
<table><caption>ȸ�� ���</caption>
<tr><th>���̵�</th><th>����</th><th>�̸�</th><th>����</th><th>���</th><th>&nbsp;</th></tr>
<c:forEach var="m" items="${list }">
<tr><td><a href="info.do?id=${m.id }">${m.id }</a></td>
<td><img src="picture/sm_${m.picture }" width="20" height="20" id="pic"></td>
<td>${m.name }</td>
<td>${m.gender==1?"��":"��"}</td>
<td><a href="updateForm.do?id=${m.id }">[����]</a>
<c:if test="${m.id != 'admin' }">
<a href="delete.do?id=${m.id }">[����Ż��]</a>
</c:if>
</td></tr></c:forEach>
</table>
</body>
</html>