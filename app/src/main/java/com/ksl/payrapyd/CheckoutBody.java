package com.ksl.payrapyd;

public class CheckoutBody {
    private final int amount;
    private final String country;
    private final String currency;
//    private final String complete_checkout_url;


    public CheckoutBody(int amount, String country, String currency) {
        this.amount = amount;
        this.country = country;
        this.currency = currency;
//        this.complete_checkout_url = complete_checkout_url;
    }
}
