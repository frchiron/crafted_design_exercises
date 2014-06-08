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
	    Double exchangeRate = exchangeRates.rateFor(localCurrency);
        if (exchangeRate == null) {
            throw new IllegalArgumentException("Invalid currency.");
        }
    }

    public void add(Money amount, boolean incoming) {
        Money realAmount = amount;
        Double exchangeRate = exchangeRates.rateFor(amount.currency()) / exchangeRates.rateFor(localCurrency);
        if (exchangeRate != null) {
            realAmount = realAmount.dividedBy(exchangeRate);
        }
        if (!incoming) {
            realAmount = realAmount.negative();
        }
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
