<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Login - CHARITY CLUB</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico.png">
    <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" />
    <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" id="theme-opt" />
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        .custom-error-message {
            color: red;
            font-weight: bold;
            font-size: 1.2rem;
        }
    </style>
</head>
<body>

<section class="bg-home d-flex bg-light align-items-center"
         style="background: url('<%= request.getContextPath() %>/assets/images/bg/bg-lines-one.png') center;">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-8">
                <img src="<%= request.getContextPath() %>/assets/images/charity.png" height="60"
                     class="mx-auto d-block" alt="">
                <div class="card login-page bg-white shadow mt-4 rounded border-0">
                    <div class="card-body">
                        <h4 class="text-center">Sign In</h4>
                        <form action="<%= request.getContextPath() %>/login" method="post" class="login-form mt-4">
                            <div class="mb-3">
                                <label class="form-label">Email <span class="text-danger">*</span></label>
                                <input type="email" class="form-control" name="email" placeholder="Email" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Password <span class="text-danger">*</span></label>
                                <input type="password" class="form-control" name="password" placeholder="Password" required>
                            </div>

                            <div class="d-grid gap-2 d-md-flex justify-content-md-between">
                                <button class="btn btn-primary">Sign in</button>
                                <a href="<%= request.getContextPath() %>/view/LoginAdmin.jsp" class="btn btn-outline-danger">Login as Admin</a>
                            </div>

                            <div class="text-center mt-3">
                                <p><small class="text-dark me-2">Don't have an account?</small>
                                    <a href="<%= request.getContextPath() %>/view/signup.jsp" class="text-dark fw-bold">Sign Up</a></p>

                                <a href="<%= request.getContextPath() %>/view/Recover.jsp" class="btn btn-outline-primary mt-2">Recover Password</a>
                            </div>

                            <c:if test="${not empty errorMessage}">
                                <div class="text-center mt-2">
                                    <span class="custom-error-message">${errorMessage}</span>
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
