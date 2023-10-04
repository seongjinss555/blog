package board.blog.service;

import board.blog.domain.Board;
import board.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board createBoard(Board board){
        return boardRepository.save(board);
    }
    public Board editBoard(Long id, Board editBoard){
        Optional<Board> existingBoard = boardRepository.findBoardById(id);

        if(existingBoard!=null){
            Board board= existingBoard.get();

            board.setTitle(editBoard.getTitle());
            board.setContent(editBoard.getContent());

            return boardRepository.save(board);
        }else{
            throw new RuntimeException("게시물을 수정할 수 없습니다");
        }
    }  //게시글 수정 로직

    public Optional<Board> findBoardById(Long id){
        return boardRepository.findById(id);
    }
    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }

    public List<Board> findAllBoard(){
        return boardRepository.findAll();
    }


}
