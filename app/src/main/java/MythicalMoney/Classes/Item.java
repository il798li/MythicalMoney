package MythicalMoney.Classes;

import java.util.ArrayList;
import java.util.Iterator;
import MythicalMoney.Utility.StringUtility;

import MythicalMoney.Main;

public class Item {
    public static ArrayList <Item> items = new ArrayList <Item> ();

    public String name;
    public String single;
    public String plural;
    public int price;

    public void initialize (String name, String single, String plural, int price) {
        this.name = name.toLowerCase();
        this.single = single;
        this.plural = plural;
        this.price = price;

        items.add (this);
    }

    public Item (String name, int price) {
        String single = StringUtility.title (name);
        String plural = single + "s";
        this.initialize (name, single, plural, price);
    }

    public Item (String name, String plural, int price) {
        String single = StringUtility.title (name);
        this.initialize (name, single, plural, price);
    }

    public Item (String name, String single, String plural, int price) {
        this.initialize (name, single, plural, price);
    }

    public static void setup () {
        Item gold = new Item ("gold", "Gold", "Gold", 1000);
        Item sapphire = new Item ("sapphire", 5000);
        Item emerald = new Item ("emerald", 1000);
        Item ruby = new Item ("ruby", "Rubies", 25000);

        Item bone = new Item ("bone", 1000);
        Item mist = new Item ("mist", "Mist", 5000);
        Item fang = new Item ("fang", 10000);
        Item flamer = new Item ("flamer", 25000);

        Item acacia = new Item ("acacia", "Acacia Wood", "Acacia Wood", 1000);
        Item birch = new Item ("birch", "Birch Wood", "Birch Wood", 5000);
        Item oak = new Item ("oak", "Oak Wood", "Oak Wood", 10000);
        Item spruce = new Item ("spruce", "Spruce Wood", "Spruce Wood", 25000);
    }

    public String toString () {
        String string = "";
        string += "Name: \"" + this.name + "\"\n";
        string += "Single: \"" + this.single + "\"\n";
        string += "Plural: \"" + this.plural + "\"\n";
        string += "Price: " + this.price;
        return string;
    }

    public static Item find (String name) {
        for (Item item : items.subList (0, items.size ())) {
            if (item.name.toLowerCase ().contains (name)) {
                return item;
            }
        }
        return null;
    }
}
