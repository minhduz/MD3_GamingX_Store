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
            <form class="d-none d-md-flex ms-4" action="<%=request.getContextPath()%>/GameServlet" method="get">
                <input class="form-control bg-dark border-0" type="search" placeholder="Search" name="gameName">
                <input type="submit" name="action" value="Search" class="btn btn-outline-info m-2"/>
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
                        <h6 class="mb-4">Responsive Table</h6>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">GameID</th>
                                    <th scope="col">Main Image</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listGame}" var="ga">
                                    <tr>
                                        <td scope="col">${ga.gameID}</td>
                                        <td scope="col"><img src="<%=request.getContextPath()%>/images/${ga.gameMainImage}" alt=""></td>
                                        <td scope="col">${ga.gameName}</td>
                                        <td scope="col">${ga.gamePrice}$</td>
                                        <td scope="col">${ga.gameStatus?"Active":"Inactive"}</td>
                                        <td scope="col">
                                            <button type="button" class="btn btn-outline-success m-2"
                                                    onclick="window.location.href='<%=request.getContextPath()%>/GameServlet?action=Update&&gameID=${ga.gameID}';" >Edit</button>
                                            <button type="button" class="btn btn-outline-warning m-2"
                                                    onclick="window.location.href='<%=request.getContextPath()%>/GameServlet?action=GetByID&&gameID=${ga.gameID}';" >Detail</button>
                                            <button type="button" class="btn btn-outline-primary m-2" data-bs-toggle="modal" data-bs-target="#deleteModal" id="delete">Delete</button>
                                            <input type="hidden" id="gaId" value="${ga.gameID}"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--Create New Game Modal-->
        <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div style="background-color: #191C24" class="modal-content">
                    <form action="<%=request.getContextPath()%>/GameServlet" method="post" enctype="multipart/form-data">
                        <div class="modal-header">
                            <h5 class="modal-title">Create new Game</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <table class="table">
                                <tr>
                                    <th scope="col"><label for="gaName" class="form-label">Game Name</label></th>
                                    <th scope="col"><input type="text" id="gaName" name="gameName" class="form-control"></th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="gaPrice" class="form-label">Game Price</label></th>
                                    <th>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text">$</span>
                                            <input type="number" id="gaPrice" name="gamePrice" class="form-control" aria-label="Amount (to the nearest dollar)">
                                            <span class="input-group-text">.00</span>
                                        </div>
                                    </th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="gaPlatforms" class="form-label">Platforms</label></th>
                                    <th>
                                        <select class="form-select mb-3" id="gaPlatforms" name="gamePlatforms" aria-label="Default select example" multiple>
                                            <c:forEach items="${listPlatform}" var="pl">
                                                <option value="${pl.platformID}">${pl.platformName}</option>
                                            </c:forEach>
                                        </select>
                                    </th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="gaDescriptions" class="form-label">Descriptions</label></th>
                                    <th><textarea class="form-control" id="gaDescriptions" name="gameDescriptions" aria-label="With textarea"></textarea></th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="gaDeveloper" class="form-label">Developer</label></th>
                                    <th scope="col"><input type="text" id="gaDeveloper" name="gameDeveloper" class="form-control"></th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="gaPublisher" class="form-label">Publisher</label></th>
                                    <th scope="col"><input type="text" id="gaPublisher" name="gamePublisher" class="form-control"></th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="gaMainImage" class="form-label">Main Image</label></th>
                                    <th scope="col"><input class="form-control bg-dark" type="file" id="gaMainImage" name="gameMainImage"></th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="gaSubImages" class="form-label">Sub Images</label></th>
                                    <th scope="col"><input class="form-control bg-dark" type="file" id="gaSubImages" name="gameSubImages" multiple></th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="gaCategories" class="form-label">Choose Categories</label></th>
                                    <th scope="col">
                                        <select class="form-select" id="gaCategories" name="gameCategories" aria-label="multiple select example" multiple>
                                            <c:forEach items="${listCategory}" var="ca">
                                                <option value="${ca.categoryID}">${ca.categoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="gaReleaseDate" class="form-label">Release Date</label></th>
                                    <th scope="col"><input class="form-control bg-dark" type="date" id="gaReleaseDate" name="gameReleaseDate"></th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="gaDiscount" class="form-label">Discount</label></th>
                                    <th scope="col">
                                        <div class="input-group mb-3">
                                            <input class="form-control bg-dark" type="number" id="gaDiscount" name="gameDiscount">
                                            <span class="input-group-text">%</span>
                                        </div>

                                    </th>
                                </tr>
                                <tr>
                                    <th scope="col"><label class="form-label">Game Status</label></th>
                                    <th>
                                        <input class="form-check-input" type="radio" name="gameStatus" id="active"
                                               value="true" checked>
                                        <label class="form-check-label" for="active">Active</label>
                                        <input style="margin-left: 20px" class="form-check-input" type="radio"
                                               name="gameStatus" id="inactive" value="false">
                                        <label class="form-check-label" for="inactive">Inactive</label>
                                    </th>
                                </tr>

                            </table>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <input type="submit" name="action" value="Create" class="btn btn-outline-info m-2"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--End-->

        <!--Modal Delete-->
        <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div style="background-color: #191C24" class="modal-content">
                    <form action="<%=request.getContextPath()%>/GameServlet" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title">Delete Category</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure want to delete this category?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <input type="submit" name="action" value="Delete" class="btn btn-outline-danger m-2"/>
                            <input type="hidden" name="gaDeleteId" id="gaDeleteId"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--End-->


        <div class="container-fluid pt-4 px-4">
            <div class="row g-4">
                <div class="col-6">
                    <button type="button" class="btn btn-outline-info m-2" data-bs-toggle="modal" data-bs-target="#createModal">Create a new Product</button>
                </div>
                <div class="col-6">
                    <div class="pagination">
                        <a href="#">Previous</a>
                        <a href="#" class="active">1</a>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#">4</a>
                        <a href="#">5</a>
                        <a href="#">6</a>
                        <a href="#">Next</a>
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
<script type="text/javascript">
    $(document).on('click', 'table #delete', function () {
        let gaId = $(this).parent().find('#gaId').val();
        $('#deleteModal #gaDeleteId').val(gaId);
        console.log(gaId)
    });
</script>
</body>

</html>