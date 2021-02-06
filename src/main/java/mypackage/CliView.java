package mypackage;

public class CliView {

    public void askForSelection(CoffeeMachine coffeeMachine) {
        System.out.println("Inventory:");
        for (Stock stock : coffeeMachine.stocks) {
            System.out.println(stock.toString() + "," + stock.amount());
        }

        System.out.println("\nMenu:");
        int count = 1;
        for (Drink drink : coffeeMachine.menu) {
            printDrink(coffeeMachine, count, drink);
            count++;
        }

        System.out.print("\nYour selection: ");
    }

    private void printDrink(CoffeeMachine coffeeMachine, int count, Drink drink) {
        System.out.printf("%d,%s,$%.2f,%s%n",
                count,
                drink.getName(),
                coffeeMachine.cost(drink),
                coffeeMachine.canMake(drink));
    }

    public void showOutOfStock(Drink drink) {
        System.out.println("Out of stock: " + drink.getName() + "\n");
    }

    public void showDispensingDrink(Drink drink) {
        System.out.println("Dispensing: " + drink.getName() + "\n");
    }

    public void showInvalidSelection(String input) {
        System.out.print("Invalid selection: " + input + ". Try again: ");
    }
}