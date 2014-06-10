package actions;

import model.payment.*;
import model.shopping.*;
import model.stock.Stock;
import model.stock.StockCheck;

import static model.stock.StockCheckStatus.OUT_OF_STOCK;

public class MakePayment {
	private Stock stock;
	private PaymentGateway paymentGateway;
	private PaymentConfirmationEmail paymentConfirmationEmail;

	public MakePayment(Stock stock, PaymentGateway paymentGateway, PaymentConfirmationEmail paymentConfirmationEmail) {
		this.stock = stock;
		this.paymentGateway = paymentGateway;
		this.paymentConfirmationEmail = paymentConfirmationEmail;
	}

	public PaymentStatus execute(Basket basket, PaymentDetails paymentDetails) {
		StockCheck stockCheck = stock.contains(basket.items());
		if (OUT_OF_STOCK == stockCheck.status()) {
			return new PaymentFailed(stockCheck.messages());
		}

		PaymentStatus status = paymentGateway.makePaymentWith(paymentDetails);
		if (status.success()) {
			paymentConfirmationEmail.send(basket);
		}
		return status;
	}

}
