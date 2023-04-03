package hello.hellospring.auction.repository;

import hello.hellospring.auction.domain.BidRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRecordRepository extends JpaRepository<BidRecord, Long> {
}