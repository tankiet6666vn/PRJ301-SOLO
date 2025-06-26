<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRoleID() != 3) {
        response.sendRedirect("Login.jsp");
        return;
    }
    String success = (String) request.getAttribute("success");
    String error = (String) request.getAttribute("errorMessage");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gửi đơn nghỉ phép</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            font-family: 'Segoe UI', sans-serif;
            background: url('<%= request.getContextPath() %>/assets/images/c.png') no-repeat center center fixed;
            background-size: cover;
            position: relative;
        }

        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100vw;
            height: 100vh;
            background: rgba(0, 0, 0, 0.5);
            z-index: 0;
        }

        .container {
            padding-top: 50px;
            padding-bottom: 40px;
            position: relative;
            z-index: 1;
        }

        .card {
            border-radius: 1rem;
            box-shadow: 0 0.5rem 1.5rem rgba(0,0,0,0.1);
        }

        .card-header {
            background: #0d6efd;
            color: white;
            border-top-left-radius: 1rem;
            border-top-right-radius: 1rem;
        }

        .form-label {
            font-weight: 600;
        }

        .btn-success {
            background-color: #dc3545;
            border: none;
        }

        .btn-success:hover {
            background-color: #bb2d3b;
        }

        .alert {
            border-radius: 0.5rem;
        }
    </style>
</head>
<body>

<!-- Lớp overlay tối -->
<div class="overlay"></div>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-7 col-md-9">
            <div class="card">
                <div class="card-header text-center">
                    <h4><i class="bi bi-pencil-square me-2"></i>Đơn xin nghỉ phép</h4>
                </div>
                <div class="card-body">
                    <form action="submit-leave" method="post">
                        <div class="mb-3">
                            <label class="form-label">👤 Nhân viên</label>
                            <input type="text" class="form-control" value="<%= user.getFullName() %>" readonly>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">📌 Loại nghỉ phép</label>
                            <select name="leaveTypeID" class="form-select" required>
                                <option value="">-- Chọn loại --</option>
                                <option value="1">Nghỉ phép năm</option>
                                <option value="2">Nghỉ không lương</option>
                                <option value="3">Nghỉ ốm</option>
                                <option value="4">Nghỉ thai sản</option>
                                <option value="5">Nghỉ việc riêng</option>
                                <option value="6">Nghỉ công tác</option>
                            </select>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">📅 Từ ngày</label>
                                <input type="date" name="startDate" class="form-control" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">📅 Đến ngày</label>
                                <input type="date" name="endDate" class="form-control" required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">📝 Lý do nghỉ</label>
                            <textarea name="reason" class="form-control" rows="3" placeholder="Nhập lý do cụ thể..." required></textarea>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-success">📤 Gửi đơn</button>
                        </div>

                        <% if (error != null) { %>
                            <div class="alert alert-danger text-center mt-3"><%= error %></div>
                        <% } %>

                        <div class="text-center mt-4">
                            <a href="<%= request.getContextPath() %>/view/Menu.jsp" class="btn btn-secondary">
                                ← Quay lại menu
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<% if (success != null) { %>
<script>
    Swal.fire({
        icon: 'success',
        title: 'Thành công!',
        text: '<%= success %>',
        confirmButtonColor: '#3085d6',
        timer: 2500,
        showConfirmButton: false
    });
</script>
<% } %>

</body>
</html>
