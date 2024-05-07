package MythicalMoney.Classes;

import MythicalMoney.Classes.Helpers.Chances;
import MythicalMoney.Classes.Helpers.Display;
import MythicalMoney.Classes.Helpers.Recipe;
import MythicalMoney.Classes.Obtainable.ToolType;

import java.util.ArrayList;

public class Land {

    public static final Land starterHuntingGround = new Land (new Display ("Starter Hunting Ground", "starter hunting ground"), ToolType.Weapon, new Chances.ChancesPlus (100, 0, 0, 0));
    public static final Land starterMine = new Land (new Display ("Starter Mine", "starter mine"), ToolType.Weapon, new Chances.ChancesPlus (100, 0, 0, 0));
    public static final Land starterForest = new Land (new Display ("Starter Forest", "starter forest"), ToolType.Axe, new Chances.ChancesPlus (100, 0, 0, 0));
    public static final Land starterFarm = new Land (new Display ("Starter Farm", "starter farm"), ToolType.Weapon, new Chances.ChancesPlus (100, 0, 0, 0));
    public static final ArrayList <Land> properties = new ArrayList <Land> ();
    public final Chances chances;
    public final Display display;
    public final ToolType toolType;

    public Land (Display display, ToolType toolType, Chances.ChancesPlus chances) {
        this.display = display;
        this.chances = chances;
        this.toolType = toolType;

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

    public static Land[] toList () {
        return toList (properties);
    }

    public static Land[] get (final ToolType toolType) {
        ArrayList <Land> toolProperties = new ArrayList <Land> ();
        for (Land land : properties) {
            if (land.toolType == toolType) {
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

    public static class LandPlus extends Land {
        public final Recipe recipe;
        public LandPlus (final Display display, final ToolType tool, final Chances.ChancesPlus chances, final Recipe recipe) {
            super (display, tool, chances);
            this.recipe = recipe;
        }
    }
}
