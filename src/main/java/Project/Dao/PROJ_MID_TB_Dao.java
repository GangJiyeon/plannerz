package Project.Dao;

import Project.Dto.Project_middle_command;
import Project.Dto.Project_middle_info;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/** 소프로젝트 정보 테이블 **/
public class PROJ_MID_TB_Dao {

    private JdbcTemplate jdbcTemplate;

    public PROJ_MID_TB_Dao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //소프로젝트 목록 조회 1
    public List<Project_middle_info> select_mid_info_by_projectIdx(Integer project_idx){
        String sql = "SELECT * FROM PROJ_MID_TB WHERE project_idx=?";
        List<Project_middle_info> results = jdbcTemplate.query(sql, new PROJ_MID_TB_Mapper(), project_idx);

        return results.isEmpty() ? null : results;
    }

    //소프로젝트 목록 조회 2
    public List<Project_middle_info> select_project_idx(Integer middle_idx){
        String sql = "SELECT * FROM PROJ_MID_TB WHERE project_middle_idx=?";
        List<Project_middle_info> results = jdbcTemplate.query(sql, new PROJ_MID_TB_Mapper(), middle_idx);
        return results.isEmpty() ? null : results;
    }

    //소프로젝트 등록
    public Integer insertProjectMiddle(Project_middle_command project_middle_command){

        Integer num;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        num = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                String sql = "INSERT INTO PROJ_MID_TB (project_idx, title) VALUES (?,?)";

                PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"project_id"});
                pstmt.setInt(1, project_middle_command.getProject_idx());
                pstmt.setString(2, project_middle_command.getMiddle_title());

                return pstmt;
            }
        }, keyHolder);

        Integer list_idx = Integer.parseInt(String.valueOf(keyHolder.getKey()));
        return list_idx;

    }

    //소프로젝트 삭제(프로젝트 전체 삭제 시)
    public void delete_project_all(Integer project_idx){
        String sql = "DELETE FROM PROJ_MID_TB WHERE project_idx=?";
        int result = jdbcTemplate.update(sql, project_idx);
    }

    //소프로젝트 수정
    public void update_project(String title, String project_idx){
        String sql = "UPDATE PROJ_MID_TB SET title=? WHERE project_middle_idx=?";
        jdbcTemplate.update(sql, title, project_idx);
    }

    //소프로젝트 삭제
    public void delete_middle(Integer mid_idx){
        String sql = "DELETE FROM PROJ_MID_TB WHERE project_middle_idx=?";
        int result = jdbcTemplate.update(sql, mid_idx);
    }
}
