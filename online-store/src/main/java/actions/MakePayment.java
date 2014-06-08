package actions;

import model.shopping.Basket;
import model.shopping.PaymentDetails;
import model.shopping.PaymentStatus;

public class MakePayment {
	public PaymentStatus execute(Basket basket, PaymentDetails paymentDetails) {
		return new PaymentStatus();
	}
}
