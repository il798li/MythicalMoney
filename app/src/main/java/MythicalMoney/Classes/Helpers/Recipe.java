package MythicalMoney.Classes.Helpers;

import MythicalMoney.Classes.Item;
import MythicalMoney.Data.Player;

public class Recipe {
    public final Ingredient[] ingredients;
    public Recipe (final Ingredient... ingredients) {
        this.ingredients = ingredients;
    }

    public boolean craft (Player player) {
        for (final Ingredient ingredient : this.ingredients) {
            if (ingredient.item == null) {
                if (player.mm < ingredient.amount) {
                    return false;
                }
            } else {
                final int amount = player.inventory.get (ingredient.item);
                if (amount < ingredient.amount) {
                    return false;
                }
            }
        }
        return true;
    }
    public static class Ingredient {
        public final Item item;
        public final int amount;

        public Ingredient (final Item item, final int amount) {
            this.item = item;
            this.amount = amount;
        }

        public static Ingredient cost (final int cost) {
            return new Ingredient (null, cost);
        }
    }
}
