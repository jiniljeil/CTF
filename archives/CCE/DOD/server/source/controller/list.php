<?php
    if($p !== "list")
        die("<script>alert('Wrong access'); history.back(-1);</script>");

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

    .table th,
    .table thead th {
        font-weight: 500
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

    .table th,
    .table thead th {
        font-weight: 500
    }

    th {
        text-align: inherit
    }

    .m-b-20 {
        margin-bottom: 20px
    }

    .customcheckbox {
        display: block;
        position: relative;
        padding-left: 24px;
        font-weight: 100;
        margin-bottom: 12px;
        cursor: pointer;
        font-size: 22px;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none
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

    .list-item-link {
        color: #000000;
        text-decoration: none;
    }

    a[class*="list-item-link"]:hover {
        color: #426eb5;
    }

    a[class*="list-item-link"]:active {
        color: #426eb5;
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
                <div class="content"> 
                    <?php
                        if($type === "support") {
                            echo "<center><a href='/?page=more'>Ask another questions?</a></center><br>";
                        }
                    ?>
                    
                    <div class="table-responsive">
                        <table class="table">
                            
                            <thead class="thead-light text-center">
                                <tr>   
                                    <th scope="col" style="width:5%">#</th>
                                    <th scope="col" style="width:55%">Title</th>
                                    <?php 
                                        if($type !== "support")
                                            echo " <th scope=\"col\" style=\"width:25%\">Writer</th>";
                                    ?>                                   
                                    <th scope="col" style="width:15%">Date</th>
                                </tr>
                            </thead>
                            
                            <tbody class="customtable text-center">
                                <?php
                                    $posts = get_posts_with_type($type);
                                    foreach($posts as $postid=>$post):
                                ?>
                                <tr class="list-item">       
                                    <td><?= $post["index"] ?></td>


                                    
                                    <?php 
                                        if($type === "support"){
                                            echo "<td><a class=\"list-item-link\" href=\"/?page=show&type={$type}&id={$post["postid"]}\" onClick=\"return enterPass(this)\">{$post["title"]}</a></td>";
                                        } else {
                                            echo "<td><a class=\"list-item-link\" href=\"/?page=show&type={$type}&id={$post["postid"]}\">{$post["title"]}</a></td>";
                                            echo "<td>{$post["writer"]}</td>";
                                        }
                                    ?>
                                    <td><?= $post["date"] ?></td>
                                </tr>
                                <?php endforeach ?>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function enterPass(element) {
        let pass = prompt("Enter Password");
        element.href = element.href + "&pass=" + pass;
        return true;
    }
</script>
