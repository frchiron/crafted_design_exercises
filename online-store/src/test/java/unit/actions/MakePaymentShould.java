package unit.actions;

import actions.MakePayment;
import model.shopping.Basket;
import model.shopping.PaymentDetails;
import model.shopping.PaymentStatus;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class MakePaymentShould {

	private MakePayment makePayment;
	private Basket basket;
	private PaymentDetails paymentDetails;

	@Before
	public void initialise() {
		makePayment = new MakePayment();
	}

	@Test public void
	return_payment_status_after_submiting_payment_details() {
		assertThat(makePayment.execute(basket, paymentDetails), instanceOf(PaymentStatus.class));
	}

}
