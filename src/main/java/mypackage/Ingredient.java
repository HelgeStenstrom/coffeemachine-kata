package mypackage;

import java.util.Objects;

public class Ingredient implements Comparable<Ingredient> {

    private final double cost;
    private final Material material;
    private int stock;

    public Ingredient(Material material, double cost) {
        this.material = material;
        this.cost = cost;
        this.stock = 10;
    }

    void restock() {
        this.stock = 10;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(material);
    }

    public String name() {
        return material.getIngredientName();
    }

    public int stock() {
        return stock;
    }

    void consume(int neededAmount) {
        this.stock = stock - neededAmount;
    }

    public boolean available(int amount) {
        return stock >= amount;
    }

    double cost(Drink drink) {
        return cost * drink.neededAmount(this);
    }


    public int compareTo(Ingredient ingredient) {
        return material.getIngredientName().compareTo(ingredient.name());
    }
}