package mypackage;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static mypackage.Material.*;

public class Ingredients implements Iterable<Ingredient> {
    public final List<Ingredient> ingredientList = new ArrayList<>();

    public Ingredients() {
        ingredientList.add(new Ingredient(COFFEE, 0.75));
        ingredientList.add(new Ingredient(DECAF_COFFEE, 0.75));
        ingredientList.add(new Ingredient(SUGAR, 0.25));
        ingredientList.add(new Ingredient(CREAM, 0.25));
        ingredientList.add(new Ingredient(STEAMED_MILK, 0.35));
        ingredientList.add(new Ingredient(FOAMED_MILK, 0.35));
        ingredientList.add(new Ingredient(ESPRESSO, 1.10));
        ingredientList.add(new Ingredient(COCOA, 0.90));
        ingredientList.add(new Ingredient(WHIPPED_CREAM, 1.00));

        Collections.sort(ingredientList);
    }

    public void restock() {
        ingredientList.forEach(ingredient -> ingredient.setStock(10));

    }

    public boolean canMake(Drink drink) {
        return stream()
                .allMatch(ingredient -> ingredient.available(drink.neededAmount(ingredient)));
    }

    public double cost(Drink drink) {
        return stream()
                .mapToDouble(ingredient -> ingredient.cost(drink))
                .sum();
    }

    void make(Drink drink, Runnable onSuccess, Runnable onError) {
        if (!canMake(drink)) {
            onError.run();
            return;
        }
        onSuccess.run();
        make(drink);

    }

    private void make(Drink drink) {
        for (Ingredient ingredient : this) {
            ingredient.consume(drink.neededAmount(ingredient));
        }
    }

    private Stream<Ingredient> stream() {
        return ingredientList.stream();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Ingredient> iterator() {
        return ingredientList.iterator();
    }

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Actions are performed in the order of iteration, if that
     * order is specified.  Exceptions thrown by the action are relayed to the
     * caller.
     * <p>
     * The behavior of this method is unspecified if the action performs
     * side-effects that modify the underlying source of elements, unless an
     * overriding class has specified a concurrent modification policy.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * @since 1.8
     */
    @Override
    public void forEach(Consumer<? super Ingredient> action) {
        ingredientList.forEach(action);
    }

    /**
     * Creates a {@link Spliterator} over the elements described by this
     * {@code Iterable}.
     *
     * @return a {@code Spliterator} over the elements described by this
     * {@code Iterable}.
     * @implSpec The default implementation creates an
     * <em><a href="../util/Spliterator.html#binding">early-binding</a></em>
     * spliterator from the iterable's {@code Iterator}.  The spliterator
     * inherits the <em>fail-fast</em> properties of the iterable's iterator.
     * @implNote The default implementation should usually be overridden.  The
     * spliterator returned by the default implementation has poor splitting
     * capabilities, is unsized, and does not report any spliterator
     * characteristics. Implementing classes can nearly always provide a
     * better implementation.
     * @since 1.8
     */
    @Override
    public Spliterator<Ingredient> spliterator() {
        return ingredientList.spliterator();
    }
}