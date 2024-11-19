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

        <!-- 섹션 자리 -->
        <section class="module">
          <div class="container">
            <div class="row">
              <div class="col-sm-8 col-sm-offset-2">
                <h4 class="font-alt mb-0">Basic Forms</h4>
                <hr class="divider-w mt-10 mb-20">
                <form class="form" role="form">
                  <div class="form-group">
                  <select name="bname" id="bname">
                  </select>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="text" placeholder="Board Create" readonly=""/>
                  </div>
                  <div class="form-group">
                    <input class="form-control input-lg" type="text" placeholder="Title" id="title"/>
                  </div>
                  <div class="form-group">
                  <textarea class="form-control" rows="7" placeholder="Content" id="content"></textarea>
                  </div>
                  <div class="form-group">
                    <button type="button" class="btn btn-block btn-round btn-b" id="board">Register</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </section>
        <?php
        include_once($path.'footer.html');
        ?>
      </div>
      <div class="scroll-up"><a href="#totop"><i class="fa fa-angle-double-up"></i></a></div>
    </main>
    <!--  
    JavaScripts
    =============================================
    -->
    <script src="/assets/lib/jquery/dist/jquery.js"></script>
    <script src="/assets/lib/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="/assets/lib/wow/dist/wow.js"></script>
    <script src="/assets/lib/jquery.mb.ytplayer/dist/jquery.mb.YTPlayer.js"></script>
    <script src="/assets/lib/isotope/dist/isotope.pkgd.js"></script>
    <script src="/assets/lib/imagesloaded/imagesloaded.pkgd.js"></script>
    <script src="/assets/lib/flexslider/jquery.flexslider.js"></script>
    <script src="/assets/lib/owl.carousel/dist/owl.carousel.min.js"></script>
    <script src="/assets/lib/smoothscroll.js"></script>
    <script src="/assets/lib/magnific-popup/dist/jquery.magnific-popup.js"></script>
    <script src="/assets/lib/simple-text-rotator/jquery.simple-text-rotator.min.js"></script>
    <script src="/assets/js/plugins.js"></script>
    <script src="/assets/js/main.js"></script>

    <script>
        $(document).ready(function(){


          $.ajax({
            url: '/board/boardlistapi.php', // 여기에 API 엔드포인트 URL 입력
            method: 'GET',
            dataType: 'json',
            success: function(data) {
                data.forEach(function(item) {
                    $("#bname").append(
                        "<option value="+item.bname+">"+item.bname+"</option>"
                    );
                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error fetching menu items:', textStatus, errorThrown);
            }
        });

        $('#board').on('click', function() {
          var bname = $('#bname').val();
          var title = $('#title').val();
          var content = $('#content').val();
          // var fileInput = document.getElementById('file'); // 파일 input 요소 가져오기
          // var formData = new FormData(); // FormData 객체 생성

          // FormData에 데이터 추가
          // formData.append('bname', bname);
          // formData.append('title', title);
          // formData.append('content', content);

          // 파일이 선택되었으면 FormData에 추가
          // if (fileInput.files.length > 0) {
          //     formData.append('file', fileInput.files[0]); // 첫 번째 파일 추가
          // }

          $.ajax({
              url: '/board/createapi.php',
              type: 'POST',
              data: {
                bname:bname,
                title:title,
                content:content
              },
              dataType: 'json',
              success: function(response) {
                  if (response.status) {
                      window.location.href = `/board/one.php?bname=${bname}&idx=${response.idx}`;
                  } else {
                      window.location.href = '/error.html';
                  }
              },
              error: function(xhr, status, error) {
                  console.error('Error:', error);
                  window.location.href = 'error.html';
            }
        });
    });
  })
    </script>
    
  </body>
</html>