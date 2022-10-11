package ToDoList.Dao;

import ToDoList.Dto.Todolist_item_info;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

/** 투투리스트 아이템 정보 테이블 **/
public class TODOLIST_ITEM_TB_Dao {

    private JdbcTemplate jdbcTemplate;
    public TODOLIST_ITEM_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //투투리스트 아이템 조회
    public List<Todolist_item_info> select_itemList_byListIdx(Integer list_idx){
        String sql = "SELECT * FROM TODOLIST_ITEM_TB WHERE list_idx=?";
        List<Todolist_item_info> results = jdbcTemplate.query(sql, new TODOLIST_ITEM_TB_Mapper(), list_idx);
        return results.isEmpty() ? null : results;
    }

    //투투리스트 아이템 삭제
    public Boolean delete_listItem(Integer list_idx, Integer list_item_idx){
        String sql = "DELETE FROM TODOLIST_ITEM_TB WHERE list_idx=? AND list_item_idx=?";
        int result = jdbcTemplate.update(sql, list_idx, list_item_idx);

        if(result!=0){
            return true;
        }else {
            return false;
        }
    }

    public void delete_listItems(Integer list_idx){
        String sql = "DELETE FROM TODOLIST_ITEM_TB WHERE list_idx=?";
        jdbcTemplate.update(sql, list_idx);
    }

    //투투리스트 아이템 추가
    public Boolean insert_todolistItem(Todolist_item_info todolist_item_info){
        String sql = "INSERT INTO TODOLIST_ITEM_TB (list_idx, item_title)" +
                "VALUES (?,?)";

        int result = jdbcTemplate.update(sql, todolist_item_info.getList_idx(),
                todolist_item_info.getItem_title());

        if(result!=0){
            return true;
        }else {
            return false;
        }

    }

    //투두리스트 아이템 수정
    public void update_todolistItem(Todolist_item_info todolist_item_info){
        String sql = "UPDATE TODOLIST_ITEM_TB SET item_title=? " +
                "WHERE list_item_idx=? AND list_idx=?";

        jdbcTemplate.update(sql, todolist_item_info.getItem_title(),
                todolist_item_info.getList_item_idx(), todolist_item_info.getList_idx());
    }
}
