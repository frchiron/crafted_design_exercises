package com.codurance.training.profitcalculator;

public class Money {

	private Currency currency;
	private int amount;

	public Money(int amount, Currency currency) {
		this.currency = currency;
		this.amount = amount;
	}

	public static Money money(int amount, Currency currency) {
		return new Money(amount, currency);
	}

	public Currency currency() {
		return currency;
	}

	public Money dividedBy(double divisor) {
		return money((int) Math.abs(amount / divisor), currency);
	}

	public Money negative() {
		return money(-amount, currency);
	}

	public Money sum(Money anotherAmount) {
		validateCurrencyForOperation(anotherAmount);
		return money(amount + anotherAmount.amount, currency);
	}

	public Money subtract(Money anotherAmount) {
		validateCurrencyForOperation(anotherAmount);
		return money(amount - anotherAmount.amount, currency);
	}

	public boolean lessThan(Money anotherAmount) {
		validateCurrencyForOperation(anotherAmount);
		return amount < anotherAmount.amount;
	}

	public Money timesBy(double multiplier) {
		return money((int) Math.abs(amount * multiplier), currency);
	}

	public Money sameAmountIn(Currency anotherCurrency) {
		return money(amount, anotherCurrency);
	}

	private void validateCurrencyForOperation(Money anotherAmount) {
		if (!currency.equals(anotherAmount.currency)) {
			throw new IllegalArgumentException("Sum cannot be performed for different currencies");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Money money = (Money) o;

		if (amount != money.amount) return false;
		if (currency != null ? !currency.equals(money.currency) : money.currency != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = currency != null ? currency.hashCode() : 0;
		result = 31 * result + amount;
		return result;
	}

	@Override
	public String toString() {
		return "Money{" +
				"currency=" + currency +
				", amount=" + amount +
				'}';
	}

}
