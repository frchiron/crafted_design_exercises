package com.codurance.training.taxcalculator;

public final class TaxCalculator {
    private int amount = 0;

    public void add(int amount, String currency) {
        int realAmount;
        switch (currency) {
            case "GBP":
                realAmount = amount;
                break;
            case "USD":
                realAmount = (int) (amount / 1.6);
                break;
            default:
                throw new IllegalArgumentException("Invalid currency.");
        }
        this.amount += realAmount;
    }

    public int calculateTaxIn(String currency) {
        if (amount > 1000) {
            return (int) (amount * 0.25);
        } else {
            return (int) (amount * 0.2);
        }
    }
}
