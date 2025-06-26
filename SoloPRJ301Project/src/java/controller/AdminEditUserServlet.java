package controller;

import dao.ActivityLogDAO;
import dao.DepartmentDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Department;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/edit-user")
public class AdminEditUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        User user = new UserDAO().getUserById(id);
        List<Department> departments = new DepartmentDAO().getAllDepartments();

        request.setAttribute("user", user);
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("view/AdminEditUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int userID = Integer.parseInt(request.getParameter("userID"));
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        int departmentID = Integer.parseInt(request.getParameter("departmentID"));
        int roleID = Integer.parseInt(request.getParameter("roleID"));

        UserDAO dao = new UserDAO();
        User existing = dao.getUserById(userID);

        User updatedUser = new User(
                userID,
                existing.getUsername(),
                existing.getPasswordHash(),
                fullName,
                email,
                departmentID,
                roleID,
                existing.getSecurityQuestion(),
                existing.getSecurityAnswer()
        );

        boolean updated = dao.updateUser(updatedUser);

        List<Department> departments = new DepartmentDAO().getAllDepartments();
        request.setAttribute("user", updatedUser);
        request.setAttribute("departments", departments);

        if (updated) {
            request.setAttribute("success", "Cập nhật người dùng thành công!");

            // ✅ Ghi log: admin chỉnh sửa thông tin người dùng
            HttpSession session = request.getSession();
            User admin = (User) session.getAttribute("user");
            if (admin != null) {
                new ActivityLogDAO().insertLog(
                    admin.getUserID(),
                    "Cập nhật người dùng",
                    "Admin cập nhật user ID: " + userID + ", họ tên: " + fullName
                );
            }

        } else {
            request.setAttribute("error", "Cập nhật thất bại!");
        }

        request.getRequestDispatcher("view/AdminEditUser.jsp").forward(request, response);
    }
}
