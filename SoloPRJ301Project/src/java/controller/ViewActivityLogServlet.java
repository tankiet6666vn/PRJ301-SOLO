package controller;

import dao.ActivityLogDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.ActivityLog;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/activity-log")
public class ViewActivityLogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRoleID() != 1) {
            response.sendRedirect("unauthorized.jsp");
            return;
        }

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

        int offset = (page - 1) * recordsPerPage;
        ActivityLogDAO dao = new ActivityLogDAO();
        List<ActivityLog> logs = dao.getLogsByPage(offset, recordsPerPage);
        int totalRecords = dao.countLogs();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        request.setAttribute("logs", logs);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("view/ActivityLog.jsp").forward(request, response);
    }
}

