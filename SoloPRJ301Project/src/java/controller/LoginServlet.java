package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.checkLogin(email, password);

        if (user != null) {
            if (user.getRoleID() != 3) {
                // Nếu không phải user thường thì không được đăng nhập trang user
                request.setAttribute("errorMessage", "Tài khoản này không được phép truy cập trang người dùng.");
                request.getRequestDispatcher("/view/Login.jsp").forward(request, response);
                return;
            }

            // Nếu là user thường, cho đăng nhập
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("view/Menu.jsp");
        } else {
            request.setAttribute("errorMessage", "Sai email hoặc mật khẩu.");
            request.getRequestDispatcher("/view/Login.jsp").forward(request, response);
        }
    }
}
