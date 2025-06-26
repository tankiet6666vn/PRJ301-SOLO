package controller;

import dao.ActivityLogDAO;
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String securityQuestion = request.getParameter("securityQuestion");
        String securityAnswer = request.getParameter("securityAnswer");

        int departmentID = 1; // mặc định
        int roleID = 3;       // user thường

        UserDAO userDao = new UserDAO();

        // Kiểm tra email đã tồn tại
        if (userDao.existsByEmail(email)) {
            request.setAttribute("errorMessage", "Email đã tồn tại!");
            request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
            return;
        }

        // Kiểm tra username đã tồn tại
        if (userDao.existsByUsername(username)) {
            request.setAttribute("errorMessage", "Username đã tồn tại!");
            request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
            return;
        }

        // Tạo user mới
        User newUser = new User(username, password, fullName, email, departmentID, roleID);
        newUser.setSecurityQuestion(securityQuestion);
        newUser.setSecurityAnswer(securityAnswer);

        // Thêm vào database
        if (userDao.insert(newUser)) {
            request.setAttribute("successMessage", "Đăng ký thành công! Hãy đăng nhập.");

            // ✅ Ghi log: đăng ký thành công
            User createdUser = userDao.getByUsername(username); // cần lấy userID sau insert
            if (createdUser != null) {
                new ActivityLogDAO().insertLog(
                    createdUser.getUserID(),
                    "Đăng ký tài khoản",
                    "Tài khoản '" + username + "' đã đăng ký thành công."
                );
            }

        } else {
            request.setAttribute("errorMessage", "Đăng ký thất bại! Vui lòng thử lại.");
        }

        request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
    }
}
