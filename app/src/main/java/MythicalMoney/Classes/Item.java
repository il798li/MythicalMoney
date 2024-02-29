package MythicalMoney.Classes;

import java.util.ArrayList;

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
        String single = title (name);
        String plural = single + "s";
        this.initialize (name, single, plural, price);
    }

    public Item (String name, String plural, int price) {
        String single = title (name);
        this.initialize (name, single, plural, price);
    }

    public Item (String name, String single, String plural, int price) {
        this.initialize (name, single, plural, price);
    }

    public static String title (String text) {
        final String letters = "abcdefghijklmnopqrstuvwxyz";
        text = "1" + text.toLowerCase ();
        boolean convert = false;
        for (int index = 0; index < text.length () - 1; index++) {
            String character = text.substring (index, index + 1);
            if (letters.contains (character) == false) {
                convert = true;
            } else if (convert) {
                text = text.substring (0, index) + character.toUpperCase () + text.substring (index + 1);
                convert = false;
            }
        }
        return text.substring (1);
    }

    public static void setup () {
        Item gold = new Item ("gold", "Gold", "Gold", 1000);
        Main.debug (gold.toString ());
    }

    public String toString () {
        String string = "";
        string += "Name: \"" + this.name + "\"\n";
        string += "Single: \"" + this.single + "\"\n";
        string += "Plural: \"" + this.plural + "\"\n";
        string += "Price: " + this.price;
        return string;
    }
}
