package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode {

    private int size;

    public void init(ArrayList args){
        size = Integer.parseInt(((String)args.get(0)));
    }

    public void execute(VirtualMachine vm){
        if(size < (vm.size() - vm.topFrame())){
            for(int i = 0; i < size; i++){
                vm.pop();
            }
        } else {
            for(int i = 0; i < (vm.size() - vm.topFrame()); i++){
                vm.pop();
            }
        }
        String dump = "POP ";
        dump = dump.concat(((Integer)size).toString());
        vm.dump(dump);
    }
}
