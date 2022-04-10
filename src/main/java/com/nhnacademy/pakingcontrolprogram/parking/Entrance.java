package com.nhnacademy.pakingcontrolprogram.parking;

import com.nhnacademy.pakingcontrolprogram.car.Car;

import java.time.LocalDateTime;

public class Entrance {
    public Car scan(String carNumber) {
        LocalDateTime dt = LocalDateTime.now();
        Car enterCar = new Car(carNumber);
        enterCar.setInTime(dt);
        return enterCar;
    }
}
