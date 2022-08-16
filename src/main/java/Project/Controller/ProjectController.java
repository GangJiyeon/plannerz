package Project.Controller;

import Project.Dto.ProjectInfo;
import Project.Dto.Project_item_info;
import Project.Dto.Project_middle_info;
import Project.Service.ProjectService;
import User.Dto.LoginSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    public void setProjectService(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping("/project")
    public String project(Model model, HttpSession session){

        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();

        List<ProjectInfo> projectInfoList = projectService.selectProjectList(user_id);

        List<Integer> project_idx_List = new ArrayList<>();
        List<List<Project_middle_info>> middleList = new ArrayList<>();
        List<List<Project_item_info>> itemList = new ArrayList<>();

        for(ProjectInfo nth : projectInfoList){
            //사용자의 프로젝트 리스트 idx
            project_idx_List.add(nth.getProject_idx());
        }

        for(Integer nth : project_idx_List){
            //프로젝트 idx를 기준으로 분류된 소목표 리스트
            System.out.println(nth);
            List<Project_middle_info> middle = projectService.selectProjectMiddleInfo(nth);
            middleList.add(projectService.selectProjectMiddleInfo(nth));
            for (Project_middle_info items : middle){
                //소목표 idx 리스트
                List<Integer> item_idxList = new ArrayList<>();
                item_idxList.add(items.getProject_middle_idx());

                for(Integer item_nth : item_idxList){
                    itemList.add(projectService.selectProjectItemList(item_nth));
                }
            }

        }

        model.addAttribute("projectInfoList", projectInfoList);
        model.addAttribute("middleList", middleList);
        model.addAttribute("itemList", itemList);

        return "project";
    }
}
