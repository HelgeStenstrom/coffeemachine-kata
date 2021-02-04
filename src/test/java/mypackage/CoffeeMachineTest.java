package mypackage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoffeeMachineTest {

    private PrintStream originalOut;
    private InputStream originalIn;

    @BeforeEach
    void setup() {
        originalOut = System.out;
        originalIn = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private String originalMenu() {
        return "Menu:\n" +
                "1,Caffe Americano,$3,30,true\n" +
                "2,Caffe Latte,$2,55,true\n" +
                "3,Caffe Mocha,$3,35,true\n" +
                "4,Cappuccino,$2,90,true\n" +
                "5,Coffee,$2,75,true\n" +
                "6,Decaf Coffee,$2,75,true\n" +
                "\n" +
                "Your selection: ";
    }

    private String outOfStockMenu() {
        return "Menu:\n" +
                "1,Caffe Americano,$3,30,false\n" +
                "2,Caffe Latte,$2,55,false\n" +
                "3,Caffe Mocha,$3,35,true\n" +
                "4,Cappuccino,$2,90,false\n" +
                "5,Coffee,$2,75,true\n" +
                "6,Decaf Coffee,$2,75,true\n" +
                "\n" +
                "Your selection: ";
    }

    private String getOriginalInventory() {
        return "Inventory:\n" +
                "Cocoa,10\n" +
                "Coffee,10\n" +
                "Cream,10\n" +
                "Decaf Coffee,10\n" +
                "Espresso,10\n" +
                "Foamed Milk,10\n" +
                "Steamed Milk,10\n" +
                "Sugar,10\n" +
                "Whipped Cream,10\n" +
                "\n";
    }

    private String invAfterSale(String espressos) {
        return  "Inventory:\n" +
                "Cocoa,10\n" +
                "Coffee,10\n" +
                "Cream,10\n" +
                "Decaf Coffee,10\n" +
                "Espresso," + espressos + "\n" +
                "Foamed Milk,10\n" +
                "Steamed Milk,10\n" +
                "Sugar,10\n" +
                "Whipped Cream,10\n" +
                "\n";
    }

    private String dispensingAmericano() {
        return "Dispensing: Caffe Americano\n\n";
    }


    @Test
    void quitting() {

        var output = CoffeeMachineRunner.runCoffeeMachine("q\n");

        assertEquals(getOriginalInventory() + originalMenu(), output);

    }

    @Test
    void refillAndQuit() {

        var output = CoffeeMachineRunner.runCoffeeMachine("r\nq\n");

        String originalInventory = getOriginalInventory();
        String originalMenu = originalMenu();

        assertEquals(originalInventory + originalMenu + originalInventory + originalMenu,
                output);
    }

    @Test
    void selectAndQuit() {

        var output = CoffeeMachineRunner.runCoffeeMachine("1\nq\n");

        String originalInventory = getOriginalInventory();
        String originalMenu = originalMenu();

        assertEquals(originalInventory + originalMenu + dispensingAmericano() + invAfterSale("7") + originalMenu,
                output);
    }

    @Test
    void americanoUntilOutOfStockAndQuit() {

        var output = CoffeeMachineRunner.runCoffeeMachine("1\n1\n1\n1\nq\n");

        String originalInventory = getOriginalInventory();
        String originalMenu = originalMenu();

        assertEquals(originalInventory + originalMenu
                        + dispensingAmericano() + invAfterSale("7") + originalMenu
                        + dispensingAmericano() + invAfterSale("4") + originalMenu
                        + dispensingAmericano() + invAfterSale("1") + outOfStockMenu()
                        + failedSale() + invAfterSale("1") + outOfStockMenu()
                ,
                output);
    }

    private String failedSale() {
        return "Out of stock: Caffe Americano\n" +
                "\n";
    }

}