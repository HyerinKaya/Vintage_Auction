package hello.hellospring.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuctionResult<T> {
    private int curPage;
    private int totalPage;
    private T auctionBoardList;
}
