package Board.Dto;

public class CocCommand {


    private Integer c_comment_idx;
    private String c_user_id;
    private String c_content;
    private Integer c_parent_board_idx;
    private Integer c_parent_comment;

    public CocCommand() {
    }

    public CocCommand(Integer c_comment_idx, String c_user_id, String c_content, Integer c_parent_board_idx, Integer c_parent_comment) {
        this.c_comment_idx = c_comment_idx;
        this.c_user_id = c_user_id;
        this.c_content = c_content;
        this.c_parent_board_idx = c_parent_board_idx;
        this.c_parent_comment = c_parent_comment;
    }

    public Integer getC_comment_idx() {
        return c_comment_idx;
    }

    public void setC_comment_idx(Integer c_comment_idx) {
        this.c_comment_idx = c_comment_idx;
    }

    public String getC_user_id() {
        return c_user_id;
    }

    public void setC_user_id(String c_user_id) {
        this.c_user_id = c_user_id;
    }

    public String getC_content() {
        return c_content;
    }

    public void setC_content(String c_content) {
        this.c_content = c_content;
    }

    public Integer getC_parent_board_idx() {
        return c_parent_board_idx;
    }

    public void setC_parent_board_idx(Integer c_parent_board_idx) {
        this.c_parent_board_idx = c_parent_board_idx;
    }

    public Integer getC_parent_comment() {
        return c_parent_comment;
    }

    public void setC_parent_comment(Integer c_parent_comment) {
        this.c_parent_comment = c_parent_comment;
    }
}

