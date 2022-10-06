<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/10
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>plannerz</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/basic_theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userInfo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/todolist.css">
    <script src="${pageContext.request.contextPath}/js/function.js"></script>
    <script src="${pageContext.request.contextPath}/js/todolist_update.js"></script>

    <style>
        @font-face {
            font-family: 'NEXON Lv2 Gothic';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv2 Gothic.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
    </style>
    <script>
        $(document).ready(function () {
            $(".con").css('display', 'none');
            $(".con").filter("#${param.list_idx}").css('display', 'block');

            if(document.getElementById("input_check").checked) {
                document.getElementById("input_check_hidden").disabled = true;
            }

        });
    </script>
</head>
<body>
<div class="contents">
    <%@include file="includes/side_bar.jsp" %>
    <div class="content" style="height: 100%">
        <div class="real">
            <div class="title">todolist</div>
            <div class="onetTothree">
                <div>
                    <div class="border_blue not">
                        <div>
                            <spring:message code="todolist_title">
                                <spring:argument value="${loginSession.user_name}"/>
                            </spring:message>
                        </div>
                        <div class="project_items">
                            <hr>
                            <div class="scroll">
                                <c:forEach items="${todolistInfoList}" var="todolistInfo">
                                    <div class="project_item" id="${todolistInfo.list_idx}">
                                        <span>${todolistInfo.list_title}</span>
                                    </div>
                                </c:forEach>
                            </div>

                        </div>
                    </div>
                </div>
                <div>
                    <div class="border_blue not" id="project_contents" >

                        <c:forEach items="${todolistInfoList}" var="todolistInfo">
                            <form:form modelAttribute="todolist" action="${pageContext.request.contextPath}/todolist/update.do">
                                <div id="${todolistInfo.list_idx}" class="con">
                                    <div class="content_title">
                                        <form:input path="list_idx" type="hidden" value="${todolistInfo.list_idx}"/>
                                        <form:input path="user_id" type="hidden" value="${loginSession.user_id}"/>
                                        <div class="three_items">
                                            <div class="title3 ">
                                                <form:input path="list_title" value="${todolistInfo.list_title}"/>
                                            </div>

                                        </div>
                                        <div>
                                            <button type="button" class="total_delete_btn"
                                                    id="list_idx=${todolistInfo.list_idx}">삭제
                                            </button>
                                        </div>
                                    </div>
                                    <div class="bg_w" style="max-height: 500px; overflow: scroll;">
                                        <c:forEach items="${listItemInfoList}" var="listItemInfo">
                                            <c:forEach items="${listItemInfo}" var="listItem">
                                                <c:if test="${todolistInfo.list_idx==listItem.list_idx}">
                                                    <div class="item_list">
                                                        <div>
                                                            <input type="checkbox" name="done" value="true"
                                                                       id="${listItem.list_item_idx}" class="input_check">

                                                            <input type="hidden" name="done" value="false"
                                                                   id="${listItem.list_item_idx}" class="input_check_hidden">

                                                            <form:input path="item_title"
                                                                        value="${listItem.item_title}"/>
                                                            <button type="button" class="delete_btn"
                                                                    id="list_idx=${todolistInfo.list_idx}&item_idx=${listItem.list_item_idx}">
                                                                X
                                                            </button>
                                                            <form:input path="list_item_idx"
                                                                        value="${listItem.list_item_idx}"
                                                                        type="hidden"/>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>

                                    </div>
                                </div>
                                <hr>
                                <div style="width: 100%;">
                                    <button type="button" class="add_todolist_item btn_op"
                                            id="list_idx=${todolistInfo.list_idx}">
                                        <span>투두리스트 추가하기</span>
                                    </button>
                                </div>
                                <div style="width: 100%;">
                                    <form:button class="btn_op">제출하기</form:button>
                                </div>

                            </form:form>
                        </c:forEach>

                    </div>
                </div>
            </div>
            <div></div>
        </div>
    </div>

</div>

<script>


</script>
</body>
</html>
