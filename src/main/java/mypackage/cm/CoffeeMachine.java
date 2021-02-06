package mypackage.cm;

import mypackage.Ingredient;
import mypackage.Stocks;
import mypackage.dr.Drink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static mypackage.Ingredient.*;

public class CoffeeMachine {
    public final Stocks stocks;
    public final List<Drink> menu = new ArrayList<>();

    public CoffeeMachine() {
        stocks = new Stocks();
        addAllDrinks();
    }

    public boolean canMake(Drink drink) {
        return stocks.canMake(drink);
    }

    public double cost(Drink drink) {
        return stocks.cost(drink);
    }

    public void makeDrink(Drink drink, Runnable onSuccess, Runnable onError) {
        stocks.make(
                drink,
                onSuccess,
                onError);
    }

    public void restock() {
        stocks.restock();
    }

    public Optional<Drink> drinkById(int drinkId) {
        if (drinkExists(drinkId)) {
            return Optional.of(menu.get(drinkId - 1));
        }
        else
            return Optional.empty();
    }

    public void addDrink(String name, Ingredient[] recipe) {
        Drink drink = new Drink(name, recipe);
        menu.add(drink);
    }

    public void addAllDrinks() {
        addDrink("Coffee", new Ingredient[]{COFFEE, COFFEE, COFFEE, SUGAR, CREAM});
        addDrink("Decaf Coffee", new Ingredient[]{DECAF_COFFEE, DECAF_COFFEE, DECAF_COFFEE, SUGAR, CREAM});
        addDrink("Caffe Latte", new Ingredient[]{ESPRESSO, ESPRESSO, STEAMED_MILK});
        addDrink("Caffe Americano", new Ingredient[]{ESPRESSO, ESPRESSO, ESPRESSO});
        addDrink("Caffe Mocha", new Ingredient[]{ESPRESSO, COCOA, STEAMED_MILK, WHIPPED_CREAM});
        addDrink("Cappuccino", new Ingredient[]{ESPRESSO, ESPRESSO, STEAMED_MILK, FOAMED_MILK});

        Collections.sort(menu);
    }

    private boolean drinkExists(int drinkId) {
        return drinkId > 0 && drinkId <= menu.size();
    }
}
