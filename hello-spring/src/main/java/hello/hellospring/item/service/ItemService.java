package hello.hellospring.item.service;

import hello.hellospring.item.domain.Item;

import java.util.List;

public interface ItemService {
    //1. 아이템 저장
    Item save(Item item);

    //2. 카테고리로 아이템 검색
    List<Long> findByCategory(String itemCategory);
}
