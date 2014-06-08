package com.codurance.twitter;

import java.util.List;

public class InMemoryTweetRepository implements TweetRepository {
	@Override
	public void post(String twitterId, String tweet) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Tweet> tweetsFrom(String twitterId) {
		throw new UnsupportedOperationException();
	}
}
