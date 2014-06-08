package model.payment;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public abstract class PaymentStatus {

	private List<String> messages;

	public PaymentStatus(List<String> messages) {
		this.messages = messages;
	}

	public abstract boolean fail();

	public List<String> messages() {
		return unmodifiableList(messages);
	}
}
