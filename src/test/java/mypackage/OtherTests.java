package mypackage;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OtherTests {
    @Test
    void drinks() {
        Drink d1 = new Drink("a name", new Ingredient[]{});
        Drink d2 = new Drink("a name", new Ingredient[]{});
        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());

    }

    @Test
    void otherDrinks() {
        Drink d1 = new Drink("a name", new Ingredient[]{});
        Drink d2 = new Drink("another name", new Ingredient[]{});
        assertNotEquals(d1, d2);
        assertFalse(d1.equals(d2)); // NOSONAR, we want to test the equals method

    }

    @Test
    void stock() {
        Stock c1 = new Stock(Ingredient.COFFEE, 0);
        Stock c2 = new Stock(Ingredient.COFFEE, 0);
        Stock sm = new Stock(Ingredient.STEAMED_MILK, 0);
        assertFalse(c1.equals(sm));
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());

    }

    @Test
    void coffeMachine() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        Optional<Drink> drink = coffeeMachine.drinkById(34);
        assertTrue(drink.isEmpty());

    }
}
