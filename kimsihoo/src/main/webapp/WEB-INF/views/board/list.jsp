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
<title>PT 업무 보조 게시판</title>
</head>
<body>
<div class="allContainer">
	<div class="left-div">
	<%@ include file="../includes/left.jsp" %>
	</div>
	<div class="right-div">
	<h1>게시글 페이지</h1>
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
				<td><a class= 'move' href='<c:out value="${board.bno}"/>' ><c:out value="${board.title}" /></a></td>
				<td><c:out value="${board.writer}" /></td>
				<td><fmt:formatDate pattern = "yyyy-MM-dd" value="${board.regdate}" /></td>
				<td><fmt:formatDate pattern= "yyyy-MM-dd" value="${board.updateDate}"/></td>
			</tr>
		</c:forEach>
		</table>
		
		<form id='searchForm' action="/board/list" method='get'>
			<select name='type'>
				<option value="" <c:out value="${pageMaker.cri.type == null? 'selected':''}"/>>--</option>
				<option value="T" <c:out value="${pageMaker.cri.type eq 'T'? 'selected':''}"/>>제목</option>
				<option value="C" <c:out value="${pageMaker.cri.type eq 'C'? 'selected':''}"/>>내용</option>
				<option value="W" <c:out value="${pageMaker.cri.type eq 'W'? 'selected':''}"/>>작성자</option>
				<option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'? 'selected':''}"/>>제목이나 내용</option>
				<option value="TW" <c:out value="${pageMaker.cri.type eq 'TW'? 'selected':''}"/>>제목이나 작성자</option>
				<option value="TWC" <c:out value="${pageMaker.cri.type eq 'TWC'? 'selected':''}"/>>제목이나 작성자나 내용</option>
				<input type="text" name="keyword" value="${pageMaker.cri.keyword}" />
				<a class="btn btn-default" href="/board/list"> 검색초기화</a>
				<button class="btn btn-default">검색</button>
			</select>
		</form>
		
		<form id="actionForm" action="/board/list" method="get">
			<input type="hidden" name="amount"  value="${pageMaker.cri.amount}" /> 
			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" />
			<input type="hidden" name="keyword" value="<c:out value= '${pageMaker.cri.keyword}'/>">
			<input type="hidden" name="type" value="<c:out value= '${pageMaker.cri.type}'/>">
		</form>
		
	
		
		<div class="pull-right">
			<ul class="pagination">
				<a href="#" id="pageFirst"  style="padding-right:5px" >맨 처음으로</a>
				<c:if test="${pageMaker.prev}">
					<li class="paginate_button previous">
						<a href="${pageMaker.startPage -1}">이전</a>
					</li>
				</c:if>
				
				<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage }">
					<li class="paginate_button ${pageMaker.cri.pageNum == num ? "active":""} " >
						<a  href="${num}" style="padding-right:5px">${num}</a>
					</li>
				</c:forEach>
				
				<c:if test="${pageMaker.next}">
					<li class="paginate_button next">
						<a href="${pageMaker.endPage +1}">다음</a>
					</li>
				</c:if>
			</ul>
		</div>
		
		
		<div class="btn_space"><button id="regBtn"  >글 쓰기</button></div>
	
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
		var modifyBno = "<c:out value='${modifyBno}'/>";
		
		checkModal(result);
		
		history.replaceState({},null,null);
		
		function checkModal(result){
			if(result=== ''|| history.state){
				return;
			}
			
			else if(parseInt(result) > 0 ){
				$('#myModal').html("게시글"+parseInt(result)+"번이 등록되었습니다.");
			}
			
			else if(result == "success"){
				$('#myModal').html( modifyBno+"번 게시글이 변경되었습니다.");
			}
			
			else if(result =="del"){
				$('#myModal').html( modifyBno+"번 게시글이 삭제되었습니다.");
			}
			
			$('#myModal').modal("show");
		}
		
		$("#regBtn").on("click",function(){
			
			self.location="/board/register";
		})
		
		var actionForm = $("#actionForm");
		
		$("#pageFirst").on("click", function(e){
			e.preventDefault();
			
			actionForm.find("input[name='pageNum']").val("1");
			actionForm.submit();
		});
		
		$(".paginate_button a").on("click", function(e){	
			e.preventDefault();
			
			console.log('click');
			
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});
		
		$(".move").on("click", function(e){	
			e.preventDefault();
			actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
			actionForm.attr("action", "/board/get");
			actionForm.submit();
		});
		
		var searchForm = $("#searchForm");
		
		$("#searchForm button").on("click", function(e){
			if(!searchForm.find("option:selected").val()){
				alert("검색어종류를 선택");
				return false;
			}
			
			if(!searchForm.find("input[name='keyword']").val()){
				alert("검색어써라");
				return false;
			}
			
			searchForm.find("input[name='pageNum']").val("1");
			e.preventDefault();
			
			searchForm.submit();
		});
		
		
		
	})
</script>


</body>
</html>