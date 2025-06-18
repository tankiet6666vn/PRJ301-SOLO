package controller;

import dao.LeaveRequestDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.LeaveRequest;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin-leave-list")
public class AdminLeaveListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        LeaveRequestDAO dao = new LeaveRequestDAO();
        List<LeaveRequest> list = dao.getAllRequests();
        request.setAttribute("leaveRequests", list);

        // Lấy thông báo từ session
        HttpSession session = request.getSession();
        String success = (String) session.getAttribute("success");
        if (success != null) {
            request.setAttribute("success", success);
            session.removeAttribute("success");
        }

        request.getRequestDispatcher("view/AdminLeaveList.jsp").forward(request, response);
    }
}

