
package com.code.utils;

import com.alibaba.fastjson.JSON;
import com.code.entity.Certificate_info;
import org.apache.hadoop.metrics2.util.Contracts;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBlockchainObject implements Serializable {


    public long id;
    public String name;
    public String times;
    public String data;
    public Certificate_info result;

    public MyBlockchainObject() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Certificate_info getResult() {
        return result;
    }

    public void setResult(Certificate_info result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "FabricBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", times='" + times + '\'' +
                ", data='" + data + '\'' +
                ", result=" + result +
                '}';
    }

    public static String getDateUtils() {
        // 创建一个 Date 对象，获取当前时间
        Date currentDate = new Date();
        // 创建 SimpleDateFormat 对象，指定日期时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 使用 SimpleDateFormat 格式化 Date 对象
        String formattedDate = sdf.format(currentDate);
        // 输出格式化后的时间
        return formattedDate;
    }


    public static void main(String[] args) {
        MyBlockchainObject obj = new MyBlockchainObject();
        obj.setId(1);
        obj.setName("张三");
        obj.setTimes("2025");
        obj.setData("AAAA");
        String ss = JSON.toJSONString(obj);
        System.out.println("ss = " + ss);
    }
}
