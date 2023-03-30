package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    //@GetMapping("/")
    public String home() {
        return "home";
    }

    //@GetMapping("/") //required==false : 로그인 안한 사용자도 들어와야하니까, Long memberId : cookie 값은 string이지만 스프링이 자동으로 타입전환 해줌
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if(memberId==null){
            return "home";
        }
        //로그인
        Member loginMember = memberRepository.findById(memberId);  
        if(loginMember==null){  //쿠키가 옜날에 만들어져서 없을 수도 있음
            return "home";
        }

        model.addAttribute("member",loginMember);
        return "loginHome";
    }

    @GetMapping("/") //required==false : 로그인 안한 사용자도 들어와야하니까, Long memberId : cookie 값은 string이지만 스프링이 자동으로 타입전환 해줌
    public String homeLoginV2(HttpServletRequest request, Model model) {

        // 세션 관리자에 저장된 회원 정보 조회
        Member member = (Member)sessionManager.getSession(request);

        //로그인
        if(member==null){  //쿠키가 옜날에 만들어져서 없을 수도 있음
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }
}