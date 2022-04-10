package com.nhnacademy.pakingcontrolprogram;

import com.nhnacademy.pakingcontrolprogram.car.Car;
import com.nhnacademy.pakingcontrolprogram.money.Money;
import com.nhnacademy.pakingcontrolprogram.parking.ParkingFlow;
import com.nhnacademy.pakingcontrolprogram.parking.ParkingLot;
import com.nhnacademy.pakingcontrolprogram.user.User;
import com.nhnacademy.pakingcontrolprogram.user.Userid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParkingSystem {
    private ParkingLot parkingLot = new ParkingLot();
    private List users;

    public static void main(String[] args) {

        ParkingSystem parkingSystem = new ParkingSystem();
        Set<Thread> parkingFlowSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            String code = "A-" + i;
            String number = "232보" + (1000 + i);
            Car car = new Car(number, new User(new Userid("동욱"), new Money(30000)));
            parkingFlowSet.add(new Thread(new ParkingFlow(parkingSystem.parkingLot, car, code)));
        }
        parkingFlowSet.forEach(thread -> {
            try {
                thread.start();
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
