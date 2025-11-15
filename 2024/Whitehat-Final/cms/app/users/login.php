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
                <h4 class="font-alt">Login</h4>
                <hr class="divider-w mb-10">
              <form class="form" >
                  <div class="form-group">
                    <input class="form-control" id="username" type="text" name="username" placeholder="Username"/>
                  </div>
                  <div class="form-group">
                    <input class="form-control" id="password" type="password" name="password" placeholder="Password"/>
                  </div>
                  <div class="form-group">
                    <button type="button" class="btn btn-block btn-round btn-b" id="login">Register</button>
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
    <?php include_once($path."script.html");?>
    <script>
      $(document).ready(function(){
        $('#login').on('click',function(){
          var username=$('#username').val();
          var password=$('#password').val();
          $.ajax({
            url: '/users/loginapi.php', 
            type: 'POST', 
            data: {
                user: username,
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
        })
      })
    </script>
  </body>
</html>