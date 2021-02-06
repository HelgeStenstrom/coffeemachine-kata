package mypackage.m;

import mypackage.cm.CoffeeMachine;
import mypackage.dr.Drink;
import mypackage.v.CliView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoffeeMachineApp {

    private static final String QUIT = "q";
    private static final String RESTOCK = "r";

    public static void main(String[] args) {

        // Model
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        // View
        CliView view = new CliView();

        // Controller
        control(coffeeMachine, view);
    }

    public static void control(CoffeeMachine coffeeMachine, CliView view) {
        view.askForSelection(coffeeMachine);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";


        do try {
            input = reader.readLine().toLowerCase();

            switch (input) {
                case "":
                case QUIT:
                    continue;
                case RESTOCK:
                    coffeeMachine.restock();
                    break;
                default:
                    makeDrink(coffeeMachine, view, Integer.parseInt(input));
                    break;
            }
            view.askForSelection(coffeeMachine);
        } catch (IOException | IllegalArgumentException e) {
            view.showInvalidSelection(input); //illegal input
        }
        while (!input.equals(QUIT));
    }

    private static void makeDrink(CoffeeMachine coffeeMachine, CliView view, int drinkId) {
        Drink drink = coffeeMachine.drinkById(drinkId);
        coffeeMachine.makeDrink(
                drink,
                () -> view.showDispensingDrink(drink),
                () -> view.showOutOfStock(drink)
        );
    }

}
