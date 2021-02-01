package mypackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static mypackage.Ingredient.Ingred.*;

public class Model {
    final List<Drink> drinkList = new ArrayList<>();
    final List<Ingredient> ingredientList = new ArrayList<>();

    public Model() {
        addAllIngredients();
        addAllDrinks();
        updateCosts();
        updateMakeable();
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

    public void updateCosts() {
        for (Drink d : drinkList) {
            double currCost = 0;
            Map<String, Integer> currRecipe = d.getRecipe();
            for (Ingredient i : ingredientList) {
                if (currRecipe.containsKey(i.getName())) {
                    currCost += i.getCost() * currRecipe.get(i.getName());
                }
            }
            d.setCost(currCost);
        }
    }

    public void updateMakeable() {
        for (Drink d : drinkList) {
            Map<String, Integer> currRecipe = d.getRecipe();
            for (Ingredient i : ingredientList) {
                if (currRecipe.containsKey(i.getName()) && i.getStock() < currRecipe.get(i.getName())) {
                    d.setMakeable(false);
                    break;
                }
                d.setMakeable(true);
            }
        }
    }

    void makeDrink(int drinkId, CliView cliView) {
        if (drinkId <= 0 || drinkId > drinkList.size()) {
            throw new IllegalArgumentException();
        }
        Drink drink = drinkList.get(drinkId - 1);
        if (drink.getMakeable()) {
            cliView.showDispensingDrink(drink);
            for (Ingredient i : ingredientList) {
                if (drink.getRecipe().containsKey(i.getName())) {
                    i.setStock(i.getStock() - drink.getRecipe().get(i.getName()));
                }
            }
        } else {
            cliView.showOutOfStock(drink);
        }
        updateMakeable();
    }

    public void restockIngredients() {
        for (Ingredient i : ingredientList) {
            i.setStock(10);
        }
        updateMakeable();
    }

}
