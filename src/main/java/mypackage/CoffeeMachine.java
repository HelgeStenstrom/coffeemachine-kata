package mypackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CoffeeMachine {

    public static void main(String[] args) {

        // Model
        Model model = new Model();

        // View
        CliView cliView = new CliView();
        cliView.askForSelection(model.ingredientList, model.drinkList);

        // Controller
        control(cliView, model);
    }

    public static void control(CliView view, Model model) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        while (true) {
            try {
                input = reader.readLine().toLowerCase();

                if (input.equals("")) {
                    break;
                } else if (input.equals("q")) {
                    System.exit(0);
                } else if (input.equals("r")) {
                    model.restockIngredients();
                } else {
                    model.makeDrink(Integer.parseInt(input), view);
                }
                view.askForSelection(model.ingredientList, model.drinkList);
            } catch (Exception e) {
                view.showInvalidSelection(input); //illegal input
            }
        }
    }

}
