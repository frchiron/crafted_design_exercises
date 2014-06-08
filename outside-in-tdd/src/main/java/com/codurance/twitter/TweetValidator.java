package com.codurance.twitter;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class TweetValidator {

	private static final int TWEET_MAX_LENGTH = 140;

	public void validate(String tweet) {
		if (tweet.length() > TWEET_MAX_LENGTH || isEmpty(tweet)) {
			throw new IllegalArgumentException();
		}
	}
}
