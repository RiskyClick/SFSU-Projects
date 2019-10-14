package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode{

    String dump;
    @Override
    public void init(ArrayList args) {
        dump = (String)(args.get(0));
    }

    @Override
    public void execute(VirtualMachine vm) {
        boolean commenceTheDump = dump.equals("ON") ? true : false;
        vm.setDumping(commenceTheDump);
    }
}
