package mypackage;

import mypackage.v.CliView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mypackage.Material.*;

public class CoffeeMachine {
    public final Ingredients ingredients;
    public final List<Drink> drinkList = new ArrayList<>();

    public CoffeeMachine() {
        ingredients = new Ingredients();
        addAllDrinks();
    }

    public void addAllDrinks() {
        addDrink("Coffee", new Material[]{COFFEE, COFFEE, COFFEE, SUGAR, CREAM});
        addDrink("Decaf Coffee", new Material[]{DECAF_COFFEE, DECAF_COFFEE, DECAF_COFFEE, SUGAR, CREAM});
        addDrink("Caffe Latte", new Material[]{ESPRESSO, ESPRESSO, STEAMED_MILK});
        addDrink("Caffe Americano", new Material[]{ESPRESSO, ESPRESSO, ESPRESSO});
        addDrink("Caffe Mocha", new Material[]{ESPRESSO, COCOA, STEAMED_MILK, WHIPPED_CREAM});
        addDrink("Cappuccino", new Material[]{ESPRESSO, ESPRESSO, STEAMED_MILK, FOAMED_MILK});

        Collections.sort(drinkList);
    }

    public void addDrink(String name, Material[] recipe) {
        Drink drink = new Drink(name, recipe);
        drinkList.add(drink);
    }

    public void makeDrink(int drinkId, CliView view) {
        ingredients.make(
                drinkById(drinkId),
                () -> view.showDispensingDrink(drinkById(drinkId)),
                () -> view.showOutOfStock(drinkById(drinkId)));
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
