package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode{

    private String tagLine;

    public void init(ArrayList args){
        tagLine = (String)args.get(0);
    }

    public void execute(VirtualMachine vm){
        String dump= "LABEL ";
        dump = dump.concat(tagLine);
        vm.dump(dump);
    }

    public String getArgs(){
        return tagLine;
    }
}
