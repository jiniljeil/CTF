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
              <div class="">
                <h4 class="font-alt">Register</h4>
                <hr class="divider-w mb-10">
                <form class="form">
                  <!-- <div class="form-group">
                    <input class="form-control" id="E-mail" type="text" name="email" placeholder="Email"/>
                  </div> -->
                  <div class="form-group">
                    <input class="form-control" id="username" type="text" name="username" placeholder="Username"/>
                  </div>
                  <div class="form-group">
                    <input class="form-control" id="password" type="password" name="password" placeholder="Password"/>
                  </div>
                  <div class="form-group">
                    <input class="form-control" id="re-password" type="password" name="re-password" placeholder="Re-enter Password"/>
                  </div>
                  <div class="form-group">
                    <button type="button" id="register" class="btn btn-block btn-round btn-b">Register</button>
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
    <?php include_once($path."script.html");?>
    <script>
      $(document).ready(function(){
        $('#register').on('click',function(){
          var username=$('#username').val();
          var password=$('#password').val();
          var repassword=$('#re-password').val();
          console.log(password);
          if(password == repassword ){
            $.ajax({
            url: '/users/registerapi.php', 
            type: 'POST', 
            data: {
                user:username,
                pass:password
            },
            dataType: 'json', 
            success: function(response) {
                if (response.status) {
                    window.location.href = '/';
                } else {
                    window.location.href = '/error.html';
                }
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                window.location.href = 'error.html';
              }
          });   
        }else{
          alert("값을 입력 해주세요");
        }
        })
      })
    </script>
  </body>
</html>