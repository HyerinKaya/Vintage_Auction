package hello.hellospring.item.repository;

import hello.hellospring.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemId(Long itemId);

    @Query(value="select item_id from item where item.item_category= :itemCategory", nativeQuery = true)
    List<Long> findByItemCategory(@Param("itemCategory") String itemCategory);
}
