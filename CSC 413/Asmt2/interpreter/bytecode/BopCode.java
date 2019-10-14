package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class BopCode extends ByteCode{

    private String operator;

    public void init(ArrayList args){
        operator = (String)args.get(0);
    }

    public void execute(VirtualMachine vm){
        int top = vm.pop();
        int bottem = vm.pop();
        Integer val= new Integer(0);
        switch(operator) {
            case "+":
                val = bottem + top;
                break;

            case "-":
                val = bottem - top;
                break;

            case "/":
                val = bottem / top;
                break;

            case "*":
                val = bottem * top;
                break;

            case "==":
                if (bottem == top) {
                    val = 1;
                } else {
                    val = 0;
                }
                break;

            case "!=":
                if (bottem == top) {
                    val = 0;
                } else {
                    val = 1;
                }
                break;

            case "<":
                if (bottem < top) {
                    val = 1;
                } else {
                    val = 0;
                }
                break;

            case ">":
                if (bottem > top) {
                    val = 1;
                } else {
                    val = 0;
                }
                break;

            case "<=":
                if (bottem <= top) {
                    val = 1;
                } else {
                    val = 0;
                }
                break;

            case ">=":
                if(bottem >= top) {
                    val = 1;
                } else {
                    val = 0;
                }
                break;

            case "|":
                if ((bottem != 0) || (top != 0)) {
                    val = 1;
                } else {
                    val = 0;
                }
                break;

            case "&":
                if((bottem != 0) && (top != 0)) {
                    val = 1;
                } else {
                    val = 0;
                }
                break;

                default:
                    System.out.println("******Operator not found*******");
        }
        vm.push(val);
        String dump = "BOP ";
        dump = dump.concat(operator);
        vm.dump(dump);
    }
}
