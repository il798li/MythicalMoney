package MythicalMoney.Classes;

import java.util.ArrayList;

import MythicalMoney.Classes.Display.Display;
import MythicalMoney.Classes.Obtainable.ToolType;

public class Land {
    public int [] chances;
    public Display display;
    public ToolType tool;

    public static ArrayList <Land> properties = new ArrayList <Land> ();

    public static final Land starterHuntingGround = new Land(new Display ("Starter Hunting Ground", "starter hunting ground"), ToolType.Weapon, new int [] {100, 0, 0, 0});
    public static final Land starterMine = new Land(new Display ("Starter Mine", "starter mine"), ToolType.Weapon, new int [] {100, 0, 0, 0});
    public static final Land starterForest = new Land(new Display ("Starter Forest", "starter forest"), ToolType.Axe, new int [] {100, 0, 0, 0});
    public static final Land starterFarm = new Land(new Display ("Starter Farm", "starter farm"), ToolType.Weapon, new int [] {100, 0, 0, 0});

    public Land(Display display, ToolType tool, int [] chances) {
        this.display = display;
        this.chances = chances;
        this.tool = tool;

        properties.add (this);
    }

    public static Land[] toList (final ArrayList <Land> landArrayList) {
        final int size = landArrayList.size ();
        Land[] landList = new Land[size];
        for (int index = 0; index < size; index++) {
            landList[index] = landArrayList.get (index);
        }
        return landList;
    }

    public static Land[] toList() {
        return toList (properties);
    }

    public static Land[] get (final ToolType tool) {
        ArrayList <Land> toolProperties = new ArrayList <Land> ();
        for (Land land : properties) {
            if (land.tool == tool) {
                toolProperties.add (land);
            }
        }
        return toList (toolProperties);
    }

    public static Land get (final String name) {
        Land[] propertiesList = toList ();
        for (Land land : propertiesList) {
            final boolean nameMatch = land.display.plural.equals (name);
            if (nameMatch) {
                return land;
            }
        }
        return null;
    }
}