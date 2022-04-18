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

function SubmitForm()
{
    document.forms['registerForm'].action='/user/registerSubmit';
    document.forms['registerForm'].submit();

    document.forms['registerForm'].action='/login/loginSubmit';
    document.forms['registerForm'].submit();
    return false;
}