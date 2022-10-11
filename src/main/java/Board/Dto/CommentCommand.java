package Board.Dto;

import java.util.Date;

public class CommentCommand {

    private Integer comment_idx;
    private String user_id;
    private String content;
    private Integer parent_board_idx;
    private Integer parent_comment;
    private Integer like;

    public CommentCommand() {
    }

    public CommentCommand(Integer comment_idx, String user_id, String content, Integer parent_board_idx, Integer parent_comment, Integer like) {
        this.comment_idx = comment_idx;
        this.user_id = user_id;
        this.content = content;
        this.parent_board_idx = parent_board_idx;
        this.parent_comment = parent_comment;
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

    public Integer getParent_board_idx() {
        return parent_board_idx;
    }

    public void setParent_board_idx(Integer parent_board_idx) {
        this.parent_board_idx = parent_board_idx;
    }

    public Integer getParent_comment() {
        return parent_comment;
    }

    public void setParent_comment(Integer parent_comment) {
        this.parent_comment = parent_comment;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }
}
