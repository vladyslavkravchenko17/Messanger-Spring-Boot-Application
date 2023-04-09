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
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="css/select2.min.css" rel="stylesheet" media="all">
    <link href="css/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="css/main.css" rel="stylesheet" media="all">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
    <body>
    <div class="page-wrapper bg-gra-01 p-t-180 p-b-100 font-poppins">
        <div class="wrapper wrapper--w780">
            <div class="card card-3">
                <div class="card-heading"></div>
                <div class="card-body">
                    <h2 class="title">Registration</h2>
                    <form action="/registration" method="post">
                        <div class="input-group">
                            <#if usernameError??>
                                <div style="color: darkred; font-size: 80%">*${usernameError}*</div>
                            </#if>
                            <input class="input--style-3 form-control ${(emailError??)?string('is-invalid', '')}"
                                   type="text" value="<#if user??>${user.username}</#if>" placeholder="Username" name="username">
                        </div>
                        <div class="input-group">
                            <#if passwordError??>
                                <div style="color: darkred; font-size: 80%">*${passwordError}*</div>
                            </#if>
                            <input class="input--style-3" type="password" placeholder="Password" name="password">
                        </div>
                        <div class="input-group">
                            <#if password2Error??>
                                <div style="color: darkred; font-size: 80%">*${password2Error}*</div>
                            </#if>
                            <input class="input--style-3" type="password" placeholder="Confirm password"
                                   name="password2">
                        </div>
                        <div class="p-t-10">
                            <input class="btn btn--pill btn--green" type="submit" value="Goooo" />
                        </div>
                        <a href="/login" style="color: white; display: flex;justify-content: center; margin-top: 20px">
                            I already have an account</a>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
