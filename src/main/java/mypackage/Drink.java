package mypackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Drink implements Comparable<Drink> {
    private final Map<String, Integer> recipe = new HashMap<>();//map ingredients to units per
    private final String name;

    public Drink(String name, Ingredient.Ingred[] recipe) {
        this.name = name;
        setRecipe(recipe);
    }

    public void setRecipe(Ingredient.Ingred[] recipe) {
        for (Ingredient.Ingred ingredient : recipe) {
            String ingredientName = ingredient.getIngredientName();
            if (this.recipe.containsKey(ingredientName)) {
                this.recipe.put(ingredientName, this.recipe.get(ingredientName) + 1);//increment if multiple units
            } else {
                this.recipe.put(ingredientName, 1);//insert first occurrence of ingredient
            }
        }
    }

    public String getName() {
        return name;
    }

    void make(Ingredients ingredients, CliView view) {
        if (isMakeable(ingredients)) {
            view.showDispensingDrink(this);
            for (Ingredient ingredient : ingredients.ingredientList) {
                consume(ingredient);
            }
        } else {
            view.showOutOfStock(this);
        }

    }

    public int compareTo(Drink drink) {
        return name.compareTo(drink.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return Objects.equals(name, drink.name);
    }

    private void consume(Ingredient ingredient) {
        if (needed(ingredient, recipe)) {
            ingredient.consume(recipe.get(ingredient.getName()));
        }
    }

    public boolean isMakeable(Ingredients ingredients) {
        return ingredients.makeable(recipe, this);
    }

    public boolean needed(Ingredient ingredient, Map<String, Integer> recipe) {
        return recipe.containsKey(ingredient.getName());
    }

    public boolean available(Ingredient ingredient, Map<String, Integer> recipe) {
        return ingredient.hasAmount(recipe.get(ingredient.getName()));
    }

    double cost(List<Ingredient> ingredientList) {
        return ingredientList.stream()
                .filter(ingredient -> needed(ingredient, recipe))
                .mapToDouble(ingredient -> ingredient.getCost() * recipe.get(ingredient.getName()))
                .sum();
    }
}