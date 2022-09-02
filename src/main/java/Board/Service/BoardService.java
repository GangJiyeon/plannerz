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

    public BoardInfo insertBoard(BoardCommand boardCommand){
       Integer board_idx = board_tb_dao.insertBoard(boardCommand);
       return board_tb_dao.selectBoardInfo_byBoardIdx(board_idx);
    }

    public BoardInfo selectBoardInfo(Integer board_idx){
        return board_tb_dao.selectBoardInfo_byBoardIdx(board_idx);
    }

    public void updateBoardSee(BoardInfo boardInfo){
        board_tb_dao.updateBoardSee(boardInfo);
    }

    public List<BoardInfo> selectBoardList_byUserId(String user_id){
        return board_tb_dao.selectBoardList_byUserId(user_id);
    }

    public List<BoardInfo> selectBoardList_byStartNum(Integer start_num){
        return board_tb_dao.selectBoardList_byStartNum(start_num);
    }

    public void delete_board(Integer board_idx){
        board_tb_dao.delete_board(board_idx);
    }

    public void update_board(BoardCommand boardCommand){
        board_tb_dao.update_board(boardCommand);
    }

    public List<CommentInfo> selectCommentInfo_byBoardIdx(Integer board_idx){
        return comment_tb_dao.selectCommentInfo_byBoardDdx(board_idx);
    }

    public void insertCommentInfo(CommentCommand commentCommand){
        comment_tb_dao.insertCommentInfo(commentCommand);
    }

    public void deleteCommentInfo(Integer comment_idx){
        comment_tb_dao.deleteCommentInfo(comment_idx);
    }

    public void updateCommentInfo(Integer comment_idx, String content){
        comment_tb_dao.updateCommentInfo(comment_idx, content);
    }

    public List<CommentInfo> selectCocComment_byBoardIdx(Integer board_idx){
        return comment_tb_dao.selectCocComment_byBoardIdx(board_idx);
    }

    public boolean addBoardLike(BoardLikeInfo boardLikeInfo, BoardInfo boardInfo){
        if(board_like_tb_dao.selectBoardLikeInfo(boardLikeInfo)){
            board_like_tb_dao.insertBoardLike(boardLikeInfo);
            board_tb_dao.addBoardLike(boardInfo);
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteBoardLike(BoardLikeInfo boardLikeInfo,BoardInfo boardInfo ){
        if(!board_like_tb_dao.selectBoardLikeInfo(boardLikeInfo)){
            board_like_tb_dao.deleteBoardLike(boardLikeInfo);
            board_tb_dao.minusBoardLike(boardInfo);
            return true;
        }else {
            return false;
        }
    }

    public void addCommentLike(CommentLikeInfo commentLikeInfo, CommentInfo commentInfo){
        if(comment_like_tb_dao.selectCommentLikeInfo(commentLikeInfo)){
            comment_like_tb_dao.insertCommentLike(commentLikeInfo);
            comment_tb_dao.plusCommentLike(commentInfo);
        }
    }

    public void deleteCommentLike(CommentLikeInfo commentLikeInfo, CommentInfo commentInfo){
        if(!comment_like_tb_dao.selectCommentLikeInfo(commentLikeInfo)) {
            comment_like_tb_dao.deleteCommentLike(commentLikeInfo);
            comment_tb_dao.minusCommentLike(commentInfo);
        }
    }

    public CommentInfo selectCommentInfo_byCommentIdx(Integer commment_idx){
        return comment_tb_dao.selectCommentInfo_byCommentIdx(commment_idx);
    }
}
