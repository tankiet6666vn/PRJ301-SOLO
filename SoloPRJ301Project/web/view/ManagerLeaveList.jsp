<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.LeaveRequest"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRoleID() != 2) {
        response.sendRedirect("Login.jsp");
        return;
    }

    List<LeaveRequest> list = (List<LeaveRequest>) request.getAttribute("leaveRequests");
    int currentPage = (request.getAttribute("currentPage") != null) ? (int) request.getAttribute("currentPage") : 1;
    int totalPages = (request.getAttribute("totalPages") != null) ? (int) request.getAttribute("totalPages") : 1;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Duyệt đơn nghỉ phép</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        body {
            margin: 0;
            min-height: 100vh;
            background: linear-gradient(-45deg, #ff9a9e, #fad0c4, #fbc2eb, #a18cd1);
            background-size: 400% 400%;
            animation: gradientBG 15s ease infinite;
            font-family: 'Segoe UI', sans-serif;
        }

        @keyframes gradientBG {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .card {
            background-color: white;
            border-radius: 12px;
            box-shadow: 0 0.5rem 1rem rgba(0,0,0,0.15);
        }

        .card-header {
            background-color: #343a40;
            color: white;
        }

        .table-hover tbody tr:hover {
            background-color: #f1f1f1;
        }

        .btn-sm {
            padding: 0.25rem 0.5rem;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header">
            <h4><i class="bi bi-person-check me-2"></i>Duyệt đơn nghỉ phép (phòng ban bạn quản lý)</h4>
        </div>
        <div class="card-body">
            <% if (list != null && !list.isEmpty()) { %>
            <table class="table table-bordered table-hover align-middle">
                <thead class="table-secondary text-center">
                <tr>
                    <th>ID</th>
                    <th>👤 Họ tên</th>
                    <th>📌 Loại nghỉ</th>
                    <th>📅 Từ</th>
                    <th>📅 Đến</th>
                    <th>📝 Lý do</th>
                    <th>🕒 Ngày gửi</th>
                    <th>⏱ Trạng thái</th>
                    <th>✔️ Duyệt</th>
                </tr>
                </thead>
                <tbody>
                <% for (LeaveRequest r : list) { %>
                <tr>
                    <td class="text-center"><%= r.getRequestID() %></td>
                    <td><%= r.getFullName() %></td>
                    <td><%= r.getLeaveTypeName() %></td>
                    <td><%= r.getStartDate() %></td>
                    <td><%= r.getEndDate() %></td>
                    <td><%= r.getReason() %></td>
                    <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(r.getRequestDate()) %></td>
                    <td class="text-center">
                        <span class="badge 
                            <%= "Approved".equals(r.getStatus()) ? "bg-success" :
                                 "Rejected".equals(r.getStatus()) ? "bg-danger" :
                                 "bg-warning text-dark" %>">
                            <%= r.getStatus() %>
                        </span>
                    </td>
                    <td class="text-center">
                        <% if ("Pending".equalsIgnoreCase(r.getStatus())) { %>
                        <form action="approve-leave" method="post" style="display:inline-block;">
                            <input type="hidden" name="requestID" value="<%= r.getRequestID() %>">
                            <input type="hidden" name="action" value="approve">
                            <button type="submit" class="btn btn-success btn-sm">✔️</button>
                        </form>
                        <form action="approve-leave" method="post" style="display:inline-block;">
                            <input type="hidden" name="requestID" value="<%= r.getRequestID() %>">
                            <input type="hidden" name="action" value="reject">
                            <button type="submit" class="btn btn-danger btn-sm">❌</button>
                        </form>
                        <% } else { %>
                        <em>Đã xử lý</em>
                        <% } %>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>

            <!-- Phân trang -->
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center mt-3">
                    <li class="page-item <%= currentPage == 1 ? "disabled" : "" %>">
                        <a class="page-link" href="manager-leave-list?page=<%= currentPage - 1 %>">←</a>
                    </li>
                    <% for (int i = 1; i <= totalPages; i++) { %>
                    <li class="page-item <%= i == currentPage ? "active" : "" %>">
                        <a class="page-link" href="manager-leave-list?page=<%= i %>"><%= i %></a>
                    </li>
                    <% } %>
                    <li class="page-item <%= currentPage == totalPages ? "disabled" : "" %>">
                        <a class="page-link" href="manager-leave-list?page=<%= currentPage + 1 %>">→</a>
                    </li>
                </ul>
            </nav>

            <% } else { %>
            <div class="alert alert-info text-center">Không có đơn xin nghỉ nào trong phòng ban của bạn.</div>
            <% } %>

            <div class="text-center mt-4">
                <a href="<%= request.getContextPath() %>/view/MenuManager.jsp" class="btn btn-secondary">
                    ← Quay lại menu
                </a>
            </div>
        </div>
    </div>
</div>

<% if (request.getAttribute("success") != null) { %>
<script>
    Swal.fire({
        icon: 'success',
        title: 'Thành công!',
        text: '<%= request.getAttribute("success") %>',
        timer: 2000,
        showConfirmButton: false
    });
</script>
<% } %>

<% if (request.getAttribute("error") != null) { %>
<script>
    Swal.fire({
        icon: 'error',
        title: 'Thất bại!',
        text: '<%= request.getAttribute("error") %>',
        timer: 2500,
        showConfirmButton: false
    });
</script>
<% } %>

</body>
</html>
