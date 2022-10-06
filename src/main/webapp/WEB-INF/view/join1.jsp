<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/11
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="javax.xml.bind.DatatypeConverter" %>
<%@page import="java.security.MessageDigest" %>
<%@page import="java.net.URLEncoder" %>
<%@page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String mid = "INIiasTest";                                    // 부여받은 MID(상점ID) 입력(영업담당자 문의)
    String apiKey = "TGdxb2l3enJDWFRTbTgvREU3MGYwUT09";      // 부여받은 MID 에 대한 apiKey
    String reqSvcCd = "01";
    String mTxId = "mTxId_" + Calendar.getInstance().getTimeInMillis();
    String reservedMsg = "isUseToken=N";                 // 결과조회 응답시 개인정보SEED 암호화 처리 요청

// 등록가맹점 확인
    String plainText1 = mid + mTxId + apiKey;
    MessageDigest authmd = MessageDigest.getInstance("SHA-256");
    authmd.update(plainText1.getBytes("UTF-8"));
    String authHash = DatatypeConverter.printHexBinary(authmd.digest()).toLowerCase();

    String userName = "";           // 사용자 이름
    String userPhone = "01011112222";  // 사용자 핸드폰
    String userBirth = "19800101";         // 사용자 생년월일
    String userHash = "";
    String flgFixedUser = "N";              // 특정사용자 고정시 Y

    if ("Y".equals(flgFixedUser)) {
        String plainText2 = userName + mid + userPhone + mTxId + userBirth + reqSvcCd;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(plainText2.getBytes("UTF-8"));
        userHash = DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
    }
%>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/join1.css">
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
    <script language="javascript">
        function callSa() {
            let window = popupCenter();
            if (window != undefined && window != null) {
                //document.saForm.setAttribute("target", "sa_popup");
                document.saForm.setAttribute("post", "post");
                document.saForm.setAttribute("action", "https://sa.inicis.com/auth");
                document.saForm.submit();
            }
        }

        function popupCenter() {
            let _width = 400;
            let _height = 620;
            var xPos = (document.body.offsetWidth / 2) - (_width / 2); // 가운데 정렬
            xPos += window.screenLeft; // 듀얼 모니터일 때

            return window.open("", "sa_popup", "width=" + _width + ", height=" + _height + ", left=" + xPos + ", menubar=yes, status=yes, titlebar=yes, resizable=yes");
        }
    </script>
</head>
<body>
<div class="fixed">
    <%@include file="./includes/header.jsp" %>

    <div class="form_wrapper">
        <div class="form">

            <div class="title2">회원가입</div>
            <div class="join_step">
                <div id="step1" class="step">
                    <div class="square"><span>본인인증</span></div>
                </div>
                <div></div>
                <div id="step2" class="step">
                    <div class="square" style="background-color: rgb(226 226 226) !important;"><span>정보입력</span></div>
                </div>
                <div></div>
                <div id="step3" class="step">
                    <div class="square" style="background-color: rgb(226 226 226) !important;"><span>약관동의</span></div>
                </div>
                <div></div>
                <div id="step4" class="step">
                    <div class="square" style="background-color: rgb(226 226 226) !important;"><span>회원가입</span></div>
                </div>
            </div>
            <div>

            </div>

            <div class="form_border">
                <div class="sns_join_area">
                    <div style="border-right: solid 1px  rgb(113, 131, 147); padding-right: 30px;">
                        <h5 style="text-align: center">일반 회원가입</h5>
                        <div style="background-color: rgb(242, 244, 247); padding: 10px; margin-bottom: 10px">
                            간편 본인인증 후 정보입력이 필요 <br>
                            남녀노소 누구나 이용가능!
                        </div>
                        <div>
                            <button type="submit" onclick="callSa()" id="identify">본인인증</button>
                        </div>
                        <form name="saForm">
                            <input type="text" name="mid" value="<%=mid %>" hidden>
                            <input type="text" name="reqSvcCd" value="<%=reqSvcCd %>" hidden>
                            <input type="text" name="mTxId" value="<%=mTxId %>" hidden>
                            <input type="text" name="authHash" value="<%=authHash %>" hidden>
                            <input type="text" name="flgFixedUser" value="<%=flgFixedUser %>" hidden>
                            <input type="text" name="userName" value="<%=userName %>" id="userName" hidden>
                            <input type="text" name="userPhone" value="<%=userPhone %>" id="userPhone" hidden>
                            <input type="text" name="userBirth" value="<%=userBirth %>" id="userBirth" hidden>
                            <input type="text" name="userHash" value="<%=userHash %>" hidden>
                            <input type="text" name="reservedMsg" value="<%=reservedMsg %>" hidden>
                            <input type="text" name="directAgency" value="" hidden>
                            <input type="text" name="successUrl" value="http://localhost:8080/Z/view/step2" hidden>
                            <input type="text" name="failUrl" value="http://localhost:8080/Z/join1" hidden>
                            <!-- successUrl/failUrl 은 분리하여도 됩니다. !-->
                        </form>
                    </div>
                    <div style="padding-left: 30px">
                        <h5 style="text-align: center">
                            SMS 회원가입
                        </h5>
                        <div style="background-color: rgb(242, 244, 247); padding: 10px; margin-bottom: 10px;">
                            본인인증이나 추가정보 입력 없이 간편한 가입이 가능!
                            <br>
                            naver 계정이나, kakao 계정이 필요
                        </div>
                        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 10px">

                            <a href="${url}" style="text-decoration: none;">
                                <div class="sns_wrapper" style="border: solid 1px #03c73c;">
                                    <div class="naver">
                                        <img src="${pageContext.request.contextPath}/img/naver.png"/>
                                    </div>
                                    <div style="padding: 17px 20px 0px 20px;">네이버로 시작하기</div>
                                </div>
                            </a>


                            <a href="${reqUrl}" style="text-decoration: none;">
                                <div class="sns_wrapper" style="border: solid 1px #ffe600;;">
                                    <div class="kakao">
                                        <img src="${pageContext.request.contextPath}/img/kakao_talk.svg"/>
                                    </div>
                                    <div style="padding: 17px 20px 0px 20px;">카카오로 시작하기</div>
                                </div>
                            </a>

                        </div>


                    </div>
                </div>


            </div>


        </div>
    </div>
</div>

</body>
</html>

