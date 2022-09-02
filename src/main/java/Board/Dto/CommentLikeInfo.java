package Board.Dto;

public class CommentLikeInfo {

    private Integer comment_like_idx;
    private Integer comment_idx;
    private String comment_user_id;

    public CommentLikeInfo() {
    }

    public CommentLikeInfo(Integer comment_like_idx, Integer comment_idx, String comment_user_id) {
        this.comment_like_idx = comment_like_idx;
        this.comment_idx = comment_idx;
        this.comment_user_id = comment_user_id;
    }

    public Integer getComment_like_idx() {
        return comment_like_idx;
    }

    public void setComment_like_idx(Integer comment_like_idx) {
        this.comment_like_idx = comment_like_idx;
    }

    public Integer getComment_idx() {
        return comment_idx;
    }

    public void setComment_idx(Integer comment_idx) {
        this.comment_idx = comment_idx;
    }

    public String getComment_user_id() {
        return comment_user_id;
    }

    public void setComment_user_id(String comment_user_id) {
        this.comment_user_id = comment_user_id;
    }
}
