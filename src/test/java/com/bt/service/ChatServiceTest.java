package com.bt.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bt.entity.ChatHistory;
import com.bt.entity.ChatMessage;
import com.bt.exception.UserNotFoundException;
import com.bt.repository.ChatRepository;

@ExtendWith(MockitoExtension.class)
public class ChatServiceTest {

    @Mock
    private ChatRepository repo;

    @InjectMocks
    private ChatService chatService;

    private ChatMessage message;
    private LocalDateTime dateTime;

    @BeforeEach
    public void setup() {
        message = new ChatMessage();
        message.setUser("user123");

        dateTime = LocalDateTime.now();
    }

    @Test
    public void testSaveData() {
    	ChatHistory result = chatService.saveData(message);

        assertNotNull(result);
        assertEquals("user123", result.getUserId());

        // Compare individual components of LocalDateTime
        assertEquals(dateTime.getYear(), result.getStartChatSession().getYear());
        assertEquals(dateTime.getMonth(), result.getStartChatSession().getMonth());
        assertEquals(dateTime.getDayOfMonth(), result.getStartChatSession().getDayOfMonth());

        // Similar assertions for endChatSession
        assertEquals(dateTime.getYear(), result.getEndChatSession().getYear());
        assertEquals(dateTime.getMonth(), result.getEndChatSession().getMonth());
        assertEquals(dateTime.getDayOfMonth(), result.getEndChatSession().getDayOfMonth());
    }

    
    public ChatHistory saveChatHistory(ChatHistory data) {
	    if (data.getUserId() == null || data.getUserId().isEmpty()) {
	        throw new UserNotFoundException("User Not found!!!");
	    } else {
	        ChatHistory chatHistory = repo.save(data);
	        System.out.println(chatHistory);
	        return chatHistory;
	    }
	}
	
    
    
    @Test
    public void testSaveChatHistory_UserNotFound() {
        ChatHistory data = new ChatHistory();
//        data.setUserId("sandesh");
        assertThrows(UserNotFoundException.class, () -> chatService.saveChatHistory(data));
    }

    @Test
    public void testSaveChatHistory_Success() {
        ChatHistory data = new ChatHistory();
        data.setUserId("user123");

        when(repo.save(data)).thenReturn(data);

        ChatHistory result = chatService.saveChatHistory(data);

        assertNotNull(result);
        assertEquals("user123", result.getUserId());
    }
}