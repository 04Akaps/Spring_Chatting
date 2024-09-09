package com.example.demo.domain.repository;

import java.util.List;

import com.example.demo.domain.repository.types.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findTop10BySenderOrReceiverOrderByTIDDesc(String sender, String receiver);


    // @Query("SELECT c FROM Chat c WHERE c.sender = :sender OR c.receiver = :receiver ORDER BY c.t_id DESC")
    // List<Chat> findTop10Chats(@Param("sender") String sender, @Param("receiver") String receiver, Pageable pageable);
}
