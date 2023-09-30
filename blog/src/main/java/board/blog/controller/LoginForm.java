package board.blog.controller;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class LoginForm {

    @NotNull
    private String loginId;

    @NotNull
    private String loginPw;


}
