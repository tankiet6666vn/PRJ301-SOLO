package controller;

import dao.ActivityLogDAO;
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
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // ✅ Ghi log khi đăng nhập admin/manager thành công
            new ActivityLogDAO().insertLog(
                user.getUserID(),
                "Đăng nhập quản trị",
                "Email: " + user.getEmail() + ", Role: " + user.getRoleID()
            );

            // ✅ Gán thông báo login thành công
            session.setAttribute("loginSuccess", "🎉 Chào mừng " + user.getFullName() + "!");

            // ✅ Điều hướng theo vai trò
            if (user.getRoleID() == 1) {
                response.sendRedirect(request.getContextPath() + "/user-list"); // Admin
            } else if (user.getRoleID() == 2) {
                response.sendRedirect(request.getContextPath() + "/view/MenuManager.jsp");
            } else {
                request.setAttribute("errorMessage", "Bạn không có quyền truy cập trang admin!");
                request.getRequestDispatcher("/view/LoginAdmin.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("errorMessage", "Sai email hoặc mật khẩu!");
            request.getRequestDispatcher("/view/LoginAdmin.jsp").forward(request, response);
        }
    }
}
