import calc.interpreter.Interpreter;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("file is missing");
            System.exit(1);
        }

        String filepath = args[0];

        try {
            String sourceCode = new String(Files.readAllBytes(Paths.get(filepath)));
//            System.out.println("Source code: " + sourceCode);
            Interpreter interpreter = new Interpreter();
            interpreter.run(sourceCode);

        } catch (Exception e) {
            System.out.println("Could nto read file '" + filepath + "'.");
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
