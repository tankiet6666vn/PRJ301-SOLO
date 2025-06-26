<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRoleID() != 3) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin cá nhân</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        body {
            height: 100vh;
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(-45deg, #ff9a9e, #fad0c4, #fbc2eb, #a18cd1);
            background-size: 400% 400%;
            animation: gradientBG 15s ease infinite;
        }

        @keyframes gradientBG {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .profile-card {
            max-width: 600px;
            margin: 60px auto;
            padding: 30px;
            background: white;
            border-radius: 15px;
            box-shadow: 0 1rem 1.5rem rgba(0, 0, 0, 0.1);
        }

        .form-label {
            font-weight: 600;
        }

        .btn-primary {
            background-color: #dc3545;
            border: none;
        }

        .btn-primary:hover {
            background-color: #bb2d3b;
        }
    </style>
</head>
<body>

<div class="profile-card">
    <h4 class="mb-4 text-center"><i class="bi bi-person-circle me-2"></i>Thông tin người dùng</h4>

    <form action="${pageContext.request.contextPath}/update-profile" method="post">

        <div class="mb-3">
            <label class="form-label">👤 Họ tên</label>
            <input type="text" name="fullName" class="form-control" value="<%= user.getFullName() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">📧 Email</label>
            <input type="email" name="email" class="form-control" value="<%= user.getEmail() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">👤 Username</label>
            <input type="text" class="form-control" value="<%= user.getUsername() %>" disabled>
        </div>

        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary">💾 Cập nhật</button>
            <a href="${pageContext.request.contextPath}/view/Menu.jsp" class="btn btn-secondary ms-2">← Quay lại</a>
        </div>
    </form>
</div>

<%-- ✅ Popup: Cập nhật thành công --%>
<% if (request.getAttribute("success") != null) { %>
<script>
    Swal.fire({
        icon: 'success',
        title: 'Cập nhật thành công!',
        showConfirmButton: false,
        timer: 2000
    });
</script>
<% } %>

<%-- ❌ Popup: Email trùng --%>
<% if (request.getAttribute("emailExists") != null) { %>
<script>
    Swal.fire({
        icon: 'error',
        title: 'Email đã tồn tại!',
        text: 'Vui lòng sử dụng email khác.',
        confirmButtonText: 'OK'
    });
</script>
<% } %>

</body>
</html>
