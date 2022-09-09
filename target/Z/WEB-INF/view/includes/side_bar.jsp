<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/15
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="side_bar">
    <div><i class="bi bi-person-circle font_30" id="go_userinfo"></i></div>
    <div class="menu">
        <div><i class="bi bi-calendar-check space" id="go_calendar"></i></div>
        <div><i class="bi bi-check2-square space" id="go_todolist"></i></div>
        <div><i class="bi bi-list-task space" id="go_project"></i></div>
        <div><i class="bi bi-people space" id="go_board"></i></div>

        <div class="bottom">
            <div><i class="bi bi-house space" id="go_home"></i></div>
            <div><i class="bi bi-bell-fill space" id="go_alarm"></i></div>
            <div><i class="bi bi-question-circle-fill space" id="go_faq"></i></div>
        </div>
    </div>
</div>
<script>


        $("#go_home").on("click", function(){
            location.href = "${pageContext.request.contextPath}/home";
        })

        $("#go_userinfo").on("click", function(){
            location.href = "${pageContext.request.contextPath}/userinfo"
        })

        $("#go_calendar").on("click", function(){
            location.href = "${pageContext.request.contextPath}/monthly"
        })

        $("#go_todolist").on("click", function(){
            location.href = "${pageContext.request.contextPath}/todolist"
        })

        $("#go_project").on("click", function(){
            location.href = "${pageContext.request.contextPath}/project"
        })

        $("#go_board").on("click", function(){
            location.href = "${pageContext.request.contextPath}/board"
        })

        $("#go_faq").on("click", function(){
            location.href = "${pageContext.request.contextPath}/home?menu=faq"
        })



        $("#go_alarm").on("click", function(){
            location.href = "${pageContext.request.contextPath}/alarm"
        });

</script>