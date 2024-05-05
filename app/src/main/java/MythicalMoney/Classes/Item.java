package MythicalMoney.Classes;

import java.util.ArrayList;

import MythicalMoney.Classes.Display.DisplayPlus;
import MythicalMoney.Utility.BasicUtility;

public class Item {
    public static ArrayList <Item> items = new ArrayList <Item> ();

    public DisplayPlus display;
    public int price;
    public Obtainable obtainable;
    public static boolean setup = false;

	public static final Item copper = new Item (new DisplayPlus ("Copper", "Copper", "copper"), 1000, Obtainable.copper);
	public static final Item titanium = new Item (new DisplayPlus ("Titanium", "Titanium", "titanium"), 5000, Obtainable.titanium);
	public static final Item gold = new Item (new DisplayPlus ("Gold", "Gold", "gold"), 10000, Obtainable.gold);
	public static final Item diamond = new Item (new DisplayPlus ("Diamond", "Diamond", "diamond"), 25000, Obtainable.diamond);

	public static final Item bone = new Item (new DisplayPlus ("Bone"), 1000, Obtainable.zombie);
	public static final Item mist = new Item (new DisplayPlus ("Mist", "Mist", "mist"), 5000, Obtainable.ghost);
	public static final Item fang = new Item (new DisplayPlus ("Fang"), 10000, Obtainable.vampire);
	public static final Item flamer = new Item (new DisplayPlus ("Flamer"), 25000, Obtainable.dragon);

	public static final Item cherry = new Item (new DisplayPlus ("Cherry Wood", "Cherry Wood", "cherry"), 1000, Obtainable.cherry);
	public static final Item maple = new Item (new DisplayPlus ("Maple Wood", "Maple Wood", "maple"), 5000, Obtainable.maple);
	public static final Item oak = new Item (new DisplayPlus ("Oak Wood", "Oak Wood", "oak"), 10000, Obtainable.oak);
	public static final Item sandal = new Item (new DisplayPlus ("Sandal Wood", "Sandal Wood", "sandal"), 25000, Obtainable.sandal);

    public Item (final DisplayPlus display, final int price, final Obtainable obtainable) {
        this.display = display;
        this.price = price;
        this.obtainable = obtainable;

        items.add (this);
    }

    public static Item [] toList () {
        final int size = items.size ();
        Item [] itemList = new Item [size];
        for (byte index = 0; index < size; index++) {
            itemList [index] = items.get (index);
        }
        return itemList;
    }

    public static Item get (final String name) {
        for (Item item : toList ()) {
            if (item.display.name.equals (name)) {
                return item;
            }
        }
        return null;
    }

    public static int indexOf (final String name) {
        final Item [] items = toList ();
        for (int index = 0; index < items.length; index++) {
            Item item = items [index];
            if (item.display.name.equals (name)) {
                return index;
            }
        }
        return -1;
    }

    public static int indexOf (final Item item) {
        return indexOf (item.display.name);
    }

    public String toString () {
        String string = "Display:\n";
        string += BasicUtility.indent (this.display.toString ());
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
}

