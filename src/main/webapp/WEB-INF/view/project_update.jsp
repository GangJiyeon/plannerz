<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/09/15
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/project.css">
    <script src="${pageContext.request.contextPath}/js/function.js"></script>
    <script src="${pageContext.request.contextPath}/js/project_update.js"></script>
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
            $(".project_middle").css('display', 'none');

            $(".project_middle").filter("#${param.project_idx}").css('display', 'block');

            $(".project_item").filter("#${param.project_idx}").css('border-radius', '12px');
            $(".project_item").filter("#${param.project_idx}").css('background-color', 'rgb(242, 244, 247)');
            $(".project_item").filter("#${param.project_idx}").css('border', 'solid 1px rgb(195, 204, 217)');

            $(".add_project_item").on("click", function () {
                let idx = $(this).attr('id');
                location.href = "/Z/project/update/for/add?" + idx;
            })
        });
    </script>
</head>
<body>
<div class="contents">
    <%@include file="includes/side_bar.jsp" %>

    <div class="content">
        <div class="real">
            <div class="title">????????????</div>
            <div class="onetTothree">
                <div>
                    <div class="border_blue border_blue_left not">
                        <div>
                            <spring:message code="project_title">
                                <spring:argument value="${loginSession.user_name}"/>
                            </spring:message>
                        </div>
                        <div class="project_items ">
                            <hr>
                            <div class="scroll">
                                <c:if test="${empty projectInfoList}">
                                    <div>???????????? ????????? ????????????.</div>
                                </c:if>
                                <c:forEach items="${projectInfoList}" var="projectInfo">
                                    <div class="project_item" id="${projectInfo.project_idx}">
                                        <span>${projectInfo.title}</span>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>

                <div>
                    <div class="border_blue " id="project_contents">
                        <form:form action="${pageContext.request.contextPath}/project/update.do" modelAttribute="projectCommand">
                            <c:forEach items="${projectInfoList}" var="projectInfo">
                                <div id="${projectInfo.project_idx}" class="project_middle">
                                    <div class="content_title">
                                        <div class="title3">
                                            <form:input path="title" value="${projectInfo.title}"/>
                                            <input type="text" name="project_idx" value="${projectInfo.project_idx}" hidden>

                                        </div>
                                        <div style="display: grid; grid-template-columns: 70px 1fr; margin: 5px 0px">
                                            <div style="padding: 3px 0px;">????????????:</div>
                                            <div>
                                                <form:input path="target_date" type="date" value="${projectInfo.target_date}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="scroller_wrapper">
                                        <div class="three">
                                            <c:forEach items="${middleList}" var="middleItem">
                                                <c:forEach items="${middleItem}" var="middle">
                                                    <c:if test="${projectInfo.project_idx==middle.project_idx}">
                                                        <div class="bg_w">
                                                            <div class="title4" id="con_${middle.project_middle_idx}">
                                                                <input type="text" name="pro_idx" value="${middle.project_idx}" hidden/>
                                                                <input type="text" name="middle_idx" value="${middle.project_middle_idx}" hidden/>
                                                                <input type="text" name="middle_title" value="${middle.title}"/>
                                                            </div>
                                                            <c:forEach items="${itemList}" var="items">
                                                                <c:forEach items="${items}" var="item">
                                                                    <c:if test="${middle.project_middle_idx==item.middle_idx}">
                                                                        <div class="item_list" style="display: grid; grid-template-columns: 15px 1fr; gap: 5px">
                                                                            <input type="text" name="item_idx" value="${item.item_idx}" hidden/>
                                                                            <c:if test="${item.done == true}">
                                                                                <input type="checkbox" name="item_done"
                                                                                       id=""
                                                                                       class="${item.done}" checked>
                                                                            </c:if>
                                                                            <c:if test="${item.done != true}">
                                                                                <input type="checkbox" name="item_done"
                                                                                       id=""
                                                                                       class="${item.done}">
                                                                            </c:if>
                                                                            <input type="text" name="item_title" value="${item.title}"/>

                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:forEach>
                                                            <button type="button" class="add_project_item btn_op"
                                                                    id="middle_idx=${middle.project_middle_idx}&project_idx=${projectInfo.project_idx}">
                                                                ?????? ????????????
                                                            </button>

                                                        </div>

                                                    </c:if>


                                                </c:forEach>
                                            </c:forEach>

                                        </div>


                                    </div>

                                </div>
                            </c:forEach>
                            <hr>
                            <form:button class="btn_op" style="margin-top: 10px">????????????</form:button>
                        </form:form>
                    </div>
                </div>


            </div>
        </div>

    </div>
</div>
</body>
</html>
