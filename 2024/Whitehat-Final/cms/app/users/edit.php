<!DOCTYPE html>
<html lang="en-US" dir="ltr">
<?php
  $path="../";
  include_once($path."head.html");
?>
<body data-spy="scroll" data-target=".onpage-navigation" data-offset="60">
<main>
  <div class="page-loader">
    <div class="loader">Loading...</div>
  </div>
  <?php
    include_once($path."nav.html");
    include_once($path."headimg.html");
  ?>
  
  <div class="main showcase-page">
    <section class="module">
      <div class="container">
        <div class="row" id="blog-post"></div>
      </div>
    </section>
    <?php include_once($path.'footer.html'); ?>
  </div>
  <div class="scroll-up"><a href="#totop"><i class="fa fa-angle-double-up"></i></a></div>
</main>

<!-- JavaScripts -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<?php include_once($path."script.html");?>


<style>
        .thumbnail img {
            max-width: 30%; /* 부모 요소에 맞게 너비 조절, 30%로 설정 */
            max-height: 30%; /* 비율 유지 */
        }
    </style>
<script>
$(document).ready(function() {
    fetch(`/users/profileapi.php`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Error fetching post');
        }
        return response.json();
    })
    .then(post => {
        generateBlogPost(post);
    })
    .catch(error => {
        console.error('Error:', error);
        window.location.href = 'error.html';
    });

    function generateBlogPost(post) {
        const postsContainer = $('#blog-post');
        let filepath = '';
        if (post.filepath != null) {
            filepath = `<div class="post-thumbnail"><a href="#"><img src="${post.filepath}" alt="Blog-post Thumbnail"/></a></div>`;
        }

        const postHtml = `
            <div class="col-sm-8 col-sm-offset-2">
                ${filepath}
                <h4 class="font-alt mb-0">Basic Forms</h4>
                <hr class="divider-w mt-10 mb-20">
                <form class="form" role="form">
                    <div class="form-group">
                        <input class="form-control" type="text" placeholder="Profile Modify" readonly=""/>
                    </div>
                    <div class="form-group">
                        <input class="form-control input-lg" type="text" placeholder="Username" id="user" value="${post.user}"/>
                    </div>
                    <div class="form-group">
                        <input class="form-control input-lg" type="password" placeholder="Password" id="pass" value=""/>
                    </div>
                    <div class="form-group">
                        <input type="file" id="file" name="file">
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-block btn-round btn-b" id="users">Modify</button>
                    </div>
                </form>
            </div>
        `;

        postsContainer.append(postHtml);

        // 버튼 클릭 이벤트 리스너 등록
        $('#users').on('click', handleUserModification);
    }

    function handleUserModification() {
        var user = $('#user').val();
        var pass = $('#pass').val();
        var fileInput = document.getElementById('file'); // 파일 input 요소 가져오기
        var formData = new FormData(); // FormData 객체 생성

        // FormData에 데이터 추가
        formData.append('user', user);
        formData.append('pass', pass);

        // 파일이 선택되었으면 FormData에 추가
        if (fileInput.files.length > 0) {
            formData.append('file', fileInput.files[0]); // 첫 번째 파일 추가
        }

        $.ajax({
            url: '/users/editapi.php',
            type: 'POST',
            data: formData,
            processData: false, // jQuery가 데이터를 처리하지 않도록 설정
            contentType: false, // jQuery가 Content-Type 헤더를 설정하지 않도록 설정
            dataType: 'json',
            success: function(response) {
                if (response.status) { // response.success의 여부로 리디렉션 처리
                    window.location.href = `/users/login.php`; // 성공 후 리디렉션할 페이지
                } else {
                    window.location.href = '/error.html';
                }
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                window.location.href = 'error.html';
            }
        });
    }
});
</script>
</body>
</html>
