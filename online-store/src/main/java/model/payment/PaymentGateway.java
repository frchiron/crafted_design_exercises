package model.payment;

public interface PaymentGateway {

	void makePaymentWith(PaymentDetails paymentDetails);
}
