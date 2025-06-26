<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.LeaveRequest"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách đơn nghỉ phép</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
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
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
        }

        .card-header {
            background-color: #6c757d;
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
                <h4><i class="bi bi-calendar-check me-2"></i>Danh sách đơn nghỉ của người dùng</h4>
            </div>
            <div class="card-body">
                <%
                    List<LeaveRequest> list = (List<LeaveRequest>) request.getAttribute("leaveRequests");
                    if (list != null && !list.isEmpty()) {
                %>
                <table class="table table-bordered table-hover align-middle">
                    <thead class="table-secondary text-center">
                        <tr>
                            <th>ID</th>
                            <th>👤 Họ tên</th>
                            <th>📌 Loại nghỉ</th>
                            <th>📅 Từ</th>
                            <th>📅 Đến</th>
                            <th>📝 Lý do</th>
                            <th>⏱ Trạng thái</th>
                            <th>🕒 Ngày gửi</th>
                            <th>⚙️ Duyệt</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (LeaveRequest lr : list) { %>
                        <tr>
                            <td class="text-center"><%= lr.getRequestID() %></td>
                            <td><%= lr.getFullName() %></td>
                            <td><%= lr.getLeaveTypeName() %></td>
                            <td><%= lr.getStartDate() %></td>
                            <td><%= lr.getEndDate() %></td>
                            <td><%= lr.getReason() %></td>
                            <td class="text-center">
                                <span class="badge
                                      <%= "Approved".equals(lr.getStatus()) ? "bg-success" :
                                          "Rejected".equals(lr.getStatus()) ? "bg-danger" :
                                          "bg-warning text-dark" %>">
                                    <%= lr.getStatus() %>
                                </span>
                            </td>
                            <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(lr.getRequestDate()) %></td>
                            <td class="text-center">
                                <% if ("Pending".equalsIgnoreCase(lr.getStatus())) { %>
                                <form action="approve-leave" method="post" style="display:inline-block;">
                                    <input type="hidden" name="requestID" value="<%= lr.getRequestID() %>">
                                    <button type="submit" name="action" value="approve" class="btn btn-sm btn-success" title="Duyệt">✔️</button>
                                </form>
                                <form action="approve-leave" method="post" style="display:inline-block;">
                                    <input type="hidden" name="requestID" value="<%= lr.getRequestID() %>">
                                    <button type="submit" name="action" value="reject" class="btn btn-sm btn-danger" title="Từ chối">❌</button>
                                </form>
                                <% } else { %>
                                <em>Đã xử lý</em>
                                <% } %>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <%
                    int currentPage = (request.getAttribute("currentPage") != null)
                                      ? (Integer) request.getAttribute("currentPage") : 1;
                    int totalPages = (request.getAttribute("totalPages") != null)
                                     ? (Integer) request.getAttribute("totalPages") : 1;
                %>
                <nav class="mt-4">
                    <ul class="pagination justify-content-center">
                        <li class="page-item <%= currentPage == 1 ? "disabled" : "" %>">
                            <a class="page-link" href="admin-leave-list?page=<%= currentPage - 1 %>">« Trước</a>
                        </li>
                        <% for (int i = 1; i <= totalPages; i++) { %>
                        <li class="page-item <%= i == currentPage ? "active" : "" %>">
                            <a class="page-link" href="admin-leave-list?page=<%= i %>"><%= i %></a>
                        </li>
                        <% } %>
                        <li class="page-item <%= currentPage == totalPages ? "disabled" : "" %>">
                            <a class="page-link" href="admin-leave-list?page=<%= currentPage + 1 %>">Tiếp »</a>
                        </li>
                    </ul>
                </nav>

                <% } else { %>
                <div class="alert alert-info text-center">Không có đơn xin nghỉ nào.</div>
                <% } %>

                <div class="text-center mt-4">
                    <a href="${pageContext.request.contextPath}/user-list" class="btn btn-secondary">
                        ← Quay lại danh sách người dùng
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
            showConfirmButton: false,
            timer: 2000
        });
    </script>
    <% } %>

</body>
</html>
