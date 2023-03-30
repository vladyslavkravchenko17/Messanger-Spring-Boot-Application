<#import "common.ftl" as c>
<@c.page>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="js/dialogueMessageLoading.js"></script>


    <div class="panel-heading" style="background-color: #0E0E0E" xmlns="http://www.w3.org/1999/html">
        <header class="panel-title">
            <div>
                <strong style="color: #e4fae4"><h3>
                        <a href="/user-${interlocutor.username}">
                            <img style="border-radius: 50%" width="100px" height=100px"
                                 src="/images/${interlocutor.avatarImage}">
                        </a>
                        <a style="color: whitesmoke;" href="/user-${interlocutor.username}">
                            ${interlocutor.username}
                        </a>
                    </h3></strong>
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
                    <input type="text" name="text" placeholder="What you wanna say?"/>
                    <input style="color: whitesmoke; background-color: #4dae3c; margin-top: 10px; margin-bottom: 10px;"
                           class="btn btn--pill btn--green" type="submit" value="Say"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
                <form>
                    <input type="hidden" id="currentPage" value="${currentPage}">
                    <input type="hidden" id="dialogueId" value="${dialogueId}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
            </div>
            <div id="message-container">
                <#list messages as message>
                    <div style="margin-top: 10px;">
                        <table style="margin-bottom: 15px">
                            <tr>
                                <th width="60px">
                                    <a href="/user-${message.getAuthorUsername()}">
                                        <img style="border-radius: 50%" width="50px" height="50px"
                                             src="/images/${message.getAuthorAvatar()}">
                                    </a>
                                </th>
                                <th>
                                    <div style="margin-bottom: 5px">
                                        <strong>
                                            <a style="color: whitesmoke;" href="/user-${message.getAuthorUsername()}">
                                                ${message.getAuthorUsername()}
                                            </a>
                                        </strong>
                                        <small>(${message.getFormattedDateTime()})</small></div>
                                    <div><p style="word-wrap: break-word;">${message.text}</p></div>
                                </th>
                            </tr>
                        </table>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</@c.page>
