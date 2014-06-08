package com.codurance.training.profitcalculator;

import static com.codurance.training.profitcalculator.Money.money;

public final class ProfitCalculator {

    private final Currency localCurrency;
	private ExchangeRates exchangeRates;
    private Money foreignAmount;
	private Items items = new Items();

	public ProfitCalculator(Currency localCurrency, ExchangeRates exchangeRates) {
        this.localCurrency = localCurrency;
	    this.exchangeRates = exchangeRates;
	    this.foreignAmount = money(0, localCurrency);
    }

    public void add(Item item) {
	    items.add(item);
        Money realAmount = item.amount();
        Double exchangeRate = exchangeRates.rateFor(realAmount.currency()) / exchangeRates.rateFor(localCurrency);

	    realAmount = realAmount.dividedBy(exchangeRate);
        if (!localCurrency.equals(realAmount.currency())) {
            this.foreignAmount = foreignAmount.sum(realAmount.sameAmountIn(localCurrency));
        }
    }

    public Money calculateProfit() {
	    Money localAmount = items.amountIn(localCurrency);
        return localAmount.subtract(calculateTax()).sum(foreignAmount);
    }

    public Money calculateTax() {
	    Money totalAmount = items.amountIn(localCurrency);
        if (totalAmount.lessThan(money(0, localCurrency))) {
            return money(0, localCurrency);
        }

        return totalAmount.timesBy(0.2);
    }
}
