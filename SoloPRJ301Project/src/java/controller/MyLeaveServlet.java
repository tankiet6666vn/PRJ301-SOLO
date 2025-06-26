package controller;

import dao.ActivityLogDAO;
import dao.LeaveRequestDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.LeaveRequest;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-leave")
public class MyLeaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRoleID() != 3) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // ✅ Ghi log: nhân viên xem đơn nghỉ của chính mình
        new ActivityLogDAO().insertLog(
            user.getUserID(),
            "Xem đơn nghỉ cá nhân",
            "Nhân viên ID: " + user.getUserID() + " xem danh sách đơn nghỉ của mình"
        );

        LeaveRequestDAO dao = new LeaveRequestDAO();
        List<LeaveRequest> allRequests = dao.getByUser(user.getUserID());

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

        List<LeaveRequest> paginatedRequests = allRequests.subList(start, end);

        request.setAttribute("requests", paginatedRequests);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("view/MyLeave.jsp").forward(request, response);
    }
}
