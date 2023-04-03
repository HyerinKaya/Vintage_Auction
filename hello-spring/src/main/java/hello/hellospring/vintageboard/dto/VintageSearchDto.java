package hello.hellospring.vintageboard.dto;

import hello.hellospring.vintageboard.domain.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

//중고상품 검색시 필요한 Dto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VintageSearchDto {
    private Long vintageId;
    private String vintageTitle;
    private List<UploadFile> uploadFiles;
}
