package mypackage.v;

import mypackage.Drink;
import mypackage.Ingredient;
import mypackage.Ingredients;

import java.util.List;

public class CliView {

    public void askForSelection(List<Drink> drinkList, Ingredients ingredients) {
        System.out.println("Inventory:");
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getName() + "," + ingredient.getStock());
        }

        System.out.println("\nMenu:");
        int count = 1;
        for (Drink drink : drinkList) {
            System.out.printf("%d,%s,$%.2f,%s%n",
                    count,
                    drink.getName(),
                    ingredients.cost(drink),
                    ingredients.canMake(drink));
            count++;
        }

        System.out.print("\nYour selection: ");
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