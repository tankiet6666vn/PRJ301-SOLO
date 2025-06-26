package controller;

import dao.ActivityLogDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            // ✅ Ghi log trước khi xóa session
            if (user != null) {
                new ActivityLogDAO().insertLog(
                    user.getUserID(),
                    "Đăng xuất",
                    "Người dùng " + user.getUsername() + " đã đăng xuất."
                );
            }

            session.invalidate();
        }

        // Quay về trang đăng nhập
        response.sendRedirect(request.getContextPath() + "/view/Login.jsp");
    }
}
