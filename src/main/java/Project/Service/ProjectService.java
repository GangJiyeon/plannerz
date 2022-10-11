package Project.Service;

import Project.Dao.PROJECT_TB_Dao;
import Project.Dao.PROJ_ITEM_TB_Dao;
import Project.Dao.PROJ_MID_TB_Dao;
import Project.Dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    public PROJECT_TB_Dao project_tb_dao;

    @Autowired
    public PROJ_MID_TB_Dao proj_mid_tb_dao;

    @Autowired
    public PROJ_ITEM_TB_Dao proj_item_tb_dao;

    public void setProjectdao(PROJECT_TB_Dao project_tb_dao,
                              PROJ_MID_TB_Dao proj_mid_tb_dao,
                              PROJ_ITEM_TB_Dao proj_item_tb_dao){

        this.project_tb_dao = project_tb_dao;
        this.project_tb_dao = project_tb_dao;
        this.proj_item_tb_dao = proj_item_tb_dao;
    }


    public List<ProjectInfo> selectProjectList(String user_id){
        List<ProjectInfo> projectInfoList = project_tb_dao.selectProjectList_byUserId(user_id);
        return projectInfoList;
    }

    public List<Project_middle_info> selectProjectMiddleInfo(Integer project_idx){
        List<Project_middle_info> projectMiddleInfoList = proj_mid_tb_dao.select_mid_info_by_projectIdx(project_idx);
        return projectMiddleInfoList;
    }

    public List<Project_middle_info> selectProjectIdx(Integer middle_idx){
        List<Project_middle_info> projectMiddleInfoList = proj_mid_tb_dao.select_project_idx(middle_idx);
        return projectMiddleInfoList;
    }


    public List<Project_item_info> selectProjectItemList(Integer middle_idx){
        List<Project_item_info> projectItemInfoList = proj_item_tb_dao.select_itemList_byMidIdx(middle_idx);
        return projectItemInfoList;
    }

    public Integer insertProject(ProjectCommand projectCommand){
        Integer project_idx = project_tb_dao.insertProject(projectCommand);
        return project_idx;
    }

    public Integer insertProjectMiddle(Project_middle_command project_middle_command){
        Integer project_middle_idx = proj_mid_tb_dao.insertProjectMiddle(project_middle_command);
        return project_middle_idx;
    }

    public Integer insertProjectItem(Project_item_command project_item_command){
        Integer item_idx = proj_item_tb_dao.insertProjectItem(project_item_command);
        return item_idx;
    }

    public void insetItem_forUpdate(Integer middle_item){
        proj_item_tb_dao.insert_item_for_update(middle_item);
    }

    public void delete_project_item(Integer item_idx){
        proj_item_tb_dao.delete_project_item(item_idx);
    }

    public void delete_all(Integer project_idx){
        List<Project_middle_info> list = proj_mid_tb_dao.select_mid_info_by_projectIdx(project_idx);

        if(list != null){
            for(Project_middle_info nth : list){
                Integer middle_idx = nth.getProject_middle_idx();
                proj_item_tb_dao.delete_project_all(middle_idx);
            }
        }

        proj_mid_tb_dao.delete_project_all(project_idx);
        project_tb_dao.delete_project(project_idx);
    }

    public void project_mid_update(String title, String middle_idx){
        proj_mid_tb_dao.update_project(title, middle_idx);
    }

    public void project_update(ProjectCommand projectCommand, Integer project_idx){
        project_tb_dao.update_project(projectCommand, project_idx);
    }

    public void project_item_update(String title, String idx){
        proj_item_tb_dao.update_project_item(title, idx);
    }

    public void delete_mid(Integer mid_idx){
        proj_mid_tb_dao.delete_middle(mid_idx);
        List<Project_item_info> item = proj_item_tb_dao.select_itemList_byMidIdx(mid_idx);

        for (Project_item_info nth : item){
            proj_item_tb_dao.delete_project_item(nth.getItem_idx());
        }

    }
}
