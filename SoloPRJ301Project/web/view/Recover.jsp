<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Recover Password - CHARITY CLUB</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico.png">
    <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" />
    <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" id="theme-opt" />

    <style>
        body {
            margin: 0;
            background: linear-gradient(-45deg, #ff9a9e, #fad0c4, #fbc2eb, #a18cd1);
            background-size: 400% 400%;
            animation: gradientBG 15s ease infinite;
            font-family: 'Segoe UI', sans-serif;
        }

        @keyframes gradientBG {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .custom-error-message {
            color: red;
            font-weight: bold;
            font-size: 1.1rem;
        }

        .custom-success-message {
            color: green;
            font-weight: bold;
            font-size: 1.1rem;
        }
    </style>
</head>
<body>
    <section class="d-flex align-items-center py-5" style="min-height: 100vh;">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-5 col-md-8">
                    <img src="<%= request.getContextPath() %>/assets/images/charity.png" height="60"
                         class="mx-auto d-block" alt="">
                    <div class="card login-page bg-white shadow mt-4 rounded border-0">
                        <div class="card-body">
                            <h4 class="text-center">Recover Password</h4>
                            <form action="<%= request.getContextPath() %>/recover" method="post" class="login-form mt-4">
                                <div class="mb-3">
                                    <label class="form-label">Username <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" name="username" placeholder="Enter your username" required>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Security Question <span class="text-danger">*</span></label>
                                    <select class="form-control" name="question" required>
                                        <option value="">-- Select a question --</option>
                                        <option value="What is your pet’s name?">What is your pet’s name?</option>
                                        <option value="What is your mother’s maiden name?">What is your mother’s maiden name?</option>
                                        <option value="What is your favorite color?">What is your favorite color?</option>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Answer <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" name="answer" placeholder="Enter your answer" required>
                                </div>

                                <div class="d-grid">
                                    <button type="submit" class="btn btn-primary">Verify</button>
                                </div>

                                <div class="text-center mt-3">
                                    <a href="<%= request.getContextPath() %>/view/Login.jsp" class="btn btn-outline-secondary">Back to Login</a>
                                </div>

                                <c:if test="${not empty error}">
                                    <div class="text-center mt-3">
                                        <span class="custom-error-message">${error}</span>
                                    </div>
                                </c:if>
                                <c:if test="${not empty success}">
                                    <div class="text-center mt-3">
                                        <span class="custom-success-message">${success}</span>
                                    </div>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
</body>
</html>
