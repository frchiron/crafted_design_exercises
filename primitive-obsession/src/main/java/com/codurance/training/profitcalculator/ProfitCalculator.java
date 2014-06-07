package com.codurance.training.profitcalculator;

import java.util.Map;
import com.google.common.collect.ImmutableMap;

public final class ProfitCalculator {
    private static final Map<String, Double> EXCHANGE_RATES = ImmutableMap.<String, Double>builder()
            .put("GBP", 1.0)
            .put("USD", 1.6)
            .put("EUR", 1.2)
            .build();

    private final String localCurrency;
    private int amount = 0;

    public ProfitCalculator(String localCurrency) {
        this.localCurrency = localCurrency;
    }

    public void add(int amount, String currency, boolean incoming) {
        int realAmount = amount;
        Double exchangeRate = EXCHANGE_RATES.get(currency);
        if (exchangeRate != null) {
            realAmount /= exchangeRate;
        }
        if (incoming) {
            this.amount += realAmount;
        } else {
            this.amount -= realAmount;
        }
    }

    public int calculateTax() {
        int taxAmount;
        if (amount > 1000) {
            taxAmount = (int) (amount * 0.25);
        } else {
            taxAmount = (int) (amount * 0.2);
        }

        Double exchangeRate = EXCHANGE_RATES.get(localCurrency);
        if (exchangeRate != null) {
            return (int) (taxAmount * exchangeRate);
        } else {
            throw new IllegalArgumentException("Invalid currency.");
        }
    }
}
