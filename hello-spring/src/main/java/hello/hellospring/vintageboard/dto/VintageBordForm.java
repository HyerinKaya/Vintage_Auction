package hello.hellospring.vintageboard.dto;

import hello.hellospring.item.domain.Item;
import hello.hellospring.vintageboard.domain.VintageBoard;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VintageBordForm {

    //VintageBoard 영역
    @NotEmpty
    @NotBlank
    private String vintageTitle;
    @NotEmpty
    @NotBlank
    private String vintageDetail;

    //Item 영역
    @NotEmpty
    @NotBlank
    private String itemName;
    @NotNull //NotEmpty 는 String 타입에만 가능하다.
    private Integer itemPrice;
    @NotEmpty
    @NotBlank
    private String itemCategory;


    //private List<MultipartFile> imageFiles; //이미지 파일들


    public VintageBoard vintageFormtoEntity(){
        return VintageBoard.builder()
                .vintageTitle(vintageTitle)
                .vintageDetail(vintageDetail)
                .build();
    }

    public Item itemFormtoEntity(){
        return Item.builder()
                .itemName(itemName)
                .itemPrice(itemPrice)
                .itemCategory(itemCategory)
                .build();
    }


}
