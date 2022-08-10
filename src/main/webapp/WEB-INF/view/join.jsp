<%--
  Created by IntelliJ IDEA.
  User: gangjiyeon
  Date: 2022/08/10
  Time: 11:25 AM
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
    <link rel="stylesheet" href="/css/base1.css">
    <link rel="stylesheet" href="/css/join.css">
    <script src="/js/function.js"></script>
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
    <div class="headerArea">
        <div class="menu_bar">
            <div></div>
            <div class="circle"></div>
            <div class="circle"></div>
            <div class="circle"></div>
            <div></div>
            <div><button id="go_login">로그인</button></div>
            <div></div>
            <div><button id="go_join">회원가입</button></div>
            <div></div>
        </div>
        <div class="header">
            <div class="logo_wrapper" id="go_home">plannerZ</div>
            <div></div>
            <div></div>
            <div><i class="bi bi-person-fill"></i></div>
            <div></div>
            <div><i class="bi bi-list" id="go_planner"></i></div>
        </div>
    </div>

    <div class="form_wrapper">
        <div class="form">

            <div class="title2">회원가입</div>
            <div class="join_step">
                <div id="step1" class="step">
                    <div class="square"><span>본인인증</span></div>
                </div>
                <div></div>
                <div id="step2" class="step">
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
            <form action="" >
                <div class="inputArea" id="step1_content">
                    본인인증
                    <div>
                        <button>다음으로</button>
                    </div>
                </div>
            </form>
            <form action="">
                <div class="inputArea" id="step2_content">
                    <div class="div2">
                        <div><label for="member_id">아이디</label></div>
                        <div class="div_2">
                            <div>
                                <input type="text" name="member_id" id="member_id" placeholder="영문자와 숫자로 구성된 8~16자리">
                            </div>
                            <div>
                                <button>아이디 확인</button>
                            </div>
                        </div>
                    </div>
                    <div class="div2">
                        <div><label for="member_pw">비밀번호</label></div>
                        <div><input type="pw" name="member_pw" id="member_pw" placeholder="영문자, 숫자, 특수문자(!, $, *, ^)로 구성된 8~16자리"></div>
                    </div>
                    <div class="div2">
                        <div><label for="member_name">이름</label></div>
                        <div><input type="text" name="member_name" id="member_name" placeholder=""></div>
                    </div>
                    <div class="div2">
                        <div><label for="member_pw">비밀번호</label></div>
                        <div><input type="pw" name="member_pw" id="member_pw" placeholder="영문자, 숫자, 특수문자(!, $, *, ^)로 구성된 8~16자리"></div>
                    </div>
                    <div class="div2">
                        <div><label for="member_name">직업/분야</label></div>
                        <div>
                            <select>
                                <option value="무직">무직</option>
                                <option value="중고등학생">중고등학생</option>
                                <option value="대학생">대학생</option>
                                <option value="중고등학생">중고등학생</option>
                                <option value="마케팅">마케팅</option>
                            </select>
                        </div>
                    </div>
                    <div class="div2">
                        <div><label for="img">프로필 사진</label></div>
                        <div><input type="image" name="img" id="img"></div>
                    </div>
                    <div class="div2">
                        <div><label for="bank">은행</label></div>
                        <div><input type="text" name="bank" id="bank" placeholder=""></div>
                    </div>
                    <div class="div2">
                        <div><label for="account">계좌번호</label></div>
                        <div><input type="text" name="account" id="account"></div>
                    </div>

                </div>
                <div>
                    <button>이전으로</button>
                    <button>다음으로</button>
                </div>
            </form>
            <form action="" >
                <div class="inputArea" id="step3_content">
                    약관동의
                </div>
                <div>
                    <button>이전으로</button>
                    <button>다음으로</button>
                </div>
            </form>
            <form action="" >
                <div class="inputArea" id="step1_content">
                    <div>회원가입 완료!</div>
                    <div><button>plannerZ 이용하기</button></div>
                    <div><button>메인으로 이동</button></div>
                </div>
            </form>

        </div>
    </div>
</div>

</body>
</html>
