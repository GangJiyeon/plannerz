package ToDoList.Service;

import ToDoList.Dao.TODOLIST_TB_Dad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodolistService {

    @Autowired
    public TODOLIST_TB_Dad todolist_tb_dad;

    public void setTodolist_tb_dad(TODOLIST_TB_Dad todolist_tb_dad){
        this.todolist_tb_dad = todolist_tb_dad;
    }

}
