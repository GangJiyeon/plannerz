package ToDoList.Controller;

import ToDoList.Service.TodolistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodolistController {

    @Autowired
    private TodolistService todolistService;

    public void setTodolistService(TodolistService todolistService){
        this.todolistService = todolistService;
    }
    @GetMapping("/todolist")
    public String todolist(){
        return "todolist";
    }
}
