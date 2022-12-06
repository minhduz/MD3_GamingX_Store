<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="description" content="Anime Template">
  <meta name="keywords" content="Anime, unica, creative, html">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Anime | Template</title>

  <!-- Google Font -->
  <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap"
        rel="stylesheet">

  <!-- Css Styles -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/views/user/css/bootstrap.min.css" type="text/css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/views/user/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/views/user/css/elegant-icons.css" type="text/css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/views/user/css/plyr.css" type="text/css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/views/user/css/nice-select.css" type="text/css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/views/user/css/owl.carousel.min.css" type="text/css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/views/user/css/slicknav.min.css" type="text/css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/views/user/css/style.css" type="text/css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/views/user/css/cart.css" type="text/css">
</head>
<body>
<!-- Page Preloder -->
<div id="preloder">
  <div class="loader"></div>
</div>
<!-- Header Section Begin -->
<header class="header">
  <div class="container">
    <div class="row d-flex align-items-center">
      <div class="col-lg-2">
        <div class="header__logo">
          <a href="<%=request.getContextPath()%>/HomeServlet">
            <img src="<%=request.getContextPath()%>/views/user/img/logo.png" alt="">
          </a>
        </div>
      </div>
      <div class="col-lg-8">
        <div class="header__nav">
          <nav class="header__menu mobile-menu">
            <ul>
              <li class="active"><a href="<%=request.getContextPath()%>/HomeServlet">Homepage</a></li>
              <li><a href="./categories.html">Categories <span class="arrow_carrot-down"></span></a>
                <ul class="dropdown">
                  <c:forEach items="${listCatMenu}" var="catParent">
                    <li>
                      <a href="<%=request.getContextPath()%>/GameCategoryServlet?categoryID=${catParent.categoryId}">${catParent.categoryName}</a>
                      <div class="child-categories">
                        <c:forEach items="${catParent.listChild}" var="catChild">
                          <a href="<%=request.getContextPath()%>/GameCategoryServlet?categoryID=${catChild.categoryId}">${catChild.categoryName}</a>
                        </c:forEach>
                      </div>
                    </li>
                  </c:forEach>
                </ul>
              </li>
              <li><a href="following.html">Following</a></li>
            </ul>
          </nav>
        </div>
      </div>
      <div class="col-lg-2">
        <div class="header__right">
          <a href="#" class="search-switch"><span class="icon_search"></span></a>
          <a style="display:${sessionScope.userByID==null?'inline-block':'none'};" href="<%=request.getContextPath()%>/LoginServlet"><span class="icon_profile"></span></a>
          <a href="#"><img style="display:${sessionScope.userByID!=null?'inline-block':''};" id="profile" src="<%=request.getContextPath()%>/images/${sessionScope.userByID.userAvatar}"></a>
          <a href="<%=request.getContextPath()%>/ShoppingCartServlet"><i class="fa fa-shopping-cart"></i><span class="badge">${sessionScope.totalCarts}</span></a>
          <a style="display: ${sessionScope.userByID==null?'none':''};" href="<%=request.getContextPath()%>/HomeServlet?action=LogOut">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-closed-fill" viewBox="0 0 16 16">
              <path d="M12 1a1 1 0 0 1 1 1v13h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3V2a1 1 0 0 1 1-1h8zm-2 9a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
            </svg>
          </a>
        </div>
      </div>
    </div>
    <div id="mobile-menu-wrap"></div>
  </div>
</header>
<!-- Header End -->


