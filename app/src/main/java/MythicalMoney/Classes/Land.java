package MythicalMoney.Classes;

import MythicalMoney.Classes.Helpers.Chances;
import MythicalMoney.Classes.Helpers.Display;
import MythicalMoney.Classes.Helpers.Recipe;
import MythicalMoney.Classes.Obtainable.ToolType;

import java.util.ArrayList;

public class Land {

    public static final ArrayList <Land> lands = new ArrayList <Land> ();
    public static final Land starterHuntingGround = new Land (new Display ("Starter Hunting Ground", "starter hunting ground"), ToolType.Weapon, new Chances.ChancesPlus (100, 0, 0, 0), 1, null);
    public static final Land starterMine = new Land (new Display ("Starter Mine", "starter mine"), ToolType.Weapon, new Chances.ChancesPlus (100, 0, 0, 0), 1, null);
    public static final Land starterForest = new Land (new Display ("Starter Forest", "starter forest"), ToolType.Axe, new Chances.ChancesPlus (100, 0, 0, 0), 1, null);
    public static final Land starterFarm = new Land (new Display ("Starter Farm", "starter farm"), ToolType.Weapon, new Chances.ChancesPlus (100, 0, 0, 0), 1, null);
    public final Chances chances;
    public final Display display;
    public final ToolType toolType;
    public final int harvests;
    public final Recipe recipe;

    public Land (final Display display, final ToolType toolType, final Chances.ChancesPlus chances, final int harvests, final Recipe recipe) {
        this.display = display;
        this.chances = chances;
        this.toolType = toolType;
        this.harvests = harvests;
        this.recipe = recipe;
        lands.add (this);
    }

    public static Land[] get (final ToolType toolType) {
        ArrayList <Land> toolProperties = new ArrayList <Land> ();
        for (Land land : lands) {
            if (land.toolType == toolType) {
                toolProperties.add (land);
            }
        }
        return toList (toolProperties);
    }

    public static Land[] toList (final ArrayList <Land> landArrayList) {
        final int size = landArrayList.size ();
        Land[] landList = new Land[size];
        for (int index = 0; index < size; index++) {
            landList[index] = landArrayList.get (index);
        }
        return landList;
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

    public static Land[] toList () {
        return toList (lands);
    }

    public String verb () {
        switch (this.toolType) {
            case Weapon: {
                return "hunt";
            }
            case Pickaxe: {
                return "mine";
            }
            case Axe: {
                return "chop";
            }
            case Hoe: {
                return "harvest";
            }
            default: {
                return null;
            }
        }
    }

    public String verbPastTense () {
        switch (this.toolType) {
            case Weapon: {
                return "hunted";
            }
            case Pickaxe: {
                return "mined";
            }
            case Axe: {
                return "chopped";
            }
            case Hoe: {
                return "harvested";
            }
            default: {
                return null;
            }
        }
    }
}
