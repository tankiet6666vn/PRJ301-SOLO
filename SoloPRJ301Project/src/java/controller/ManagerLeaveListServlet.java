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

@WebServlet("/manager-leave-list")
public class ManagerLeaveListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User manager = (User) session.getAttribute("user");

        if (manager == null || manager.getRoleID() != 2) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // ✅ Ghi log khi manager vào danh sách đơn
        new ActivityLogDAO().insertLog(
            manager.getUserID(),
            "Xem danh sách đơn nghỉ",
            "Manager phòng ban ID " + manager.getDepartmentID()
        );

        int departmentId = manager.getDepartmentID();
        LeaveRequestDAO dao = new LeaveRequestDAO();
        List<LeaveRequest> allRequests = dao.getRequestsByDepartment(departmentId);

        // Phân trang
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
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

        String error = (String) session.getAttribute("error");
        if (error != null) {
            request.setAttribute("error", error);
            session.removeAttribute("error");
        }

        request.getRequestDispatcher("view/ManagerLeaveList.jsp").forward(request, response);
    }
}
