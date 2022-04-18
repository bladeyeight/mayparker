<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../pub/css/shows.css">
    <title>Document</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<div id = "topflag">
    <h1 id ="meetstyle"><span id = "specwords">Upcoming</span> Shows</h1>
</div >
<div class="btn-group dropdown">
    <button type="button" class="btn btn-info btn-md dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
        Menu
    </button>
    <ul class="dropdown-menu">
        <!-- Dropdown menu links -->
        <li><a class="dropdown-item" href="../index">Home</a></li>
        <li><a class="dropdown-item" href="../bandmembers">BandMembers</a></li>
        <li><a class="dropdown-item" href="../songs">Songs</a></li>
        <li><hr class="dropdown-divider"></li>
        <li><a class="dropdown-item" href="../user/register">Create Account</a></li>
        <li><a class="dropdown-item" href="../login/login">Login</a></li>
    </ul>
</div>
<a id ="createshow" class = "btn btn-info btn-md" href = "admin/showForm">Create Show</a>
<table class = "table">
    <tr>
        <th>Location</th>
        <th>Date</th>
        <th></th>
    </tr>
    <c:forEach items="${shows}" var ="show">

        <tr scope ="row">
            <td>${show.location}</td>
            <td>${show.date}</td>
            <td><a href = "/admin/editShow/${show.id}">More Info</a></td>
        </tr>
    </c:forEach>
</table>
</body>