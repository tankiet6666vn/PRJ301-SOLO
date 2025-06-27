<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Recover Password - CHARITY CLUB</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico.png">
    <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" />
    <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" id="theme-opt" />

    <style>
        html, body {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: 'Segoe UI', sans-serif;
            overflow: hidden;
        }

        .video-bg {
            position: fixed;
            top: 0; left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
            z-index: 0;
        }

        .overlay {
            position: fixed;
            top: 0; left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.6);
            z-index: 1;
        }

        .recovery-wrapper {
            position: relative;
            z-index: 2;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            padding: 30px 15px;
        }

        .card {
            border-radius: 1rem;
            background-color: #fff;
        }

        .custom-error-message {
            color: red;
            font-weight: bold;
            font-size: 1.1rem;
        }

        .custom-success-message {
            color: green;
            font-weight: bold;
            font-size: 1.1rem;
        }

        .btn-primary {
            background-color: #dc3545;
            border: none;
        }

        .btn-primary:hover {
            background-color: #bb2d3b;
        }

        .btn-outline-secondary:hover {
            background-color: #6c757d;
            color: #fff;
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

<!-- 🎥 VIDEO BACKGROUND -->
<video autoplay muted loop class="video-bg">
    <source src="<%= request.getContextPath() %>/assets/video/R.mp4" type="video/mp4">
</video>
<div class="overlay"></div>

<section class="recovery-wrapper">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-8">
                
                <div class="card shadow mt-4 border-0">
                    <div class="card-body">
                        <h4 class="text-center">Khôi Phục Mật Khẩu</h4>
                        <form action="<%= request.getContextPath() %>/recover" method="post" class="login-form mt-4">
                            <div class="mb-3">
                                <label class="form-label">Tên Đăng Nhập<span class="text-danger">*</span></label>
                                <input type="text" class="form-control" name="username" placeholder=" UserName Của Bạn" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Câu Hỏi Bảo Mật <span class="text-danger">*</span></label>
                                <select class="form-control" name="question" required>
                                    <option value="">-- Chọn Câu Hỏi --</option>
                                    <option value="What is your pet’s name?">What is your pet’s name?</option>
                                    <option value="What is your mother’s maiden name?">What is your mother’s maiden name?</option>
                                    <option value="What is your favorite color?">What is your favorite color?</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Câu Trả Lời <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" name="answer" placeholder="Câu Trả Lời Của Bạn" required>
                            </div>

                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">Xác Thực</button>
                            </div>

                            <div class="text-center mt-3">
                                <a href="<%= request.getContextPath() %>/view/Login.jsp" class="btn btn-outline-secondary">Trở Lại Đăng Nhập</a>
                            </div>

                            <c:if test="${not empty error}">
                                <div class="text-center mt-3">
                                    <span class="custom-error-message">${error}</span>
                                </div>
                            </c:if>
                            <c:if test="${not empty success}">
                                <div class="text-center mt-3">
                                    <span class="custom-success-message">${success}</span>
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
