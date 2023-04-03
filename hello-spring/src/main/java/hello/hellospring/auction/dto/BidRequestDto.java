package hello.hellospring.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidRequestDto implements Serializable {
    private Long auctionId;
    private Long bidderId;
    private Long bidPrice;
}

