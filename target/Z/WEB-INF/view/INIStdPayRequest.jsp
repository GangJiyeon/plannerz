<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="com.inicis.std.util.SignatureUtil" %>
<%@page import="java.util.*" %>
<%

    /*
        //*** 위변조 방지체크를 signature 생성 ***

            oid, price, timestamp 3개의 키와 값을

            key=value 형식으로 하여 '&'로 연결한 하여 SHA-256 Hash로 생성 된값

            ex) oid=INIpayTest_1432813606995&price=819000&timestamp=2012-02-01 09:19:04.004


             * key기준 알파벳 정렬

             * timestamp는 반드시 signature생성에 사용한 timestamp 값을 timestamp input에 그대로 사용하여야함
    */

    //############################################
    // 1.전문 필드 값 설정(***가맹점 개발수정***)
    //############################################

    // 여기에 설정된 값은 Form 필드에 동일한 값으로 설정
    String mid = "INIpayTest";        // 가맹점 ID(가맹점 수정후 고정)

    //인증
    String signKey = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";    // 가맹점에 제공된 웹 표준 사인키(가맹점 수정후 고정)
    String timestamp = SignatureUtil.getTimestamp();            // util에 의해서 자동생성

    String oid = mid + "_" + SignatureUtil.getTimestamp();    // 가맹점 주문번호(가맹점에서 직접 설정)
    String price = "100";                                                    // 상품가격(특수기호 제외, 가맹점에서 직접 설정)

    String cardNoInterestQuota = "11-2:3:,34-5:12,14-6:12:24,12-12:36,06-9:12,01-3:4";        // 카드 무이자 여부 설정(가맹점에서 직접 설정)
    String cardQuotaBase = "2:3:4:5:6:11:12:24:36";        // 가맹점에서 사용할 할부 개월수 설정

    //###############################################
    // 2. 가맹점 확인을 위한 signKey를 해시값으로 변경 (SHA-256방식 사용)
    //###############################################
    String mKey = SignatureUtil.hash(signKey, "SHA-256");

    //###############################################
    // 2.signature 생성
    //###############################################
    Map<String, String> signParam = new HashMap<String, String>();

    signParam.put("oid", oid);                    // 필수
    signParam.put("price", price);                // 필수
    signParam.put("timestamp", timestamp);        // 필수

    // signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
    String signature = SignatureUtil.makeSignature(signParam);


    /* 기타 */
    String siteDomain = "http://localhost:8080/Z"; //가맹점 도메인 입력
    // 페이지 URL에서 고정된 부분을 적는다.
    // Ex) returnURL이 http://localhost:8080INIpayStdSample/INIStdPayReturn.jsp 라면
    // http://localhost:8080/INIpayStdSample 까지만 기입한다.

%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        body {
            background-color: #efefef;
        }

        body, tr, td {
            font-size: 9pt;
            font-family: 굴림, verdana;
            color: #433F37;
            line-height: 19px;
        }

        table, img {
            border: none
        }

		.btn_op{
			background-color: #9ea2bb;
			border: none;
			color: white;
			width: 100%;
			padding: 10px;
			border-radius: 3px;
		}
		input{
			margin-top: 5px;
			padding: 10px;
			width: 100%;
		}
    </style>

    <!-- 이니시스 표준결제 js -->
    <!--
      연동시 유의 사항!!
      1) 테스트 URL(stgstdpay.inicis.com) - 샘플에 제공된 테스트 MID 전용으로 실제 가맹점 MID 사용 시 에러가 발생 할 수 있습니다.
      2) 상용 URL(stdpay.inicis.com) - 실제 가맹점 MID 로 테스트 및 오픈 시 해당 URL 변경하여 사용합니다.
      3) 가맹점의 URL이 http: 인경우 js URL도 https://stgstdpay.inicis.com/stdjs/INIStdPay.js 로 변경합니다.
      4) 가맹점에서 사용하는 케릭터셋이 EUC-KR 일 경우 charset="UTF-8"로 UTF-8 일 경우 charset="UTF-8"로 설정합니다.
    -->

    <!-- 상용 JS(가맹점 MID 변경 시 주석 해제, 테스트용 JS 주석 처리 필수!) -->
    <!--script language="javascript" type="text/javascript" src="https://stdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script-->

    <!-- 테스트 JS(샘플에 제공된 테스트 MID 전용) -->
    <script language="javascript" type="text/javascript" src="https://stgstdpay.inicis.com/stdjs/INIStdPay.js"
            charset="UTF-8"></script>

