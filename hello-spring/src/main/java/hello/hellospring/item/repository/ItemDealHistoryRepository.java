package hello.hellospring.item.repository;

import hello.hellospring.item.domain.ItemDealHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDealHistoryRepository extends JpaRepository<ItemDealHistory, Long> {
}
