package mypackage.v;

import mypackage.Drink;
import mypackage.Ingredient;
import mypackage.Ingredients;

import java.util.List;

public class CliView {

    public void askForSelection(List<Drink> drinkList, Ingredients ingredients) {
        System.out.println("Inventory:");
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.name() + "," + ingredient.stock());
        }

        System.out.println("\nMenu:");
        int count = 1;
        for (Drink drink : drinkList) {
            double cost = ingredients.cost(drink);
            System.out.printf("%d,%s,$%.2f,%s%n",
                    count,
                    drink.name,
                    cost,
                    ingredients.canMake(drink));
            count++;
        }

        System.out.print("\nYour selection: ");
    }

    public void showOutOfStock(Drink drink) {
        System.out.println("Out of stock: " + drink.name + "\n");
    }

    public void showDispensingDrink(Drink drink) {
        System.out.println("Dispensing: " + drink.name + "\n");
    }

    public void showInvalidSelection(String input) {
        System.out.print("Invalid selection: " + input + ". Try again: ");
    }
}