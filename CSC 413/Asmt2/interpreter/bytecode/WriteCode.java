package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class WriteCode extends ByteCode{

    public void init(ArrayList args){

    }

    public void execute(VirtualMachine vm){
        Integer i = vm.peek();
        String dump = "WRITE ";
        vm.dump(dump);
        System.out.println(i.toString());
    }
}
