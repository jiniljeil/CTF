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
        color: #426eb5;
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

    .list-item td {
        text-align: center;
    }

    .list-item td:not(:first-child) {
        border-left: 1px solid #cbcbcb;
    }

    .form-group {
        margin-bottom: 30px;
    }

    main[class*="my-form"] {
        padding: 20px;
        outline: 1px solid #426eb5;
    }

    .btn {
        background: #426eb5;
    }

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
        border-top: 2px solid #426eb5;
        border-bottom: 1px solid #dedede;
    }

    table {
        table-layout: fixed;

        border: 0;
        border-spacing: 0;
        
        border-collapse: collapse; 
    }

    td {
        padding: 10px 30px 10px 10px;
    }
    input:focus {  
        outline: none; 
    }

    textarea:focus {
        outline: none;
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

    thead tr:not(:last-child) td{
        border-bottom: 1px solid #dedede;
        border-left: 1px  solid #dedede;
    }

    tr th {
        color: #426eb5;
    }


    .wrap textarea {
      width: 100%;
      resize: none;
      overflow-y: hidden; /* prevents scroll bar flash */
      padding: 1.1em; /* prevents text jump on Enter keypress */
      padding-bottom: 0.2em;
      line-height: 1.6;
    }

    .sendBtn {
        border:0;
        color: #426eb5;
        background-color: transparent;
        width: 100%;
    }

</style>



<div class="row" style="padding-bottom: 30px">
    <div class="col-12">
        <div class="card">
            <!-- TITLE -->
            <div class="card-body">
                <h2 class="card-title m-b-0 big-title">More</h2>
                <hr/>
                <div >
                    <p align="left" class="explain"><strong>Provides the ability to send inquiries.</strong></p>
                </div>
            </div>
            

            <!-- CONTENT -->
            <div class="content"> 
                <form action="/?page=proc_more" method="POST" onsubmit="return sendQuestion()" enctype="multipart/form-data">
                    <table class="vertical-type">
                        <colgroup>
                            <col style="width:15%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th style="border-bottom: 1px solid #dedede;">Title</th>
                                <td class="row-value">
                                    <input style="width:100%; border: 0;" placeholder="Title" name="title" class="title">
                                </td>
                            </tr>
                            <tr>
                                <th style="border-bottom: 1px solid #dedede;">Attachment</th>
                                <td class="row-value">
                                    <input type="file" id="file" name="attachment[]" multiple>
                                </td>
                            </tr>
                        </thead>
                        <tr>
                            <td colspan="2">
                                <div class="wrap">
                                    <textarea style="width:100%; border: 0;"  placeholder="Content" class="text_content" name="content"></textarea>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th style="border-bottom: 1px solid #dedede; border-top: 1px solid #dedede;">Password</th>
                            <td class="row-value">
                                <input type="password" style="width:100%; border: 0;" placeholder="Password" name="password" class="password">
                            </td>
                        </tr>
                    
                        <tr>
                            <td colspan="2">
                                    <input type="submit" class="sendBtn" value="send">
                            </td>
                        </tr>       
                    </table>
                </form>                 
            </div>
        </div>
    </div>
</div>



<script>
    $(".text_content").ready(function() {
      $('.wrap').on( 'keyup', 'textarea', function (e){
        $(this).css('height', 'auto' );
        $(this).height( this.scrollHeight );
      });
      $('.wrap').find( 'textarea' ).keyup();
    });

    function sendQuestion() {
        var title = $(".title")[0].value;
        var content = $(".text_content")[0].value;

        if(title.length <= 5) {
            alert("Length of title must be bigger than 5");
            return false;
        } else if (content.length <= 15) {
            alert("Length of content must be bigger than 15");
            return false;
        } else {
            //success
            return true;
        }
    }
</script>