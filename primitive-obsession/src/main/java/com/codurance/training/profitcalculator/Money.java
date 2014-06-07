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

	public Money sum(Money anotherAmount) {
		if (!currency.equals(anotherAmount.currency)) {
			throw new IllegalArgumentException("Sum cannot be performed for different currencies");
		}
		return new Money(amount + anotherAmount.amount, currency);
	}
}
