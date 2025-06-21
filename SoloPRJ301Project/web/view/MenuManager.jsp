<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRoleID() != 2) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Quản Lý</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
        .bg-approve { background-color: #198754; }
        .bg-profile { background-color: #ffc107; color: black; }
        .bg-logout { background-color: #dc3545; }
    </style>
</head>
<body>

<div class="container mt-5">
    <div class="text-center mb-4">
        <h2>🧑‍💼 Xin chào, <%= user.getFullName() %>!</h2>
        <p class="text-muted">Bạn đang đăng nhập với vai trò: <strong>Manager</strong></p>
    </div>

    <div class="row g-4">
        <div class="col-md-6 col-lg-4">
            <a href="${pageContext.request.contextPath}/manager-leave-list" class="text-decoration-none">
                <div class="card card-option text-center">
                    <div class="card-body">
                        <div class="icon-circle bg-approve"><i class="bi bi-journal-check"></i></div>
                        <h5 class="card-title">Duyệt đơn nghỉ</h5>
                        <p class="card-text text-muted">Xem và duyệt đơn trong phòng ban</p>
                    </div>
                </div>
            </a>
        </div>

        

        <div class="col-md-6 col-lg-4">
            <a href="#" onclick="confirmLogout(event)" class="text-decoration-none">
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

<script>
    function confirmLogout(e) {
        e.preventDefault();
        Swal.fire({
            title: 'Bạn chắc chắn muốn đăng xuất?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Đăng xuất',
            cancelButtonText: 'Hủy',
            confirmButtonColor: '#dc3545',
            cancelButtonColor: '#6c757d'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = '<%= request.getContextPath() %>/logout';
            }
        });
    }
</script>

</body>
</html>
