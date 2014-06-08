package model.payment;

public interface PaymentGateway {

	PaymentStatus makePaymentWith(PaymentDetails paymentDetails);
}
