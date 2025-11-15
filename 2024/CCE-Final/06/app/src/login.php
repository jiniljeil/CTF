<?php
	require_once "lib/config.php";

	if(is_login()) alert("Already Login", "./");

	$require_params = array('memberid', 'password');

	if($_POST){
		foreach ($require_params as $key){
			if(!trim($_POST[$key])){
				alert('Parameter is empty', 'back');
			}

			$$key = trim($_POST[$key]);
		}

		if(!valid_str($memberid, 'memberid')){
			alert('Invalid Value', 'back');
		}
		$pw = md5($password . __SALT__);

		$query = array(
			'id' => $memberid,
		);
		$id_check = fetch_row('member_info', $query);
		if(!$id_check) alert('Login Fail', 'back');

		if($id_check['password'] !== $pw) alert('Login Fail', 'back');

		foreach ($id_check as $key => $value) {
			$_SESSION[$key] = $value;
		}

		alert('Success', './');
		exit;
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
	function login(){
		form = document.getElementById('login_form');
		form.submit();
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
					<li><a href="/login.php"><img src="/static/gnb_login.png" alt="로그인"></a></li>
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
		<!-- container -->
		<div id="contents04">
			<!-- contents -->
            <div class="section02">
                <div class="cont_login">
                    <div class="box_lef">
                        <form method="post" id="login_form" action="/login.php">
                        <fieldset>
                        <legend class="u_hc">고레일 로그인</legend>
                            <div class="log_chg al_lft" id="loginDisplay1" style="display:block;">
                                <div class="login-title" style="font-family: 'Noto Sans KR', sans-serif;">고레일 로그인</div>
                                <ul class="login_mem">
                                       <li>
                                           <label for="txtMember" style="width: 180px;">아이디</label>
                                           <input type="text" id="memberid" name="memberid" class="txt" maxlength="10" title="아이디입력">
                                       </li>
                                       <li>
                                           <label for="txtPwd" style="width: 180px;">비밀번호</label>
                                        <input type="password" name="password" id="password" class="txt" minlength="4" maxlength="20" value="" title="8자리이상 영문 숫자 특수문자">
                                       </li>
                                    <li class="btn_login"><a href="javascript:login();"><img src="/static/btn_login.gif" alt="확인" border="0"></a></li>
                                </ul>
                                <span class="kb_sel"></span>
                                <p class="chkbox"><span class="eml mg_lft0"></label></span>
                                    <span style="position:absolute;right:0;top:-8px;">
                                        <a href="/join.php"><span class="btn_gray_ang" style="padding:5px 5px; cursor:pointer">멤버십 가입</span></a>
                                    </span>
                                </p>
                            </div>
                        </fieldset>
                        </form>
                    </div>

                </div>
            </div>
		</div>
	</div>
</body>
</html>