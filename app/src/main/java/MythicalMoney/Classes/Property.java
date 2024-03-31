package MythicalMoney.Classes;

import java.util.ArrayList;

import MythicalMoney.Classes.Obtainable.Tool;

public class Property {
    public int [] chances;
    public DisplayPlus display;
    public Tool tool;

    public static ArrayList <Property> properties = new ArrayList <Property> ();

    public Property (DisplayPlus display, Tool tool, int [] chances) {
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

    public static Property [] get (final Tool tool) {
        ArrayList <Property> toolProperties = new ArrayList <Property> ();
        for (Property property : properties) {
            if (property.tool == tool) {
                toolProperties.add (property);
            }
        }
        final Property [] toolPropertiesList = toList (toolProperties);
        return toolPropertiesList;
    }
}