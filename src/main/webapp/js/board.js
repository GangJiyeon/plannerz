$(document).ready(function () {

    $(".update_comment_con").css('display', 'none');
    $(".add_comment_con").css('display', 'none');

    $(".update_comment_btn").on("click", function (){
        $(".update_comment_con").css('display', 'block');
        $(".com_area").css('display', 'none');

    })
    $(".add_comment_btn").on("click", function (){
        $(".add_comment_con").css('display', 'block');
    })


});