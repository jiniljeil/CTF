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
          <div class="row" id="blog-post">
            <!-- JSON 데이터로 생성된 블로그 포스트가 여기에 삽입됩니다. -->
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
    ============================================= -->
    <?php include_once($path."script.html");?>

    <style>
        .thumbnail img {
            max-width: 30%; /* 부모 요소에 맞게 너비 조절, 30%로 설정 */
            max-height: 30%; /* 비율 유지 */
        }
    </style>

    <script>
        $(document).ready(function() {
            $.ajax({
                url: '/users/profileapi.php', 
                dataType: 'json', 
                success: function(response) {
                    generateBlogPost(response);
                },
                error: function(xhr, status, error) {
                    window.location.href = 'error.html';
                }
            });   
            function generateBlogPost(post) {
                var postsContainer = $('#blog-post');
                let filepath = '';
                if(post.filepath != null) {
                    filepath = `<div class="thumbnail"><a href="#"><img src="${post.filepath}" alt="Blog-post Thumbnail"/></a></div>`;
                }
                
                var postHtml = `
                    <div class="col-sm-8 col-sm-offset-2">
                        <div class="post">
                            ${filepath}
                            <div class="post-header font-alt">
                                <h2 class="post-title"><a href="#">${post.idx}</a></h2>
                                <div class="post-meta">By&nbsp;<a href="#">${post.user}</a>&nbsp;| ${post.role}</div>
                            </div>
                            <div class="post-entry">
                                <p>${post.idx}</p>
                            </div>
                            <div class="post-more"><a class="more-link" href="/users/edit.php">Edit</a></div>
                            <div class="post-more"><a class="more-link" href="/users/delete.php">Delete</a></div>
                        </div>
                    </div>
                `;
                postsContainer.append(postHtml);
            }
        });
    </script>
    
  </body>
</html>
