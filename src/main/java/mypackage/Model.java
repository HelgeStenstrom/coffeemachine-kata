package mypackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Model {
    final List<Drink> drinkList = new ArrayList<>();
    final List<Ingredient> ingredientList = new ArrayList<>();
    private static final String COFFEE = "Coffee";
    private static final String SUGAR = "Sugar";
    private static final String CREAM = "Cream";
    private static final String DECAF_COFFEE = "Decaf Coffee";
    private static final String ESPRESSO = "Espresso";
    private static final String STEAMED_MILK = "Steamed Milk";

    public Model() {
        addAllIngredients();
        addAllDrinks();
        updateCosts();
        updateMakeable();
    }

    public void addAllDrinks() {
        addDrink("Coffee", new String[]{COFFEE, COFFEE, COFFEE, SUGAR, CREAM});
        addDrink("Decaf Coffee", new String[]{DECAF_COFFEE, DECAF_COFFEE, DECAF_COFFEE, SUGAR, CREAM});
        addDrink("Caffe Latte", new String[]{ESPRESSO, ESPRESSO, STEAMED_MILK});
        addDrink("Caffe Americano", new String[]{ESPRESSO, ESPRESSO, ESPRESSO});
        addDrink("Caffe Mocha", new String[]{ESPRESSO, "Cocoa", STEAMED_MILK, "Whipped Cream"});
        addDrink("Cappuccino", new String[]{ESPRESSO, ESPRESSO, STEAMED_MILK, "Foamed Milk"});

        Collections.sort(drinkList);
    }

    public void addAllIngredients() {
        addIngredient(new Ingredient(COFFEE, 0.75));
        addIngredient(new Ingredient(DECAF_COFFEE, 0.75));
        addIngredient(new Ingredient(SUGAR, 0.25));
        addIngredient(new Ingredient(CREAM, 0.25));
        addIngredient(new Ingredient(STEAMED_MILK, 0.35));
        addIngredient(new Ingredient("Foamed Milk", 0.35));
        addIngredient(new Ingredient(ESPRESSO, 1.10));
        addIngredient(new Ingredient("Cocoa", 0.90));
        addIngredient(new Ingredient("Whipped Cream", 1.00));

        Collections.sort(ingredientList);
    }

    public void addDrink(String name, String[] recipe) {
        drinkList.add(new Drink(name, recipe));
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
