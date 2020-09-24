package com.spring.design.pattern.controller;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/28 16:11
 * @description:
 */
public class DesignPatter {
	public static void main(String[] args) {
		hotCar12 car12 = new hotCar12();
		car12.getInfo(new hotCar());
	}

}

interface Car {
	String open();
}

class hotCar implements Car {

	@Override
	public String open() {
		return "hotCar000000000000000";
	}
}

class hotCar1 implements Car {

	@Override
	public String open() {
		return "hotCar11111111111111";
	}
}

class hotCar12 {

	public void getInfo(Car car) {
		System.out.println(car.open());
	}
}
