package com.jkxy.car.api.service;

import com.jkxy.car.api.pojo.Buyer;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.pojo.FuzzyQueryAndRange;

import java.util.List;


public interface CarService {

    List<Car> findAll();

    Car findById(int id);

    List<Car> findByCarName(String carName);

    void deleteById(int id);

    void updateById(Car car);

    void insertCar(Car car);
    void buyCar(Buyer buyer) throws Exception;

    List<Car> fuzzyQueryAndRange(FuzzyQueryAndRange fuzzyQueryAndRange) throws Exception;
}
