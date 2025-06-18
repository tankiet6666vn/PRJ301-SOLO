package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xóa session người dùng
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Quay về trang đăng nhập
        response.sendRedirect(request.getContextPath() + "/view/Login.jsp");
    }
}
