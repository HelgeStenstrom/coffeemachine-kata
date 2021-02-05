package mypackage;

import java.util.List;

public class CliView {

    public void askForSelection(List<Drink> drinkList, Ingredients ingredients) {
        System.out.println("Inventory:");
        for (Ingredient i : ingredients.ingredientList) {
            System.out.println(i.getName() + "," + i.getStock());
        }

        System.out.println("\nMenu:");
        int count = 1;
        for (Drink drink : drinkList) {
            System.out.printf("%d,%s,$%.2f,%s%n",
                    count,
                    drink.getName(),
                    drink.cost(ingredients.ingredientList),
                    drink.isMakeable(ingredients));
            count++;
        }

        System.out.print("\nYour selection: ");
    }

    void showOutOfStock(Drink drink) {
        System.out.println("Out of stock: " + drink.getName() + "\n");
    }

    void showDispensingDrink(Drink drink) {
        System.out.println("Dispensing: " + drink.getName() + "\n");
    }

    void showInvalidSelection(String input) {
        System.out.print("Invalid selection: " + input + ". Try again: ");
    }
}