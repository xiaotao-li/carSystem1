package com.jkxy.car.api.pojo;

import lombok.Data;

@Data
public class FuzzyQueryAndRange {
    //模糊查询的内容
    private String content;
    //显示数据的范围
    private Integer start;
    private Integer end;
}
