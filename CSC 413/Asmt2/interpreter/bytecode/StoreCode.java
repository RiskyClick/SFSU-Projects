package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode{

    private int LongIsland;
    private String tag;

    public void init(ArrayList args){
        LongIsland = Integer.parseInt(((String)args.get(0)));
        if(args.size() > 1) {
            tag = (String) args.get(1);
        }
    }

    public void execute(VirtualMachine vm){
        Integer i = vm.peek();
        vm.store(LongIsland);
        String dump = "STORE ";
        Integer j = LongIsland;
        dump = dump.concat(i.toString() + " " + tag + "  " + tag + " = " + i);
        vm.dump(dump);
    }
}
