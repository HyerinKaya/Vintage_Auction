package hello.hellospring.dto;

import hello.hellospring.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor //기본 생성자 생성
@AllArgsConstructor
@Getter
public class MemberSignupDto {
    @NotBlank(message = "아이디를 입력하세요.")
    private String id;
    @NotBlank(message = "이름을 입력하세요.")
    private String name;
    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;


    // DTO -> 객체
    public Member toEntity(){
        return Member.builder()
                .memberId(id)
                .memberName(name)
                .memberPassword(password)
                .build();
    }
}