package mypackage;

public enum Ingredient {
    COFFEE("Coffee"),
    SUGAR("Sugar"),
    CREAM("Cream"),
    DECAF_COFFEE("Decaf Coffee"),
    ESPRESSO("Espresso"),
    STEAMED_MILK("Steamed Milk"),
    COCOA("Cocoa"),
    WHIPPED_CREAM("Whipped Cream"),
    FOAMED_MILK("Foamed Milk");

    public String getIngredientName() {
        return ingredientName;
    }

    private final String ingredientName;

    Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
