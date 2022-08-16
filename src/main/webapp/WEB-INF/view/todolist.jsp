<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/10
  Time: 11:27 AM
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <link rel="stylesheet" href="../Z/css/base2.css">
    <link rel="stylesheet" href="../Z/css/mypage.css">
    <link rel="stylesheet" href="../Z/css/basic_theme.css">
    <link rel="stylesheet" href="../Z/css/userInfo.css">
    <link rel="stylesheet" href="../Z/css/todolist.css">
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
            <div class="title">todolist</div>
            <div class="onetTothree">
                <div>
                    <div class="border_blue">
                        <div>강지연님의 todolist</div>
                        <div class="project_items">
                            <hr>
                            <div class="project_item">
                                <span>2022년 8월 </span>

                            </div>
                            <div class="project_item">
                                빙그린 몰 기능 완성하기
                            </div>
                            <div class="project_item">
                                식물공장 아두이노 프로젝트
                            </div>
                            <hr>
                            <div class="project_item">
                                프로젝트를 추가하세요
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="border_blue" id="project_contents">
                        <div class="content_title">
                            <div class="title3">포트폴리오에 쓸 내용</div>
                            <div>목표기한: 2022년 8월 31일</div>
                        </div>

                        <div class="bg_w">
                            <div class="title4">목표</div>
                            <div class="item_list">
                                <input type="checkbox" name="" id="" checked disabled>
                                <span> 마라탕 시켜먹기</span>
                            </div>
                            <div class="item_list">
                                <input type="checkbox" name="" id="" checked disabled>
                                <span> 마라탕 시켜먹기</span>
                            </div>
                            <div class="item_list">
                                <input type="checkbox" name="" id="" checked disabled>
                                <span> 마라탕 시켜먹기</span>
                            </div>
                            <div class="item_list">
                                <input type="checkbox" name="" id="" checked disabled>
                                <span> 마라탕 시켜먹기</span>
                            </div>
                            <div class="item_list">
                                <input type="checkbox" name="" id="" checked disabled>
                                <span> 마라탕 시켜먹기</span>
                            </div>
                            <div class="item_list">
                                <input type="checkbox" name="" id="" checked disabled>
                                <span> 마라탕 시켜먹기</span>
                            </div>
                            <div class="item_list">
                                <input type="checkbox" name="" id="" checked disabled>
                                <span> 마라탕 시켜먹기</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div></div>
        </div>
    </div>

</div>
</div>
</body>
</html>
