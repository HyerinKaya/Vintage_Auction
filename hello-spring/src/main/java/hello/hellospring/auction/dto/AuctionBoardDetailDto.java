package hello.hellospring.auction.dto;

import hello.hellospring.auction.domain.AuctionBoard;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AuctionBoardDetailDto {
    private String title;
    private String detail;
    private String itemName;
    private int itemBidPrice;
    private String itemCategory;
    private List<String> storeFileName;
    private String memberId;

    private Long memberNo;

    public AuctionBoardDetailDto(AuctionBoard auctionBoard) {
        title = auctionBoard.getAuctionTitle();
        detail = auctionBoard.getAuctionDetail();
        itemName = auctionBoard.getAuctionItem().getItemName();
        itemBidPrice = auctionBoard.getAuctionItem().getItemBidPrice();
        itemCategory = auctionBoard.getAuctionItem().getItemCategory();
        storeFileName = auctionBoard.getAuctionItem().getUploadFiles().stream()
                .map(storeFileName -> storeFileName.getStoreFileName())
                .collect(Collectors.toList());
        memberId = auctionBoard.getMember().getMemberName();
        memberNo = auctionBoard.getMember().getMemberNo();
    }
}
