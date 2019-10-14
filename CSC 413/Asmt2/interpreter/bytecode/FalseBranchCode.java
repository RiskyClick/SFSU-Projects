package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode implements AddressResolution {

    private String tagLine;
    private int address;

    public void init(ArrayList args) {
        tagLine = (String) args.get(0);
    }

    public void execute(VirtualMachine vm) {
        int branch = vm.pop();
        if (branch == 0) {
            vm.setPcToVal(address);
        }
        String dump = "FALSEBRANCH ";
        dump = dump.concat(tagLine);
        vm.dump(dump);
    }

    public String getArgs() {
        return tagLine;
    }

    public void setAddress(int i) {
        address = i;
    }
}
