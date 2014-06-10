package unit.actions;

import actions.MakePayment;
import model.payment.*;
import model.shopping.Basket;
import model.stock.Stock;
import model.stock.StockCheck;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static model.stock.StockCheckStatus.IN_STOCK;
import static model.stock.StockCheckStatus.OUT_OF_STOCK;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MakePaymentShould {

	public static final String SOCRA_BOOK_NOT_IN_STOCK = "SoCra Book not in stock";
	private static final String CREDIT_CHECK_FAILED = "User credit check failed";
	private MakePayment makePayment;
	private Basket basket;
	private PaymentDetails paymentDetails;

	@Mock private Stock stock;
	@Mock private PaymentGateway paymentGateway;
	@Mock private PaymentConfirmationEmail paymentConfirmationEmail;

	@Before
	public void initialise() {
		makePayment = new MakePayment(stock, paymentGateway, paymentConfirmationEmail);
		basket = new Basket();
		paymentDetails = new PaymentDetails();
	}

	@Test public void
	inform_when_items_are_out_of_stock() {
		givenStockCheckWillFailWithMessage(SOCRA_BOOK_NOT_IN_STOCK);

		PaymentStatus paymentStatus = makePayment.execute(basket, paymentDetails);

		assertPaymentFailedWithMessage(paymentStatus, SOCRA_BOOK_NOT_IN_STOCK);
	}

	@Test public void
	send_payment_details_to_be_processed() {
		givenStockCheckIsSuccessful();
		givenPaymentIsSuccessful();

		makePayment.execute(basket, paymentDetails);

		verify(paymentGateway).makePaymentWith(paymentDetails);
	}

	@Test public void
	inform_when_payment_could_not_be_made() {
		givenStockCheckIsSuccessful();
		givenPaymentGatewayReturnsAFailureWithMessage(CREDIT_CHECK_FAILED);

		PaymentStatus status = makePayment.execute(basket, paymentDetails);

	    assertPaymentFailedWithMessage(status, CREDIT_CHECK_FAILED);
	}

	@Test public void
	not_send_a_confirmation_email_when_payment_is_not_successful() {
		givenStockCheckIsSuccessful();
		givenPaymentGatewayReturnsAFailureWithMessage(CREDIT_CHECK_FAILED);

		makePayment.execute(basket, paymentDetails);

		verify(paymentConfirmationEmail, never()).send(basket);
	}

	@Test public void
	send_payment_confirmation_email() {
		givenStockCheckIsSuccessful();
		givenPaymentIsSuccessful();

		makePayment.execute(basket, paymentDetails);

		verify(paymentConfirmationEmail).send(basket);
	}

	private void givenPaymentGatewayReturnsAFailureWithMessage(String errorMessage) {
		paymentGatewayWillReturn(new PaymentFailed(errorMessage));
	}

	private void givenPaymentIsSuccessful() {
		paymentGatewayWillReturn(new PaymentSuccessful());
	}

	private void paymentGatewayWillReturn(PaymentStatus value) {
		given(paymentGateway.makePaymentWith(paymentDetails)).willReturn(value);
	}

	private void givenStockCheckIsSuccessful() {
		stockCheckWillReturn(inStock());
	}

	private void givenStockCheckWillFailWithMessage(String errorMessage) {
		stockCheckWillReturn(notInStockWithMessage(errorMessage));
	}

	private void stockCheckWillReturn(StockCheck value) {
		given(stock.contains(basket.items())).willReturn(value);
	}

	private void assertPaymentFailedWithMessage(PaymentStatus paymentStatus, String errorMessage) {
	    assertThat(paymentStatus.fail(), is(true));
		assertThat(paymentStatus.messages().get(0), is(errorMessage));
	}

	private StockCheck notInStockWithMessage(String... messages) {
		return new StockCheck(OUT_OF_STOCK, messages);
	}

	private StockCheck inStock() {
		return new StockCheck(IN_STOCK);
	}


}
