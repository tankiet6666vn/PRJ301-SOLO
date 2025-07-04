<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        html, body {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: 'Segoe UI', sans-serif;
            background: url('<%= request.getContextPath() %>/assets/images/e.png') no-repeat center center fixed;
            background-size: cover;
        }
        .overlay {
            position: fixed;
            top: 0; left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            z-index: 0;
        }
        .wrapper {
            display: flex;
            min-height: 100vh;
            position: relative;
            z-index: 1;
        }
        .sidebar {
            width: 220px;
            background-color: #6c757d;
            color: white;
            transition: all 0.3s ease;
        }
        .sidebar.collapsed { width: 70px; }
        .sidebar .nav-link {
            color: #f8f9fa;
            padding: 12px 20px;
        }
        .sidebar .nav-link:hover, .sidebar .nav-link.active {
            background-color: #5a6268;
        }
        .sidebar .nav-link i { margin-right: 10px; }
        .sidebar.collapsed .nav-link span { display: none; }
        .sidebar-header { text-align: center; margin-top: 10px; }
        .sidebar-toggle { text-align: end; padding: 15px; }
        .toggle-btn { background: none; border: none; color: white; }
        .main-content {
            flex: 1;
            padding: 30px;
            background-color: rgba(255,255,255,0.95);
            overflow: auto;
        }
        .card-header { background-color: #6c757d; color: white; }
        .badge-admin { background-color: #28a745; }
        .badge-employee { background-color: #17a2b8; }
        .badge-manager { background-color: #ffc107; color: black; }
        .table-hover tbody tr:hover { background-color: #f1f1f1; }
        .table thead { background-color: #6c757d; color: white; }
    </style>
</head>
<body>

<div class="overlay"></div>

<div class="wrapper">
    <!-- Sidebar -->
    <div class="sidebar" id="sidebar">
        <div class="sidebar-toggle">
            <button class="toggle-btn" onclick="toggleSidebar()">
                <i class="bi bi-list fs-5 text-white"></i>
            </button>
        </div>
        <div class="sidebar-header">
            <span class="fw-bold fs-5">🔧 Admin Tool</span>
        </div>
        <nav class="nav flex-column mt-4">
    <a class="nav-link active" href="user-list"><i class="bi bi-people"></i><span>Quản lý người dùng</span></a>
    <a class="nav-link" href="${pageContext.request.contextPath}/admin-leave-list">
        <i class="bi bi-calendar-check"></i><span>Danh sách đơn</span>
    </a>
    <a class="nav-link" href="${pageContext.request.contextPath}/activity-log">

        <i class="bi bi-clipboard-data"></i><span> Xem Log</span>
    </a>
    <a class="nav-link" href="#" onclick="confirmLogout()">
        <i class="bi bi-box-arrow-right"></i><span>Đăng xuất</span>
    </a>
</nav>

    </div>

    <!-- Main Content -->
    <div class="main-content">
        <div class="card shadow-lg">
            <div class="card-header">
                <h4>📋 Danh sách người dùng</h4>
            </div>
            <div class="card-body">
                <form method="get" action="user-list" class="mb-3 d-flex">
                    <input type="text" name="search" class="form-control me-2"
                           placeholder="Tìm theo tên hoặc email"
                           value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
                    <button class="btn btn-outline-dark">🔍 Tìm</button>
                </form>

                <%
                    List<User> users = (List<User>) request.getAttribute("users");
                    Integer currentPage = (Integer) request.getAttribute("currentPage");
                    Integer totalPages = (Integer) request.getAttribute("totalPages");
                    if (currentPage == null) currentPage = 1;
                    if (totalPages == null) totalPages = 1;
                %>

                <% if (users != null && !users.isEmpty()) { %>
                <table class="table table-bordered table-hover align-middle">
                    <thead class="text-center">
                        <tr>
                            <th>ID</th>
                            <th>👤 Username</th>
                            <th>📛 Full Name</th>
                            <th>✉️ Email</th>
                            <th>🏢 Department</th>
                            <th>🔐 Role</th>
                            <th>⚙️ Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (User user : users) {
                            String roleClass = "badge-employee";
                            String roleName = user.getRoleName();
                            if ("Admin".equalsIgnoreCase(roleName)) roleClass = "badge-admin";
                            else if ("Manager".equalsIgnoreCase(roleName)) roleClass = "badge-manager";
                        %>
                        <tr>
                            <td class="text-center"><%= user.getUserID() %></td>
                            <td><%= user.getUsername() %></td>
                            <td><%= user.getFullName() %></td>
                            <td><%= user.getEmail() %></td>
                            <td><%= user.getDepartmentName() %></td>
                            <td class="text-center">
                                <span class="badge <%= roleClass %>"><%= roleName %></span>
                            </td>
                            <td class="text-center">
                                <a href="edit-user?id=<%= user.getUserID() %>" class="btn btn-sm btn-outline-secondary">✏️ Edit</a>
                                <button class="btn btn-sm btn-danger" onclick="confirmDelete(<%= user.getUserID() %>)">🗑️ Xóa</button>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>

                <!-- Pagination -->
                <nav class="mt-4">
                    <ul class="pagination justify-content-center">
                        <li class="page-item <%= currentPage == 1 ? "disabled" : "" %>">
                            <a class="page-link" href="user-list?page=<%= currentPage - 1 %>">« Trước</a>
                        </li>
                        <% for (int i = 1; i <= totalPages; i++) { %>
                        <li class="page-item <%= i == currentPage ? "active" : "" %>">
                            <a class="page-link" href="user-list?page=<%= i %>"><%= i %></a>
                        </li>
                        <% } %>
                        <li class="page-item <%= currentPage == totalPages ? "disabled" : "" %>">
                            <a class="page-link" href="user-list?page=<%= currentPage + 1 %>">Tiếp »</a>
                        </li>
                    </ul>
                </nav>
                <% } else { %>
                <div class="alert alert-info text-center">Không có người dùng nào.</div>
                <% } %>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function toggleSidebar() {
        document.getElementById('sidebar').classList.toggle('collapsed');
    }

    function confirmLogout() {
        Swal.fire({
            title: 'Bạn có chắc chắn muốn đăng xuất?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Đăng xuất',
            cancelButtonText: 'Hủy',
            confirmButtonColor: '#d33',
            cancelButtonColor: '#6c757d'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = '${pageContext.request.contextPath}/logout';
            }
        });
    }

    function confirmDelete(userId) {
        Swal.fire({
            title: 'Xác nhận xóa?',
            text: 'Bạn có chắc chắn muốn xóa người dùng này?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#6c757d',
            confirmButtonText: 'Xóa',
            cancelButtonText: 'Hủy'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = 'delete-user?id=' + userId;
            }
        });
    }

    <% String success = (String) session.getAttribute("loginSuccess");
       String deleteSuccess = (String) session.getAttribute("success");
       if (success != null) {
           session.removeAttribute("loginSuccess");
    %>
    Swal.fire({
        icon: 'success',
        title: '<%= success %>',
        showConfirmButton: false,
        timer: 2000
    });
    <% } else if (deleteSuccess != null) {
         session.removeAttribute("success");
    %>
    Swal.fire({
        icon: 'success',
        title: '<%= deleteSuccess %>',
        showConfirmButton: false,
        timer: 2000
    });
    <% } %>
</script>

</body>
</html>
