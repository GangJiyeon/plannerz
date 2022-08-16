$(document).ready(function(){

    $(".project_middle").css('display', 'none');
    $(".project_middle:first").css('display','block');


    $(".project_item").on("click", function (){
        let project_idx = $(this).attr('id');
        $(".project_middle:contains(#project_idx)").css('display','block')

    })

});