package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save: member={}",member);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){
//        List<Member> all = findAll();
//        for (Member m : all) {
//            if(m.getLoginId().equals(loginId)){
//                return Optional.of(m);  // Optional이라는 통에 회원객체가 있을 수도 있고 비어있을수도 있음
//            }
//        }
//        return Optional.empty(); // 비어있으면 null반환

        return findAll().stream()   //리스트를 스트림으로 바꾸고
                .filter(member -> member.getLoginId().equals(loginId)) //필터에서 만족하는 것은 다음단계로
                .findFirst();   //먼저 나오는애 반환 뒤에는 무시
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }


}
