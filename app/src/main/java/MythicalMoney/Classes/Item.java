package MythicalMoney.Classes;

import java.util.ArrayList;

public class Item {
    public static ArrayList <Item> items = new ArrayList <Item> ();

    public DisplayPlus display;
    public int price;
    public Obtainable obtainable;
    public static boolean setup = false;

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

