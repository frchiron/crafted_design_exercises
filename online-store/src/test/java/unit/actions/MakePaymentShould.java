package unit.actions;

import actions.MakePayment;
import model.shopping.Basket;
import model.shopping.PaymentDetails;
import model.shopping.PaymentStatus;
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
import static org.hamcrest.core.Is.isA;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class MakePaymentShould {

	public static final String SOCRA_BOOK_NOT_IN_STOCK = "SoCra Book not in stock";
	private MakePayment makePayment;
	private Basket basket;
	private PaymentDetails paymentDetails;

	@Mock private Stock stock;

	@Before
	public void initialise() {
		makePayment = new MakePayment(stock);
		basket = new Basket();
		paymentDetails = new PaymentDetails();
		given(stock.contains(basket.items())).willReturn(inStock());
	}

	@Test public void
	return_payment_status_after_submiting_payment_details() {
		assertThat(makePayment.execute(basket, paymentDetails), isA(PaymentStatus.class));
	}

	@Test public void
	inform_when_items_are_out_of_stock() {
		given(stock.contains(basket.items())).willReturn(
				notInStockWithMessage(SOCRA_BOOK_NOT_IN_STOCK));

		PaymentStatus paymentStatus = makePayment.execute(basket, paymentDetails);

	    assertThat(paymentStatus.fail(), is(true));
		assertThat(paymentStatus.messages().get(0), is(SOCRA_BOOK_NOT_IN_STOCK));
	}

	private StockCheck notInStockWithMessage(String... messages) {
		return new StockCheck(OUT_OF_STOCK, messages);
	}

	private StockCheck inStock() {
		return new StockCheck(IN_STOCK);
	}


}
