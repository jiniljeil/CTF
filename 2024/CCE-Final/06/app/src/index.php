<?php
require_once "lib/config.php";
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>레츠 고레일-Lets Go Rail</title>
<link rel="stylesheet" type="text/css" href="/static/basic.css">
<link rel="stylesheet" type="text/css" href="/static/main.css">

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

<script>
	function reserve_submit(){
		form = document.getElementById('reserve_form');
		form.submit();
	}
</script>
</head>
<body>
	<div id="header">
		<div class="h_cont">
			<h1><a href="#"><img src="/static/lets_logo.gif" alt="Let's Gorail"></a></h1>
			<div class="gnb">
				<ul class="gnb_list">
					<li><a href="/"><img src="/static/gnb_home.gif" alt="HOME"></a></li>
					<?php if(is_login()) { ?>
                    <li><div class="shwSchMlg1" style="margin-top :2px"><strong>안녕하세요, <?=$_SESSION["name"]?>님</strong></div></li>
                    <?php } else { ?>
                    <li><a href="/login.php"><img src="/static/gnb_login.png" alt="로그인"></a></li>
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
			<div id="contents01">
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
											<input type="text" id="selGoStartDay" name="start" class="txt120" value="<?=isset($_GET["selGoStartDay"])?$_GET["selGoStartDay"]:"2024.9.11"?>" title="출발일" disabled>
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

								<div class="re_site" style="border">
									<ul>
										<li><img src="/static/tab_site11.jpg" id="site11" alt="관광전용열차"></a></li>
										<li><img src="/static/tab_site12.jpg" id="site12" alt="여행 만들기"></a></li>
										<li><img src="/static/tab_site13.jpg" id="site13" alt="렌터카"></a></li>
										<li><img src="/static/tab_site14.jpg" id="site14" alt="자유여행패스"></a></li>
										<li><img src="/static/tab_site15.jpg" id="site15" alt="회의실"></a></li>
										<li><img src="/static/tab_site16.jpg" id="site16" alt="레일텔"></a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="res_tab02">
							<h4 class="tb03">기차여행검색</h4>
						</div>
					</div>
				</div>
				<div class="section02" id="main001">
				    <div class="spot">
				        <div class="spot_img slides2" id="slides2">
				            <ul class="slide" style="overflow: hidden; position: relative; display: block;">
				                <div class="slides_control" style="position: relative; width: 1671px; height: 440px; left: -557px;">
				                    <li class="nums" style="position: absolute; top: 0px; left: 557px; z-index: 5; display: block;"><a><img src="/static/mainbanner.png" width="557" height="440" alt="고레일 추천여행"></a></li>
				                </div>
				            </ul><map name="idea">
				        </div>
				    </div>
				</div>
				<div class="section03" id="main002">
				    <div class="brand_sh">
				        <div class="b_ct slides4" id="slides4">
				            <ul class="slides4" id="main002_1">
				                <div class="slides_control">
				                    <li class="nums"><a>
				                            <div class="img"><img src="/static/main_under.png"></div>
				                        </a></li>
				                </div>
				            </ul>
				        </div>
				    </div>
				    <div class="hot_travel">
				        <div class="slides5" id="slides5">
				            <ul class="slides5">
				                <div class="slides_control">
				                    <li class="nums"><a>
				                            <div class="img"><img src="/static/ProdDisplay_202208011128454870"></div>
				                        </a></li>
				                </div>
				            </ul>
				        </div>
				    </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>