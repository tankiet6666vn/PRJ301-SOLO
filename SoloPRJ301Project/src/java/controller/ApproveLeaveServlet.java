package controller;

import dao.ActivityLogDAO;
import dao.LeaveRequestDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;

@WebServlet("/approve-leave")
public class ApproveLeaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User approver = (User) session.getAttribute("user");

        if (approver == null || approver.getRoleID() == 3) {
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            int requestID = Integer.parseInt(request.getParameter("requestID"));
            String action = request.getParameter("action");

            String status = "Pending";
            if ("approve".equalsIgnoreCase(action)) {
                status = "Approved";
            } else if ("reject".equalsIgnoreCase(action)) {
                status = "Rejected";
            }

            LeaveRequestDAO dao = new LeaveRequestDAO();
            boolean updated = dao.updateRequestStatus(requestID, status, approver.getUserID());

            // ✅ Ghi log nếu cập nhật thành công
            if (updated) {
                session.setAttribute("success", "Cập nhật trạng thái thành công!");

                String actionText = status.equals("Approved") ? "Duyệt đơn nghỉ" : "Từ chối đơn nghỉ";
                String detailText = "Đơn ID: " + requestID + ", trạng thái: " + status;
                new ActivityLogDAO().insertLog(
                    approver.getUserID(),
                    actionText,
                    detailText
                );
            } else {
                session.setAttribute("error", "Không thể cập nhật trạng thái.");
            }

            // ✅ Chuyển trang theo vai trò
            if (approver.getRoleID() == 1) {
                response.sendRedirect(request.getContextPath() + "/admin-leave-list");
            } else if (approver.getRoleID() == 2) {
                response.sendRedirect(request.getContextPath() + "/manager-leave-list");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Lỗi xử lý đơn nghỉ!");
            response.sendRedirect(request.getContextPath() + "/manager-leave-list");
        }
    }
}
