package interpreter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class RunTimeStack {
    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        framePointer.add(0);
    }

    public ArrayList<Integer> getArguments() {
        ArrayList<Integer> arguments = new ArrayList<>();
        if ((framePointer.peek() - (runTimeStack.size()) - 1) >= 0) {
            if ((framePointer.peek() <= (runTimeStack.size()) - 1)) {
                Integer front = framePointer.peek();
                front++;
                Integer temp = (int) runTimeStack.get(front);
                arguments.add(temp);
            }
        }
        return arguments;
    }

    public void dump() {
        if ((framePointer.size()) == 1) {
            System.out.println(runTimeStack.toString());
        }
        else {
            Iterator<Integer> stackIterator = runTimeStack.iterator();
            Iterator<Integer> frameIterator = framePointer.iterator();
            System.out.print("[");
            int sizeOfStack = 0;
            int frameCoounter = 0;

            Integer endFrame = frameIterator.next();

            while (stackIterator.hasNext()) {
                Integer i = stackIterator.next();
                if((stackIterator.hasNext()) && (sizeOfStack <= endFrame)) {
                    if(sizeOfStack != endFrame) {
                        System.out.print(i.toString() + ",");
                    } else {
                        System.out.print(i.toString());
                    }
                    if (!framePointer.isEmpty() && sizeOfStack == framePointer.get(frameCoounter)) {
                        System.out.print("][");
                        if (frameIterator.hasNext()) {
                            frameCoounter++;
                            endFrame = frameIterator.next();
                        }
                    }
                }
                sizeOfStack++;
            }
            System.out.print("]");
            System.out.println();
        }
    }

    public int peek() {
        Integer i = (int) runTimeStack.get(runTimeStack.size() - 1);
        return i;
    }

    public int pop() {
        Integer i = (int) runTimeStack.get(runTimeStack.size() - 1);
        runTimeStack.remove(runTimeStack.size() - 1);
        return i;
    }

    public int push(int value) {
        Integer i = new Integer(value);
        runTimeStack.add(i);
        int j = (int) runTimeStack.get(runTimeStack.size() - 1);
        return j;
    }

    public Integer push(Integer value) {
        runTimeStack.add(value);
        int j = (int)runTimeStack.get(runTimeStack.size() - 1);
        return j;
    }

    public void newFrameAt(int offSet) {
        int size = runTimeStack.size();
        framePointer.add(size - offSet);
    }

    public void popFrame() {
        int size = runTimeStack.size() - 1;
        Integer value = (int) runTimeStack.get(runTimeStack.size() -1);
        Integer shelf = framePointer.pop();
        while(size >= shelf){
            runTimeStack.remove(size--);
        }
        runTimeStack.add(value);
    }

    public int store(int offSet) {
        Integer top = pop();
        offSet += framePointer.peek();
        top = (int) runTimeStack.set(offSet, top);
        runTimeStack.add(top);
        int i = (int) runTimeStack.get(offSet);
        return i;
    }

    public int load(int offSet) {
        offSet += framePointer.peek();
        Integer longIslandIceTea = (int)runTimeStack.get(offSet);
        runTimeStack.add(longIslandIceTea);
        int i = (int)runTimeStack.get(runTimeStack.size() -1);
        return i;
    }

    public int size(){
        return runTimeStack.size();
    }

    public int topFrame(){
        return framePointer.peek();
    }
}
