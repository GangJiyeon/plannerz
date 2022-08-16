<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/11
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="../Z/css/base1.css">
    <link rel="stylesheet" href="../Z/css/join.css">
    <script src="../Z/js/function.js"></script>
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
    <div class="headerArea">
        <div class="menu_bar">
            <div></div>
            <div class="circle"></div>
            <div class="circle"></div>
            <div class="circle"></div>
            <div></div>
            <div>
                <button id="go_login">로그인</button>
            </div>
            <div></div>
            <div>
                <button id="go_join">회원가입</button>
            </div>
            <div></div>
        </div>
        <div class="header">
            <div class="logo_wrapper" id="go_home">plannerZ</div>
            <div></div>
            <div></div>
            <div><i class="bi bi-person-fill"></i></div>
            <div></div>
            <div><i class="bi bi-list" id="go_planner"></i></div>
        </div>
    </div>

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
                <div id="step3" class="step true">
                    <div class="square"><span>약관동의</span></div>
                </div>
                <div></div>
                <div id="step4" class="step">
                    <div class="square"><span>회원가입</span></div>
                </div>
            </div>

            <form action="" class="form_border">
                <div class="inputArea" id="step3_content">
                    약관동의
                </div>
                <div>
                    <button>이전으로</button>
                    <button>다음으로</button>
                </div>
            </form>

        </div>
    </div>
</div>

</body>
</html>

