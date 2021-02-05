package mypackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mypackage.Ingredient.Ingred.*;

public class Model {
    final List<Drink> drinkList = new ArrayList<>();
    final List<Ingredient> ingredientList = new ArrayList<>();

    public Model() {
        addAllIngredients();
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

    public void addAllIngredients() {
        addIngredient(new Ingredient(COFFEE, 0.75));
        addIngredient(new Ingredient(DECAF_COFFEE, 0.75));
        addIngredient(new Ingredient(SUGAR, 0.25));
        addIngredient(new Ingredient(CREAM, 0.25));
        addIngredient(new Ingredient(STEAMED_MILK, 0.35));
        addIngredient(new Ingredient(FOAMED_MILK, 0.35));
        addIngredient(new Ingredient(ESPRESSO, 1.10));
        addIngredient(new Ingredient(COCOA, 0.90));
        addIngredient(new Ingredient(WHIPPED_CREAM, 1.00));

        Collections.sort(ingredientList);
    }

    public void addDrink(String name, Ingredient.Ingred[] recipe) {
        Drink drink = new Drink(name, recipe);
        drinkList.add(drink);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    public void makeDrink(int drinkId, CliView view) {
        drinkById(drinkId).make(ingredientList, view);
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
        for (Ingredient i : ingredientList) {
            i.setStock(10);
        }

    }

}
