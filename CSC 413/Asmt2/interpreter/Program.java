package interpreter;

import interpreter.bytecode.AddressResolution;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.LabelCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Program {

    private ArrayList<ByteCode> program;
    private HashMap<String,Integer> addresses = new java.util.HashMap<>();

    public Program() {
        program = new ArrayList<>();
    }

    public ByteCode getCode(int pc) {
        return this.program.get(pc);
    }

    public void resolveAddress() {
        ByteCode byteCode;
        Integer i;
        Iterator<ByteCode> iterator = program.iterator();
        while (iterator.hasNext()) {
            byteCode = iterator.next();
            if (byteCode instanceof AddressResolution) {
                AddressResolution ARinterface = (AddressResolution) byteCode;
                String label = ARinterface.getArgs();
                i = addresses.get(label);
                ARinterface.setAddress(i.intValue());
            }
        }
    }

    public void set(ByteCode byteCode){
        program.add(byteCode);
        if (byteCode instanceof LabelCode) {
            String label = ((LabelCode) byteCode).getArgs();
            Integer i = new Integer((program.size()) - 1);
            addresses.put(label, i);
        }
    }
}


