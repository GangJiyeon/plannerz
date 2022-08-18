$(document).ready(function(){

    $(".project_middle").css('display', 'none');
    $(".project_middle:first").css('display','block');
    $(".project_item:first").css('border-radius', '12px');
    $(".project_item:first").css('background-color', 'rgb(242, 244, 247)');
    $(".project_item:first").css('border', 'solid 1px rgb(195, 204, 217)');

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
        $("#add_middle_btn").before("<div class=\"input_project\"><form:input path=\"middle_title\">중간목표</form:input></div>");
    })

});