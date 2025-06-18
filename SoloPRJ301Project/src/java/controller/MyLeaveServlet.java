package controller;

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
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getRoleID() != 3) {
            response.sendRedirect("Login.jsp");
            return;
        }

        LeaveRequestDAO dao = new LeaveRequestDAO();
        List<LeaveRequest> requests = dao.getByUser(user.getUserID());

        request.setAttribute("requests", requests);
        request.getRequestDispatcher("view/MyLeave.jsp").forward(request, response);
    }
}
