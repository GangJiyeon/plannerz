<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/11
  Time: 10:22 AM
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
            <div class="title2">회원가입</div>
            <div class="join_step">
                <div id="step1" class="step">
                    <div class="square"><span>본인인증</span></div>
                </div>
                <div></div>
                <div id="step2" class="step">
                    <div class="square"><span>정보입력</span></div>
                </div>
                <div></div>
                <div id="step3" class="step ">
                    <div class="square"><span>약관동의</span></div>
                </div>
                <div></div>
                <div id="step4" class="step true">
                    <div class="square"><span>회원가입</span></div>
                </div>
            </div>

            <form:form method="get">
                <div class="inputArea form_border" id="step4_content">
                    <div>회원가입 완료!</div>
                    <div>
                        <button type="submit" onclick="javascript: form.action='${pageContext.request.contextPath}/login'">로그인하기</button>
                    </div>
                    <div>
                        <button type="submit" onclick="javascript: form.action='${pageContext.request.contextPath}/home'">메인으로 이동</button>
                    </div>
                </div>
            </form:form>

        </div>
    </div>
</div>
</div>

</body>
</html>

