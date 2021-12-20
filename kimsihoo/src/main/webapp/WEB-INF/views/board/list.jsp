<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% System.out.print("result"); %>

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
	<h1>list page</h1>
		<table id="board table">
			<thead>
				<tr>
					<th>글 번호(순서)</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>수정일</th>
				</tr>
			</thead>
		
		
		<c:forEach items="${list}" var="board">
			<tr>
				<td><c:out value="${board.bno}" /></td>
				<td><a href='/board/get?bno=<c:out value="${board.bno}"/>' ><c:out value="${board.title}" /></a></td>
				<td><c:out value="${board.writer}" /></td>
				<td><fmt:formatDate pattern = "yyyy-MM-dd" value="${board.regdate}" /></td>
				<td><fmt:formatDate pattern= "yyyy-MM-dd" value="${board.updateDate}"/></td>
			</tr>
		</c:forEach>
		</table>
		<div class="btn_space"><button  onclick="location.href='register'" >글 쓰기</button></div>
	
		<!-- modal창 -->
		<div class="modal" id="myModal" tabindex="-1" role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">Modal title</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <p>Modal body text goes here.</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary">Save changes</button>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- modal창 -->
		
	</div>
</div>
	<div class="footer-div">
		<%@ include file="../includes/footer.jsp" %>
	</div>

<script type="text/javascript">
	$(document).ready(function() {
		var result = "<c:out value='${result}'/>";
		
		checkModal(result);
		console.log(result);
		
		function checkModal(result){
			if(result=== ''){
				return;
			}
			
			if(parseInt(result) > 0){
				$('#myModal').html("게시글"+parseInt(result)+"번이 들록되었습니다.");
			}
			
			$('#myModal').modal("show");
		}
	})
</script>


</body>
</html>