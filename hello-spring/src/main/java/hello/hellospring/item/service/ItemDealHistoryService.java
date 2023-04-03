package hello.hellospring.item.service;

import hello.hellospring.item.domain.ItemDealHistory;

public interface ItemDealHistoryService {

    //거래 버튼 누른 경우
    ItemDealHistory deal(Long vintageBoardId, Long buyerNo);
}
