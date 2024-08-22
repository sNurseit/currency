package kz.diploma.exchange_rate.util;

public enum Currency {
    EUR("EUR"),
    USD ("USD"),
    RUB ("RUB");

    public final String label;

    Currency(String label) {
        this.label = label;
    }

}
