package mypackage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CoffeeMachineRunner {



    public static String runCoffeeMachine(String input) {
        byte[] bytes = input.getBytes();
        System.setIn(new ByteArrayInputStream(bytes));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));


        CoffeeMachine.main(new String[]{});
        return output.toString();
    }
}
