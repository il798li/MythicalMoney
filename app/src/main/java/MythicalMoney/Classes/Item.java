package MythicalMoney.Classes;

import java.util.ArrayList;

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

    public static Item find (final String name) {
        for (Item item : toList ()) {
            if (item.display.name.equals (name)) {
                return item;
            }
        }
        return null;
    }

    public static void setup () {
        if (setup) {
            return;
        }

        //new Item (new DisplayPlus (), 1000,)

        setup = true;
    }
}

