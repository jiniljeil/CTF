<?php

require_once "lib/config.php";

if(!is_login()) header("Location: /login.php");

if ($_SERVER["method"] == "POST") {
	if (isset($_POST["selGoHour"])) {
		$departure_time = (int)$_POST["selGoHour"].":00:00";
	} else {
		$departure_time = "12:00:00";
	}

	$rows = fetch_multi_row("train_schedule", [], "", "", "", "TIME(departure_time) >= '$departure_time'");
}
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>레츠 고레일-Lets Go Rail</title>
<link rel="stylesheet" type="text/css" href="/static/basic.css">
<link rel="stylesheet" type="text/css" href="/static/main.css">

<script>
	function reserve_submit(){
		form = document.getElementById('reserve_form');
		form.submit();
	}

	function reserve(idx){
	if(idx){
            //location.href="/reserve.php?idx=" + idx;
        }
	}
</script>
<style>
	.head {
		display: flex;
		flex-direction: column;
		height: 120px;
	  }
	
	  .blue-section {
		background-color: #26314A;
		height: 50px;
	  }
	
	  .logo {
		color: white;
		padding: 15px;
		font-size: 25px;
		font-weight: bold;
		font-family: 'Noto Sans KR', sans-serif;
	  }
	
	  .ivory-section {
		background-color: white;
		display: flex;
		justify-content: center;
		align-items: center;
		height: 70px; 
	  }
	
	  .menu {
		list-style-type: none;
		margin: 0;
		padding: 0;
		display: flex;
		justify-content: center;
		align-items: center;
	  }
	
	  .menu li {
		margin-right: 10px;
		border-right: 1px solid #ccc;
		padding-right: 10px;
		width: 300px;
		font-size: 30px;
		font-weight: bold;
		text-align center;
		font-family: 'Noto Sans KR', sans-serif;

	  }

	  .menu li:last-child {
		border-right: none; 
		padding-right: 0; 
	  }
	
	  .menu li a {
		
		padding: 10px;
		text-decoration: none;
		color: #333;
		font-weight: bold;
		text-align center;
		display: flex; align-items: center; justify-content: center;
	  }
	
	  .menu li a:hover {
		background-color: #ddd;
	  }
</style>
</head>
<body>
	<div id="header">
		<div class="h_cont">
			<h1><a href="#" onclick="return m_main_prd_link()"><img src="/static/lets_logo.gif" alt="Let's Gorail"></a></h1>
			<div class="gnb">
				<ul class="gnb_list">
					<li><a href="/"><img src="/static/gnb_home.gif" alt="HOME"></a></li>
					<?php if(is_login()) { ?>
                    <li><div class="shwSchMlg1" style="margin-top :2px"><strong>안녕하세요, <?=$_SESSION["name"]?>님</strong></div></li>
                    <?php } else { ?>
                    <li><a href="/login"><img src="/static/gnb_login.png" alt="로그인"></a></li>
                    <?php } ?>
				</ul>
			</div>
		</div>
	</div>

	<div class="head">
		<div class="blue-section">
		  <div class="logo">Let's Gorail</div>
		</div>
		<div class="ivory-section">
		  <ul class="menu">
			<li><a href="#">승차권</a></li>
			<li><a href="#">여행 상품</a></li>
			<li><a href="#">고객 센터</a></li>
			<li><a href="#">이용 안내</a></li>
		  </ul>
		</div>
	  </div>
	  <BR><BR>

	<div id="container">
		<div id="contents">
            <div class="section01">
                <div class="reserv">
                    <div class="res_tab01">
                        <h4 class="tb01 selected">승차권간편예매<a>승차권간편예매</a></h4>
                        <div class="res_cont" id="res_cont_tab01" style="display:block">
                            <div class="tk_box">
                                <fieldset class="box01">
                                <legend>승차권 예매하기</legend>
                                    <ul class="cont_lin">
                                    <li><label for="txtGoStart">출발역</label>
                                        <input type="text" id="txtGoStart" name="txtGoStart" class="txt120" value="서울" title="출발역" autocomplete="off" style="ime-mode:active" disabled>

                                    </li>
                                    <li><label for="txtGoEnd">도착역</label>
                                        <input type="text" id="txtGoEnd" name="txtGoEnd" class="txt120" value="부산" title="도착역" autocomplete="off" style="ime-mode:active" disabled>

                                    </ul>
                                    <ul>
                                    <li>
                                        <label for="selGoStartDay">출발일</label>
                                        <input type="text" id="selGoStartDay" name="start" class="txt120" value="2024.9.12" title="출발일" disabled>
                                    </li>
                                    <form id="reserve_form" method="post" action="/search.php">
                                    <li><label for="time">시간</label>
                                        <select id="time" class="select" name="selGoHour" title="출발일시:시">
                                            <option value="12">12 (오후00)</option>
                                            <option value="14">14 (오후02)</option>
                                            <option value="16">16 (오후04)</option>
                                            <option value="18">18 (오후06)</option>
                                        </select>시
                                    </li>
                                    </form>
                                    <li>
                                        <label for="people_num">인원</label>
                                        <input type="text" id="people_num" name="txtPsgFlg_1" class="txt120" value="어른 1명" title="인원" disabled>
                                    </li>
                                    </ul>
                                    <p class="btn_res"><a onclick="reserve_submit();" style="cursor:pointer"><img src="/static/btn_reserve.gif" alt="승차권예매" border="0"></a></p>
                                </fieldset>
                            </div>
                            </form>
                        </div>
                    </div>
                    <div class="res_tab02">
                        <h4 class="tb03">기차여행검색</h4>
                    </div>
                </div>
            </div>
            <div class="section02" id="main001">
			<table id="tableResult" class="tbl_h" style="margin-top:0px;width:1000px;">
                <tr>
                    <th scope="col">구분</th>
                    <th scope="col">열차<br>번호</th>
                    <th scope="col">출발</th>
                    <th scope="col">도착</th>
                    <th scope="col">일반실</th>
                    <th scope="col">운임<br>요금</th>
                    <th scope="col">소요<br>시간</th>
                </tr>
                <tbody>
					<?php for($i=0; $i<count($rows); $i++){ ?>
                    <tr class="">
                        <td><font>직통</font></td>
                        <td class="bdl_on bg-00" title="KTX">
                            <span class="point-00" style="text-decoration:none; letter-spacing:-1px;">GTX</span><br>
                            <a><span class="point-00"><?=$rows[$i]["train_number"]?></span></a>
                        </td>
                        <td>서울<br><?=strftime("%H:%M", $rows[$i]["departure_time"])?></td>
                        <td>부산<br><?=strftime("%H:%M", $rows[$i]["arrival_time"])?></td>
                        <td><a onclick="reserve(<?=$rows[$i]["schedule_id"]?>)"><img name="btnRsv2_0" src="/static/icon_apm_bl.gif" alt="예약하기" style="cursor: pointer;"></a></td>
                        <td class="guide365"><div class="shwSchMlg1"><strong>59,400원</strong></div></td>
                        <td><?=gap_time($rows[$i]["departure_time"], $rows[$i]["arrival_time"])?></td>
                    </tr>
                    <?php } ?>
                </tbody>
            </table>
            </div>
		</div>
	</div>
</body>
</html>