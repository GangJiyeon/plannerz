<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/15
  Time: 12:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="menu_bar">
    <div id="userImgWrapper">
        <img src="${pageContext.request.contextPath}/img/2021_Login_with_naver_guidelines_Kr/btnW_아이콘원형.png" alt="">
    </div>
    <div id="user_info"><span>user's info</span></div>
    <div id="go_alarm2"><span>alram</span></div>
    <div id="go_setting"><span>setting</span></div>
    <div id="go_board2"><span>board</span></div>
    <hr>
    <div id="go_status"><span>status</span></div>
    <hr>
    <div><span>logout</span></div>
    <div><span>회원탈퇴</span></div>
</div>
<script>

    $("#user_info").on("click", function(){
        location.href = "${pageContext.request.contextPath}/userinfo"
    })

    $("#go_board2").on("click", function(){
        location.href = "${pageContext.request.contextPath}/board"
    })

    $("#go_status").on("click", function(){
        location.href = "${pageContext.request.contextPath}/status"
    })

    $("#go_alarm2").on("click", function(){
        location.href = "${pageContext.request.contextPath}/alarm"
    })

    $("#go_setting").on("click", function(){
        location.href = "${pageContext.request.contextPath}/setting"
    })


</script>

