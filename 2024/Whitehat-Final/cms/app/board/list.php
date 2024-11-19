<?php $path="";?>
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
        include_once($path. "headimg.html");
      ?>
      
      <div class="main showcase-page">

        <!-- 섹션 자리 -->
        <section class="module">
          <div class="container">
          <div class="row" id="blog-posts">
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
    =============================================
    -->
      <?php include_once($path."script.html");?>

    <script>
        
        $(document).ready(function() {
          var url = window.location.search;
          const parsedUrl = new URLSearchParams(url);
          const bname = parsedUrl.get("bname");

            $.ajax({
            url: `/board/listapi.php`, 
            data: {
                bname:bname
            },
            dataType: 'json', 
            success: function(response) {
                // if (response.status) {
                //     window.location.href = '/';
                // } else {
                //     window.location.href = '/error.html';
                // }
                console.log(response.title);
                generateBlogPosts(response,bname);
            },
            error: function(xhr, status, error) {
                // console.error('Error:', error);
                // window.location.href = 'error.html';
              }
          });
            function generateBlogPosts(posts) {
                const bname = parsedUrl.get("bname");
                var postsContainer = $('#blog-posts');
                posts.forEach(function(post) {
                    var postHtml = `
                        <div class="col-md-6 col-lg-6">
                            <div class="post">  
                                <div class="post-header font-alt">
                                    <h2 class="post-title"><a href="/board/one.php?bname=${bname}&idx=${post.idx}">${post.title}</a></h2>
                                    <div class="post-meta">By&nbsp;<a href="#">${post.owner}</a>&nbsp;| ${post.content} </div>
                                </div>
                                <div class="post-entry">
                                    <p>${post.content}</p>
                                </div>
                                
                            </div>
                        </div>
                    `;
                    postsContainer.append(postHtml);
                });
            }

            // <div class="post-more"><a class="more-link" href="#">Read more</a></div>
        });
    
    </script>
    
  </body>
</html>