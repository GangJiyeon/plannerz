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
    <%@include file="../includes/side_bar.jsp" %>

    <div class="content">
        <div class="real">
            <div class="two_div">
                <%@include file="../includes/user_menu.jsp" %>
                <div class="contentArea">
                    <div class="title">강지연 님의 통계</div>
                    <div class="border_blue" id="userInfo_area">
                        <div>
                            <div class="user_two_div">
                                <div><span>이름</span></div>
                                <div><span>강지연</span></div>
                            </div>
                            <div class="user_two_div">
                                <div><span>생년월일</span></div>
                                <div><span>2001.06.14</span></div>
                            </div>
                            <div class="user_two_div">
                                <div><span>가입일</span></div>
                                <div><span>2022.08.08</span></div>
                            </div>
                            <div class="user_two_div">
                                <div><span>생년월일</span></div>
                                <div><span>2001.06.14</span></div>
                            </div>
                            <div class="user_two_div">
                                <div><span>전화번호</span></div>
                                <div><span>010-3574-7494</span></div>
                            </div>
                            <div class="user_two_div">
                                <div><span>직업</span></div>
                                <div><span>학생</span></div>
                            </div>
                        </div>
                        <div>
                            <div class="user_two_div">
                                <div><span>연결된 계좌</span></div>
                                <div><span>지연이의 농협통장 </span>
                                    <button>계좌관리하기</button>
                                </div>
                            </div>
                            <div class="user_two_div">
                                <div><span></span></div>
                                <div><span>352-0888-5857-13</span></div>
                            </div>
                            <div class="user_two_div">
                                <div><span></span></div>
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div class="oneToone">
                        <div class="border_blue">
                            <div>
                                <span>board</span>
                                <button> +</button>
                            </div>
                            <div class="oneSpaceone">

                            </div>
                        </div>
                        <div class="border_blue">

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
