<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Login - CHARITY CLUB</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico.png">
    <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" />
    <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" />

    <style>
        html, body {
            margin: 0;
            height: 100%;
            font-family: 'Segoe UI', sans-serif;
            overflow: hidden;
        }

        #bg-video {
            position: fixed;
            top: 0;
            left: 0;
            min-width: 100vw;
            min-height: 100vh;
            object-fit: cover;
            z-index: -2;
        }

        .video-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100vw;
            height: 100vh;
            background-color: rgba(0, 0, 0, 0.45);
            z-index: -1;
        }

        section {
            position: relative;
            z-index: 1;
        }

        .login-page {
            border-radius: 1rem;
        }

        .btn-danger {
            background-color: #dc3545;
            border: none;
        }

        .btn-danger:hover {
            background-color: #bb2d3b;
        }

        .custom-error-message {
            color: red;
            font-weight: bold;
            font-size: 1.2rem;
        }
         .home-btn {
            position: fixed;
            top: 20px;
            left: 30px;
            z-index: 100;
        }

        .home-btn a {
            display: inline-block;
            padding: 8px 20px;
            background-color: #0d6efd;
            color: #fff;
            border-radius: 30px;
            font-weight: 500;
            text-decoration: none;
            transition: all 0.3s ease-in-out;
            box-shadow: 0 4px 12px rgba(13, 110, 253, 0.2);
        }

        .home-btn a:hover {
            background-color: #0b5ed7;
            box-shadow: 0 6px 18px rgba(13, 110, 253, 0.35);
        }
    </style>
</head>
<body>
       <div class="home-btn">
    <a href="<%= request.getContextPath() %>/view/Homepage.jsp"> Home</a>
</div>

<!-- 🔥 Video nền -->
<video autoplay muted loop playsinline id="bg-video">
    <source src="<%= request.getContextPath() %>/assets/video/A.mp4" type="video/mp4">
</video>
<!-- 🌓 Overlay mờ -->
<div class="video-overlay"></div>

<section class="d-flex align-items-center justify-content-center" style="min-height: 100vh;">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-8">
                <div class="card login-page bg-white shadow mt-4 border-0">
                    <div class="card-body">
                        <h4 class="text-center">Đăng Nhập Admin</h4>  
                        <form action="<%= request.getContextPath() %>/loginAdmin" method="post" class="login-form mt-4">
                            <div class="mb-3">
                                <label class="form-label">Email <span class="text-danger">*</span></label>
                                <input type="email" class="form-control" name="email" placeholder="Email" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Password <span class="text-danger">*</span></label>
                                <input type="password" class="form-control" name="password" placeholder="Mật Khẩu" required>
                            </div>

                            <div class="d-grid">
                                <button class="btn btn-danger">Đăng Nhập</button>
                            </div>

                            <div class="text-center mt-3">
                                <a href="<%= request.getContextPath() %>/view/Login.jsp" class="btn btn-outline-secondary">Đăng nhập Người Dùng</a>
                            </div>

                            <c:if test="${not empty errorMessage}">
                                <div class="text-center mt-2">
                                    <span class="custom-error-message">${errorMessage}</span>
                                </div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
</body>
</html>
