package mypackage;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoffeeMachineTest {

    @Test
    void quitting() {

        byte[] bytes = "q\n".getBytes();
        System.setIn(new ByteArrayInputStream(bytes));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));


        CoffeeMachine.main(new String[]{});

        assertEquals("Inventory:\n" +
                "Cocoa,10\n" +
                "Coffee,10\n" +
                "Cream,10\n" +
                "Decaf Coffee,10\n" +
                "Espresso,10\n" +
                "Foamed Milk,10\n" +
                "Steamed Milk,10\n" +
                "Sugar,10\n" +
                "Whipped Cream,10\n" +
                "\n" +
                "Menu:\n" +
                "1,Caffe Americano,$3,30,true\n" +
                "2,Caffe Latte,$2,55,true\n" +
                "3,Caffe Mocha,$3,35,true\n" +
                "4,Cappuccino,$2,90,true\n" +
                "5,Coffee,$2,75,true\n" +
                "6,Decaf Coffee,$2,75,true\n" +
                "\n" +
                "Your selection: ", output.toString());

    }

}