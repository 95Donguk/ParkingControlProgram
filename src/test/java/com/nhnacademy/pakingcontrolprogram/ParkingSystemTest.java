package com.nhnacademy.pakingcontrolprogram;

import com.nhnacademy.pakingcontrolprogram.exception.AlreadyParkedSpaceException;
import com.nhnacademy.pakingcontrolprogram.car.Car;
import com.nhnacademy.pakingcontrolprogram.exception.DontHaveMoneyException;
import com.nhnacademy.pakingcontrolprogram.money.Money;
import com.nhnacademy.pakingcontrolprogram.parking.Entrance;
import com.nhnacademy.pakingcontrolprogram.parking.Exit;
import com.nhnacademy.pakingcontrolprogram.parking.ParkingLot;
import com.nhnacademy.pakingcontrolprogram.parking.ParkingSpace;
import com.nhnacademy.pakingcontrolprogram.user.User;
import com.nhnacademy.pakingcontrolprogram.user.Userid;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ParkingSystemTest {
    private final String carNumber = "212보 1894";
    private final String code = "A-1";
    private Car car;
    private Entrance entrance;
    private Exit exit;
    private ParkingSpace parkingSpace;
    private ParkingLot parkingLot;
    private List<ParkingSpace> spaces;
    private User user;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
        entrance = new Entrance();
        exit = new Exit();
        spaces = new ArrayList<>();
        car = new Car(carNumber);
    }

    @DisplayName("주차장에 차가 들어온다. (차가 들어오면 번호판을 인식(scan)합니다.)")
    @Test
    void scanCarNumberTest() {
        Car enterCar = entrance.scan(carNumber);
        assertThat(enterCar.getCarNumber()).isEqualTo(car.getCarNumber());
    }

    @DisplayName("A-1에 주차한다.(특정 주차구역(ParkingSpaces)에 주차합니다.")
    @Test
    void parkingTest() {
        ParkingSpace parkingSpace = new ParkingSpace(code, car);
        spaces.add(parkingSpace);

        assertThat(parkingLot.enter(code, car))
                .usingRecursiveComparison()
                .isEqualTo(spaces);
    }
    @DisplayName("이미 주차된 구역 A-1에 다른 차가 주차를 시도한다.")
    @Test
    void parkingTest_AlreadyParkedSpaceException() {
        String otherCarNumber = "33허 9201";
        Car otherCar = new Car(otherCarNumber);

        parkingLot.enter(code, car);

        assertThatThrownBy(() -> parkingLot.enter(code, otherCar))
                .isInstanceOf(AlreadyParkedSpaceException.class)
                .hasMessageContaining("이미 주차된");

    }

    @DisplayName("주차장에서 차가 나간다.")
    @Test
    void leaveTest() {
        String otherCode = "B-3";
        String otherCarNumber = "33허 9201";
        Car otherCar = new Car(otherCarNumber);
        ParkingSpace parkingSpace = new ParkingSpace(otherCode, otherCar);
        spaces.add(parkingSpace);
        assertThat(parkingLot.enter(otherCode, otherCar))
                .usingRecursiveComparison()
                .isEqualTo(spaces);
        car = new Car(code, new User(new Userid("동욱"), new Money(50000)));
        spaces.add(new ParkingSpace(code, car));
        assertThat(parkingLot.enter(code, car))
                .usingRecursiveComparison()
                .isEqualTo(spaces);
        car.setOutTime(car.getInTime().plusMinutes(46L));
        spaces.remove(new ParkingSpace(code, car));
        assertThat(parkingLot.exit(code, car))
                .usingRecursiveComparison()
                .isEqualTo(spaces);
    }

    @DisplayName("주차장에서 차가 나갈 때 돈이 없으면 나갈 수 없습니다.")
    @Test
    void leaveTest_dontHaveMoneyException () {
        assertThatThrownBy(() -> exit.pay(car))
                .isInstanceOf(DontHaveMoneyException.class)
                .hasMessageContaining("dont have enough money");
    }

    @DisplayName("30분 1초 주차한 경우 요금은 1,500원 입니다.")
    @Test
    void payTest_testCase1() {
        LocalDateTime dt = localDateTimeInit();
        car.setOutTime(dt.plusSeconds(1801L));
        assertThat(exit.pay(car)).isEqualTo(1500);
    }

    @DisplayName("50분 주차한 경우 요금은 2,000원 입니다.")
    @Test
    void payTest_testCase2() {
        LocalDateTime dt = localDateTimeInit();
        car.setOutTime(dt.plusMinutes(50L));
        assertThat(exit.pay(car)).isEqualTo(2000);
    }

    @DisplayName("61분 주차한경우 요금은 3,000원입니다.")
    @Test
    void payTest_testcase3() {
        LocalDateTime dt = localDateTimeInit();
        car.setOutTime(dt.plusMinutes(61L));
        assertThat(exit.pay(car)).isEqualTo(3000);
    }

    @DisplayName("6시간 주차한 경우 요금은 10,000원입니다.")
    @Test
    void payTest_testcase4() {
        LocalDateTime dt = localDateTimeInit();
        car.setOutTime(dt.plusHours(6L));
        assertThat(exit.pay(car)).isEqualTo(10000);
    }

    @DisplayName("이틀 연속 주차한 경우")
    @Test
    void payTest_testcase5() {
        LocalDateTime dt = localDateTimeInit();
        car.setOutTime(dt.plusDays(2L));
        assertThat(exit.pay(car)).isEqualTo(20000);
    }

    private LocalDateTime localDateTimeInit() {
        car = new Car(code, new User(new Userid("동욱"), new Money(50000)));
        LocalDateTime dt = LocalDateTime.now();
        car.setInTime(dt);
        return dt;
    }
    
    @AfterEach
    void tearDown() {
        spaces.clear();
    }
}