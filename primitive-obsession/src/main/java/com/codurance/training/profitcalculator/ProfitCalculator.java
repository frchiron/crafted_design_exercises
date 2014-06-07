package com.codurance.training.profitcalculator;

public final class ProfitCalculator {

    private final Currency localCurrency;
	private ExchangeRates exchangeRates;
	private int localAmount = 0;
    private int foreignAmount = 0;

    public ProfitCalculator(Currency localCurrency, ExchangeRates exchangeRates) {
        this.localCurrency = localCurrency;
	    this.exchangeRates = exchangeRates;
	    Double exchangeRate = exchangeRates.rateFor(localCurrency);
        if (exchangeRate == null) {
            throw new IllegalArgumentException("Invalid currency.");
        }
    }

    public void add(int amount, Currency currency, boolean incoming) {
        int realAmount = amount;
        Double exchangeRate = exchangeRates.rateFor(currency) / exchangeRates.rateFor(localCurrency);
        if (exchangeRate != null) {
            realAmount /= exchangeRate;
        }
        if (!incoming) {
            realAmount = -realAmount;
        }
        if (localCurrency.equals(currency)) {
            this.localAmount += realAmount;
        } else {
            this.foreignAmount += realAmount;
        }
    }

    public int calculateProfit() {
        return localAmount - calculateTax() + foreignAmount;
    }

    public int calculateTax() {
        if (localAmount < 0) {
            return 0;
        }

        return (int) (localAmount * 0.2);
    }
}
