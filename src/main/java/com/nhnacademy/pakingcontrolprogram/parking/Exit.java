package com.nhnacademy.pakingcontrolprogram.parking;

import com.nhnacademy.pakingcontrolprogram.exception.car.Car;
import com.nhnacademy.pakingcontrolprogram.exception.DontHaveMoneyException;
import com.nhnacademy.pakingcontrolprogram.money.Money;
import com.nhnacademy.pakingcontrolprogram.user.User;

import java.time.temporal.ChronoUnit;

public class Exit {
    private static final long HALF_AN_HOUR_CONVERT_TO_SECONDS = 1800L;
    private static final long ONE_DAY_CONVERT_TO_SECONDS = 86400L;
    private static final long TEN_MINUTES_CONVERT_TO_SECONDS = 600L;
    private static final long MAXIMUM_DAILY_PARKING_TIME = 12600L;

    public int pay(Car car) {
        User carOwner = car.getUser();
        Money amount = carOwner.getAmount();
        int defaultPrice = 1000;
        int addPrice = 500;

        if (amount.getAmount() < defaultPrice) {
            throw new DontHaveMoneyException(carOwner.getId().getUserid() + " dont have enough money");
        }
        long parkingTime = ChronoUnit.SECONDS.between(car.getInTime(), car.getOutTime());
        if (parkingTime <= HALF_AN_HOUR_CONVERT_TO_SECONDS) {
            return defaultPrice;
        }
        if (parkingTime > MAXIMUM_DAILY_PARKING_TIME) {
            if(parkingTime < ONE_DAY_CONVERT_TO_SECONDS) {
                defaultPrice = 10000;
                return defaultPrice;
            }
            defaultPrice = (int) (10000 * parkingTime / ONE_DAY_CONVERT_TO_SECONDS);
            return defaultPrice;
        }
        for (int i = 0; i <= (parkingTime - HALF_AN_HOUR_CONVERT_TO_SECONDS - 1) / TEN_MINUTES_CONVERT_TO_SECONDS; i++) {
            defaultPrice += addPrice;
        }
        return defaultPrice;
    }
}
