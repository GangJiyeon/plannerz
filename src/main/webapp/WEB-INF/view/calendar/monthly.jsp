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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <link rel="stylesheet" href="../Z/css/base2.css">
    <link rel="stylesheet" href="../Z/css/monthly.css">
    <link href='../Z/css/main.css' rel='stylesheet'/>
    <script src="../Z/js/function.js"></script>
    <script src='../Z/js/main.js'></script>
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

        $.ajax(

        )

        document.addEventListener('DOMContentLoaded', function () {
            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl, {
                height: '100%',
                expandRows: true,
                slotMinTime: '08:00',
                slotMaxTime: '20:00',
                headerToolbar: {
                    left: 'prev title next today',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
                },
                initialView: 'dayGridMonth',
                initialDate: '2020-09-12',
                navLinks: true, // can click day/week names to navigate views
                editable: true,
                selectable: true,
                nowIndicator: true,
                dayMaxEvents: true, // allow "more" link when too many events
                locale: 'ko',

                events: [
                    {
                        title: 'All Day Event',
                        start: '2020-09-01',
                    },
                    {
                        title: 'Long Event',
                        start: '2020-09-07',
                        end: '2020-09-10'
                    },
                    {
                        groupId: 999,
                        title: 'Repeating Event',
                        start: '2020-09-16T16:00:00'
                    },
                    {
                        title: 'Dinner',
                        start: '2020-09-12T20:00:00'
                    },
                    {
                        title: 'Birthday Party',
                        start: '2020-09-13T07:00:00'
                    },
                    {
                        title: 'Click for Google',
                        url: 'http://google.com/',
                        start: '2020-09-28'
                    }
                ]
            });

            calendar.render();
        });

    </script>

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
                <div id='calendar-container'>
                    <div id='calendar'></div>
                </div>
            </div>
        </div>
    </div>
    <div class="calendar_sidemenu">

    </div>
</div>
</div>
</body>
</html>
