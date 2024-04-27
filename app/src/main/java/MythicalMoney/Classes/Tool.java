package MythicalMoney.Classes;

import java.util.ArrayList;

import MythicalMoney.Classes.Display.Display;
import MythicalMoney.Classes.Obtainable.ToolType;

public class Tool {
    public int [] chances;
    public Display display;
    public int cooldown;
    public ToolType toolType; 

    public static ArrayList <Tool> all = new ArrayList <Tool> ();

    public static final Tool starterWeapon = Tool.weapon (new Display ("Starter Weapon", "starter weapon"), 60, new int [] {100, 0, 0, 0});
    public static final Tool starterAxe = Tool.weapon (new Display ("Starter Axe", "starter axe"), 60, new int [] {100, 0, 0, 0});
    public static final Tool starterHoe = Tool.hoe (new Display ("Starter Hoe", "starter hoe"), 60, new int [] {100, 0, 0, 0});
    public static final Tool starterPickaxe = Tool.pickaxe (new Display ("Starter Pickaxe", "starter pickaxe"), 60, new int[] {100, 0, 0, 0});
    
    public Tool (Display display, int cooldown, int [] chances, ToolType toolType) {
        this.display = display;
        this.cooldown = cooldown;
        this.chances = chances;
        this.toolType = toolType;

        all.add (this);
    }

    public static Tool get (final String name) {
        final int size = all.size ();
        for (int index = 0; index < size; index++) {
            final Tool tool = all.get (index);
            final boolean nameMatch = tool.display.plural.equals (name);
            if (nameMatch) {
                return tool;
            }
        }
        return null;
    }

    public static Tool weapon (Display display, int cooldown, int [] chances) {
        Tool weapon = new Tool(display, cooldown, chances, ToolType.Weapon);
        return weapon;
    }

    public static Tool axe (Display display, int cooldown, int [] chances) {
        Tool axe = new Tool(display, cooldown, chances, ToolType.Axe);
        return axe;
    }

    public static Tool hoe (Display display, int cooldown, int [] chances) {
        Tool hoe = new Tool(display, cooldown, chances, ToolType.Hoe);
        return hoe;
    }

    public static Tool pickaxe (Display display, int cooldown, int [] chances) {
        Tool pickaxe = new Tool(display, cooldown, chances, ToolType.Pickaxe);
        return pickaxe;
    }
}
