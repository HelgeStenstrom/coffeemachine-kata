package mypackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoffeeMachineApp {

    public static void main(String[] args) {

        // Model
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        // View
        CliView view = new CliView();

        // Controller
        control(view, coffeeMachine);
    }

    public static void control(CliView view, CoffeeMachine coffeeMachine) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        view.askForSelection(coffeeMachine.drinkList, coffeeMachine.ingredients);

        while (true) try {
            input = reader.readLine().toLowerCase();

            if (input.equals("q")) {
                break;
            } else if (input.equals("r")) {
                coffeeMachine.restockIngredients();
            } else {
                coffeeMachine.makeDrink(Integer.parseInt(input), view);
            }
            view.askForSelection(coffeeMachine.drinkList, coffeeMachine.ingredients);
        } catch (IOException | IllegalArgumentException e) {
            view.showInvalidSelection(input); //illegal input
        }
    }

}
