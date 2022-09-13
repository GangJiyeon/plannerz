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

    //투두리스트 첫 페이지
    @GetMapping("/todolist")
    public String todolist(Model model, HttpSession session) {

        //1. 세션에 저장딘 사용자 아이디 가져오기
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        //2. 해당 사용자 아이디로 작성된 투두리스트 조회 후 List(todolistInfoList)에 저장하기
        List<Todolist_info> todolistInfoList = todolistService.select_List(user_id);
        List<List<Todolist_item_info>> listItemInfoList = new ArrayList<>();

        if(todolistInfoList != null){
            //3. 해당 리스트의 아이템 조회 후 List(listItemInfoList)에 저장하기
            for (Todolist_info nth : todolistInfoList) {
                listItemInfoList.add(todolistService.select_ListItem(nth.getList_idx()));
            }

            //4. view로 List 전달하기
            model.addAttribute("todolistInfoList", todolistInfoList);
            model.addAttribute("listItemInfoList", listItemInfoList);
        }


        return "todolist";
    }

    //투두리스트 아이템 삭제하기
    @GetMapping("/todolist/delete")
    public String list_delete(@RequestParam(value = "list_idx") Integer list_idx,
                              @RequestParam(value = "item_idx") Integer item_idx,
                              HttpSession session, Model model) {

        //1. 해당 투두리스트 + 아이템 idx로 조회한 투두리스트 아이템 삭제하기
        todolistService.delete_listItem(list_idx, item_idx);

        //2. 뷰에 전달할 내용 구성 후 전달
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

    //투두리스트 리스트 삭제하기
    @GetMapping("/todolist/delete/total")
    public String list_delete_total(@RequestParam(value = "list_idx") Integer list_idx,
                                    HttpSession session, Model model) {

        //1. 해당 리스트 idx로 조회된 투두리스트 삭제하기
        todolistService.delete_list(list_idx);

        //2. 뷰에 전달할 내용 구성 후 전달하기
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

    //투두리스트 작성하기
    @PostMapping("/todolist/form")
    public String list_form(HttpSession session,
                            @RequestParam(value = "list_title") String list_title,
                            @RequestParam(value = "item_title") String item_title, Model model) {

        //1. 사용자 정보 가져오기
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        //2. 투두리스트 리스트 등록하기
        Integer list_idx = todolistService.insert_todolist(list_title, user_id);

        //3. 해당 리스트에 등록할 전달받은 리스트 아이템 배열 구성하기
        String[] item_titles = item_title.split(",");

        //4. 투두리스트 아이템 인스턴스 생성
        Todolist_item_info todolist_item_info = new Todolist_item_info();

        //5. 투두리스트 아이템 등록하기
        for (int i = 0; i < item_titles.length; i++) {
            todolist_item_info.setDone(false);
            todolist_item_info.setList_idx(list_idx);
            todolist_item_info.setItem_title(item_titles[i]);
            todolistService.insert_todolist_item(todolist_item_info);
        }

        //6. 뷰에 전달할 내용 구성 후 전달하기
        List<Todolist_info> todolistInfoList = todolistService.select_List(user_id);
        List<List<Todolist_item_info>> listItemInfoList = new ArrayList<>();

        for (Todolist_info nth : todolistInfoList) {
            listItemInfoList.add(todolistService.select_ListItem(nth.getList_idx()));
        }

        model.addAttribute("todolistInfoList", todolistInfoList);
        model.addAttribute("listItemInfoList", listItemInfoList);

        return "todolist";
    }

    //투두리스트 수정 페이지 보여주기
    @GetMapping("/todolist/update")
    public String view_todolist_update(Model model, HttpSession session,
                                       @RequestParam(value = "list_idx") Integer list_idx) {

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
        model.addAttribute("list_idx", list_idx);

        return "todolist_update";
    }

    //투두리스트 수정
    @PostMapping("/todolist/update.do")
    public String list_update(Todolist todolist, HttpSession session, Model model) {

        Todolist_info todolist_info = new Todolist_info(todolist.getList_idx(), todolist.getList_title(), todolist.getUser_id());
        Todolist_item_info todolist_item_info = new Todolist_item_info(todolist.getList_item_idx(), todolist.getList_idx(), todolist.getItem_title(), todolist.getDone());

        String[] item_titles = todolist_item_info.getItem_title().split(",");
        String[] list_item_idx = todolist_item_info.getList_item_idx().split(",");
        System.out.println(todolist_item_info.getList_item_idx());
        Integer list_idx = todolistService.update_todolist(todolist_info);

        for (int i = 0; i < item_titles.length; i++) {
            todolist_item_info.setItem_title(item_titles[i]);
            todolist_item_info.setList_item_idx(list_item_idx[i]);
            todolistService.update_todolist_item(todolist_item_info);
        }


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

    //투두리스트 수정 시, 리스트 아이템 추가
    @GetMapping("/todolist/add/list_item")
    public String list_item_add(@RequestParam(value = "list_idx") Integer list_idx, HttpSession session,
                                Model model) {

        Todolist_item_info todolist_item_info = new Todolist_item_info();
        todolist_item_info.setItem_title("내용을 입력하세요");
        todolist_item_info.setList_idx(list_idx);
        todolistService.insert_todolist_item(todolist_item_info);


        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        List<Todolist_info> todolistInfoList = todolistService.select_List(user_id);
        List<List<Todolist_item_info>> listItemInfoList = new ArrayList<>();

        for (Todolist_info nth : todolistInfoList) {
            listItemInfoList.add(todolistService.select_ListItem(nth.getList_idx()));
        }

        model.addAttribute("todolistInfoList", todolistInfoList);
        model.addAttribute("listItemInfoList", listItemInfoList);
        model.addAttribute("todolist", new Todolist());

        return "todolist_update";
    }
}
