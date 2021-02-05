package mypackage;

import java.util.Map;
import java.util.Objects;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Drink implements Comparable<Drink> {
    private final Map<Material, Integer> recipe;//map ingredients to units per
    public final String name;

    public Drink(String name, Material[] recipe) {
        this.name = name;
        this.recipe = asMap(recipe);
    }

    private Map<Material, Integer> asMap(Material[] recipe) {

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

    public Integer neededAmount(Ingredient ingredient) {
        return recipe.getOrDefault(ingredient.getMaterial(), 0);

    }

    public int compareTo(Drink drink) {
        return name.compareTo(drink.name);
    }

}