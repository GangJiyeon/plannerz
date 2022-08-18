$(document).ready(function(){

    $(".con").css('display', 'none');
    $(".con:first").css('display','block');

    $(".project_item").on("click", function (){
        $(".project_item").css('border-radius', 'none');
        $(".project_item").css('background-color', 'white');
        $(".project_item").css('border', 'none');

        $(this).css('border-radius', '12px');
        $(this).css('background-color', 'rgb(242, 244, 247)');
        $(this).css('border', 'solid 1px rgb(195, 204, 217)');

        let todolist_idx = $(this).attr('id');
        $(".con").css('display', 'none');

        $("#project_contents")
        $(".con").filter('#'+todolist_idx).css('display','block');

    })

    $("#add_todolist_btn").on("click", function (){
        $("#add_content").css('display','block');
    })

    $(".delete_btn").on("click", function (){
        let param = $(this).attr('id');
        location.href = "/Z/todolist/delete?" +param;
    })
});