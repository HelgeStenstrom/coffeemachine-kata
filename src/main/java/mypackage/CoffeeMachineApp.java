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
            String commmand = input.get();
            if (commmand.equals(QUIT)) break;
            handleCommand(commmand, coffeeMachine, view);
        }
    }

    private static void handleCommand(String command, CoffeeMachine coffeeMachine, CliView view) {
        switch (command) {
            case "":
            case QUIT:
                break;
            case RESTOCK:
                restock(coffeeMachine, view);
                break;
            default:
                makeDrink(command, coffeeMachine, view);
                break;
        }
    }

    private static void restock(CoffeeMachine coffeeMachine, CliView view) {
        coffeeMachine.restock();
        view.askForSelection(coffeeMachine);
    }

    private static void makeDrink(String commmand, CoffeeMachine coffeeMachine, CliView view) {
        try {
            Drink drink = coffeeMachine.drinkById(Integer.parseInt(commmand)).orElseThrow(IllegalArgumentException::new);
            coffeeMachine.makeDrink(
                    drink,
                    () -> view.showDispensingDrink(drink),
                    () -> view.showOutOfStock(drink)
            );
            view.askForSelection(coffeeMachine);
        } catch (NumberFormatException e) {
            view.showInvalidSelection(commmand); //illegal commmand
        }
    }

}
