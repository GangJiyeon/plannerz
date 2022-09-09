package UserInfo.Controller;

import UserInfo.Service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {

    @Autowired
    private SettingService settingService;

    public void setSettingService(SettingService settingService){
        this.settingService = settingService;
    }

    @GetMapping("/setting")
    public String setting(){
        return "setting";
    }
}
