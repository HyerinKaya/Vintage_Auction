package hello.hellospring.member.dto;

import hello.hellospring.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberInfoDto {

    private String id;
    private String name;
    private String memberImgUrl;
    private Long memberPoint;


    @Builder
    public MemberInfoDto(Member member) {
        this.id = member.getMemberId();
        this.name = member.getMemberName();
        this.memberImgUrl = member.getMemberImgUrl();
        this.memberPoint = member.getMemberPoint();
    }
}
