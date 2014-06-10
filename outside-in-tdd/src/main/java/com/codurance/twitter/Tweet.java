package com.codurance.twitter;

import static java.lang.Integer.valueOf;

public class Tweet implements Comparable<Tweet> {

	private int id;
	private String twitterId;
	private String message;

	public Tweet(int id, String twitterId, String message) {
		this.id = id;
		this.twitterId = twitterId;
		this.message = message;
	}

	public int id() {
		return id;
	}

	public String twitterId() {
		return twitterId;
	}

	public String message() {
		return message;
	}

	@Override
	public int compareTo(Tweet other) {
		return valueOf(id).compareTo(other.id);
	}
}
