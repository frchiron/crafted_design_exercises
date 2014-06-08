package unit;

import com.codurance.twitter.Tweet;
import com.codurance.twitter.TweetRepository;
import com.codurance.twitter.TweetValidator;
import com.codurance.twitter.TwitterEngine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TwitterEngineShould {

	private static final String LONG_TWEET = "";
	private static final String SANDRO = "sandromancus";
	private static final String MASH = "mashooq";
	private static final String SANDRO_FIRST_TWEET = "Sandro first tweet";
	private static final List<Tweet> SANDRO_TWEETS = new ArrayList<>();
	private static final List<Tweet> SOME_TWEETS = new ArrayList<>();

	@Mock private TweetValidator tweetValidator;
	@Mock private TweetRepository tweetRepository;

	private TwitterEngine twitterEngine;

	@Before
	public void initialise() {
		twitterEngine = new TwitterEngine(tweetValidator, tweetRepository);
	}

	@Test(expected = IllegalArgumentException.class) public void
	validate_long_tweet() {
		doThrow(IllegalArgumentException.class).when(tweetValidator).validate(LONG_TWEET);

		twitterEngine.post("any twitter id", LONG_TWEET);
	}

	@Test public void
	store_a_valid_tweet() {
		twitterEngine.post(SANDRO, SANDRO_FIRST_TWEET);

	    verify(tweetRepository).post(SANDRO, SANDRO_FIRST_TWEET);
	}

	@Test public void
	return_tweet_sent_by_a_user() {
		given(tweetRepository.tweetsFrom(SANDRO)).willReturn(SANDRO_TWEETS);

		List<Tweet> tweets = twitterEngine.tweetsBy(SANDRO);

	    assertThat(tweets, is(SANDRO_TWEETS));
	}

	@Test public void
	store_following_information() {
		twitterEngine.formerFollowsLatter(SANDRO, MASH);

		verify(tweetRepository).addFollowing(SANDRO, MASH);
	}

	@Test public void
	return_wall_belonging_to_a_user() {
		given(tweetRepository.wallFor(SANDRO)).willReturn(SOME_TWEETS);

		List<Tweet> tweets = twitterEngine.wallFor(SANDRO);

	    assertThat(tweets, is(SOME_TWEETS));
	}

}
