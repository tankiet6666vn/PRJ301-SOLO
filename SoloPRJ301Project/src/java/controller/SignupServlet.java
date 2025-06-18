package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.User;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String securityQuestion = request.getParameter("securityQuestion");
        String securityAnswer = request.getParameter("securityAnswer");

        int departmentID = 1; // mặc định
        int roleID = 3; // user thường
        String username = email; // dùng email làm username

        UserDAO userDao = new UserDAO();

        if (userDao.existsByEmail(email)) {
            request.setAttribute("errorMessage", "Email đã tồn tại!");
            request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
            return;
        }

        User newUser = new User(username, password, fullName, email, departmentID, roleID);
        newUser.setSecurityQuestion(securityQuestion);
        newUser.setSecurityAnswer(securityAnswer);

        if (userDao.insert(newUser)) {
            request.setAttribute("successMessage", "Đăng ký thành công! Hãy đăng nhập.");
        } else {
            request.setAttribute("errorMessage", "Đăng ký thất bại! Vui lòng thử lại.");
        }

        request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
    }
}
