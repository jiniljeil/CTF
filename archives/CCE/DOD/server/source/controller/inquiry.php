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
</style>



<div class="row" style="padding-bottom: 30px">
    <div class="col-12">
        <div class="card">
            <!-- TITLE -->
            <div class="card-body">
                <h2 class="card-title m-b-0 big-title">Military Recruitment Unit Deployment Inquiry</h2>
                <hr/>
                <div >
                    <p align="left" class="explain"><strong>Provides the information where you were deployed to.</strong></p>
                </div>
            </div>
            

            <!-- CONTENT -->
            <div class="content"> 
            <form class="form-horizontal" action='/?page=proc_inquiry' method="POST">
            <main class="my-form">
                <div class="cotainer">
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                                <div class="card">
                                    <div class="card-body">
                                        <form name="my-form" action="success.php" method="">
                                            <div class="form-group row">
                                                <label for="full_name" class="col-md-4 col-form-label text-md-right">Full Name*</label>
                                                <div class="col-md-6">
                                                    <input type="text" id="full_name" class="form-control" name="full-name" placeholder="Kang Baekho">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label for="birthday" class="col-md-4 col-form-label text-md-right">Birthday*</label>
                                                <div class="col-md-6">
                                                    <input type="text" id="birthday" class="form-control" name="birthday" placeholder="020610">
                                                </div>
                                            </div>
                                            

                                            <div class="form-group row">
                                                <label for="enl_date" class="col-md-4 col-form-label text-md-right">Enlistment Date*</label>
                                                <div class="col-md-6">
                                                    <input type="text" id="enl_date" class="form-control" name="enl_date" placeholder="12/07">
                                                </div>
                                            </div>

                                            <div class="col-md-6 offset-md-4">
                                                <button type="submit" class="btn btn-primary">Search</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                          </div>
                      </div>
                    </div>
                </main>                  
            </div>
        </div>
    </div>
</div>