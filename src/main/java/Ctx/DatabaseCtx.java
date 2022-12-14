package Ctx;

import Board.Dao.BOARD_LIKE_TB_Dao;
import Board.Dao.BOARD_TB_Dao;
import Board.Dao.COMMENT_LIKE_TB_Dao;
import Board.Dao.COMMENT_TB_Dao;
import Board.Service.BoardService;
import Calendar.Dao.MONTHLY_TB_Dao;
import Calendar.Service.MonthlyService;
import Project.Dao.PROJECT_TB_Dao;
import Project.Dao.PROJ_ITEM_TB_Dao;
import Project.Dao.PROJ_MID_TB_Dao;
import Project.Service.ProjectService;
import ToDoList.Dao.TODOLIST_ITEM_TB_Dao;
import ToDoList.Dao.TODOLIST_TB_Dao;
import ToDoList.Service.TodolistService;
import User.Dao.PLANNER_TB_Dao;
import User.Dao.SNS_ACCOUNT_TB_Dao;
import User.Dao.USER_TB_Dao;
import User.Service.FindAccountService;
import User.Service.JoinService;
import User.Service.LoginService;
import User.Service.PlannerService;
import UserInfo.Dao.ALARM_TB_Dao;
import UserInfo.Dao.SETTING_TB_Dao;
import UserInfo.Dao.STATUS_TB_Dao;
import UserInfo.Dao.USERINFO_TB_Dao;
import UserInfo.Service.AlarmService;
import UserInfo.Service.SettingService;
import UserInfo.Service.StatusService;
import UserInfo.Service.UserInfoService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DatabaseCtx {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();

        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/plannerz");
        ds.setUsername("root");
        ds.setPassword("67201702");

        return ds;
    }
    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }

    @Bean
    public BOARD_TB_Dao board_tb_dao(){
        return new BOARD_TB_Dao(dataSource());
    }

    @Bean
    public COMMENT_TB_Dao comment_tb_dao(){
        return new COMMENT_TB_Dao(dataSource());
    }

    @Bean
    public BOARD_LIKE_TB_Dao board_like_tb_dao(){
        return new BOARD_LIKE_TB_Dao(dataSource());
    }

    @Bean
    public COMMENT_LIKE_TB_Dao comment_like_tb_dao(){
        return new COMMENT_LIKE_TB_Dao(dataSource());
    }

    @Bean
    public BoardService boardService(){
        BoardService boardService = new BoardService();
        boardService.setBoard_tb_dao(board_tb_dao(), comment_tb_dao(), board_like_tb_dao(), comment_like_tb_dao());
        return boardService;
    }

    @Bean
    public UserInfoService userInfoService(){
        UserInfoService userInfoService = new UserInfoService();
        userInfoService.setUser_tb_dao(user_tb_dao());
        userInfoService.setUserinfo_tb_dao(userinfo_tb_dao());
        userInfoService.setSns_account_tb_dao(sns_account_tb_dao());
        return userInfoService;
    }


    @Bean
    public MONTHLY_TB_Dao monthly_tb_dao(){
        return new MONTHLY_TB_Dao(dataSource());
    }

    @Bean
    public MonthlyService monthlyService(){
        MonthlyService monthlyService = new MonthlyService();
        monthlyService.setMonthly_tb_dao(monthly_tb_dao());
        return monthlyService;
    }

    @Bean
    public PROJECT_TB_Dao project_tb_dao(){
        return new PROJECT_TB_Dao(dataSource());
    }

    @Bean
    public PROJ_MID_TB_Dao proj_mid_tb_dao(){
        return  new PROJ_MID_TB_Dao(dataSource());
    }

    @Bean
    public PROJ_ITEM_TB_Dao proj_item_tb_dao(){
        return new PROJ_ITEM_TB_Dao(dataSource());
    }
    @Bean
    public ProjectService projectService(){
        ProjectService projectService = new ProjectService();
        projectService.setProjectdao(project_tb_dao(), proj_mid_tb_dao(), proj_item_tb_dao());
        return projectService;
    }

    @Bean
    public TODOLIST_TB_Dao todolist_tb_dad(){
        return new TODOLIST_TB_Dao(dataSource());
    }

    @Bean
    public TODOLIST_ITEM_TB_Dao todolist_item_tb_dao(){
        return new TODOLIST_ITEM_TB_Dao(dataSource());
    }
    @Bean
    public TodolistService todolistService(){
        TodolistService todolistService = new TodolistService();
        todolistService.setTodolist_tb(todolist_tb_dad(), todolist_item_tb_dao());
        return todolistService;
    }

    @Bean
    public USER_TB_Dao user_tb_dao(){
        return new USER_TB_Dao(dataSource());
    }

    @Bean
    public SNS_ACCOUNT_TB_Dao sns_account_tb_dao(){
        return new SNS_ACCOUNT_TB_Dao(dataSource());
    }

    @Bean
    public FindAccountService findInfoService(){
        FindAccountService findInfoService = new FindAccountService();
        findInfoService.setUser_tb_dao(user_tb_dao());
        return findInfoService;
    }

    @Bean
    public LoginService loginService(){
        LoginService loginService = new LoginService();
        loginService.setUser_tb_dao(user_tb_dao());
        return loginService;
    }

    @Bean
    public JoinService joinService(){
        JoinService joinService = new JoinService();
        joinService.setUser_tb_dao(user_tb_dao());
        joinService.setSns_account_tb_dao(sns_account_tb_dao());
        return joinService;
    }

    @Bean
    public ALARM_TB_Dao alarm_tb_dao(){
        return new ALARM_TB_Dao(dataSource());
    }

    @Bean
    public AlarmService alarmService(){
        AlarmService alarmService = new AlarmService();
        alarmService.setAlarm_tb_dao(alarm_tb_dao());
        return alarmService;
    }

    @Bean
    public PLANNER_TB_Dao planner_tb_dao(){
        return new PLANNER_TB_Dao(dataSource());
    }

    @Bean
    public PlannerService plannerService(){
        PlannerService plannerService = new PlannerService();
        plannerService.setPlanner_tb_dao(planner_tb_dao());
        return plannerService;
    }

    @Bean
    public SETTING_TB_Dao setting_tb_dao(){
        return new SETTING_TB_Dao(dataSource());
    }

    @Bean
    public SettingService settingService(){
        SettingService settingService = new SettingService();
        settingService.setSetting_tb_dao(setting_tb_dao());
        return settingService;
    }

    @Bean
    public STATUS_TB_Dao status_tb_dao(){
        return new STATUS_TB_Dao(dataSource());
    }

    @Bean
    public StatusService statusService(){
        StatusService statusService = new StatusService();
        statusService.setStatus_tb_dao(status_tb_dao());
        return statusService;
    }

    @Bean
    public USERINFO_TB_Dao userinfo_tb_dao(){
        return new USERINFO_TB_Dao(dataSource());
    }

}
