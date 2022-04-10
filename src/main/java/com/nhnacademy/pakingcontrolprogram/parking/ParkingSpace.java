package com.nhnacademy.pakingcontrolprogram.parking;

import com.nhnacademy.pakingcontrolprogram.exception.car.Car;

public class ParkingSpace {
    private String code;
    private Car car;
    public ParkingSpace(String code, Car car) {
        this.code = code;
        this.car = car;
    }

    public Car getCar() {
        return this.car;
    }

    public String getCode() {
        return this.code;
    }
}
