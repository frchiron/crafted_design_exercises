package com.codurance.twitter;

import java.util.List;

public class TwitterEngine {

	private TweetValidator tweetValidator;
	private TweetRepository tweetRepository;

	public TwitterEngine(TweetValidator tweetValidator, TweetRepository tweetRepository) {
		this.tweetValidator = tweetValidator;
		this.tweetRepository = tweetRepository;
	}

	public void post(String twitterId, String tweet) {
		tweetValidator.validate(tweet);
		tweetRepository.post(twitterId, tweet);
	}

	public List<Tweet> tweetsFrom(String twitterId) {
		return tweetRepository.tweetsFrom(twitterId);
	}
}
