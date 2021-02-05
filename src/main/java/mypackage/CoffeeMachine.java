package mypackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mypackage.Ingredient.Ingred.*;

public class CoffeeMachine {
    public final Ingredients ingredients;
    final List<Drink> drinkList = new ArrayList<>();

    public CoffeeMachine() {
        ingredients = new Ingredients();
        addAllDrinks();
    }

    public void addAllDrinks() {
        addDrink("Coffee", new Ingredient.Ingred[]{COFFEE, COFFEE, COFFEE, SUGAR, CREAM});
        addDrink("Decaf Coffee", new Ingredient.Ingred[]{DECAF_COFFEE, DECAF_COFFEE, DECAF_COFFEE, SUGAR, CREAM});
        addDrink("Caffe Latte", new Ingredient.Ingred[]{ESPRESSO, ESPRESSO, STEAMED_MILK});
        addDrink("Caffe Americano", new Ingredient.Ingred[]{ESPRESSO, ESPRESSO, ESPRESSO});
        addDrink("Caffe Mocha", new Ingredient.Ingred[]{ESPRESSO, COCOA, STEAMED_MILK, WHIPPED_CREAM});
        addDrink("Cappuccino", new Ingredient.Ingred[]{ESPRESSO, ESPRESSO, STEAMED_MILK, FOAMED_MILK});

        Collections.sort(drinkList);
    }

    public void addDrink(String name, Ingredient.Ingred[] recipe) {
        Drink drink = new Drink(name, recipe);
        drinkList.add(drink);
    }

    public void makeDrink(int drinkId, CliView view) {
        drinkById(drinkId).make(ingredients, view);
    }

    private Drink drinkById(int drinkId) {
        assertDrinkExists(drinkId);
        return drinkList.get(drinkId - 1);
    }

    private void assertDrinkExists(int drinkId) {
        if (drinkId <= 0 || drinkId > drinkList.size()) {
            throw new IllegalArgumentException();
        }
    }

    public void restockIngredients() {

        ingredients.restock();
    }

}
