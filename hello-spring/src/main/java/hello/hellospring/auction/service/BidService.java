package hello.hellospring.auction.service;

import hello.hellospring.auction.dto.BidRequestDto;
import hello.hellospring.auction.dto.BidResponseDto;

public interface BidService {
    BidResponseDto bidSave(BidRequestDto bidRequestDto);
    BidResponseDto bidSelect(Long auctionId);
}
