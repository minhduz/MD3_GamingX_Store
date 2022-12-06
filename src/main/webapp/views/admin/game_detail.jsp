<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <title>Product</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicon -->
  <link href="img/favicon.ico" rel="icon">

  <!-- Google Web Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap"
        rel="stylesheet">

  <!-- Icon Font Stylesheet -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

  <!-- Libraries Stylesheet -->
  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

  <!-- Customized Bootstrap Stylesheet -->
  <link href="<%=request.getContextPath()%>/views/admin/css/bootstrap.min.css" rel="stylesheet">

  <!-- Template Stylesheet -->
  <link href="<%=request.getContextPath()%>/views/admin/css/style.css" rel="stylesheet">
</head>

<body>
<div class="container-fluid position-relative d-flex p-0">
  <!-- Spinner Start -->
  <div id="spinner"
       class="show bg-dark position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
    <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
      <span class="sr-only">Loading...</span>
    </div>
  </div>
  <!-- Spinner End -->


  <!-- Sidebar Start -->
  <div class="sidebar pe-4 pb-3">
    <nav class="navbar bg-secondary navbar-dark">
      <a href="<%=request.getContextPath()%>/CategoryServlet?pageNumber=1" class="navbar-brand mx-4 mb-3">
        <img src="<%=request.getContextPath()%>/views/admin/img/logo.png" alt="">
      </a>
      <div class="navbar-nav w-100">
        <a href="<%=request.getContextPath()%>/CategoryServlet?pageNumber=1" class="nav-item nav-link"><i class="fa fa-table me-2"></i>Categories</a>
        <a href="<%=request.getContextPath()%>/GameServlet?action=GetAll" class="nav-item nav-link active"><i class="fa-solid fa-file me-2"></i>Games</a>
        <a href="<%=request.getContextPath()%>/UserServlet?action=GetAll" class="nav-item nav-link"><i class="fa-solid fa-users me-2"></i>Users</a>
        <a href="<%=request.getContextPath()%>/AccessoryServlet" class="nav-item nav-link"><i class="fa-solid fa-flag me-2"></i>Accessories</a>
        <a href="<%=request.getContextPath()%>/OrderServlet" class="nav-item nav-link"><i class="fa-solid fa-cart-shopping me-2"></i>Order</a>
        <a href="<%=request.getContextPath()%>/ReviewServlet" class="nav-item nav-link"><i class="fa-solid fa-comments"></i>Reviews</a>
      </div>
    </nav>
  </div>
  <!-- Sidebar End -->


  <!-- Content Start -->
  <div class="content">
    <!-- Navbar Start -->
    <nav class="navbar navbar-expand bg-secondary navbar-dark sticky-top px-4 py-0">
      <a href="products.html" class="navbar-brand d-flex d-lg-none me-4">
        <h2 class="text-primary mb-0"><i class="fa fa-user-edit"></i></h2>
      </a>
      <a href="#" class="sidebar-toggler flex-shrink-0">
        <i class="fa fa-bars"></i>
      </a>
      <form class="d-none d-md-flex ms-4">
        <input class="form-control bg-dark border-0" type="search" placeholder="Search">
      </form>
      <div class="navbar-nav align-items-center ms-auto">
        <div class="nav-item dropdown">
          <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
            <img class="rounded-circle me-lg-2" src="<%=request.getContextPath()%>/images/${sessionScope.userByID.userAvatar}" alt="" style="width: 40px; height: 40px;">
            <span class="d-none d-lg-inline-flex">${sessionScope.userByID.userFullName}</span>
          </a>
          <div class="dropdown-menu dropdown-menu-end bg-secondary border-0 rounded-0 rounded-bottom m-0">
            <a href="<%=request.getContextPath()%>/HomeServlet?action=LogOut" class="dropdown-item">Log Out</a>
          </div>
        </div>
      </div>
    </nav>
    <!-- Navbar End -->

    <div class="container-fluid pt-4 px-4">
      <div class="row g-4">
        <div class="col-sm-12 col-xl-4">
          <div class="bg-secondary rounded h-100 p-4">
            <h6 class="mb-4">Main Image</h6>
            <img id="mainImage" src="<%=request.getContextPath()%>/images/${ga.gameMainImage}">
          </div>
        </div>
        <div class="col-sm-12 col-xl-8">
          <div class="bg-secondary rounded h-100 p-4">
            <h6 class="mb-4">Responsive Table</h6>
            <div class="table-responsive">
              <table class="table">
                <thead>
                <tr>
                  <th scope="col">GameID</th>
                  <th scope="col">Name</th>
                  <th scope="col">Categories</th>
                  <th scope="col">Price</th>
                  <th scope="col">Status</th>
                  <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td scope="col">${ga.gameID}</td>
                  <td scope="col">${ga.gameName}</td>
                  <td scope="col">${ga.gameCategories}</td>
                  <td scope="col">${ga.gamePrice}$</td>
                  <td scope="col">${ga.gameStatus?"Active":"Inactive"}</td>
                  <td scope="col">
                    <button type="button" class="btn btn-outline-warning m-2"
                            onclick="window.location.href='<%=request.getContextPath()%>/GameServlet?action=GetAll';">Back to All</button>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="col-12">
          <div class="bg-secondary rounded h-100 p-4">
            <h6 class="mb-4">Sub Images</h6>
            <div class="subImages">
              <c:forEach items="${listSubImages}" var="lsi">
                <img src="<%=request.getContextPath()%>/images/${lsi.imageLink}">
              </c:forEach>
            </div>

          </div>
        </div>
        <div class="col-sm-12 col-xl-8">
          <div class="bg-secondary rounded h-100 p-4">
            <h6 class="mb-4">Detail</h6>
            <table class="table">
              <thead>
              <tr>
                <th scope="col">Platforms</th>
                <th scope="col">Developer</th>
                <th scope="col">Publisher</th>
                <th scope="col">Release Date</th>
                <th scope="col">Discount</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <th>${ga.gamePlatforms}</th>
                <td>${ga.gameDeveloper}</td>
                <td>${ga.gamePublisher}</td>
                <td>${ga.gameReleaseDate}</td>
                <td>${ga.gameDiscount}%</td>
              </tr>

              </tbody>
            </table>
          </div>
        </div>
        <div class="col-sm-12 col-xl-4">
          <div class="bg-secondary rounded h-100 p-4">
            <div class="table-responsive">
              <table class="table">
                <thead>
                <tr>
                  <th scope="col">Descriptions</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td scope="col">
                    ${ga.gameDescriptions}
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Footer Start -->
    <div class="container-fluid pt-4 px-4">
      <div class="bg-secondary rounded-top p-4">
        <div class="row">
          <div class="col-12 col-sm-6 text-center text-sm-start">
            &copy; <a href="#">GamingX</a>, All Right Reserved.
          </div>
          <div class="col-12 col-sm-6 text-center text-sm-end">
            <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
            Designed By <a href="https://htmlcodex.com">HTML Codex</a>
            <br>Distributed By: <a href="https://themewagon.com" target="_blank">ThemeWagon</a>
          </div>
        </div>
      </div>
    </div>
    <!-- Footer End -->
  </div>
  <!-- Content End -->


  <!-- Back to Top -->
  <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
</div>

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="lib/chart/chart.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>
<script src="lib/tempusdominus/js/moment.min.js"></script>
<script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
<script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

<!-- Template Javascript -->
<script src="<%=request.getContextPath()%>/views/admin/js/main.js"></script>
</body>

</html>
