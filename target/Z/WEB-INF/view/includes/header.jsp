<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/15
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="headerArea">
    <div class="menu_bar">
        <div></div>
        <div class="circle"></div>
        <div class="circle"></div>
        <div class="circle"></div>
        <div></div>
        <div>
            <button id="go_login">로그인</button>
        </div>
        <div></div>
        <div>
            <button id="go_join">회원가입</button>
        </div>
        <div></div>

    </div>
    <div class="header">
        <div class="logo_wrapper" id="go_home">plannerZ</div>
        <div></div>
        <div></div>
        <div><i class="bi bi-person-fill"></i></div>
        <div></div>
        <a href="${pageContext.request.contextPath}/userinfo">
            <div><i class="bi bi-list" id="go_planner"></i></div>
        </a>

    </div>
</div>
<script>
    $("#go_login").on("click", function(){
        location.href = "${pageContext.request.contextPath}/login"
    })

    $("#go_join").on("click", function(){
        location.href = "${pageContext.request.contextPath}/join"
    })

    $("#go_home").on("click", function(){
        location.href = "${pageContext.request.contextPath}/home";
    })
</script>
