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
    private String departmentName; 
    private String roleName;       

    public User() {
    }

    public User(int userID, String username, String passwordHash, String fullName, String email, int departmentID, int roleID) {
        this.userID = userID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.email = email;
        this.departmentID = departmentID;
        this.roleID = roleID;
    }

    public User(String username, String passwordHash, String fullName, String email, int departmentID, int roleID) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.email = email;
        this.departmentID = departmentID;
        this.roleID = roleID;
    }
    public User(int userID, String username, String passwordHash, String fullName, String email,
            int departmentID, int roleID, String securityQuestion, String securityAnswer) {
    this.userID = userID;
    this.username = username;
    this.passwordHash = passwordHash;
    this.fullName = fullName;
    this.email = email;
    this.departmentID = departmentID;
    this.roleID = roleID;
    this.securityQuestion = securityQuestion;
    this.securityAnswer = securityAnswer;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
