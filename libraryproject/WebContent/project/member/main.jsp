<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>메인화면</title>
</head>
<body>
<form action="" name="sf" style="text-align:center; width:100%;" method="post">
	<div style="display:flex; justify-content:center; width:100%;">
		<input type="text" name="find" style="width:50%;">
		<input type="submit" value="검색">
	</div>
	<hr color="black" width="100%">
	<div class="w3-row-padding w3-margin-bottom">
		<div class="w3-third" style="width:33%; border: 1px solid #000000">
			<img name="img" src="../img/new.jpg" width="100" height="100">
			<br><a href="main.do" class="w3-bar-item" style="text-decoration: none"><font size="6">신상도서</font></a>		
		</div>
		<div class="w3-third" style="width:33%; border: 1px solid #000000">
			<img name="img" src="../img/research.jpg" width="100" height="100">
			<br><a href="main.do" class="w3-bar-item" style="text-decoration: none"><font size="6">대출도서/연장신청</font></a>
		</div>
		<div class="w3-third" style="width:33%; border: 1px solid #000000">
			<img name="img" src="../img/register.jpg" width="100" height="100">
			<br><a href="main.do" class="w3-bar-item" style="text-decoration: none"><font size="6">희망도서 신청</font></a>
		</div>
	</div>
</form>
</body>
</html>