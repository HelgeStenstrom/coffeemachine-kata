package mypackage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Drink implements Comparable<Drink> {
    private final Map<String, Integer> recipe;//map ingredients to units per
    private final String name;

    public Drink(String name, Material[] recipe) {
        this.name = name;
        this.recipe = asMap(recipe);
    }

    public String getName() {
        return name;
    }

    private Map<String, Integer> asMap(Material[] recipe) {
        // Time: 14:38
        Map<Material, Long> collect = Arrays.stream(recipe).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        Map<String, Integer> recipeMap = new HashMap<>();
        for (Material ingredient : recipe) {
            String ingredientName = ingredient.getIngredientName();
            if (recipeMap.containsKey(ingredientName)) {
                recipeMap.put(ingredientName, recipeMap.get(ingredientName) + 1);//increment if multiple units
            } else {
                recipeMap.put(ingredientName, 1);//insert first occurrence of ingredient
            }
        }
        return recipeMap;
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

    public Integer neededAmount(Ingredient ingredient) {
        return recipe.getOrDefault(ingredient.getName(), 0);

    }

    public int compareTo(Drink drink) {
        return name.compareTo(drink.getName());
    }

}