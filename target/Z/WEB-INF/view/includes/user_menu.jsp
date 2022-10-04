<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/15
  Time: 12:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="menu_bar">

    <div id="userImgWrapper">
        <c:if test="${loginSession.sns == 'none'}">
            <img src="${pageContext.request.contextPath}/img/user/${loginSession.img}" alt="" style="border-radius: 50%">
        </c:if>
        <img src="${loginSession.img}" alt="" style="border-radius: 50%">
    </div>
    <div id="user_info"><span>user's info</span></div>
    <div id="go_alarm2"><span>alarm</span></div>
    <div id="go_board2"><span>board</span></div>
    <div id="go_status"><span>status</span></div>
    <hr>
    <div id="logout_btn"><span>logout</span></div>
    <div id="delete_user"><span>회원탈퇴</span></div>
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

    $("#delete_user").on("click", function(){
        location.href = "${pageContext.request.contextPath}/delete/user"
    })

    $("#logout_btn").on("click", function(){
        location.href = "${pageContext.request.contextPath}/logout"
    })


</script>

