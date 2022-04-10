package com.nhnacademy.pakingcontrolprogram.parking;

import com.nhnacademy.pakingcontrolprogram.car.Car;

public class ParkingFlow implements Runnable{
    private ParkingLot parkingLot;
    private Car car;
    private String code;

    public ParkingFlow(ParkingLot parkingLot, Car car, String code) {
        this.parkingLot = parkingLot;
        this.car = car;
        this.code = code;
    }

    @Override
    public void run() {
        parkingLot.enter(code, car);
        System.out.println("차 번호는 " + car.getCarNumber());
        System.out.println("주차 구역은 " + code);
        System.out.println(car.getUser().getId().getUserid() + "님의 현재 가지고 있는 금액은 " + car.getUser().getAmount().getAmount());
        parkingLot.exit(code, car);
        System.out.println(car.getUser().getAmount().getAmount());
    }
}
