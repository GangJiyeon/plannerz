package Board.Dto;

public class BoardCommand {

    private Integer board_idx;
    private String user_id;
    private String board_title;
    private String board_content;
    private String board_img1;
    private String board_img2;
    private String board_img3;
    private String board_img4;
    private String board_img5;

    public BoardCommand() {
    }

    public BoardCommand(Integer board_idx, String user_id, String board_title, String board_content, String board_img1, String board_img2, String board_img3, String board_img4, String board_img5) {
        this.board_idx = board_idx;
        this.user_id = user_id;
        this.board_title = board_title;
        this.board_content = board_content;
        this.board_img1 = board_img1;
        this.board_img2 = board_img2;
        this.board_img3 = board_img3;
        this.board_img4 = board_img4;
        this.board_img5 = board_img5;
    }

    public Integer getBoard_idx() {
        return board_idx;
    }

    public void setBoard_idx(Integer board_idx) {
        this.board_idx = board_idx;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getBoard_img1() {
        return board_img1;
    }

    public void setBoard_img1(String board_img1) {
        this.board_img1 = board_img1;
    }

    public String getBoard_img2() {
        return board_img2;
    }

    public void setBoard_img2(String board_img2) {
        this.board_img2 = board_img2;
    }

    public String getBoard_img3() {
        return board_img3;
    }

    public void setBoard_img3(String board_img3) {
        this.board_img3 = board_img3;
    }

    public String getBoard_img4() {
        return board_img4;
    }

    public void setBoard_img4(String board_img4) {
        this.board_img4 = board_img4;
    }

    public String getBoard_img5() {
        return board_img5;
    }

    public void setBoard_img5(String board_img5) {
        this.board_img5 = board_img5;
    }
}
