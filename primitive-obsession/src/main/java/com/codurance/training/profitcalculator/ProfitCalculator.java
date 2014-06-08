package com.codurance.training.profitcalculator;

import static com.codurance.training.profitcalculator.Money.money;

public final class ProfitCalculator {

    private final Currency localCurrency;
	private ExchangeRates exchangeRates;
	private Money localAmount;
    private Money foreignAmount;

    public ProfitCalculator(Currency localCurrency, ExchangeRates exchangeRates) {
        this.localCurrency = localCurrency;
	    this.exchangeRates = exchangeRates;
	    this.localAmount = money(0, localCurrency);
	    this.foreignAmount = money(0, localCurrency);
    }

    public void add(Item item) {
        Money realAmount = item.amount();
        Double exchangeRate = exchangeRates.rateFor(realAmount.currency()) / exchangeRates.rateFor(localCurrency);

	    realAmount = realAmount.dividedBy(exchangeRate);
        if (localCurrency.equals(realAmount.currency())) {
            this.localAmount = localAmount.sum(realAmount);
        } else {
            this.foreignAmount = foreignAmount.sum(realAmount.sameAmountIn(localCurrency));
        }
    }

    public Money calculateProfit() {
        return localAmount.subtract(calculateTax()).sum(foreignAmount);
    }

    public Money calculateTax() {
        if (localAmount.lessThan(money(0, localCurrency))) {
            return money(0, localCurrency);
        }

        return localAmount.timesBy(0.2);
    }
}
