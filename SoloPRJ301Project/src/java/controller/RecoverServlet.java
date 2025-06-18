package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.User;
import dao.UserDAO;

@WebServlet("/recover")
public class RecoverServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String username = request.getParameter("username");
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");

        User user = userDAO.checkSecurity(username, question, answer);

        if (user != null) {
            request.setAttribute("success", "Your password is: " + user.getPasswordHash());
        } else {
            request.setAttribute("error", "Invalid information provided.");
        }

        request.getRequestDispatcher("/view/Recover.jsp").forward(request, response);
    }
}
