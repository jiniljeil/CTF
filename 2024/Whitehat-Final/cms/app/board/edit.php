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

<script>
$(document).ready(function() {
    const url = window.location.search;
    const parsedUrl = new URLSearchParams(url);
    const bname = parsedUrl.get("bname");
    const idx = parsedUrl.get("idx");

    Promise.all([
        fetch('/board/boardlistapi.php').then(response => response.json()),
        fetch(`/board/oneapi.php?bname=${bname}&idx=${idx}`).then(response => {
            if (!response.ok) {
                throw new Error('Error fetching post');
            }
            return response.json();
        })
    ])
    .then(([menuData, post]) => {
        generateBlogPost(post,bname);
        generateBlogOption(menuData, bname);
        
        // 버튼 클릭 이벤트 리스너 등록
        $('#board').on('click', handleBoardClick);
    })
    .catch(error => {
        console.error('Error:', error);
        window.location.href = 'error.html';
    });

    function generateBlogOption(items, bname) {
        items.forEach(function(item) {
            if (bname == item.bname) {
                $("#toname").append(
                    `<option value="${item.bname}" selected>${item.bname} (Now)</option>`
                );
            } else {
                $("#toname").append(
                    `<option value="${item.bname}">${item.bname}</option>`
                );
            }
        });
    }

    function generateBlogPost(post,bname) {
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
                    <input type="hidden" id="idx" value="${post.idx}">
                    <input type="hidden" id="bname" value="${bname}">
                    <div class="form-group">
                        <select name="toname" id="toname"></select>
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="text" placeholder="Board Modify" readonly=""/>
                    </div>
                    <div class="form-group">
                        <input class="form-control input-lg" type="text" placeholder="Title" id="title" value="${post.title}"/>
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" rows="7" placeholder="Content" id="content">${post.content}</textarea>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-block btn-round btn-b" id="board">Register</button>
                    </div>
                </form>
            </div>
        `;

        postsContainer.append(postHtml);
    }

    function handleBoardClick() {
        var idx = $('#idx').val();
        var bname = $('#bname').val();
        var toname = $('#toname').val();
        var title = $('#title').val();
        var content = $('#content').val();

        $.ajax({
            url: '/board/editapi.php',
            type: 'POST',
            data: {
                idx:idx,
                bname:bname,
                toname:toname,
                title:title,
                content:content
            },
            dataType: 'json',
            success: function(response) {
                if (response.status) {
                    window.location.href = `/board/one.php?bname=${response.bname}&idx=${response.idx}`;
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
