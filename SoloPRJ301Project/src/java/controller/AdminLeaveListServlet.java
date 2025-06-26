package controller;

import dao.ActivityLogDAO;
import dao.LeaveRequestDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.LeaveRequest;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin-leave-list")
public class AdminLeaveListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("user");

        // ✅ Kiểm tra quyền admin (roleID == 1)
        if (admin == null || admin.getRoleID() != 1) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // ✅ Ghi log truy cập
        new ActivityLogDAO().insertLog(
            admin.getUserID(),
            "Xem tất cả đơn nghỉ",
            "Admin xem danh sách đơn nghỉ toàn hệ thống"
        );

        LeaveRequestDAO dao = new LeaveRequestDAO();
        List<LeaveRequest> allRequests = dao.getAllRequests();

        // Xử lý phân trang
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

        int totalRecords = allRequests.size();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        int start = (page - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalRecords);
        List<LeaveRequest> paginated = allRequests.subList(start, end);

        request.setAttribute("leaveRequests", paginated);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Thông báo nếu có
        String success = (String) session.getAttribute("success");
        if (success != null) {
            request.setAttribute("success", success);
            session.removeAttribute("success");
        }

        request.getRequestDispatcher("view/AdminLeaveList.jsp").forward(request, response);
    }
}
