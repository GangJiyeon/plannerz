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
                <%@include file="includes/board.jsp"%>
                <div class="title">
                    <div>
                        <c:if test="${board_userInfo.sns == 'none'}">
                            <img src="${pageContext.request.contextPath}/img/user/${board_userInfo.img}" alt="" style="border-radius: 50%">
                        </c:if>
                        <img src="${board_userInfo.img}" alt="" style="border-radius: 50%">
                        <span>${board_userInfo.user_name}</span>
                    </div>
                </div>

                <div class="board">
                    <div class="margin_con">
                        <form:form modelAttribute="boardCommand">
                            <div>
                                <div style="margin-bottom: 10px; padding-bottom: 20px; border-bottom: solid 1px rgb(152, 166, 185);">
                                    <img src="${pageContext.request.contextPath}/img/board/${boardInfo.board_img1}" style="width: 100%; max-width: 600px; max-height: 600px; margin: auto;">
                                </div>

                                <div>
                                    <div style="text-align: right; color: #9b9b9b; font-size: 13px">
                                        <span>${boardInfo.insert_date}</span>
                                            <button type="submit"
                                                    onclick="javascript:form.action='${pageContext.request.contextPath}/board/delete'"
                                                    id="service">삭제하기
                                            </button>

                                            <button type="submit"
                                                    onclick="javascript:form.action='${pageContext.request.contextPath}/board/update'"
                                                    id="logout_btn">수정하기
                                            </button>
                                    </div>
                                    <div style="display: grid; grid-template-columns: 1fr 1.5fr 50px 50px">
                                        <div>
                                            <span>${boardInfo.user_id}</span>
                                        </div>
                                        <div>
                                            <span>${boardInfo.board_title}</span>
                                        </div>
                                        <div style="text-align: right"><i class="bi bi-eye"></i> ${boardInfo.see}</div>
                                        <div style="text-align: right"><i class="bi bi-heart"></i> ${boardInfo.like}</div>
                                    </div>
                                    <div style="display: grid; grid-template-columns: 1fr 1.5fr 50px 50px">
                                        <div></div>
                                        <div>
                                            <span>${boardInfo.board_content}</span>
                                        </div>
                                        <div></div>
                                    </div>


                                </div>


                                <form:input path="board_idx" type="hidden" value="${boardInfo.board_idx}"/>

                                <a href="${pageContext.request.contextPath}/add/board/like?board_idx=${boardInfo.board_idx}">좋아요 추가</a>
                                <a href="${pageContext.request.contextPath}/delete/board/like?board_idx=${boardInfo.board_idx}">좋아요 삭제</a>

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
                                <c:if test="${loginSession.sns !='none'}">
                                    <img src="${loginSession.img}" alt="" style="border-radius: 50%; width: 50px;">
                                </c:if>
                                <img src="${pageContext.request.contextPath}/user/${loginSession.img}" alt="">
                                <form:input path="user_id" value="${loginSession.user_id}" id="user_id" readonly="true" hidden="true"/>
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
                                        <div>내용: ${commentInfo.content}</div>
                                        <a href="${pageContext.request.contextPath}/add/comment/like?comment_idx=${commentInfo.comment_idx}&board_idx=${boardInfo.board_idx}">좋아요 추가</a>
                                        <a href="${pageContext.request.contextPath}/delete/comment/like?comment_idx=${commentInfo.comment_idx}&board_idx=${boardInfo.board_idx}">좋아요 삭제</a>

                                        <div class="comment_btn_area">
                                            <div>
                                                <a href="${pageContext.request.contextPath}/comment/delete?comment_idx=${commentInfo.comment_idx}&board_idx=${commentInfo.board_idx}&parent_idx=${commentInfo.parent_comment}">
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
                                                <input type="text" hidden name="board_idx" value="${commentInfo.board_idx}">
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



                                    <c:forEach items="${cocCommentInfoList}" var="cocCommentInfo">
                                        <c:if test="${cocCommentInfo.parent_comment == commentInfo.comment_idx}">
                                            <div class="cocComment">
                                                <hr>
                                                <div id="${cocCommentInfo.comment_idx}">
                                                    <div>작성자: ${cocCommentInfo.user_id}</div>
                                                    <div>작성일: ${cocCommentInfo.insert_date}</div>
                                                    <div>내용: ${cocCommentInfo.content}</div>
                                                </div>
                                                <div>
                                                    <a href="${pageContext.request.contextPath}/comment/delete?comment_idx=${cocCommentInfo.comment_idx}&board_idx=${commentInfo.board_idx}&parent_idx=${commentInfo.parent_comment}">
                                                        <button type="button" class="delete_comment">삭제</button>
                                                    </a>
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
