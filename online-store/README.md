Online Store
------------

### Requirements

Given a user has a few items in her shopping basket
When she submits the payment details
Then she receives the payment confirmation

### Acceptance criteria

1. User has a shopping basket with a few items
2. Check if all items are in stock (external system). 
	1. If not in stock, payment is aborted and user is informed which items are out of stock
3. Payment is sent to the payment gateway
	1. Check if user passes the credit check (external system). 
		1. If user fails credit check, payment is aborted and message is returned to the user
	2. Payment details is sent to the Credit Card payment system
4. If payment is successful, a confirmation email is sent
5. Payment status is returned. It could be:
	1. OK. Everything went well. 
	2. Fail. Message explaining what went wrong is returned. 

### TIPS

1. Don't worry about the content of the basket. It can be mocked.
2. Don't worry about the User attributes.
3. Don't worry about the delivery mechanism. The entry point of this exercise is an action.
4. Mock external systems
5. Model packages and classes according to IDD building blocks.
6. Don't worry about acceptance tests for this exercise.