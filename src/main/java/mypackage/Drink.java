package mypackage;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Drink implements Comparable<Drink> {
    private final Map<Material, Integer> recipe;//map ingredients to units per
    private final String name;

    public Drink(String name, Material[] recipe) {
        this.name = name;
        this.recipe = asMap(recipe);
    }

    public String getName() {
        return name;
    }

    private Map<Material, Integer> asMap(Material[] recipe) {

        return Arrays.stream(recipe)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.summingInt(e -> 1)));
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
        return recipe.getOrDefault(ingredient.getMaterial(), 0);

    }

    public int compareTo(Drink drink) {
        return name.compareTo(drink.getName());
    }

}