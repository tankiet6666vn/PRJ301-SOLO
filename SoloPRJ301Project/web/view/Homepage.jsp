<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Doctris - Doctor Appointment Booking System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap & Icons -->
    <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" />

    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: 'Segoe UI', sans-serif;
        }

        .login-btn {
            position: fixed;
            top: 20px;
            right: 30px;
            z-index: 10;
        }

        .login-btn a {
            background: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            font-weight: 500;
            border-radius: 30px;
            text-decoration: none;
            transition: all 0.3s ease;
            box-shadow: 0 4px 12px rgba(0, 123, 255, 0.2);
        }

        .login-btn a:hover {
            background: #0056b3;
            color: white;
            box-shadow: 0 6px 15px rgba(0, 86, 179, 0.3);
        }
    </style>
</head>
<body>

<!-- 👤 Login Button -->
<div class="login-btn">
    <a href="<%= request.getContextPath() %>/view/Login.jsp">
        <i class="ri-login-box-line me-1"></i> Đăng nhập
    </a>
</div>

<!-- 🎥 Hero Section with Video -->
<section class="position-relative overflow-hidden d-flex align-items-center" style="min-height: 100vh;">
    <!-- 🔥 Background Video -->
    <video autoplay muted loop playsinline class="position-absolute top-0 start-0 w-100 h-100" style="object-fit: cover; z-index: -2;">
        <source src="<%= request.getContextPath() %>/assets/video/AB.mp4" type="video/mp4">
    </video>

    <!-- 🌑 Overlay -->
    <div class="position-absolute top-0 start-0 w-100 h-100 bg-dark" style="opacity: 0.6; z-index: -1;"></div>

    <!-- ✍️ Centered Content -->
    <div class="container text-center">
        <div class="row justify-content-center">
            <div class="col-lg-10">
                <div class="heading-title">
       
                    <h4 class="display-4 fw-bold text-white title-dark mt-3 mb-4">CHÀO MỪNG TỚI WEBSITE ĐƠN XIN NGHỈ PHÉP </h4>
                        <p class="para-desc text-white-50 mb-0">Toàn bộ template đều được lấy trên cms của Đại Học FPT + thêm chút xào nấu của CHAT GPT =))</p>
                    <div class="mt-4 pt-2">
                        <a href="booking-appointment.html" class="btn btn-primary">Tiến vào</a>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- JS -->
<script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
</body>
</html>
