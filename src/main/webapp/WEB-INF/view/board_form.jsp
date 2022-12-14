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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/basic_theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userInfo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base2.css">
    <link  href="/path/to/cropper.css" rel="stylesheet">
    <script src="/path/to/cropper.js"></script>
    <script src="${pageContext.request.contextPath}/js/function.js"></script>
    <script src="${pageContext.request.contextPath}/js/board.js"></script>
    <script>
        $(document).ready(function () {

        });
    </script>
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
    <%@include file="includes/side_bar.jsp"%>
    <div class="content margin_40">
        <div class="real">

            <div class="contentArea">
                <%@include file="includes/board.jsp"%>
                <div class="title">
                    <div>
                        ????????? ????????????
                    </div>


                </div>
                <div class="margin_con">
                    <form:form method="post" action="${pageContext.request.contextPath}/board/form.do"  enctype="multipart/form-data" modelAttribute="boardCommand">
                        <div style="margin-bottom: 5px;">
                            <label>
                                <spring:message code="board.title" />
                            </label>
                            <form:input path="board_title"/>
                            <form:errors path="board_title"/>
                        </div>
                        <div style="margin-bottom: 5px;">
                            <label>
                                <spring:message code="board.content" />
                            </label>
                            <form:input path="board_content"/>
                            <form:errors path="board_content" />
                        </div>
                        <div style="margin-bottom: 5px;">
                            <label>
                                <spring:message code="board.img" />
                            </label>
                            <input type="file" name="file" multiple="multiple" id="board_img"/>
                        </div>
                        <form:button class="btn_css">
                            <spring:message code="board.form"/>
                        </form:button>
                    </form:form>

                    <hr>



                </div>

            </div>
        </div>

        <div></div>
    </div>
</div>
</body>
</html>
