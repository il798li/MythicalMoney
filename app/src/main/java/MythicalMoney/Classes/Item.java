package MythicalMoney.Classes;

import MythicalMoney.Classes.Helpers.Display;
import MythicalMoney.Utility.BasicUtility;

import java.util.ArrayList;

public class Item {

    public static final ArrayList <Item> items = new ArrayList <Item> ();
    public static final Item copper = new Item (new Display.DisplayPlus ("Copper", "Copper", "copper"), 1000, Obtainable.copper);
    public static final Item titanium = new Item (new Display.DisplayPlus ("Titanium", "Titanium", "titanium"), 5000, Obtainable.titanium);
    public static final Item gold = new Item (new Display.DisplayPlus ("Gold", "Gold", "gold"), 10000, Obtainable.gold);
    public static final Item diamond = new Item (new Display.DisplayPlus ("Diamond", "Diamond", "diamond"), 25000, Obtainable.diamond);
    public static final Item bone = new Item (new Display.DisplayPlus ("Bone"), 1000, Obtainable.zombie);
    public static final Item mist = new Item (new Display.DisplayPlus ("Mist", "Mist", "mist"), 5000, Obtainable.ghost);
    public static final Item fang = new Item (new Display.DisplayPlus ("Fang"), 10000, Obtainable.vampire);
    public static final Item flamer = new Item (new Display.DisplayPlus ("Flamer"), 25000, Obtainable.dragon);
    public static final Item cherry = new Item (new Display.DisplayPlus ("Cherry Wood", "Cherry Wood", "cherry"), 1000, Obtainable.cherry);
    public static final Item maple = new Item (new Display.DisplayPlus ("Maple Wood", "Maple Wood", "maple"), 5000, Obtainable.maple);
    public static final Item oak = new Item (new Display.DisplayPlus ("Oak Wood", "Oak Wood", "oak"), 10000, Obtainable.oak);
    public static final Item sandal = new Item (new Display.DisplayPlus ("Sandal Wood", "Sandal Wood", "sandal"), 25000, Obtainable.sandal);
    public static final Item wheat = new Item (new Display.DisplayPlus ("Wheat", "Wheat", "wheat"), 1000, Obtainable.wheat);
    public static final Item pumpkin = new Item (new Display.DisplayPlus ("Pumpkin"), 5000, Obtainable.pumpkin);
    public static final Item watermelon = new Item (new Display.DisplayPlus ("Watermelon"), 10000, Obtainable.watermelon);
    public static final Item bamboo = new Item (new Display.DisplayPlus ("Bamboo", "Bamboo", "bamboo"), 25000, Obtainable.bamboo);
    public final Display.DisplayPlus display;
    public final int price;
    public final Obtainable obtainable;

    public Item (final Display.DisplayPlus display, final int price, final Obtainable obtainable) {
        this.display = display;
        this.price = price;
        this.obtainable = obtainable;
        items.add (this);
    }

    public static Item get (final String name) {
        for (Item item : toList ()) {
            final boolean nameMatches = item.display.name.equals (name);
            if (nameMatches) {
                return item;
            }
        }
        return null;
    }

    public static Item[] toList () {
        final int size = items.size ();
        Item[] itemList = new Item[size];
        for (byte index = 0; index < size; index++) {
            itemList[index] = items.get (index);
        }
        return itemList;
    }

    public static int indexOf (final Item item) {
        return indexOf (item.display.name);
    }

    public static int indexOf (final String name) {
        final Item[] items = toList ();
        for (int index = 0; index < items.length; index++) {
            Item item = items[index];
            if (item.display.name.equals (name)) {
                return index;
            }
        }
        return -1;
    }

    public String toString () {
        String string = "Display:\n";
        {
            final String displayString = this.display.toString ();
            string += displayString;
        }
        {
            string += "\nPrice: ";
            string += this.price;
        }
        {
            string += "\nObtainable:\n";
            string += BasicUtility.indent (this.obtainable.toString ());
        }
        return string;
    }

    public static Item get (final Obtainable obtainable) {
        for (final Item item : items) {
            if (item.obtainable == obtainable)  {
                return item;
            }
        }
        return null;
    }
}

