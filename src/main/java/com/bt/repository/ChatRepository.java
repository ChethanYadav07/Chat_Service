package com.bt.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.bt.entity.ChatHistory;
 
@Repository
public interface ChatRepository extends JpaRepository<ChatHistory, Integer>{
 
}