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
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<%--Header changes whether editing or creating a song--%>
<c:if test="${empty form.id}">
    <h1 class="topflag">Create Song</h1>
</c:if>
<c:if test="${not empty form.id}">
    <h1 class="topflag">Edit Song</h1>
</c:if>
<%--Form for adding a song--%>
<form id="songform" action="/admin/songForm/registerSong" method="get">
    <input type="hidden" name="id" value="${form.id}">
    <span id="mywords">Song Name:</span> <input type="text" name="name" id="songname" value="${form.name}">
    <button id="subbutt" type="submit">Submit</button>
    <%--    Delete button if there is something to delete--%>
    <c:if test="${not empty form.id}">
        <a id="deletesong" href="/admin/remove/${form.id}">Delete Song</a>
    </c:if>
</form>
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
        margin-bottom: 140px;
    }

    #songform {
        display: flex;
        justify-content: center;
        color: white;

    }

    #mywords {
        position: relative;
        font-size: 30px;
        bottom: 5px;
    }

    #songname {
        margin-left: 40px;
        border: none;
        background-color: lightskyblue;
        margin-right: 40px;
        height: 36px;
    }

    #subbutt {
        position: relative;
        top: 5px;
        height: 33px;
        margin-right: 10px;
    }

    #deletesong {
        position: relative;
        color: red;
        top: 10px;
    }
</style>
</body>