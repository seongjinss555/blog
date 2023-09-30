package board.blog.controller;

import board.blog.domain.Member;
import board.blog.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }  // bean으로 설정되었던 memberService를 넣어줌

    @GetMapping("/members/new")
    public String createForm(){
        return "members/newMember";
    } //get 방식으로 http에서 get으로 넘어올 때/ 주로 출력

    @PostMapping("/members/new")
    public String create(MemberForm memberForm){
        Member member=new Member();
        member.setName(memberForm.getName());
        member.setId(memberForm.getUserId());
        member.setPasswd(memberForm.getUserPw());

        memberService.join(member);

        return "redirect:/";
    } // http에서 members/new 페이지에 대해 post 방식으로 넘어올 때/ 주로 저장

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);

        return"/members/memberlist";
    }





}
