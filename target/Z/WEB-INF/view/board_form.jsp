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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/basic_theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userInfo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base2.css">
    <script src="${pageContext.request.contextPath}/js/function.js"></script>
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
    <div class="content margin_40">
        <div class="real">

            <div class="contentArea">
                <div class="two">
                    <div class="service_name">plannerZ > board</div>
                    <div class="btn_area">
                        <button id="board_form">글 작성하기</button>
                        <button id="board_mine">내 피드보기</button>
                        <button id="board_list">전체 글 보기</button>
                    </div>
                </div>

                <div class="title">
                    <div>
                        <img src="${pageContext.request.contextPath}/img/2021_Login_with_naver_guidelines_Kr/btnW_아이콘원형.png" alt="">
                        <span>${loginSession.user_name}</span>
                    </div>


                </div>
                <div class="margin_con">
                    <form:form method="post" action="${pageContext.request.contextPath}/board/form.do"  enctype="multipart/form-data" modelAttribute="boardCommand">
                        <div>
                            <label>
                                <spring:message code="board.title" />
                            </label>
                            <form:input path="board_title"/>
                        </div>
                        <div>
                            <label>
                                <spring:message code="board.content" />
                            </label>
                            <form:input path="board_content"/>
                        </div>
                        <div>
                            <label>
                                <spring:message code="board.img" />
                            </label>
                            <input type="file" name="file" multiple="multiple"/>
                        </div>
                        <div>
                            <label>
                                <spring:message code="board.img" />
                            </label>
                            <input type="file" name="file" multiple="multiple"/>
                        </div>

                        <form:button>
                            <spring:message code="board.form"/>
                        </form:button>
                    </form:form>

                    <hr>



                </div>

            </div>
        </div>

        <div></div>
    </div>
</div>
</body>
</html>
