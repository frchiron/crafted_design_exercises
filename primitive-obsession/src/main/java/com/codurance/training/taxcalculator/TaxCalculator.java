package com.codurance.training.taxcalculator;

public final class TaxCalculator {
    private int amount = 0;

    public void add(int amount, String currency) {
        this.amount += amount;
    }

    public int calculateTaxIn(String currency) {
        if (amount > 1000) {
            return (int) (amount * 0.25);
        } else {
            return (int) (amount * 0.2);
        }
    }
}