</head>
<body bgcolor="#FFFFFF" text="#242424" leftmargin=0 topmargin=15 marginwidth=0 marginheight=0 bottommargin=0
      rightmargin=0>

<div style="padding:10px;width:100%;font-size:13px;color: #ffffff; background-color: #9ea2bb;text-align: center">
    PLANNERZ 챌린지 결제 페이지
</div>

<table width="650" border="0" cellspacing="0" cellpadding="0" style="padding:10px;" align="center">
    <tr>

        <td bgcolor="#9ea2bb" align="center" style="padding:10px">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="padding:20px">

                <tr>
                    <td>
                        <div style="text-align: center">
                            <h1>
                                PLANNERZ 챌린지 결제하기
                            </h1>
                        </div>

                        <hr>
                    </td>
                </tr>
                <tr>

                    <td>
                        <table>
                            <td style="text-align:left;">

                                <form id="SendPayForm_id" name="" method="POST">

                                    <input hidden style="width:100%;" name="version" value="1.0">
                                    <input hidden style="width:100%;" name="mid" value="<%=mid%>">
                                    <input hidden style="width:100%;" name="goodname" value="plannerz 챌린지" readonly>
                                    <input hidden style="width:100%;" name="oid" value="<%=oid%>">
                                    <div>총 한달간 환급받을 금액을 설정하세요</div>
                                    <div>(실제로 결제가 되었다가 23시 이후 결제 금액이 환불됩니다.)</div>
                                    <input style="width:100%;" name="price" value="<%=price%>">
                                    <input hidden style="width:100%;" name="currency" value="WON">
                                    <input hidden style="width:100%;" name="buyername" value="홍길동">
                                    <input hidden style="width:100%;" name="buyertel" value="010-1234-5678">
                                    <input hidden style="width:100%;" name="buyeremail" value="test@inicis.com">
                                    <input hidden type="hidden" style="width:100%;" name="timestamp"
                                           value="<%=timestamp %>">
                                    <input hidden type="hidden" style="width:100%;" name="signature"
                                           value="<%=signature%>">
                                    <input hidden style="width:100%;" name="returnUrl"
                                           value="<%=siteDomain%>/INIStdPayReturn">
                                    <input hidden type="hidden" name="mKey" value="<%=mKey%>">
                                    <input hidden style="width:100%;" name="gopaymethod" value="Card">
                                    <input hidden style="width:100%;" name="offerPeriod" value="20151001-20151231">
                                    <input hidden style="width:100%;" name="acceptmethod"
                                           value="CARDPOINT:HPP(1):no_receipt:va_receipt:below1000">
                                    <input style="width:100%;" name="languageView" value="" hidden>
                                    <input style="width:100%;" name="charset" value="" hidden>
                                    <input style="width:100%;" name="payViewType" value="" hidden>
                                    <input style="width:100%;" name="closeUrl" value="<%=siteDomain%>/close" hidden>
                                    <input style="width:100%;" name="popupUrl" value="<%=siteDomain%>/popup" hidden>
                                    <input style="width:100%;" name="quotabase" value="<%=cardQuotaBase%>" hidden>
                                    <input style="width:100%;" name="ini_onlycardcode" value="" hidden>
                                    <input style="width:100%;" name="ini_cardcode" value="" hidden>
                                    <input style="width:100%;" name="ansim_quota" value="" hidden>
                                    <input style="width:100%;" name="INIregno" value="" hidden>
                                    <input style="width:100%;" name="merchantData" value="" hidden>
                                </form>
                            </td>
                        </table>
                    </td>
                </tr>
				<tr>
					<td>
                        <hr style="margin: 15px 0px">
						<!-- 결제요청 -->
						<button onclick="INIStdPay.pay('SendPayForm_id')" class="btn_op">결제요청</button>
					</td>
				</tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>