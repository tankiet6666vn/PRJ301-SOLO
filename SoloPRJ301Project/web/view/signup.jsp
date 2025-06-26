<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>CHARITY CLUB - Sign Up</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico.png">
    <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" />

    <style>
        body {
            height: 100vh;
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(-45deg, #ff9a9e, #fad0c4, #fbc2eb, #a18cd1);
            background-size: 400% 400%;
            animation: gradientBG 15s ease infinite;
        }

        @keyframes gradientBG {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .login-page {
            border-radius: 1rem;
        }

        .btn-primary {
            background-color: #dc3545;
            border: none;
        }

        .btn-primary:hover {
            background-color: #bb2d3b;
        }

        .alert {
            border-radius: 0.5rem;
        }
    </style>
</head>
<body>
<section class="d-flex align-items-center justify-content-center" style="min-height: 100vh;">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-8">
                <img src="<%= request.getContextPath() %>/assets/images/charity.png" height="60" class="mx-auto d-block" alt="">
                <div class="card login-page bg-white shadow mt-4 rounded border-0">
                    <div class="card-body">
                        <h4 class="text-center">Sign Up</h4>

                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success text-center mt-3">${successMessage}</div>
                        </c:if>

                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger text-center mt-3">${errorMessage}</div>
                        </c:if>

                        <form action="<%= request.getContextPath() %>/signup" method="post" class="login-form mt-4">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Full Name <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" name="fullName" placeholder="Full Name" required>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Username <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" name="username" placeholder="Username" required>
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="mb-3">
                                        <label class="form-label">Your Email <span class="text-danger">*</span></label>
                                        <input type="email" class="form-control" name="email" placeholder="Email" required>
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="mb-3">
                                        <label class="form-label">Password <span class="text-danger">*</span></label>
                                        <input type="password" class="form-control" name="password" placeholder="Password" required>
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="mb-3">
                                        <label class="form-label">Security Question <span class="text-danger">*</span></label>
                                        <select name="securityQuestion" class="form-control" required>
                                            <option value="">-- Select a question --</option>
                                            <option value="What is your favorite color?">What is your favorite color?</option>
                                            <option value="What is your pet's name?">What is your pet's name?</option>
                                            <option value="What is your mother's maiden name?">What is your mother's maiden name?</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="mb-3">
                                        <label class="form-label">Answer <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" name="securityAnswer" placeholder="Your Answer" required>
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="mb-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" id="accept-tnc-check" required>
                                            <label class="form-check-label" for="accept-tnc-check">
                                                I Accept <a href="#" class="text-primary">Terms And Conditions</a>
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="d-grid">
                                        <button class="btn btn-primary">Register</button>
                                    </div>
                                </div>

                                <div class="text-center mt-3">
                                    <small class="text-dark">Already have an account?</small>
                                    <a href="<%= request.getContextPath() %>/view/Login.jsp" class="text-dark fw-bold">Sign in</a>
                                </div>
                            </div>
                        </form>
                    </div> 
                </div> 
            </div> 
        </div> 
    </div> 
</section>

<script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
