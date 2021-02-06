package mypackage.m;

import mypackage.Input;
import mypackage.cm.CoffeeMachine;
import mypackage.dr.Drink;
import mypackage.v.CliView;

import java.io.IOException;

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
        Input input = new Input();
        String commmand = "";

        do {
            try {
                commmand = input.get();

                switch (commmand) {
                    case "":
                    case QUIT:
                        continue;
                    case RESTOCK:
                        coffeeMachine.restock();
                        view.askForSelection(coffeeMachine);
                        break;
                    default:
                        try {
                            makeDrink(coffeeMachine, view, Integer.parseInt(commmand));
                            view.askForSelection(coffeeMachine);
                        } catch (NumberFormatException e) {
                            view.showInvalidSelection(commmand); //illegal commmand
                        }
                        break;
                }
            } catch (IOException | IllegalArgumentException e) {
            }
        } while (!commmand.equals(QUIT));
    }

    private static void makeDrink(CoffeeMachine coffeeMachine, CliView view, int drinkId) {
        Drink drink = coffeeMachine.drinkById(drinkId).orElseThrow(IllegalArgumentException::new);
        coffeeMachine.makeDrink(
                drink,
                () -> view.showDispensingDrink(drink),
                () -> view.showOutOfStock(drink)
        );
    }

}
