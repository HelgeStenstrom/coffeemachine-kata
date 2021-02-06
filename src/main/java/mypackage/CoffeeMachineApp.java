package mypackage;

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

        while (true) {
            String command = input.get();
            if (command.equals(QUIT)) break;
            handleCommand(command, coffeeMachine, view);
        }
    }

    private static void handleCommand(String command, CoffeeMachine coffeeMachine, CliView view) {
        if (RESTOCK.equals(command)) {
            restock(coffeeMachine, view);
        } else {
            makeDrink(command, coffeeMachine, view);
        }
    }

    private static void restock(CoffeeMachine coffeeMachine, CliView view) {
        coffeeMachine.restock();
        view.askForSelection(coffeeMachine);
    }

    private static void makeDrink(String command, CoffeeMachine coffeeMachine, CliView view) {
        try {
            Drink drink = coffeeMachine.drinkById(Integer.parseInt(command)).orElseThrow(IllegalArgumentException::new);
            coffeeMachine.makeDrink(
                    drink,
                    () -> view.showDispensingDrink(drink),
                    () -> view.showOutOfStock(drink)
            );
            view.askForSelection(coffeeMachine);
        } catch (NumberFormatException e) {
            view.showInvalidSelection(command); //illegal command
        }
    }

}
