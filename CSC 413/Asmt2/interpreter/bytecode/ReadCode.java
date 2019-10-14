package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadCode extends ByteCode{

    public void init(ArrayList args){

    }

    public void execute(VirtualMachine vm){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter A Integer");
        int i = input.nextInt();
        Integer  j = new Integer(i);
        vm.push(j);
        String dump = "READ ";
        vm.dump(dump);
    }
}
