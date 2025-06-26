<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<html>
<body>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Date</th>
        <th>Category Name</th> <!-- ✅ Category Name -->
    </tr>
<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    for (Book b : books) {
%>
<tr>
    <td><%= b.getBid() %></td>
    <td><%= b.getTitle() %></td>
    <td><%= b.getPublisheddate() %></td>
    <td><%= b.getCategoryName() %></td>
</tr>
<% } %>
</table>
</body>
</html>
