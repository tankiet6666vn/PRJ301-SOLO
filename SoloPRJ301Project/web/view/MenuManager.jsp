<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRoleID() != 2) {
        response.sendRedirect("Login.jsp");
        return;
    }

    String loginSuccess = (String) session.getAttribute("loginSuccess");
    if (loginSuccess != null) session.removeAttribute("loginSuccess");
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
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap');

        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            color: #fff;
            overflow-x: hidden;
            background: linear-gradient(45deg, #6b48ff, #af69ef, #d4a4eb);
            background-size: 200% 200%;
            animation: dreamyPurpleFlow 12s ease infinite;
        }

        @keyframes dreamyPurpleFlow {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .container {
            padding-top: 60px;
            padding-bottom: 40px;
            max-width: 1200px;
        }

        .header-text {
            font-size: 2.5rem;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 2px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
            margin-bottom: 10px;
        }

        .subheader-text {
            font-size: 1.1rem;
            font-weight: 300;
            opacity: 0.9;
            margin-bottom: 30px;
        }

        .card-option {
            background: rgba(255, 255, 255, 0.95);
            border: none;
            border-radius: 15px;
            transition: all 0.3s ease;
            overflow: hidden;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            height: 100%;
        }

        .card-option:hover {
            transform: translateY(-10px);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2);
        }

        .card-body {
            padding: 20px;
            text-align: center;
        }

        .icon-circle {
            font-size: 2.5rem;
            width: 80px;
            height: 80px;
            line-height: 80px;
            border-radius: 50%;
            color: #fff;
            margin: 0 auto 15px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .bg-approve {
            background: linear-gradient(135deg, #198754, #28a745);
        }

        .bg-logout {
            background: linear-gradient(135deg, #dc3545, #e74c3c);
        }

        .card-title {
            font-size: 1.25rem;
            font-weight: 500;
            color: #333;
            margin-bottom: 10px;
        }

        .card-text {
            font-size: 0.9rem;
            color: #666;
            line-height: 1.4;
        }

        a.text-decoration-none:hover .card-option {
            text-decoration: none;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="text-center">
        <h2 class="header-text">🧑‍💼 Xin chào, <%= user.getFullName().toUpperCase() %>!</h2>
        <p class="subheader-text">Bạn đang đăng nhập với vai trò: <strong>Manager</strong></p>
    </div>

    <div class="row g-4 justify-content-center">
        <div class="col-md-6 col-lg-4">
            <a href="${pageContext.request.contextPath}/manager-leave-list" class="text-decoration-none">
                <div class="card card-option text-center">
                    <div class="card-body">
                        <div class="icon-circle bg-approve"><i class="bi bi-journal-check"></i></div>
                        <h5 class="card-title">Duyệt đơn nghỉ</h5>
                        <p class="card-text">Xem và duyệt đơn trong phòng ban</p>
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
                        <p class="card-text">Rời khỏi hệ thống</p>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>

<!-- SweetAlert2 confirm logout -->
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

    <% if (loginSuccess != null) { %>
    Swal.fire({
        icon: 'success',
        title: '<%= loginSuccess %>',
        showConfirmButton: false,
        timer: 2000
    });
    <% } %>
</script>

</body>
</html>
