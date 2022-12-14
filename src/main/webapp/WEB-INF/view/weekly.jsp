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
  <link rel="stylesheet" href="../Z/css/weekly.css">
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
      <div class="calendar_menu">
        <div class="title">2022??? 8???</div>
        <div class="calendar_btn">
          <button id="go_monthly">?????????</button>
          <button id="go_weekly">?????????</button>
          <button id="go_daily">?????????</button>
        </div>
      </div>

      <div class="div7">
        <div>sun</div><div>mon</div><div>tue</div>
        <div>wed</div><div>thu</div><div>sat</div><div>sun</div>
      </div>
      <div class="calendar">
        <div class="sun">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
        <div class="mon">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
        <div class="tue">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
        <div class="wed">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
        <div class="thu">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
        <div class="fri">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
        <div class="sat">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
      </div>

    </div>
    <div></div>
  </div>
</div>
</body>
</html>
