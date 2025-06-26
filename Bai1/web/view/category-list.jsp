<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.BookCategory" %>
<html>
<head>
    <title>Category List</title>
</head>
<body>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <%
        List<BookCategory> list = (List<BookCategory>) request.getAttribute("categories");
        for (BookCategory c : list) {
    %>
    <tr>
        <td><%= c.getCid() %></td>
        <td><%= c.getCname() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
