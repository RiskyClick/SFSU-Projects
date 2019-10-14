package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

import java.security.PublicKey;

public class PowerOperator extends Operator {

    @Override
    public int priority(){
        return 3;
    }

    @Override
    public Operand execute(Operand first, Operand second){
        int pow = (int) Math.pow(first.getValue(), second.getValue());
        Operand val = new Operand(pow);
        return val;
    }
}
