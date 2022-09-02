package ToDoList.Controller;

import ToDoList.Dto.Todolist;
import ToDoList.Dto.Todolist_info;
import ToDoList.Dto.Todolist_item_info;
import ToDoList.Service.TodolistService;
import User.Dto.LoginSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TodolistController {

    @Autowired
    private TodolistService todolistService;

    public void setTodolistService(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    @GetMapping("/todolist")
    public String todolist(Model model, HttpSession session) {


        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        List<Todolist_info> todolistInfoList = todolistService.select_List(user_id);
        List<List<Todolist_item_info>> listItemInfoList = new ArrayList<>();

        for (Todolist_info nth : todolistInfoList) {
            listItemInfoList.add(todolistService.select_ListItem(nth.getList_idx()));
        }

        model.addAttribute("todolistInfoList", todolistInfoList);
        model.addAttribute("listItemInfoList", listItemInfoList);

        return "todolist";
    }

    @GetMapping("/todolist/delete")
    public String list_delete(@RequestParam(value = "list_idx") Integer list_idx,
                              @RequestParam(value = "item_idx") Integer item_idx) {

        todolistService.delete_listItem(list_idx, item_idx);
        return "todolist";
    }

    @GetMapping("/todolist/delete/total")
    public String list_delete_total(@RequestParam(value = "list_idx") Integer list_idx) {

        todolistService.delete_list(list_idx);
        return "todolist";
    }

    @PostMapping("/todolist/form")
    public String list_form(HttpSession session,
                            @RequestParam(value = "list_title") String list_title,
                            @RequestParam(value = "item_title") String item_title) {

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        String[] item_titles = item_title.split(",");

        Integer list_idx = todolistService.insert_todolist(list_title, user_id);

        Todolist_item_info todolist_item_info = new Todolist_item_info();

        for(int i=0; i<item_titles.length; i++){
            todolist_item_info.setDone(false);
            todolist_item_info.setList_idx(list_idx);
            todolist_item_info.setItem_title(item_titles[i]);

            todolistService.insert_todolist_item(todolist_item_info);
        }


        return "todolist";
    }

    @GetMapping("/todolist/update")
    public String view_todolist_update(Model model, HttpSession session){
        model.addAttribute("todolist", new Todolist());
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        model.addAttribute("loginSession", loginSession);

        List<Todolist_info> todolistInfoList = todolistService.select_List(loginSession.getUser_id());
        List<List<Todolist_item_info>> listItemInfoList = new ArrayList<>();


        for (Todolist_info nth : todolistInfoList) {
            listItemInfoList.add(todolistService.select_ListItem(nth.getList_idx()));
        }

        model.addAttribute("todolistInfoList", todolistInfoList);
        model.addAttribute("listItemInfoList", listItemInfoList);

        return "todolist_update";
    }

    @PostMapping("/todolist/update.do")
    public String list_update(Todolist todolist) {

        Todolist_info todolist_info = new Todolist_info(todolist.getList_idx(), todolist.getList_title(), todolist.getUser_id());
        Todolist_item_info todolist_item_info = new Todolist_item_info(todolist.getList_item_idx(),todolist.getList_idx(), todolist.getItem_title(), todolist.getDone());

        String[] item_titles = todolist_item_info.getItem_title().split(",");
        String[] list_item_idx = todolist_item_info.getList_item_idx().split(",");
        System.out.println(todolist_item_info.getList_item_idx());
        Integer list_idx = todolistService.update_todolist(todolist_info);

        for(int i=0; i<item_titles.length; i++){
            todolist_item_info.setItem_title(item_titles[i]);
            todolist_item_info.setList_item_idx(list_item_idx[i]);
            todolistService.update_todolist_item(todolist_item_info);
        }


        return "todolist";
    }

    @GetMapping("/todolist/add/list_item")
    public String list_item_add(@RequestParam(value = "list_idx") Integer list_idx){

        Todolist_item_info todolist_item_info = new Todolist_item_info();
        todolist_item_info.setItem_title("내용을 입력하세요");
        todolist_item_info.setList_idx(list_idx);
        todolistService.insert_todolist_item(todolist_item_info);
        return "todolist_update";
    }
}
