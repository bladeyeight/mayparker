<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../pub/css/bandmembers.css">
    <title>Document</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<div id = "topflag">
    <h1 id ="meetstyle">Meet <span id = "underline">The Band</span>
        <img id ="wild" src="https://scontent-iad3-1.xx.fbcdn.net/v/t1.6435-9/52991424_300738343948514_4145273516637290496_n.jpg?_nc_cat=111&ccb=1-5&_nc_sid=e3f864&_nc_ohc=03gziW-gPZkAX-1BIhL&_nc_ht=scontent-iad3-1.xx&oh=00_AT-HrKIYSmMl_4UTF5zGvcw8nXiXoscNqEgIoJqqG6f_Tg&oe=6267F760" alt="">
    </h1>
</div >
<!-- Default dropend button -->
<div class="btn-group dropdown">
    <button type="button" class="btn btn-info btn-md dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
        Menu
    </button>
    <ul class="dropdown-menu">
        <!-- Dropdown menu links -->
        <li><a class="dropdown-item" href="../index">Home</a></li>
        <li><a class="dropdown-item" href="../songs">Songs</a></li>
        <li><a class="dropdown-item" href="../shows">Upcoming Shows</a></li>
        <li><hr class="dropdown-divider"></li>
        <li><a class="dropdown-item" href="../user/register">Create Account</a></li>
        <sec:authorize access="!isAuthenticated()">
            <li><a class="dropdown-item" href="../login/login">Login</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li><a class="dropdown-item" href="../login/logout">Logout</a></li>
        </sec:authorize>
    </ul>
</div>
<div id = "sully">
    <img id = "sullypic" src="https://i.imgur.com/25HXcPc_d.jpg?maxwidth=520&shape=thumb&fidelity=high" alt="">
    Sully
    <h3>Born: Washington DC 1995</h3>
    <h3>Position: Drummer</h3>
    <h3>Bio: Sully is a rock drummer who has been playing since he was 10 years old.  He draws influences from his father
        ,Chuck Sullivan, Travis Barker, and Stuart Copeland.
    </h3>
</div>
<div id = "brett">
    Brett
    <img id = "brettpic" src="https://scontent-iad3-1.xx.fbcdn.net/v/t1.6435-9/176558835_10215346556735389_4954931198730839208_n.jpg?stp=dst-jpg_s851x315&_nc_cat=101&ccb=1-5&_nc_sid=da31f3&_nc_ohc=Q-O54t02vGIAX8X0pIz&_nc_ht=scontent-iad3-1.xx&oh=00_AT90mkarQ0ehCXUI1CwU257OOj03qttvLB4thM0yFJUmkA&oe=62694B3A" alt="">
    <div id = "bretttext">
        <h3>Born: Ipsum monkim rapt 1998</h3>
        <h3>Position: Lead Guitar</h3>
        <h3>Bio:  Dalkit rapt nights toom elfim rightum sollum intend harkl ohm</h3>
    </div>
</div>
<div id = "michael">
    Michael
    <img id = "michaelpic" src="https://scontent-iad3-1.xx.fbcdn.net/v/t1.6435-9/74209213_419920958696918_6119767680229572608_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=8cmBwBezJuIAX-U_PjT&_nc_ht=scontent-iad3-1.xx&oh=00_AT--Wvo8R4KZuupy2GsUae6knEmQswuz0ihzmk6cVp6SlQ&oe=626ABF5F" alt="">
    <div id = "michaeltext">
        <h3>Born: Ipsum monkim rapt 1998</h3>
        <h3>Position: Bass</h3>
        <h3>Bio:  Dalkit rapt nights toom elfim rightum sollum intend harkl ohm</h3>
    </div>
</div>
<div id = "sam">
    Sam
    <img id = "sampic" src="https://scontent-iad3-1.xx.fbcdn.net/v/t31.18172-8/16819205_1415046815207172_5714370087721865876_o.jpg?_nc_cat=109&ccb=1-5&_nc_sid=174925&_nc_ohc=rPBiALZD7BsAX9cW4pM&tn=fTMtQc0XuLVZBa32&_nc_ht=scontent-iad3-1.xx&oh=00_AT-vns9gRMldXTOyxuHmb7lQRnyj4Pw3UlEChfUAY4zEcQ&oe=626A9620" alt="">
    <div id = "samtext">
        <h3>Born: New York City 1991</h3>
        <h3>Position: Singer</h3>
        <h3>Bio: Enjoys singing and having a good time.  Usually one leads to the other.</h3>
    </div>
</div>
<div id = "tyler">
    Tyler
    <img id = "tylerpic" src="https://scontent-iad3-1.xx.fbcdn.net/v/t1.18169-9/17903849_1461026887291797_6293715898692262136_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=y6scnrX9i0oAX92xHXo&_nc_ht=scontent-iad3-1.xx&oh=00_AT8K4ZdH-QPnJLOsOjtlyvHh2Z0vDkH9XrK0dMLcUq_QLA&oe=626AA781" alt="">
    <div id = "tylertext">
        <h3>Born: Ipsum monkim rapt 1998</h3>
        <h3>Position: Rhythm Guitar/ Manager</h3>
        <h3>Bio:  Dalkit rapt nights toom elfim rightum sollum intend harkl ohm</h3>
    </div>
</div>

</body>
</html>