<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../pub/css/songForm.css">
    <title>Document</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<c:if test="${empty form.id}">
    <h1 class = "topflag">Create Show</h1>
</c:if>
<c:if test="${not empty form.id}">
    <h1 class = "topflag">Edit Show</h1>
</c:if>
<form id = "showform" action = "/admin/showForm/registerShow" method ="get">
    <input type = "hidden" name = "id" value = "${form.id}">
    <div class="mb-3">
        <label for="InputDate" class="form-label">Show Date:</label>
        <input type="date" class="form-control" id="InputDate" name = "date" value = "${form.date}">
    </div>
    <div class="mb-3">
        <label for="InputLocation" class="form-label">Show Location:</label>
        <input type="text" class="form-control" id="InputLocation" name = "location" value = "${form.location}">
    </div>
    <div class="mb-3">
        <label for="InputTime" class="form-label">Show Time:</label>
        <input type="text" class="form-control" id="InputTime" name = "time" value = "${form.time}">
    </div>
    <button type="submit" class="btn btn-light">Submit</button>
    <c:if test="${not empty form.id}">
    <a href="/admin/removeShow/${form.id}" class="btn btn-danger" tabindex="-1" role="button">Delete Show</a>
    </c:if>
</form>
<c:if test="${not empty form.id}">
    <h3 id = "sethead">Set List</h3>
<table class = "table">
    <tr>
        <th>Name</th>
        <th></th>
    </tr>
    <c:forEach items="${showSongs}" var ="showSong">

        <tr scope ="row">
            <td>${showSong.name}</td>
            <td><a class = "removesong" href = "/admin/removeSong/${form.id}/${showSong.id}">Remove Song</a></td>
        </tr>
    </c:forEach>
</table>
    <div class="dropdown">
        <a class="btn btn-sm btn-info dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
            Add Song
        </a>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
            <c:forEach items="${songs}" var ="song">
            <li><a class="dropdown-item" href="/admin/addSong/${form.id}/${song.id}">"${song.name}"</a></li>
            </c:forEach>
        </ul>
    </div>
</c:if>
</body>
<style>
    body {
        background-color: black;
    }

    .topflag {
        display: flex;
        justify-content: center;
        color: black;
        text-shadow: 0 0 3px white;
        font-size: 60px;
        margin-bottom: 70px;
    }

    #showform{
        color: white;
    }

    #showform > div > input {
        background-color: lightskyblue;
    }

    .table {
        margin-top: 20px;
        color: white;
        text-align: center;
    }

    .table > tbody > tr > td {
        border: none;
    }

    #sethead{
        display: flex;
        right: 30px;
        justify-content: center;
        color: black;
        text-shadow: 0 0 3px white;
        font-size: 30px;
        margin-top: 20px;
    }

    .dropdown {
        display: flex;
        justify-content: center;

    }

    .dropdown-menu {
        background-color: lightskyblue;
    }

    .removesong {
        color: red;
    }

</style>
