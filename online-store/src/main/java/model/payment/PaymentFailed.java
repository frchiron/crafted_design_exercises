package model.payment;

import java.util.List;

import static java.util.Arrays.asList;

public class PaymentFailed extends PaymentStatus {

	public PaymentFailed(List<String> messages) {
		super(messages);
	}

	public PaymentFailed(String... messages) {
		super(asList(messages));
	}

	@Override
	public boolean fail() {
		return true;
	}

	@Override
	public boolean success() {
		return false;
	}
}
