package MythicalMoney.Classes;

import java.util.ArrayList;

import MythicalMoney.Utility.BasicUtility;

public class Obtainable {
    public Display display;
    public Tool tool;

    public static ArrayList <Obtainable> obtainables = new ArrayList <Obtainable> ();

    public static final Obtainable copper = new Obtainable (new Display ("Copper Ore"), Tool.Pickaxe);     // 1,000
    public static final Obtainable titanium = new Obtainable (new Display ("Titanium Ore"), Tool.Pickaxe); // 5,000
    public static final Obtainable gold = new Obtainable (new Display ("Gold Ore"), Tool.Pickaxe);         //10,000
    public static final Obtainable diamond = new Obtainable (new Display ("Diamond Ore"), Tool.Pickaxe);   //25,000

    public static final Obtainable zombie = new Obtainable (new Display ("Zombie"), Tool.Weapon);
    public static final Obtainable ghost = new Obtainable (new Display ("Ghost"), Tool.Weapon);
    public static final Obtainable vampire = new Obtainable (new Display ("Vampire"), Tool.Weapon);
    public static final Obtainable dragon = new Obtainable(new Display ("Dragon"), Tool.Weapon);

    public static final Obtainable cherry = new Obtainable(new Display ("Cherry Tree"), Tool.Axe);
    public static final Obtainable maple = new Obtainable(new Display ("Maple Tree"), Tool.Axe);
    public static final Obtainable oak = new Obtainable (new Display ("Oak Tree"), Tool.Axe);
    public static final Obtainable sandal = new Obtainable(new Display ("Sandalwood Tree"), Tool.Axe);


    public static enum Tool {
        Weapon,
        Axe,
        Pickaxe,
        Hoe
    }

    public Obtainable (final Display display, final Tool tool) {
        this.display = display;
        this.tool = tool;
        obtainables.add (this);
    }

    public Obtainable (final String single, final String plural, final Tool tool) {
        this.display = new Display (single, plural);
        this.tool = tool;
        obtainables.add (this);
    }

    public static Obtainable [] toList () {
        final int size = obtainables.size ();
        Obtainable [] obtainableList = new Obtainable [size];
        for (int index = 0; index < size; index++) {
            obtainableList [index] = obtainables.get (index);
        }
        return obtainableList;
    }

    public static Obtainable find (String name) {
        name = name.toLowerCase ();
        final Obtainable [] obtainableList = toList ();
        for (Obtainable obtainable : obtainableList) {
            if (obtainable.display.single.toLowerCase ().contains (name)) {
                return obtainable;
            }
        }
        return null;
    }

    public String toString () {
        String string = "Tool: ";
        string += this.tool.toString ();
        {
            string += "\nDisplay:\n";
            string += BasicUtility.indent (this.display.toString ());
        }
        return string;
    }
}
