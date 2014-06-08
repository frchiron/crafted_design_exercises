package acceptance;

import com.codurance.twitter.Tweet;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WallSpec extends TwitterSpec {

	@Test
	public void
	should_return_all_tweets_from_a_user_in_reverse_chronological_order() {
		post(SANDRO, SANDRO_FIRST_TWEET);
		post(SAMIR,  SAMIR_FIRST_TWEET);
		post(SANDRO, SANDRO_SECOND_TWEET);
		post(PEDRO,  PEDRO_FIRST_TWEET);
		post(MASH,   MASH_FIRST_TWEET);
		post(STEVE,  STEVE_FIRST_TWEET);
		post(STEVE,  STEVE_SECOND_TWEET);
		post(SAMIR,  SAMIR_SECOND_TWEET);

		formerFollowsLatter(STEVE, SAMIR);
		formerFollowsLatter(STEVE, PEDRO);

		List<Tweet> tweets = wallOf(STEVE);

		assertThat(tweets.size(), is(5));
		assertThat(tweets.get(0).message(), is(SAMIR_SECOND_TWEET));
		assertThat(tweets.get(1).message(), is(STEVE_SECOND_TWEET));
		assertThat(tweets.get(2).message(), is(STEVE_FIRST_TWEET));
		assertThat(tweets.get(3).message(), is(PEDRO_FIRST_TWEET));
		assertThat(tweets.get(4).message(), is(SAMIR_FIRST_TWEET));
	}

}
