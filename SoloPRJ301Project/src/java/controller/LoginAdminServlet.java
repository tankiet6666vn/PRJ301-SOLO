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
                request.setAttribute("errorMessage", "Bạn không có quyền truy cập trang admin!");
                request.getRequestDispatcher("/view/LoginAdmin.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // ✅ Gửi popup thành công
                session.setAttribute("loginSuccess", "Đăng nhập admin thành công!");

                response.sendRedirect(request.getContextPath() + "/user-list");
            }
        } else {
            request.setAttribute("errorMessage", "Sai email hoặc mật khẩu!");
            request.getRequestDispatcher("/view/LoginAdmin.jsp").forward(request, response);
        }
    }
}
