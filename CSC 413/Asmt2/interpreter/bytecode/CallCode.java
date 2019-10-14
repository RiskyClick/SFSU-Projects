package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends ByteCode implements AddressResolution {

    private String tagLine;
    private int address;

    public void init(ArrayList args) {
        tagLine = (String) args.get(0);
    }

    public void execute(VirtualMachine vm) {
        vm.saveReturnAddrs();
        vm.setPcToVal(address);
        String dump = "CALL ";
        dump = dump.concat(tagLine);
        ArrayList<Integer> args = vm.getArguments();
        if (args.isEmpty()) {
            dump = dump.concat(" f( )");
        }
        else {
            for (int i = 0; i < args.size(); i++) {
                if (i == 0) {
                    Integer j = args.get(0);
                    dump = dump.concat("    f(" + j.toString());
                }
                else {
                    Integer j = args.get(i);
                    dump = dump.concat(", "+ i);
                }
            }
            dump = dump.concat(")");
        }
        vm.dump(dump);
    }

    public String getArgs() {
        return tagLine;
    }

    public void setAddress(int i) {
        address = i;
    }
}
