package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.User;

@WebServlet("/user-list")
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // KHÔNG kiểm tra login, chỉ test dữ liệu
        UserDAO dao = new UserDAO();
        List<User> users = dao.getAllUsers();
        
        // In ra console để debug nếu cần
        System.out.println("Tổng số user: " + users.size());

        request.setAttribute("users", users);
       request.getRequestDispatcher("view/UserList.jsp").forward(request, response);

    }
}
