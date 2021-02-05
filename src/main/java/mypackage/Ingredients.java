package mypackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static mypackage.Ingredient.Ingred.*;

public class Ingredients {
    public final List<Ingredient> ingredientList = new ArrayList<>();

    public Ingredients() {
        ingredientList.add(new Ingredient(COFFEE, 0.75));
        ingredientList.add(new Ingredient(DECAF_COFFEE, 0.75));
        ingredientList.add(new Ingredient(SUGAR, 0.25));
        ingredientList.add(new Ingredient(CREAM, 0.25));
        ingredientList.add(new Ingredient(STEAMED_MILK, 0.35));
        ingredientList.add(new Ingredient(FOAMED_MILK, 0.35));
        ingredientList.add(new Ingredient(ESPRESSO, 1.10));
        ingredientList.add(new Ingredient(COCOA, 0.90));
        ingredientList.add(new Ingredient(WHIPPED_CREAM, 1.00));

        Collections.sort(ingredientList);
    }

    public void restock() {
        ingredientList.forEach(ingredient -> ingredient.setStock(10));

    }

    boolean makeable(Map<String, Integer> recipe, Drink drink) {
        return ingredientList.stream()
                .filter(ingredient -> drink.needed(ingredient, recipe))
                .allMatch(ingredient -> drink.available(ingredient, recipe));
    }
}