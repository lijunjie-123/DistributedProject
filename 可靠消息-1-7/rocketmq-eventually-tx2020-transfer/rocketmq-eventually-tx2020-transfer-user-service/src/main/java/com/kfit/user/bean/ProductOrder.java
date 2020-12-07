package com.kfit.user.bean;

import java.io.Serializable;
import java.util.*;

/**
 * Created by wangxy on 20/11/29.
 */
public class ProductOrder implements Serializable {

    private long orderId;


    private String type;


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ProductOrder(){}

    public ProductOrder(long orderId,String type){
        this.orderId = orderId;
        this.type = type;
    }

    public static List<ProductOrder> getOrderList(){
        List<ProductOrder> list = new ArrayList<ProductOrder>();
        ProductOrder order = new ProductOrder();
        list.add(new ProductOrder(111L,"创建订单"));
        list.add(new ProductOrder(222L,"创建订单"));
        list.add(new ProductOrder(111L,"支付订单"));
        list.add(new ProductOrder(333L,"创建订单"));
        list.add(new ProductOrder(333L,"支付订单"));
        list.add(new ProductOrder(222L,"支付订单"));
        list.add(new ProductOrder(111L,"完成订单"));
        list.add(new ProductOrder(333L,"完成订单"));
        list.add(new ProductOrder(222L,"完成订单"));


        return list;
    }
}
