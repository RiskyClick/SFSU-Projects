package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.operators.Operator;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "+-*^/()";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public void run(){
        while(!operatorStack.isEmpty() && operatorStack.peek().priority() >= 1){
            Operator oldOpr = operatorStack.pop();
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            operandStack.push( oldOpr.execute( op1, op2 ));
        }
        operatorStack.pop();
    }

    public int eval(String expression) {
        int result = 0;
        String token;
        this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);

        while (this.tokenizer.hasMoreTokens()) {
            // filter out spaces
            if (!(token = this.tokenizer.nextToken()).equals(" ")) {
                // check if token is an operand
                if (Operand.check(token)) {
                    operandStack.push(new Operand(token));
                } else {
                    if (!Operator.check(token)) {
                        System.out.println("*****invalid token******");
                        throw new RuntimeException("*****invalid token******");
                    }
                    Operator newOperator = Operator.functionCall(token);
                    if(token.equals(")")){
                        run();
                    }

                    while ( operatorStack.size() != 0 &&
                            operatorStack.peek().priority() >= newOperator.priority() &&
                            newOperator.priority() > 0) {
                        Operator oldOpr = operatorStack.pop();
                        Operand op2 = operandStack.pop();
                        Operand op1 = operandStack.pop();
                        operandStack.push(oldOpr.execute(op1, op2));
                    }
                    if(newOperator.priority() >= 0) {
                        operatorStack.push(newOperator);
                    }
                }
            }
        }
        while(!operatorStack.isEmpty() && operatorStack.peek().priority() > 0){
            Operator Opr = operatorStack.pop();
            Operand o2 = operandStack.pop();
            Operand o1 = operandStack.pop();
            operandStack.push(Opr.execute(o1, o2));
        }
        result = operandStack.pop().getValue();
        return result;
    }

}

