package com.model.cbehavioral.Command;

/**
 * @author 厚积薄发
 * @create 2023-05-29-17:40
 */
public class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.sell();
    }
}