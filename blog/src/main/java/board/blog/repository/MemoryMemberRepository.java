package board.blog.repository;

import board.blog.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setCode(++sequence); // store 에 저장 전 member 값에 sequence 값을 하나 올려서 넣음
        store.put(member.getCode(), member); // store 에 member와 code와 함께 저장
        return member;
    }

    @Override
    public Optional<Member> findByCode(Long code) {
        return Optional.ofNullable(store.get(code));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    @Override
    public Optional<Member> findById(String id){ // id로 저장된 값을 찾아옴
        return store.values().stream() // store에 저장된 값(value)를 반환
                .filter(member -> member.getId().equals(id))
                // member 에서 meber.getid 를 통해 id 와 동일한 값을 필터
                .findAny();
    }
    @Override
    public Optional<Member> findByPasswd(String passwd){
        return store.values().stream()
                .filter(member -> member.getPasswd().equals(passwd))
                .findAny();
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
