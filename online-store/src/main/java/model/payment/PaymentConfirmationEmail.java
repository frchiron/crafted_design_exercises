package model.payment;

import model.shopping.Basket;

public interface PaymentConfirmationEmail {
	void send(Basket basket);
}
