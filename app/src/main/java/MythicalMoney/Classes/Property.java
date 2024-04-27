package MythicalMoney.Classes;

import java.util.ArrayList;

import MythicalMoney.Classes.Display.Display;
import MythicalMoney.Classes.Obtainable.ToolType;

public class Property {
    public int [] chances;
    public Display display;
    public ToolType tool;

    public static final Property starterRange = new Property (new Display ("Starter Range"), ToolType.Weapon, new int [] {1, 2, 3, 4});
    public static ArrayList <Property> properties = new ArrayList <Property> ();

    public Property (Display display, ToolType tool, int [] chances) {
        this.display = display;
        this.chances = chances;
        this.tool = tool;

        properties.add (this);
    }

    public static Property [] toList (final ArrayList <Property> propertyArrayList) {
        final int size = propertyArrayList.size ();
        Property [] propertyList = new Property [size];
        for (int index = 0; index < size; index++) {
            propertyList [index] = propertyArrayList.get (index);
        }
        return propertyList;
    }

    public static Property [] toList () {
        Property [] propertiesList = toList (properties);
        return propertiesList;
    }

    public static Property [] get (final ToolType tool) {
        ArrayList <Property> toolProperties = new ArrayList <Property> ();
        for (Property property : properties) {
            if (property.tool == tool) {
                toolProperties.add (property);
            }
        }
        final Property [] toolPropertiesList = toList (toolProperties);
        return toolPropertiesList;
    }

    public static Property get (final String name) {
        Property [] propertiesList = toList ();
        for (Property property : propertiesList) {
            final boolean nameMatch = property.display.name.equals (name);
            if (nameMatch) {
                return property;
            }
        }
        return null;
    }
}