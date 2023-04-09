<#import "common.ftl" as c>
<@c.page>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="js/chatMessageLoading.js"></script>

    <div class="panel-heading" style="background-color: #0E0E0E" xmlns="http://www.w3.org/1999/html">
        <header class="panel-title">
            <div class="text-center">
                <strong style="color: #e4fae4"><h3>Chat</h3></strong>
            </div>
        </header>
    </div>
    <div class="panel-body" style="background-color: #1a1a1a">
        <div>
            <div>
                <form id="message-form" method="post" action="/chat" enctype="multipart/form-data">
                    <#if textError??>
                        <div style="color: red;">*${textError}*</div>
                    </#if>
                    <input type="text" id="message-input" name="text" placeholder="What you wanna say?"/>
                    <label style="margin-top: 10px">Choose file to send:
                        <input type="file" name="messageFile" accept="*/*" multiple>
                    </label>
                    <input style="color: whitesmoke; background-color: #4dae3c; margin-top: 10px; margin-bottom: 10px;"
                           class="btn btn--pill btn--green" id="send-button" type="submit" value="Say"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
                <form>
                    <input type="hidden" id="currentPage" value="${currentPage}">
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
                                    <div>
                                        <#if message.files??>
                                            <#list message.files as file>
                                            <p style="word-wrap: break-word; margin-bottom: 0px"><a
                                                        href="/download/${file}">${file.substring(37)}</a>
                                            </p>
                                            </#list>
                                        </#if>
                                        <p style="word-wrap: break-word;">${message.text}</p>
                                    </div>
                                </th>
                            </tr>
                        </table>
                    </div>
                </#list>
            </div>
        </div>
    </div>

</@c.page>
