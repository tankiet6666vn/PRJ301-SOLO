package controller;

import dao.LeaveRequestDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;

@WebServlet("/submit-leave")
public class SubmitLeaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRoleID() != 3) {
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            int leaveTypeID = Integer.parseInt(request.getParameter("leaveTypeID"));
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String reason = request.getParameter("reason");

            LeaveRequestDAO dao = new LeaveRequestDAO();
            boolean success = dao.insertLeaveRequest(user.getUserID(), leaveTypeID, startDate, endDate, reason);

            if (success) {
                request.setAttribute("successMessage", "🎉 Gửi đơn nghỉ phép thành công!");
                request.getRequestDispatcher("view/Menu.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Gửi đơn thất bại. Vui lòng thử lại.");
                request.getRequestDispatcher("view/RequestLeave.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Dữ liệu không hợp lệ hoặc lỗi hệ thống.");
            request.getRequestDispatcher("view/RequestLeave.jsp").forward(request, response);
        }
    }
}
