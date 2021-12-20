<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../resources/style.css" >
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="allContainer">
	
	<div class="left-div">
	<%@ include file="../includes/left.jsp" %>
	</div>
	
	<div class="right-div">
	<h1>게시물 등록 page</h1>
		<form role="form" action="/board/register" method="post">
			<div><label>게시물 제목</label><input name="title"></div><br/>
			<div><label>게시글 내용</label><textarea name="content" rows="25" cols="100"></textarea></div><br/>
			<div><label>게시글 작성자</label><input name="writer"></div>
			<div class="btn_space"><button type="submit" >완료</button><button type="reset">리셋</button><button type="button" >목록</button></div>
		</form>
	</div>
</div>

<div class="footer-div">
	<%@ include file="../includes/footer.jsp" %>
</div>

</body>
</html>