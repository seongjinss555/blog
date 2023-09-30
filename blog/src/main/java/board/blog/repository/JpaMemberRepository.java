package board.blog.repository;

import board.blog.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em=em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByCode(Long code) {
       Member member= em.find(Member.class, code);
       return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
       List<Member> result= em.createQuery("select M from Member M where M.name=:name", Member.class)
               .setParameter("name",name)
               .getResultList();

       return result.stream().findAny();
    }

    @Override
    public Optional<Member> findById(String Id) {
        List<Member> result= em.createQuery("select M from Member M where M.name=:id", Member.class)
                .setParameter("id",Id)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByPasswd(String passwd) {
        List<Member> result= em.createQuery("select M from Member M where M.passwd=:passwd", Member.class)
                .setParameter("passwd",passwd)
                .getResultList();

        return result.stream().findAny();
    }


    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m ", Member.class)
                .getResultList();
        return result;
    }
}
