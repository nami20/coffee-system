package com.ideas.interview;

public class Display {

	private String message;

	public void show(String message) {
		this.message = message;
	}

	public String currentMessage() {
		return this.message;
	}

}
