package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     *
     * @return null 로그인 실패
     */
    public Member login(String loginId, String password){
//        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
//        Member member = findMemberOptional.get(); //Optional에서 꺼내기 get() 없으면 예외터짐
//
//        if(member.getPassword().equals(password)){
//            return member;
//        }else{
//            return null;
//        }
        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))    // 옵셔널안에 들어있는 멤버 패스 워드 확인 맞으면 이거 쓰고
                .orElse(null);  //아니면 null 반환
    }
}
