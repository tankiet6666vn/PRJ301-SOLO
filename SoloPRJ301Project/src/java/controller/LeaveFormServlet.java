package controller;

import dao.LeaveTypeDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.LeaveType;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/request-leave")
public class LeaveFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getRoleID() != 3) {
            response.sendRedirect("Login.jsp");
            return;
        }

        LeaveTypeDAO dao = new LeaveTypeDAO();
        List<LeaveType> types = dao.getAll();
        request.setAttribute("leaveTypes", types);

        request.getRequestDispatcher("view/RequestLeave.jsp").forward(request, response);
    }
}
