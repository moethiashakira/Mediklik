package com.mediklik.application;

import com.mediklik.models.User;

public class SessionController {
	private static SessionController session = null;
	private User user;
	
	private SessionController() {
		
	}
	
	private SessionController(User user) {
		this.user = user;
	}
	
	public static SessionController getSession() {
		if (session == null) {
			session = new SessionController();
		}
		return session;
	}
	
	public static SessionController getSession(User user) {
		if (session == null) {
			session = new SessionController(user);
		}
		return session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
