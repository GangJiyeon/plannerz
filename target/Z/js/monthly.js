

$(document).ready(function(){

    $(".fc-event-main").mouseenter(function (){
        $(this).find(".fc-event-title-container").append("<button class='update'><i class=\"bi bi-pencil\"></i></button><button class='delete'>X</button>")

    })

    $(".fc-event-title-container").mouseout(function (){
        $(".delete").remove();
        $(".update").remove();
    })










});