package com.duong.service;

import java.util.List;

import com.duong.models.Message;
import com.duong.models.User;

public interface MessageService {
	public Message createMessage(User user,Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
	
}
