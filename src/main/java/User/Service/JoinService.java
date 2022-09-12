package User.Service;

import User.Dao.SNS_ACCOUNT_TB_Dao;
import User.Dao.USER_TB_Dao;
import User.Dto.JoinCommand;
import User.Dto.SNSAccount;
import User.Dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Objects;
import java.util.Random;

public class JoinService {

    @Autowired
    public USER_TB_Dao user_tb_dao;

    @Autowired
    public SNS_ACCOUNT_TB_Dao sns_account_tb_dao;

    public void setUser_tb_dao(USER_TB_Dao user_tb_dao){
        this.user_tb_dao = user_tb_dao;
    }

    public void setSns_account_tb_dao(SNS_ACCOUNT_TB_Dao sns_account_tb_dao){
        this.sns_account_tb_dao = sns_account_tb_dao;
    }

    public Boolean checkid(String user_id){
        Boolean notExist = user_tb_dao.check_id(user_id);
        return notExist;
    }

    public void join(JoinCommand joinCommand){
        user_tb_dao.insert(joinCommand);
    }

    //해당 계정이 SNS_ACCOUNT_TB 에 등록되어 있는 지 확인
    public SNSAccount selectSNS_Account(SNSAccount snsAccount){
        SNSAccount select_snsAccount = sns_account_tb_dao.snsAccount(snsAccount);
        return select_snsAccount;

    }

    //랜덤 아이디 생성 메서드
    public String isPossibleRandomId(){
        Random random = new Random();
        String random_id ="";

        //파라미터 규칙에 맞는 랜덤아이디 생성: 8-16 자리의 숫자와 영문자가 포함된 문자열
        //1. 랜덤아이디 길이 결정
        int length_id = 8 + random.nextInt(9);

        //2. 랜덤아이디 생성
        for(int i=0; i<=length_id; i++){
            if(i<=7) {
                random_id = random_id + Integer.toString(random.nextInt(10));
            }else {
                random_id = random_id + (char) ((random.nextInt(26)) + 'A');
            }
        }

        return random_id;
    }

    public SNSAccount create_random_id(SNSAccount snsAccount){
            String random_id = "";
            //사용가능한 랜덤 아이디인지 조회
            boolean create = false;
            while (!create){
                //랜덤 아이디 생성
                random_id = isPossibleRandomId();
                if(user_tb_dao.possible_random_id(random_id)){
                    create = true;

                }
                System.out.println(random_id);
            }
            //SNS_ACCOUNT_TB에 사용자 정보와 새롭게 생성한 random_id 등록


            sns_account_tb_dao.insert_userInfo(random_id, snsAccount);

            //해당 계정 정보 return
            return sns_account_tb_dao.snsAccount(snsAccount);

    }

}
