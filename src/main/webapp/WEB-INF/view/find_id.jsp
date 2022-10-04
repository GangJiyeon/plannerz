<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/10
  Time: 10:40 PM
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/join.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <script src="${pageContext.request.contextPath}/js/function.js"></script>
    <style>
        @font-face {
            font-family: 'NEXON Lv2 Gothic';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv2 Gothic.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        @font-face {
            font-family: 'GangwonEduPowerExtraBoldA';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEduPowerExtraBoldA.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
    </style>
</head>
<body>
<div class="fixed">
    <%@include file="./includes/header.jsp"%>

    <div class="form_wrapper">
        <div class="form">
            <div class="title2">아이디 찾기</div>
            <div class="inputArea success_login">

                <c:if test="${newUserInfo != null}">
                    <c:if test="${none eq enewUserInfo.sns}">
                        <form:form method="get">
                            <div class="welcome">
                                <div>회원님의 아이디는 ${newUserInfo.user_id} 입니다. </div>
                            </div>
                            <div>
                                <button type="submit" onclick="javascript:form.action='/Z/login'" id="service">로그인 하러 가기</button>
                            </div>
                            <div>
                                <button type="submit" onclick="javascript:form.action='/Z/home'" id="logout_btn">홈 화면으로 이동</button>
                            </div>
                            <div>
                                <button type="submit" onclick="javascript:form.action='/Z/view/find/pw'" id="service">비밀번호 찾기</button>
                            </div>
                        </form:form>
                    </c:if>
                    <c:if test="${newUserInfo.sns eq naver}">
                        <div class="welcome">
                            <div>회원님은 ${newUserInfo.sns} 계정 회원입니다.  </div>
                        </div>

                        <a href="https://nid.naver.com/user2/help/idInquiry?m=viewInputUserInfo&token_help=ghJLbcEXMjKLd7W1">
                            <div>
                                <button type="button">네이버 계정 찾으러 가기</button>
                            </div>
                        </a>
                        <div>
                            <button type="submit" onclick="javascript:form.action='/Z/login'" id="service">로그인 하러 가기</button>
                        </div>
                    </c:if>
                    <c:if test="${newUserInfo.sns eq kakao}">
                        <div class="welcome">
                            <div>회원님은 ${newUserInfo.sns} 계정 회원입니다.  </div>
                        </div>
                        <a href="https://accounts.kakao.com/weblogin/find_account_guide?continue=https%3A%2F%2Fcs.kakao.com%2Fsearch%3Fquery%3D%25EC%25B9%25B4%25EC%25B9%25B4%25EC%2598%25A4%25ED%258B%25B0%25EB%25B9%2584%26serviceId%3D52%26categoryId%3D167%26sort%3D#pageFindAccountSelect">
                            <div>
                                <button type="button">카카오 계정 찾으러 가기</button>
                            </div>
                        </a>
                        <div>
                            <button type="submit" onclick="javascript:form.action='/Z/login'" id="service">로그인 하러 가기</button>
                        </div>
                    </c:if>
                </c:if>


                <c:if test="${success_find == false}">
                    <div>가입되지 않는 계정입니다. </div>
                    <form:form method="get">
                        <div>
                            <button type="submit" onclick="javascript:form.action='/Z/home'" id="service">홈 화면으로 이동</button>
                        </div>
                        <div>
                            <button type="submit" onclick="javascript:form.action='/Z/join'" id="service" >회원가입하기</button>
                        </div>
                    </form:form>
                </c:if>


                <c:if test="${success_find == null}" >
                    <div>전화번호를 입력하세요 </div>
                    <form:form action="${pageContext.request.contextPath}/find/id" modelAttribute="userInfo">
                        <form:input path="phone" placeholder="010-1234-5678 형식으로 입력" />
                        <form:button id="service">비밀번호 찾기</form:button>
                    </form:form>
                </c:if>



            </div>
        </div>
    </div>
</div>

</body>
</html>
