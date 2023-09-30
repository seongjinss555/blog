package board.blog.repository;

import board.blog.domain.Member;
import board.blog.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository=new MemoryMemberRepository();
    @AfterEach
    public void AfterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();

        member.setName("안성진");

        repository.save(member);

        Member result=repository.findByCode(member.getCode()).get();
        //repository 안에 저장된 membercode를 가져와서 result에 저장
        Assertions.assertThat(result).isEqualTo(member);

    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("안성진");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("김가나");
        repository.save(member2);

        Member result=repository.findByName("안성진").get();

        Assertions.assertThat(result).isEqualTo(member1);

        System.out.println(member1.getName());
    }
    @Test
    public void findById(){
        Member member = new Member();
        member.setId("qwer");
        repository.save(member);

        Member findId=repository.findById("qwer").get();

        Assertions.assertThat(findId).isEqualTo(member);

        System.out.println(member.getId());
    }

    @Test
    public void findByPasswd(){
        Member member = new Member();
        member.setPasswd("1234");
        repository.save(member);

        Member findPw=repository.findByPasswd("1234").get();

        Assertions.assertThat(findPw).isEqualTo(member);

        System.out.println(member.getPasswd());
    }

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("안성진");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("김가나");
        repository.save(member2);

        List<Member> result=repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
