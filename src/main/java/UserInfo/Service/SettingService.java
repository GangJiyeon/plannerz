package UserInfo.Service;

import UserInfo.Dao.SETTING_TB_Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    @Autowired
    private SETTING_TB_Dao setting_tb_dao;

    public void setSetting_tb_dao(SETTING_TB_Dao setting_tb_dao){
        this.setting_tb_dao = setting_tb_dao;
    }

}
