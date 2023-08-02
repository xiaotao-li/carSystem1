package com.jkxy.car.api.dao;

import com.jkxy.car.api.pojo.Buyer;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.pojo.FuzzyQueryAndRange;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CarDao {
    @Select("select * from carMessage")
    List<Car> findAll();

    @Select("select * from carMessage where id = #{id}")
    Car findById(int id);

    @Select("select * from carMessage where carName = #{carName}")
    List<Car> findByCarName(String carName);

    @Delete("delete from carMessage where id = #{id}")
    void deleteById(int id);

    @Update("update carMessage set carName=#{carName},carType=#{carType},price=#{price},carSeries=#{carSeries} where id = #{id}")
    void updateById(Car car);

    @Insert("insert into carMessage(carName,carType,price,carSeries,inventory) values(#{carName},#{carType},#{price},#{carSeries},#{inventory})")
    void insertCar(Car car);


    @Update("UPDATE carMessage SET inventory = inventory - #{num} WHERE carName = #{carName}")
    void buyCar(Buyer buyer);

    @Select("SELECT * FROM carMessage WHERE carName LIKE concat('%',#{content},'%') LIMIT #{start},#{num}")
    List<Car> fuzzyQueryAndRange(String content, int start, int num);
}
