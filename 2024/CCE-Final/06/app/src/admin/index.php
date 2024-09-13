<?php

require_once "../lib/config.php";

if(!is_login() || !is_admin()) header("Location: ./login.php");
$rows = fetch_multi_row("train_schedule", [], "", "", "", "TIME(departure_time) >= '$departure_time'");
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
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
</head>
<body>
	<div class="head">
		<div class="blue-section">
		  <div class="logo">Let's Gorail</div>
		</div>
	  <BR><BR>

	<div id="container">
		<div id="contents">
			<h2>배차 현황 등록</h2>
			<form action="./upload_process.php" method="post" enctype="multipart/form-data">
				<input type="file" name="file" id="file">
				<button type="submit">Upload Image</button>
			</form>
		</div>
		<div id="contents">
            <div class="section02" id="main001">
            <div style="display: grid; grid-template-columns: repeat(4, 1fr); grid-template-rows: 1fr;">
              <div style="background-color: #4F5AFF; color: white; text-align: center; border: 1px solid gray;cursor:pointer">
                <p><b>배차 현황</b></p>
              </div>
              <div style="background-color: #D4D4D4; color: black; text-align: center; border: 1px solid white;">
                <p>열차 배차</p>
              </div>
              <div style="background-color: #D4D4D4; color: black; text-align: center; border: 1px solid white;">
                <p>앱 푸쉬 등록</p>
              </div>
              <div style="background-color: #D4D4D4; color: black; text-align: center; border: 1px solid white;">
                <p>기타 설정</p>
              </div>
            </div>
			<table id="tableResult" class="tbl_h" style="margin-top:0px;width:1000px;">
                <tr>
                    <th scope="col">구분</th>
                    <th scope="col">열차<br>번호</th>
                    <th scope="col">출발</th>
                    <th scope="col">도착</th>
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