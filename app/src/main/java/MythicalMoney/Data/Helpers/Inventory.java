package MythicalMoney.Data.Helpers;

import MythicalMoney.Classes.Item;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Inventory {

    public static final int totalItems = Item.items.size ();
    public final int[] items;

    public Inventory () {
        this.items = new int[totalItems];
        Arrays.fill (this.items, 0);
    }

    public static Inventory fromJSON (JSONObject jsonObject) {
        Inventory inventory = new Inventory ();
        inventory.setup (jsonObject);
        return inventory;
    }

    public static JSONObject toJSON (Inventory inventory) {
        JSONObject jsonObject = new JSONObject ();
        for (int index = 0; index < inventory.items.length; index++) {
            Item item = Item.items.get (index);
            final int amount = inventory.items[index];
            jsonObject.put (item.display.name, amount);
        }
        return jsonObject;
    }

    public void setup (JSONObject jsonObject) {
        final Item[] items = Item.toList ();
        for (int index = 0; index < items.length; index++) {
            final Item item = items[index];
            String name = item.display.name;
            int amount;
            try {
                amount = jsonObject.getInt (name);
            } catch (JSONException jsonException) {
                amount = 0;
            }
            this.items[index] = amount;
        }
    }

    public JSONObject toJSON () {
        return toJSON (this);
    }

    public int subtract (Item item, int amount) {
        return this.add (item, -amount);
    }

    public int add (Item item, int amount) {
        final int newAmount = this.get (item) + amount;
        final int index = Item.indexOf (item);
        this.items[index] = newAmount;
        return newAmount;
    }

    public int get (Item item) {
        return this.get (item.display.name);
    }

    public int get (String item) {
        int index = Item.indexOf (item);
        return this.items[index];
    }
}
