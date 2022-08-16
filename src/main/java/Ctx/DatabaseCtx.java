
package Ctx;


import Board.Dao.BOARD_TB_Dao;
import Board.Service.BoardService;
import Calendar.Dao.DAILY_TB_Dao;
import Calendar.Dao.MONTHLY_TB_Dao;
import Calendar.Dao.WEEKLY_TB_Dao;
import Calendar.Service.DailyService;
import Calendar.Service.MonthlyService;
import Calendar.Service.WeeklyService;
import Project.Dao.PROJECT_TB_Dao;
import Project.Dao.PROJ_ITEM_TB_Dao;
import Project.Dao.PROJ_MID_TB_Dao;
import Project.Service.ProjectService;
import ToDoList.Dao.TODOLIST_TB_Dad;
import ToDoList.Service.TodolistService;
import User.Dao.USER_TB_Dao;
import User.Service.JoinService;
import User.Service.LoginService;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
    public BoardService boardService(){
        BoardService boardService = new BoardService();
        boardService.setBoard_tb_dao(board_tb_dao());
        return boardService;
    }

    @Bean
    public DAILY_TB_Dao daily_tb_dao(){
        return new DAILY_TB_Dao(dataSource());
    }

    @Bean
    public DailyService dailyService(){
        DailyService dailyService = new DailyService();
        dailyService.setDaily_tb_dao(daily_tb_dao());
        return dailyService;
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
    public WEEKLY_TB_Dao weekly_tb_dao(){
        return new WEEKLY_TB_Dao(dataSource());
    }

    @Bean
    public WeeklyService weeklyService(){
        WeeklyService weeklyService = new WeeklyService();
        weeklyService.setWeekly_tb_dao(weekly_tb_dao());
        return weeklyService;
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
    public TODOLIST_TB_Dad todolist_tb_dad(){
        return new TODOLIST_TB_Dad(dataSource());
    }

    @Bean
    public TodolistService todolistService(){
        TodolistService todolistService = new TodolistService();
        todolistService.setTodolist_tb_dad(todolist_tb_dad());
        return todolistService;
    }
    @Bean
    public USER_TB_Dao user_tb_dao(){
        return new USER_TB_Dao(dataSource());
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

    @Bean
    public UserInfoService userInfoService(){
        UserInfoService userInfoService = new UserInfoService();
        userInfoService.setUserinfo_tb_dao(userinfo_tb_dao());
        return userInfoService;
    }

}
