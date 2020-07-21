<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<title><decorator:title /></title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
<script type="text/javascript">
	function inputcheck(f){
		if(f.id.value==''){
			alert("아이디를 입력하세요.");
			return false;
		}
		if(f.id.value.length < 4){
			alert("아이디는 4자리 이상 입력하세요.");
			return false;
		}
		if(f.pass.value==''){
			alert("비밀번호를 입력하세요.");
			return false;
		}
		return true;
	}
	
	function win_upload(){
		var op = "width=500, height=150, left=50, top=150";
		open("pictureForm.do","",op);
	}
	function win_passchg(){
		var op = "width=500, height=250, left=50, top=150";
		open("passwordForm.do","",op);
	}
</script>
<decorator:head />
<body class="w3-white">

<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
  <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  
  <a href="${path}/project/member/main.do" class="w3-bar-item w3-right">Main</a>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-light-grey w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
  <div class="w3-container w3-row">  
   	<c:if test="${empty sessionScope.login.id }">
    	<input type="button" value="로그인" onclick="document.getElementById('login').style.display='block'" style="width:80%; text-align: center; margin-bottom: 3px;"><br>
    	<input type="button" value="회원가입" onclick="document.getElementById('join').style.display='block'" style="width:80%; text-align: center;">    	 
    </c:if>
    <c:if test="${!empty sessionScope.login.id }">    		 
    	<div class="w3-col s4">
    		<img src="picture/${sessionScope.login.picture }" class="w3-circle w3-margin-right" style="width:46px">
    	</div>
    	<div class="w3-col s8 w3-bar"> 
    <span><strong>${sessionScope.login.name}님</strong></span><br>
    <span>${sessionScope.login.email}</span><br>
    <input type="button" value="내정보" onclick="location.href='${path}/project/member/info.do?id=${sessionScope.login.id}'" style="width:40%; text-align: center;">&nbsp;
    <input type="button" value="로그아웃" onclick="location.href='${path}/project/member/logout.do'" style="width:40%; text-align: center;">
    </div>
    </c:if>
  </div>
  <hr color="black">
  <div class="w3-container">
    <h5>도서관</h5>
  </div>
  <div class="w3-bar-block">
  	<c:if test="${empty sessionScope.login.id }">
    	<a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
	    <a href="${path}/project/member/info.do?id=${sessionScope.login.id}" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-users fa-fw"></i>내정보/회원관리</a>
	    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-book fa-fw"></i>신간도서</a>
	    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bell fa-fw"></i>대출/연장</a>
	    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-history fa-fw"></i>희망도서 신청</a>
	</c:if>
	<c:if test="${!empty sessionScope.login.id }">
	<c:choose>
		<c:when test="${sessionScope.login.id.equals('admin')}">
		<a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
	    <a href="${path}/project/member/mlist.do" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-users fa-fw"></i>회원관리</a>
	    <a href="${path}/project/book/blist.do" class="w3-bar-item w3-button w3-padding"><i class="fa fa-search fa-fw"></i>도서관리</a>
	    <a href="${path}/project/book/warehousing.do" class="w3-bar-item w3-button w3-padding"><i class="fa fa-book fa-fw"></i>도서입고</a>
	    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bell fa-fw"></i>대출/연체관리</a>
	    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-history fa-fw"></i>희망도서 관리</a>
		</c:when>
		<c:otherwise>
		<a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
	    <a href="${path}/project/member/info.do?id=${sessionScope.login.id}" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-users fa-fw"></i>내정보</a>
	    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-eye fa-fw"></i>보관함</a>
	    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-book fa-fw"></i>신간도서</a>
	    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bell fa-fw"></i>대출/연장</a>
	    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-history fa-fw"></i>희망도서 신청</a>
		</c:otherwise>
	</c:choose>
    	<c:if test="${sessionScope.login.id.equals('admin')}">
    	</c:if>
	</c:if>
    
  </div>
</nav>

<!-- Modal -->
<div id="login" class="w3-modal">
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
      <div class="w3-center"><br>
        <span onclick="document.getElementById('login').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
        <img src="../img/profile.png" alt="Avatar" style="width:30%" class="w3-circle w3-margin-top">
      </div>
      <form class="w3-container" action="${path}/project/member/login.do" method="post" name="p" onsubmit="return inputcheck(this)">
        <div class="w3-section">
          <label><b>아이디</b></label>
          <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="아이디를 입력하세요." name="id" required>
          <label><b>패스워드</b></label>
          <input class="w3-input w3-border" type="password" placeholder="비밀번호를 입력하세요." name="pass" required>
          <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit">Login</button>
        </div>
      </form>
      <div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
        <div class="w3-center">
			<a target="_blank" id="idinquiry" href="javascript:">아이디 찾기</a> 
			<span class="bar" aria-hidden="true">|</span> 
			<a target="_blank" id="pwinquiry" href="javascript:">비밀번호 찾기</a> 
		</div>
      </div>
    </div>
  </div>
 
<div id="join" class="w3-modal">
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
      <div class="w3-center"><br>
        <span onclick="document.getElementById('join').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
      </div>
      <form class="w3-container" action="${path}/project/member/join.do" method="post" name="f" onsubmit="return inputcheck(this)">
        <div class="w3-section">
          <input type="hidden" name="picture" value="">
	<table><tr><td rowspan="4"	valign="bottom">
		<img src="" width="100" height="120" id="pic"><br>
		<font size="1"><a href="javascript:win_upload()">사진등록</a></font>
		</td><th>아이디</th><td><input type="text" name="id"></td></tr>
		<tr><th>비밀번호</th><td><input type="password" name="pass"></td></tr>
		<tr><th>이름</th><td><input type="text" name="name"></td></tr><tr><td>성별</td><td>
		<tr><th>성별</th>
			<td><input type="radio" name="gender" value="1" checked>남
				<input type="radio" name="gender" value="2">여</td></tr>
		<tr><th>이메일</th><td colspan="2"><input type="text" name="email"></td></tr>
	</table>
          <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit">회원가입</button>
        </div>
      </form>
    </div>
  </div>
<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

  <!-- Header -->
  <div class="w3-row w3-padding-64">
	<div class="w3-twothird w3-container">
      <decorator:body/>
    </div>
   </div>

  <!-- Footer -->
  <footer class="w3-container w3-padding-16 w3-dark-grey">
    <h4>FOOTER</h4>
    <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
  </footer>

  <!-- End page content -->
</div>

<script>
// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidebar.style.display = 'block';
    overlayBg.style.display = "block";
  }
}

// Close the sidebar with the close button
function w3_close() {
  mySidebar.style.display = "none";
  overlayBg.style.display = "none";
}
</script>

</body>
</html>
