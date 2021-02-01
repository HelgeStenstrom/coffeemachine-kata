package mypackage;

import java.util.Objects;

public class Ingredient implements Comparable<Ingredient> {
    private final String name;
    private final double cost;
    private int stock;

    public Ingredient(String name, double cost) {
        this.name = name;
        this.cost = cost;
        this.stock = 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int compareTo(Ingredient ingredient) {
        return name.compareTo(ingredient.getName());
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getStock() {
        return stock;
    }

}