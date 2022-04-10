package com.nhnacademy.pakingcontrolprogram.exception.car;

import com.nhnacademy.pakingcontrolprogram.money.Money;
import com.nhnacademy.pakingcontrolprogram.user.User;
import com.nhnacademy.pakingcontrolprogram.user.Userid;

import java.time.LocalDateTime;
import java.util.Objects;

public class Car {
    private String number;
    private User user;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    public Car(String number) {
        this(number, new User(new Userid("unknown"), new Money(0)));
    }

    public Car(String number, User user) {
        this.number = number;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return number.equals(car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public String getCarNumber() {
        return this.number;
    }
    public User getUser() {
        return this.user;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }

    public void setOutTime(LocalDateTime outTime) {
        this.outTime = outTime;
    }

    public LocalDateTime getInTime() {
        return this.inTime;
    }

    public LocalDateTime getOutTime() {
        return this.outTime;
    }

}
