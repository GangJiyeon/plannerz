package Board.Dto;

public class BoardLikeInfo {

    private Integer like_idx;
    private Integer like_board_idx;
    private String like_user_id;

    public BoardLikeInfo() {
    }

    public BoardLikeInfo(Integer like_idx, Integer like_board_idx, String like_user_id) {
        this.like_idx = like_idx;
        this.like_board_idx = like_board_idx;
        this.like_user_id = like_user_id;
    }

    public Integer getLike_idx() {
        return like_idx;
    }

    public void setLike_idx(Integer like_idx) {
        this.like_idx = like_idx;
    }

    public Integer getLike_board_idx() {
        return like_board_idx;
    }

    public void setLike_board_idx(Integer like_board_idx) {
        this.like_board_idx = like_board_idx;
    }

    public String getLike_user_id() {
        return like_user_id;
    }

    public void setLike_user_id(String like_user_id) {
        this.like_user_id = like_user_id;
    }
}
