$(document).ready(function(){

    $(".project_middle").css('display', 'none');
    $(".project_middle:first").css('display','block');

    $(".project_item").on("click", function (){
        $(".project_item").css('border-radius', 'none');
        $(".project_item").css('background-color', 'white');
        $(".project_item").css('border', 'none');

        $(this).css('border-radius', '12px');
        $(this).css('background-color', 'rgb(242, 244, 247)');
        $(this).css('border', 'solid 1px rgb(195, 204, 217)');

        let project_idx = $(this).attr('id');
        $(".project_middle").css('display', 'none');

        $("#project_contents")
        $(".project_middle").filter('#'+project_idx).css('display','block');

    })


    $("#add_project").on("click",function (){
        $("#project_input_area").css('display', 'block');
    })

    $("#add_middle_btn").on("click", function (){
        $("#add_middle_btn").before("<div class=\"input_project\"><input name=\"middle_title\" placeholder='중간목표를 입력하세요. '/></div>");
    })






});