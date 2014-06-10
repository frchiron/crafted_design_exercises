package model.stock;

import java.util.List;

import static java.util.Arrays.asList;

public class StockCheck {

	private StockCheckStatus status;
	private String[] messages;

	public StockCheck(StockCheckStatus status, String... messages) {
		this.status = status;
		this.messages = messages;
	}

	public StockCheckStatus status() {
		return status;
	}

	public List<String> messages() {
		return asList(messages);
	}
}
