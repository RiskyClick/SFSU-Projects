package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode{

    private int offSet;
    private String tagLine;

    public void init(ArrayList args){
        offSet= Integer.parseInt(((String)args.get(0)));
        tagLine = (String)args.get(1);
    }

    public void execute(VirtualMachine vm){
        vm.load(offSet);
        String dump = "LOAD ";
        dump = dump.concat(((Integer)offSet).toString());
        if( (tagLine.isEmpty()) == false){
            dump = dump.concat(" "+ tagLine + " int" + tagLine + " < load " + tagLine + ">");
        }
        vm.dump(dump);
    }
}
