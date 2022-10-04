$(document).ready(function () {

    $(".con").css('display', 'none');
    $(".con:first").css('display', 'block');
    $(".project_item:first").css('border-radius', '12px');
    $(".project_item:first").css('background-color', 'rgb(242, 244, 247)');
    $(".project_item:first").css('border', 'solid 1px rgb(195, 204, 217)');

    $(".project_item").on("click", function () {
        $(".project_item").css('border-radius', 'none');
        $(".project_item").css('background-color', 'white');
        $(".project_item").css('border', 'none');

        $(this).css('border-radius', '12px');
        $(this).css('background-color', 'rgb(242, 244, 247)');
        $(this).css('border', 'solid 1px rgb(195, 204, 217)');

        let todolist_idx = $(this).attr('id');
        $(".con").css('display', 'none');

        //$("#project_contents")
        $(".con").filter('#' + todolist_idx).css('display', 'block');

    })

    $("#add_todolist_btn").on("click", function () {
        $("#add_content").css('display', 'block');
    })

    $(".delete_btn").on("click", function () {
        let param = $(this).attr('id');
        alert(param);
        location.href = "/Z/todolist/delete?" + param;
    })

    $(".total_delete_btn").on("click", function () {
        let param = $(this).attr('id');
        location.href = "/Z/todolist/delete/total?" + param;
    })

    $(".total_update_btn").on("click", function () {
        let param = $(this).attr('id');
        alert(param);
        location.href = "/Z/todolist/update?" + param;
    })


    $("#add_todolist_item").on("click", function () {
        let param = $(this).attr('class');
        location.href = "/Z/todolist/add/list_item?" + param;
    })

    $("#add_todolist_input").on("click", function (){
        $("#here").before("<input name=\"item_title\" placeholder=\"투두리스트 내용\">");
    })

});