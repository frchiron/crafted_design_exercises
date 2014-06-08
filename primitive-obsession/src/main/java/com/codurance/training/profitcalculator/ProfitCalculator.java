package com.codurance.training.profitcalculator;

import static com.codurance.training.profitcalculator.Money.money;

public final class ProfitCalculator {

    private final Currency localCurrency;
	private ExchangeRates exchangeRates;
	private Items items = new Items();

	public ProfitCalculator(Currency localCurrency, ExchangeRates exchangeRates) {
        this.localCurrency = localCurrency;
	    this.exchangeRates = exchangeRates;
    }

    public void add(Item item) {
	    items.add(item);
    }

    public Money calculateProfit() {
	    Money localAmount = items.amountIn(localCurrency);
	    Money foreignAmount = items.notIn(localCurrency).amountIn(localCurrency, exchangeRates);
        return localAmount.subtract(calculateTax()).sum(foreignAmount);
    }

	public Money calculateTax() {
	    Money totalAmount = items.amountIn(localCurrency);
        return totalAmount.lessThan(money(0, localCurrency))
								? money(0, localCurrency)
								: totalAmount.timesBy(0.2);
    }
}
