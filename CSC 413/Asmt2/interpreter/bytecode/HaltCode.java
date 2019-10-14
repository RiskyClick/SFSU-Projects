package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class HaltCode extends ByteCode {

    public void init(ArrayList args){

    }

    public void execute(VirtualMachine vm){
        vm.stopVM();
    }
}
