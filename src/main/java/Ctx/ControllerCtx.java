package Ctx;

import Board.Controller.BoardController;
import Board.Service.BoardService;
import Calendar.Controller.MonthlyController;
import Calendar.Service.MonthlyService;
import Project.Controller.ProjectController;
import Project.Service.ProjectService;
import ToDoList.Controller.TodolistController;
import ToDoList.Service.TodolistService;
import User.Controller.FindAccountController;
import User.Controller.JoinController;
import User.Controller.LoginController;
import User.Controller.LogoutController;
import User.Dto.NaverLoginBO;
import User.ExController;
import User.Service.FindAccountService;
import User.Service.JoinService;
import User.Service.LoginService;
import UserInfo.Controller.AlarmController;
import UserInfo.Controller.SettingController;
import UserInfo.Controller.StatusController;
import UserInfo.Controller.UserInfoController;
import UserInfo.Service.AlarmService;
import UserInfo.Service.SettingService;
import UserInfo.Service.StatusService;
import UserInfo.Service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerCtx {

    @Bean
    public ExController exController() {
        return new ExController();
    }

    private BoardService boardService;

    @Bean
    public BoardController boardController(){
        BoardController boardController = new BoardController();
        boardController.setBoardService(boardService);
        boardController.setLoginService(loginService);
        return boardController;
    }

    private MonthlyService monthlyService;

    @Bean
    public MonthlyController monthlyController(){
        MonthlyController monthlyController = new MonthlyController();
        monthlyController.setMonthlyService(monthlyService);
        return monthlyController;
    }


    private ProjectService projectService;

    @Bean
    public ProjectController projectController(){
        ProjectController projectController = new ProjectController();
        projectController.setProjectService(projectService);
        return projectController;
    }
    private TodolistService todolistService;

    @Bean
    public TodolistController todolistController(){
        TodolistController todolistController = new TodolistController();
        todolistController.setTodolistService(todolistService);
        return todolistController;
    }

    private FindAccountService findAccountService;

    @Bean
    public FindAccountController findAccountController(){
        FindAccountController findAccountController = new FindAccountController();
        findAccountController.setFindAccountService(findAccountService);
        return  findAccountController;
    }

    private LoginService loginService;

    @Bean
    public LoginController loginController(){
        LoginController controller = new LoginController();
        controller.setLoginService(loginService, naverLoginBO() ,joinService);
        controller.setAlarmService(alarmService);
        return controller;
    }

    private JoinService joinService;

    @Bean
    public JoinController joinController(){
        JoinController joinController = new JoinController();
        joinController.setLoginService(joinService);
        joinController.setAlarmService(alarmService);
        return joinController;
    }


    @Bean
    public LogoutController logoutController(){
        return new LogoutController();
    }

    private AlarmService alarmService;

    @Bean
    public AlarmController alarmController(){
        AlarmController alarmController = new AlarmController();
        alarmController.setAlarm(alarmService, loginService);
        return alarmController;
    }

    private SettingService settingService;

    @Bean
    public SettingController settingController(){
        SettingController settingController = new SettingController();
        settingController.setSettingService(settingService);
        return settingController;

    }

    private StatusService statusService;

    @Bean
    public StatusController statusController(){
        StatusController statusController = new StatusController();
        statusController.setStatusService(statusService);
        return statusController;
    }

    private UserInfoService userInfoService;

    @Bean
    public UserInfoController userInfoController(){
        UserInfoController userInfoController = new UserInfoController();
        userInfoController.setUserInfoService(userInfoService, loginService);
        return userInfoController;
    }

    @Bean
    public NaverLoginBO naverLoginBO(){
        return new NaverLoginBO();
    }





}
