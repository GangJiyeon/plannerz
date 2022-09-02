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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
</head>
<body>
<div class="contents">
    <%@include file="includes/side_bar.jsp" %>
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
                <script>


                    $(document).ready(function () {

                        $("#board_form").on("click", function () {
                            location.href = "/board/form";
                        })
                    });
                </script>

                <div class="title">
                    <div>
                        <img src="${pageContext.request.contextPath}/img/2021_Login_with_naver_guidelines_Kr/btnW_아이콘원형.png"
                             alt="">
                        <span>${loginSession.user_name}</span>
                    </div>
                </div>

                <div class="board">
                    <div class="margin_con">
                        <form:form modelAttribute="boardCommand">
                            <div>
                                <div>
                                    <span>제목: ${boardInfo.board_title}</span>
                                </div>
                                <div>
                                    <span>조회수: ${boardInfo.see}</span>
                                </div>
                                <div>
                                    <span>작성자: ${boardInfo.user_id}</span>
                                </div>
                                <div>
                                    <span>내용: ${boardInfo.board_content}</span>
                                </div>
                                <div>
                                    <span>작성일: ${boardInfo.insert_date}</span>
                                </div>
                                <div>
                                    <span>좋아요: ${boardInfo.like}</span>
                                </div>
                                <div>
                                    <span>이미지1: </span>
                                    <img src="${boardInfo.board_img1}">
                                </div>
                                <div>
                                    <span>이미지2: ${boardInfo.board_img2}</span>
                                </div>
                                <div>
                                    <span>이미지3: ${boardInfo.board_img3}</span>
                                </div>
                                <div>
                                    <span>이미지4: ${boardInfo.board_img4}</span>
                                </div>
                                <div>
                                    <span>이미지5: ${boardInfo.board_img5}</span>
                                </div>
                                <form:input path="board_idx" type="hidden" value="${boardInfo.board_idx}"/>

                                <a href="${pageContext.request.contextPath}/add/board/like?board_idx=${boardInfo.board_idx}">좋아요 추가</a>
                                <a href="${pageContext.request.contextPath}/delete/board/like?board_idx=${boardInfo.board_idx}">좋아요 삭제</a>

                            </div>


                            <div>
                                <button type="submit"
                                        onclick="javascript:form.action='${pageContext.request.contextPath}/board/delete'"
                                        id="service">삭제하기
                                </button>
                            </div>
                            <div>
                                <button type="submit"
                                        onclick="javascript:form.action='${pageContext.request.contextPath}/board/update'"
                                        id="logout_btn">수정하기
                                </button>
                            </div>


                        </form:form>

                    </div>

                    <div class="margin_con">
                        <div>
                            <span>댓글</span>
                            <span style="color: #2C3E50">${commentAmount}</span>
                        </div>
                        <hr>
                        <div class="add_comment">
                            <form:form modelAttribute="commentCommand" action="${pageContext.request.contextPath}/comment/add">
                                <label for="user_id">작성자: </label>
                                <form:input path="user_id" value="${loginSession.user_id}" id="user_id" readonly="true"/>
                                <label for="content">내용: </label>
                                <form:input path="content" id="content"/>
                                <form:input path="parent_board_idx" value="${boardInfo.board_idx}" type="hidden"/>
                                <form:button>작성하기</form:button>
                            </form:form>
                        </div>

                        <hr>

                        <div class="board_scroll">
                            <c:forEach items="${commentInfoList}" var="commentInfo">
                                <div id="${commentInfo.comment_idx}">
                                    <div class="comment_info_area">
                                        <div>작성자: ${commentInfo.user_id}</div>
                                        <div>작성일: ${commentInfo.insert_date}</div>
                                        <div>종아요: ${commentInfo.like}</div>
                                        <a href="${pageContext.request.contextPath}/add/comment/like?comment_idx=${commentInfo.comment_idx}">좋아요 추가</a>
                                        <a href="${pageContext.request.contextPath}/delete/comment/like?comment_idx=${commentInfo.comment_idx}">좋아요 삭제</a>

                                        <div class="comment_btn_area">
                                            <div>
                                                <a href="${pageContext.request.contextPath}/comment/delete?comment_idx=${commentInfo.comment_idx}">
                                                    <button type="button" class="delete_comment">삭제</button>
                                                </a>
                                            </div>
                                            <div>
                                                <button type="button" class="update_comment_btn">수정</button>
                                            </div>
                                            <div>
                                                <button type="button" class="add_comment_btn">대댓글 작성</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="update_comment_con">
                                            <form:form action="${pageContext.request.contextPath}/comment/update" modelAttribute="commentCommand">
                                                <input type="text" name="content" value="${commentInfo.content}">
                                                <input type="text" hidden name="comment_idx" value="${commentInfo.comment_idx}">
                                                <form:button>수정하기</form:button>
                                            </form:form>
                                        </div>
                                        <div class="add_comment_con">
                                            <form:form modelAttribute="cocCommand" action="${pageContext.request.contextPath}/comment/coc/add">
                                                <label for="user_id">작성자: </label>
                                                <form:input path="c_user_id" value="${loginSession.user_id}" id="user_id" readonly="true"/>
                                                <label for="content">내용: </label>
                                                <form:input path="c_content" id="content"/>
                                                <form:input path="c_parent_board_idx" value="${boardInfo.board_idx}" type="hidden"/>
                                                <form:input path="c_parent_comment" value="${commentInfo.comment_idx}" type="hidden"/>
                                                <form:button>작성하기</form:button>
                                            </form:form>
                                        </div>
                                    </div>
                                    <div>내용: ${commentInfo.content}</div>


                                    <c:forEach items="${cocCommentInfoList}" var="cocCommentInfo">
                                        <c:if test="${cocCommentInfo.parent_comment == commentInfo.comment_idx}">
                                            <div class="cocComment">
                                                <hr>
                                                <div id="${cocCommentInfo.comment_idx}">
                                                    <div>작성자: ${cocCommentInfo.user_id}</div>
                                                    <div>작성일: ${cocCommentInfo.insert_date}</div>
                                                    <div>내용: ${cocCommentInfo.content}</div>
                                                    <div>종아요: ${cocCommentInfo.like}</div>
                                                </div>




                                            </div>

                                        </c:if>
                                    </c:forEach>

                                </div>
                                <hr>

                            </c:forEach>
                        </div>

                    </div>
                </div>

            </div>
        </div>

        <div></div>
    </div>
</div>
</body>
</html>
