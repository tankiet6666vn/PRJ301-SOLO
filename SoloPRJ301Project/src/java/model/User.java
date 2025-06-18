package model;

public class User {

    private int userID;
    private String username;
    private String passwordHash;
    private String fullName;
    private String email;
    private int departmentID;
    private int roleID;
    private String securityQuestion;
    private String securityAnswer;

    // Constructor mặc định
    public User() {
    }

    // Constructor đầy đủ (dùng khi load từ DB)
    public User(int userID, String username, String passwordHash, String fullName, String email, int departmentID, int roleID) {
        this.userID = userID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.email = email;
        this.departmentID = departmentID;
        this.roleID = roleID;
    }

    // Constructor khi đăng ký (ID tự tăng nên không cần truyền)
    public User(String username, String passwordHash, String fullName, String email, int departmentID, int roleID) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.email = email;
        this.departmentID = departmentID;
        this.roleID = roleID;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    // Getters và Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}
