package hello.hellospring.member.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberSigninDto {

    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
}