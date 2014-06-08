package unit;

import com.codurance.twitter.TweetValidator;
import org.junit.Test;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.junit.Assert.fail;

public class TweetValidatorShould {

	private TweetValidator tweetValidator = new TweetValidator();

	@Test(expected = IllegalArgumentException.class) public void
	throw_exception_when_tweet_is_longer_than_140_chars() {
		tweetValidator.validate(repeat("X", 150));
	}

	@Test(expected = IllegalArgumentException.class) public void
	throw_exception_when_tweet_is_empty() {
		tweetValidator.validate(EMPTY);
	}

	@Test public void
	not_throw_exception_if_tweet_is_less_than_140_but_not_empty() {
		try {
			tweetValidator.validate("Valid tweet");
		} catch (IllegalArgumentException e) {
			fail("Exception should not be thrown");
		}
	}

}
