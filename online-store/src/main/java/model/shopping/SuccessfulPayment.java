package model.shopping;

import static java.util.Collections.EMPTY_LIST;

public class SuccessfulPayment extends PaymentStatus {

	public SuccessfulPayment() {
		super(EMPTY_LIST);
	}

	@Override
	public boolean fail() {
		return false;
	}
}
