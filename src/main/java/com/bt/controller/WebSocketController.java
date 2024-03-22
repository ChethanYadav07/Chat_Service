package com.bt.controller;


import com.bt.entity.ChatHistory;  
import com.bt.entity.ChatMessage;
import com.bt.service.ChatService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class WebSocketController {
	final static Logger log=LoggerFactory.getLogger(WebSocketController.class);
	@Autowired
	ChatService chatService;
	
	 @MessageMapping("/chat/{roomId}")//The incoming messages will be mapped here
	    @SendTo("/topic/{roomId}")//this is the subscription url
	    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message) {
	    	System.out.println("room id is : "+roomId);
	    	System.out.println("User Id : "+message.getUser());
	    	System.out.println("User Message : "+message.getMessage());
	    	ChatHistory history= chatService.saveData(message);
	    	chatService.saveChatHistory(history);
	    	log.info("Chat History Saved Successfully with chat Id : "+history.getChatId());
	        return new ChatMessage(message.getMessage(), message.getUser());
	    }
	 
	 
	 @PutMapping("/updateEndChatSessionTime")
		public ResponseEntity<?> updateSession(@RequestBody ChatHistory data) throws Exception {
			return new ResponseEntity<ChatHistory>(chatService.updateEndSession(data), HttpStatus.OK);
		}
   
}