package com.bt.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class ChatHistory{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int chatId;
	String userId;
	LocalDateTime startChatSession;
	LocalDateTime endChatSession;
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public LocalDateTime getStartChatSession() {
		return startChatSession;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setStartChatSession(LocalDateTime startChatSession) {
		this.startChatSession = startChatSession;
	}
	@Override
	public String toString() {
		return "ChatHistory [chatId=" + chatId + ", userId=" + userId + ", startChatSession=" + startChatSession
				+ ", endChatSession=" + endChatSession + "]";
	}
	public LocalDateTime getEndChatSession() {
		return endChatSession;
	}
	public void setEndChatSession(LocalDateTime localDateTime) {
		this.endChatSession = localDateTime;
	}
}