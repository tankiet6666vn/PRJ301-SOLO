package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;

@WebServlet("/update-profile")
public class UpdateProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null || currentUser.getRoleID() != 3) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");

        UserDAO dao = new UserDAO();

        // ✅ Kiểm tra email đã tồn tại bởi user khác
        if (dao.isEmailTakenByOthers(currentUser.getUserID(), email)) {
            request.setAttribute("emailExists", true);

            request.getRequestDispatcher("/view/UserProfile.jsp").forward(request, response);
            return;
        }

        // ✅ Tiến hành cập nhật
        currentUser.setFullName(fullName);
        currentUser.setEmail(email);

        if (dao.updateUserProfile(currentUser)) {
            request.setAttribute("success", true);
            session.setAttribute("user", currentUser); // cập nhật session
        } else {
            request.setAttribute("message", "Cập nhật thất bại. Vui lòng thử lại.");
        }

        request.getRequestDispatcher("/view/UserProfile.jsp").forward(request, response);
    }
}
