package com.codurance.twitter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;

public class InMemoryTweetRepository implements TweetRepository {

	List<Tweet> tweets = new ArrayList<>();

	@Override
	public void post(String twitterId, String tweet) {
		tweets.add(new Tweet(nextId(), twitterId, tweet));
	}

	@Override
	public List<Tweet> tweetsFrom(String twitterId) {
		return tweets.stream()
					.filter(t -> t.twitterId().equals(twitterId))
					.sorted(reverseOrder())
					.collect(Collectors.toList());
	}

	@Override
	public void addFollowing(String twitterId, String twitterIdToBeFollowed) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Tweet> wallFor(String twitterId) {
		throw new UnsupportedOperationException();
	}

	private int nextId() {
		return tweets.isEmpty()
						? 1
						: tweets.get(tweets.size() - 1).id() + 1;
	}
}
