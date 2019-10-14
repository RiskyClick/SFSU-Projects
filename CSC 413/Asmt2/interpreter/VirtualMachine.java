package interpreter;

import interpreter.bytecode.ByteCode;

import java.util.ArrayList;
import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack returnAddrs;
    private Program program;
    private int pc;
    private boolean isRunning;
    protected boolean dumping;

    protected VirtualMachine(Program program) {
        this.program = program;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
    }

    public void executeProgram() {
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
        isRunning = true;
        while (isRunning) {
            ByteCode bytecode = program.getCode(pc);
            bytecode.execute(this);
            runStack.dump();
            pc++;
        }
    }

    public void setPcToVal(int i){
        pc = i;
    }

    public Integer popReturnAddrs(){
        return (int) returnAddrs.pop();
    }

    public ArrayList<Integer> getArguments(){
        return runStack.getArguments();
    }

    public void dump(String argumentLable) {
        if(dumping){
            System.out.println(argumentLable);
            runStack.dump();
        }
    }

    public void setDumping(boolean dumping) {
        this.dumping = dumping;
    }

    public void saveReturnAddrs(){
        returnAddrs.push(pc);
    }

    public int peek() {
        return runStack.peek();
    }

    public int pop() {
        return runStack.pop();
    }

    public int push(int i) {
        return runStack.push(i);
    }

    public Integer push(Integer i) {
        return runStack.push(i);
    }

    public void newFrameAt(int offSet) {
        runStack.newFrameAt(offSet);
    }

    public int size(){
        return runStack.size();
    }

    public void popFrame() {
        runStack.popFrame();
    }

    public int store(int offSet) {
        return runStack.store(offSet);
    }

    public int load(int offSet) {
        return runStack.load(offSet);
    }

    public void stopVM(){
        isRunning = false;
    }

    public int topFrame(){
        return runStack.topFrame();
    }
}
