<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� �԰�</title>
</head>
<body>
<form action="write.do" method="post" name="f">
	<table>
		<caption>���� �԰�</caption>
		<tr><td>����</td><td><input type="text" name="name"></td></tr>
		<tr><td>����</td><td><input type="text" name="author"></td></tr>
		<tr><td>���ǻ�</td><td><input type="text" name="publisher"></td></tr>
		<tr><td>���ǳ⵵</td><td><input type="text" name="publicationyear"></td></tr>
		<tr><td>����</td><td><input type="text" name="price"></td></tr>
		<tr><td>å�Ұ�</td><td><input type="text" name="introduction"></td></tr>
		<tr><td colspan="2"><input type="submit" value="�����԰�"></td></tr>
	</table>	
</form>
</body>
</html>