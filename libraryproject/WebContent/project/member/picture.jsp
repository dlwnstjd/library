<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Graphics2D"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<script type="text/javascript">
	img = opener.document.getElementById("pic");
	//opener 창에 이미지가 보여짐
	img.src = "picture/${fname}";	
	//hidden태그. 파라미터값 설정
	opener.document.f.picture.value="${fname}";
	self.close();
</script> 