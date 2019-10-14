package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class AddOperator extends Operator{

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public Operand execute(Operand first, Operand second) {
        Operand val = new Operand(first.getValue() + second.getValue());
        return val;
    }

}
