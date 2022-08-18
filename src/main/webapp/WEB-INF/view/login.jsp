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
            <div class="title2">로그인</div>
            <div class="inputArea success_login">

                <c:if test="${loginSession != null}">
                    <form:form method="get">
                        <div class="welcome">
                            <div>${loginSession.user_name}님 반갑습니다!</div>
                            <div><img src="https://cr3.shopping.naver.com/bridge/searchGate?query=빵빠레&bt=-1&nv_mid=31177622472&cat_id=50013240&h=571e6ebe1d019e6a8470394c801d0a51538726af&t=L6NPX366&frm=NVSCPRO"></div>
                        </div>
                        <div>
                            <button type="submit" onclick="javascript:form.action='/Z/userInfo'" id="service">서비스 이용하기</button>
                        </div>
                        <div>
                            <button type="submit" onclick="javascript:form.action='/Z/logout'" id="logout_btn">로그아웃</button>
                        </div>
                    </form:form>

                </c:if>
                <c:if test="${loginSession == null}">
                    

                    <form:form action="${pageContext.request.contextPath}/login/self" modelAttribute="loginCommand">
                        <div>
                            <form:input path="user_id" placeholder="아이디를 입력하세요"/>
                        </div>
                        <div>
                            <form:password path="user_pw" placeholder="비밀번호 입력하세요"/>
                        </div>
                        <div class="re_id">
                            <input type="checkbox" name="remember_id" id="remember_id">
                            <label for="remember_id">아이디 기억하기</label>
                        </div>
                        <div class="login_menu">
                            <div></div>
                            <div><a href="http://">아이디 찾기</a></div>
                            <div> |</div>
                            <div><a href="http://">비밀번호 찾기</a></div>
                            <div> |</div>
                            <div><a href="http://">회원가입 하기</a></div>
                        </div>
                        <div>
                            <div>
                                <button type="submit" id="login_btn">로그인</button>
                            </div>
                            <div>
                                <div id="naver_id_login" style="text-align:center">
                                    <a href="${url}">
                                        <img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/>
                                    </a>
                                </div>
                            </div>
                            <div class="naver_login_wrapper">
                                <img id="naver_login"
                                     src="../Z/img/2021_Login_with_naver_guidelines_Kr/btnG_완성형.png" alt="">
                            </div>
                        </div>
                    </form:form>



                </c:if>





            </div>
        </div>
    </div>
</div>

</body>
</html>