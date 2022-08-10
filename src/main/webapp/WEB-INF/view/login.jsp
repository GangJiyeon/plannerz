<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/10
  Time: 11:26 AM
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
  <link rel="stylesheet" href="../Z/css/base1.css">
  <link rel="stylesheet" href="../Z/css/join.css">
  <link rel="stylesheet" href="../Z/css/login.css">
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
      <div><button id="go_login">로그인</button></div>
      <div></div>
      <div><button id="go_join">회원가입</button></div>
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
      <div class="title2">로그인</div>
      <div class="inputArea">
        <form action="">
          <div>
            <input type="text" placeholder="아이디를 입력하세요">
          </div>
          <div>
            <input type="pw" placeholder="비밀번호 입력하세요">
          </div>
          <div>
            <input type="checkbox" name="remember_id" id="remember_id">
            <label for="remember_id">아이디 기억하기</label>
          </div>
          <div class="login_menu">
            <div><a href="http://">아이디 찾기</a></div>
            <div> | </div>
            <div><a href="http://">비밀번호 찾기</a></div>
            <div> | </div>
            <div><a href="http://">회원가입 하기</a></div>
          </div>
          <div >
            <div><button>로그인</button></div>
            <div class="naver_login_wrapper"><img id="naver_login" src="/img/2021_Login_with_naver_guidelines_Kr/btnG_완성형.png" alt=""></div>
          </div>
        </form>

      </div>
    </div>
  </div>
</div>

</body>
</html>