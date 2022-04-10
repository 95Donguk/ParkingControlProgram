package com.nhnacademy.pakingcontrolprogram.user;

import com.nhnacademy.pakingcontrolprogram.money.Money;

public class User {
    Userid id;
    Money amount;

    public User(Userid id, Money amount) {
        this.id = id;
        this.amount = amount;
    }

    public Money getAmount() {
        return amount;
    }

    public Userid getId() {
        return id;
    }
}
