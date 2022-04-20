$(document).ready(function () {
    $('#registerForm').on("submit", function (event) {
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
var check = function () {
    if (document.getElementById('password').value ==
        document.getElementById('confirmPassword').value) {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = 'Password confirmed';
    } else {
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = 'Password does not match';
    }
}

var submitForm = async function () {
    await createUser();
    await loginUser();
}

function createUser() {
    document.forms['registerForm'].action = '/user/registerSubmit';
    document.forms['registerForm'].method = 'GET';
    document.forms['registerForm'].submit();
}

function loginUser() {
    document.forms['registerForm'].action = '/login/loginSubmit';
    document.forms['registerForm'].method = 'POST';
    document.forms['registerForm'].submit();
}