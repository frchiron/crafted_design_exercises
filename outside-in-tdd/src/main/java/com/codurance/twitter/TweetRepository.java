package com.codurance.twitter;

import java.util.List;

public interface TweetRepository {

	void post(String twitterId, String tweet);

	List<Tweet> tweetsFrom(String twitterId);
}
