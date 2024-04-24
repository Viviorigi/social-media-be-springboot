package com.duong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duong.models.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	public List<Message> findByChatId(Integer chatId);
	
	
}
