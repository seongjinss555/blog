package board.blog.controller;

import board.blog.domain.Board;
import board.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

   @GetMapping("/list")
   public String listBoards(Model model){
       List<Board> boards=boardService.findAllBoard();
       model.addAttribute("boards",boards);
       return "boards/list";
   } // 글 목록이 보여짐


   @GetMapping("/{id}")
    public String viewBoard(@PathVariable Long id, Model model){
       Optional<Board> board=boardService.findBoardById(id);
       model.addAttribute("board",board.orElse(null));
       return "boards/view";
   }  //해당 아이디로 쓴 글에 대한 상세정보 확인

   @GetMapping("/post")
    public String createBoard(){
       return "boards/post";
   }

   @PostMapping("/post")
    public String createBoard(@ModelAttribute Board board){
       boardService.createBoard(board);
       return"/boards/list";
   }

   @GetMapping("/boards/{id}/edit")
   public String editForm(@PathVariable Long id, Model model){
       Optional<Board> board= boardService.findBoardById(id);
       model.addAttribute("board",board.orElse(null));
       return "boards/editForm";
   }
   @PostMapping("/boards/{id}/edit")
    public String editBoard(@PathVariable Long id,@ModelAttribute Board board){
       boardService.editBoard(id,board);
       return "/boards/{id}";
   }

   @PostMapping("boards/{id}/delete")
    public String delteBoard(@PathVariable Long id){
       boardService.deleteBoard(id);
       return "redirect:/boards";
   }
}
