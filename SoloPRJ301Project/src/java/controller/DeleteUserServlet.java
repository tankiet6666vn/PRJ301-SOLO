package controller;

import dao.UserDAO;
import dao.ActivityLogDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        try {
            int userID = Integer.parseInt(request.getParameter("id"));

            // ✅ Không cho phép admin tự xóa chính mình
            if (currentUser != null && currentUser.getUserID() == userID) {
                session.setAttribute("error", "⚠️ Bạn không thể xóa chính tài khoản của mình.");
                response.sendRedirect("user-list");
                return;
            }

            UserDAO dao = new UserDAO();
            boolean deleted = dao.deleteUserCompletely(userID);

            if (deleted) {
                // ✅ Ghi log
                if (currentUser != null) {
                    new ActivityLogDAO().insertLog(
                        currentUser.getUserID(),
                        "Xóa người dùng",
                        "Đã xóa người dùng có ID: " + userID
                    );
                }
                session.setAttribute("success", "✅ Xóa người dùng thành công!");
            } else {
                session.setAttribute("error", "❌ Không thể xóa người dùng (có thể còn dữ liệu liên quan).");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "❌ Lỗi không xác định!");
        }

        response.sendRedirect("user-list");
    }
}
