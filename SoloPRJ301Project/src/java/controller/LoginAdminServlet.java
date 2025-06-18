package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;

@WebServlet("/loginAdmin")
public class LoginAdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.checkLogin(email, password);

        if (user != null) {
            if (user.getRoleID() == 3) {
                // Không cho user thường vào admin
                request.setAttribute("errorMessage", "Bạn không có quyền truy cập trang admin!");
                request.getRequestDispatcher("/view/LoginAdmin.jsp").forward(request, response);
            } else {
                // Đăng nhập thành công, lưu session và chuyển hướng
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/view/AdminDashboard.jsp");
            }
        } else {
            // Sai tài khoản hoặc mật khẩu
            request.setAttribute("errorMessage", "Sai email hoặc mật khẩu!");
            request.getRequestDispatcher("/view/LoginAdmin.jsp").forward(request, response);
        }
    }
}
