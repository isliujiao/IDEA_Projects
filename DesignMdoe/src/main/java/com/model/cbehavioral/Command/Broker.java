package com.model.cbehavioral.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 厚积薄发
 * @create 2023-05-29-17:40
 */
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}