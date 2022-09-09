<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/09/07
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="two">
    <div class="service_name">plannerZ > board</div>
    <div class="btn_area">
        <button id="board_form">글 작성하기</button>
        <button id="board_mine">내 피드보기</button>
        <button id="board_list">전체 글 보기</button>
    </div>
</div>
<script>
    $("#board_form").on("click", function (){
        location.href = "${pageContext.request.contextPath}/board/form";
    })
    $("#board_mine").on("click", function (){
        location.href = "${pageContext.request.contextPath}/board";
    })
    $("#board_list").on("click", function (){
        location.href = "${pageContext.request.contextPath}/board/list";
    })
</script>
