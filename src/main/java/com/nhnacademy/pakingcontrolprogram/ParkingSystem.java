package com.nhnacademy.pakingcontrolprogram;

import com.nhnacademy.pakingcontrolprogram.exception.car.Car;
import com.nhnacademy.pakingcontrolprogram.money.Money;
import com.nhnacademy.pakingcontrolprogram.parking.ParkingLot;
import com.nhnacademy.pakingcontrolprogram.user.User;
import com.nhnacademy.pakingcontrolprogram.user.Userid;

import java.util.List;

public class ParkingSystem {
    private ParkingLot parkingLot;
    private List users;

    public static void main(String[] args) {
        ParkingSystem parkingSystem = new ParkingSystem();
        String code = "A-5";
        Car car = new Car("345 가 1234", new User(new Userid("동욱"), new Money(50000)));
        parkingSystem.parkingLot = new ParkingLot();
        parkingSystem.parkingLot.enter(code, car);
        parkingSystem.parkingLot.exit(code, car);
    }
}
