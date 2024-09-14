<?php
    if($p !== "show")
        die("<script>alert('Wrong access'); history.back(-1);</script>");

    if(!isset($type) || !isset($id) || !is_numeric($id))
        die("<script>alert('Something error occurred!'); history.back(-1);</script>");

        
    if(!array_key_exists($type, $board_types))
        die("<script>alert('Something Error'); history.back(-1);</script>");
?>

<style>
     @import url(http://fonts.googleapis.com/css?family=Calibri:400,300,700);
    .card {
        position: relative;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -ms-flex-direction: column;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border: 0px solid transparent;
        border-radius: 0px
    }
    }

    .card-body {
        -webkit-box-flex: 1;
        -ms-flex: 1 1 auto;
        flex: 1 1 auto;
        padding: 1.25rem
    }

    .card .card-title {
        position: relative;
        font-weight: 600;
        margin-bottom: 10px
    }

    .table {
        width: 100%;
        max-width: 100%;
        margin-bottom: 1rem;
        background-color: transparent
    }

    * {
        outline: none
    }


    .table thead th {
        vertical-align: bottom;
        border-bottom: 2px solid #dee2e6
    }

    .table th {
        padding: 1rem;
        vertical-align: top;
        border-top: 1px solid #dee2e6
    }

    

    th {
        text-align: inherit
    }

    .m-b-20 {
        margin-bottom: 20px
    }


    .customcheckbox input {
        position: absolute;
        opacity: 0;
        cursor: pointer
    }

    .checkmark {
        position: absolute;
        top: -3px;
        left: 0;
        height: 20px;
        width: 20px;
        background-color: #CDCDCD;
        border-radius: 6px
    }

    .customcheckbox input:checked~.checkmark {
        background-color: #2196BB
    }

    .customcheckbox .checkmark:after {
        left: 8px;
        top: 4px;
        width: 5px;
        height: 10px;
        border: solid white;
        border-width: 0 3px 3px 0;
        -webkit-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        transform: rotate(45deg)
    }

    .explain {
        background: #f4fcfe;
        padding: 10px 0px 10px 10px;
        border: 1px solid #cae3e8;
        color: #426eb5;
    }

    .big-title {
        color: #003366;
    }

    tr[class*="list-item"] {
        line-height: 23px;
    }

    .table>:not(:last-child)>:last-child>* {
        border-bottom-color: #5082c9;
    }

    .attachment-link {
        color: #000000;
        text-decoration: none;
    }

    a[class*="attachment-link"]:hover {
        color: #426eb5;
    }

    a[class*="attachment-link"]:active {
        color: #426eb5;
    }
</style>

<style>
    table.vertical-type2 thead th {
        background: #faf7ef;
        
        border-top: 1px solid #cbcbcb;
        color: #73645a;
        letter-spacing: -1px;
        padding: 10px 30px 10px 20px;
    }

    .leaders-history .vertical-type2 td {
        letter-spacing: -1px;
        border-left: 1px solid #dedede;
        text-align: center;
        
    }

    table.vertical-type {
        border-top: 2px solid #5082c9;
        border-bottom: 1px solid #dedede;
    }

    table {
        table-layout: fixed;
        width: 100%;
        border: 0;
        border-spacing: 0;
        border-collapse: separate;
    }

    td {
        padding: 10px 30px 10px 30px;
    }

    th {
        text-align: center;
        padding: 10px 0px;
    }

    table.vertical-type2 tbody td {
        border-top: 1px solid #cbcbcb;
        border-left: 1px solid #cbcbcb;
        
    }

    table.vertical-type tbody td {
        padding: 10px 10px 9px;
        text-align: center;
        border-top: 1px solid #dedede;
        letter-spacing: -1px;
    }

    table.vertical-type tbody td a {
        overflow: hidden;
        display: inline-block;
        margin-right: 5px;
        white-space: nowrap;
        text-overflow: ellipsis;
        color: #666;
        text-align: left;
        vertical-align: middle;
    }
    

    .row-value{
        border-left: 1px  solid #dedede;
    }

    thead tr th {
        color: #4cb3ff;
    }

    textarea {
      width: 100%;
      resize: none;
      overflow-y: hidden; /* prevents scroll bar flash */
      padding: 1.1em; /* prevents text jump on Enter keypress */
      padding-bottom: 0.2em;
      line-height: 1.6;
    }

    textarea:focus {
        outline: none;
    }

    .title {
        background: #fbfbfb;
        color: #434343;
    }

    

</style>


<div class="row">
    <div class="col-12">
        <div class="card">
            <?php
                $title = $board_types[$type]["title"];
                $explain = $board_types[$type]["explain"];
            ?>
            <!-- TITLE -->
            <div class="card-body">
                <h2 class="card-title m-b-0 big-title"><?= $title ?></h2>
                <hr/>
                <div >
                    <p align="left" class="explain"><strong></strong><?= $explain ?></p>
                </div>
            </div>

            <!-- CONTENT -->
            <div class="content" style="padding-bottom: 50px"> 
            <table class="vertical-type">
    <colgroup>
        <col style="width:15%">
    </colgroup>
    <?php
        $postid = "p".$id;
        $posts = get_posts_with_type($type);

        if(!array_key_exists($postid, $posts))
                die("<script>alert('Something Error'); history.back(-1); </script>");
        
        $post = $posts[$postid];

        if($type == "support" && $post["writer"] !== md5($pass))
            die("<script>alert('Something Error'); history.back(-1); </script>");
    ?>
    <thead>
        <tr><th style="border-bottom: 1px solid #dedede;" class="title">Title</th><td style="padding-left: 20px; border-left: 1px solid #dedede; border-bottom: 1px solid #dedede;" class="row-value"><?= $post['title'] ?></td></tr>
        <tr>
            <td style="border-bottom: 1px solid #dedede;border-left: 0px solid #dedede;" colspan="2" class="row-value">
                <p align="left" style="disply: inline; margin-bottom: 0px">
                <?php
                    if($type !== "support")
                        echo "<strong>Writer</strong>  : {$post['writer']}&nbsp; &nbsp; &nbsp; &nbsp;";
                ?>
                <strong>Date</strong>: <?= $post['date'] ?></p>
            </td>
        </tr>

        <tr>
            <td style="border-bottom: 1px solid #dedede;" colspan="2">
                <div class="wrap">
                    <textarea style="width:100%; border: 0;" readonly placeholder="Content" class="text_content" name="content"><?= $post['content'] ?></textarea>
                </div>
            </td>
        </tr>

        <tr><th style="border-bottom: 0px solid #dedede;" class="title">Attachment</th><td style="padding-left: 20px; border-left: 1px solid #dedede; border-bottom: 0px solid #dedede;" class="row-value"><a class="attachment-link" href="/download.php?attachment=<?= $post['attachmentid'] ?>"><?= get_filename_with_attachmentid($post['attachmentid']); ?></a></td></tr>
    </thead>
</div>


<script>
    $(".text_content").ready(function() {
      $('.wrap').on( 'keyup', 'textarea', function (e){
        $(this).css('height', 'auto' );
        $(this).height( this.scrollHeight );
      });
      $('.wrap').find( 'textarea' ).keyup();
    });

</script>