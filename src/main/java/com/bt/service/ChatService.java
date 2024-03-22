package com.bt.service;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.entity.ChatHistory;
import com.bt.entity.ChatMessage;
import com.bt.exception.UserNotFoundException;
import com.bt.repository.ChatRepository;


@Service
public class ChatService {
	@Autowired
	ChatRepository repo;

	LocalDateTime dateTime = LocalDateTime.now();
	// Map the data coming from front end to our models
	public ChatHistory saveData(ChatMessage message) {
		ChatHistory history = new ChatHistory();
		history.setUserId(message.getUser());
		history.setEndChatSession(dateTime);
		history.setStartChatSession(dateTime);
		return history;
	}

	// To save the chat history into the data base
	public ChatHistory saveChatHistory(ChatHistory data) {
	    if (data.getUserId() == null || data.getUserId().isEmpty()) {
	        throw new UserNotFoundException("User Not found!!!");
	    } else {
	        ChatHistory chatHistory = repo.save(data);
	        System.out.println(chatHistory);
	        return chatHistory;
	    }
	}
	
	public ChatHistory updateEndSession(ChatHistory data) throws Exception {	
		if (repo.existsById(data.getChatId())) {
			return repo.save(data);
		} else {
			throw new Exception("No Id found ");
		}
		
	}
}