package com.duong.service;

import java.util.List;

import com.duong.models.Chat;
import com.duong.models.User;

public interface ChatService {

	public Chat CreateChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
	
}
