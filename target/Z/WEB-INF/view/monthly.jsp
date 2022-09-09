<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Calendar.Dto.MonthlyInfo" %>
<%@ page import="java.util.List" %><%--
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/monthly.css">
    <link href='${pageContext.request.contextPath}/css/main.css' rel='stylesheet'/>
    <script src="${pageContext.request.contextPath}/js/function.js"></script>
    <script src='${pageContext.request.contextPath}/js/main.js'></script>
    <script src='${pageContext.request.contextPath}/js/monthly.js'></script>

    <style>
        html, body {
            overflow: hidden; /* don't do scrollbars */
            font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
            font-size: 14px;
        }

        #calendar-container {
            position: absolute;
            top: 0;
            left: 50px;
            right: 0;
            bottom: 0;
        }

        .fc-header-toolbar {
            /*
            the calendar will be butting up against the edges,
            but let's scoot in the header's buttons
            */
            padding-top: 1em;
            padding-left: 1em;
            padding-right: 1em;
        }

    </style>
    <style>
        @font-face {
            font-family: 'NEXON Lv2 Gothic';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv2 Gothic.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
    </style>
    <script>


        document.addEventListener('DOMContentLoaded', function () {
            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl, {
                height: '100%',
                expandRows: true,
                slotMinTime: '08:00',
                slotMaxTime: '20:00',
                headerToolbar: {
                    left: 'prev title next today',
                    right: 'addEventButton dayGridMonth,timeGridWeek,timeGridDay'
                },
                customButtons: {
                    addEventButton: { // 추가한 버튼 설정
                        text: "일정 추가",  // 버튼 내용
                        click: function () { // 버튼 클릭 시 이벤트 추가
                            $("#calendarModal").modal("show"); // modal 나타내기
                        }
                    }

                },
                eventClick: function (info) {
                    let idx = info.event.id;
                    if (confirm("'" + info.event.title + "'일정을 삭제하시겠습니까 ?")) {
                        location.href = "${pageContext.request.contextPath}/monthly/delete?idx=" + idx;
                    }
                },
                eventDrop: function (info) {
                    $("#monthly_idx").prop("value", info.event.id);
                    alert(info.event.id);
                    let idx = info.event.id;

                    $.ajax({
                        url: "${pageContext.request.contextPath}/monthly/item/select",
                        data: "monthly_idx=" + idx,
                        method: "GET",
                        dataType: 'json',
                        success: function (monthlyInfoItem){
                            alert("dho")
                            $("#calendarModalUpdate").modal("show"); // modal 나타내기
                            $("#title2").prop("value", monthlyInfoItem.title);
                        },
                        error: function (){
                            alert("fail");
                        }
                    })




                },
                initialView: 'dayGridMonth',
                navLinks: true, // can click day/week names to navigate views
                editable: true,
                selectable: true,
                nowIndicator: true,
                dayMaxEvents: true, // allow "more" link when too many events
                locale: 'ko',
                events: [
                    <%List<MonthlyInfo> monthlyInfoList = (List<MonthlyInfo>) request.getAttribute("monthlyInfoList");%>
                    <%if (monthlyInfoList != null) {%>
                    <%for (MonthlyInfo nth : monthlyInfoList) {%>
                    {
                        title: '<%= nth.getTitle()%>',
                        start: '<%= nth.getStart_date()%>',
                        end: '<%= nth.getFinish_date()%>',
                        color: '<%= nth.getBg_color()%>',
                        textColor: '<%= nth.getTx_color()%>',
                        id: '<%= nth.getMonthly_idx() %>',
                        alarm: <%= nth.getAlarm_time() %>
                    },
                    <%}
                }%>]

            });

            calendar.render();
        });


    </script>

</head>
<body>
<div class="contents">
    <%@include file="includes/side_bar.jsp" %>


    <div class="content">
        <div class="real">
            <div class="calendar_menu">
                <div id='calendar-container'>
                    <div id='calendar'></div>
                </div>
            </div>
        </div>
    </div>
    <!-- modal 추가 -->
    <div class="modal fade" id="calendarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">일정을 입력하세요.</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form:form action="${pageContext.request.contextPath}/monthly/add" modelAttribute="monthlyCommand">
                    <div class="modal-body">
                        <div class="form-group">
                            <div>
                                <label for="title">일정 내용</label>
                                <form:input path="title" class="form-control" id="title"/>
                            </div>
                            <div>
                                <label for="start_date">시작 날짜</label>
                                <form:input type="datetime-local" path="start_date" class="form-control"
                                            id="start_date"/>

                            </div>
                            <div>
                                <label for="finish_date">종료 날짜</label>
                                <form:input type="datetime-local" path="finish_date" class="form-control"
                                            value="<fmt:formatDate pattern = yyyy-MM-dd'/>" id="finish_date"/>
                            </div>
                            <div>
                                <label for="bg_color">배경색</label>
                                <form:input type="color" path="bg_color" class="form-control" id="bg_color"/>
                            </div>
                            <div>
                                <label for="tx_color">글자색</label>
                                <form:input type="color" path="tx_color" class="form-control" id="tx_color"/>
                            </div>
                            <div>
                                <label for="alarm_time">알림 설정(미입력 시 알림 없음)</label>
                                <form:input type="datetime-local" path="alarm_time" class="form-control"
                                            id="alarm_time"/>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <form:button class="btn btn-warning">추가</form:button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                    id="sprintSettingModalClose">취소
                            </button>
                        </div>

                    </div>
                </form:form>
            </div>
        </div>

        <div class="calendar_sidemenu">

        </div>
    </div>


    <div class="modal fade" id="calendarModalUpdate" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">일정을 입력하세요.</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form:form action="${pageContext.request.contextPath}/monthly/update" modelAttribute="monthlyUpdate">
                    <div class="modal-body">
                        <div class="form-group">
                            <form:input path="update_monthly_idx" type="hidden" id="monthly_idx"/>
                            <div>
                                <label for="title2">일정 내용</label>
                                <form:input path="update_title" class="form-control" id="title2"
                                            value="${monthlyInfoItem.title}"/>
                            </div>
                            <div>
                                <label for="start_date2">시작 날짜</label>
                                <form:input type="datetime-local" path="update_start_date" class="form-control"
                                            value="${monthlyInfoItem.start_date}" id="start_date2"/>

                            </div>
                            <div>
                                <label for="finish_date2">종료 날짜</label>
                                <form:input type="datetime-local" path="update_finish_date" class="form-control"
                                            value="${monthlyInfoItem.finish_date}" id="finish_date2"/>
                            </div>
                            <div>
                                <label for="bg_color2">배경색</label>
                                <form:input type="color" path="update_bg_color" class="form-control" id="bg_color2"
                                            value="${monthlyInfoItem.bg_color}"/>
                            </div>
                            <div>
                                <label for="tx_color2">글자색</label>
                                <form:input type="color" path="update_tx_color" class="form-control" id="tx_color2"
                                            value="${monthlyInfoItem.tx_color}"/>
                            </div>
                            <div>
                                <label for="alarm_time2">알림 설정(미입력 시 알림 없음)</label>
                                <form:input type="datetime-local" path="update_alarm_time" class="form-control"
                                            value="${monthlyInfoItem.alarm_time}"
                                            id="alarm_time2"/>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <form:button class="btn btn-light">추가</form:button>
                            <button type="button" class="btn btn-outline-info" data-dismiss="modal"
                                    id="sprintSettingModalClose">취소
                            </button>
                        </div>

                    </div>
                </form:form>
            </div>
        </div>

        <div class="calendar_sidemenu">

        </div>
    </div>


</div>
</body>
</html>
