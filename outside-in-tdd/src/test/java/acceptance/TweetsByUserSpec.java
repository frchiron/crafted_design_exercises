package acceptance;

import com.codurance.twitter.Tweet;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TweetsByUserSpec extends BaseTwitterSpec {

	@Test public void
	should_return_all_tweets_from_a_user_in_reverse_chronological_order() {
		post(SANDRO, SANDRO_FIRST_TWEET);
		post(SAMIR,  SAMIR_FIRST_TWEET);
		post(SANDRO, SANDRO_SECOND_TWEET);
		post(MASH,   MASH_FIRST_TWEET);

		List<Tweet> tweets = tweetsFrom(SANDRO);

		assertThat(tweets.size(), is(2));
		assertThat(tweets.get(0).message(), is(SANDRO_SECOND_TWEET));
		assertThat(tweets.get(1).message(), is(SANDRO_FIRST_TWEET));
	}

}
