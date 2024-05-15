package MythicalMoney.Classes;

import MythicalMoney.Classes.Helpers.Chances;
import MythicalMoney.Classes.Helpers.Display;
import MythicalMoney.Classes.Obtainable.ToolType;

import java.util.ArrayList;

public class Tool {

    public static final ArrayList <Tool> tools = new ArrayList <Tool> ();
    public static final Tool starterWeapon = Tool.weapon (new Display ("Starter Weapon", "starter weapon"), 60, new Chances.ChancesPlus (100, 0, 0, 0), 1);
    public static final Tool starterAxe = Tool.axe (new Display ("Starter Axe", "starter axe"), 60, new Chances.ChancesPlus (100, 0, 0, 0), 1);
    public static final Tool starterHoe = Tool.hoe (new Display ("Starter Hoe", "starter hoe"), 60, new Chances.ChancesPlus (100, 0, 0, 0), 1);
    public static final Tool starterPickaxe = Tool.pickaxe (new Display ("Starter Pickaxe", "starter pickaxe"), 60, new Chances.ChancesPlus (100, 0, 0, 0), 1);
    public final Chances chances;
    public final Display display;
    public final int cooldown;
    public final ToolType toolType;
    public final int purity;

    public Tool (final Display display, final int cooldown, final Chances.ChancesPlus chances, final ToolType toolType, final int purity) {
        this.display = display;
        this.cooldown = cooldown;
        this.chances = chances;
        this.toolType = toolType;
        this.purity = purity;
        tools.add (this);
    }

    public static Tool get (final String name) {
        for (final Tool tool : tools) {
            final boolean nameMatch = tool.display.plural.equals (name);
            if (nameMatch) {
                return tool;
            }
        }
        return null;
    }

    public static Tool weapon (Display display, int cooldown, Chances.ChancesPlus chances, int purity) {
        return new Tool (display, cooldown, chances, ToolType.Weapon, purity);
    }

    public static Tool axe (Display display, int cooldown, Chances.ChancesPlus chances, int purity) {
        return new Tool (display, cooldown, chances, ToolType.Axe, purity);
    }

    public static Tool hoe (Display display, int cooldown, Chances.ChancesPlus chances, int purity) {
        return new Tool (display, cooldown, chances, ToolType.Hoe, purity);
    }

    public static Tool pickaxe (Display display, int cooldown, Chances.ChancesPlus chances, int purity) {
        return new Tool (display, cooldown, chances, ToolType.Pickaxe, purity);
    }
}
