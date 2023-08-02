package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.Buyer;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.pojo.FuzzyQueryAndRange;
import com.jkxy.car.api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(Car car) {
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Override
    public void buyCar(Buyer buyer) throws Exception {
        //要购买的车辆名称
        String carName = buyer.getCarName();
        //要购买的数量
        Integer num = buyer.getNum();

        //对入参进行非空校验
        if (StringUtils.isEmpty(carName)){
            throw new Exception("输入的车辆名称为空！");
        }
        if (num == null){
            throw new Exception("输入的购买数量为空！");
        }

        List<Car> cars = carDao.findByCarName(carName);
        if (cars.size() == 0){
            throw new Exception("输入的车型不存在");
        }
        Car car = cars.get(0);
        if (car.getInventory().compareTo(num) < 0 ){
            throw new Exception("购买量大于库存,该车型目前库存" + car.getInventory() + "量");
        }
        carDao.buyCar(buyer);
    }

    @Override
    public List<Car> fuzzyQueryAndRange(FuzzyQueryAndRange fuzzyQueryAndRange) throws Exception {
        //模糊查询的内容
        String content = fuzzyQueryAndRange.getContent();
        //模糊查询的范围
        Integer startNum = fuzzyQueryAndRange.getStart();
        Integer endNum = fuzzyQueryAndRange.getEnd();

        //对入参进行非空校验
        if (content == null || content.isEmpty()){
            throw new Exception("输入的查询内容为空！");
        }
        if (StringUtils.isEmpty(endNum)){
            throw new Exception("输入的开始或结束条目为空！");
        }
        if (startNum.compareTo(endNum) > 0){
            throw new Exception("开始条目不得大于结束条目");
        }

        // 从 strat +1 条 开始查询 num 个数据
        int start = startNum - 1;
        int num = endNum - start;
        return carDao.fuzzyQueryAndRange(content, start, num);
    }
}
