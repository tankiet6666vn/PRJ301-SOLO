<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.BookCategory" %>
<html>
<body>
<h2>Create New Book</h2>
<form method="post" action="${pageContext.request.contextPath}/book/create">
    Title: <input type="text" name="title" required /><br/>
    Date: <input type="date" name="date" required /><br/>
    Category:
    <select name="cid">
        <%
            List<BookCategory> list = (List<BookCategory>) request.getAttribute("categories");
            for (BookCategory c : list) {
        %>
        <option value="<%= c.getCid() %>"><%= c.getCname() %></option>
        <% } %>
    </select><br/>
    <input type="submit" value="Save Book" />
</form>
</body>
</html>
