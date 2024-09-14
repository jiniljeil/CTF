<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style>
  .sidebar {
    float: left;
    position: relative;
    width: 230px;
    height: 300px;
    background: #f6f7fb;
    border: 1px solid #cbd2e4;
  }

  .item-announcement {
    background: url(/static/img/announcement_icon.png) no-repeat 0px 0px;
    background-size: 25px;
  }

  .item-deploy {
    background: url(/static/img/inquiry_icon.png) no-repeat 0px 3px;
    background-size: 20px;
  }

  .item-chatbot {
    background: url(/static/img/chatbot_icon.png) no-repeat 0px 3px;
    background-size: 25px;
  }

  .sidebar a:hover {
    text-decoration: none;
  }

  .sidebar a {
    text-decoration: none;
    color: #656565;
    font-family: 'NanumGothic','Malgun Gothic','Dotum','Verdana' !important;
  }

  .sidebar ul {
    padding: 0;
    list-style-type: none;
  }

  .sidebar ul li {
    margin-top: 40px;
  }

  .item-link {
    margin-left: 37px;
  }

  .banner {
    background: url(/static/img/bannerimg.jpeg) no-repeat 0px -200px;
    background-size: 1500px;
  }

  .nav-link {
    color: #000000;
    text-decoration: none;
  }

  a[class*="nav-link"]:hover {
    color: #000000;
    text-decoration: none;
  }

  a[class*="nav-link"]:active {
    color: #000000;
    text-decoration: none;
  }

  a[class*="nav-link"]:visited {
    color: #000000;
    text-decoration: none;
  }
  
  
</style>
  <header class="py-3 mb-4 border-bottom">
    <div class="container d-flex flex-wrap justify-content-center">
      <a href="/" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto text-dark text-decoration-none">
          <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
          <span class="fs-4">Department of Defense</span>
          </a>
      <div class="my-2 my-md-0 mr-md-3">
        <ul class="nav me-auto">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="information-toggle" data-bs-toggle="dropdown" aria-expanded="false">Information</a>
            <ul class="dropdown-menu" aria-labelledby="information-toggle">
              <li><a class="dropdown-item" href="/?page=about">About</a></li>
              <li><a class="dropdown-item" href="/?page=history">History</a></li>
              <li><a class="dropdown-item" href="/?page=contact">Contact</a></li>
            </ul>
          </li>

          <a class="nav-link dropdown-toggle" href="#" id="public-toggle" data-bs-toggle="dropdown" aria-expanded="false">Public</a>
            <ul class="dropdown-menu" aria-labelledby="public-toggle">
              <li><a class="dropdown-item" href="?page=list&type=announcements">Announcements</a></li>
              <li><a class="dropdown-item" href="?page=list&type=news">News</a></li>
              <li><a class="dropdown-item" href="?page=list&type=faq">FAQ</a></li>
            </ul>
          </li>
          <a class="nav-link dropdown-toggle" href="#" id="support-toggle" data-bs-toggle="dropdown" aria-expanded="false">Support</a>
            <ul class="dropdown-menu" aria-labelledby="support-toggle">
              <li><a class="dropdown-item" href="?page=inquiry">Deployment Inquiry</a></li>
              <li><a class="dropdown-item" href="?page=list&type=support">More</a></li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
    
  </header>
<main class="container banner">
  <div class="p-4 p-md-5 mb-4 text-white">
    <div class="col-md-6 px-0">
      <h1 class="display-8 fst-italic">Department of Defense</h1>
      <p class="lead my-3">Department of Defense always does its best for the people.</p>
    </div>
  </div>
</main>
  <div class="content">
    <div class="container">
        <div class="row">
    
                <!-- <div class="control-box p-3">
                    <h2 style="padding-left: 10px;">Shortcuts</h2>
                </div> -->
                <div class="control-box p-3 sidebar mt-4">
                  <ul>
                    <li class="shortcut-item item-announcement"><a href="?page=list&type=announcements" class="item-link">Announcements</a></li>
                    <li class="shortcut-item item-deploy"><a href="?page=inquiry" class="item-link">Deployment Inquiry</a></li>
                    <li class="shortcut-item item-chatbot"><a href="/chatbot.php" onclick="return popupChat(this)" class="item-link">ChatBot</a></li>
                </ul>
                </div>
     

            <div class="col-md-8 ms-5">
                <div class="control-box p-3 main-content">
                    <?php
                      include_once("./config.php");
                      include_once("./util.php");

                      $p = basename($page);
                      
                      if(!isset($page))
                        $page = __CONTROLLER__."home.php";
                      else if(file_exists(__CONTROLLER__.$p))
                        $page = __CONTROLLER__.$p;
                      else
                        $page = __CONTROLLER__.$p.".php";

                      include_once($page);
                    ?>
            </div>
        </div>
    </div>
</div>

<script>
  function popupChat(element) {
      window.open(element.href, "support", "resizable=0, width=700, height=900, left=100, top=50");

    return false;
  }
</script>