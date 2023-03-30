<#import "common.ftl" as c>
<@c.page>
    <div class="panel-heading" style="background-color: #0E0E0E" xmlns="http://www.w3.org/1999/html">
        <header class="panel-title">
            <div class="text-center">
                <strong style="color: #e4fae4"><h3>Dialogues</h3></strong>
            </div>
        </header>
    </div>
    <div class="panel-body" style="background-color: #1a1a1a">
        <div>
            <div>
                <form method="post">
                    <#if textError??>
                        <div style="color: red;">*${textError}*</div>
                    </#if>
                    <input type="text" name="username" placeholder="Who you wanna message to?"/>
                    <input style="color: whitesmoke; background-color: #4dae3c; margin-top: 10px; margin-bottom: 10px;"
                           class="btn btn--pill btn--green" type="submit" value="Create new dialogue"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
            </div>
            <#list dialogues as dialogue>
                <div style="margin-top: 10px; border-bottom: 2px solid #444444;">
                    <a href="/dialogue-${dialogue.id}" style="text-decoration: none; color: white">
                        <table style="margin-bottom: 15px">
                            <tr>
                                <th width="60px" style="margin-left: 15px">
                                    <img style="border-radius: 50%;" width="50px" height="50px"
                                         src="/images/${dialogue.interlocutorUser.avatarImage}">
                                </th>
                                <th>
                                    <div style="margin-top: 10px">
                                        <strong>
                                            ${dialogue.interlocutorUser.username}
                                        </strong>
                                        <div>
                                            <p style="word-wrap: break-word;">
                                                <#if dialogue.lastMessage??>
                                                    ${dialogue.lastMessage.text}
                                                    <small>${dialogue.lastMessage.getFormattedDateTime()}</small>
                                                <#else>
                                                    <em>No messages yeeet....</em>
                                                </#if>
                                            </p>
                                        </div>
                                    </div>
                                </th>
                            </tr>
                        </table>
                    </a>
                </div>
                <br>
            </#list>
        </div>
    </div>
</@c.page>
