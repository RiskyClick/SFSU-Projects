package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode {

    protected String tagLine;

    public void init(ArrayList args) {
        if (args.isEmpty()) {
            tagLine = "NULL";
        } else{
            tagLine = (String) args.get(0);
        }
    }

    public void execute(VirtualMachine vm) {
        vm.popFrame();
        Integer count = vm.popReturnAddrs();
        vm.setPcToVal(count);
        String dump= "RETURN ";
        if((tagLine.equals("NULL")) ){
            vm.dump(dump);
        }
        else {
            dump = dump.concat(tagLine);
            vm.dump(dump);
        }
    }

    public String getArgs() {
        return tagLine;
    }


}
