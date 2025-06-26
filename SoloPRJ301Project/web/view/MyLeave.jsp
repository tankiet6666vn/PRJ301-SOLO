<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="model.LeaveRequest"%>
<%@page import="java.util.List"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRoleID() != 3) {
        response.sendRedirect("Login.jsp");
        return;
    }

    List<LeaveRequest> requests = (List<LeaveRequest>) request.getAttribute("requests");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đơn nghỉ đã gửi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            height: 100vh;
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: url('<%= request.getContextPath() %>/assets/images/c.png') no-repeat center center fixed;
            background-size: cover;
            position: relative;
            color: #333;
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
        }

        .card-header {
            font-weight: bold;
        }

        .table thead {
            background-color: #343a40;
            color: white;
        }

        .badge {
            font-size: 0.9rem;
        }
    </style>
</head>
<body>

<!-- Overlay nền tối -->
<div class="overlay"></div>

<div class="container">
    <div class="card shadow-lg">
        <div class="card-header text-white bg-primary text-center">
            <h4><i class="bi bi-clock-history me-2"></i>Lịch sử đơn nghỉ phép</h4>
        </div>
        <div class="card-body">

            <% if (requests == null || requests.isEmpty()) { %>
                <div class="alert alert-info text-center">Bạn chưa gửi đơn nghỉ nào.</div>
            <% } else { %>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover align-middle">
                        <thead class="text-center">
                            <tr>
                                <th>Loại nghỉ</th>
                                <th>Từ ngày</th>
                                <th>Đến ngày</th>
                                <th>Lý do</th>
                                <th>Trạng thái</th>
                                <th>Ngày gửi</th>
                            </tr>
                        </thead>
                        <tbody>
                        <% for (LeaveRequest r : requests) { %>
                            <tr>
                                <td><%= r.getLeaveTypeName() %></td>
                                <td class="text-center"><%= r.getStartDate() %></td>
                                <td class="text-center"><%= r.getEndDate() %></td>
                                <td><%= r.getReason() %></td>
                                <td class="text-center">
                                    <% if ("Approved".equalsIgnoreCase(r.getStatus())) { %>
                                        <span class="badge bg-success">Đã duyệt</span>
                                    <% } else if ("Rejected".equalsIgnoreCase(r.getStatus())) { %>
                                        <span class="badge bg-danger">Từ chối</span>
                                    <% } else { %>
                                        <span class="badge bg-warning text-dark">Chờ duyệt</span>
                                    <% } %>
                                </td>
                                <td class="text-center"><%= r.getRequestDate() %></td>
                            </tr>
                        <% } %>
                        </tbody>
                    </table>

                    <%-- Phân trang --%>
                    <div class="d-flex justify-content-center mt-3">
                        <nav>
                            <ul class="pagination">
                                <%
                                    int currentPage = (Integer) request.getAttribute("currentPage");
                                    int totalPages = (Integer) request.getAttribute("totalPages");
                                    for (int i = 1; i <= totalPages; i++) {
                                %>
                                    <li class="page-item <%= i == currentPage ? "active" : "" %>">
                                        <a class="page-link" href="my-leave?page=<%= i %>"><%= i %></a>
                                    </li>
                                <% } %>
                            </ul>
                        </nav>
                    </div>

                </div>
            <% } %>

            <div class="text-center mt-4">
                <a href="${pageContext.request.contextPath}/view/Menu.jsp" class="btn btn-secondary">← Quay lại Menu</a>
            </div>

        </div>
    </div>
</div>

</body>
</html>
