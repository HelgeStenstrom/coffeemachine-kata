package mypackage;

import mypackage.dr.Drink;

import java.util.Objects;

public class Stock implements Comparable<Stock> {

    public final Ingredient ingredient;
    private final double cost;
    private int amount;

    public Stock(Ingredient ingredient, double cost) {
        this.ingredient = ingredient;
        this.cost = cost;
        amount = 10;
    }

    public int amount() {
        return amount;
    }

    void restock() {
        amount = 10;
    }

    void consume(Drink drink) {
        amount -= drink.neededAmount(ingredient);
    }

    boolean available(Drink drink) {
        return amount >= drink.neededAmount(ingredient);
    }

    double cost(Drink drink) {
        return cost * drink.neededAmount(ingredient);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock that = (Stock) o;
        return Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient);
    }

    public String name() {
        return ingredient.getIngredientName();
    }


    public int compareTo(Stock stock) {
        return ingredient.getIngredientName().compareTo(stock.name());
    }
}