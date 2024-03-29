package hello.hellospring.item.service;


import hello.hellospring.item.domain.Item;
import hello.hellospring.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public Item save(Item item) {
        Item saveItem = itemRepository.save(item);
        return item;
    }

    @Transactional //해당 카테고리의 아이템을 모두 반환할 수 있도록 트랜잭션 처리
    public List<Long> findByCategory(String itemCategory) {
        List<Long> itemList = itemRepository.findByItemCategory(itemCategory);
        return itemList;
    }
}
