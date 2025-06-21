package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/user-list")
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Kiểm tra đăng nhập admin (nếu cần)
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null || loginUser.getRoleID() != 1) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // Lấy trang hiện tại từ request (mặc định là 1)
        int page = 1;
        int recordsPerPage = 10;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // Lấy danh sách toàn bộ user
        UserDAO dao = new UserDAO();
        List<User> allUsers = dao.getAllUsers();
        int totalUsers = allUsers.size();
        int totalPages = (int) Math.ceil((double) totalUsers / recordsPerPage);

        // Cắt danh sách theo trang
        int start = (page - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalUsers);
        List<User> paginatedUsers = allUsers.subList(start, end);

        // Gửi dữ liệu sang JSP
        request.setAttribute("users", paginatedUsers);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("view/UserList.jsp").forward(request, response);
    }
}
