package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class OpenParOperator extends Operator{

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        Operand val = new Operand(op1.getValue());
        return val;
    }
}
