package actions;

import model.shopping.*;
import model.stock.Stock;
import model.stock.StockCheck;

import static model.stock.StockCheckStatus.OUT_OF_STOCK;

public class MakePayment {
	private Stock stock;

	public MakePayment(Stock stock) {
		this.stock = stock;
	}

	public PaymentStatus execute(Basket basket, PaymentDetails paymentDetails) {
		StockCheck stockCheck = stock.contains(basket.items());
		if (OUT_OF_STOCK == stockCheck.status()) {
			return new FailPayment(stockCheck.messages());
		}
		return new SuccessfulPayment();
	}
}
