package controller;

import dao.LeaveRequestDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/approve-leave")
public class ApproveLeaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int requestID = Integer.parseInt(request.getParameter("requestID"));
        String action = request.getParameter("action");

        LeaveRequestDAO dao = new LeaveRequestDAO();
        boolean updated = false;

        if ("approve".equals(action)) {
            updated = dao.updateStatus(requestID, "Approved");
        } else if ("reject".equals(action)) {
            updated = dao.updateStatus(requestID, "Rejected");
        }

        if (updated) {
            HttpSession session = request.getSession();
            session.setAttribute("success", "Cập nhật trạng thái đơn thành công!");
        }

        response.sendRedirect("admin-leave-list");
    }
}

