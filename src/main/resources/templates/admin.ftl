<#import "common.ftl" as c>
<@c.page>
    <div class="panel-heading" style="background-color: #0E0E0E" xmlns="http://www.w3.org/1999/html">
        <header class="panel-title">
            <div class="text-center">
                <strong style="color: #e4fae4"><h3>Application users</h3></strong>
            </div>
        </header>
    </div>
    <div class="panel-body" style="background-color: #1a1a1a">
        <div id="message-container">
            <#list users as user>
                <div style="margin-top: 10px;">
                    <table style="margin-bottom: 15px">
                        <tr>
                            <th width="60px">
                                <a href="/user-${user.username}">
                                    <img style="border-radius: 50%" width="50px" height="50px"
                                         src="/images/${user.avatarImage}">
                                </a>
                            </th>
                            <th>
                                <div style="margin-bottom: 5px">
                                    <strong>
                                        <a style="color: whitesmoke;" href="/user-${user.username}">
                                            ${user.username}
                                        </a>
                                    </strong>
                                </div>
                            </th>
                        </tr>
                    </table>
                </div>
            </#list>
        </div>
    </div>
</@c.page>