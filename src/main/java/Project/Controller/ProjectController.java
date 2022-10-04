package Project.Controller;

import Project.Dto.*;
import Project.Service.ProjectService;
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
import java.util.ListIterator;

@Controller
public class ProjectController {

    //의존주입
    @Autowired
    private ProjectService projectService;

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }


    //프로젝트 내역 조회 메서드
    public void select_user_projectInfoList(Model model, String user_id){

        List<ProjectInfo> projectInfoList = projectService.selectProjectList(user_id);

        List<Integer> project_idx_List = new ArrayList<>();
        List<List<Project_middle_info>> middleList = new ArrayList<>();
        List<List<Project_item_info>> itemList = new ArrayList<>();

        if(projectInfoList != null){
            for (ProjectInfo nth : projectInfoList) {
                //사용자의 프로젝트 리스트 idx
                project_idx_List.add(nth.getProject_idx());
            }

            for (Integer nth : project_idx_List) {
                //프로젝트 idx를 기준으로 분류된 소목표 리스트
                List<Project_middle_info> middle = projectService.selectProjectMiddleInfo(nth);

                if (middle!=null){
                    middleList.add(projectService.selectProjectMiddleInfo(nth));
                    for (Project_middle_info items : middle) {
                        //소목표 idx 리스트
                        List<Integer> item_idxList = new ArrayList<>();
                        item_idxList.add(items.getProject_middle_idx());

                        if (item_idxList != null){
                            for (Integer item_nth : item_idxList) {
                                itemList.add(projectService.selectProjectItemList(item_nth));
                            }
                        }

                    }
                }


            }

            model.addAttribute("projectInfoList", projectInfoList);
            model.addAttribute("middleList", middleList);
            model.addAttribute("itemList", itemList);
        }
    }

    //세션에서 사용자 id 가지고 오기 메서드
    public String find_userSession(HttpSession session){
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();
        return user_id;
    }

    //<-------------------------------------------------------------------------------->//


    //프로젝트 내역 조회
    @GetMapping("/project")
    public String project(Model model, HttpSession session) {

        //커멘드 객체 생성 후 view로 전달
        model.addAttribute("projectCommand", new ProjectCommand());

        //사용자 아이디 가져오기
        String user_id = find_userSession(session);

        //프로젝트 내역 조회 메서드로 프로젝트 내역 조회
        select_user_projectInfoList(model, user_id);

        return "project";
    }

    //메인 프로젝트, 소 프로젝트 작성
    @PostMapping("/project/form1")
    public String projectForm1(Model model,
                               ProjectCommand projectCommand, HttpSession session,
                               @RequestParam("middle_title") String middle_title) {

        //1. 메인프로젝트 데이터 베이스에 저장하기
        String user_id = find_userSession(session); //사용자 아이디 가져오기
        projectCommand.setUser_id(user_id); //아이디 저장
        Integer project_idx = projectService.insertProject(projectCommand); //메인 프로젝트 db에 저장 후, auto_increment 값 가져오기

        //2. 소프로젝트 데이터 베이스에 저장하기
        //middle_title 파라미터에 담긴 값을 배열에 저장
        String[] middle_titles = middle_title.split(",");
        //필요한 객체 생성
        Project_middle_command project_middle_command = new Project_middle_command();
        List<Integer> proj_mid_idx_list = new ArrayList<>();

        //데이터베이스에 소 프로젝트 저장하기
        for(int i = 0; i<middle_titles.length; i++){
            project_middle_command.setProject_idx(project_idx);
            project_middle_command.setMiddle_title(middle_titles[i]);
            proj_mid_idx_list.add(projectService.insertProjectMiddle(project_middle_command));
        }

        //모델에 담기
        model.addAttribute("proj_mid_idx_list", proj_mid_idx_list);

        select_user_projectInfoList(model, user_id);

        model.addAttribute("project_item_command", new Project_item_command());
        model.addAttribute("project_idx", project_idx);


        return "project_form";
    }


    //소 프로젝트별 프로젝트 아이템 작성
    @PostMapping("/project/mid/form")
    public String project_item_form(HttpSession session,
                                    @RequestParam("title") String title,
                                    @RequestParam("middle_idx") String middle_idx,
                                    Model model){

        String[] titles = title.split(",");
        String[] middle_idxs = middle_idx.split(",");

        Project_item_command project_item_command = new Project_item_command();

        for(int i=0; i<titles.length; i++){
            project_item_command.setMiddle_idx(Integer.parseInt(middle_idxs[i]));
            project_item_command.setTitle(titles[i]);
            projectService.insertProjectItem(project_item_command);
        }

        String user_id = find_userSession(session);
        select_user_projectInfoList(model, user_id);

        model.addAttribute("projectCommand", new ProjectCommand());

        return "project";
    }
    //프로젝트 업데이트
    @GetMapping("/project/update")
    public String projectUpdate(Model model, HttpSession session) {
        //사용자 아이디 가져오기
        String user_id = find_userSession(session);


        model.addAttribute("project_item_command", new Project_item_command());
        model.addAttribute("projectCommand", new ProjectCommand());

        select_user_projectInfoList(model, user_id);

        return "project_update";
    }

    @PostMapping("/project/update.do")
    public String project_update(Model model, HttpSession session,
                                 ProjectCommand projectCommand,
                                 @RequestParam("project_idx") Integer project_idx,
                                 @RequestParam("middle_title") String middle_title,
                                 @RequestParam("middle_idx") String middle_idx,
                                 @RequestParam("item_idx") String item_idx,
                                 @RequestParam("item_title") String item_title
                                 ){

        String[] middle_titles = middle_title.split(",");
        String[] middle_idxs = middle_idx.split(",");
        String[] item_idxs = item_idx.split(",");
        String[] item_titles = item_title.split(",");

        for(int i = 0; i<middle_titles.length; i++){
            projectService.project_mid_update(middle_titles[i], middle_idxs[i]);
        }

        projectService.project_update(projectCommand, project_idx);

        for(int i = 0; i<item_titles.length; i++){
            projectService.project_item_update(item_titles[i], item_idxs[i]);
        }


        //커멘드 객체 생성 후 view로 전달
        model.addAttribute("projectCommand", new ProjectCommand());

        //프로젝트 내역 조회 메서드로 프로젝트 내역 조회
        select_user_projectInfoList(model, find_userSession(session));

        return "project";
    }


    //프로젝트 수정페이지에서 프로젝트 아이템 추가
    @GetMapping("/project/update/for/add")
    public String addd(HttpSession session,
                       @RequestParam("middle_idx") Integer middle_idx,
                       @RequestParam("project_idx") Integer project_idx,
                       Model model){

        model.addAttribute("projectCommand", new ProjectCommand());
        model.addAttribute("project_item_command", new Project_item_command());
        model.addAttribute("projectCommand", new ProjectCommand());

        String user_id = find_userSession(session);


        projectService.insetItem_forUpdate(middle_idx);

        select_user_projectInfoList(model, user_id);


        return "project_update";
    }

    //프로젝트 아이템 삭제
    @GetMapping("/project/item/delete")
    public String delete_project_item(@RequestParam("item_idx") Integer item_idx,
                                      @RequestParam("project_idx") Integer project_idx,
                                      HttpSession session, Model model
                                      ){

        projectService.delete_project_item(item_idx);
        return "redirect:/project?project_idx="+project_idx;
    }

    //소 프로젝트 아이템 삭제
    @GetMapping("/project/mid/delete")
    public String delete_mid_delete(HttpSession session, Model model,
                                    @RequestParam("mid_idx") Integer mid_idx
                                    ){
        projectService.delete_mid(mid_idx);
        select_user_projectInfoList(model, find_userSession(session));

        List project = projectService.selectProjectIdx(mid_idx);

        if (project != null){
            ProjectInfo projectInfo = (ProjectInfo) project.get(0);
            return "redirect:/project?project_idx="+projectInfo.getProject_idx();

        }

        return "redirect:/project?";

    }


    //프로젝트 삭제
    @GetMapping("/project/total/delete")
    public String delete_project(@RequestParam("project_idx") Integer project_idx){
        projectService.delete_all(project_idx);
        return "redirect:/project";
    }


}