<section class="product spad">
  <div class="container">
    <div class="row">
      <div class="col-lg-8">
        <div class="trending__product">
          <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-8">
              <div class="section-title">
                <h4>${categoryName}</h4>
              </div>
            </div>
          </div>
          <div class="row">
            <c:forEach items="${listGame}" var="ga">
              <div class="col-lg-4 col-md-6 col-sm-6">
                <div class="product__item">
                  <a href="<%=request.getContextPath()%>/GameDetailServlet?gameID=${ga.gameID}">
                    <div class="product__item__pic set-bg" data-setbg="<%=request.getContextPath()%>/images/${ga.gameMainImage}">
                      <div class="view">${ga.gamePrice}$</div>
                    </div>
                    <div class="product__item__text">
                      <h5><a href="#">${ga.gameName}</a></h5>
                    </div>
                  </a>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-8">
        <div class="anime__details__sidebar">
          <div class="section-title">
            <h5>you might like...</h5>
          </div>
          <div class="product__sidebar__view__item set-bg" data-setbg="<%=request.getContextPath()%>/views/user/img/sidebar/tv-1.jpg">
            <div class="view"><i class="fa fa-eye"></i> 9141</div>
            <h5><a href="#">Elden Ring</a></h5>
          </div>
          <div class="product__sidebar__view__item set-bg" data-setbg="<%=request.getContextPath()%>/views/user/img/sidebar/tv-2.jpg">
            <div class="view"><i class="fa fa-eye"></i> 9141</div>
            <h5><a href="#">Marvel's Spider-Man: Miles Morales</a></h5>
          </div>
          <div class="product__sidebar__view__item set-bg" data-setbg="<%=request.getContextPath()%>/views/user/img/sidebar/tv-3.jpg">
            <div class="view"><i class="fa fa-eye"></i> 9141</div>
            <h5><a href="#">Call of Duty: Modern Warfare II</a></h5>
          </div>
          <div class="product__sidebar__view__item set-bg" data-setbg="<%=request.getContextPath()%>/views/user/img/sidebar/tv-4.jpg">
            <div class="view"><i class="fa fa-eye"></i> 9141</div>
            <h5><a href="#">Sekiro: Shadows Die Twice</a></h5>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- Footer Section Begin -->
<footer class="footer">
  <div class="page-up">
    <a href="#" id="scrollToTopButton"><span class="arrow_carrot-up"></span></a>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-lg-3">
        <div class="footer__logo">
          <a href="./index.html">
            <img src="img/logo.png" alt="">
          </a>
        </div>
      </div>
      <div class="col-lg-6">
        <div class="footer__nav">
          <ul>
            <li class="active"><a href="./index.html">Homepage</a></li>
            <li><a href="./categories.html">Categories</a></li>
            <li><a href="#">Following</a></li>
          </ul>
        </div>
      </div>
      <div class="col-lg-3">
        <p>Copyright &copy;<script>document.write(new Date().getFullYear());</script></p>
        <p>Contacts us: </p>
        <p>Email: gamingX@gmail.com</p>
      </div>
    </div>
  </div>
</footer>
<!-- Footer Section End -->


<!-- Search model Begin -->
<div class="search-model">
  <div class="h-100 d-flex align-items-center justify-content-center">
    <div class="search-close-switch"><i class="icon_close"></i></div>
    <form class="search-model-form" action="<%=request.getContextPath()%>/GameHomeServlet" >
      <input type="text" id="search-input" placeholder="Search here....." name="gameName">
      <input style="display: none" type="submit" name="action" value="Search">
    </form>
  </div>
</div>
<!-- Search model end -->

<!-- Js Plugins -->
<script src="<%=request.getContextPath()%>/views/user/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/views/user/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/views/user/js/player.js"></script>
<script src="<%=request.getContextPath()%>/views/user/js/jquery.nice-select.min.js"></script>
<script src="<%=request.getContextPath()%>/views/user/js/mixitup.min.js"></script>
<script src="<%=request.getContextPath()%>/views/user/js/jquery.slicknav.js"></script>
<script src="<%=request.getContextPath()%>/views/user/js/owl.carousel.min.js"></script>
<script src="<%=request.getContextPath()%>/views/user/js/main.js"></script>
<script src="<%=request.getContextPath()%>/views/user/js/cart.js"></script>
</body>
</html>





