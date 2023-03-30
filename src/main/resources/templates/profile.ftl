<#include "security.ftl">
<#import "common.ftl" as c>

<@c.page>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/ font-awesome.min.css"/>
    <script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="https://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
    <div class="container">
        <div id="main">
            <div class="row" id="real-estates-detail">
                <div class="col-lg-4 col-md-4 col-xs-12">
                    <div class="">
                        <div class="panel-heading" style="background-color: #0E0E0E">
                            <header class="panel-title">
                            </header>
                        </div>
                        <div class="panel-body" style="background-color: #1f231f">
                            <div class="text-center" id="author">
                                <img width="250px" height="250px"
                                     src="/images/${user.avatarImage}">
                                <h3 style=""><strong>${user.username}</strong></h3>
                                <label style="font-size: 100%" class="label label-warning">
                                    <#if user.isAdmin()>admiinnn<#else>useeeerrr</#if>
                                </label>
                                <p>${user.description!'*No profile description*'}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-8 col-xs-12">
                    <div class="panel" style="background-color: #1f231f">
                        <div class="panel-body">
                            <div id="myTabContent" class="tab-content">
                                <div class="tab-pane active in" id="detail">
                                    <h4>Information</h4>
                                    <table style="background-color: #1f231f" class="table table-th-block">
                                        <tbody>
                                        <tr>
                                            <td style="background-color: #1f231f; color: #e4fae4" class="active">
                                                Registration date:
                                            </td>
                                            <td style="color: #e4fae4">${user.dateOfCreation}</td>
                                        </tr>
                                        <tr>
                                            <td style="background-color: #1f231f; color: #e4fae4" class="active">
                                                Information 1
                                            </td>
                                            <td style="color: #e4fae4">Some information</td>
                                        </tr>
                                        <tr>
                                            <td style="background-color: #1f231f; color: #e4fae4" class="active">
                                                Information 2
                                            </td>
                                            <td style="color: #e4fae4">Some information</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="button-container" style="margin-bottom: 20px">
                                    <button id="click" class="btn btn--radius btn--pill btn--green"
                                            style="color: whitesmoke; background-color: #4dae3c; width: 100%">Edit
                                        account info
                                    </button>
                                </div>
                                <div id="element" style="display: none; width: 100%">
                                    <form action="/profile" method="post" enctype="multipart/form-data">
                                        <div class="tab-pane active in" id="detail">
                                            <table style="background-color: #1f231f" class="table table-th-block">
                                                <tbody>
                                                <tr>
                                                    <td style="background-color: #1f231f; color: #e4fae4"
                                                        class="active">
                                                        Username:<br>
                                                        <small>(You must re-login after changing your
                                                            username!)</small>
                                                    </td>
                                                    <td style="color: #e4fae4"><input name="username"
                                                                                      placeholder="Username"
                                                                                      value="${user.username}"></td>
                                                </tr>
                                                <tr>
                                                    <td style="background-color: #1f231f; color: #e4fae4"
                                                        class="active">
                                                        Avatar image:
                                                    </td>
                                                    <td style="color: #e4fae4"><input type="file" name="file"
                                                                                      accept="image/png, image/jpeg">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="background-color: #1f231f; color: #e4fae4"
                                                        class="active">
                                                        Profile description:
                                                    </td>
                                                    <td style="color: #e4fae4; margin-top: 15px"><input type="text"
                                                                                                        name="description"
                                                                                                        placeholder="Profile description"
                                                                                                        value="${user.description!''}">
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <input style="color: whitesmoke; background-color: #4dae3c; width: 100%"
                                                   class="btn btn--pill btn--green"
                                                   type="submit" value="Save"/>
                                        </div>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div><!-- /.main -->
    </div><!-- /.container -->
</@c.page>