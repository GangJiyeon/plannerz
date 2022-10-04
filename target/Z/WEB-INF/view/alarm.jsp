<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/10
  Time: 11:28 AM
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/basic_theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userInfo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alarm.css">

    <script src="${pageContext.request.contextPath}/js/function.js"></script>

    <style>
        @font-face {
            font-family: 'NEXON Lv2 Gothic';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv2 Gothic.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
    </style>
    <script>
        $(document).ready(function () {

            <c:if test="${alarmInfo.schedule_alarm == true}">
            $("#schedule_off").css('display', 'none');
            </c:if>

            <c:if test="${alarmInfo.schedule_alarm == false}">
            $("#schedule_on").css('display', 'none');
            </c:if>

            <c:if test="${alarmInfo.write_alarm == true}">
            $("#write_off").css('display', 'none');
            </c:if>

            <c:if test="${alarmInfo.write_alarm == false}">
            $("#write_on").css('display', 'none');
            </c:if>

            <c:if test="${alarmInfo.check_alarm == true}">
            $("#check_off").css('display', 'none');
            </c:if>

            <c:if test="${alarmInfo.check_alarm == false}">
            $("#check_on").css('display', 'none');
            </c:if>


            $("#schedule_on").on('click', function () {
                $("#schedule_on").css('display', 'none');
                $("#schedule_off").css('display', 'block');
                $('input[name=schedule_alarm]').attr('value', false);

            })

            $("#schedule_off").on('click', function () {
                $("#schedule_off").css('display', 'none');
                $("#schedule_on").css('display', 'block');
                $('input[name=schedule_alarm]').attr('value', true);

            })

            $("#write_on").on('click', function () {
                $("#write_on").css('display', 'none');
                $("#write_off").css('display', 'inline');
                $('input[name=write_alarm]').attr('value', false);

            })

            $("#write_off").on('click', function () {
                $("#write_off").css('display', 'none');
                $("#write_on").css('display', 'inline');
                $('input[name=write_alarm]').attr('value', true);

            })

            $("#check_on").on('click', function () {
                $("#check_on").css('display', 'none');
                $("#check_off").css('display', 'inline');
                $('input[name=check_alarm]').attr('value', false);

            })

            $("#check_off").on('click', function () {
                $("#check_off").css('display', 'none');
                $("#check_on").css('display', 'inline');
                $('input[name=check_alarm]').attr('value', true);

            })
        });
    </script>
</head>
<script>
</script>
<body>
<div class="contents">
    <%@include file="includes/side_bar.jsp" %>

    <div class="content">
        <div class="real">
            <div class="two_div">
                <%@include file="includes/user_menu.jsp" %>
                <div class="contentArea">
                    <div class="title">alarm</div>
                    <div class="border_blue" id="">

                        <form action="${pageContext.request.contextPath}/alarm/update" method="post">
                            <div class="setting_area">
                                <div class="howToUse">
                                    사용자의 기호에 따라 간편하게 설정을 변경할 수 있습니다.
                                </div>
                                <div class="setting_item">
                                    <div class="setting_title">
                                        일정 안내 SMS 알림
                                    </div>
                                    <div class="fourToone">
                                        <div class="small">
                                            캘린더 내 일정에 등록한 스케줄의 알림을 받을 수 있습니다. <br>
                                            알림시간 설정은 해당 일정을 등록하거나, 수정할 때 설정할 수 있습니다.
                                        </div>
                                        <div class="switch">
                                            <div id="schedule_on">
                                                <i class="bi bi-toggle-on"></i>
                                            </div>
                                            <div id="schedule_off">
                                                <i class="bi bi-toggle-off"></i>
                                            </div>
                                        </div>
                                        <input name="schedule_alarm" value="${alarmInfo.schedule_alarm}"
                                               id="schedule_alarm" hidden>
                                    </div>
                                </div>
                                <div class="setting_item">
                                    <div class="setting_title">
                                        <span>스케줄 설정 SMS 알림</span>
                                    </div>
                                    <div class="">
                                        <div class="small">
                                            <div>
                                                사용자가 정한 시간에 스케줄 작성, 체크관련 알림을 SMS로 전송합니다.
                                            </div>
                                            <div class="optional">
                                                <div class="three_div">
                                                    <div class="center">
                                                        <label for="write_time">스케줄 작성 알림</label>
                                                    </div>
                                                    <div>
                                                        <select name="write_time" id="write_time">
                                                            <c:if test="${alarmInfo.write_time == 8}">
                                                                <option value="08" selected>08</option>
                                                                <option value="09">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                            </c:if>
                                                            <c:if test="${alarmInfo.write_time == 9}">
                                                                <option value="08">08</option>
                                                                <option value="09" selected>09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                            </c:if>
                                                            <c:if test="${alarmInfo.write_time == 10}">
                                                                <option value="08">08</option>
                                                                <option value="09">09</option>
                                                                <option value="10" selected>10</option>
                                                                <option value="11">11</option>
                                                            </c:if>
                                                            <c:if test="${alarmInfo.write_time == 11}">
                                                                <option value="08">08</option>
                                                                <option value="09">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11" selected>11</option>
                                                            </c:if>
                                                            <c:if test="${empty alarmInfo.write_time}">
                                                                <option value="08">08</option>
                                                                <option value="09">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                            </c:if>

                                                        </select>
                                                        :
                                                        <select>
                                                            <option>00</option>
                                                        </select>
                                                    </div>
                                                    <div class="switch">
                                                        <div id="write_on">
                                                            <i class="bi bi-toggle-on"></i>
                                                        </div>
                                                        <div id="write_off">
                                                            <i class="bi bi-toggle-off"></i>
                                                        </div>
                                                    </div>
                                                    <input name="write_alarm" value="${alarmInfo.write_alarm}" hidden>

                                                </div>
                                                <div class="three_div">
                                                    <div class="center">
                                                        <label for="check_time">스케줄 체크 알림</label>
                                                    </div>
                                                    <div>
                                                        <select name="check_time" id="check_time">
                                                            <c:if test="${alarmInfo.check_time == 20}">
                                                                <option value="20" selected>20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                                <option value="00">00</option>
                                                            </c:if>
                                                            <c:if test="${alarmInfo.check_time == 21}">
                                                                <option value="20">20</option>
                                                                <option value="21" selected>21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                                <option value="00">00</option>
                                                            </c:if>
                                                            <c:if test="${alarmInfo.check_time == 22}">
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22" selected>22</option>
                                                                <option value="23">23</option>
                                                                <option value="00">00</option>
                                                            </c:if>
                                                            <c:if test="${alarmInfo.check_time == 23}">
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23" selected>23</option>
                                                                <option value="00">00</option>
                                                            </c:if>
                                                            <c:if test="${alarmInfo.check_time == '00'}">
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                                <option value="00" selected>00</option>
                                                            </c:if>
                                                            <c:if test="${empty alarmInfo.check_time}">
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                                <option value="00" >00</option>
                                                            </c:if>
                                                        </select>
                                                        :
                                                        <select>
                                                            <option>00</option>
                                                        </select>
                                                    </div>

                                                    <div class="switch">
                                                        <div id="check_on">
                                                            <i class="bi bi-toggle-on"></i>
                                                        </div>
                                                        <div id="check_off">
                                                            <i class="bi bi-toggle-off"></i>
                                                        </div>
                                                    </div>
                                                    <input name="check_alarm" value="${alarmInfo.check_alarm}" hidden>

                                                </div>
                                            </div>
                                        </div>
                                        <div></div>
                                    </div>
                                </div>

                            </div>
                            <button type="submit">수정하기</button>
                        </form>

                    </div>


                </div>
            </div>


        </div>
        <div></div>
    </div>
</div>
</body>
</html>
```