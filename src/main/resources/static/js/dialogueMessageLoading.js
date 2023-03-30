$(window).scroll(function() {
    console.log('scrolling');
    if($(window).scrollTop() + $(window).height() > $(document).height() - 100) {
        console.log('reached bottom');
        var currentPage = parseInt($('#currentPage').val()) + 1;
        var dialogueId = parseInt($('#dialogueId').val());
        $.ajax({
            url: '/dialogue-' + dialogueId + '?pageNumber=' + currentPage,
            type: 'GET',
            success: function(data) {
                var messagesHtml = $(data).find('#message-container').html();
                $('#message-container').append(messagesHtml);
                $('#currentPage').val(currentPage);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    }
});