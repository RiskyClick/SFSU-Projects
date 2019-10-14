package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class GotoCode extends ByteCode implements AddressResolution{

    private String tagLine;
    private int address;

    public void init(ArrayList args) {
        tagLine = (String) args.get(0);
    }

    public void execute(VirtualMachine vm) {
        vm.setPcToVal(address);
        String dump = "GOTO ";
        dump = dump.concat(tagLine);
        vm.dump(dump);
    }

    public String toString() {
        return tagLine;
    }

    public String getArgs() {
        return tagLine;
    }

    public void setAddress(int i){
        address = i;
    }
}
