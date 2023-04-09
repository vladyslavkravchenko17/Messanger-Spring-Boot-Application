<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Colorlib Templates">
        <meta name="author" content="Colorlib">
        <meta name="keywords" content="Colorlib Templates">

        <!-- Title Page-->
        <title>VL CHAT</title>

        <!-- Icons font CSS-->
        <link href="css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
        <link href="css/font-awesome.min.css" rel="stylesheet" media="all">
        <link rel="icon" type="image/png" href="/images/icon.png" />
        <!-- Font special for pages-->
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
              rel="stylesheet">

        <!-- Vendor CSS-->
        <link href="css/select2.min.css" rel="stylesheet" media="all">
        <link href="css/daterangepicker.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="css/main.css" rel="stylesheet" media="all">

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body style="background-color: black; color: #F3FFF4">
    <#include "navbar.ftl">
    <div class="container mt-5">
        <#nested>
    </div>


    <script src="js/select2.min.js"></script>
    <script src="js/moment.min.js"></script>
    <script src="js/daterangepicker.js"></script>

    <!-- Main JS-->
    <script src="js/global.js"></script>
    </body>

    </html>
</#macro>