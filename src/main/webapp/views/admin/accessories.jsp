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
                <a href="<%=request.getContextPath()%>/GameServlet?action=GetAll" class="nav-item nav-link"><i class="fa-solid fa-file me-2"></i>Games</a>
                <a href="<%=request.getContextPath()%>/UserServlet?action=GetAll" class="nav-item nav-link"><i class="fa-solid fa-users me-2"></i>Users</a>
                <a href="<%=request.getContextPath()%>/AccessoryServlet" class="nav-item nav-link active"><i class="fa-solid fa-flag me-2"></i>Accessories</a>
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
                <div class="col-sm-12 col-xl-5">
                    <div class="bg-secondary rounded h-100 p-4">
                        <h6 class="mb-4">Platform</h6>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Platform Name</th>
                                <th scope="col">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listPlatform}" var="pl">
                                <tr>
                                    <th scope="row">${pl.platformID}</th>
                                    <td scope="row">${pl.platformName}</td>
                                    <td>
                                        <button type="button" class="btn btn-outline-success m-2" data-bs-toggle="modal" data-bs-target="#updatePlatformModal" id="updatePlatform">Edit</button>
                                        <button type="button" class="btn btn-outline-primary m-2" data-bs-toggle="modal" data-bs-target="#deletePlatformModal" id="deletePlatform">Delete</button>
                                        <input type="hidden" id="plId" value="${pl.platformID}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <th colspan="3" scope="row"><button type="button" class="btn btn-outline-info m-2" data-bs-toggle="modal" data-bs-target="#addPlatformModal">Add</button></th>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-sm-12 col-xl-7">
                    <div class="bg-secondary rounded h-100 p-4">
                        <h6 class="mb-4" >Trending</h6>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">GID</th>
                                <th scope="col">Main Image
                                <th scope="col">Game Name</th>
                                <th scope="col">Game Price</th>
                                <th scope="col">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listTrendingGame}" var="lTG" varStatus="cnt">
                                <tr>
                                    <th scope="row">${cnt.count}</th>
                                    <th scope="col">${lTG.gameID}</th>
                                    <td>
                                        <img class="trending" src="<%=request.getContextPath()%>/images/${lTG.gameMainImage}">
                                    </td>
                                    <td>${lTG.gameName}</td>
                                    <td>${lTG.gamePrice}$</td>
                                    <td>
                                        <button type="button" class="btn btn-outline-primary m-2" data-bs-toggle="modal" data-bs-target="#deleteTrendingModal" id="deleteGame">Remove</button>
                                        <input type="hidden" id="gaId" value="${lTG.gameID}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <th colspan="6" scope="row"><button type="button" class="btn btn-outline-info m-2" data-bs-toggle="modal" data-bs-target="#addTrendingModal">Add</button></th>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-12">
                    <div class="bg-secondary rounded h-100 p-4">
                        <h6 class="mb-4" >Banner</h6>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Banner Image</th>
                                <th scope="col">Banner Title</th>
                                <th scope="col">Banner Content</th>
                                <th scope="col">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listBanner}" var="bn">
                                <tr>
                                    <th scope="col">${bn.bannerID}</th>
                                    <th scope="col">
                                        <img id="banner" src="<%=request.getContextPath()%>/images/${bn.bannerImage}">
                                    </th>
                                    <th scope="col">${bn.bannerTitle}</th>
                                    <th scope="col">${bn.bannerContent}</th>
                                    <th scope="col">
                                        <button type="button" class="btn btn-outline-success m-2" data-bs-toggle="modal" data-bs-target="#updateBannerModal" id="updateBanner">Edit</button>
                                        <button type="button" class="btn btn-outline-primary m-2" data-bs-toggle="modal" data-bs-target="#deleteBannerModal" id="deleteBanner">Delete</button>
                                        <input type="hidden" id="bnId" value="${bn.bannerID}"/>
                                    </th>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="5"><button type="button" class="btn btn-outline-info m-2" data-bs-toggle="modal" data-bs-target="#addBannerModal">Add</button></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>


        <!--Create New Platform Modal-->
        <div class="modal fade" id="addPlatformModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div style="background-color: #191C24" class="modal-content">
                    <form action="<%=request.getContextPath()%>/AccessoryServlet?action=AddNewPlatform" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title">Add new Platform</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <table class="table">
                                <tr>
                                    <th><th scope="col"><label for="plName" class="form-label">Platform Name</label></th>
                                    <th><input type="text" name="platformName" class="form-control" id="plName"></th>
                                </tr>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-outline-info m-2">Add</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--End-->

        <!--Update Platform Modal-->
        <div class="modal fade" id="updatePlatformModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div style="background-color: #191C24" class="modal-content">
                    <form action="<%=request.getContextPath()%>/AccessoryServlet?action=EditPlatform" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title">Create new Game</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <table class="table">
                                <tr>
                                    <th scope="col"><label for="updatePlatformID" class="form-label">PlatformID</label></th>
                                    <th scope="col"><input id="updatePlatformID" type="text" name="platformID" class="form-control" readonly></th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="updatePlatformName" class="form-label">Platform Name</label></th>
                                    <th scope="col"><input id="updatePlatformName" type="text" name="platformName" class="form-control"></th>
                                </tr>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-outline-info m-2">Edit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--End-->

        <!--Delete Platform Model-->
        <div class="modal fade" id="deletePlatformModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div style="background-color: #191C24" class="modal-content">
                    <form action="<%=request.getContextPath()%>/AccessoryServlet?action=DeletePlatform" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title">Delete Platform</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure want to delete this platform ?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-outline-primary m-2">Delete</button>
                            <input type="hidden" name="platformDeleteID" id="platformDeleteID"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--End-->

        <!--Create New Banner Modal-->
        <div class="modal fade" id="addBannerModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div style="background-color: #191C24" class="modal-content">
                    <form action="<%=request.getContextPath()%>/AccessoryServlet?action=AddNewBanner" method="post" enctype="multipart/form-data">
                        <div class="modal-header">
                            <h5 class="modal-title">Add new Banner</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <table class="table">
                                <tr>
                                    <th scope="col"><label for="bnImage" class="form-label">Banner Image</label></th>
                                    <th scope="col"><input class="form-control bg-dark" type="file" id="bnImage" name="bannerImage"></th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="bnTitle" class="form-label">Banner Title</label></th>
                                    <th><input type="text" name="bannerTitle" class="form-control" id="bnTitle"></th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="bnContent" class="form-label">Banner Content</label></th>
                                    <th><textarea class="form-control" id="bnContent" name="bannerContent" aria-label="With textarea"></textarea></th>
                                </tr>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-outline-info m-2">Add</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--End-->

        <!--Update Banner Modal-->
        <div class="modal fade" id="updateBannerModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div style="background-color: #191C24" class="modal-content">
                    <form action="<%=request.getContextPath()%>/AccessoryServlet?action=EditBanner" method="post" enctype="multipart/form-data">
                        <div class="modal-header">
                            <h5 class="modal-title">Create new Game</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <table class="table">
                                <tr>
                                    <th scope="col"><label for="updateBannerID" class="form-label">BannerID</label></th>
                                    <th scope="col"><input id="updateBannerID" type="text" name="bannerID" class="form-control" readonly></th>
                                </tr>
                                <tr>
                                    <th><label class="form-label">Banner Image</label></th>
                                    <th scope="col">
                                        <input type="hidden" id="updateBannerImage" name="oldBannerImage">
                                        <input class="form-control bg-dark" type="file" name="bannerImage">
                                    </th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="updateBannerTitle" class="form-label">Banner Title</label></th>
                                    <th scope="col"><input id="updateBannerTitle" type="text" name="bannerTitle" class="form-control"></th>
                                </tr>
                                <tr>
                                    <th scope="col"><label for="updateBannerContent" class="form-label">Banner Content</label></th>
                                    <th scope="col"><textarea class="form-control" id="updateBannerContent" name="bannerContent" aria-label="With textarea"></textarea></th>
                                </tr>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-outline-info m-2">Edit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--End-->

        <!--Delete Platform Model-->
        <div class="modal fade" id="deleteBannerModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div style="background-color: #191C24" class="modal-content">
                    <form action="<%=request.getContextPath()%>/AccessoryServlet?action=DeleteBanner" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title">Delete Banner</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure want to delete this banner ?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-outline-primary m-2">Delete</button>
                            <input type="hidden" name="bannerDeleteID" id="bannerDeleteID"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--End-->

        <!--Add Trending Game Modal-->
        <div class="modal fade" id="addTrendingModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div style="background-color: #191C24" class="modal-content">
                    <form action="<%=request.getContextPath()%>/AccessoryServlet?action=AddTrending" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title">Add new Banner</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <table class="table">
                                <tr>
                                    <th scope="col"><label for="bnImage" class="form-label">Select Games</label></th>
                                    <th scope="col"></th>
                                </tr>
                                <tr>
                                    <th colspan="2">
                                        <select class="form-select mb-3" aria-label="Default select example" name="gameID">
                                            <c:forEach items="${listGame}" var="ga">
                                                <option value="${ga.gameID}">${ga.gameName}</option>
                                            </c:forEach>
                                        </select>
                                    </th>
                                </tr>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-outline-info m-2">Add</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--End-->

        <!--Delete Trending Model-->
        <div class="modal fade" id="deleteTrendingModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div style="background-color: #191C24" class="modal-content">
                    <form action="<%=request.getContextPath()%>/AccessoryServlet?action=DeleteTrending" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title">Delete Trending</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure want to remove this game?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-outline-primary m-2">Remove</button>
                            <input type="hidden" name="gameDeleteID" id="gameDeleteID"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- Template Javascript -->
