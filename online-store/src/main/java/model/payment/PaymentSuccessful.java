package model.payment;

import static java.util.Collections.EMPTY_LIST;

public class PaymentSuccessful extends PaymentStatus {

	public PaymentSuccessful() {
		super(EMPTY_LIST);
	}

	@Override
	public boolean fail() {
		return false;
	}

	@Override
	public boolean success() {
		return true;
	}
}
