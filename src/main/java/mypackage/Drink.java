package mypackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Drink implements Comparable<Drink> {
    private final Map<String, Integer> recipe = new HashMap<>();//map ingredients to units per
    private final String name;
    private double totalCost = 0;

    public Drink(String name, Ingredient.Ingred[] recipe) {
        this.name = name;
        setRecipe(recipe);
    }

    public int compareTo(Drink drink) {
        return name.compareTo(drink.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return Objects.equals(name, drink.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setRecipe(Ingredient.Ingred[] recipe) {
        for (Ingredient.Ingred ingr : recipe) {
            String s = ingr.getIngredientName();
            if (this.recipe.containsKey(s)) {
                this.recipe.put(s, this.recipe.get(s) + 1);//increment if multiple units
            } else {
                this.recipe.put(s, 1);//insert first occurrence of ingredient
            }
        }
    }

    public void setCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Map<String, Integer> getRecipe() {
        return recipe;
    }

    public double getCost() {
        return totalCost;
    }

    public String getName() {
        return name;
    }

    void make(List<Ingredient> ingredientList, CliView view) {
        if (isMakeable(ingredientList)) {
            view.showDispensingDrink(this);
            for (Ingredient ingredient : ingredientList) {
                consume(ingredient);
            }
        } else {
            view.showOutOfStock(this);
        }

    }

    private void consume(Ingredient ingredient) {
        if (isNeeded(ingredient)) {
            ingredient.consume(neededAmount(ingredient));
        }
    }

    private boolean isNeeded(Ingredient ingredient) {
        return recipe.containsKey(ingredient.getName());
    }

    private Integer neededAmount(Ingredient ingredient) {
        return recipe.get(ingredient.getName());
    }

    public boolean isMakeable(List<Ingredient> ingredientList) {
        return ingredientList.stream()
                .filter(this::isNeeded)
                .allMatch(this::available);
    }

    private boolean available(Ingredient ingredient) {
        return ingredient.hasAmount(neededAmount(ingredient));
    }

}