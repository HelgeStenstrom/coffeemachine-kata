package mypackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class CoffeeMachine {

    private static List<Drink> drinkList = new ArrayList<>();
    private static List<Ingredient> ingredientList = new ArrayList<>();

    public static void main(String[] args) {

        // Model
        addAllIngredients();
        addAllDrinks();
        updateCosts();
        updateMakeable();

        // View
        CliView cliView = new CliView();
        cliView.askForSelection(ingredientList, drinkList);

        // Controller
        control(cliView);
    }

    public static void control(CliView cliView) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        while (true) {
            try {
                input = reader.readLine().toLowerCase();

                if (input.equals("")) {
                    continue;
                } else if (input.equals("q")) {
                    System.exit(0);
                } else if (input.equals("r")) {
                    restockIngredients(cliView);
                    updateMakeable();
                } else if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= drinkList.size()) { //dynamic drink menu selection
                    makeDrink(drinkList.get(Integer.parseInt(input) - 1), cliView);
                } else {
                    throw new IOException(); //legal, but invalid input
                }
            } catch (Exception e) {
                cliView.showInvalidSelection(input); //illegal input
            }
        }
    }

    public static void updateMakeable() {
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

    public static void updateCosts() {
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

    public static void makeDrink(Drink drink, CliView cliView) {
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
        cliView.askForSelection(ingredientList, drinkList);
    }

    public static void restockIngredients(CliView cliView) {
        for (Ingredient i : ingredientList) {
            i.setStock(10);
        }
        updateMakeable();
        cliView.askForSelection(ingredientList, drinkList);
    }

    public static void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    public static void addDrink(String name, String[] recipe) {
        drinkList.add(new Drink(name, recipe));
    }

    public static void addAllIngredients() {
        addIngredient(new Ingredient("Coffee", 0.75));
        addIngredient(new Ingredient("Decaf Coffee", 0.75));
        addIngredient(new Ingredient("Sugar", 0.25));
        addIngredient(new Ingredient("Cream", 0.25));
        addIngredient(new Ingredient("Steamed Milk", 0.35));
        addIngredient(new Ingredient("Foamed Milk", 0.35));
        addIngredient(new Ingredient("Espresso", 1.10));
        addIngredient(new Ingredient("Cocoa", 0.90));
        addIngredient(new Ingredient("Whipped Cream", 1.00));

        Collections.sort(ingredientList);
    }

    public static void addAllDrinks() {
        addDrink("Coffee", new String[]{"Coffee", "Coffee", "Coffee", "Sugar", "Cream"});
        addDrink("Decaf Coffee", new String[]{"Decaf Coffee", "Decaf Coffee", "Decaf Coffee", "Sugar", "Cream"});
        addDrink("Caffe Latte", new String[]{"Espresso", "Espresso", "Steamed Milk"});
        addDrink("Caffe Americano", new String[]{"Espresso", "Espresso", "Espresso"});
        addDrink("Caffe Mocha", new String[]{"Espresso", "Cocoa", "Steamed Milk", "Whipped Cream"});
        addDrink("Cappuccino", new String[]{"Espresso", "Espresso", "Steamed Milk", "Foamed Milk"});

        Collections.sort(drinkList);
    }

}
