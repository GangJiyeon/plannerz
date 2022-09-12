<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/10
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.nio.charset.Charset"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.util.Enumeration"%>
<%@ page import="org.springframework.ui.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String getBirthday="";
    String getName = "";
    String getPhone = "";
%>

<%
    request.setCharacterEncoding("UTF-8");
    String resultCode = request.getParameter("resultCode");
    String resultMsg = request.getParameter("resultMsg");


    // STEP2 에 이어 인증결과가 성공일(resultCode=0000) 경우 STEP2 에서 받은 인증결과로 아래 승인요청 진행

    JSONObject resJson = null;
    if("0000".equals(resultCode)){

        String authRequestUrl = request.getParameter("authRequestUrl");
        String txId = request.getParameter("txId");

        String token = request.getParameter("token");     // 최초 요청시 reservedMsg="isUseToken=Y" 일경우 개인정보SEED 복호화를 위한 토큰값 전달

        JSONParser parser = new JSONParser();
        URL url = new URL(authRequestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        if (conn != null) {
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestMethod("POST");
            conn.setDefaultUseCaches(false);
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setDoOutput(true);

            JSONObject reqJson = new JSONObject();
            reqJson.put("mid", "INIiasTest");    // 부여받은 MID(상점ID) 입력(영업담당자 문의)

            reqJson.put("txId", txId);

            if (conn.getDoOutput()) {
                conn.getOutputStream().write(reqJson.toString().getBytes());
                conn.getOutputStream().flush();
                conn.getOutputStream().close();
            }

            conn.connect();

            if (conn.getResponseCode() == HttpServletResponse.SC_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                resJson = (JSONObject) parser.parse(br);

                br.close();
            }
        }


        String userBirthday = "userBirthday";
        String userName = "userName";
        String userPhone = "userPhone";

        for(Object key : resJson.keySet()){
            if (key.equals(userBirthday)) {
                getBirthday = resJson.get(key).toString();
            } else if (key.equals(userName)) {
                getName = resJson.get(key).toString();
            } else if (key.equals(userPhone)) {
                getPhone = resJson.get(key).toString();
            }
        }


// -------------------- 결과 수신 -------------------------------------------//


    }else{
        out.print("<p>"+resultCode+"</p>");
        out.print("<p>"+resultMsg+"</p>");
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

    <%@include file="./includes/header.jsp"%>

    <div class="form_wrapper">
        <div class="form">
            <div class="title2">회원가입</div>
            <div class="join_step">
                <div id="step1" class="step">
                    <div class="square"><span>본인인증</span></div>
                </div>
                <div></div>
                <div id="step2" class="step true">
                    <div class="square"><span>정보입력</span></div>
                </div>
                <div></div>
                <div id="step3" class="step">
                    <div class="square"><span>약관동의</span></div>
                </div>
                <div></div>
                <div id="step4" class="step">
                    <div class="square"><span>회원가입</span></div>
                </div>
            </div>


            <div></div>


            <div class="inputArea form_border" id="step2_content">

                <form:form action="${pageContext.request.contextPath}/check/id" modelAttribute="idCheck">
                    <div class="div2">
                        <div>
                            <label for="user_id"><spring:message code="user_id"/></label>
                        </div>
                        <div class="div_2">
                            <div>
                                <form:input path="check_user_id" value="${new_userId}"/>
                            </div>
                            <div>
                                <div>
                                    <button type="submit"><spring:message code="check_id"/></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>

                <form:form action="${pageContext.request.contextPath}/join/input" method="post" modelAttribute="JoinCommand">
                <div class="div2">
                    <div>
                        <form:input path="user_id" type="hidden" value="${new_userId}"/>
                        <label for="user_pw"><spring:message code="user_pw"/></label>
                    </div>
                    <div>
                        <form:password path="user_pw"/>
                    </div>
                </div>
                <div class="div2">
                    <div>
                        <label for="pw_check"><spring:message code="check_pw"/></label>
                    </div>
                    <div>
                        <form:password path="pw_check" id="pw_check"/>
                    </div>
                </div>
                <div class="div2">
                    <div>
                        <label for="user_name"><spring:message code="user_name" /></label>
                    </div>
                    <div>
                        <form:input path="user_name" id="user_name" value="<%=getName%>" readonly="true"/>
                    </div>
                </div>

                <div class="div2">
                    <div>
                        <label for="user_birth"><spring:message code="birth"/></label>
                    </div>
                    <div>
                        <form:input path="user_birth" type="date" datetype="" id="user_birth" value="<%=getBirthday%>" readonly="true"/>
                    </div>
                </div>
                <div class="div2">
                    <div>
                        <label for="phone">전화번호</label>
                    </div>
                    <div>
                        <form:input path="phone" type="text" datetype="" id="phone" value="<%=getPhone%>" readonly="true"/>
                    </div>
                </div>

                <div class="div2">
                    <div><label for="job"><spring:message code="job"/> </label></div>
                    <div>
                        <form:select path="job" id="job">
                            <form:option value="무직"></form:option>
                            <form:option value="중고등학생"></form:option>
                            <form:option value="대학생"></form:option>
                            <form:option value="중고등학생"></form:option>
                            <form:option value="마케팅"></form:option>
                        </form:select>
                    </div>
                </div>
                <div class="div2">
                    <div><label for="img"><spring:message code="profile_photo"/></label></div>
                    <div>
                        <form:input path="img" id="img"/>
                    </div>
                </div>


            </div>

            <div>
                <form:button><spring:message code="before"/></form:button>
                <form:button><spring:message code="next"/></form:button>
            </div>
            </form:form>
        </div>

    </div>
</div>
</div>
</body>
</html>
