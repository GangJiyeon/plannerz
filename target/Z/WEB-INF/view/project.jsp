<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/10
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
    <script src="${pageContext.request.contextPath}/js/project.js"></script>
    <style>
        @font-face {
            font-family: 'NEXON Lv2 Gothic';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv2 Gothic.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
    </style>
</head>
<body>
<div class="contents">
    <%@include file="includes/side_bar.jsp"%>

    <div class="content">
        <div class="real">
            <div class="title">프로젝트</div>
            <div class="onetTothree">
                <div>
                    <div class="border_blue">
                        <div>
                            <spring:message code="project_title">
                                <spring:argument value="${loginSession.user_name}"/>
                            </spring:message>
                        </div>
                        <div class="project_items">
                            <hr>
                            <c:forEach items="${projectInfoList}" var="projectInfo">
                                <div class="project_item" id="${projectInfo.project_idx}">
                                    <span>${projectInfo.title}</span>
                                </div>
                            </c:forEach>
                            <hr>
                            <div class="project_item" id="add_project">
                                프로젝트를 추가하세요
                            </div>
                        </div>
                    </div>
                </div>
                <div>

                    <div class="border_blue" id="project_contents">
                        <c:forEach items="${projectInfoList}" var="projectInfo">
                            <div id="${projectInfo.project_idx}" class="project_middle">
                                <div class="content_title">
                                    <div class="title3">${projectInfo.title} [D-20]</div>
                                    <div>목표기한: ${projectInfo.target_date}</div>
                                </div>
                                <div class="three">
                                    <c:forEach items="${middleList}" var="middleItem">
                                        <c:forEach items="${middleItem}" var="middle">
                                            <c:if test="${projectInfo.project_idx==middle.project_idx}">
                                                <div class="bg_w">
                                                    <div class="title4" id="con_${middle.project_middle_idx}">
                                                            ${middle.title}
                                                    </div>
                                                    <c:forEach items="${itemList}" var="items">
                                                        <c:forEach items="${items}" var="item">
                                                            <c:if test="${middle.project_middle_idx==item.middle_idx}">
                                                                <div class="item_list">
                                                                    <input type="checkbox" name="" id=""
                                                                           class="${item.done}"
                                                                           checked disabled>
                                                                    <span>${item.title}</span>
                                                                </div>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:forEach>
                                                </div>

                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>


                                </div>
                            </div>
                        </c:forEach>
                        <div id="project_input_area" class="project_middle">
                            <div class="content_title">
                                <form:form action="${pageContext.request.contextPath}/project/form1" modelAttribute="projectCommand">
                                    <div class="title3">프로젝트 추가하기</div>
                                    <div>
                                        <form:input path="title" placeholder="프로젝트 이름을 입력하세요. "/>
                                    </div>
                                    <div>
                                        <form:input path="target_date" type="date" value='<fmt:formatDate pattern = yyyy-mm-dd>' placeholder="목표날짜를 입력하세요. "/>
                                    </div>
                                    <div class="input_project">
                                        <form:input path="middle_title" placeholder="중간목표를 입력하세요. "/>
                                    </div>
                                    <div class="input_project" id="add_middle_btn">
                                        <span>중간목표 추가하기</span>
                                    </div>
                                    <div>
                                        <form:button>다음으로</form:button>
                                    </div>
                                </form:form>


                            </div>
                        </div>
                    </div>


                </div>

            </div>
        </div>

    </div>
</div>
</body>
</html>
