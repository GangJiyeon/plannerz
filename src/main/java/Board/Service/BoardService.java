package Board.Service;

import Board.Dao.BOARD_LIKE_TB_Dao;
import Board.Dao.BOARD_TB_Dao;
import Board.Dao.COMMENT_LIKE_TB_Dao;
import Board.Dao.COMMENT_TB_Dao;
import Board.Dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    public BOARD_TB_Dao board_tb_dao;

    @Autowired
    public BOARD_LIKE_TB_Dao board_like_tb_dao;

    @Autowired
    public COMMENT_TB_Dao comment_tb_dao;

    @Autowired
    public COMMENT_LIKE_TB_Dao comment_like_tb_dao;

    public void setBoard_tb_dao(BOARD_TB_Dao board_tb_dao, COMMENT_TB_Dao comment_tb_dao, BOARD_LIKE_TB_Dao board_like_tb_dao, COMMENT_LIKE_TB_Dao comment_like_tb_dao){
        this.board_tb_dao = board_tb_dao;
        this.comment_tb_dao = comment_tb_dao;
        this.board_like_tb_dao = board_like_tb_dao;
        this.comment_like_tb_dao = comment_like_tb_dao;
    }

    //게시글 갯수 조회
    public Integer countBoard(){
        return board_tb_dao.count_boardList();
    }

    //게시글 등록
    public BoardInfo insertBoard(BoardCommand boardCommand){
       Integer board_idx = board_tb_dao.insertBoard(boardCommand);
       return board_tb_dao.selectBoardInfo_byBoardIdx(board_idx);
    }

    //게시글 수정
    public void update(BoardCommand boardCommand){
        board_tb_dao.update_board(boardCommand);
    }

    //게시글 조회
    public BoardInfo selectBoardInfo(Integer board_idx){
        return board_tb_dao.selectBoardInfo_byBoardIdx(board_idx);
    }

    //게시글 조회수 수정
    public void updateBoardSee(BoardInfo boardInfo){
        board_tb_dao.updateBoardSee(boardInfo);
    }

    //해당 사용자 아이디로 작성된 게시글 목록 조회하기
    public List<BoardInfo> selectBoardList_byUserId(String user_id){
        return board_tb_dao.selectBoardList_byUserId(user_id);
    }

    //페이지네이션에 따른 게시글 조회
    public List<BoardInfo> selectBoardList_byStartNum(Integer start_num){
        return board_tb_dao.selectBoardList_byStartNum(start_num);
    }

    //게시글 삭제
    public void delete_board(Integer board_idx){
        board_tb_dao.delete_board(board_idx);
    }

    //게시글 수정
    public void update_board(BoardCommand boardCommand){
        board_tb_dao.update_board(boardCommand);
    }

    //해당 게시글에 작성된 댓글 정보 목록 조회
    public List<CommentInfo> selectCommentInfo_byBoardIdx(Integer board_idx){
        return comment_tb_dao.selectCommentInfo_byBoardDdx(board_idx);
    }

    //댓글 작성
    public void insertCommentInfo(CommentCommand commentCommand, String img, String sns){
        comment_tb_dao.insertCommentInfo(commentCommand, img, sns);
    }

    //댓글 삭제
    public void deleteCommentInfo(Integer comment_idx, Integer parent_comment){
        comment_tb_dao.deleteCommentInfo(comment_idx);
        if (parent_comment != 0){
            comment_tb_dao.deleteParent(parent_comment);
        }
    }

    //댓글 수정
    public void updateCommentInfo(Integer comment_idx, String content){
        comment_tb_dao.updateCommentInfo(comment_idx, content);
    }

    //대댓글 목록 조회
    public List<CommentInfo> selectCocComment_byBoardIdx(Integer board_idx){
        return comment_tb_dao.selectCocComment_byBoardIdx(board_idx);
    }

    //게시글 좋아요 추가
    public boolean addBoardLike(BoardLikeInfo boardLikeInfo, BoardInfo boardInfo){

        //좋아요 추가가 가능하다면(해당 게시글에 좋아요를 하지 않은 상태하면)
        if(board_like_tb_dao.selectBoardLikeInfo(boardLikeInfo)){
            //좋아요(게시글-회원아이디) 정보 추가
            board_like_tb_dao.insertBoardLike(boardLikeInfo);
            //게시글 좋아요 정보 변경
            board_tb_dao.addBoardLike(boardInfo);
            return true;
        }else {
            return false;
        }
    }

    //게시글 좋아요 삭제
    public boolean deleteBoardLike(BoardLikeInfo boardLikeInfo,BoardInfo boardInfo ){
        //좋아요 삭제가 가능하다면(해당 게시글에 좋아요를 한 상태하면)
        if(!board_like_tb_dao.selectBoardLikeInfo(boardLikeInfo)){
            //좋아요(게시글-회원아이디) 정보 삭제
            board_like_tb_dao.deleteBoardLike(boardLikeInfo);
            //게시글 좋아요 정보 변경
            board_tb_dao.minusBoardLike(boardInfo);
            return true;
        }else {
            return false;
        }
    }

    //댓글 좋아요 추가
    public void addCommentLike(CommentLikeInfo commentLikeInfo, CommentInfo commentInfo){
        //좋아요 추가가 가능한 상테라면
        if(comment_like_tb_dao.selectCommentLikeInfo(commentLikeInfo)){
            //좋아요 추가
            comment_like_tb_dao.insertCommentLike(commentLikeInfo);
            //댓글 좋아요 정보 변경
            comment_tb_dao.plusCommentLike(commentInfo);
        }
    }

    //댓글 좋아요 삭제
    public void deleteCommentLike(CommentLikeInfo commentLikeInfo, CommentInfo commentInfo){
        //좋아요 삭제가 가능한 상태라면
        if(!comment_like_tb_dao.selectCommentLikeInfo(commentLikeInfo)) {
            //좋아요 삭제
            comment_like_tb_dao.deleteCommentLike(commentLikeInfo);
            //댓글 좋아요 정보 변경
            comment_tb_dao.minusCommentLike(commentInfo);
        }
    }

    //댓글 정보 조회
    public CommentInfo selectCommentInfo_byCommentIdx(Integer comment_idx){
        return comment_tb_dao.selectCommentInfo_byCommentIdx(comment_idx);
    }
}
