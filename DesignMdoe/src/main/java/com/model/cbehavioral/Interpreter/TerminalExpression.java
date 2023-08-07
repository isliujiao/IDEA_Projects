package com.model.cbehavioral.Interpreter;

/**
 * @author 厚积薄发
 * @create 2023-05-29-17:48
 */
public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data){
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if(context.contains(data)){
            return true;
        }
        return false;
    }
}