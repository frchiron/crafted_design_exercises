package model.shopping;

import java.util.List;

public class FailPayment extends PaymentStatus {

	public FailPayment(List<String> messages) {
		super(messages);
	}

	@Override
	public boolean fail() {
		return true;
	}
}
