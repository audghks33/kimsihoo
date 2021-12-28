<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<title>title</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PaintJS</title>
   
</head>
<body>
    <canvas id="jsCanvas" class="canvas"></canvas>
    <div class="controls__btns">
        <button id="jsBrush">브러쉬</button>
        <button id="jsErase">지우개</button>
        <input type="range" id="jsRange" min="0.1" max="20.0" step="0.1"/>
    </div>
<script >
    const canvas = document.getElementById("jsCanvas");
const ctx = canvas.getContext("2d");
const brush = document.getElementById("jsBrush");
const erase = document.getElementById("jsErase");
const range = document.getElementById("jsRange");

const INITIAL_COLOR = "#2c2c2c";
const INITIAL_LINEWIDTH = 5.0;
const CANVAS_SIZE = 500;

ctx.strokeStyle = INITIAL_COLOR;
ctx.fillStyle = INITIAL_COLOR;
ctx.lineWidth = INITIAL_LINEWIDTH;
canvas.width = CANVAS_SIZE;
canvas.height = CANVAS_SIZE;

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
ctx.drawImage(img, 0, 0, 720, 1050);

MODE_BUTTON[0].addEventListener("click", handleModeChange);
MODE_BUTTON[1].addEventListener("click", handleModeChange);

range.addEventListener("input", handleRangeChange);
</script>
</body>
</html>