<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <a href="<%=request.getContextPath()%>/CategoryServlet?pageNumber=1" class="nav-item nav-link"><i class="fa-solid fa-file me-2"></i>Categories</a>
        <a href="<%=request.getContextPath()%>/GameServlet?action=GetAll" class="nav-item nav-link active"><i class="fa fa-table me-2"></i>Games</a>
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
        <div class="col-12">
          <div class="bg-secondary rounded h-100 p-4">
            <h6 class="mb-4">Update Game</h6>
            <div class="table-responsive">
              <form action="<%=request.getContextPath()%>/GameServlet" method="post" enctype="multipart/form-data">
                <table class="table">
                  <tr>
                    <th scope="col"><label for="updateGameID" class="form-label">GameID</label></th>
                    <th scope="col"><input id="updateGameID" type="text" name="gameID" class="form-control" value="${gameUpdate.gameID}" readonly></th>
                  </tr>
                  <tr>
                    <th scope="col"><label for="updateGameName" class="form-label">Game Name</label></th>
                    <th scope="col"><input type="text" id="updateGameName" name="gameName" class="form-control" value="${gameUpdate.gameName}"></th>
                  </tr>
                  <tr>
                    <th scope="col"><label for="updateGamePrice" class="form-label">Game Price</label></th>
                    <th>
                      <div class="input-group mb-3">
                        <span class="input-group-text">$</span>
                        <input type="number" id="updateGamePrice" name="gamePrice" class="form-control" aria-label="Amount (to the nearest dollar)" value="${gameUpdate.gamePrice}">
                        <span class="input-group-text">.00</span>
                      </div>
                    </th>
                  </tr>
                  <tr>
                    <th scope="col">
                      <label for="updateGamePlatforms" class="form-label">Platforms</label>
                      <p style="max-width: 300px;">${gameUpdate.gamePlatforms}</p>
                    </th>
                    <th>
                      <select class="form-select mb-3" id="updateGamePlatforms" name="gamePlatforms" aria-label="Default select example" multiple>
                        <c:forEach items="${listPlatform}" var="pl">
                          <c:set var = "listGAID" value = "${pl.gameID}"/>
                          <c:set var = "gaID" value = "${gameUpdate.gameID}"/>
                          <c:choose>
                            <c:when test = "${fn:contains(listGAID,gaID)}">
                              <option value="${pl.platformID}" selected>${pl.platformName}</option>
                            </c:when>
                            <c:otherwise>
                              <option value="${pl.platformID}">${pl.platformName}</option>
                            </c:otherwise>
                          </c:choose>
                        </c:forEach>
                      </select>
                    </th>
                  </tr>
                  <tr>
                    <th scope="col"><label for="updateGameDescriptions" class="form-label">Descriptions</label></th>
                    <th><textarea class="form-control" id="updateGameDescriptions" name="gameDescriptions" aria-label="With textarea">${gameUpdate.gameDescriptions}</textarea></th>
                  </tr>
                  <tr>
                    <th scope="col"><label for="updateGameDeveloper" class="form-label">Developer</label></th>
                    <th scope="col"><input type="text" id="updateGameDeveloper" name="gameDeveloper" class="form-control" value="${gameUpdate.gameDeveloper}"></th>
                  </tr>
                  <tr>
                    <th scope="col"><label for="updateGamePublisher" class="form-label">Publisher</label></th>
                    <th scope="col"><input type="text" id="updateGamePublisher" name="gamePublisher" class="form-control" value="${gameUpdate.gamePublisher}"></th>
                  </tr>
                  <tr>
                    <th scope="col">
                      <label for="updateMainImage" class="form-label">Main Image</label>
                    </th>
                    <th scope="col">
                      <img id="oldMainImage" alt="" src="<%=request.getContextPath()%>/images/${gameUpdate.gameMainImage}">
                      <input type="hidden" value="${gameUpdate.gameMainImage}" name="oldImage">
                      <input class="form-control bg-dark" type="file" id="updateMainImage" name="gameMainImage">
                    </th>
                  </tr>
                  <tr>
                    <th scope="col">
                      <label class="form-label">Sub Images</label>
                    </th>
                    <th scope="col">
                      <div class="oldSubImages">
                        <c:forEach items="${listSubImages}" var="lsi" varStatus="theCount">
                          <img data-bs-toggle="modal" data-bs-target="#subImageModal${lsi.imageID}"
                               id="modalSubImages" alt=""
                               src="<%=request.getContextPath()%>/images/${lsi.imageLink}">
                          <input name="inputSubImgID" type="hidden" id="" value="${lsi.imageID}">
                        </c:forEach>
                        <button type="button" class="btn btn-sm btn-outline-info m-2" data-bs-toggle="modal" data-bs-target="#createSubImageModal">Add</button>
                      </div>
                    </th>
                  </tr>
                  <tr>
                    <th scope="col">
                      <label for="gaCategories" class="form-label">Choose Categories</label>
                      <p style="max-width: 300px;">${gameUpdate.gameCategories}</p>
                    </th>
                    <th scope="col">
                      <select class="form-select" id="gaCategories" name="gameCategories" multiple aria-label="multiple select example">
                        <c:forEach items="${listCategory}" var="ca">
                          <c:set var = "listGAID2" value = "${ca.gameID}"/>
                          <c:set var = "gaID" value = "${gameUpdate.gameID}"/>
                          <c:choose>
                            <c:when test = "${fn:contains(listGAID2,gaID)}">
                              <option value="${ca.categoryID}" selected>${ca.categoryName}</option>
                            </c:when>
                            <c:otherwise>
                              <option value="${ca.categoryID}">${ca.categoryName}</option>
                            </c:otherwise>
                          </c:choose>
                        </c:forEach>
                      </select>
                    </th>
                  </tr>
                  <tr>
                    <th scope="col"><label for="updateGameReleaseDate" class="form-label">Release Date</label></th>
                    <th scope="col"><input class="form-control bg-dark" type="date" id="updateGameReleaseDate" name="gameReleaseDate" value="${gameUpdate.gameReleaseDate}"></th>
                  </tr>
                  <tr>
                    <th scope="col"><label for="updateGameDiscount" class="form-label">Discount</label></th>
                    <th scope="col">
                      <div class="input-group mb-3">
                        <input class="form-control bg-dark" type="number" id="updateGameDiscount" name="gameDiscount" value="${gameUpdate.gameDiscount}">
                        <span class="input-group-text">%</span>
                      </div>
                    </th>
                  </tr>
                  <tr>
                    <th scope="col"><label class="form-label">Game Status</label></th>
                    <th>
                    <c:choose>
                      <c:when test="${gameUpdate.gameStatus}">
                        <input class="form-check-input" type="radio" name="gameStatus" id="active"
                               value="true" checked>
                        <label class="form-check-label" for="active">Active</label>
                        <input style="margin-left: 20px" class="form-check-input" type="radio"
                               name="gameStatus" id="inactive" value="false">
                        <label class="form-check-label" for="inactive">Inactive</label>
                      </c:when>
                      <c:otherwise>
                        <input class="form-check-input" type="radio" name="gameStatus" id="active"
                               value="true">
                        <label class="form-check-label" for="active">Active</label>
                        <input style="margin-left: 20px" class="form-check-input" type="radio"
                               name="gameStatus" id="inactive" value="false" checked>
                        <label class="form-check-label" for="inactive">Inactive</label>
                      </c:otherwise>
                    </c:choose>
                    </th>
                  </tr>
                  <tr>
                    <th></th>
                    <th>
                      <input type="submit" name="action" value="Update" class="btn btn-outline-success m-2"/>
                      <button type="button" class="btn btn-outline-warning m-2"
                              onclick="window.location.href='<%=request.getContextPath()%>/GameServlet?action=GetAll';">Back</button>
                    </th>
                  </tr>
                </table>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!--Create Sub Image Modal-->
    <div class="modal fade" id="createSubImageModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div style="background-color: #191C24" class="modal-content">
          <form action="<%=request.getContextPath()%>/GameServlet" method="post">
            <div class="modal-header">
              <h5 class="modal-title">Create new Sub Images</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <table class="table">
                <tr>
                  <th scope="col"><label for="subImageLink" class="form-label">New SubImage File</label></th>
                  <th scope="col">
                    <input type="hidden" name="gameID" value="${gameUpdate.gameID}">
                    <input class="form-control form-control-sm bg-dark" id="subImageLink" type="file" name="subImageLink">
                  </th>
                </tr>
              </table>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <input type="submit" name="action" value="Add" class="btn btn-outline-info m-2"/>
            </div>
          </form>
        </div>
      </div>
    </div>

    <!--Sub Images Modal Update & Delete-->
    <c:forEach items="${listSubImages}" var="lsi">
      <div class="modal fade" id="subImageModal${lsi.imageID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div style="background-color: #191C24" class="modal-content">
            <form action="<%=request.getContextPath()%>/GameServlet" method="post">
              <div class="modal-header">
                <h5 class="modal-title">Delete Category</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <table class="table">
                <tr>
                  <th scope="col"><label for="subImageLinkUpdate" class="form-label">New SubImage File</label></th>
                  <th scope="col">
                    <input class="form-control form-control-sm bg-dark" id="subImageLinkUpdate" type="file" name="subImageLinkUpdate">
                  </th>
                </tr>
                </table>
              </div>
              <div class="modal-footer">
                <input type="hidden" name="gameID" value="${gameUpdate.gameID}">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <input type="submit" name="action" value="UpdateSubImage" class="btn btn-success m-2" placeholder="Update"/>
                <input type="submit" name="action" value="DeleteSubImage" class="btn btn-danger m-2"/>
                <input type="hidden" name="subImageID" id="subImageID" value="${lsi.imageID}"/>
              </div>
            </form>
          </div>
        </div>
      </div>
    </c:forEach>

    <!--End-->

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
<script type="text/javascript">
  $(document).on('click', 'table #delete', function () {
    $('#deleteModal #catDeleteId').val(catId);
    console.log(catId)
  });
</script>
</body>

</html>
