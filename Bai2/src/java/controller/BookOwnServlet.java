package controller;

import dao.BookDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Book;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/book/own")
public class BookOwnServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.getWriter().write("access denied");
            return;
        }

        BookDAO dao = new BookDAO();
        List<Book> list = dao.getBooksByUser(user.getUsername());
        request.setAttribute("books", list);
        request.getRequestDispatcher("/view/book-own.jsp").forward(request, response);
    }
}
