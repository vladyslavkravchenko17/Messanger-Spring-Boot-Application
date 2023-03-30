<#include "security.ftl">

<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #151515">
    <a class="navbar-brand" href="/">Vl-Messenger</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/dialogues">Dialogues</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/chat">Global chat</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/profile">My profile</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/admin">Admin panel</a>
                </li>
            </#if>
        </ul>

        <div class="navbar-text mr-3"><strong style="color: white">${name}</strong></div>
        <form action="/logout" method="post">
            <button class="btn btn--pill btn--green" style="color: whitesmoke; background-color: #4dae3c;">Sign out</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>    </div>
</nav>