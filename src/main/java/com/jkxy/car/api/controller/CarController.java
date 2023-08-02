package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.Buyer;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.pojo.FuzzyQueryAndRange;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }

    /**
     * 通过车名购买
     *
     * @param buyer
     * @return
     */
    @PostMapping("buyCar")
    public JSONResult insertCar(Buyer buyer) throws Exception {
        try {
            carService.buyCar(buyer);
            return JSONResult.ok();
        } catch (Exception e) {
            return JSONResult.errorMsg(e.getMessage());
        }
    }

    /**
     * 模糊查询并根据范围显示
     *
     * @param fuzzyQueryAndRange
     * @return
     */
    @PostMapping("fuzzyQueryAndRange")
    public JSONResult fuzzyQueryAndRange(FuzzyQueryAndRange fuzzyQueryAndRange) throws Exception {
        try {
            List<Car> cars = carService.fuzzyQueryAndRange(fuzzyQueryAndRange);
            return JSONResult.ok(cars);
        } catch (Exception e) {
            return JSONResult.errorMsg(e.getMessage());
        }
    }
}
