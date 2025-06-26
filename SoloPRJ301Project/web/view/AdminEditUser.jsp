<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="model.Department"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        .btn-group-center {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-top: 20px;
        }
        .btn-sm-custom {
            padding: 6px 18px;
            font-size: 0.9rem;
        }
    </style>
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">✏️ Chỉnh sửa người dùng</h4>
        </div>
        <div class="card-body">
            <form action="edit-user" method="post">
                <input type="hidden" name="userID" value="${user.userID}"/>

                <div class="mb-3">
                    <label class="form-label">👤 Họ tên</label>
                    <input type="text" name="fullName" class="form-control" value="${user.fullName}" required/>
                </div>

                <div class="mb-3">
                    <label class="form-label">✉️ Email</label>
                    <input type="email" name="email" class="form-control" value="${user.email}" required/>
                </div>

                <div class="mb-3">
                    <label class="form-label">🏢 Phòng ban</label>
                    <select name="departmentID" class="form-select" required>
                        <c:forEach var="dept" items="${departments}">
                            <option value="${dept.departmentID}" ${dept.departmentID == user.departmentID ? 'selected' : ''}>
                                ${dept.departmentName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">🔐 Vai trò</label>
                    <select name="roleID" class="form-select" required>
                        <option value="1" ${user.roleID == 1 ? 'selected' : ''}>Admin</option>
                        <option value="2" ${user.roleID == 2 ? 'selected' : ''}>Manager</option>
                        <option value="3" ${user.roleID == 3 ? 'selected' : ''}>Nhân viên</option>
                    </select>
                </div>

                <div class="btn-group-center">
                    <button type="submit" class="btn btn-success btn-sm-custom">💾 Lưu</button>
                    <a href="user-list" class="btn btn-secondary btn-sm-custom">← Quay lại danh sách</a>
                </div>
            </form>
        </div>
    </div>
</div>

<% if (request.getAttribute("success") != null) { %>
<script>
    Swal.fire({
        icon: 'success',
        title: 'Thành công!',
        text: '<%= request.getAttribute("success") %>',
        showConfirmButton: false,
        timer: 2000
    });
</script>
<% } %>

<% if (request.getAttribute("error") != null) { %>
<script>
    Swal.fire({
        icon: 'error',
        title: 'Thất bại!',
        text: '<%= request.getAttribute("error") %>',
        showConfirmButton: true
    });
</script>
<% } %>

</body>
</html>
