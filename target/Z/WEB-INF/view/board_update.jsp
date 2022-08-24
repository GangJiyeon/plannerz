<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/10
  Time: 11:25 AM
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
    <div class="content">
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
                    <form:form modelAttribute="boardCommand">
                        <div>
                            <div>
                                <span>제목: </span>
                                <form:input path="board_title" value="${boardInfo.board_title}"/>
                            </div>
                            <div>
                                <span>작성자: </span>
                                <form:input path="board_content" value="${boardInfo.user_id}" disabled="true"/>
                            </div>
                            <div>
                                <span>내용: </span>
                                <form:input path="board_content" value="${boardInfo.board_content}"/>
                            </div>
                            <div>
                                <span>이미지1: </span>
                                <form:input path="board_img1" value="${boardInfo.board_img1}"/>
                            </div>
                            <div>
                                <span>이미지2: </span>
                                <form:input path="board_img2" value="${boardInfo.board_img2}"/>
                            </div>
                            <div>
                                <span>이미지3: </span>
                                <form:input path="board_img3" value="${boardInfo.board_img3}"/>
                            </div>
                            <div>
                                <span>이미지4: </span>
                                <form:input path="board_img4" value="${boardInfo.board_img4}"/>
                            </div>
                            <div>
                                <span>이미지5: </span>
                                <form:input path="board_img5" value="${boardInfo.board_img5}"/>
                            </div>

                            <form:input path="board_idx" type="hidden" value="${boardInfo.board_idx}"/>
                        </div>

                        <div>
                            <button type="submit" onclick="javascript:form.action='${pageContext.request.contextPath}/board/delete'">삭제하기</button>
                        </div>
                        <div>
                            <button type="submit" onclick="javascript:form.action='${pageContext.request.contextPath}/board/update.do'">수정하기</button>
                        </div>
                    </form:form>

                </div>

            </div>
        </div>

        <div></div>
    </div>
</div>
</body>
</html>
