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
	<h1>게시물 등록 페이지</h1>
		<form role="form" action="/board/register" method="post">
			<div><label>게시물 제목</label><input name="title"  style="width: auto;" ></div><br/>
		<div style="display:none">
		<div><label>게시글 내용</label><textarea id='content_txtarea'name="content" rows="25" cols="100" value="">공란</textarea></div><br/>
		</div>
    <canvas id="jsCanvas" class="canvas"></canvas>
    <div class="controls__btns">
        <button id="jsBrush">브러쉬</button>
        <button id="jsErase">지우개</button>
        <input type="range" id="jsRange" min="0.1" max="20.0" step="0.1"/>
    </div>

			<div><label>게시글 작성자</label><input name="writer" value="작성자 필요시 지우고 쓰기" ></div>
			
			<div class="btn_space">
			<button type="submit" data-oper="register" >등록</button>
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
		
		$('#jsCanvas').change(function(e){
			
		});
		
		$('button').on('click',function(e){
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation);
			
			if(operation ==='register'){
				if(!formObj.find("input[name='title']").val()){
					alert("제목 미입력");
					return false;
				}
				if(!formObj.find("input[name='writer']").val()){
					alert("작성자란 미입력");
					return false;
				}
				
				var inputFile = $("#jsCanvas").get(0);
				
				 var dataUrl = inputFile.toDataURL("image/jpeg");
				
				 var blob = dataURItoBlob(dataUrl);
				var formData = new FormData();
				formData.append("uploadFile", blob);
				
				
				// canvas를 파일로 변경
				function dataURItoBlob(dataURI)
				{
				    var byteString = atob(dataURI.split(',')[1]);
				    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0]
				    var ab = new ArrayBuffer(byteString.length);
				    var ia = new Uint8Array(ab);
				    for (var i = 0; i < byteString.length; i++)
				    {
				        ia[i] = byteString.charCodeAt(i);
				    }

				    var bb = new Blob([ab], { "type": mimeString });
				    
				    return bb;
				}
				
				// canvas를 파일로 변경
			
				
				$.ajax({
					url: '/board/uploadAjaxAction',
					processData : false,
					contentType: false,
					data: formData,
					type: 'POST',
					dataType: 'json',
					success: function(result){
						console.log(result);
					}
				});
				formObj.attr("action", "/board/register2");
				formObj.submit();
				
			}else if(operation === 'list'){
				self.location="/board/list";
				
			}
			
		});
		
	});
</script>
<script >
    const canvas = document.getElementById("jsCanvas");
const ctx = canvas.getContext("2d");
const brush = document.getElementById("jsBrush");
const erase = document.getElementById("jsErase");
const range = document.getElementById("jsRange");

const INITIAL_COLOR = "#2c2c2c";
const INITIAL_LINEWIDTH = 5.0;


var img = new Image();
img.src="../resources/images/trainingProgram.jpg";

ctx.strokeStyle = INITIAL_COLOR;
ctx.fillStyle = INITIAL_COLOR;
ctx.lineWidth = INITIAL_LINEWIDTH;
canvas.width = 720;
canvas.height =1050;


ctx.drawImage(img, 0, 0, 720, 1050);
const MODE_BUTTON = [brush, erase];
let mode = brush;
let painting = false;

function startPainting() { painting = true; }
function stopPainting() { painting = false; }

function onMouseMove(event) {
    const x = event.offsetX;
    const y = event.offsetY;
    if(mode === brush){
        if(!painting) {
            ctx.beginPath();
            ctx.moveTo(x, y);
        }
        else {
            ctx.lineTo(x, y);
            ctx.stroke();
        }
    }
    else if(mode === erase){
        if(painting) {
            ctx.clearRect(x-ctx.lineWidth/2, y-ctx.lineWidth/2, ctx.lineWidth, ctx.lineWidth);
        }
    }
}

function handleModeChange(event) {
    mode = event.target;
    // Button Highlight
    for(i = 0 ; i < MODE_BUTTON.length ; i++){
        var button = MODE_BUTTON[i];
        if(button === mode){
            button.style.backgroundColor = "skyblue";
        }
        else {
            button.style.backgroundColor = "white";
        }
    }
}

function handleRangeChange(event) {
    const size = event.target.value;
    ctx.lineWidth = size;
    range.value = size;
}

if (canvas) {
    canvas.addEventListener("mousemove", onMouseMove);
    canvas.addEventListener("mousedown", startPainting);
    canvas.addEventListener("mouseup", stopPainting);
    canvas.addEventListener("mouseleave", stopPainting);
}


MODE_BUTTON[0].addEventListener("click", handleModeChange);
MODE_BUTTON[1].addEventListener("click", handleModeChange);

range.addEventListener("input", handleRangeChange);
</script>

</body>
</html>