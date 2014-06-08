package com.codurance.twitter;

import java.util.*;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

public class InMemoryTweetRepository implements TweetRepository {

	List<Tweet> tweets = new ArrayList<>();
	Map<String, Set<String>> followingMap = new HashMap<>();

	@Override
	public void post(String twitterId, String tweet) {
		tweets.add(new Tweet(nextId(), twitterId, tweet));
	}

	@Override
	public List<Tweet> tweetsFrom(String twitterId) {
		return tweets.stream()
					.filter(t -> t.twitterId().equals(twitterId))
					.sorted(reverseOrder())
					.collect(toList());
	}

	@Override
	public void addFollowing(String twitterId, String twitterIdToBeFollowed) {
		followingListFor(twitterId).add(twitterIdToBeFollowed);
	}

	@Override
	public List<Tweet> wallFor(String twitterId) {
		Set<String> following = followingListFor(twitterId);
		return tweets.stream()
					.filter(t -> t.twitterId().equals(twitterId) || following.contains(t.twitterId()))
					.sorted(reverseOrder())
					.collect(toList());
	}

	private Set<String> followingListFor(String twitterId) {
		Set<String> following = followingMap.getOrDefault(twitterId, new HashSet<>());
		followingMap.put(twitterId, following);
		return following;
	}

	private int nextId() {
		return tweets.isEmpty()
						? 1
						: tweets.get(tweets.size() - 1).id() + 1;
	}
}
