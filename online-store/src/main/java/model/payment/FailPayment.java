package model.payment;

import java.util.List;

import static java.util.Arrays.asList;

public class FailPayment extends PaymentStatus {

	public FailPayment(List<String> messages) {
		super(messages);
	}

	public FailPayment(String... messages) {
		super(asList(messages));
	}

	@Override
	public boolean fail() {
		return true;
	}
}
