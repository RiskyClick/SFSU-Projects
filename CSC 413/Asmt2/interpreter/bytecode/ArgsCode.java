package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class ArgsCode extends ByteCode{

    private int argumentCounter;

    public void init(ArrayList args){
        argumentCounter = Integer.parseInt(((String)args.get(0)));
    }

    public void execute(VirtualMachine vm){
        vm.newFrameAt(argumentCounter);
        String dump = "ARGS ";
        dump = dump.concat(((Integer)argumentCounter).toString());
        vm.dump(dump);
    }
}
