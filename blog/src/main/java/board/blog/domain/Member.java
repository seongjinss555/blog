package board.blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code; // 회원가입 번호
    @Column(name="name")
    private String name; // 회원 이름
   @Column(name="id")
    private String id; // 회원 id
   @Column(name="passwd")
    private String passwd; // 회원 passwd

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Board> board =new ArrayList<>();
}
