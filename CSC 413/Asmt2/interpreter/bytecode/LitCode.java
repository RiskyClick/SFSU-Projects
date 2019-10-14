package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode{

    private int longIslandIceTea;
    private String tagLine;

    public void init(ArrayList args){
        tagLine = "";
        longIslandIceTea = Integer.parseInt(((String)args.get(0)));
        if(args.size() > 1) {
            tagLine = (String) args.get(1);
        }
    }

    public void execute(VirtualMachine vm){
        vm.push(longIslandIceTea);
        String dump = "LIT ";
        dump = dump.concat(((Integer)longIslandIceTea).toString());
        if(!(tagLine.isEmpty())){
            dump = dump.concat(" " + tagLine + " int" + tagLine);
        }
        vm.dump(dump);
    }
}
