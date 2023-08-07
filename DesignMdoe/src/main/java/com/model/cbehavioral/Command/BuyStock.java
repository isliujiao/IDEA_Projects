package com.model.cbehavioral.Command;

/**
 * @author 厚积薄发
 * @create 2023-05-29-17:39
 */
public class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.buy();
    }
}