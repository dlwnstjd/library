<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Graphics2D"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<script type="text/javascript">
	img = opener.document.getElementById("pic");
	//opener â�� �̹����� ������
	img.src = "picture/${fname}";	
	//hidden�±�. �Ķ���Ͱ� ����
	opener.document.f.picture.value="${fname}";
	self.close();
</script> 