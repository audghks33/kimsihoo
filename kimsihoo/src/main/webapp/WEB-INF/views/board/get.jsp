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
			<div><label>게시물 번호</label><input name="bno" value='<c:out value="${board.bno}"/>' readonly="readonly" ></div><br/>
			<div><label>게시물 제목</label><input name="title" value='<c:out value="${board.title}"/>'  readonly="readonly"></div><br/>
			<div><label>게시글 내용</label><textarea name="content" rows="25" cols="100" readonly="readonly"><c:out value="${board.title}"/></textarea></div><br/>
			<div><label>게시글 작성자</label><input name="writer" value='<c:out value="${board.writer}"/>' readonly="readonly" ></div>
			
			<div class="btn_space">
				<button data-oper="modify" onclick="location.href='/board/modify?bno=<c:out value="${board.bno}"/>'" >변경</button>
				<button data-oper="list" onclick="location.href='/board/list'" >목록</button>
			</div>
	</div>
</div>

<div class="footer-div">
	<%@ include file="../includes/footer.jsp" %>
</div>

</body>
</html>