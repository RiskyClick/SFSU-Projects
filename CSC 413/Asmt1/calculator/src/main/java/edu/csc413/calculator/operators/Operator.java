package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;
import java.util.HashMap;

public abstract class Operator {
    private static HashMap<String, Operator> ops = new HashMap<>();
    static {
        ops = new HashMap<>();
        ops.put("+", new AddOperator());
        ops.put("-", new SubtractOperator());
        ops.put("*", new MultiplyOperator());
        ops.put("/", new DivideOperator());
        ops.put("^", new PowerOperator());
        ops.put("(", new OpenParOperator());
        ops.put(")", new CloseParOperator());
    }

    public abstract int priority();

    public abstract Operand execute(Operand op1, Operand op2 );

    public static Operator functionCall(String token){
        return ops.get(token);
    }

    public static boolean check( String token ) {
        return  token.equals("+") ||
                token.equals("-") ||
                token.equals("/") ||
                token.equals("*") ||
                token.equals("^") ||
                token.equals("(") ||
                token.equals(")");
    }

    public static Operator getOperator(String token){
        return ops.get(token);
    }
}
