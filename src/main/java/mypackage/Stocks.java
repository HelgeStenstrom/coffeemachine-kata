package mypackage;

import mypackage.dr.Drink;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static mypackage.Ingredient.*;

public class Stocks implements Iterable<Stock> {
    private final List<Stock> stockList = new ArrayList<>();

    public Stocks() {
        stockList.add(new Stock(COFFEE, 0.75));
        stockList.add(new Stock(DECAF_COFFEE, 0.75));
        stockList.add(new Stock(SUGAR, 0.25));
        stockList.add(new Stock(CREAM, 0.25));
        stockList.add(new Stock(STEAMED_MILK, 0.35));
        stockList.add(new Stock(FOAMED_MILK, 0.35));
        stockList.add(new Stock(ESPRESSO, 1.10));
        stockList.add(new Stock(COCOA, 0.90));
        stockList.add(new Stock(WHIPPED_CREAM, 1.00));

        Collections.sort(stockList);
    }

    public void restock() {
        stockList.forEach(Stock::restock);

    }

    public boolean canMake(Drink drink) {
        return stream()
                .allMatch(stock -> stock.available(drink));
    }

    public double cost(Drink drink) {
        return stream()
                .mapToDouble(stock -> stock.cost(drink))
                .sum();
    }

    public void make(Drink drink, Runnable onSuccess, Runnable onError) {
        if (!canMake(drink)) {
            onError.run();
            return;
        }
        onSuccess.run();
        _make(drink);

    }

    private void _make(Drink drink) {
        for (Stock stock : this) {
            stock.consume(drink);
        }
    }

    private Stream<Stock> stream() {
        return stockList.stream();
    }


    @Override
    public Iterator<Stock> iterator() {
        return stockList.iterator();
    }


    @Override
    public void forEach(Consumer<? super Stock> action) {
        stockList.forEach(action);
    }


    @Override
    public Spliterator<Stock> spliterator() {
        return stockList.spliterator();
    }
}