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
<title>PT 업무 보조 게시판</title>
</head>
<body>
<div class="allContainer">
	
	<div class="left-div">
	<%@ include file="../includes/left.jsp" %>
	</div>
	
	<div class="right-div">
		<h1>게시물 변경 페이지</h1>
			<form action="/board/modify" method="post">
				<div><label>게시물 번호</label><input name="bno" value='<c:out value="${board.bno}"/>' readonly="readonly" ></div><br/>
				<div><label>게시물 제목</label><input name="title" value='<c:out value="${board.title}"/>'  ></div><br/>
				<div><label>게시글 내용</label><textarea name="content" rows="25" cols="100" ><c:out value="${board.title}"/></textarea></div><br/>
				<div><label>게시글 작성자</label><input name="writer" value='<c:out value="${board.writer}"/>' readonly="readonly" ></div>
				
				<div class="btn_space">
					<button type="submit" data-oper="remove" >삭제</button>
					<button type="submit" data-oper="modify" >변경</button>
					<button type="submit" data-oper="list"  >목록</button>
				</div>
			</form>
	</div>
</div>

<div class="footer-div">
	<%@ include file="../includes/footer.jsp" %>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var formObj=$("form");
		
		$('button').on('click',function(e){
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation)
			
			if(operation ==='remove'){
				formObj.attr("action", "/board/remove");
				
			}else if(operation === 'list'){
				self.location="/board/list";
				formObj.attr("action","/board/list").attr("method","get")
				formObj.empty();
				
			}
			console.log("")
			console.log(operation)
			formObj.submit();
	
		});
	})
</script>
</body>
</html>