package board.blog.controller;

import board.blog.domain.Member;
import board.blog.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String LoginForm() {
        return "members/login";
    }

    @PostMapping("/login")
    /* HttpServletResponse : 아래의 쿠키값을 생성 후 클라이언트에게 보낼때 response 에 넣어서 보내야함 */
    public String login(
            @ModelAttribute LoginForm form,
            BindingResult bindingResult,
            HttpServletResponse httpServletResponse,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            return "members/login";
        }//@modelattribute에서 loginform form객체로 바인딩한 데이테의 검증결과 확인

        // loginForm 을 통해 값들을 가져옴
        Member LoginMember = loginService.login(form.getLoginId(), form.getLoginPw());

        if (LoginMember == null) { // login 메서드에서 던져주는 값이 null 이면 로그인 실패
            System.out.println("로그인 실패");
            return "members/login";
        }
        //로그인 성공시 사용자 정보를 세션에 저장
        session.setAttribute("loginUser", LoginMember);

        // null 이외의 값 즉 member 객체라면 로그인 성공 처리
        // 쿠키에 시간 정보를 주지 않았기 때문에 세션 쿠키로 인식됨 -> 브라우저 종료시 모두 종료
        Cookie CookieCode = new Cookie("memberCode", String.valueOf(LoginMember.getCode()));
        httpServletResponse.addCookie(CookieCode);
        System.out.println("쿠키 정보 전달 완료 : " + CookieCode);
        return "redirect:/";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         HttpSession session){
       //세션 삭제
        session.invalidate();
        //System.out.println("세션 삭제");
        //쿠키 삭제
        Cookie newCookie= new Cookie("memberCode",null);
        newCookie.setPath("/");
        newCookie.setValue("");
        newCookie.setMaxAge(0);
        response.addCookie(newCookie);
        //System.out.println("쿠키 삭제");
        return "redirect:/";
    }

}






