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
    =============================================
    -->
    <?php include_once($path."script.html");?>

    <script>
        
        $(document).ready(function() {
          var url = window.location.search;
          const parsedUrl = new URLSearchParams(url);
          const bname = parsedUrl.get("bname");
          const idx = parsedUrl.get("idx");

            $.ajax({
            url: '/board/oneapi.php', 
            data: {
                bname:bname,
                idx:idx
            },
            dataType: 'json', 
            success: function(response) {
                // if (response.status) {
                //     window.location.href = '/';
                // } else {
                //     window.location.href = '/error.html';
                // }
                console.log(response.title);
                generateBlogPost(response,bname);
            },
            error: function(xhr, status, error) {
                // console.error('Error:', error);
                // window.location.href = 'error.html';
              }
          });   
            function generateBlogPost(post,bname) {
                // const bname = parsedUrl.get("bname");
                var postsContainer = $('#blog-post');

                var postHtml = `
                    <div class="col-sm-8 col-sm-offset-2">
                        <div class="post">
                            <div class="post-header font-alt">
                                <h2 class="post-title"><a href="#">${post.title}</a></h2>
                                <div class="post-meta">By&nbsp;<a href="#">${post.owner}</a>&nbsp;| </div>
                            </div>
                            <div class="post-entry">
                                <p>${post.content}</p>
                            </div>
                            <div class="post-more"><a class="more-link" href="/board/edit.php?bname=${bname}&idx=${post.idx}">Edit</a></div>
                            <div class="post-more"><a class="more-link" href="/board/delete.php?bname=&idx=">Delete</a></div>
                        </div>
                    </div>
                `;
                postsContainer.append(postHtml);
            
            }

            // generateBlogPosts(jsonData);
        });
    
    </script>
    
  </body>
</html>