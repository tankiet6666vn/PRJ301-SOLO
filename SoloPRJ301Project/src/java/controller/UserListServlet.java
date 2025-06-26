package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user-list")
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Kiểm tra đăng nhập Admin
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null || loginUser.getRoleID() != 1) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // ✅ Lấy tham số tìm kiếm (nếu có)
        String search = request.getParameter("search");
        if (search == null) search = "";

        // ✅ Lấy trang hiện tại
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

        // ✅ Lấy danh sách người dùng từ DB
        UserDAO dao = new UserDAO();
        List<User> allUsers = dao.getAllUsers(); // Hoặc dao.searchUsers(search) nếu có hàm riêng

        // ✅ Lọc theo tên/email (tìm kiếm đơn giản)
        List<User> filteredUsers = new ArrayList<>();
        for (User u : allUsers) {
            if (u.getFullName().toLowerCase().contains(search.toLowerCase()) ||
                u.getEmail().toLowerCase().contains(search.toLowerCase())) {
                filteredUsers.add(u);
            }
        }

        // ✅ Phân trang
        int totalUsers = filteredUsers.size();
        int totalPages = (int) Math.ceil((double) totalUsers / recordsPerPage);
        int start = (page - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalUsers);
        List<User> paginatedUsers = filteredUsers.subList(start, end);

        // ✅ Truyền dữ liệu sang view
        request.setAttribute("users", paginatedUsers);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("search", search);

        request.getRequestDispatcher("view/UserList.jsp").forward(request, response);
    }
}
