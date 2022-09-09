<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/10
  Time: 11:28 AM
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
</head>
<script>
    window.kakaoAsyncInit = function() {
        Kakao.Channel.createAddChannelButton({
            container: '#kakao-talk-channel-add-button',
        });
    };

    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = 'https://developers.kakao.com/sdk/js/kakao.channel.min.js';
        fjs.parentNode.insertBefore(js, fjs);
    })(document, 'script', 'kakao-js-sdk');
</script>
<body>
<div class="contents">
    <%@include file="includes/side_bar.jsp"%>

    <div class="content">
        <div class="real">
            <div class="two_div">
                <%@include file="includes/user_menu.jsp"%>
                <div class="contentArea">
                    <div class="title">alarm</div>
                    <div class="border_blue" id="">

                        <div class="setting_area">
                            <div class="howToUse">
                                사용자의 기호에 따라 간편하게 설정을 변경할 수 있습니다.
                            </div>
                            <div class="setting_item">
                                <div class="setting_title">
                                    게시판 알림
                                </div>
                                <div class="fourToone">
                                    <div class="small">내가 쓴 글에 댓글 혹은 좋아요가 추가될 시 사이트네 알림표시 켜기</div>
                                    <div class="switch"><i class="bi bi-toggle-off"></i></div>
                                </div>
                            </div>
                            <div class="setting_item">
                                <div class="setting_title">
                                    <span>카카오톡 알림서비스</span>
                                    <div
                                            id="kakao-talk-channel-add-button"
                                            data-channel-public-id="_hvNxdxj"
                                            data-size="small"
                                            data-support-multiple-densities="true"
                                    ></div>
                                </div>
                                <div class="">
                                    <div class="small">

                                        <div>
                                            사용자가 정한 시간에 스케줄 작성, 체크, 안내 알림메시지를 카카오톡으로 전송합니다.
                                        </div>

                                        <div class="optional">
                                            <div class="three_div">
                                                <div class="center">
                                                    <label for="form_time">스케줄 작성 알림</label>
                                                </div>
                                                <div>
                                                    <input type="time" name="" id="form_time">
                                                </div>
                                                <div class="switch">
                                                    <i class="bi bi-toggle-on"></i>
                                                </div>
                                            </div>
                                            <div class="three_div">
                                                <div class="center">
                                                    <label for="check_time">스케줄 체크 알림</label>
                                                </div>
                                                <div>
                                                    <input type="time" name="" id="check_time">
                                                </div>
                                                <div class="switch">
                                                    <i class="bi bi-toggle-on"></i>
                                                </div>
                                            </div>
                                            <div class="three_div">
                                                <div class="center">
                                                    <label for="schedule_notice">일정 안내 알림</label>
                                                </div>
                                                <div>
                                                    <input type="time" name="" id="schedule_notice">
                                                </div>
                                                <div class="switch">
                                                    <i class="bi bi-toggle-on"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div></div>
                                </div>
                            </div>

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
