<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet" href="../pub/css/registration.css">
</head>
<body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="vh-125 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col-12 col-lg-9 col-xl-7">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Registration</h3>

                        <form id ="registerForm" action = "/user/registerSubmit" method = "get">
                            <input type="hidden" name="id" value="${form.id}">
                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input type="text" name ="username" id="username" value = "${form.username}" class="form-control form-control-lg" />
                                        <label class="form-label" for="username">Username</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-2 pb-2">
                                    <div class="form-outline">
                                        <input type="text" name = "password" value = "${form.password}" id="password" class="form-control form-control-lg" />
                                        <label class="form-label" for="password">Password</label>
                                    </div>
                                </div>
                                <div class="col-md-6 mb-2 pb-2">
                                    <div class="form-outline">
                                        <input type="text" name = "confirmPassword" value = "${form.confirmPassword}"id="confirmPassword" class="form-control form-control-lg"
                                               onkeyup='check();'/>
                                        <label class="form-label" for="confirmPassword">Confirm Password</label>
                                    </div>
                                </div>
                            </div>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-12 mb-4">--%>
<%--                                    <h6 class="mb-2 pb-1">User Type: </h6>--%>
<%--                                    <div class="form-check form-check-inline">--%>
<%--                                        <input class="form-check-input" type="radio" name="inlineRadioOptions"--%>
<%--                                               id="admin" value="option1"--%>
<%--                                        />--%>
<%--                                        <label class="form-check-label" for="admin">Admin</label>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-check form-check-inline">--%>
<%--                                        <input--%>
<%--                                                class="form-check-input" type="radio" name="inlineRadioOptions"--%>
<%--                                                id="user" value="option2" checked />--%>
<%--                                        <label class="form-check-label" for="user">User</label>--%>
<%--                                    </div>--%>
<%--                                    <div class="form-check form-check-inline">--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                            <div class="row">
                                <div class="col-md-3 mt-4 pt-2">
                                    <input class="btn btn-outline-warning btn-lg" type="submit" value="Submit" />
                                </div>
                                <div class="col-md-3 mt-4 pt-2">
                                </div>
                                <div class="col-md-6 mt-4 pt-2" id="message">Welcome to the May Parker Band Page</div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
<script>
    $(document).ready(function() {
        $('#registerForm').on("submit", function(event){
            // event.preventDefault();
            if (
                $('#email').val() == '' ||
                $('#firstName').val() == '' ||
                $('#lastName').val() == '' ||
                $('#password').val() == '' ||
                $('#confirmPassword').val() == ''
            ) {
                alert("All fields are required in order to register");
                return false;
            }
        })
    });
    var check = function() {
        if (document.getElementById('password').value ==
            document.getElementById('confirmPassword').value) {
            document.getElementById('message').style.color = 'green';
            document.getElementById('message').innerHTML = 'Password confirmed';
        } else {
            document.getElementById('message').style.color = 'red';
            document.getElementById('message').innerHTML = 'Password does not match';
        }
    }
</script>
</html>