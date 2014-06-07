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
        int taxAmount;
        if (amount > 1000) {
            taxAmount = (int) (amount * 0.25);
        } else {
            taxAmount = (int) (amount * 0.2);
        }

        switch (currency) {
            case "GBP":
                return taxAmount;
            case "USD":
                return (int) (taxAmount * 1.6);
            case "EUR":
                return (int) (taxAmount * 1.2);
            default:
                throw new IllegalArgumentException("Invalid currency.");
        }
    }
}