<script src="<%=request.getContextPath()%>/views/admin/js/main.js"></script>
<script type="text/javascript">
    $(document).on('click', 'table #deleteGame', function () {
        let gaId = $(this).parent().find('#gaId').val();
        $('#deleteTrendingModal #gameDeleteID').val(gaId);
        console.log(gaId)
    });

    $(document).on('click', 'table #deletePlatform', function () {
        let plId = $(this).parent().find('#plId').val();
        $('#deletePlatformModal #platformDeleteID').val(plId);
        console.log(plId)
    });


    $(document).on('click','table #updatePlatform',function (){
        let plId = $(this).parent().find('#plId').val();
        console.log(plId);
        $.ajax({
            type:'GET',
            url: '${pageContext.request.contextPath}/AccessoryServlet?action=GetPlatformByID&&platformID='+plId,
            success:function (platformUpdate){
                console.log(platformUpdate);
                $('#updatePlatformID').val(platformUpdate.platformID);
                $('#updatePlatformName').val(platformUpdate.platformName);
            }
        });
    });

    $(document).on('click', 'table #deleteBanner', function () {
        let bnId = $(this).parent().find('#bnId').val();
        $('#deleteBannerModal #bannerDeleteID').val(bnId);
        console.log(bnId)
    });

    $(document).on('click','table #updateBanner',function (){
        let bnId = $(this).parent().find('#bnId').val();
        console.log(bnId);
        $.ajax({
            type:'GET',
            url: '${pageContext.request.contextPath}/AccessoryServlet?action=GetBannerByID&&bannerID='+bnId,
            success:function (bannerUpdate){
                console.log(bannerUpdate);
                $('#updateBannerID').val(bannerUpdate.bannerID);
                $('#updateBannerImage').val(bannerUpdate.bannerImage);
                $('#updateBannerTitle').val(bannerUpdate.bannerTitle);
                $('#updateBannerContent').val(bannerUpdate.bannerContent);
            }
        });
    });
</script>
</body>

</html>

