package controller;

import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User u = dao.login(user, pass);

        if (u != null) {
            // ✅ Lưu vào session để các servlet khác dùng
            request.getSession().setAttribute("user", u);
            request.getRequestDispatcher("/view/success.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/view/fail.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/login.jsp").forward(request, response);
    }
}

