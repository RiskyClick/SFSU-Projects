
package interpreter;

import interpreter.bytecode.ByteCode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;

    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    public Program loadCodes(){

        String key, lock, nextString;
        ByteCode bytecode;
        ArrayList<String> token = new ArrayList<>();
        Program program = new Program();

        try {
            nextString = byteSource.readLine();

            while (nextString != null) {
                StringTokenizer newToken = new StringTokenizer(nextString);
                key = newToken.nextToken();
                lock = CodeTable.getClassName(key);
                bytecode = (ByteCode) (Class.forName("interpreter.bytecode." + lock).getDeclaredConstructor().newInstance());

                while (newToken.hasMoreTokens()) {
                    token.add(newToken.nextToken());
                }

                bytecode.init(token);
                program.set(bytecode);
                token.clear();
                nextString = byteSource.readLine();
            }

        } catch (Exception e) {
            System.out.println("******ByteCodeLoader Error******");
        }
        program.resolveAddress();
        return program;
    }
}
