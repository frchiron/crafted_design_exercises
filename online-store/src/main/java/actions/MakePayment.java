package actions;

import model.payment.*;
import model.shopping.*;
import model.stock.Stock;
import model.stock.StockCheck;

import static model.stock.StockCheckStatus.OUT_OF_STOCK;

public class MakePayment {
	private Stock stock;
	private PaymentGateway paymentGateway;

	public MakePayment(Stock stock, PaymentGateway paymentGateway) {
		this.stock = stock;
		this.paymentGateway = paymentGateway;
	}

	public PaymentStatus execute(Basket basket, PaymentDetails paymentDetails) {
		StockCheck stockCheck = stock.contains(basket.items());
		if (OUT_OF_STOCK == stockCheck.status()) {
			return new FailPayment(stockCheck.messages());
		}
		return paymentGateway.makePaymentWith(paymentDetails);
//		return new SuccessfulPayment();
	}
}
