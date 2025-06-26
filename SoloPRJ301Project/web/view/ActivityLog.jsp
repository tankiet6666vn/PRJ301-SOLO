<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ActivityLog" %>
<%
    Integer currentPage = (Integer) request.getAttribute("currentPage");
    Integer totalPages = (Integer) request.getAttribute("totalPages");
    if (currentPage == null) currentPage = 1;
    if (totalPages == null) totalPages = 1;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log hoạt động</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        html, body {
            margin: 0;
            height: 100%;
            font-family: 'Segoe UI', sans-serif;
            overflow-x: hidden;
        }

        body {
            background: url('<%= request.getContextPath() %>/assets/images/e.png') no-repeat center center fixed;
            background-size: cover;
        }

        .bg-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100vw;
            height: 100vh;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: -1;
        }

        .content-wrapper {
            position: relative;
            z-index: 1;
            min-height: 100vh;
            padding: 50px 15px;
        }

        .card {
            border-radius: 12px;
            box-shadow: 0 0.5rem 1.5rem rgba(0, 0, 0, 0.2);
            overflow: hidden;
        }

        .card-header {
            background-color: #343a40;
            color: white;
        }

        h3 {
            color: white;
            text-shadow: 1px 1px 4px black;
        }

        .table-hover tbody tr:hover {
            background-color: #f8f9fa;
        }

        .alert, .btn {
            border-radius: 8px;
        }
    </style>
</head>
<body>

<!-- 🌑 Overlay -->
<div class="bg-overlay"></div>

<div class="container content-wrapper">
    <div class="card">
        <div class="card-header text-center">
            <h4>📋 Lịch sử hoạt động</h4>
        </div>
        <div class="card-body bg-white">
            <c:choose>
                <c:when test="${not empty logs}">
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered mb-0">
                            <thead class="table-dark text-center">
                                <tr>
                                    <th>ID</th>
                                    <th>👤 Người thực hiện</th>
                                    <th>🎯 Hành động</th>
                                    <th>📄 Chi tiết</th>
                                    <th>🕒 Thời gian</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="log" items="${logs}">
                                    <tr>
                                        <td class="text-center">${log.logID}</td>
                                        <td>${log.fullName} (ID: ${log.userID})</td>
                                        <td>${log.action}</td>
                                        <td>${log.details}</td>
                                        <td>${log.timestamp}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!-- ⏭️ Pagination -->
                    <nav class="mt-4">
                        <ul class="pagination justify-content-center">
                            <li class="page-item <%= currentPage == 1 ? "disabled" : "" %>">
                                <a class="page-link" href="activity-log?page=<%= currentPage - 1 %>">« Trước</a>
                            </li>
                            <% for (int i = 1; i <= totalPages; i++) { %>
                                <li class="page-item <%= i == currentPage ? "active" : "" %>">
                                    <a class="page-link" href="activity-log?page=<%= i %>"><%= i %></a>
                                </li>
                            <% } %>
                            <li class="page-item <%= currentPage == totalPages ? "disabled" : "" %>">
                                <a class="page-link" href="activity-log?page=<%= currentPage + 1 %>">Tiếp »</a>
                            </li>
                        </ul>
                    </nav>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info text-center">Không có dữ liệu log nào.</div>
                </c:otherwise>
            </c:choose>

            <div class="text-center mt-4">
                <a href="${pageContext.request.contextPath}/user-list" class="btn btn-secondary">← Quay lại</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
