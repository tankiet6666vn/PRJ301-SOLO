<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRoleID() != 3) {
        response.sendRedirect("Login.jsp");
        return;
    }
    String success = (String) request.getAttribute("successMessage");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #dee2e6, #f8f9fa);
            font-family: 'Segoe UI', sans-serif;
        }

        .card-option {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .card-option:hover {
            transform: translateY(-5px);
            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
        }

        .icon-circle {
            font-size: 2rem;
            padding: 20px;
            border-radius: 50%;
            color: white;
            display: inline-block;
            margin-bottom: 10px;
        }

        .bg-leave { background-color: #0d6efd; }
        .bg-view  { background-color: #20c997; }
        .bg-profile { background-color: #ffc107; color: black; }
        .bg-logout { background-color: #dc3545; }
    </style>
</head>
<body>

<div class="container mt-5">
    <div class="text-center mb-4">
        <h2>👋 Chào mừng, <%= user.getFullName() %>!</h2>
        <p class="text-muted">Bạn đang đăng nhập với vai trò: <strong>Người dùng</strong></p>
    </div>

    <% if (success != null) { %>
        <div class="alert alert-success text-center mx-auto" style="max-width: 600px;">
            <%= success %>
        </div>
    <% } %>

    <div class="row g-4">
        <div class="col-md-6 col-lg-3">
            <a href="${pageContext.request.contextPath}/request-leave" class="text-decoration-none"> 
                <div class="card card-option text-center">
                    <div class="card-body">
                        <div class="icon-circle bg-leave"><i class="bi bi-pencil-square"></i></div>
                        <h5 class="card-title">Gửi đơn nghỉ</h5>
                        <p class="card-text text-muted">Tạo đơn xin nghỉ phép mới</p>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-6 col-lg-3">
            <a href="my-leaves.jsp" class="text-decoration-none">
                <div class="card card-option text-center">
                    <div class="card-body">
                        <div class="icon-circle bg-view"><i class="bi bi-file-earmark-text"></i></div>
                        <h5 class="card-title">Đơn đã gửi</h5>
                        <p class="card-text text-muted">Xem lịch sử đơn xin nghỉ</p>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-6 col-lg-3">
            <a href="profile.jsp" class="text-decoration-none">
                <div class="card card-option text-center">
                    <div class="card-body">
                        <div class="icon-circle bg-profile"><i class="bi bi-person-circle"></i></div>
                        <h5 class="card-title">Thông tin</h5>
                        <p class="card-text text-muted">Xem thông tin cá nhân</p>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-6 col-lg-3">
            <a href="logout" class="text-decoration-none">
                <div class="card card-option text-center">
                    <div class="card-body">
                        <div class="icon-circle bg-logout"><i class="bi bi-box-arrow-right"></i></div>
                        <h5 class="card-title text-danger">Đăng xuất</h5>
                        <p class="card-text text-muted">Rời khỏi hệ thống</p>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>

</body>
</html>
