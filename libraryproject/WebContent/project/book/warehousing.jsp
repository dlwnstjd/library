<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>도서 입고</title>
</head>
<body>
<form action="write.do" method="post" name="f">
	<table>
		<caption>도서 입고</caption>
		<tr><td>제목</td><td><input type="text" name="name"></td></tr>
		<tr><td>저자</td><td><input type="text" name="author"></td></tr>
		<tr><td>출판사</td><td><input type="text" name="publisher"></td></tr>
		<tr><td>출판년도</td><td><input type="text" name="publicationyear"></td></tr>
		<tr><td>가격</td><td><input type="text" name="price"></td></tr>
		<tr><td>책소개</td><td><input type="text" name="introduction"></td></tr>
		<tr><td colspan="2"><input type="submit" value="도서입고"></td></tr>
	</table>	
</form>
</body>
</html>