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


/** 투두리스트 관련 컨트롤러
 1. 제공기능
 투두리스트 조회, 수정, 삭제, 등록
 투두리스트 아이템 삭제
 **/
@Controller
public class TodolistController {

    @Autowired
    private TodolistService todolistService;

    public void setTodolistService(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    //세션에서 사용자 id 가지고 오기 메서드
    public String find_userSession(HttpSession session){
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();
        return user_id;
    }

    //사용자 아이디로 작성된 투두리스트 조회 후 model에 저장
    public void select_todolist(Model model, String user_id){
        //1. 해당 사용자 아이디로 작성된 투두리스트 조회 후 List(todolistInfoList)에 저장하기
        List<Todolist_info> todolistInfoList = todolistService.select_List(user_id);
        List<List<Todolist_item_info>> listItemInfoList = new ArrayList<>();

        if(todolistInfoList != null){
            //2. 해당 리스트의 아이템 조회 후 List(listItemInfoList)에 저장하기
            for (Todolist_info nth : todolistInfoList) {
                listItemInfoList.add(todolistService.select_ListItem(nth.getList_idx()));
            }

            //3. view로 List 전달하기
            model.addAttribute("todolistInfoList", todolistInfoList);
            model.addAttribute("listItemInfoList", listItemInfoList);
        }
    }

    //투두리스트 첫 페이지
    @GetMapping("/todolist")
    public String todolist(Model model, HttpSession session) {

        //1. 뷰에 전달할 내용 구성 후 전달
        String user_id = find_userSession(session);
        select_todolist(model, user_id);

        return "todolist";
    }

    //투두리스트 아이템 삭제하기
    @GetMapping("/todolist/delete")
    public String list_delete(@RequestParam(value = "list_idx") Integer list_idx,
                              @RequestParam(value = "item_idx") Integer item_idx,
                              HttpSession session, Model model) {

        //1. 해당 투두리스트 + 아이템 idx로 조회한 투두리스트 아이템 삭제하기
        todolistService.delete_listItem(list_idx, item_idx);
        return "redirect:/todolist";
    }

    //투두리스트 리스트 삭제하기
    @GetMapping("/todolist/delete/total")
    public String list_delete_total(@RequestParam(value = "list_idx") Integer list_idx,
                                    HttpSession session, Model model) {

        //1. 해당 리스트 idx로 조회된 투두리스트 삭제하기
        todolistService.delete_list(list_idx);
        return "redirect:/todolist";
    }

    //투두리스트 작성하기
    @PostMapping("/todolist/form")
    public String list_form(HttpSession session,
                            @RequestParam(value = "list_title") String list_title,
                            @RequestParam(value = "item_title") String item_title, Model model) {


        //1. 사용자 정보 가져오기
        String user_id = find_userSession(session);

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

        return "redirect:/todolist";
    }

    //투두리스트 수정 페이지 보여주기
    @GetMapping("/todolist/update")
    public String view_todolist_update(Model model, HttpSession session,
                                       @RequestParam(value = "list_idx") Integer list_idx) {

        model.addAttribute("todolist", new Todolist());

        String user_id = find_userSession(session);
        select_todolist(model, user_id);

        return "todolist_update";
    }

    //투두리스트 수정
    @PostMapping("/todolist/update.do")
    public String list_update(Todolist todolist, HttpSession session, Model model,
                              @RequestParam("done") String done) {

        System.out.println(done);
        String[] doneList = done.split(",");
        Boolean[] dones = new Boolean[doneList.length];
        for (int i = 0; i<doneList.length; i++){
            dones[i] = Boolean.parseBoolean(doneList[i]);
        }
        Todolist_info todolist_info = new Todolist_info(todolist.getList_idx(), todolist.getList_title(), todolist.getUser_id());
        Todolist_item_info todolist_item_info = new Todolist_item_info(todolist.getList_item_idx(), todolist.getList_idx(), todolist.getItem_title(), todolist.getDone());

        String[] item_titles = todolist_item_info.getItem_title().split(",");
        String[] list_item_idx = todolist_item_info.getList_item_idx().split(",");
        System.out.println(todolist_item_info.getList_item_idx());
        Integer list_idx = todolistService.update_todolist(todolist_info);

        for (int i = 0; i < item_titles.length; i++) {
            todolist_item_info.setItem_title(item_titles[i]);
            todolist_item_info.setList_item_idx(list_item_idx[i]);
            todolist_item_info.setDone(dones[i]);
            todolistService.update_todolist_item(todolist_item_info);
        }

        return "redirect:/todolist";
    }

    //투두리스트 수정 시, 리스트 아이템 추가
    @GetMapping("/todolist/add/list_item")
    public String list_item_add(@RequestParam(value = "list_idx") Integer list_idx, HttpSession session,
                                Model model) {

        Todolist_item_info todolist_item_info = new Todolist_item_info();
        todolist_item_info.setItem_title("내용을 입력하세요");
        todolist_item_info.setList_idx(list_idx);
        todolistService.insert_todolist_item(todolist_item_info);


        String user_id = find_userSession(session);
        select_todolist(model, user_id);

        model.addAttribute("todolist", new Todolist());

        return "todolist_update";
    }
}
