<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/10
  Time: 11:29 AM
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
    <link rel="stylesheet" href="../Z/css/base2.css">
    <link rel="stylesheet" href="../Z/css/mypage.css">
    <link rel="stylesheet" href="../Z/css/basic_theme.css">
    <link rel="stylesheet" href="../Z/css/userInfo.css">
    <!-- Libs CSS -->
    <link rel="stylesheet" href="../Z/css/libs.bundle.css"/>

    <!-- Theme CSS -->
    <link rel="stylesheet" href="../Z/css/theme.bundle.css"/>
    <script src="../Z/js/function.js"></script>

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
    <div class="side_bar">
        <div><i class="bi bi-person-circle font_30" id="go_userinfo"></i></div>
        <div class="menu">
            <div><i class="bi bi-calendar-check space" id="go_calendar"></i></div>
            <div><i class="bi bi-check2-square space" id="go_todolist"></i></div>
            <div><i class="bi bi-list-task space" id="go_project"></i></div>
            <div><i class="bi bi-people space" id="go_board"></i></div>

            <div class="bottom">
                <div><i class="bi bi-house space" id="go_home"></i></div>
                <div><i class="bi bi-bell-fill space" id="go_alram"></i></div>
                <div><i class="bi bi-question-circle-fill space" id="go_faq"></i></div>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="real">
            <div class="two_div">
                <div class="menu_bar">
                    <div id="userImgWrapper">
                        <img src="../Z/img/2021_Login_with_naver_guidelines_Kr/btnW_아이콘원형.png" alt="">
                    </div>
                    <div id="user_info"><span>user's info</span></div>
                    <div id="go_alarm"><span>alram</span></div>
                    <div id="go_setting"><span>setting</span></div>
                    <div id="go_board2"><span>board</span></div>
                    <hr>
                    <div id="go_status"><span>status</span></div>
                    <hr>
                    <div><span>logout</span></div>
                    <div><span>회원탈퇴</span></div>
                </div>

                <div class="contentArea">
                    <div class="title">
                        <spring:message code="userInfo_title">
                            <spring:argument value="${loginSession.user_name}"/>
                        </spring:message>
                    </div>

                    <div class="border_blue">
                        <div class="col-12 col-md-9 col-lg-8 offset-lg-1">
                            <form>
                                <div class="row">
                                    <div class="col-12 col-md-6">
                                        <div class="form-group">
                                            <label class="form-label" for="user_name">
                                                <spring:message code="user_name"/>
                                            </label>
                                            <input class="form-control form-control-sm" id="user_name" type="text"
                                                   value="${loginSession.user_name}" required="">
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-6">
                                        <div class="form-group">
                                            <label class="form-label" for="birth">
                                                <spring:message code="birth"/>
                                            </label>
                                            <input class="form-control form-control-sm" id="birth" name="birth"
                                                   type="datetime" value="" required="">
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-6">
                                        <div class="form-group">
                                            <label class="form-label" for="phone">
                                                <spring:message code="phone"/>
                                            </label>
                                            <input class="form-control form-control-sm" id="phone" name="phone" type="text"
                                                   value="" required="">
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-6">
                                        <div class="form-group">
                                            <label class="form-label" for="user_id">
                                                <spring:message code="user_id"/>
                                            </label>
                                            <input class="form-control form-control-sm" id="user_id" type="text"
                                                   value="${loginSession.user_id}" required="">
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-6">
                                        <div class="form-group">
                                            <label class="form-label" for="user_pw">
                                                <spring:message code="user_pw"/>
                                            </label>
                                            <input class="form-control form-control-sm" id="user_pw" type="password"
                                                   value="" required="">
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label class="form-label" for="accountEmail">
                                                <spring:message code="email"/>
                                            </label>
                                            <input class="form-control form-control-sm" id="accountEmail" type="email"
                                                   placeholder="Email Address *" value="user@email.com" required="">
                                        </div>
                                    </div>


                                    <div class="col-12 col-lg-6">
                                        <div class="form-group mb-8">
                                            <label class="form-label"><spring:message code="gendar"/></label>
                                            <div>
                                                <input class="btn-check" type="radio" name="gender" id="male"
                                                       checked="">
                                                <label class="btn btn-sm btn-outline-border" for="male">
                                                    <spring:message code="male"/>
                                                </label>
                                                <input class="btn-check" type="radio" name="gender" id="female">
                                                <label class="btn btn-sm btn-outline-border" for="female">
                                                    <spring:message code="female"/>
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <button class="btn btn-dark" type="submit">
                                            <spring:message code="save_btn"/>
                                        </button>
                                    </div>
                                </div>
                            </form>

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
