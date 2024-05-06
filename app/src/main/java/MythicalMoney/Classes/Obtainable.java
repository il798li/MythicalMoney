package MythicalMoney.Classes;

import MythicalMoney.Classes.Helpers.Display;
import MythicalMoney.Utility.BasicUtility;

import java.util.ArrayList;

public class Obtainable {

    public static final Obtainable copper = new Obtainable (new Display ("Copper Ore"), ToolType.Pickaxe);     // 1,000
    public static final Obtainable titanium = new Obtainable (new Display ("Titanium Ore"), ToolType.Pickaxe); // 5,000
    public static final Obtainable gold = new Obtainable (new Display ("Gold Ore"), ToolType.Pickaxe);         //10,000
    public static final Obtainable diamond = new Obtainable (new Display ("Diamond Ore"), ToolType.Pickaxe);   //25,000
    public static final Obtainable zombie = new Obtainable (new Display ("Zombie"), ToolType.Weapon);
    public static final Obtainable ghost = new Obtainable (new Display ("Ghost"), ToolType.Weapon);
    public static final Obtainable vampire = new Obtainable (new Display ("Vampire"), ToolType.Weapon);
    public static final Obtainable dragon = new Obtainable (new Display ("Dragon"), ToolType.Weapon);
    public static final Obtainable cherry = new Obtainable (new Display ("Cherry Tree"), ToolType.Axe);
    public static final Obtainable maple = new Obtainable (new Display ("Maple Tree"), ToolType.Axe);
    public static final Obtainable oak = new Obtainable (new Display ("Oak Tree"), ToolType.Axe);
    public static final Obtainable sandal = new Obtainable (new Display ("Sandalwood Tree"), ToolType.Axe);
    public static final ArrayList <Obtainable> obtainables = new ArrayList <Obtainable> ();
    public final Display display;
    public final ToolType tool;

    public Obtainable (final Display display, final ToolType tool) {
        this.display = display;
        this.tool = tool;
        obtainables.add (this);
    }

    public static Obtainable[] toList () {
        return toList (obtainables);
    }

    public static Obtainable[] toList (final ArrayList <Obtainable> obtainableArrayList) {
        Obtainable[] obtainableList = new Obtainable[obtainableArrayList.size ()];
        for (int index = 0; index < obtainableList.length; index++) {
            obtainableList[index] = obtainableArrayList.get (index);
        }
        return obtainableList;
    }

    public static Obtainable get (String name) {
        name = name.toLowerCase ();
        final Obtainable[] obtainableList = toList ();
        for (Obtainable obtainable : obtainableList) {
            if (obtainable.display.single.toLowerCase ().contains (name)) {
                return obtainable;
            }
        }
        return null;
    }

    public static Obtainable[] get (ToolType tool) {
        ArrayList <Obtainable> toolObtainables = new ArrayList <Obtainable> ();
        for (Obtainable obtainable : obtainables) {
            if (obtainable.tool == tool) {
                toolObtainables.add (obtainable);
            }
        }
        return toList (toolObtainables);
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

    public enum ToolType {
        Weapon,
        Axe,
        Pickaxe,
        Hoe
    }
}
