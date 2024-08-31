package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.repository.types.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
   
}
