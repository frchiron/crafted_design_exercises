Outside-In TDD
--------------

### Requirements

Create a Twitter engine class that provides the following:

* Accept tweets from users
* Return tweets from a specific user in reverse-chronological order
* Users can follow other users
* Return all tweets from users being followed, issuing a "wall"
	* User's own tweets are also returned as part of her wall. 

### Implementation details

* Create a TwitterEngine class to handle all requests defined above
* Posts contain the Twitter handle of the user sending the tweet and the message
* If a Twitter handle does not exist, it should be treated as a new twitter handle and be accepted.
* Tweet must be less than 140 chars to be accepted. If more, an exception should be thrown

### Testing

* Create a failing acceptance (using JUnit, Cucumber, etc) describing a scenario
* Test-Drive the TwitterEngine, mocking possible collaborators
* Once the TwitterEngine is unit tested, Test-Drive the each collaborator
* Once all collaborators are tested and implemented, the acceptance test should go green.
* Write another failing acceptance test and start the cycle again.

### TIPS

* You many need a TweetValidator for the validation
* You may need a in-memory repository to store user and tweets. Should you have in interface for it?
* Acceptance Tests
	1. After posting a few tweets, you should get the tweets from a specific user in reverse-chronological order
		* It's easier to get a reverse-chronological order sorting by ID than date. 
			* IDs should be created sequentially. Don't worry about it being thread-safe for this exercise.
	2. Different users post a few tweets. A user follows a couple of other users. User asks for tweets contained in her "wall"
	

	
	
