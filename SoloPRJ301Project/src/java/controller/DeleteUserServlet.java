package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int userID = Integer.parseInt(request.getParameter("id"));
            boolean deleted = new UserDAO().deleteUser(userID);

            HttpSession session = request.getSession();
            if (deleted) {
                session.setAttribute("success", "Xóa người dùng thành công!");
            } else {
                session.setAttribute("error", "Xóa người dùng thất bại!");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Lỗi không xác định!");
        }

        response.sendRedirect("user-list");
    }
}
