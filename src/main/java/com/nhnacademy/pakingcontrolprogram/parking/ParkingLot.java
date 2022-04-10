package com.nhnacademy.pakingcontrolprogram.parking;

import com.nhnacademy.pakingcontrolprogram.exception.AlreadyParkedSpaceException;
import com.nhnacademy.pakingcontrolprogram.car.Car;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final List<ParkingSpace> spaces = new ArrayList<>();
    private final Entrance entrance = new Entrance();
    private final Exit exit = new Exit();
    private ParkingSpace parkingSpace;

    public synchronized List<ParkingSpace> enter(String code, Car car) {
        for (ParkingSpace space : spaces) {
            if(space.getCode().equals(code)){
                throw new AlreadyParkedSpaceException("이미 주차된 공간입니다.");
            }
        }
        car.setInTime(entrance.scan(car.getCarNumber()).getInTime());
        parkingSpace = new ParkingSpace(code, car);
        spaces.add(parkingSpace);
        return spaces;
    }

    public synchronized List<ParkingSpace> exit(String code, Car car) {
        LocalDateTime dateTime = LocalDateTime.now();
        car.setOutTime(dateTime);
        System.out.println(car.getUser().getId().getUserid() + " 의 주차 금액은 " + exit.pay(car) + "입니다." );
        parkingSpace = new ParkingSpace(code, car);
        spaces.remove(parkingSpace);
        return spaces;
    }
}
