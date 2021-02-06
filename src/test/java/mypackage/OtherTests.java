package mypackage;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
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
        //noinspection SimplifiableAssertion
        assertFalse(d1.equals(d2)); // NOSONAR, we want to test the equals method

    }

    @Test
    void stock() {
        Stock c1 = new Stock(Ingredient.COFFEE, 0);
        Stock c2 = new Stock(Ingredient.COFFEE, 0);
        Stock sm = new Stock(Ingredient.STEAMED_MILK, 0);
        //noinspection SimplifiableAssertion
        assertFalse(c1.equals(sm)); // NOSONAR, we want to test the equals method
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());

    }

    @Test
    void coffeeMachine() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        Optional<Drink> drink = coffeeMachine.drinkById(34);
        assertTrue(drink.isEmpty());

    }

    @Test
    void stocks() {
        Stocks stocks = new Stocks();
        assertDoesNotThrow(() -> stocks.forEach(Stock::restock));
    }

    @Test
    void input() {
        Input input = new Input(new MyBufferedReader());
        assertThrows(UncheckedIOException.class, input::get);
    }

    static class MyBufferedReader extends BufferedReader {

        public MyBufferedReader() {
            super(new MyReader());
        }

        @Override
        public String readLine() throws IOException {
            throw new IOException();
        }
    }

    static class MyReader extends Reader {

        @Override
        public int read(char[] cb, int off, int len) {
            return 0;
        }


        @Override
        public void close() {

        }
    }

}
