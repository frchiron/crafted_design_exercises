package unit;

import com.codurance.twitter.InMemoryTweetRepository;
import com.codurance.twitter.Tweet;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InMemoryTweetRepositoryShould {

	private static final String SANDRO_FIRST_TWEET = "Sandro First tweet";
	private static final String SANDRO = "sandromancuso";
	private static final String SANDRO_SECOND_TWEET = "Sandro second tweet";
	private static final String MASH = "mashooq";
	private static final String MASH_FIRST_TWEET = "Mash first tweet";
	private static final String SANDRO_THIRD_TWEET = "Sandro third tweet";
	private InMemoryTweetRepository tweetRepository;

	@Before
	public void initialise() {
		tweetRepository = new InMemoryTweetRepository();
	}

	@Test public void
	return_a_tweet_posted_by_a_user() {
		tweetRepository.post(SANDRO, SANDRO_FIRST_TWEET);

		List<Tweet> tweets = tweetRepository.tweetsFrom(SANDRO);
		Tweet tweet = tweets.get(0);

		assertThat(tweets.size(), is(1));
		assertThat(tweet.twitterId(), is(SANDRO));
		assertThat(tweet.message(), is(SANDRO_FIRST_TWEET));
	}

	@Test public void
	return_tweets_by_a_user_in_reverse_chronological_order() {
		tweetRepository.post(SANDRO, SANDRO_FIRST_TWEET);
		tweetRepository.post(SANDRO, SANDRO_SECOND_TWEET);
		tweetRepository.post(MASH, MASH_FIRST_TWEET);
		tweetRepository.post(SANDRO, SANDRO_THIRD_TWEET);

		List<Tweet> tweets = tweetRepository.tweetsFrom(SANDRO);

	    assertThat(tweets.size(), is(3));
		assertThat(tweets.get(0).message(), is(SANDRO_THIRD_TWEET));
		assertThat(tweets.get(1).message(), is(SANDRO_SECOND_TWEET));
		assertThat(tweets.get(2).message(), is(SANDRO_FIRST_TWEET));
	}

}
