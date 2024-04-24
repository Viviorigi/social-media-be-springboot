package com.duong.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duong.models.Chat;
import com.duong.models.Message;
import com.duong.models.User;
import com.duong.repository.ChatRepository;
import com.duong.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private ChatService chatService;
	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Message createMessage(User user, Integer chatId, Message req) throws Exception {
	
		Message message = new Message();
		
		Chat chat=chatService.findChatById(chatId);
		
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		Message saveMessage= messageRepository.save(message);
		
		chat.getMessages().add(saveMessage);
		chatRepository.save(chat);
		
		return saveMessage;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {
		
		Chat chat=chatService.findChatById(chatId);
		
		return messageRepository.findByChatId(chatId);
	}

	

}
