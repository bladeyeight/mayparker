<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../pub/css/songs.css">
    <title>Document</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<div id = "topflag">
    <h1 id ="meetstyle">Songs</h1>
</div >
<!-- Default dropend button -->
<div class="btn-group dropdown">
    <button type="button" class="btn btn-warning btn-md dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
        Menu
    </button>
    <ul class="dropdown-menu">
        <!-- Dropdown menu links -->
        <li><a class="dropdown-item" href="../index">Home</a></li>
        <li><a class="dropdown-item" href="../bandmembers">BandMembers</a></li>
        <li><a class="dropdown-item" href="#">Upcoming Shows</a></li>
        <li><hr class="dropdown-divider"></li>
        <li><a class="dropdown-item" href="../user/register">Create Account</a></li>
        <li><a class="dropdown-item" href="../login/login">Login</a></li>
    </ul>
</div>
<table class = "table">
    <tr>
        <th>Name</th>
        <th># of Shows</th>
        <th>Edit</th>
    </tr>
    <c:forEach items="${songs}" var ="song">
    <c:forEach items="${sLength}" var ="length">
        <tr scope ="row">
            <td>${song.name}</td>

            <td>${length}</td>

            <td><a href = "/user/edit/${song.id}">Edit</a></td>
        </tr>
    </c:forEach>
    </c:forEach>
</table>
</body>