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
    <script src="${pageContext.request.contextPath}/js/board.js"></script>

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
            let width = $(".board_img_1 img").width();
            $(".board_img_1 img").css('height', width);
            $(".board_img_1 img").css('object-fit', 'contain');

        });
    </script>
</head>
<body>
<div class="contents">
    <%@include file="includes/side_bar.jsp"%>
    <div class="content margin_40">
        <div class="real">

            <div class="contentArea">
                <%@include file="includes/board.jsp"%>

                <div class="title">
                    <div>
                        <img src="${pageContext.request.contextPath}/img/2021_Login_with_naver_guidelines_Kr/btnW_아이콘원형.png" alt="">
                        <span>plannerz</span>
                    </div>


                </div>
                <div class="margin_con " id="board_area">
                    <div class="board_scroll">
                        <c:if test="${boardInfoList_18 == null}">
                            게시글이 없습니다.
                        </c:if>

                        <c:if test="${boardInfoList_18 != null}">
                            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                                <c:forEach items="${boardInfoList_18}" var="boardInfo">
                                    <div class="col">
                                        <div class="card shadow-sm">
                                            <div class="card-body">
                                                <a href="${pageContext.request.contextPath}/board/detail?board_idx=${boardInfo.board_idx}">
                                                    <div class="board_img_1">
                                                        <img src="${pageContext.request.contextPath}/img/board/${boardInfo.board_img1}" style="width: 100%;">
                                                        <div class="board_grid">
                                                            <div>제목: ${boardInfo.board_title}</div>
                                                            <div style="text-align: right"><i class="bi bi-eye"></i> ${boardInfo.see}</div>
                                                            <div style="text-align: right"><i class="bi bi-heart"></i> ${boardInfo.like}</div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>


                        </c:if>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">
                                <c:forEach begin="1" end="${end}" var="number">
                                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/board/list?page=${number}">${number}</a></li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>

                </div>




            </div>
        </div>

        <div></div>
    </div>
</div>
</body>
</html>
