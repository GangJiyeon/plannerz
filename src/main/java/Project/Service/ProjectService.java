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

    public List<Project_item_info> selectProjectItemList(Integer middle_idx){
        List<Project_item_info> projectItemInfoList = proj_item_tb_dao.select_itemList_byMidIdx(middle_idx);
        return projectItemInfoList;
    }

    public Integer insertProject(ProjectCommand pc){
        Integer project_idx = project_tb_dao.insertProject(pc);
        return project_idx;
    }

}
