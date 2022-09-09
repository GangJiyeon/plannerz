<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/10
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/status.css">

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
    <%@include file="includes/side_bar.jsp" %>

    <div class="content">
        <div class="real">
            <div class="two_div pr_50">
                <%@include file="includes/user_menu.jsp" %>
                <div class="contentArea">
                    <div class="title">강지연 님의 통계</div>
                    <div class="border_blue he_auto" >
                        <div>
                            <h4>환급챌린지</h4>
                        </div>
                        <hr>
                        <div>45일째 도전중</div>
                        <div>
                            <div class="user_two_div">
                                <div><span>총 환급금:</span></div>
                                <div><span>45000원</span></div>
                            </div>
                            <div class="user_two_div">
                                <div><span>이번달 환급금:</span></div>
                                <div><span>15000원</span></div>
                            </div>

                        </div>

                    </div>
                    <div class="oneToone">
                        <div class="border_blue left">
                            <div>
                                <span>TO DO LIST</span>
                                <hr>
                            </div>
                            <div class="oneSpaceone">
                                <div>진행 중 프로젝트</div>
                                <div>4개</div>
                            </div>
                        </div>
                        <div class="border_blue right">
                            <div>
                                <span>PROJECT</span>
                                <hr>
                            </div>
                            <div class="oneSpaceone">

                            </div>
                        </div>
                    </div>
                    <div class="border_blue he_auto" >
                        <div>
                            <h4>게시판</h4>
                        </div>
                        <hr>
                        <div>45일째 도전중</div>
                        <div>
                            <div class="user_two_div">
                                <div><span>총 환급금:</span></div>
                                <div><span>45000원</span></div>
                            </div>
                            <div class="user_two_div">
                                <div><span>이번달 환급금:</span></div>
                                <div><span>15000원</span></div>
                            </div>

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
