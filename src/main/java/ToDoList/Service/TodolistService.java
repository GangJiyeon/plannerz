package ToDoList.Service;

import ToDoList.Dao.TODOLIST_ITEM_TB_Dao;
import ToDoList.Dao.TODOLIST_TB_Dao;
import ToDoList.Dto.Todolist_info;
import ToDoList.Dto.Todolist_item_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodolistService {

    @Autowired
    public TODOLIST_TB_Dao todolist_tb_dad;
    @Autowired
    public TODOLIST_ITEM_TB_Dao todolist_item_tb_dao;

    public void setTodolist_tb(TODOLIST_TB_Dao todolist_tb_dad,
                                   TODOLIST_ITEM_TB_Dao todolist_item_tb_dao){
        this.todolist_tb_dad = todolist_tb_dad;
        this.todolist_item_tb_dao = todolist_item_tb_dao;
    }

    //투두리스트 목록 조회
    public List<Todolist_info> select_List(String user_id){
        List<Todolist_info> list = todolist_tb_dad.selectToDoList_byUserId(user_id);
        return list;
    }

    //투두리스트 아이템 목록 조회
    public List<Todolist_item_info> select_ListItem(Integer list_idx){
        List<Todolist_item_info> list = todolist_item_tb_dao.select_itemList_byListIdx(list_idx);
        return list;
    }

    //투두리스트 아이템 삭제하기
    public boolean delete_listItem(Integer list_idx, Integer list_item_idx){
        return todolist_item_tb_dao.delete_listItem(list_idx, list_item_idx);
    }

    //투두리스트 삭제하기
    public boolean delete_list(Integer list_idx){
        todolist_item_tb_dao.delete_listItems(list_idx);    //아이템 삭제
        return todolist_tb_dad.delete_list(list_idx);       //투두리스트 삭제
    }

    //투두리스트 등록하기
    public Integer insert_todolist(String list_title, String user_id){
        return todolist_tb_dad.insertTodolist(list_title, user_id);
    }

    //투두리스트 아이템 등록하기
    public boolean insert_todolist_item(Todolist_item_info todolist_item_info){
        return todolist_item_tb_dao.insert_todolistItem(todolist_item_info);
    }

    //투두리스트 수정하기(전체)
    public Integer update_todolist(Todolist_info todolist_info){
        return todolist_tb_dad.updateTodolist(todolist_info);
    }

    //투두리스트 수정하기(아이템)
    public void update_todolist_item(Todolist_item_info todolist_item_info){
        todolist_item_tb_dao.update_todolistItem(todolist_item_info);
    }

}
