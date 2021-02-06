package mypackage.dr;

import mypackage.Ingredient;

import java.util.Map;
import java.util.Objects;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Drink implements Comparable<Drink> {
    private final Map<Ingredient, Integer> recipe;//map ingredients to units per
    private final String name;

    public Drink(String name, Ingredient[] recipe) {
        this.name = name;
        this.recipe = asMap(recipe);
    }

    public Integer neededAmount(Ingredient ingredient) {
        return recipe.getOrDefault(ingredient, 0);

    }

    private Map<Ingredient, Integer> asMap(Ingredient[] recipe) {

        return stream(recipe)
                .collect(groupingBy(
                        identity(),
                        summingInt(e -> 1)));
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

    public int compareTo(Drink drink) {
        return name.compareTo(drink.name);
    }

    public String getName() {
        return name;
    }
}