package board.blog.repository;

import board.blog.domain.Board;
import board.blog.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>{
//    // 게시물의 id를 사용하여 특정 게시물을 찾는 메서드
      Optional<Board> findBoardById(Long id);
//
      List<Board> findAll();
//    //게시물의 id를 사용하여 게시글 삭제
      void deleteById(Long id);





}
