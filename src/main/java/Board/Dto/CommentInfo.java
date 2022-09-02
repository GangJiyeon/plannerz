package Board.Dto;

import java.util.Date;

public class CommentInfo {

    private Integer comment_idx;
    private String user_id;
    private String content;
    private Integer board_idx;
    private Integer parent_comment;
    private Date insert_date;
    private Integer like;

    public CommentInfo() {
    }

    public CommentInfo(Integer comment_idx, String user_id, String content, Integer board_idx, Integer parent_comment, Date insert_date, Integer like) {
        this.comment_idx = comment_idx;
        this.user_id = user_id;
        this.content = content;
        this.board_idx = board_idx;
        this.parent_comment = parent_comment;
        this.insert_date = insert_date;
        this.like = like;
    }

    public Integer getComment_idx() {
        return comment_idx;
    }

    public void setComment_idx(Integer comment_idx) {
        this.comment_idx = comment_idx;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBoard_idx() {
        return board_idx;
    }

    public void setBoard_idx(Integer board_idx) {
        this.board_idx = board_idx;
    }

    public Integer getParent_comment() {
        return parent_comment;
    }

    public void setParent_comment(Integer parent_comment) {
        this.parent_comment = parent_comment;
    }

    public Date getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }
}
