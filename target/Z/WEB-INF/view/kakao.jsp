<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/09/06
  Time: 12:41 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>



</head>
<body>




    <input type="button" onClick="friend();" value="친구목록 받아오기"/>
    <script>
        function friend(){
            $.ajax({
                url: "http://kapi/kakao.com/v1/api/talk/friends"
                data:
            })
        }
    </script>
</body>
</html>
